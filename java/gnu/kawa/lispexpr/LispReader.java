package gnu.kawa.lispexpr;

import gnu.bytecode.Access;
import gnu.expr.Keyword;
import gnu.expr.QuoteExp;
import gnu.expr.Special;
import gnu.kawa.util.GeneralHashTable;
import gnu.lists.FString;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.lists.Sequence;
import gnu.mapping.Environment;
import gnu.mapping.InPort;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.math.IntNum;
import gnu.text.Char;
import gnu.text.Lexer;
import gnu.text.LineBufferedReader;
import gnu.text.SourceMessages;
import gnu.text.SyntaxException;
import java.io.IOException;

/* loaded from: classes.dex */
public class LispReader extends Lexer {
    static final int SCM_COMPLEX = 1;
    public static final int SCM_NUMBERS = 1;
    public static final char TOKEN_ESCAPE_CHAR = 65535;
    protected boolean seenEscapes;
    GeneralHashTable<Integer, Object> sharedStructureTable;

    public LispReader(LineBufferedReader port) {
        super(port);
    }

    public LispReader(LineBufferedReader port, SourceMessages messages) {
        super(port, messages);
    }

    public final void readNestedComment(char c1, char c2) throws IOException, SyntaxException {
        int commentNesting = 1;
        int startLine = this.port.getLineNumber();
        int startColumn = this.port.getColumnNumber();
        do {
            int c = read();
            if (c == 124) {
                c = read();
                if (c == c1) {
                    commentNesting--;
                }
            } else if (c == c1 && (c = read()) == c2) {
                commentNesting++;
            }
            if (c < 0) {
                eofError("unexpected end-of-file in " + c1 + c2 + " comment starting here", startLine + 1, startColumn - 1);
                return;
            }
        } while (commentNesting > 0);
    }

    static char getReadCase() {
        try {
            String read_case_string = Environment.getCurrent().get("symbol-read-case", "P").toString();
            char read_case = read_case_string.charAt(0);
            if (read_case != 'P') {
                if (read_case == 'u') {
                    return 'U';
                }
                if (read_case == 'd' || read_case == 'l' || read_case == 'L') {
                    return 'D';
                }
                if (read_case == 'i') {
                    return Access.INNERCLASS_CONTEXT;
                }
                return read_case;
            }
            return read_case;
        } catch (Exception e) {
            return 'P';
        }
    }

    public Object readValues(int ch, ReadTable rtable) throws IOException, SyntaxException {
        return readValues(ch, rtable.lookup(ch), rtable);
    }

    public Object readValues(int ch, ReadTableEntry entry, ReadTable rtable) throws IOException, SyntaxException {
        int startPos = this.tokenBufferLength;
        this.seenEscapes = false;
        int kind = entry.getKind();
        switch (kind) {
            case 0:
                String err = "invalid character #\\" + ((char) ch);
                if (this.interactive) {
                    fatal(err);
                } else {
                    error(err);
                }
                return Values.empty;
            case 1:
                return Values.empty;
            case 2:
            case 3:
            case 4:
            default:
                return readAndHandleToken(ch, startPos, rtable);
            case 5:
            case 6:
                return entry.read(this, ch, -1);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Object readAndHandleToken(int ch, int startPos, ReadTable rtable) throws IOException, SyntaxException {
        int j;
        Object value;
        readToken(ch, getReadCase(), rtable);
        int endPos = this.tokenBufferLength;
        if (this.seenEscapes || (value = parseNumber(this.tokenBuffer, startPos, endPos - startPos, (char) 0, 0, 1)) == null || (value instanceof String)) {
            char readCase = getReadCase();
            if (readCase == 'I') {
                int upperCount = 0;
                int lowerCount = 0;
                int i = startPos;
                while (i < endPos) {
                    char ci = this.tokenBuffer[i];
                    if (ci == 65535) {
                        i++;
                    } else if (Character.isLowerCase(ci)) {
                        lowerCount++;
                    } else if (Character.isUpperCase(ci)) {
                        upperCount++;
                    }
                    i++;
                }
                if (lowerCount == 0) {
                    readCase = 'D';
                } else if (upperCount == 0) {
                    readCase = 'U';
                } else {
                    readCase = 'P';
                }
            }
            boolean handleUri = endPos >= startPos + 2 && this.tokenBuffer[endPos + (-1)] == '}' && this.tokenBuffer[endPos + (-2)] != 65535 && peek() == 58;
            int packageMarker = -1;
            int lbrace = -1;
            int rbrace = -1;
            int braceNesting = 0;
            int i2 = startPos;
            int j2 = startPos;
            while (i2 < endPos) {
                char ci2 = this.tokenBuffer[i2];
                if (ci2 == 65535) {
                    i2++;
                    if (i2 < endPos) {
                        j = j2 + 1;
                        this.tokenBuffer[j2] = this.tokenBuffer[i2];
                    } else {
                        j = j2;
                    }
                } else {
                    if (handleUri) {
                        if (ci2 == '{') {
                            if (lbrace < 0) {
                                lbrace = j2;
                            } else if (braceNesting == 0) {
                            }
                            braceNesting++;
                        } else if (ci2 == '}' && braceNesting - 1 >= 0 && braceNesting == 0 && rbrace < 0) {
                            rbrace = j2;
                        }
                    }
                    if (ci2 == ':') {
                        packageMarker = packageMarker >= 0 ? -1 : j2;
                    } else if (readCase == 'U') {
                        ci2 = Character.toUpperCase(ci2);
                    } else if (readCase == 'D') {
                        ci2 = Character.toLowerCase(ci2);
                    }
                    j = j2 + 1;
                    this.tokenBuffer[j2] = ci2;
                }
                i2++;
                j2 = j;
            }
            int endPos2 = j2;
            int len = endPos2 - startPos;
            if (lbrace >= 0 && rbrace > lbrace) {
                String prefix = lbrace > 0 ? new String(this.tokenBuffer, startPos, lbrace - startPos) : null;
                int lbrace2 = lbrace + 1;
                String uri = new String(this.tokenBuffer, lbrace2, rbrace - lbrace2);
                read();
                int ch2 = read();
                Object rightOperand = readValues(ch2, rtable.lookup(ch2), rtable);
                if (!(rightOperand instanceof SimpleSymbol)) {
                    error("expected identifier in symbol after '{URI}:'");
                }
                Object value2 = Symbol.valueOf(rightOperand.toString(), uri, prefix);
                return value2;
            } else if (rtable.initialColonIsKeyword && packageMarker == startPos && len > 1) {
                int startPos2 = startPos + 1;
                String str = new String(this.tokenBuffer, startPos2, endPos2 - startPos2);
                Object value3 = Keyword.make(str.intern());
                return value3;
            } else if (rtable.finalColonIsKeyword && packageMarker == endPos2 - 1 && (len > 1 || this.seenEscapes)) {
                String str2 = new String(this.tokenBuffer, startPos, len - 1);
                Object value4 = Keyword.make(str2.intern());
                return value4;
            } else {
                Object value5 = rtable.makeSymbol(new String(this.tokenBuffer, startPos, len));
                return value5;
            }
        }
        return value;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0032, code lost:
        unread(r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0035, code lost:
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void readToken(int r9, char r10, gnu.kawa.lispexpr.ReadTable r11) throws java.io.IOException, gnu.text.SyntaxException {
        /*
            r8 = this;
            r7 = 65535(0xffff, float:9.1834E-41)
            r5 = 1
            r2 = 0
            r0 = 0
        L6:
            if (r9 >= 0) goto Lf
            if (r2 == 0) goto L35
            java.lang.String r6 = "unexpected EOF between escapes"
            r8.eofError(r6)
        Lf:
            gnu.kawa.lispexpr.ReadTableEntry r1 = r11.lookup(r9)
            int r3 = r1.getKind()
            if (r3 != 0) goto L36
            if (r2 == 0) goto L26
            r8.tokenBufferAppend(r7)
            r8.tokenBufferAppend(r9)
        L21:
            int r9 = r8.read()
            goto L6
        L26:
            r6 = 125(0x7d, float:1.75E-43)
            if (r9 != r6) goto L32
            int r0 = r0 + (-1)
            if (r0 < 0) goto L32
            r8.tokenBufferAppend(r9)
            goto L21
        L32:
            r8.unread(r9)
        L35:
            return
        L36:
            char r6 = r11.postfixLookupOperator
            if (r9 != r6) goto L51
            if (r2 != 0) goto L51
            gnu.text.LineBufferedReader r6 = r8.port
            int r4 = r6.peek()
            char r6 = r11.postfixLookupOperator
            if (r4 != r6) goto L4a
            r8.unread(r9)
            goto L35
        L4a:
            boolean r6 = r8.validPostfixLookupStart(r4, r11)
            if (r6 == 0) goto L51
            r3 = 5
        L51:
            r6 = 3
            if (r3 != r6) goto L78
            int r9 = r8.read()
            if (r9 >= 0) goto L5f
            java.lang.String r6 = "unexpected EOF after single escape"
            r8.eofError(r6)
        L5f:
            boolean r6 = r11.hexEscapeAfterBackslash
            if (r6 == 0) goto L6f
            r6 = 120(0x78, float:1.68E-43)
            if (r9 == r6) goto L6b
            r6 = 88
            if (r9 != r6) goto L6f
        L6b:
            int r9 = r8.readHexEscape()
        L6f:
            r8.tokenBufferAppend(r7)
            r8.tokenBufferAppend(r9)
            r8.seenEscapes = r5
            goto L21
        L78:
            r6 = 4
            if (r3 != r6) goto L83
            if (r2 != 0) goto L81
            r2 = r5
        L7e:
            r8.seenEscapes = r5
            goto L21
        L81:
            r2 = 0
            goto L7e
        L83:
            if (r2 == 0) goto L8c
            r8.tokenBufferAppend(r7)
            r8.tokenBufferAppend(r9)
            goto L21
        L8c:
            switch(r3) {
                case 1: goto L90;
                case 2: goto L94;
                case 3: goto L8f;
                case 4: goto La2;
                case 5: goto La7;
                case 6: goto L9e;
                default: goto L8f;
            }
        L8f:
            goto L21
        L90:
            r8.unread(r9)
            goto L35
        L94:
            r6 = 123(0x7b, float:1.72E-43)
            if (r9 != r6) goto L9e
            gnu.kawa.lispexpr.ReadTableEntry r6 = gnu.kawa.lispexpr.ReadTableEntry.brace
            if (r1 != r6) goto L9e
            int r0 = r0 + 1
        L9e:
            r8.tokenBufferAppend(r9)
            goto L21
        La2:
            r2 = 1
            r8.seenEscapes = r5
            goto L21
        La7:
            r8.unread(r9)
            goto L35
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.lispexpr.LispReader.readToken(int, char, gnu.kawa.lispexpr.ReadTable):void");
    }

    public Object readObject() throws IOException, SyntaxException {
        int line;
        int column;
        Object value;
        char saveReadState = ((InPort) this.port).readState;
        int startPos = this.tokenBufferLength;
        ((InPort) this.port).readState = ' ';
        try {
            ReadTable rtable = ReadTable.getCurrent();
            do {
                line = this.port.getLineNumber();
                column = this.port.getColumnNumber();
                int ch = this.port.read();
                if (ch < 0) {
                    return Sequence.eofValue;
                }
                value = readValues(ch, rtable);
            } while (value == Values.empty);
            return handlePostfix(value, rtable, line, column);
        } finally {
            this.tokenBufferLength = startPos;
            ((InPort) this.port).readState = saveReadState;
        }
    }

    protected boolean validPostfixLookupStart(int ch, ReadTable rtable) throws IOException {
        if (ch < 0 || ch == 58 || ch == rtable.postfixLookupOperator) {
            return false;
        }
        if (ch == 44) {
            return true;
        }
        int kind = rtable.lookup(ch).getKind();
        return kind == 2 || kind == 6 || kind == 4 || kind == 3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Object handlePostfix(Object value, ReadTable rtable, int line, int column) throws IOException, SyntaxException {
        if (value == QuoteExp.voidExp) {
            value = Values.empty;
        }
        while (true) {
            int ch = this.port.peek();
            if (ch < 0 || ch != rtable.postfixLookupOperator) {
                break;
            }
            this.port.read();
            int ch2 = this.port.peek();
            if (!validPostfixLookupStart(ch2, rtable)) {
                unread();
                break;
            }
            int ch3 = this.port.read();
            Object rightOperand = readValues(ch3, rtable.lookup(ch3), rtable);
            value = PairWithPosition.make(LispLanguage.lookup_sym, LList.list2(value, LList.list2(rtable.makeSymbol(LispLanguage.quasiquote_sym), rightOperand)), this.port.getName(), line + 1, column + 1);
        }
        return value;
    }

    private boolean isPotentialNumber(char[] buffer, int start, int end) {
        int sawDigits = 0;
        for (int i = start; i < end; i++) {
            char ch = buffer[i];
            if (Character.isDigit(ch)) {
                sawDigits++;
            } else if (ch == '-' || ch == '+') {
                if (i + 1 == end) {
                    return false;
                }
            } else if (ch == '#') {
                return true;
            } else {
                if (Character.isLetter(ch) || ch == '/' || ch == '_' || ch == '^') {
                    if (i == start) {
                        return false;
                    }
                } else if (ch != '.') {
                    return false;
                }
            }
        }
        return sawDigits > 0;
    }

    public static Object parseNumber(CharSequence str, int radix) {
        char[] buf;
        if (str instanceof FString) {
            buf = ((FString) str).data;
        } else {
            buf = str.toString().toCharArray();
        }
        int len = str.length();
        return parseNumber(buf, 0, len, (char) 0, radix, 1);
    }

    /* JADX WARN: Code restructure failed: missing block: B:120:0x01a9, code lost:
        r33 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:121:0x01ab, code lost:
        if (r4 >= 0) goto L288;
     */
    /* JADX WARN: Code restructure failed: missing block: B:122:0x01ad, code lost:
        if (r49 == false) goto L139;
     */
    /* JADX WARN: Code restructure failed: missing block: B:124:0x01b3, code lost:
        if ((r11 + 4) >= r25) goto L139;
     */
    /* JADX WARN: Code restructure failed: missing block: B:126:0x01bb, code lost:
        if (r53[r11 + 3] != '.') goto L139;
     */
    /* JADX WARN: Code restructure failed: missing block: B:128:0x01c3, code lost:
        if (r53[r11 + 4] != '0') goto L139;
     */
    /* JADX WARN: Code restructure failed: missing block: B:130:0x01c9, code lost:
        if (r53[r11] != 'i') goto L132;
     */
    /* JADX WARN: Code restructure failed: missing block: B:132:0x01d1, code lost:
        if (r53[r11 + 1] != 'n') goto L132;
     */
    /* JADX WARN: Code restructure failed: missing block: B:134:0x01d9, code lost:
        if (r53[r11 + 2] != 'f') goto L132;
     */
    /* JADX WARN: Code restructure failed: missing block: B:135:0x01db, code lost:
        r33 = 'i';
     */
    /* JADX WARN: Code restructure failed: missing block: B:136:0x01dd, code lost:
        if (r33 != 0) goto L141;
     */
    /* JADX WARN: Code restructure failed: missing block: B:137:0x01df, code lost:
        return "no digits";
     */
    /* JADX WARN: Code restructure failed: missing block: B:186:0x0299, code lost:
        if (r53[r11] != 'n') goto L139;
     */
    /* JADX WARN: Code restructure failed: missing block: B:188:0x02a1, code lost:
        if (r53[r11 + 1] != 'a') goto L139;
     */
    /* JADX WARN: Code restructure failed: missing block: B:190:0x02a9, code lost:
        if (r53[r11 + 2] != 'n') goto L139;
     */
    /* JADX WARN: Code restructure failed: missing block: B:191:0x02ab, code lost:
        r33 = 'n';
     */
    /* JADX WARN: Code restructure failed: missing block: B:192:0x02af, code lost:
        r42 = r11 + 5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:193:0x02b3, code lost:
        if (r29 != false) goto L144;
     */
    /* JADX WARN: Code restructure failed: missing block: B:194:0x02b5, code lost:
        if (r51 == false) goto L144;
     */
    /* JADX WARN: Code restructure failed: missing block: B:196:0x02bb, code lost:
        if (r56 == 'i') goto L287;
     */
    /* JADX WARN: Code restructure failed: missing block: B:198:0x02c1, code lost:
        if (r56 == 'I') goto L287;
     */
    /* JADX WARN: Code restructure failed: missing block: B:200:0x02c7, code lost:
        if (r56 != ' ') goto L151;
     */
    /* JADX WARN: Code restructure failed: missing block: B:201:0x02c9, code lost:
        if (r29 == false) goto L151;
     */
    /* JADX WARN: Code restructure failed: missing block: B:202:0x02cb, code lost:
        r32 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:203:0x02cd, code lost:
        r26 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:204:0x02d1, code lost:
        if (r33 == 0) goto L238;
     */
    /* JADX WARN: Code restructure failed: missing block: B:206:0x02d9, code lost:
        if (r33 != 'i') goto L237;
     */
    /* JADX WARN: Code restructure failed: missing block: B:207:0x02db, code lost:
        r20 = Double.POSITIVE_INFINITY;
     */
    /* JADX WARN: Code restructure failed: missing block: B:209:0x02df, code lost:
        if (r7 == false) goto L160;
     */
    /* JADX WARN: Code restructure failed: missing block: B:210:0x02e1, code lost:
        r20 = -r20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:211:0x02e6, code lost:
        r37 = new gnu.math.DFloNum(r20);
     */
    /* JADX WARN: Code restructure failed: missing block: B:213:0x02f1, code lost:
        if (r56 == 'e') goto L236;
     */
    /* JADX WARN: Code restructure failed: missing block: B:215:0x02f7, code lost:
        if (r56 != 'E') goto L165;
     */
    /* JADX WARN: Code restructure failed: missing block: B:216:0x02f9, code lost:
        r37 = r37.toExact();
     */
    /* JADX WARN: Code restructure failed: missing block: B:218:0x0301, code lost:
        if (r42 >= r25) goto L221;
     */
    /* JADX WARN: Code restructure failed: missing block: B:219:0x0303, code lost:
        r11 = r42 + 1;
        r17 = r53[r42];
     */
    /* JADX WARN: Code restructure failed: missing block: B:220:0x030b, code lost:
        if (r17 != '@') goto L183;
     */
    /* JADX WARN: Code restructure failed: missing block: B:221:0x030d, code lost:
        r16 = parseNumber(r53, r11, r25 - r11, r56, 10, r58);
     */
    /* JADX WARN: Code restructure failed: missing block: B:222:0x031f, code lost:
        if ((r16 instanceof java.lang.String) != false) goto L182;
     */
    /* JADX WARN: Code restructure failed: missing block: B:224:0x0325, code lost:
        if ((r16 instanceof gnu.math.RealNum) != false) goto L174;
     */
    /* JADX WARN: Code restructure failed: missing block: B:225:0x0327, code lost:
        return "invalid complex polar constant";
     */
    /* JADX WARN: Code restructure failed: missing block: B:226:0x032b, code lost:
        r32 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:227:0x032e, code lost:
        r20 = Double.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:228:0x0331, code lost:
        if (r28 >= 0) goto L271;
     */
    /* JADX WARN: Code restructure failed: missing block: B:229:0x0333, code lost:
        if (r22 < 0) goto L240;
     */
    /* JADX WARN: Code restructure failed: missing block: B:231:0x0337, code lost:
        if (r4 <= r22) goto L275;
     */
    /* JADX WARN: Code restructure failed: missing block: B:232:0x0339, code lost:
        if (r22 < 0) goto L275;
     */
    /* JADX WARN: Code restructure failed: missing block: B:233:0x033b, code lost:
        r4 = r22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:234:0x033d, code lost:
        if (r39 == null) goto L278;
     */
    /* JADX WARN: Code restructure failed: missing block: B:236:0x0345, code lost:
        r50 = new java.lang.String(r53, r4, r42 - r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:237:0x0350, code lost:
        if (r28 < 0) goto L283;
     */
    /* JADX WARN: Code restructure failed: missing block: B:238:0x0352, code lost:
        r26 = java.lang.Character.toLowerCase(r53[r28]);
     */
    /* JADX WARN: Code restructure failed: missing block: B:239:0x035c, code lost:
        if (r26 == 'e') goto L283;
     */
    /* JADX WARN: Code restructure failed: missing block: B:240:0x035e, code lost:
        r43 = r28 - r4;
        r50 = r50.substring(0, r43) + 'e' + r50.substring(r43 + 1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:241:0x0388, code lost:
        r20 = gnu.lists.Convert.parseDouble(r50);
     */
    /* JADX WARN: Code restructure failed: missing block: B:242:0x038e, code lost:
        if (r7 == false) goto L286;
     */
    /* JADX WARN: Code restructure failed: missing block: B:243:0x0390, code lost:
        r20 = -r20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:244:0x0395, code lost:
        r37 = new gnu.math.DFloNum(r20);
     */
    /* JADX WARN: Code restructure failed: missing block: B:245:0x039e, code lost:
        r34 = valueOf(r53, r4, r42 - r4, r57, r7, r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:246:0x03a8, code lost:
        if (r39 != null) goto L254;
     */
    /* JADX WARN: Code restructure failed: missing block: B:247:0x03aa, code lost:
        r38 = r34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:248:0x03ae, code lost:
        if (r32 == false) goto L253;
     */
    /* JADX WARN: Code restructure failed: missing block: B:250:0x03b4, code lost:
        if (r38.isExact() == false) goto L253;
     */
    /* JADX WARN: Code restructure failed: missing block: B:252:0x03b8, code lost:
        if (r40 == false) goto L252;
     */
    /* JADX WARN: Code restructure failed: missing block: B:254:0x03be, code lost:
        if (r38.isZero() == false) goto L252;
     */
    /* JADX WARN: Code restructure failed: missing block: B:255:0x03c0, code lost:
        r5 = -0.0d;
     */
    /* JADX WARN: Code restructure failed: missing block: B:256:0x03c2, code lost:
        r37 = new gnu.math.DFloNum(r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:258:0x03cd, code lost:
        if (r34.isZero() == false) goto L270;
     */
    /* JADX WARN: Code restructure failed: missing block: B:259:0x03cf, code lost:
        r41 = r39.isZero();
     */
    /* JADX WARN: Code restructure failed: missing block: B:260:0x03d3, code lost:
        if (r32 == false) goto L266;
     */
    /* JADX WARN: Code restructure failed: missing block: B:262:0x03d7, code lost:
        if (r41 == false) goto L263;
     */
    /* JADX WARN: Code restructure failed: missing block: B:263:0x03d9, code lost:
        r5 = Double.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:264:0x03db, code lost:
        r37 = new gnu.math.DFloNum(r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:265:0x03e0, code lost:
        r38 = r37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:266:0x03e3, code lost:
        if (r40 == false) goto L265;
     */
    /* JADX WARN: Code restructure failed: missing block: B:267:0x03e5, code lost:
        r5 = Double.NEGATIVE_INFINITY;
     */
    /* JADX WARN: Code restructure failed: missing block: B:268:0x03e8, code lost:
        r5 = Double.POSITIVE_INFINITY;
     */
    /* JADX WARN: Code restructure failed: missing block: B:269:0x03eb, code lost:
        if (r41 == false) goto L269;
     */
    /* JADX WARN: Code restructure failed: missing block: B:271:0x03f3, code lost:
        r37 = gnu.math.RatNum.make(r39, r34);
     */
    /* JADX WARN: Code restructure failed: missing block: B:272:0x03fc, code lost:
        r37 = gnu.math.RatNum.make(r39, r34);
        r38 = r37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:273:0x0407, code lost:
        r5 = r38.doubleValue();
     */
    /* JADX WARN: Code restructure failed: missing block: B:274:0x040c, code lost:
        r45 = (gnu.math.RealNum) r16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:275:0x0414, code lost:
        if (r37.isZero() == false) goto L180;
     */
    /* JADX WARN: Code restructure failed: missing block: B:277:0x041a, code lost:
        if (r45.isExact() != false) goto L180;
     */
    /* JADX WARN: Code restructure failed: missing block: B:281:0x0435, code lost:
        if (r17 == '-') goto L208;
     */
    /* JADX WARN: Code restructure failed: missing block: B:283:0x043b, code lost:
        if (r17 != '+') goto L187;
     */
    /* JADX WARN: Code restructure failed: missing block: B:284:0x043d, code lost:
        r11 = r11 - 1;
        r31 = parseNumber(r53, r11, r25 - r11, r56, 10, r58);
     */
    /* JADX WARN: Code restructure failed: missing block: B:285:0x0451, code lost:
        if ((r31 instanceof java.lang.String) == false) goto L212;
     */
    /* JADX WARN: Code restructure failed: missing block: B:288:0x045b, code lost:
        if ((r31 instanceof gnu.math.Complex) != false) goto L216;
     */
    /* JADX WARN: Code restructure failed: missing block: B:290:0x047a, code lost:
        r18 = (gnu.math.Complex) r31;
        r46 = r18.re();
     */
    /* JADX WARN: Code restructure failed: missing block: B:291:0x0486, code lost:
        if (r46.isZero() != false) goto L219;
     */
    /* JADX WARN: Code restructure failed: missing block: B:292:0x0488, code lost:
        return "invalid numeric constant";
     */
    /* JADX WARN: Code restructure failed: missing block: B:294:0x0498, code lost:
        r35 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:296:0x049e, code lost:
        if (java.lang.Character.isLetter(r17) != false) goto L190;
     */
    /* JADX WARN: Code restructure failed: missing block: B:297:0x04a0, code lost:
        r11 = r11 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:299:0x04a5, code lost:
        if (r35 != 1) goto L205;
     */
    /* JADX WARN: Code restructure failed: missing block: B:300:0x04a7, code lost:
        r44 = r53[r11 - 1];
     */
    /* JADX WARN: Code restructure failed: missing block: B:301:0x04af, code lost:
        if (r44 == 'i') goto L200;
     */
    /* JADX WARN: Code restructure failed: missing block: B:303:0x04b5, code lost:
        if (r44 != 'I') goto L205;
     */
    /* JADX WARN: Code restructure failed: missing block: B:305:0x04b9, code lost:
        if (r11 >= r25) goto L203;
     */
    /* JADX WARN: Code restructure failed: missing block: B:306:0x04bb, code lost:
        return "junk after imaginary suffix 'i'";
     */
    /* JADX WARN: Code restructure failed: missing block: B:307:0x04bf, code lost:
        r35 = r35 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:308:0x04c3, code lost:
        if (r11 == r25) goto L193;
     */
    /* JADX WARN: Code restructure failed: missing block: B:309:0x04c5, code lost:
        r17 = r53[r11];
        r11 = r11 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:311:0x04d8, code lost:
        return "excess junk after number";
     */
    /* JADX WARN: Code restructure failed: missing block: B:313:0x04e0, code lost:
        if ((r37 instanceof gnu.math.DFloNum) == false) goto L234;
     */
    /* JADX WARN: Code restructure failed: missing block: B:314:0x04e2, code lost:
        if (r26 <= 0) goto L234;
     */
    /* JADX WARN: Code restructure failed: missing block: B:316:0x04e8, code lost:
        if (r26 == 'e') goto L234;
     */
    /* JADX WARN: Code restructure failed: missing block: B:317:0x04ea, code lost:
        r20 = r37.doubleValue();
     */
    /* JADX WARN: Code restructure failed: missing block: B:318:0x04ee, code lost:
        switch(r26) {
            case 100: goto L230;
            case 102: goto L228;
            case 108: goto L232;
            case 115: goto L228;
            default: goto L234;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:323:0x0512, code lost:
        r37 = r38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:324:0x0516, code lost:
        r42 = r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:383:?, code lost:
        return r16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:385:?, code lost:
        return "floating-point number after fraction symbol '/'";
     */
    /* JADX WARN: Code restructure failed: missing block: B:386:?, code lost:
        return "0/0 is undefined";
     */
    /* JADX WARN: Code restructure failed: missing block: B:387:?, code lost:
        return new gnu.math.DFloNum(0.0d);
     */
    /* JADX WARN: Code restructure failed: missing block: B:388:?, code lost:
        return gnu.math.Complex.polar(r37, r45);
     */
    /* JADX WARN: Code restructure failed: missing block: B:389:?, code lost:
        return r31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:390:?, code lost:
        return "invalid numeric constant (" + r31 + ")";
     */
    /* JADX WARN: Code restructure failed: missing block: B:392:?, code lost:
        return gnu.math.Complex.make(r37, r18.im());
     */
    /* JADX WARN: Code restructure failed: missing block: B:394:?, code lost:
        return gnu.math.Complex.make(gnu.math.IntNum.zero(), r37);
     */
    /* JADX WARN: Code restructure failed: missing block: B:396:?, code lost:
        return r37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:397:?, code lost:
        return java.lang.Float.valueOf((float) r20);
     */
    /* JADX WARN: Code restructure failed: missing block: B:398:?, code lost:
        return java.lang.Double.valueOf(r20);
     */
    /* JADX WARN: Code restructure failed: missing block: B:399:?, code lost:
        return java.math.BigDecimal.valueOf(r20);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.Object parseNumber(char[] r53, int r54, int r55, char r56, int r57, int r58) {
        /*
            Method dump skipped, instructions count: 1428
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.lispexpr.LispReader.parseNumber(char[], int, int, char, int, int):java.lang.Object");
    }

    private static IntNum valueOf(char[] buffer, int digits_start, int number_of_digits, int radix, boolean negative, long lvalue) {
        if (number_of_digits + radix <= 28) {
            if (negative) {
                lvalue = -lvalue;
            }
            return IntNum.make(lvalue);
        }
        return IntNum.valueOf(buffer, digits_start, number_of_digits, radix, negative);
    }

    public int readEscape() throws IOException, SyntaxException {
        int c = read();
        if (c < 0) {
            eofError("unexpected EOF in character literal");
            return -1;
        }
        return readEscape(c);
    }

    /* JADX WARN: Removed duplicated region for block: B:48:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00a5 A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00a9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int readEscape(int r11) throws java.io.IOException, gnu.text.SyntaxException {
        /*
            Method dump skipped, instructions count: 362
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.lispexpr.LispReader.readEscape(int):int");
    }

    public int readHexEscape() throws IOException, SyntaxException {
        int d;
        int c = 0;
        while (true) {
            d = read();
            int v = Character.digit((char) d, 16);
            if (v < 0) {
                break;
            }
            c = (c << 4) + v;
        }
        if (d != 59 && d >= 0) {
            unread(d);
        }
        return c;
    }

    public final Object readObject(int c) throws IOException, SyntaxException {
        unread(c);
        return readObject();
    }

    public Object readCommand() throws IOException, SyntaxException {
        return readObject();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Object makeNil() {
        return LList.Empty;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Pair makePair(Object car, int line, int column) {
        return makePair(car, LList.Empty, line, column);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Pair makePair(Object car, Object cdr, int line, int column) {
        String pname = this.port.getName();
        return (pname == null || line < 0) ? Pair.make(car, cdr) : PairWithPosition.make(car, cdr, pname, line + 1, column + 1);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setCdr(Object pair, Object cdr) {
        ((Pair) pair).setCdrBackdoor(cdr);
    }

    public static Object readNumberWithRadix(int previous, LispReader reader, int radix) throws IOException, SyntaxException {
        int startPos = reader.tokenBufferLength - previous;
        reader.readToken(reader.read(), 'P', ReadTable.getCurrent());
        int endPos = reader.tokenBufferLength;
        if (startPos == endPos) {
            reader.error("missing numeric token");
            return IntNum.zero();
        }
        Object result = parseNumber(reader.tokenBuffer, startPos, endPos - startPos, (char) 0, radix, 0);
        if (result instanceof String) {
            reader.error((String) result);
            return IntNum.zero();
        } else if (result == null) {
            reader.error("invalid numeric constant");
            return IntNum.zero();
        } else {
            return result;
        }
    }

    public static Object readCharacter(LispReader reader) throws IOException, SyntaxException {
        int i;
        int ch = reader.read();
        if (ch < 0) {
            reader.eofError("unexpected EOF in character literal");
        }
        int startPos = reader.tokenBufferLength;
        reader.tokenBufferAppend(ch);
        reader.readToken(reader.read(), 'D', ReadTable.getCurrent());
        char[] tokenBuffer = reader.tokenBuffer;
        int length = reader.tokenBufferLength - startPos;
        if (length == 1) {
            return Char.make(tokenBuffer[startPos]);
        }
        String name = new String(tokenBuffer, startPos, length);
        int ch2 = Char.nameToChar(name);
        if (ch2 >= 0) {
            return Char.make(ch2);
        }
        char c = tokenBuffer[startPos];
        if (c == 'x' || c == 'X') {
            int value = 0;
            while (i != length) {
                int v = Character.digit(tokenBuffer[startPos + i], 16);
                i = (v >= 0 && (value = (value * 16) + v) <= 1114111) ? i + 1 : 1;
            }
            return Char.make(value);
        }
        int ch3 = Character.digit((int) c, 8);
        if (ch3 >= 0) {
            int value2 = ch3;
            for (int i2 = 1; i2 != length; i2++) {
                int ch4 = Character.digit(tokenBuffer[startPos + i2], 8);
                if (ch4 >= 0) {
                    value2 = (value2 * 8) + ch4;
                }
            }
            return Char.make(value2);
        }
        reader.error("unknown character name: " + name);
        return Char.make(63);
    }

    public static Object readSpecial(LispReader reader) throws IOException, SyntaxException {
        int ch = reader.read();
        if (ch < 0) {
            reader.eofError("unexpected EOF in #! special form");
        }
        if (ch == 47 && reader.getLineNumber() == 0 && reader.getColumnNumber() == 3) {
            ReaderIgnoreRestOfLine.getInstance().read(reader, 35, 1);
            return Values.empty;
        }
        int startPos = reader.tokenBufferLength;
        reader.tokenBufferAppend(ch);
        reader.readToken(reader.read(), 'D', ReadTable.getCurrent());
        int length = reader.tokenBufferLength - startPos;
        String name = new String(reader.tokenBuffer, startPos, length);
        if (name.equals("optional")) {
            return Special.optional;
        }
        if (name.equals("rest")) {
            return Special.rest;
        }
        if (name.equals("key")) {
            return Special.key;
        }
        if (name.equals("eof")) {
            return Special.eof;
        }
        if (name.equals("void")) {
            return QuoteExp.voidExp;
        }
        if (name.equals("default")) {
            return Special.dfault;
        }
        if (name.equals("undefined")) {
            return Special.undefined;
        }
        if (name.equals("abstract")) {
            return Special.abstractSpecial;
        }
        if (name.equals("null")) {
            return null;
        }
        reader.error("unknown named constant #!" + name);
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0096  */
    /* JADX WARN: Removed duplicated region for block: B:55:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static gnu.lists.SimpleVector readSimpleVector(gnu.kawa.lispexpr.LispReader r11, char r12) throws java.io.IOException, gnu.text.SyntaxException {
        /*
            r10 = 40
            r9 = 32
            r6 = 0
            r5 = 0
        L6:
            int r0 = r11.read()
            if (r0 >= 0) goto L11
            java.lang.String r7 = "unexpected EOF reading uniform vector"
            r11.eofError(r7)
        L11:
            char r7 = (char) r0
            r8 = 10
            int r1 = java.lang.Character.digit(r7, r8)
            if (r1 >= 0) goto L36
            r7 = 8
            if (r5 == r7) goto L28
            r7 = 16
            if (r5 == r7) goto L28
            if (r5 == r9) goto L28
            r7 = 64
            if (r5 != r7) goto L30
        L28:
            r7 = 70
            if (r12 != r7) goto L2e
            if (r5 < r9) goto L30
        L2e:
            if (r0 == r10) goto L3b
        L30:
            java.lang.String r7 = "invalid uniform vector syntax"
            r11.error(r7)
        L35:
            return r6
        L36:
            int r7 = r5 * 10
            int r5 = r7 + r1
            goto L6
        L3b:
            r7 = -1
            r8 = 41
            java.lang.Object r3 = gnu.kawa.lispexpr.ReaderParens.readList(r11, r10, r7, r8)
            r7 = 0
            int r2 = gnu.lists.LList.listLength(r3, r7)
            if (r2 >= 0) goto L4f
            java.lang.String r7 = "invalid elements in uniform vector syntax"
            r11.error(r7)
            goto L35
        L4f:
            r4 = r3
            gnu.lists.Sequence r4 = (gnu.lists.Sequence) r4
            switch(r12) {
                case 70: goto L56;
                case 83: goto L59;
                case 85: goto L5c;
                default: goto L55;
            }
        L55:
            goto L35
        L56:
            switch(r5) {
                case 32: goto L66;
                case 64: goto L6c;
                default: goto L59;
            }
        L59:
            switch(r5) {
                case 8: goto L72;
                case 16: goto L78;
                case 32: goto L7e;
                case 64: goto L84;
                default: goto L5c;
            }
        L5c:
            switch(r5) {
                case 8: goto L60;
                case 16: goto L8a;
                case 32: goto L90;
                case 64: goto L96;
                default: goto L5f;
            }
        L5f:
            goto L35
        L60:
            gnu.lists.U8Vector r6 = new gnu.lists.U8Vector
            r6.<init>(r4)
            goto L35
        L66:
            gnu.lists.F32Vector r6 = new gnu.lists.F32Vector
            r6.<init>(r4)
            goto L35
        L6c:
            gnu.lists.F64Vector r6 = new gnu.lists.F64Vector
            r6.<init>(r4)
            goto L35
        L72:
            gnu.lists.S8Vector r6 = new gnu.lists.S8Vector
            r6.<init>(r4)
            goto L35
        L78:
            gnu.lists.S16Vector r6 = new gnu.lists.S16Vector
            r6.<init>(r4)
            goto L35
        L7e:
            gnu.lists.S32Vector r6 = new gnu.lists.S32Vector
            r6.<init>(r4)
            goto L35
        L84:
            gnu.lists.S64Vector r6 = new gnu.lists.S64Vector
            r6.<init>(r4)
            goto L35
        L8a:
            gnu.lists.U16Vector r6 = new gnu.lists.U16Vector
            r6.<init>(r4)
            goto L35
        L90:
            gnu.lists.U32Vector r6 = new gnu.lists.U32Vector
            r6.<init>(r4)
            goto L35
        L96:
            gnu.lists.U64Vector r6 = new gnu.lists.U64Vector
            r6.<init>(r4)
            goto L35
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.lispexpr.LispReader.readSimpleVector(gnu.kawa.lispexpr.LispReader, char):gnu.lists.SimpleVector");
    }
}
