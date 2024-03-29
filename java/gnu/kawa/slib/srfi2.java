package gnu.kawa.slib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.SimpleSymbol;
import kawa.lang.Macro;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxTemplate;
import kawa.lang.TemplateScope;
import kawa.lib.prim_syntax;
import kawa.lib.std_syntax;
import kawa.standard.syntax_case;

/* compiled from: srfi2.scm */
/* loaded from: classes.dex */
public class srfi2 extends ModuleBody {
    static final SimpleSymbol Lit22 = (SimpleSymbol) new SimpleSymbol("let").readResolve();
    static final SimpleSymbol Lit21 = (SimpleSymbol) new SimpleSymbol("and").readResolve();
    static final SyntaxTemplate Lit20 = new SyntaxTemplate("\u0001", "\u0018\u0004", new Object[]{Boolean.TRUE}, 0);
    static final SyntaxPattern Lit19 = new SyntaxPattern("\f\u0007\f\b\b", new Object[0], 1);
    static final SyntaxTemplate Lit18 = new SyntaxTemplate("\u0001\u0001\u0000", "\u000b", new Object[0], 0);
    static final SyntaxTemplate Lit17 = new SyntaxTemplate("\u0001\u0001\u0000", "\u0011\u0018\u0004\t\u000b\b\t\u0003\b\u0012", new Object[]{Lit21}, 0);
    static final SyntaxTemplate Lit16 = new SyntaxTemplate("\u0001\u0001\u0000", "\u000b", new Object[0], 0);
    static final SyntaxPattern Lit15 = new SyntaxPattern("\f\u0007\u001c\f\u000f\u0013\b", new Object[0], 3);
    static final SyntaxTemplate Lit14 = new SyntaxTemplate("\u0001\u0001\u0000", "\u0011\u0018\u0004\t\u000b\b\t\u0003\b\u0012", new Object[]{Lit21}, 0);
    static final SyntaxPattern Lit13 = new SyntaxPattern("\f\u0007,\u001c\f\u000f\b\u0013\b", new Object[0], 3);
    static final SyntaxTemplate Lit12 = new SyntaxTemplate("\u0001\u0001\u0001\u0000", "\u0011\u0018\u0004)\b\t\u000b\b\u0013\b\u0011\u0018\f\t\u000b\b\t\u0003\b\u001a", new Object[]{Lit22, Lit21}, 0);
    static final SyntaxPattern Lit11 = new SyntaxPattern("\f\u0007<,\f\u000f\f\u0017\b\u001b\b", new Object[0], 4);
    static final SyntaxTemplate Lit10 = new SyntaxTemplate("\u0001\u0001", "\u000b", new Object[0], 0);
    static final SyntaxTemplate Lit9 = new SyntaxTemplate("\u0001\u0001", "\u000b", new Object[0], 0);
    static final SyntaxTemplate Lit8 = new SyntaxTemplate("\u0001\u0001", "\u000b", new Object[0], 0);
    static final SyntaxPattern Lit7 = new SyntaxPattern("\f\u0007\u001c\f\u000f\b\b", new Object[0], 2);
    static final SyntaxTemplate Lit6 = new SyntaxTemplate("\u0001\u0001", "\u000b", new Object[0], 0);
    static final SyntaxPattern Lit5 = new SyntaxPattern("\f\u0007,\u001c\f\u000f\b\b\b", new Object[0], 2);
    static final SyntaxTemplate Lit4 = new SyntaxTemplate("\u0001\u0001\u0001", "\u0011\u0018\u0004)\b\t\u000b\b\u0013\b\u000b", new Object[]{Lit22}, 0);
    static final SyntaxPattern Lit3 = new SyntaxPattern("\f\u0007<,\f\u000f\f\u0017\b\b\b", new Object[0], 3);
    static final SyntaxTemplate Lit2 = new SyntaxTemplate("\u0001\u0003\u0001\u0000", "\t\u0003\b\u0011\r\u000b\b\b\u0011\u0018\u0004\t\u0013\u001a", new Object[]{(SimpleSymbol) new SimpleSymbol("begin").readResolve()}, 1);
    static final SyntaxPattern Lit1 = new SyntaxPattern("\f\u0007,\r\u000f\b\b\b\f\u0017\u001b", new Object[0], 4);
    static final SimpleSymbol Lit0 = (SimpleSymbol) new SimpleSymbol("and-let*").readResolve();
    public static final srfi2 $instance = new srfi2();
    public static final Macro and$Mnlet$St = Macro.make(Lit0, new ModuleMethod($instance, 1, null, 4097), $instance);

    static {
        $instance.run();
    }

    public srfi2() {
        ModuleInfo.register(this);
    }

    @Override // gnu.expr.ModuleBody
    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        return moduleMethod.selector == 1 ? lambda1(obj) : super.apply1(moduleMethod, obj);
    }

    @Override // gnu.expr.ModuleBody
    public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
        if (moduleMethod.selector == 1) {
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }
        return super.match1(moduleMethod, obj, callContext);
    }

    @Override // gnu.expr.ModuleBody
    public final void run(CallContext $ctx) {
        Consumer consumer = $ctx.consumer;
    }

    static Object lambda1(Object form) {
        Object[] allocVars = SyntaxPattern.allocVars(4, null);
        if (Lit1.match(form, allocVars, 0)) {
            return Lit2.execute(allocVars, TemplateScope.make());
        }
        if (Lit3.match(form, allocVars, 0)) {
            return Lit4.execute(allocVars, TemplateScope.make());
        }
        if (Lit5.match(form, allocVars, 0)) {
            return Lit6.execute(allocVars, TemplateScope.make());
        }
        if (Lit7.match(form, allocVars, 0)) {
            if (std_syntax.isIdentifier(Lit8.execute(allocVars, TemplateScope.make()))) {
                return Lit9.execute(allocVars, TemplateScope.make());
            }
            return prim_syntax.syntaxError(Lit10.execute(allocVars, TemplateScope.make()), "expected a variable name" instanceof Object[] ? "expected a variable name" : new Object[]{"expected a variable name"});
        } else if (Lit11.match(form, allocVars, 0)) {
            return Lit12.execute(allocVars, TemplateScope.make());
        } else {
            if (Lit13.match(form, allocVars, 0)) {
                return Lit14.execute(allocVars, TemplateScope.make());
            }
            if (!Lit15.match(form, allocVars, 0)) {
                return Lit19.match(form, allocVars, 0) ? Lit20.execute(allocVars, TemplateScope.make()) : syntax_case.error("syntax-case", form);
            } else if (std_syntax.isIdentifier(Lit16.execute(allocVars, TemplateScope.make()))) {
                return Lit17.execute(allocVars, TemplateScope.make());
            } else {
                return prim_syntax.syntaxError(Lit18.execute(allocVars, TemplateScope.make()), "expected a variable name" instanceof Object[] ? "expected a variable name" : new Object[]{"expected a variable name"});
            }
        }
    }
}
