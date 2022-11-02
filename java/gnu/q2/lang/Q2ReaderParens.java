package gnu.q2.lang;

import gnu.kawa.lispexpr.ReaderDispatchMisc;
import gnu.text.Lexer;
import gnu.text.LineBufferedReader;
import gnu.text.SyntaxException;
import java.io.IOException;

/* compiled from: Q2Read.java */
/* loaded from: classes.dex */
class Q2ReaderParens extends ReaderDispatchMisc {
    @Override // gnu.kawa.lispexpr.ReaderDispatchMisc, gnu.kawa.lispexpr.ReadTableEntry
    public Object read(Lexer in, int ch, int count) throws IOException, SyntaxException {
        Q2Read reader = (Q2Read) in;
        char saveReadState = reader.pushNesting('(');
        try {
            Object result = reader.readCommand(true);
            LineBufferedReader port = reader.getPort();
            if (port.read() != 41) {
                reader.error("missing ')'");
            }
            return result;
        } finally {
            reader.popNesting(saveReadState);
        }
    }
}
