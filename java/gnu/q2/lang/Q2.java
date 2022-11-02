package gnu.q2.lang;

import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.expr.Language;
import gnu.expr.ModuleBody;
import gnu.kawa.functions.AppendValues;
import gnu.kawa.lispexpr.ReadTable;
import gnu.lists.Consumer;
import gnu.lists.FString;
import gnu.mapping.InPort;
import gnu.mapping.Procedure;
import gnu.text.Lexer;
import gnu.text.SourceMessages;
import gnu.xml.XMLPrinter;
import java.io.Writer;
import kawa.standard.Scheme;

/* loaded from: classes.dex */
public class Q2 extends Scheme {
    static final Object emptyForm = new FString();
    static Q2 instance;

    public Q2() {
        instance = this;
        ModuleBody.setMainPrintValues(true);
    }

    public static Q2 getQ2Instance() {
        if (instance == null) {
            new Q2();
        }
        return instance;
    }

    @Override // gnu.kawa.lispexpr.LispLanguage, gnu.expr.Language
    public Lexer getLexer(InPort inp, SourceMessages messages) {
        Compilation.defaultCallConvention = 2;
        Q2Read lexer = new Q2Read(inp, messages);
        return lexer;
    }

    @Override // gnu.expr.Language
    public Consumer getOutputConsumer(Writer out) {
        return new XMLPrinter(out, false);
    }

    public static void registerEnvironment() {
        Language.setDefaults(new Q2());
    }

    @Override // gnu.kawa.lispexpr.LispLanguage
    public Expression makeBody(Expression[] exps) {
        return new ApplyExp(AppendValues.appendValues, exps);
    }

    @Override // kawa.standard.Scheme, gnu.kawa.lispexpr.LispLanguage
    public Expression makeApply(Expression func, Expression[] args) {
        Expression[] exps = new Expression[args.length + 1];
        exps[0] = func;
        System.arraycopy(args, 0, exps, 1, args.length);
        return new ApplyExp(Q2Apply.q2Apply, exps);
    }

    @Override // gnu.expr.Language
    public Procedure getPrompter() {
        return new Prompter();
    }

    @Override // kawa.standard.Scheme, gnu.kawa.lispexpr.LispLanguage
    public ReadTable createReadTable() {
        ReadTable rt = ReadTable.createInitial();
        rt.set(40, new Q2ReaderParens());
        rt.setFinalColonIsKeyword(true);
        return rt;
    }

    public static int compareIndentation(int indentation1, int indentation2) {
        int numTabs1 = indentation1 >>> 16;
        int numTabs2 = indentation1 >>> 16;
        int numSpaces1 = indentation1 & 255;
        int numSpaces2 = indentation2 & 255;
        if (numTabs1 == numTabs2) {
            return numSpaces1 - numSpaces2;
        }
        if ((numTabs1 < numTabs2 && numSpaces1 <= numSpaces2) || (numTabs1 > numTabs2 && numSpaces1 >= numSpaces2)) {
            return (numTabs1 - numTabs2) * 8;
        }
        return Integer.MIN_VALUE;
    }
}
