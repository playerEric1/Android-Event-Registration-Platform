package com.google.appinventor.components.runtime.util;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import com.google.appinventor.components.runtime.ReplForm;
import com.google.appinventor.components.runtime.util.NanoHTTPD;
import gnu.expr.Language;
import gnu.expr.ModuleExp;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Formatter;
import java.util.Properties;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import kawa.standard.Scheme;

/* loaded from: classes.dex */
public class AppInvHTTPD extends NanoHTTPD {
    private static final String LOG_TAG = "AppInvHTTPD";
    private static final String MIME_JSON = "application/json";
    private static final int YAV_SKEW_BACKWARD = 4;
    private static final int YAV_SKEW_FORWARD = 1;
    private static byte[] hmacKey;
    private static int seq;
    private final Handler androidUIHandler;
    private ReplForm form;
    private File rootDir;
    private Language scheme;
    private boolean secure;

    public AppInvHTTPD(int port, File wwwroot, boolean secure, ReplForm form) throws IOException {
        super(port, wwwroot);
        this.androidUIHandler = new Handler();
        this.rootDir = wwwroot;
        this.scheme = Scheme.getInstance("scheme");
        this.form = form;
        this.secure = secure;
        ModuleExp.mustNeverCompile();
    }

    @Override // com.google.appinventor.components.runtime.util.NanoHTTPD
    public NanoHTTPD.Response serve(String uri, String method, Properties header, Properties parms, Properties files, Socket mySocket) {
        NanoHTTPD.Response res;
        String installer;
        NanoHTTPD.Response res2;
        Log.d(LOG_TAG, method + " '" + uri + "' ");
        if (this.secure) {
            InetAddress myAddress = mySocket.getInetAddress();
            String hostAddress = myAddress.getHostAddress();
            if (!hostAddress.equals("127.0.0.1")) {
                Log.d(LOG_TAG, "Debug: hostAddress = " + hostAddress + " while in secure mode, closing connection.");
                NanoHTTPD.Response res3 = new NanoHTTPD.Response(NanoHTTPD.HTTP_OK, MIME_JSON, "{\"status\" : \"BAD\", \"message\" : \"Security Error: Invalid Source Location " + hostAddress + "\"}");
                res3.addHeader("Access-Control-Allow-Origin", "*");
                res3.addHeader("Access-Control-Allow-Headers", "origin, content-type");
                res3.addHeader("Access-Control-Allow-Methods", "POST,OPTIONS,GET,HEAD,PUT");
                res3.addHeader("Allow", "POST,OPTIONS,GET,HEAD,PUT");
                return res3;
            }
        }
        if (method.equals("OPTIONS")) {
            Enumeration e = header.propertyNames();
            while (e.hasMoreElements()) {
                String value = (String) e.nextElement();
                Log.d(LOG_TAG, "  HDR: '" + value + "' = '" + header.getProperty(value) + "'");
            }
            NanoHTTPD.Response res4 = new NanoHTTPD.Response(NanoHTTPD.HTTP_OK, NanoHTTPD.MIME_PLAINTEXT, "OK");
            res4.addHeader("Access-Control-Allow-Origin", "*");
            res4.addHeader("Access-Control-Allow-Headers", "origin, content-type");
            res4.addHeader("Access-Control-Allow-Methods", "POST,OPTIONS,GET,HEAD,PUT");
            res4.addHeader("Allow", "POST,OPTIONS,GET,HEAD,PUT");
            return res4;
        } else if (uri.equals("/_newblocks")) {
            String inSeq = parms.getProperty("seq", "0");
            int iseq = Integer.parseInt(inSeq);
            String blockid = parms.getProperty("blockid");
            String code = parms.getProperty("code");
            String inMac = parms.getProperty("mac", "no key provided");
            if (hmacKey != null) {
                try {
                    Mac hmacSha1 = Mac.getInstance("HmacSHA1");
                    SecretKeySpec key = new SecretKeySpec(hmacKey, "RAW");
                    hmacSha1.init(key);
                    byte[] tmpMac = hmacSha1.doFinal((code + inSeq + blockid).getBytes());
                    StringBuffer sb = new StringBuffer(tmpMac.length * 2);
                    Formatter formatter = new Formatter(sb);
                    for (byte b : tmpMac) {
                        formatter.format("%02x", Byte.valueOf(b));
                    }
                    String compMac = sb.toString();
                    Log.d(LOG_TAG, "Incoming Mac = " + inMac);
                    Log.d(LOG_TAG, "Computed Mac = " + compMac);
                    Log.d(LOG_TAG, "Incoming seq = " + inSeq);
                    Log.d(LOG_TAG, "Computed seq = " + seq);
                    Log.d(LOG_TAG, "blockid = " + blockid);
                    if (!inMac.equals(compMac)) {
                        Log.e(LOG_TAG, "Hmac does not match");
                        this.form.dispatchErrorOccurredEvent(this.form, LOG_TAG, ErrorMessages.ERROR_REPL_SECURITY_ERROR, "Invalid HMAC");
                        NanoHTTPD.Response res5 = new NanoHTTPD.Response(NanoHTTPD.HTTP_OK, MIME_JSON, "{\"status\" : \"BAD\", \"message\" : \"Security Error: Invalid MAC\"}");
                        return res5;
                    } else if (seq != iseq && seq != iseq + 1) {
                        Log.e(LOG_TAG, "Seq does not match");
                        this.form.dispatchErrorOccurredEvent(this.form, LOG_TAG, ErrorMessages.ERROR_REPL_SECURITY_ERROR, "Invalid Seq");
                        NanoHTTPD.Response res6 = new NanoHTTPD.Response(NanoHTTPD.HTTP_OK, MIME_JSON, "{\"status\" : \"BAD\", \"message\" : \"Security Error: Invalid Seq\"}");
                        return res6;
                    } else {
                        if (seq == iseq + 1) {
                            Log.e(LOG_TAG, "Seq Fixup Invoked");
                        }
                        seq = iseq + 1;
                        String code2 = "(begin (require <com.google.youngandroid.runtime>) (process-repl-input " + blockid + " (begin " + code + " )))";
                        Log.d(LOG_TAG, "To Eval: " + code2);
                        try {
                            if (code.equals("#f")) {
                                Log.e(LOG_TAG, "Skipping evaluation of #f");
                            } else {
                                this.scheme.eval(code2);
                            }
                            res2 = new NanoHTTPD.Response(NanoHTTPD.HTTP_OK, MIME_JSON, RetValManager.fetch(false));
                        } catch (Throwable ex) {
                            Log.e(LOG_TAG, "newblocks: Scheme Failure", ex);
                            RetValManager.appendReturnValue(blockid, "BAD", ex.toString());
                            res2 = new NanoHTTPD.Response(NanoHTTPD.HTTP_OK, MIME_JSON, RetValManager.fetch(false));
                        }
                        res2.addHeader("Access-Control-Allow-Origin", "*");
                        res2.addHeader("Access-Control-Allow-Headers", "origin, content-type");
                        res2.addHeader("Access-Control-Allow-Methods", "POST,OPTIONS,GET,HEAD,PUT");
                        res2.addHeader("Allow", "POST,OPTIONS,GET,HEAD,PUT");
                        return res2;
                    }
                } catch (Exception e2) {
                    Log.e(LOG_TAG, "Error working with hmac", e2);
                    this.form.dispatchErrorOccurredEvent(this.form, LOG_TAG, ErrorMessages.ERROR_REPL_SECURITY_ERROR, "Exception working on HMAC");
                    NanoHTTPD.Response res7 = new NanoHTTPD.Response(NanoHTTPD.HTTP_OK, NanoHTTPD.MIME_PLAINTEXT, "NOT");
                    return res7;
                }
            }
            Log.e(LOG_TAG, "No HMAC Key");
            this.form.dispatchErrorOccurredEvent(this.form, LOG_TAG, ErrorMessages.ERROR_REPL_SECURITY_ERROR, "No HMAC Key");
            NanoHTTPD.Response res8 = new NanoHTTPD.Response(NanoHTTPD.HTTP_OK, MIME_JSON, "{\"status\" : \"BAD\", \"message\" : \"Security Error: No HMAC Key\"}");
            return res8;
        } else if (uri.equals("/_values")) {
            NanoHTTPD.Response res9 = new NanoHTTPD.Response(NanoHTTPD.HTTP_OK, MIME_JSON, RetValManager.fetch(true));
            res9.addHeader("Access-Control-Allow-Origin", "*");
            res9.addHeader("Access-Control-Allow-Headers", "origin, content-type");
            res9.addHeader("Access-Control-Allow-Methods", "POST,OPTIONS,GET,HEAD,PUT");
            res9.addHeader("Allow", "POST,OPTIONS,GET,HEAD,PUT");
            return res9;
        } else if (uri.equals("/_getversion")) {
            try {
                String packageName = this.form.getPackageName();
                PackageInfo pInfo = this.form.getPackageManager().getPackageInfo(packageName, 0);
                if (SdkLevel.getLevel() >= 5) {
                    installer = EclairUtil.getInstallerPackageName("edu.mit.appinventor.aicompanion3", this.form);
                } else {
                    installer = "Not Known";
                }
                String versionName = pInfo.versionName;
                if (installer == null) {
                    installer = "Not Known";
                }
                res = new NanoHTTPD.Response(NanoHTTPD.HTTP_OK, MIME_JSON, "{\"version\" : \"" + versionName + "\", \"fingerprint\" : \"" + Build.FINGERPRINT + "\", \"installer\" : \"" + installer + "\", \"package\" : \"" + packageName + "\" }");
            } catch (PackageManager.NameNotFoundException n) {
                n.printStackTrace();
                res = new NanoHTTPD.Response(NanoHTTPD.HTTP_OK, MIME_JSON, "{\"verison\" : \"Unknown\"");
            }
            res.addHeader("Access-Control-Allow-Origin", "*");
            res.addHeader("Access-Control-Allow-Headers", "origin, content-type");
            res.addHeader("Access-Control-Allow-Methods", "POST,OPTIONS,GET,HEAD,PUT");
            res.addHeader("Allow", "POST,OPTIONS,GET,HEAD,PUT");
            if (this.secure) {
                seq = 1;
                this.androidUIHandler.post(new Runnable() { // from class: com.google.appinventor.components.runtime.util.AppInvHTTPD.1
                    @Override // java.lang.Runnable
                    public void run() {
                        AppInvHTTPD.this.form.clear();
                    }
                });
                return res;
            }
            return res;
        } else if (uri.equals("/_update") || uri.equals("/_install")) {
            String url = parms.getProperty("url", "");
            String inMac2 = parms.getProperty("mac", "");
            if (!url.equals("") && hmacKey != null && !inMac2.equals("")) {
                try {
                    SecretKeySpec key2 = new SecretKeySpec(hmacKey, "RAW");
                    Mac hmacSha12 = Mac.getInstance("HmacSHA1");
                    hmacSha12.init(key2);
                    byte[] tmpMac2 = hmacSha12.doFinal(url.getBytes());
                    StringBuffer sb2 = new StringBuffer(tmpMac2.length * 2);
                    Formatter formatter2 = new Formatter(sb2);
                    for (byte b2 : tmpMac2) {
                        formatter2.format("%02x", Byte.valueOf(b2));
                    }
                    String compMac2 = sb2.toString();
                    Log.d(LOG_TAG, "Incoming Mac (update) = " + inMac2);
                    Log.d(LOG_TAG, "Computed Mac (update) = " + compMac2);
                    if (!inMac2.equals(compMac2)) {
                        Log.e(LOG_TAG, "Hmac does not match");
                        this.form.dispatchErrorOccurredEvent(this.form, LOG_TAG, ErrorMessages.ERROR_REPL_SECURITY_ERROR, "Invalid HMAC (update)");
                        NanoHTTPD.Response res10 = new NanoHTTPD.Response(NanoHTTPD.HTTP_OK, MIME_JSON, "{\"status\" : \"BAD\", \"message\" : \"Security Error: Invalid MAC\"}");
                        res10.addHeader("Access-Control-Allow-Origin", "*");
                        res10.addHeader("Access-Control-Allow-Headers", "origin, content-type");
                        res10.addHeader("Access-Control-Allow-Methods", "POST,OPTIONS,GET,HEAD,PUT");
                        res10.addHeader("Allow", "POST,OPTIONS,GET,HEAD,PUT");
                        return res10;
                    }
                    doPackageUpdate(url);
                    NanoHTTPD.Response res11 = new NanoHTTPD.Response(NanoHTTPD.HTTP_OK, MIME_JSON, "{\"status\" : \"OK\", \"message\" : \"Update Should Happen\"}");
                    res11.addHeader("Access-Control-Allow-Origin", "*");
                    res11.addHeader("Access-Control-Allow-Headers", "origin, content-type");
                    res11.addHeader("Access-Control-Allow-Methods", "POST,OPTIONS,GET,HEAD,PUT");
                    res11.addHeader("Allow", "POST,OPTIONS,GET,HEAD,PUT");
                    return res11;
                } catch (Exception e3) {
                    Log.e(LOG_TAG, "Error verifying update", e3);
                    this.form.dispatchErrorOccurredEvent(this.form, LOG_TAG, ErrorMessages.ERROR_REPL_SECURITY_ERROR, "Exception working on HMAC for update");
                    NanoHTTPD.Response res12 = new NanoHTTPD.Response(NanoHTTPD.HTTP_OK, MIME_JSON, "{\"status\" : \"BAD\", \"message\" : \"Security Error: Exception processing MAC\"}");
                    res12.addHeader("Access-Control-Allow-Origin", "*");
                    res12.addHeader("Access-Control-Allow-Headers", "origin, content-type");
                    res12.addHeader("Access-Control-Allow-Methods", "POST,OPTIONS,GET,HEAD,PUT");
                    res12.addHeader("Allow", "POST,OPTIONS,GET,HEAD,PUT");
                    return res12;
                }
            }
            NanoHTTPD.Response res13 = new NanoHTTPD.Response(NanoHTTPD.HTTP_OK, MIME_JSON, "{\"status\" : \"BAD\", \"message\" : \"Missing Parameters\"}");
            res13.addHeader("Access-Control-Allow-Origin", "*");
            res13.addHeader("Access-Control-Allow-Headers", "origin, content-type");
            res13.addHeader("Access-Control-Allow-Methods", "POST,OPTIONS,GET,HEAD,PUT");
            res13.addHeader("Allow", "POST,OPTIONS,GET,HEAD,PUT");
            return res13;
        } else if (uri.equals("/_package")) {
            String packageapk = parms.getProperty("package", null);
            if (packageapk == null) {
                NanoHTTPD.Response res14 = new NanoHTTPD.Response(NanoHTTPD.HTTP_OK, NanoHTTPD.MIME_PLAINTEXT, "NOT OK");
                return res14;
            }
            Log.d(LOG_TAG, this.rootDir + "/" + packageapk);
            Intent intent = new Intent("android.intent.action.VIEW");
            Uri packageuri = Uri.fromFile(new File(this.rootDir + "/" + packageapk));
            intent.setDataAndType(packageuri, "application/vnd.android.package-archive");
            this.form.startActivity(intent);
            NanoHTTPD.Response res15 = new NanoHTTPD.Response(NanoHTTPD.HTTP_OK, NanoHTTPD.MIME_PLAINTEXT, "OK");
            return res15;
        } else if (method.equals("PUT")) {
            Boolean error = false;
            String tmpFileName = files.getProperty("content", null);
            if (tmpFileName != null) {
                File fileFrom = new File(tmpFileName);
                String filename = parms.getProperty("filename", null);
                if (filename != null && (filename.startsWith("..") || filename.endsWith("..") || filename.indexOf("../") >= 0)) {
                    Log.d(LOG_TAG, " Ignoring invalid filename: " + filename);
                    filename = null;
                }
                if (filename != null) {
                    File fileTo = new File(this.rootDir + "/" + filename);
                    if (!fileFrom.renameTo(fileTo)) {
                        copyFile(fileFrom, fileTo);
                        fileFrom.delete();
                    }
                } else {
                    fileFrom.delete();
                    Log.e(LOG_TAG, "Received content without a file name!");
                    error = true;
                }
            } else {
                Log.e(LOG_TAG, "Received PUT without content.");
                error = true;
            }
            if (error.booleanValue()) {
                NanoHTTPD.Response res16 = new NanoHTTPD.Response(NanoHTTPD.HTTP_OK, NanoHTTPD.MIME_PLAINTEXT, "NOTOK");
                res16.addHeader("Access-Control-Allow-Origin", "*");
                res16.addHeader("Access-Control-Allow-Headers", "origin, content-type");
                res16.addHeader("Access-Control-Allow-Methods", "POST,OPTIONS,GET,HEAD,PUT");
                res16.addHeader("Allow", "POST,OPTIONS,GET,HEAD,PUT");
                return res16;
            }
            NanoHTTPD.Response res17 = new NanoHTTPD.Response(NanoHTTPD.HTTP_OK, NanoHTTPD.MIME_PLAINTEXT, "OK");
            res17.addHeader("Access-Control-Allow-Origin", "*");
            res17.addHeader("Access-Control-Allow-Headers", "origin, content-type");
            res17.addHeader("Access-Control-Allow-Methods", "POST,OPTIONS,GET,HEAD,PUT");
            res17.addHeader("Allow", "POST,OPTIONS,GET,HEAD,PUT");
            return res17;
        } else {
            Enumeration e4 = header.propertyNames();
            while (e4.hasMoreElements()) {
                String value2 = (String) e4.nextElement();
                Log.d(LOG_TAG, "  HDR: '" + value2 + "' = '" + header.getProperty(value2) + "'");
            }
            Enumeration e5 = parms.propertyNames();
            while (e5.hasMoreElements()) {
                String value3 = (String) e5.nextElement();
                Log.d(LOG_TAG, "  PRM: '" + value3 + "' = '" + parms.getProperty(value3) + "'");
            }
            Enumeration e6 = files.propertyNames();
            if (e6.hasMoreElements()) {
                String fieldname = (String) e6.nextElement();
                String tempLocation = files.getProperty(fieldname);
                String filename2 = parms.getProperty(fieldname);
                if (filename2.startsWith("..") || filename2.endsWith("..") || filename2.indexOf("../") >= 0) {
                    Log.d(LOG_TAG, " Ignoring invalid filename: " + filename2);
                    filename2 = null;
                }
                File fileFrom2 = new File(tempLocation);
                if (filename2 == null) {
                    fileFrom2.delete();
                } else {
                    File fileTo2 = new File(this.rootDir + "/" + filename2);
                    if (!fileFrom2.renameTo(fileTo2)) {
                        copyFile(fileFrom2, fileTo2);
                        fileFrom2.delete();
                    }
                }
                Log.d(LOG_TAG, " UPLOADED: '" + filename2 + "' was at '" + tempLocation + "'");
                NanoHTTPD.Response res18 = new NanoHTTPD.Response(NanoHTTPD.HTTP_OK, NanoHTTPD.MIME_PLAINTEXT, "OK");
                res18.addHeader("Access-Control-Allow-Origin", "*");
                res18.addHeader("Access-Control-Allow-Headers", "origin, content-type");
                res18.addHeader("Access-Control-Allow-Methods", "POST,OPTIONS,GET,HEAD,PUT");
                res18.addHeader("Allow", "POST,OPTIONS,GET,HEAD,PUT");
                return res18;
            }
            NanoHTTPD.Response res19 = serveFile(uri, header, this.rootDir, true);
            return res19;
        }
    }

    private void copyFile(File infile, File outfile) {
        try {
            FileInputStream in = new FileInputStream(infile);
            FileOutputStream out = new FileOutputStream(outfile);
            byte[] buffer = new byte[32768];
            while (true) {
                int len = in.read(buffer);
                if (len > 0) {
                    out.write(buffer, 0, len);
                } else {
                    in.close();
                    out.close();
                    return;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setHmacKey(String inputKey) {
        hmacKey = inputKey.getBytes();
        seq = 1;
    }

    private void doPackageUpdate(String inurl) {
        PackageInstaller.doPackageInstall(this.form, inurl);
    }

    public void resetSeq() {
        seq = 1;
    }
}
