package kawa.lib;

import gnu.bytecode.ClassType;
import gnu.bytecode.Type;
import gnu.expr.Compilation;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.lispexpr.LangObjType;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import kawa.lang.GetFieldProc;
import kawa.lang.Macro;
import kawa.lang.Record;
import kawa.lang.RecordConstructor;
import kawa.lang.SetFieldProc;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxRule;
import kawa.lang.SyntaxRules;
import kawa.lang.SyntaxTemplate;
import kawa.lang.TemplateScope;
import kawa.standard.syntax_case;

/* compiled from: reflection.scm */
/* loaded from: classes.dex */
public class reflection extends ModuleBody {
    public static final reflection $instance;
    static final SimpleSymbol Lit0;
    static final SyntaxPattern Lit1;
    static final SimpleSymbol Lit10;
    static final SimpleSymbol Lit11;
    static final SimpleSymbol Lit12;
    static final SimpleSymbol Lit13;
    static final SimpleSymbol Lit14;
    static final SyntaxRules Lit15;
    static final SimpleSymbol Lit16;
    static final SyntaxRules Lit17;
    static final SimpleSymbol Lit18;
    static final SyntaxRules Lit19;
    static final SyntaxTemplate Lit2;
    static final SimpleSymbol Lit20;
    static final SyntaxRules Lit21;
    static final SimpleSymbol Lit22;
    static final SyntaxRules Lit23;
    static final SimpleSymbol Lit24;
    static final SyntaxRules Lit25;
    static final SimpleSymbol Lit26;
    static final SyntaxRules Lit27;
    static final SimpleSymbol Lit28;
    static final SyntaxRules Lit29;
    static final SyntaxPattern Lit3;
    static final SyntaxTemplate Lit4;
    static final SimpleSymbol Lit5;
    static final SimpleSymbol Lit6;
    static final SimpleSymbol Lit7;
    static final SimpleSymbol Lit8;
    static final SimpleSymbol Lit9;
    public static final ModuleMethod make$Mnrecord$Mntype;
    public static final Macro primitive$Mnarray$Mnget;
    public static final Macro primitive$Mnarray$Mnlength;
    public static final Macro primitive$Mnarray$Mnnew;
    public static final Macro primitive$Mnarray$Mnset;
    public static final Macro primitive$Mnconstructor;
    public static final Macro primitive$Mnget$Mnfield;
    public static final Macro primitive$Mnget$Mnstatic;
    public static final Macro primitive$Mnset$Mnfield;
    public static final Macro primitive$Mnset$Mnstatic;
    public static final ModuleMethod record$Mnaccessor;
    public static final ModuleMethod record$Mnconstructor;
    public static final ModuleMethod record$Mnmodifier;
    public static final ModuleMethod record$Mnpredicate;
    public static final ModuleMethod record$Mntype$Mndescriptor;
    public static final ModuleMethod record$Mntype$Mnfield$Mnnames;
    public static final ModuleMethod record$Mntype$Mnname;
    public static final ModuleMethod record$Qu;
    public static final ModuleMethod subtype$Qu;
    static final IntNum Lit34 = IntNum.make(1);
    static final IntNum Lit33 = IntNum.make(9);
    static final SimpleSymbol Lit32 = (SimpleSymbol) new SimpleSymbol("make").readResolve();
    static final SimpleSymbol Lit31 = (SimpleSymbol) new SimpleSymbol("constant-fold").readResolve();
    static final SimpleSymbol Lit30 = (SimpleSymbol) new SimpleSymbol("subtype?").readResolve();

    static {
        SimpleSymbol simpleSymbol = (SimpleSymbol) new SimpleSymbol("primitive-set-static").readResolve();
        Lit28 = simpleSymbol;
        Lit29 = new SyntaxRules(new Object[]{simpleSymbol}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\f\u000f\f\u0017\b", new Object[0], 3), "\u0001\u0001\u0001", "\u0011\u0018\u0004\u0011\u0018\f\u0011\u0018\u0014\t\u0003\t\u000b\t\u0013\u0018\u001c", new Object[]{Lit31, Lit32, (SimpleSymbol) new SimpleSymbol("<gnu.kawa.reflect.StaticSet>").readResolve(), PairWithPosition.make(Lit33, LList.Empty, "reflection.scm", 454679)}, 0)}, 3);
        SimpleSymbol simpleSymbol2 = (SimpleSymbol) new SimpleSymbol("primitive-get-static").readResolve();
        Lit26 = simpleSymbol2;
        Lit27 = new SyntaxRules(new Object[]{simpleSymbol2}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\f\u000f\f\u0017\b", new Object[0], 3), "\u0001\u0001\u0001", "\u0011\u0018\u0004\u0011\u0018\f\u0011\u0018\u0014\t\u0003\t\u000b\t\u0013\u0018\u001c", new Object[]{Lit31, Lit32, (SimpleSymbol) new SimpleSymbol("<gnu.kawa.reflect.StaticGet>").readResolve(), PairWithPosition.make(Lit33, LList.Empty, "reflection.scm", 430103)}, 0)}, 3);
        SimpleSymbol simpleSymbol3 = (SimpleSymbol) new SimpleSymbol("primitive-set-field").readResolve();
        Lit24 = simpleSymbol3;
        Lit25 = new SyntaxRules(new Object[]{simpleSymbol3}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\f\u000f\f\u0017\b", new Object[0], 3), "\u0001\u0001\u0001", "\u0011\u0018\u0004\u0011\u0018\f\u0011\u0018\u0014\t\u0003\t\u000b\t\u0013\u0018\u001c", new Object[]{Lit31, Lit32, (SimpleSymbol) new SimpleSymbol("<kawa.lang.SetFieldProc>").readResolve(), PairWithPosition.make(Lit34, LList.Empty, "reflection.scm", 401431)}, 0)}, 3);
        SimpleSymbol simpleSymbol4 = (SimpleSymbol) new SimpleSymbol("primitive-get-field").readResolve();
        Lit22 = simpleSymbol4;
        Lit23 = new SyntaxRules(new Object[]{simpleSymbol4}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\f\u000f\f\u0017\b", new Object[0], 3), "\u0001\u0001\u0001", "\u0011\u0018\u0004\u0011\u0018\f\u0011\u0018\u0014\t\u0003\t\u000b\t\u0013\u0018\u001c", new Object[]{Lit31, Lit32, (SimpleSymbol) new SimpleSymbol("<kawa.lang.GetFieldProc>").readResolve(), PairWithPosition.make(Lit34, LList.Empty, "reflection.scm", 376855)}, 0)}, 3);
        SimpleSymbol simpleSymbol5 = (SimpleSymbol) new SimpleSymbol("primitive-array-length").readResolve();
        Lit20 = simpleSymbol5;
        Lit21 = new SyntaxRules(new Object[]{simpleSymbol5}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\b", new Object[0], 1), "\u0001", "\u0011\u0018\u0004\u0011\u0018\f\u0011\u0018\u0014\b\u0003", new Object[]{Lit31, Lit32, (SimpleSymbol) new SimpleSymbol("<gnu.kawa.reflect.ArrayLength>").readResolve()}, 0)}, 1);
        SimpleSymbol simpleSymbol6 = (SimpleSymbol) new SimpleSymbol("primitive-array-get").readResolve();
        Lit18 = simpleSymbol6;
        Lit19 = new SyntaxRules(new Object[]{simpleSymbol6}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\b", new Object[0], 1), "\u0001", "\u0011\u0018\u0004\u0011\u0018\f\u0011\u0018\u0014\b\u0003", new Object[]{Lit31, Lit32, (SimpleSymbol) new SimpleSymbol("<gnu.kawa.reflect.ArrayGet>").readResolve()}, 0)}, 1);
        SimpleSymbol simpleSymbol7 = (SimpleSymbol) new SimpleSymbol("primitive-array-set").readResolve();
        Lit16 = simpleSymbol7;
        Lit17 = new SyntaxRules(new Object[]{simpleSymbol7}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\b", new Object[0], 1), "\u0001", "\u0011\u0018\u0004\u0011\u0018\f\u0011\u0018\u0014\b\u0003", new Object[]{Lit31, Lit32, (SimpleSymbol) new SimpleSymbol("<gnu.kawa.reflect.ArraySet>").readResolve()}, 0)}, 1);
        SimpleSymbol simpleSymbol8 = (SimpleSymbol) new SimpleSymbol("primitive-array-new").readResolve();
        Lit14 = simpleSymbol8;
        Lit15 = new SyntaxRules(new Object[]{simpleSymbol8}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\b", new Object[0], 1), "\u0001", "\u0011\u0018\u0004\u0011\u0018\f\u0011\u0018\u0014\b\u0003", new Object[]{Lit31, Lit32, (SimpleSymbol) new SimpleSymbol("<gnu.kawa.reflect.ArrayNew>").readResolve()}, 0)}, 1);
        Lit13 = (SimpleSymbol) new SimpleSymbol("record-type-field-names").readResolve();
        Lit12 = (SimpleSymbol) new SimpleSymbol("record-type-name").readResolve();
        Lit11 = (SimpleSymbol) new SimpleSymbol("record-type-descriptor").readResolve();
        Lit10 = (SimpleSymbol) new SimpleSymbol("record-predicate").readResolve();
        Lit9 = (SimpleSymbol) new SimpleSymbol("record?").readResolve();
        Lit8 = (SimpleSymbol) new SimpleSymbol("record-modifier").readResolve();
        Lit7 = (SimpleSymbol) new SimpleSymbol("record-accessor").readResolve();
        Lit6 = (SimpleSymbol) new SimpleSymbol("record-constructor").readResolve();
        Lit5 = (SimpleSymbol) new SimpleSymbol("make-record-type").readResolve();
        Lit4 = new SyntaxTemplate("\u0001\u0001\u0003\u0003", "\u0011\u0018\u0004\u0019\b\u001d\u001b\u0011\u0018\f\t\u000b\b\u0011\u0018\u0014\t\u000b\b\u0015\u0011\u0018\u001c\t\u0013\b\u001b", new Object[]{(SimpleSymbol) new SimpleSymbol("lambda").readResolve(), (SimpleSymbol) new SimpleSymbol("::").readResolve(), Lit32, (SimpleSymbol) new SimpleSymbol("as").readResolve()}, 1);
        Lit3 = new SyntaxPattern("\r\u001f\u0018\b\b", new Object[0], 4);
        Lit2 = new SyntaxTemplate("\u0001\u0001\u0003", "\b\u0015\u0013", new Object[0], 1);
        Lit1 = new SyntaxPattern("\f\u0007\f\u000f,\r\u0017\u0010\b\b\b", new Object[0], 3);
        Lit0 = (SimpleSymbol) new SimpleSymbol("primitive-constructor").readResolve();
        $instance = new reflection();
        SimpleSymbol simpleSymbol9 = Lit0;
        reflection reflectionVar = $instance;
        primitive$Mnconstructor = Macro.make(simpleSymbol9, new ModuleMethod(reflectionVar, 2, null, 4097), $instance);
        make$Mnrecord$Mntype = new ModuleMethod(reflectionVar, 3, Lit5, 8194);
        record$Mnconstructor = new ModuleMethod(reflectionVar, 4, Lit6, 8193);
        record$Mnaccessor = new ModuleMethod(reflectionVar, 6, Lit7, 8194);
        record$Mnmodifier = new ModuleMethod(reflectionVar, 7, Lit8, 8194);
        record$Qu = new ModuleMethod(reflectionVar, 8, Lit9, 4097);
        record$Mnpredicate = new ModuleMethod(reflectionVar, 9, Lit10, 4097);
        record$Mntype$Mndescriptor = new ModuleMethod(reflectionVar, 10, Lit11, 4097);
        record$Mntype$Mnname = new ModuleMethod(reflectionVar, 11, Lit12, 4097);
        record$Mntype$Mnfield$Mnnames = new ModuleMethod(reflectionVar, 12, Lit13, 4097);
        primitive$Mnarray$Mnnew = Macro.make(Lit14, Lit15, $instance);
        primitive$Mnarray$Mnset = Macro.make(Lit16, Lit17, $instance);
        primitive$Mnarray$Mnget = Macro.make(Lit18, Lit19, $instance);
        primitive$Mnarray$Mnlength = Macro.make(Lit20, Lit21, $instance);
        primitive$Mnget$Mnfield = Macro.make(Lit22, Lit23, $instance);
        primitive$Mnset$Mnfield = Macro.make(Lit24, Lit25, $instance);
        primitive$Mnget$Mnstatic = Macro.make(Lit26, Lit27, $instance);
        primitive$Mnset$Mnstatic = Macro.make(Lit28, Lit29, $instance);
        subtype$Qu = new ModuleMethod(reflectionVar, 13, Lit30, 8194);
        $instance.run();
    }

    public reflection() {
        ModuleInfo.register(this);
    }

    public static RecordConstructor recordConstructor(ClassType classType) {
        return recordConstructor(classType, null);
    }

    @Override // gnu.expr.ModuleBody
    public final void run(CallContext $ctx) {
        Consumer consumer = $ctx.consumer;
    }

    static Object lambda2(Object form) {
        Object[] allocVars = SyntaxPattern.allocVars(3, null);
        if (Lit1.match(form, allocVars, 0)) {
            Object generateTemporaries = std_syntax.generateTemporaries(Lit2.execute(allocVars, TemplateScope.make()));
            Object[] allocVars2 = SyntaxPattern.allocVars(4, allocVars);
            if (Lit3.match(generateTemporaries, allocVars2, 0)) {
                return Lit4.execute(allocVars2, TemplateScope.make());
            }
            return syntax_case.error("syntax-case", generateTemporaries);
        }
        return syntax_case.error("syntax-case", form);
    }

    public static ClassType makeRecordType(String name, LList fnames) {
        return Record.makeRecordType(name, fnames);
    }

    @Override // gnu.expr.ModuleBody
    public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
        switch (moduleMethod.selector) {
            case 3:
                callContext.value1 = obj;
                if (obj2 instanceof LList) {
                    callContext.value2 = obj2;
                    callContext.proc = moduleMethod;
                    callContext.pc = 2;
                    return 0;
                }
                return -786430;
            case 4:
                if (LangObjType.coerceToClassTypeOrNull(obj) != null) {
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.proc = moduleMethod;
                    callContext.pc = 2;
                    return 0;
                }
                return -786431;
            case 5:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            default:
                return super.match2(moduleMethod, obj, obj2, callContext);
            case 6:
                if (LangObjType.coerceToClassTypeOrNull(obj) != null) {
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.proc = moduleMethod;
                    callContext.pc = 2;
                    return 0;
                }
                return -786431;
            case 7:
                if (LangObjType.coerceToClassTypeOrNull(obj) != null) {
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.proc = moduleMethod;
                    callContext.pc = 2;
                    return 0;
                }
                return -786431;
            case 13:
                if (LangObjType.coerceToTypeOrNull(obj) != null) {
                    callContext.value1 = obj;
                    if (LangObjType.coerceToTypeOrNull(obj2) != null) {
                        callContext.value2 = obj2;
                        callContext.proc = moduleMethod;
                        callContext.pc = 2;
                        return 0;
                    }
                    return -786430;
                }
                return -786431;
        }
    }

    public static RecordConstructor recordConstructor(ClassType cl, Object flds) {
        return new RecordConstructor(cl, flds);
    }

    @Override // gnu.expr.ModuleBody
    public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
        switch (moduleMethod.selector) {
            case 2:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 3:
            case 5:
            case 6:
            case 7:
            default:
                return super.match1(moduleMethod, obj, callContext);
            case 4:
                if (LangObjType.coerceToClassTypeOrNull(obj) != null) {
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                }
                return -786431;
            case 8:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 9:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 10:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 11:
                if (LangObjType.coerceToClassTypeOrNull(obj) != null) {
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                }
                return -786431;
            case 12:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
        }
    }

    public static GetFieldProc recordAccessor(ClassType classType, String fname) {
        return new GetFieldProc(classType, fname);
    }

    public static SetFieldProc recordModifier(ClassType classType, String fname) {
        return new SetFieldProc(classType, fname);
    }

    public static boolean isRecord(Object obj) {
        return obj instanceof Record;
    }

    /* compiled from: reflection.scm */
    /* loaded from: classes.dex */
    public class frame extends ModuleBody {
        final ModuleMethod lambda$Fn1;
        Object rtype;

        public frame() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 1, null, 4097);
            moduleMethod.setProperty("source-location", "reflection.scm:30");
            this.lambda$Fn1 = moduleMethod;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            return moduleMethod.selector == 1 ? lambda1(obj) ? Boolean.TRUE : Boolean.FALSE : super.apply1(moduleMethod, obj);
        }

        boolean lambda1(Object object) {
            Object obj = this.rtype;
            try {
                return ((Type) obj).isInstance(object);
            } catch (ClassCastException e) {
                throw new WrongType(e, "gnu.bytecode.Type.isInstance(java.lang.Object)", 1, obj);
            }
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
    }

    public static Procedure recordPredicate(Object rtype) {
        frame frameVar = new frame();
        frameVar.rtype = rtype;
        return frameVar.lambda$Fn1;
    }

    public static Type recordTypeDescriptor(Object object) {
        return Type.make(object.getClass());
    }

    public static String recordTypeName(ClassType rtd) {
        return Compilation.demangleName(rtd.getName(), true);
    }

    public static LList recordTypeFieldNames(Object rtd) {
        try {
            return Record.typeFieldNames(LangObjType.coerceToClassType(rtd));
        } catch (ClassCastException e) {
            throw new WrongType(e, "kawa.lang.Record.typeFieldNames(class-type)", 1, rtd);
        }
    }

    @Override // gnu.expr.ModuleBody
    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        switch (moduleMethod.selector) {
            case 2:
                return lambda2(obj);
            case 3:
            case 5:
            case 6:
            case 7:
            default:
                return super.apply1(moduleMethod, obj);
            case 4:
                try {
                    return recordConstructor(LangObjType.coerceToClassType(obj));
                } catch (ClassCastException e) {
                    throw new WrongType(e, "record-constructor", 1, obj);
                }
            case 8:
                return isRecord(obj) ? Boolean.TRUE : Boolean.FALSE;
            case 9:
                return recordPredicate(obj);
            case 10:
                return recordTypeDescriptor(obj);
            case 11:
                try {
                    return recordTypeName(LangObjType.coerceToClassType(obj));
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "record-type-name", 1, obj);
                }
            case 12:
                return recordTypeFieldNames(obj);
        }
    }

    public static boolean isSubtype(Type t1, Type t2) {
        return t1.isSubtype(t2);
    }

    @Override // gnu.expr.ModuleBody
    public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
        switch (moduleMethod.selector) {
            case 3:
                try {
                    return makeRecordType(obj != null ? obj.toString() : null, (LList) obj2);
                } catch (ClassCastException e) {
                    throw new WrongType(e, "make-record-type", 2, obj2);
                }
            case 4:
                try {
                    return recordConstructor(LangObjType.coerceToClassType(obj), obj2);
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "record-constructor", 1, obj);
                }
            case 5:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            default:
                return super.apply2(moduleMethod, obj, obj2);
            case 6:
                try {
                    return recordAccessor(LangObjType.coerceToClassType(obj), obj2 != null ? obj2.toString() : null);
                } catch (ClassCastException e3) {
                    throw new WrongType(e3, "record-accessor", 1, obj);
                }
            case 7:
                try {
                    return recordModifier(LangObjType.coerceToClassType(obj), obj2 != null ? obj2.toString() : null);
                } catch (ClassCastException e4) {
                    throw new WrongType(e4, "record-modifier", 1, obj);
                }
            case 13:
                try {
                    try {
                        return isSubtype(LangObjType.coerceToType(obj), LangObjType.coerceToType(obj2)) ? Boolean.TRUE : Boolean.FALSE;
                    } catch (ClassCastException e5) {
                        throw new WrongType(e5, "subtype?", 2, obj2);
                    }
                } catch (ClassCastException e6) {
                    throw new WrongType(e6, "subtype?", 1, obj);
                }
        }
    }
}
