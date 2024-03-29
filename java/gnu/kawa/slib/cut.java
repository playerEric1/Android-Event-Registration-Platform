package gnu.kawa.slib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.kawa.functions.GetNamedPart;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.SimpleSymbol;
import kawa.lang.Macro;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxRule;
import kawa.lang.SyntaxRules;

/* compiled from: cut.scm */
/* loaded from: classes.dex */
public class cut extends ModuleBody {
    public static final Macro $Prvt$srfi$Mn26$Mninternal$Mncut;
    public static final Macro $Prvt$srfi$Mn26$Mninternal$Mncute;
    public static final cut $instance;
    static final SimpleSymbol Lit0;
    static final SyntaxRules Lit1;
    static final SimpleSymbol Lit2;
    static final SyntaxRules Lit3;
    static final SimpleSymbol Lit4;
    static final SyntaxRules Lit5;
    static final SimpleSymbol Lit6;
    static final SyntaxRules Lit7;
    public static final Macro cut;
    public static final Macro cute;
    static final SimpleSymbol Lit14 = (SimpleSymbol) new SimpleSymbol("rest-slot").readResolve();
    static final SimpleSymbol Lit13 = (SimpleSymbol) new SimpleSymbol("apply").readResolve();
    static final SimpleSymbol Lit12 = (SimpleSymbol) new SimpleSymbol(GetNamedPart.CLASSTYPE_FOR).readResolve();
    static final SimpleSymbol Lit11 = (SimpleSymbol) new SimpleSymbol("x").readResolve();
    static final SimpleSymbol Lit10 = (SimpleSymbol) new SimpleSymbol("lambda").readResolve();
    static final SimpleSymbol Lit9 = (SimpleSymbol) new SimpleSymbol("let").readResolve();
    static final SimpleSymbol Lit8 = (SimpleSymbol) new SimpleSymbol("<...>").readResolve();

    public cut() {
        ModuleInfo.register(this);
    }

    static {
        SimpleSymbol simpleSymbol = (SimpleSymbol) new SimpleSymbol("cute").readResolve();
        Lit6 = simpleSymbol;
        SyntaxPattern syntaxPattern = new SyntaxPattern("\f\u0018\u0003", new Object[0], 1);
        SimpleSymbol simpleSymbol2 = (SimpleSymbol) new SimpleSymbol("srfi-26-internal-cute").readResolve();
        Lit2 = simpleSymbol2;
        Lit7 = new SyntaxRules(new Object[]{simpleSymbol}, new SyntaxRule[]{new SyntaxRule(syntaxPattern, "\u0000", "\u0011\u0018\u0004\t\u0010\t\u0010\t\u0010\u0002", new Object[]{simpleSymbol2}, 0)}, 1);
        SimpleSymbol simpleSymbol3 = (SimpleSymbol) new SimpleSymbol("cut").readResolve();
        Lit4 = simpleSymbol3;
        SyntaxPattern syntaxPattern2 = new SyntaxPattern("\f\u0018\u0003", new Object[0], 1);
        SimpleSymbol simpleSymbol4 = (SimpleSymbol) new SimpleSymbol("srfi-26-internal-cut").readResolve();
        Lit0 = simpleSymbol4;
        Lit5 = new SyntaxRules(new Object[]{simpleSymbol3}, new SyntaxRule[]{new SyntaxRule(syntaxPattern2, "\u0000", "\u0011\u0018\u0004\t\u0010\t\u0010\u0002", new Object[]{simpleSymbol4}, 0)}, 1);
        Lit3 = new SyntaxRules(new Object[]{Lit2, Lit12, Lit8}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018,\r\u0007\u0000\b\b\f\u000f<\f\u0017\r\u001f\u0018\b\b\b", new Object[0], 4), "\u0003\u0001\u0001\u0003", "\u0011\u0018\u0004\t\u000b\b\u0011\u0018\f\u0019\b\u0005\u0003\b\t\u0013\b\u001d\u001b", new Object[]{Lit9, Lit10}, 1), new SyntaxRule(new SyntaxPattern("\f\u0018,\r\u0007\u0000\b\b\f\u000f<\f\u0017\r\u001f\u0018\b\b\f\u0002\b", new Object[]{Lit8}, 4), "\u0003\u0001\u0001\u0003", "\u0011\u0018\u0004\t\u000b\b\u0011\u0018\f)\u0011\u0005\u0003\u0018\u0014\b\u0011\u0018\u001c\t\u0013\u0011\u001d\u001b\u0018$", new Object[]{Lit9, Lit10, Lit11, Lit13, PairWithPosition.make(Lit11, LList.Empty, "cut.scm", 356424)}, 1), new SyntaxRule(new SyntaxPattern("\f\u0018,\r\u0007\u0000\b\b\f\u000f,\r\u0017\u0010\b\b\f\u0002\u001b", new Object[]{Lit12}, 4), "\u0003\u0001\u0003\u0000", "\u0011\u0018\u0004)\u0011\u0005\u0003\u0018\f\t\u000b)\u0011\u0015\u0013\u0018\u0014\u001a", new Object[]{Lit2, PairWithPosition.make(Lit11, LList.Empty, "cut.scm", 380950), PairWithPosition.make(Lit11, LList.Empty, "cut.scm", 380987)}, 1), new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\f\u000f,\r\u0017\u0010\b\b\f\u001f#", new Object[0], 5), "\u0001\u0001\u0003\u0001\u0000", "\u0011\u0018\u0004\t\u00039)\u0011\u0018\f\b\u001b\u000b)\u0011\u0015\u0013\u0018\u0014\"", new Object[]{Lit2, Lit11, PairWithPosition.make(Lit11, LList.Empty, "cut.scm", 401465)}, 1)}, 5);
        Lit1 = new SyntaxRules(new Object[]{Lit0, Lit12, Lit8}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018,\r\u0007\u0000\b\b<\f\u000f\r\u0017\u0010\b\b\b", new Object[0], 3), "\u0003\u0001\u0003", "\u0011\u0018\u0004\u0019\b\u0005\u0003\b)\u0011\u0018\f\b\u000b\b\u0015\u0013", new Object[]{Lit10, (SimpleSymbol) new SimpleSymbol("begin").readResolve()}, 1), new SyntaxRule(new SyntaxPattern("\f\u0018,\r\u0007\u0000\b\b<\f\u000f\r\u0017\u0010\b\b\f\u0002\b", new Object[]{Lit8}, 3), "\u0003\u0001\u0003", "\u0011\u0018\u0004)\u0011\u0005\u0003\u0018\f\b\u0011\u0018\u0014\t\u000b\u0011\u0015\u0013\u0018\u001c", new Object[]{Lit10, Lit14, Lit13, PairWithPosition.make(Lit14, LList.Empty, "cut.scm", 249918)}, 1), new SyntaxRule(new SyntaxPattern("\f\u0018,\r\u0007\u0000\b\b,\r\u000f\b\b\b\f\u0002\u0013", new Object[]{Lit12}, 3), "\u0003\u0003\u0000", "\u0011\u0018\u0004)\u0011\u0005\u0003\u0018\f)\u0011\r\u000b\u0018\u0014\u0012", new Object[]{Lit0, PairWithPosition.make(Lit11, LList.Empty, "cut.scm", 266283), PairWithPosition.make(Lit11, LList.Empty, "cut.scm", 266300)}, 1), new SyntaxRule(new SyntaxPattern("\f\u0018,\r\u0007\u0000\b\b,\r\u000f\b\b\b\f\u0017\u001b", new Object[0], 4), "\u0003\u0003\u0001\u0000", "\u0011\u0018\u0004\u0019\b\u0005\u0003)\u0011\r\u000b\b\u0013\u001a", new Object[]{Lit0}, 1)}, 4);
        $instance = new cut();
        $Prvt$srfi$Mn26$Mninternal$Mncut = Macro.make(Lit0, Lit1, $instance);
        $Prvt$srfi$Mn26$Mninternal$Mncute = Macro.make(Lit2, Lit3, $instance);
        cut = Macro.make(Lit4, Lit5, $instance);
        cute = Macro.make(Lit6, Lit7, $instance);
        $instance.run();
    }

    @Override // gnu.expr.ModuleBody
    public final void run(CallContext $ctx) {
        Consumer consumer = $ctx.consumer;
    }
}
