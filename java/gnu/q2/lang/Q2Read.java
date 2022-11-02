package gnu.q2.lang;

import gnu.kawa.lispexpr.LispReader;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.Sequence;
import gnu.mapping.InPort;
import gnu.text.SourceMessages;
import gnu.text.SyntaxException;
import java.io.IOException;
import kawa.standard.begin;

/* loaded from: classes.dex */
public class Q2Read extends LispReader {
    int curIndentation;
    int expressionStartColumn;
    String expressionStartFile;
    int expressionStartLine;

    void init() {
        ((InPort) this.port).readState = ' ';
    }

    public Q2Read(InPort port) {
        super(port);
        init();
    }

    public Q2Read(InPort port, SourceMessages messages) {
        super(port, messages);
        init();
    }

    int skipIndentation() throws IOException, SyntaxException {
        int numTabs = 0;
        int numSpaces = 0;
        int ch = this.port.read();
        while (ch == 9) {
            numTabs++;
            ch = this.port.read();
        }
        while (ch == 32) {
            numSpaces++;
            ch = this.port.read();
        }
        if (ch < 0) {
            return -1;
        }
        this.port.unread();
        return (numTabs << 16) + numSpaces;
    }

    Object readIndentCommand() throws IOException, SyntaxException {
        int startIndentation = this.curIndentation;
        LList rresult = LList.Empty;
        LList lList = LList.Empty;
        while (true) {
            int ch = read();
            if (ch < 0) {
                break;
            } else if (ch != 32 && ch != 9) {
                unread();
                if (ch == 41) {
                    break;
                } else if (ch == 13 || ch == 10) {
                    break;
                } else {
                    int line = this.port.getLineNumber();
                    int column = this.port.getColumnNumber();
                    Object val = readObject();
                    rresult = makePair(val, rresult, line, column);
                }
            }
        }
        if (!singleLine()) {
            read();
            this.port.mark(Integer.MAX_VALUE);
            int subIndentation = skipIndentation();
            LList qresult = LList.Empty;
            this.curIndentation = subIndentation;
            while (true) {
                if (this.curIndentation == -1 || subIndentation != this.curIndentation) {
                    break;
                }
                int comparedIndent = Q2.compareIndentation(subIndentation, startIndentation);
                if (comparedIndent == Integer.MIN_VALUE) {
                    error('e', "cannot compare indentation - mix of tabs and spaces");
                    break;
                } else if (comparedIndent != -1 && comparedIndent != 1) {
                    if (comparedIndent <= 0) {
                        break;
                    }
                    int line2 = this.port.getLineNumber();
                    int column2 = this.port.getColumnNumber();
                    Object val2 = readIndentCommand();
                    qresult = makePair(val2, qresult, line2, column2);
                } else {
                    break;
                }
            }
            error('e', "indentation must differ by 2 or more");
            if (qresult != LList.Empty) {
                rresult = new Pair(new Pair(begin.begin, LList.reverseInPlace(qresult)), rresult);
            }
        }
        return LList.reverseInPlace(rresult);
    }

    boolean singleLine() {
        return this.interactive && this.nesting == 0;
    }

    @Override // gnu.kawa.lispexpr.LispReader
    public Object readCommand() throws IOException, SyntaxException {
        int indent = skipIndentation();
        if (indent < 0) {
            return Sequence.eofValue;
        }
        this.curIndentation = indent;
        Object readIndentCommand = readIndentCommand();
        if (!this.interactive) {
            this.port.reset();
            return readIndentCommand;
        }
        return readIndentCommand;
    }

    /* JADX WARN: Code restructure failed: missing block: B:59:0x0161, code lost:
        if (r7 != null) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0021, code lost:
        if (r23 != false) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0023, code lost:
        if (r14 != r7) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:?, code lost:
        return r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:?, code lost:
        return r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:?, code lost:
        return gnu.expr.QuoteExp.voidExp;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0029, code lost:
        return r7.getCar();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object readCommand(boolean r23) throws java.io.IOException, gnu.text.SyntaxException {
        /*
            Method dump skipped, instructions count: 359
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.q2.lang.Q2Read.readCommand(boolean):java.lang.Object");
    }

    public static Object readObject(InPort port) throws IOException, SyntaxException {
        return new Q2Read(port).readObject();
    }

    void saveExpressionStartPosition() {
        this.expressionStartFile = this.port.getName();
        this.expressionStartLine = this.port.getLineNumber();
        this.expressionStartColumn = this.port.getColumnNumber();
    }
}
