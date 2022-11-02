package kawa.lib;

import gnu.expr.GenericProc;
import gnu.expr.Keyword;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.expr.Special;
import gnu.lists.Consumer;
import gnu.lists.FVector;
import gnu.lists.LList;
import gnu.mapping.CallContext;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;

/* compiled from: vectors.scm */
/* loaded from: classes.dex */
public class vectors extends ModuleBody {
    public static final ModuleMethod list$Mn$Grvector;
    public static final ModuleMethod make$Mnvector;
    public static final ModuleMethod vector$Mn$Grlist;
    public static final ModuleMethod vector$Mnfill$Ex;
    public static final ModuleMethod vector$Mnlength;
    public static final GenericProc vector$Mnref = null;
    static final ModuleMethod vector$Mnref$Fn1;
    public static final ModuleMethod vector$Mnset$Ex;
    public static final ModuleMethod vector$Qu;
    static final SimpleSymbol Lit8 = (SimpleSymbol) new SimpleSymbol("vector-fill!").readResolve();
    static final SimpleSymbol Lit7 = (SimpleSymbol) new SimpleSymbol("list->vector").readResolve();
    static final SimpleSymbol Lit6 = (SimpleSymbol) new SimpleSymbol("vector->list").readResolve();
    static final SimpleSymbol Lit5 = (SimpleSymbol) new SimpleSymbol("vector-ref").readResolve();
    static final SimpleSymbol Lit4 = (SimpleSymbol) new SimpleSymbol("vector-set!").readResolve();
    static final SimpleSymbol Lit3 = (SimpleSymbol) new SimpleSymbol("vector-length").readResolve();
    static final SimpleSymbol Lit2 = (SimpleSymbol) new SimpleSymbol("make-vector").readResolve();
    static final SimpleSymbol Lit1 = (SimpleSymbol) new SimpleSymbol("vector?").readResolve();
    static final Keyword Lit0 = Keyword.make("setter");
    public static final vectors $instance = new vectors();

    static {
        vectors vectorsVar = $instance;
        vector$Qu = new ModuleMethod(vectorsVar, 1, Lit1, 4097);
        make$Mnvector = new ModuleMethod(vectorsVar, 2, Lit2, 8193);
        vector$Mnlength = new ModuleMethod(vectorsVar, 4, Lit3, 4097);
        vector$Mnset$Ex = new ModuleMethod(vectorsVar, 5, Lit4, 12291);
        vector$Mnref$Fn1 = new ModuleMethod(vectorsVar, 6, Lit5, 8194);
        vector$Mn$Grlist = new ModuleMethod(vectorsVar, 7, Lit6, 4097);
        list$Mn$Grvector = new ModuleMethod(vectorsVar, 8, Lit7, 4097);
        vector$Mnfill$Ex = new ModuleMethod(vectorsVar, 9, Lit8, 8194);
        $instance.run();
    }

    public vectors() {
        ModuleInfo.register(this);
    }

    public static FVector makeVector(int i) {
        return makeVector(i, Special.undefined);
    }

    @Override // gnu.expr.ModuleBody
    public final void run(CallContext $ctx) {
        Consumer consumer = $ctx.consumer;
        vector$Mnref = new GenericProc("vector-ref");
        GenericProc genericProc = vector$Mnref;
        ModuleMethod moduleMethod = vector$Mnref$Fn1;
        genericProc.setProperties(new Object[]{Lit0, vector$Mnset$Ex, vector$Mnref$Fn1});
    }

    public static boolean isVector(Object x) {
        return x instanceof FVector;
    }

    @Override // gnu.expr.ModuleBody
    public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
        switch (moduleMethod.selector) {
            case 1:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 2:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 3:
            case 5:
            case 6:
            default:
                return super.match1(moduleMethod, obj, callContext);
            case 4:
                if (obj instanceof FVector) {
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                }
                return -786431;
            case 7:
                if (obj instanceof FVector) {
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                }
                return -786431;
            case 8:
                if (obj instanceof LList) {
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                }
                return -786431;
        }
    }

    public static FVector makeVector(int k, Object fill) {
        return new FVector(k, fill);
    }

    @Override // gnu.expr.ModuleBody
    public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
        switch (moduleMethod.selector) {
            case 2:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 6:
                if (obj instanceof FVector) {
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.proc = moduleMethod;
                    callContext.pc = 2;
                    return 0;
                }
                return -786431;
            case 9:
                if (obj instanceof FVector) {
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.proc = moduleMethod;
                    callContext.pc = 2;
                    return 0;
                }
                return -786431;
            default:
                return super.match2(moduleMethod, obj, obj2, callContext);
        }
    }

    public static int vectorLength(FVector x) {
        return x.size();
    }

    @Override // gnu.expr.ModuleBody
    public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
        if (moduleMethod.selector == 5) {
            try {
                try {
                    ((FVector) obj).set(((Number) obj2).intValue(), obj3);
                    return Values.empty;
                } catch (ClassCastException e) {
                    throw new WrongType(e, "vector-set!", 2, obj2);
                }
            } catch (ClassCastException e2) {
                throw new WrongType(e2, "vector-set!", 1, obj);
            }
        }
        return super.apply3(moduleMethod, obj, obj2, obj3);
    }

    @Override // gnu.expr.ModuleBody
    public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
        if (moduleMethod.selector == 5) {
            if (obj instanceof FVector) {
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            }
            return -786431;
        }
        return super.match3(moduleMethod, obj, obj2, obj3, callContext);
    }

    public static Object vectorRef(FVector vector, int k) {
        return vector.get(k);
    }

    public static LList vector$To$List(FVector vec) {
        LList result = LList.Empty;
        int i = vectorLength(vec);
        while (true) {
            i--;
            if (i < 0) {
                return result;
            }
            result = lists.cons(vector$Mnref.apply2(vec, Integer.valueOf(i)), result);
        }
    }

    public static FVector list$To$Vector(LList x) {
        return new FVector(x);
    }

    @Override // gnu.expr.ModuleBody
    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        switch (moduleMethod.selector) {
            case 1:
                return isVector(obj) ? Boolean.TRUE : Boolean.FALSE;
            case 2:
                try {
                    return makeVector(((Number) obj).intValue());
                } catch (ClassCastException e) {
                    throw new WrongType(e, "make-vector", 1, obj);
                }
            case 3:
            case 5:
            case 6:
            default:
                return super.apply1(moduleMethod, obj);
            case 4:
                try {
                    return Integer.valueOf(vectorLength((FVector) obj));
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "vector-length", 1, obj);
                }
            case 7:
                try {
                    return vector$To$List((FVector) obj);
                } catch (ClassCastException e3) {
                    throw new WrongType(e3, "vector->list", 1, obj);
                }
            case 8:
                try {
                    return list$To$Vector((LList) obj);
                } catch (ClassCastException e4) {
                    throw new WrongType(e4, "list->vector", 1, obj);
                }
        }
    }

    @Override // gnu.expr.ModuleBody
    public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
        switch (moduleMethod.selector) {
            case 2:
                try {
                    return makeVector(((Number) obj).intValue(), obj2);
                } catch (ClassCastException e) {
                    throw new WrongType(e, "make-vector", 1, obj);
                }
            case 6:
                try {
                    try {
                        return vectorRef((FVector) obj, ((Number) obj2).intValue());
                    } catch (ClassCastException e2) {
                        throw new WrongType(e2, "vector-ref", 2, obj2);
                    }
                } catch (ClassCastException e3) {
                    throw new WrongType(e3, "vector-ref", 1, obj);
                }
            case 9:
                try {
                    ((FVector) obj).setAll(obj2);
                    return Values.empty;
                } catch (ClassCastException e4) {
                    throw new WrongType(e4, "vector-fill!", 1, obj);
                }
            default:
                return super.apply2(moduleMethod, obj, obj2);
        }
    }
}
