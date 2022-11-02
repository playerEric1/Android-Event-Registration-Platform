package gnu.kawa.servlet;

import com.google.appinventor.components.runtime.util.NanoHTTPD;
import gnu.expr.Compilation;
import gnu.expr.Language;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleContext;
import gnu.expr.ModuleExp;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleManager;
import gnu.mapping.CallContext;
import gnu.mapping.Environment;
import gnu.mapping.InPort;
import gnu.text.Path;
import gnu.text.SourceMessages;
import gnu.text.SyntaxException;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Hashtable;

/* loaded from: classes.dex */
public class KawaAutoHandler {
    static final String MODULE_MAP_ATTRIBUTE = "gnu.kawa.module-map";

    public static void run(HttpRequestContext hctx, CallContext ctx) throws Throwable {
        boolean saveClass = hctx.getRequestParameter("qexo-save-class") != null;
        Object mod = getModule(hctx, ctx, saveClass);
        if (mod instanceof ModuleBody) {
            ((ModuleBody) mod).run(ctx);
        }
    }

    public static Object getModule(HttpRequestContext hctx, CallContext ctx, boolean saveClass) throws Exception {
        Compilation comp;
        String path = hctx.getRequestPath().substring(hctx.getContextPath().length() - 1);
        Hashtable mmap = (Hashtable) hctx.getAttribute(MODULE_MAP_ATTRIBUTE);
        if (mmap == null) {
            mmap = new Hashtable();
            hctx.setAttribute(MODULE_MAP_ATTRIBUTE, mmap);
        }
        ModuleContext mcontext = (ModuleContext) hctx.getAttribute("gnu.kawa.module-context");
        if (mcontext == null) {
            mcontext = ModuleContext.getContext();
        }
        mcontext.addFlags(ModuleContext.IN_HTTP_SERVER);
        if (hctx.getClass().getName().endsWith("KawaServlet$Context")) {
            mcontext.addFlags(ModuleContext.IN_SERVLET);
        }
        ModuleInfo minfo = (ModuleInfo) mmap.get(path);
        long now = System.currentTimeMillis();
        ModuleManager mmanager = mcontext.getManager();
        if (minfo != null && now - minfo.lastCheckedTime < mmanager.lastModifiedCacheTime) {
            return mcontext.findInstance(minfo);
        }
        int plen = path.length();
        URL url = (plen == 0 || path.charAt(plen + (-1)) == '/') ? null : hctx.getResourceURL(path);
        String upath = path;
        if (url == null) {
            String xpath = path;
            while (true) {
                int sl = xpath.lastIndexOf(47);
                if (sl >= 0) {
                    xpath = xpath.substring(0, sl);
                    upath = xpath + "/+default+";
                    url = hctx.getResourceURL(upath);
                    if (url != null) {
                        hctx.setScriptAndLocalPath(path.substring(1, sl + 1), path.substring(sl + 1));
                        break;
                    }
                } else {
                    break;
                }
            }
        } else {
            hctx.setScriptAndLocalPath(path, "");
        }
        if (url == null) {
            String msg = "The requested URL " + path + " was not found on this server. res/:" + hctx.getResourceURL("/") + "\r\n";
            byte[] bmsg = msg.getBytes();
            hctx.sendResponseHeaders(404, null, bmsg.length);
            try {
                hctx.getResponseStream().write(bmsg);
                return null;
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        String urlString = url.toExternalForm();
        if (minfo == null || !urlString.equals(minfo.getSourceAbsPathname())) {
            minfo = mmanager.findWithURL(url);
        }
        if (minfo.checkCurrent(mmanager, now)) {
            return mcontext.findInstance(minfo);
        }
        mmap.put(path, minfo);
        Path absPath = minfo.getSourceAbsPath();
        InputStream resourceStream = absPath.openInputStream();
        if (!(resourceStream instanceof BufferedInputStream)) {
            resourceStream = new BufferedInputStream(resourceStream);
        }
        Language language = Language.getInstanceFromFilenameExtension(path);
        if (language != null) {
            hctx.log("Compile " + path + " - a " + language.getName() + " source file (based on extension)");
        } else {
            language = Language.detect(resourceStream);
            if (language != null) {
                hctx.log("Compile " + path + " - a " + language.getName() + " source file (detected from content)");
            } else if (path != upath) {
                String msg2 = "The requested URL " + path + " was not found on this server. upath=" + upath + ".\r\n";
                byte[] bmsg2 = msg2.getBytes();
                hctx.sendResponseHeaders(404, null, bmsg2.length);
                try {
                    hctx.getResponseStream().write(bmsg2);
                    return null;
                } catch (IOException ex2) {
                    throw new RuntimeException(ex2);
                }
            } else {
                long len = absPath.getContentLength();
                hctx.sendResponseHeaders(HttpRequestContext.HTTP_OK, null, len);
                OutputStream out = hctx.getResponseStream();
                byte[] buffer = new byte[4096];
                while (true) {
                    int n = resourceStream.read(buffer);
                    if (n >= 0) {
                        out.write(buffer, 0, n);
                    } else {
                        resourceStream.close();
                        out.close();
                        return null;
                    }
                }
            }
        }
        InPort port = new InPort(resourceStream, absPath);
        Language.setCurrentLanguage(language);
        SourceMessages messages = new SourceMessages();
        try {
            comp = language.parse(port, messages, 9, minfo);
        } catch (SyntaxException ex3) {
            if (ex3.getMessages() != messages) {
                throw ex3;
            }
            comp = null;
        }
        Class cl = null;
        if (!messages.seenErrors()) {
            comp.getModule();
            Environment env = Environment.getCurrent();
            cl = (Class) ModuleExp.evalModule1(env, comp, url, null);
        }
        if (messages.seenErrors()) {
            String msg3 = "script syntax error:\n" + messages.toString(20);
            ((ServletPrinter) ctx.consumer).addHeader("Content-type", NanoHTTPD.MIME_PLAINTEXT);
            hctx.sendResponseHeaders(500, "Syntax errors", -1L);
            ctx.consumer.write(msg3);
            minfo.cleanupAfterCompilation();
            return null;
        }
        minfo.setModuleClass(cl);
        return mcontext.findInstance(minfo);
    }
}
