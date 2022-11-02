package gnu.kawa.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Hashtable;
import java.util.StringTokenizer;

/* loaded from: classes.dex */
public class PreProcess {
    static final String JAVA4_FEATURES = "+JAVA2 +use:java.util.IdentityHashMap +use:java.lang.CharSequence +use:java.lang.Throwable.getCause +use:java.net.URI +use:java.util.regex +SAX2 +use:java.nio";
    static final String JAVA5_FEATURES = "+JAVA5 +JAVA2 +use:java.util.IdentityHashMap +use:java.lang.CharSequence +use:java.lang.Throwable.getCause +use:java.net.URI +use:java.util.regex +SAX2 +use:java.nio +use:org.w3c.dom.Node +use:javax.xml.transform +JAXP-1.3 -JAXP-QName";
    static final String NO_JAVA4_FEATURES = "-JAVA5 -use:java.util.IdentityHashMap -use:java.lang.CharSequence -use:java.lang.Throwable.getCause -use:java.net.URI -use:java.util.regex -use:org.w3c.dom.Node -JAXP-1.3 -use:javax.xml.transform -JAVA5 -JAVA6 -JAVA6COMPAT5 -JAXP-QName -use:java.text.Normalizer -SAX2 -use:java.nio -Android";
    static final String NO_JAVA6_FEATURES = "-JAVA6 -JAVA7 -use:java.dyn -use:java.text.Normalizer";
    static String[] version_features = {"java1", "-JAVA2 -JAVA5 -use:java.util.IdentityHashMap -use:java.lang.CharSequence -use:java.lang.Throwable.getCause -use:java.net.URI -use:java.util.regex -use:org.w3c.dom.Node -JAXP-1.3 -use:javax.xml.transform -JAVA5 -JAVA6 -JAVA6COMPAT5 -JAXP-QName -use:java.text.Normalizer -SAX2 -use:java.nio -Android -JAVA6 -JAVA7 -use:java.dyn -use:java.text.Normalizer", "java2", "+JAVA2 -JAVA5 -use:java.util.IdentityHashMap -use:java.lang.CharSequence -use:java.lang.Throwable.getCause -use:java.net.URI -use:java.util.regex -use:org.w3c.dom.Node -JAXP-1.3 -use:javax.xml.transform -JAVA5 -JAVA6 -JAVA6COMPAT5 -JAXP-QName -use:java.text.Normalizer -SAX2 -use:java.nio -Android -JAVA6 -JAVA7 -use:java.dyn -use:java.text.Normalizer", "java4", "-JAVA5 +JAVA2 +use:java.util.IdentityHashMap +use:java.lang.CharSequence +use:java.lang.Throwable.getCause +use:java.net.URI +use:java.util.regex +SAX2 +use:java.nio -use:org.w3c.dom.Node -JAXP-1.3 -use:javax.xml.transform -JAXP-QName -JAVA6COMPAT5 -Android -JAVA6 -JAVA7 -use:java.dyn -use:java.text.Normalizer", "java4x", "-JAVA5 +JAVA2 +use:java.util.IdentityHashMap +use:java.lang.CharSequence +use:java.lang.Throwable.getCause +use:java.net.URI +use:java.util.regex +SAX2 +use:java.nio +use:org.w3c.dom.Node +JAXP-1.3 +use:javax.xml.transform -JAXP-QName -JAVA6COMPAT5 -Android -JAVA6 -JAVA7 -use:java.dyn -use:java.text.Normalizer", "java5", "+JAVA5 +JAVA2 +use:java.util.IdentityHashMap +use:java.lang.CharSequence +use:java.lang.Throwable.getCause +use:java.net.URI +use:java.util.regex +SAX2 +use:java.nio +use:org.w3c.dom.Node +use:javax.xml.transform +JAXP-1.3 -JAXP-QName -JAVA6COMPAT5 -Android -JAVA6 -JAVA7 -use:java.dyn -use:java.text.Normalizer", "java6compat5", "+JAVA5 +JAVA2 +use:java.util.IdentityHashMap +use:java.lang.CharSequence +use:java.lang.Throwable.getCause +use:java.net.URI +use:java.util.regex +SAX2 +use:java.nio +use:org.w3c.dom.Node +use:javax.xml.transform +JAXP-1.3 -JAXP-QName -JAVA6 -JAVA7 +JAVA6COMPAT5 +use:java.text.Normalizer -use:java.dyn -Android", "java6", "+JAVA5 +JAVA2 +use:java.util.IdentityHashMap +use:java.lang.CharSequence +use:java.lang.Throwable.getCause +use:java.net.URI +use:java.util.regex +SAX2 +use:java.nio +use:org.w3c.dom.Node +use:javax.xml.transform +JAXP-1.3 -JAXP-QName +JAVA6 -JAVA7 -JAVA6COMPAT5 +use:java.text.Normalizer -use:java.dyn -Android", "java7", "+JAVA5 +JAVA2 +use:java.util.IdentityHashMap +use:java.lang.CharSequence +use:java.lang.Throwable.getCause +use:java.net.URI +use:java.util.regex +SAX2 +use:java.nio +use:org.w3c.dom.Node +use:javax.xml.transform +JAXP-1.3 -JAXP-QName +JAVA6 +JAVA7 -JAVA6COMPAT5 +use:java.text.Normalizer +use:java.dyn -Android", "android", "+JAVA5 +JAVA2 +use:java.util.IdentityHashMap +use:java.lang.CharSequence +use:java.lang.Throwable.getCause +use:java.net.URI +use:java.util.regex +SAX2 +use:java.nio +use:org.w3c.dom.Node +JAXP-1.3 -JAXP-QName -use:javax.xml.transform -JAVA6 -JAVA6COMPAT5 +Android -JAVA6 -JAVA7 -use:java.dyn -use:java.text.Normalizer"};
    String filename;
    Hashtable keywords = new Hashtable();
    int lineno;
    byte[] resultBuffer;
    int resultLength;

    void error(String str) {
        System.err.println(this.filename + ':' + this.lineno + ": " + str);
        System.exit(-1);
    }

    public void filter(String str) throws Throwable {
        if (filter(str, new BufferedInputStream(new FileInputStream(str)))) {
            FileOutputStream fileOutputStream = new FileOutputStream(str);
            fileOutputStream.write(this.resultBuffer, 0, this.resultLength);
            fileOutputStream.close();
            System.err.println("Pre-processed " + str);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0081, code lost:
        if (r5 >= 0) goto L185;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0083, code lost:
        if (r3 > 0) goto L185;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0087, code lost:
        if (r15 == 13) goto L185;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x008b, code lost:
        if (r15 == 10) goto L185;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x008d, code lost:
        if (r8 == r7) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0091, code lost:
        if (r15 == 32) goto L185;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0095, code lost:
        if (r15 == 9) goto L185;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0099, code lost:
        if (r15 != 47) goto L184;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x009b, code lost:
        r21.mark(100);
        r12 = r21.read();
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00aa, code lost:
        if (r12 != 47) goto L168;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00ac, code lost:
        r12 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00ad, code lost:
        r21.reset();
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00b0, code lost:
        if (r12 == false) goto L185;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00b2, code lost:
        r3 = r11 + 1;
        r13[r11] = 47;
        r12 = r3 + 1;
        r13[r3] = 47;
        r13[r12] = 32;
        r14 = true;
        r12 = r12 + 1;
        r11 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x0121, code lost:
        if (r12 != 42) goto L183;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x0123, code lost:
        r12 = r21.read();
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x012b, code lost:
        if (r12 == 32) goto L182;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x0131, code lost:
        if (r12 == 9) goto L180;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x0137, code lost:
        if (r12 == 35) goto L179;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x0139, code lost:
        r12 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x013c, code lost:
        r12 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x013f, code lost:
        r12 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x0142, code lost:
        r12 = true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean filter(java.lang.String r20, java.io.BufferedInputStream r21) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 800
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.util.PreProcess.filter(java.lang.String, java.io.BufferedInputStream):boolean");
    }

    void handleArg(String str) {
        int i = 0;
        if (str.charAt(0) == '%') {
            String substring = str.substring(1);
            while (true) {
                if (i >= version_features.length) {
                    System.err.println("Unknown version: " + substring);
                    System.exit(-1);
                }
                if (substring.equals(version_features[i])) {
                    break;
                }
                i += 2;
            }
            String str2 = version_features[i + 1];
            System.err.println("(variant " + substring + " maps to: " + str2 + ")");
            StringTokenizer stringTokenizer = new StringTokenizer(str2);
            while (stringTokenizer.hasMoreTokens()) {
                handleArg(stringTokenizer.nextToken());
            }
        } else if (str.charAt(0) == '+') {
            this.keywords.put(str.substring(1), Boolean.TRUE);
        } else if (str.charAt(0) == '-') {
            int indexOf = str.indexOf(61);
            if (indexOf > 1) {
                String substring2 = str.substring(str.charAt(1) == '-' ? 2 : 1, indexOf);
                String substring3 = str.substring(indexOf + 1);
                Boolean bool = Boolean.FALSE;
                if (substring3.equalsIgnoreCase("true")) {
                    bool = Boolean.TRUE;
                } else if (!substring3.equalsIgnoreCase("false")) {
                    System.err.println("invalid value " + substring3 + " for " + substring2);
                    System.exit(-1);
                }
                this.keywords.put(substring2, bool);
                return;
            }
            this.keywords.put(str.substring(1), Boolean.FALSE);
        } else {
            try {
                filter(str);
            } catch (Throwable th) {
                System.err.println("caught " + th);
                th.printStackTrace();
                System.exit(-1);
            }
        }
    }

    public static void main(String[] strArr) {
        PreProcess preProcess = new PreProcess();
        preProcess.keywords.put("true", Boolean.TRUE);
        preProcess.keywords.put("false", Boolean.FALSE);
        for (String str : strArr) {
            preProcess.handleArg(str);
        }
    }
}
