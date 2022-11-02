package org.acra;

import android.content.Context;
import gnu.kawa.servlet.HttpRequestContext;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Map;
import org.acra.collector.CrashReportData;

/* loaded from: classes.dex */
final class CrashReportPersister {
    private static final int CONTINUE = 3;
    private static final int IGNORE = 5;
    private static final int KEY_DONE = 4;
    private static final String LINE_SEPARATOR = "\n";
    private static final int NONE = 0;
    private static final int SLASH = 1;
    private static final int UNICODE = 2;
    private final Context context;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CrashReportPersister(Context context) {
        this.context = context;
    }

    public CrashReportData load(String fileName) throws IOException {
        CrashReportData load;
        FileInputStream in = this.context.openFileInput(fileName);
        if (in == null) {
            throw new IllegalArgumentException("Invalid crash report fileName : " + fileName);
        }
        try {
            BufferedInputStream bis = new BufferedInputStream(in, 8192);
            bis.mark(Integer.MAX_VALUE);
            boolean isEbcdic = isEbcdic(bis);
            bis.reset();
            if (!isEbcdic) {
                load = load(new InputStreamReader(bis, "ISO8859-1"));
            } else {
                load = load(new InputStreamReader(bis));
            }
            return load;
        } finally {
            in.close();
        }
    }

    public void store(CrashReportData crashData, String fileName) throws IOException {
        OutputStream out = this.context.openFileOutput(fileName, 0);
        try {
            StringBuilder buffer = new StringBuilder((int) HttpRequestContext.HTTP_OK);
            OutputStreamWriter writer = new OutputStreamWriter(out, "ISO8859_1");
            for (Map.Entry<ReportField, String> entry : crashData.entrySet()) {
                String key = entry.getKey().toString();
                dumpString(buffer, key, true);
                buffer.append('=');
                dumpString(buffer, entry.getValue(), false);
                buffer.append(LINE_SEPARATOR);
                writer.write(buffer.toString());
                buffer.setLength(0);
            }
            writer.flush();
        } finally {
            out.close();
        }
    }

    private boolean isEbcdic(BufferedInputStream in) throws IOException {
        byte b;
        do {
            b = (byte) in.read();
            if (b == -1 || b == 35 || b == 10 || b == 61) {
                return false;
            }
        } while (b != 21);
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:135:0x0172 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0130  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0178  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private synchronized org.acra.collector.CrashReportData load(java.io.Reader r22) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 540
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.acra.CrashReportPersister.load(java.io.Reader):org.acra.collector.CrashReportData");
    }

    private void dumpString(StringBuilder buffer, String string, boolean key) {
        int i = 0;
        if (!key && 0 < string.length() && string.charAt(0) == ' ') {
            buffer.append("\\ ");
            i = 0 + 1;
        }
        while (i < string.length()) {
            char ch = string.charAt(i);
            switch (ch) {
                case '\t':
                    buffer.append("\\t");
                    break;
                case '\n':
                    buffer.append("\\n");
                    break;
                case 11:
                default:
                    if ("\\#!=:".indexOf(ch) >= 0 || (key && ch == ' ')) {
                        buffer.append('\\');
                    }
                    if (ch >= ' ' && ch <= '~') {
                        buffer.append(ch);
                        break;
                    } else {
                        String hex = Integer.toHexString(ch);
                        buffer.append("\\u");
                        for (int j = 0; j < 4 - hex.length(); j++) {
                            buffer.append("0");
                        }
                        buffer.append(hex);
                        break;
                    }
                    break;
                case '\f':
                    buffer.append("\\f");
                    break;
                case '\r':
                    buffer.append("\\r");
                    break;
            }
            i++;
        }
    }
}
