package kawa.lib.rnrs;

import gnu.expr.ApplicationMainSupport;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.mapping.CallContext;
import gnu.mapping.OutPort;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import kawa.lib.lists;
import kawa.lib.numbers;

/* compiled from: programs.scm */
/* loaded from: classes.dex */
public class programs extends ModuleBody {
    public static final ModuleMethod command$Mnline;
    public static final ModuleMethod exit;
    static final SimpleSymbol Lit2 = (SimpleSymbol) new SimpleSymbol("exit").readResolve();
    static final SimpleSymbol Lit1 = (SimpleSymbol) new SimpleSymbol("command-line").readResolve();
    static final IntNum Lit0 = IntNum.make(0);
    public static final programs $instance = new programs();

    static {
        programs programsVar = $instance;
        command$Mnline = new ModuleMethod(programsVar, 1, Lit1, 0);
        exit = new ModuleMethod(programsVar, 2, Lit2, 4096);
        $instance.run();
    }

    public programs() {
        ModuleInfo.register(this);
    }

    public static void exit() {
        exit(Lit0);
    }

    @Override // gnu.expr.ModuleBody
    public final void run(CallContext $ctx) {
        Consumer consumer = $ctx.consumer;
    }

    public static LList commandLine() {
        return lists.cons("kawa", LList.makeList(ApplicationMainSupport.commandLineArgArray, 0));
    }

    @Override // gnu.expr.ModuleBody
    public int match0(ModuleMethod moduleMethod, CallContext callContext) {
        switch (moduleMethod.selector) {
            case 1:
                callContext.proc = moduleMethod;
                callContext.pc = 0;
                return 0;
            case 2:
                callContext.proc = moduleMethod;
                callContext.pc = 0;
                return 0;
            default:
                return super.match0(moduleMethod, callContext);
        }
    }

    public static void exit(Object code) {
        int status;
        OutPort.runCleanups();
        if (!numbers.isInteger(code)) {
            status = code != Boolean.FALSE ? 0 : -1;
        } else {
            try {
                status = ((Number) code).intValue();
            } catch (ClassCastException e) {
                throw new WrongType(e, "status", -2, code);
            }
        }
        System.exit(status);
    }

    @Override // gnu.expr.ModuleBody
    public Object apply0(ModuleMethod moduleMethod) {
        switch (moduleMethod.selector) {
            case 1:
                return commandLine();
            case 2:
                exit();
                return Values.empty;
            default:
                return super.apply0(moduleMethod);
        }
    }

    @Override // gnu.expr.ModuleBody
    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        if (moduleMethod.selector == 2) {
            exit(obj);
            return Values.empty;
        }
        return super.apply1(moduleMethod, obj);
    }

    @Override // gnu.expr.ModuleBody
    public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
        if (moduleMethod.selector == 2) {
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }
        return super.match1(moduleMethod, obj, callContext);
    }
}
