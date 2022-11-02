package gnu.kawa.slib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.Apply;
import gnu.kawa.functions.IsEq;
import gnu.kawa.lispexpr.LispLanguage;
import gnu.kawa.slib.condition;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import kawa.lang.Macro;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxRule;
import kawa.lang.SyntaxRules;
import kawa.lib.lists;
import kawa.lib.misc;
import kawa.standard.Scheme;
import kawa.standard.append;

/* compiled from: conditions.scm */
/* loaded from: classes.dex */
public class conditions extends ModuleBody {
    public static Object $Amcondition;
    public static Object $Amerror;
    public static Object $Ammessage;
    public static Object $Amserious;
    static final Class $Lscondition$Mntype$Gr;
    public static final Class $Prvt$$Lscondition$Gr;
    public static final ModuleMethod $Prvt$type$Mnfield$Mnalist$Mn$Grcondition;
    public static final conditions $instance;
    static final SimpleSymbol Lit0;
    static final SimpleSymbol Lit1;
    static final SimpleSymbol Lit10;
    static final SimpleSymbol Lit11;
    static final SimpleSymbol Lit12;
    static final SimpleSymbol Lit13;
    static final SyntaxRules Lit14;
    static final SimpleSymbol Lit15;
    static final SimpleSymbol Lit16;
    static final SimpleSymbol Lit17;
    static final SimpleSymbol Lit18;
    static final SyntaxRules Lit19;
    static final PairWithPosition Lit2;
    static final SimpleSymbol Lit3;
    static final SimpleSymbol Lit4;
    static final SimpleSymbol Lit5;
    static final SimpleSymbol Lit6;
    static final SimpleSymbol Lit7;
    static final SimpleSymbol Lit8;
    static final SyntaxRules Lit9;
    public static final Macro condition;
    public static final ModuleMethod condition$Mnhas$Mntype$Qu;
    public static final ModuleMethod condition$Mnref;
    static final Macro condition$Mntype$Mnfield$Mnalist;
    public static final ModuleMethod condition$Mntype$Qu;
    public static final ModuleMethod condition$Qu;
    public static final Macro define$Mncondition$Mntype;
    public static final ModuleMethod extract$Mncondition;
    public static final ModuleMethod make$Mncompound$Mncondition;
    public static final ModuleMethod make$Mncondition;
    public static final ModuleMethod make$Mncondition$Mntype;
    static final SimpleSymbol Lit22 = (SimpleSymbol) new SimpleSymbol("thing").readResolve();
    static final SimpleSymbol Lit21 = (SimpleSymbol) new SimpleSymbol(LispLanguage.quote_sym).readResolve();
    static final SimpleSymbol Lit20 = (SimpleSymbol) new SimpleSymbol("type-field-alist->condition").readResolve();

    public conditions() {
        ModuleInfo.register(this);
    }

    @Override // gnu.expr.ModuleBody
    public final void run(CallContext $ctx) {
        Consumer consumer = $ctx.consumer;
        $Amcondition = new condition.Mntype(Lit0, Boolean.FALSE, LList.Empty, LList.Empty);
        SimpleSymbol simpleSymbol = Lit1;
        Object obj = $Amcondition;
        try {
            $Ammessage = makeConditionType(simpleSymbol, (condition.Mntype) obj, Lit2);
            SimpleSymbol simpleSymbol2 = Lit3;
            Object obj2 = $Amcondition;
            try {
                $Amserious = makeConditionType(simpleSymbol2, (condition.Mntype) obj2, LList.Empty);
                SimpleSymbol simpleSymbol3 = Lit4;
                Object obj3 = $Amserious;
                try {
                    $Amerror = makeConditionType(simpleSymbol3, (condition.Mntype) obj3, LList.Empty);
                } catch (ClassCastException e) {
                    throw new WrongType(e, "make-condition-type", 1, obj3);
                }
            } catch (ClassCastException e2) {
                throw new WrongType(e2, "make-condition-type", 1, obj2);
            }
        } catch (ClassCastException e3) {
            throw new WrongType(e3, "make-condition-type", 1, obj);
        }
    }

    static {
        SimpleSymbol simpleSymbol = (SimpleSymbol) new SimpleSymbol("condition").readResolve();
        Lit18 = simpleSymbol;
        Lit19 = new SyntaxRules(new Object[]{simpleSymbol}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018]\f\u0007-\f\u000f\f\u0017\b\b\u0010\b\u0000\u0018\b", new Object[0], 3), "\u0003\u0005\u0005", "\u0011\u0018\u0004\b\u0011\u0018\f\b\u0005\u0011\u0018\u0014\t\u0003\b\u0011\u0018\f\b\r\u0011\u0018\u0014)\u0011\u0018\u001c\b\u000b\b\u0013", new Object[]{Lit20, (SimpleSymbol) new SimpleSymbol("list").readResolve(), (SimpleSymbol) new SimpleSymbol("cons").readResolve(), Lit21}, 2)}, 3);
        Lit17 = (SimpleSymbol) new SimpleSymbol("extract-condition").readResolve();
        Lit16 = (SimpleSymbol) new SimpleSymbol("make-compound-condition").readResolve();
        Lit15 = (SimpleSymbol) new SimpleSymbol("condition-ref").readResolve();
        SimpleSymbol simpleSymbol2 = (SimpleSymbol) new SimpleSymbol("condition-type-field-alist").readResolve();
        Lit13 = simpleSymbol2;
        Lit14 = new SyntaxRules(new Object[]{simpleSymbol2}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\b", new Object[0], 1), "\u0001", "\u0011\u0018\u0004\b\u0011\u0018\f\u0011\u0018\u0014\b\u0003", new Object[]{PairWithPosition.make((SimpleSymbol) new SimpleSymbol("$lookup$").readResolve(), Pair.make((SimpleSymbol) new SimpleSymbol("*").readResolve(), Pair.make(Pair.make((SimpleSymbol) new SimpleSymbol(LispLanguage.quasiquote_sym).readResolve(), Pair.make((SimpleSymbol) new SimpleSymbol(".type-field-alist").readResolve(), LList.Empty)), LList.Empty)), "conditions.scm", 581639), (SimpleSymbol) new SimpleSymbol("as").readResolve(), (SimpleSymbol) new SimpleSymbol("<condition>").readResolve()}, 0)}, 1);
        Lit12 = (SimpleSymbol) new SimpleSymbol("condition-has-type?").readResolve();
        Lit11 = (SimpleSymbol) new SimpleSymbol("make-condition").readResolve();
        Lit10 = (SimpleSymbol) new SimpleSymbol("condition?").readResolve();
        SimpleSymbol simpleSymbol3 = (SimpleSymbol) new SimpleSymbol("define-condition-type").readResolve();
        Lit8 = simpleSymbol3;
        SyntaxPattern syntaxPattern = new SyntaxPattern("\f\u0018\f\u0007\f\u000f\f\u0017-\f\u001f\f'\b\u0018\u0010\b", new Object[0], 5);
        SimpleSymbol simpleSymbol4 = (SimpleSymbol) new SimpleSymbol("make-condition-type").readResolve();
        Lit7 = simpleSymbol4;
        Lit9 = new SyntaxRules(new Object[]{simpleSymbol3}, new SyntaxRule[]{new SyntaxRule(syntaxPattern, "\u0001\u0001\u0001\u0003\u0003", "\u0011\u0018\u0004É\u0011\u0018\f\t\u0003\b\u0011\u0018\u0014)\u0011\u0018\u001c\b\u0003\t\u000b\b\u0011\u0018\u001c\b\b\u001d\u001bÁ\u0011\u0018\f!\t\u0013\u0018$\b\u0011\u0018,\u0011\u00184\b\u0011\u0018<\u0011\u0018D\b\u0003\b%\u0011\u0018\f!\t#\u0018L\b\u0011\u0018TA\u0011\u0018\\\u0011\u0018d\b\u0003\b\u0011\u0018\u001c\b\u001b", new Object[]{(SimpleSymbol) new SimpleSymbol("begin").readResolve(), (SimpleSymbol) new SimpleSymbol("define").readResolve(), simpleSymbol4, Lit21, PairWithPosition.make(Lit22, LList.Empty, "conditions.scm", 327708), (SimpleSymbol) new SimpleSymbol("and").readResolve(), PairWithPosition.make(Lit10, PairWithPosition.make(Lit22, LList.Empty, "conditions.scm", 331803), "conditions.scm", 331791), Lit12, Lit22, PairWithPosition.make(Lit18, LList.Empty, "conditions.scm", 339996), Lit15, Lit17, Lit18}, 1)}, 5);
        Lit6 = (SimpleSymbol) new SimpleSymbol("condition-type?").readResolve();
        Lit5 = (SimpleSymbol) new SimpleSymbol("message").readResolve();
        Lit4 = (SimpleSymbol) new SimpleSymbol("&error").readResolve();
        Lit3 = (SimpleSymbol) new SimpleSymbol("&serious").readResolve();
        Lit2 = PairWithPosition.make(Lit5, LList.Empty, "conditions.scm", 925699);
        Lit1 = (SimpleSymbol) new SimpleSymbol("&message").readResolve();
        Lit0 = (SimpleSymbol) new SimpleSymbol("&condition").readResolve();
        $instance = new conditions();
        $Lscondition$Mntype$Gr = condition.Mntype.class;
        conditions conditionsVar = $instance;
        condition$Mntype$Qu = new ModuleMethod(conditionsVar, 2, Lit6, 4097);
        make$Mncondition$Mntype = new ModuleMethod(conditionsVar, 3, Lit7, 12291);
        define$Mncondition$Mntype = Macro.make(Lit8, Lit9, $instance);
        $Prvt$$Lscondition$Gr = condition.class;
        condition$Qu = new ModuleMethod(conditionsVar, 4, Lit10, 4097);
        make$Mncondition = new ModuleMethod(conditionsVar, 5, Lit11, -4095);
        condition$Mnhas$Mntype$Qu = new ModuleMethod(conditionsVar, 6, Lit12, 8194);
        condition$Mntype$Mnfield$Mnalist = Macro.make(Lit13, Lit14, $instance);
        condition$Mnref = new ModuleMethod(conditionsVar, 7, Lit15, 8194);
        make$Mncompound$Mncondition = new ModuleMethod(conditionsVar, 8, Lit16, -4095);
        extract$Mncondition = new ModuleMethod(conditionsVar, 9, Lit17, 8194);
        condition = Macro.make(Lit18, Lit19, $instance);
        $Prvt$type$Mnfield$Mnalist$Mn$Grcondition = new ModuleMethod(conditionsVar, 10, Lit20, 4097);
        $instance.run();
    }

    public static boolean isConditionType(Object obj) {
        return obj instanceof condition.Mntype;
    }

    @Override // gnu.expr.ModuleBody
    public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
        switch (moduleMethod.selector) {
            case 2:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 4:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 10:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            default:
                return super.match1(moduleMethod, obj, callContext);
        }
    }

    public static condition.Mntype makeConditionType(Symbol name, condition.Mntype supertype, Object fields) {
        if (!lists.isNull(srfi1.lsetIntersection$V(Scheme.isEq, supertype.all$Mnfields, new Object[]{fields}))) {
            misc.error$V("duplicate field name", new Object[0]);
        }
        return new condition.Mntype(name, supertype, fields, append.append$V(new Object[]{supertype.all$Mnfields, fields}));
    }

    @Override // gnu.expr.ModuleBody
    public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
        if (moduleMethod.selector == 3) {
            try {
                try {
                    return makeConditionType((Symbol) obj, (condition.Mntype) obj2, obj3);
                } catch (ClassCastException e) {
                    throw new WrongType(e, "make-condition-type", 2, obj2);
                }
            } catch (ClassCastException e2) {
                throw new WrongType(e2, "make-condition-type", 1, obj);
            }
        }
        return super.apply3(moduleMethod, obj, obj2, obj3);
    }

    @Override // gnu.expr.ModuleBody
    public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
        if (moduleMethod.selector == 3) {
            if (obj instanceof Symbol) {
                callContext.value1 = obj;
                if (obj2 instanceof condition.Mntype) {
                    callContext.value2 = obj2;
                    callContext.value3 = obj3;
                    callContext.proc = moduleMethod;
                    callContext.pc = 3;
                    return 0;
                }
                return -786430;
            }
            return -786431;
        }
        return super.match3(moduleMethod, obj, obj2, obj3, callContext);
    }

    static boolean isConditionSubtype(condition.Mntype subtype, condition.Mntype supertype) {
        while (subtype != Boolean.FALSE) {
            if (subtype == supertype) {
                return true;
            }
            subtype = (condition.Mntype) subtype.supertype;
        }
        return false;
    }

    static Object conditionTypeFieldSupertype(condition.Mntype condition$Mntype, Object field) {
        while (condition$Mntype != Boolean.FALSE) {
            if (lists.memq(field, condition$Mntype.fields) == Boolean.FALSE) {
                condition$Mntype = (condition.Mntype) condition$Mntype.supertype;
            } else {
                return condition$Mntype;
            }
        }
        return Boolean.FALSE;
    }

    public static boolean isCondition(Object obj) {
        return obj instanceof condition;
    }

    public static condition makeCondition$V(condition.Mntype type, Object[] argsArray) {
        LList field$Mnplist = LList.makeList(argsArray, 0);
        Object alist = lambda1label(field$Mnplist);
        IsEq isEq = Scheme.isEq;
        Object[] objArr = new Object[2];
        objArr[0] = type.all$Mnfields;
        Object obj = LList.Empty;
        Object arg0 = alist;
        while (arg0 != LList.Empty) {
            try {
                Pair arg02 = (Pair) arg0;
                Object arg03 = arg02.getCdr();
                obj = Pair.make(lists.car.apply1(arg02.getCar()), obj);
                arg0 = arg03;
            } catch (ClassCastException e) {
                throw new WrongType(e, "arg0", -2, arg0);
            }
        }
        objArr[1] = LList.reverseInPlace(obj);
        if (srfi1.lset$Eq$V(isEq, objArr) == Boolean.FALSE) {
            misc.error$V("condition fields don't match condition type", new Object[0]);
        }
        return new condition(LList.list1(lists.cons(type, alist)));
    }

    @Override // gnu.expr.ModuleBody
    public int matchN(ModuleMethod moduleMethod, Object[] objArr, CallContext callContext) {
        switch (moduleMethod.selector) {
            case 5:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 6:
            case 7:
            default:
                return super.matchN(moduleMethod, objArr, callContext);
            case 8:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
        }
    }

    public static Object lambda1label(Object plist) {
        return lists.isNull(plist) ? LList.Empty : lists.cons(lists.cons(lists.car.apply1(plist), lists.cadr.apply1(plist)), lambda1label(lists.cddr.apply1(plist)));
    }

    public static boolean isConditionHasType(Object condition2, condition.Mntype type) {
        Object types = conditionTypes(condition2);
        while (true) {
            Object apply1 = lists.car.apply1(types);
            try {
                boolean x = isConditionSubtype((condition.Mntype) apply1, type);
                if (x) {
                    return x;
                }
                types = lists.cdr.apply1(types);
            } catch (ClassCastException e) {
                throw new WrongType(e, "condition-subtype?", 0, apply1);
            }
        }
    }

    @Override // gnu.expr.ModuleBody
    public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
        switch (moduleMethod.selector) {
            case 6:
                callContext.value1 = obj;
                if (obj2 instanceof condition.Mntype) {
                    callContext.value2 = obj2;
                    callContext.proc = moduleMethod;
                    callContext.pc = 2;
                    return 0;
                }
                return -786430;
            case 7:
                if (obj instanceof condition) {
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.proc = moduleMethod;
                    callContext.pc = 2;
                    return 0;
                }
                return -786431;
            case 8:
            default:
                return super.match2(moduleMethod, obj, obj2, callContext);
            case 9:
                if (obj instanceof condition) {
                    callContext.value1 = obj;
                    if (obj2 instanceof condition.Mntype) {
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

    public static Object conditionRef(condition condition2, Object field) {
        return typeFieldAlistRef(condition2.type$Mnfield$Mnalist, field);
    }

    static Object typeFieldAlistRef(Object type$Mnfield$Mnalist, Object field) {
        while (!lists.isNull(type$Mnfield$Mnalist)) {
            Object temp = lists.assq(field, lists.cdr.apply1(lists.car.apply1(type$Mnfield$Mnalist)));
            if (temp == Boolean.FALSE) {
                type$Mnfield$Mnalist = lists.cdr.apply1(type$Mnfield$Mnalist);
            } else {
                return lists.cdr.apply1(temp);
            }
        }
        return misc.error$V("type-field-alist-ref: field not found", new Object[]{type$Mnfield$Mnalist, field});
    }

    public static condition makeCompoundCondition$V(Object condition$Mn1, Object[] argsArray) {
        LList conditions = LList.makeList(argsArray, 0);
        Apply apply = Scheme.apply;
        append appendVar = append.append;
        Pair arg0 = lists.cons(condition$Mn1, conditions);
        Object obj = LList.Empty;
        while (arg0 != LList.Empty) {
            try {
                Pair arg02 = arg0;
                Object arg03 = arg02.getCdr();
                obj = Pair.make(Scheme.applyToArgs.apply2(condition$Mntype$Mnfield$Mnalist, arg02.getCar()), obj);
                arg0 = arg03;
            } catch (ClassCastException e) {
                throw new WrongType(e, "arg0", -2, arg0);
            }
        }
        return new condition(apply.apply2(appendVar, LList.reverseInPlace(obj)));
    }

    @Override // gnu.expr.ModuleBody
    public Object applyN(ModuleMethod moduleMethod, Object[] objArr) {
        switch (moduleMethod.selector) {
            case 5:
                Object obj = objArr[0];
                try {
                    condition.Mntype mntype = (condition.Mntype) obj;
                    int length = objArr.length - 1;
                    Object[] objArr2 = new Object[length];
                    while (true) {
                        length--;
                        if (length < 0) {
                            return makeCondition$V(mntype, objArr2);
                        }
                        objArr2[length] = objArr[length + 1];
                    }
                } catch (ClassCastException e) {
                    throw new WrongType(e, "make-condition", 1, obj);
                }
            case 6:
            case 7:
            default:
                return super.applyN(moduleMethod, objArr);
            case 8:
                Object obj2 = objArr[0];
                int length2 = objArr.length - 1;
                Object[] objArr3 = new Object[length2];
                while (true) {
                    length2--;
                    if (length2 < 0) {
                        return makeCompoundCondition$V(obj2, objArr3);
                    }
                    objArr3[length2] = objArr[length2 + 1];
                }
        }
    }

    public static condition extractCondition(condition condition2, condition.Mntype type) {
        frame frameVar = new frame();
        frameVar.type = type;
        Object entry = srfi1.find(frameVar.lambda$Fn1, condition2.type$Mnfield$Mnalist);
        if (entry == Boolean.FALSE) {
            misc.error$V("extract-condition: invalid condition type", new Object[]{condition2, frameVar.type});
        }
        condition.Mntype mntype = frameVar.type;
        Object arg0 = frameVar.type.all$Mnfields;
        Object obj = LList.Empty;
        while (arg0 != LList.Empty) {
            try {
                Pair arg02 = (Pair) arg0;
                Object arg03 = arg02.getCdr();
                Object field = arg02.getCar();
                obj = Pair.make(lists.assq(field, lists.cdr.apply1(entry)), obj);
                arg0 = arg03;
            } catch (ClassCastException e) {
                throw new WrongType(e, "arg0", -2, arg0);
            }
        }
        return new condition(LList.list1(lists.cons(mntype, LList.reverseInPlace(obj))));
    }

    @Override // gnu.expr.ModuleBody
    public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
        switch (moduleMethod.selector) {
            case 6:
                try {
                    return isConditionHasType(obj, (condition.Mntype) obj2) ? Boolean.TRUE : Boolean.FALSE;
                } catch (ClassCastException e) {
                    throw new WrongType(e, "condition-has-type?", 2, obj2);
                }
            case 7:
                try {
                    return conditionRef((condition) obj, obj2);
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "condition-ref", 1, obj);
                }
            case 8:
            default:
                return super.apply2(moduleMethod, obj, obj2);
            case 9:
                try {
                    try {
                        return extractCondition((condition) obj, (condition.Mntype) obj2);
                    } catch (ClassCastException e3) {
                        throw new WrongType(e3, "extract-condition", 2, obj2);
                    }
                } catch (ClassCastException e4) {
                    throw new WrongType(e4, "extract-condition", 1, obj);
                }
        }
    }

    /* compiled from: conditions.scm */
    /* loaded from: classes.dex */
    public class frame extends ModuleBody {
        final ModuleMethod lambda$Fn1;
        condition.Mntype type;

        public frame() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 1, null, 4097);
            moduleMethod.setProperty("source-location", "conditions.scm:166");
            this.lambda$Fn1 = moduleMethod;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            return moduleMethod.selector == 1 ? lambda2(obj) ? Boolean.TRUE : Boolean.FALSE : super.apply1(moduleMethod, obj);
        }

        boolean lambda2(Object entry) {
            Object apply1 = lists.car.apply1(entry);
            try {
                return conditions.isConditionSubtype((condition.Mntype) apply1, this.type);
            } catch (ClassCastException e) {
                throw new WrongType(e, "condition-subtype?", 0, apply1);
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

    public static condition typeFieldAlist$To$Condition(Object type$Mnfield$Mnalist) {
        Object arg0 = type$Mnfield$Mnalist;
        Pair pair = LList.Empty;
        while (arg0 != LList.Empty) {
            try {
                Pair arg02 = (Pair) arg0;
                Object arg03 = arg02.getCdr();
                Object entry = arg02.getCar();
                Object apply1 = lists.car.apply1(entry);
                Object arg04 = ((condition.Mntype) lists.car.apply1(entry)).all$Mnfields;
                Object obj = LList.Empty;
                while (arg04 != LList.Empty) {
                    try {
                        Pair arg05 = (Pair) arg04;
                        Object arg06 = arg05.getCdr();
                        Object field = arg05.getCar();
                        Object x = lists.assq(field, lists.cdr.apply1(entry));
                        if (x == Boolean.FALSE) {
                            x = lists.cons(field, typeFieldAlistRef(type$Mnfield$Mnalist, field));
                        }
                        obj = Pair.make(x, obj);
                        arg04 = arg06;
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "arg0", -2, arg04);
                    }
                }
                Pair result = Pair.make(lists.cons(apply1, LList.reverseInPlace(obj)), pair);
                pair = result;
                arg0 = arg03;
            } catch (ClassCastException e2) {
                throw new WrongType(e2, "arg0", -2, arg0);
            }
        }
        return new condition(LList.reverseInPlace(pair));
    }

    @Override // gnu.expr.ModuleBody
    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        switch (moduleMethod.selector) {
            case 2:
                return isConditionType(obj) ? Boolean.TRUE : Boolean.FALSE;
            case 4:
                return isCondition(obj) ? Boolean.TRUE : Boolean.FALSE;
            case 10:
                return typeFieldAlist$To$Condition(obj);
            default:
                return super.apply1(moduleMethod, obj);
        }
    }

    static Object conditionTypes(Object condition2) {
        Object arg0 = ((condition) condition2).type$Mnfield$Mnalist;
        Object obj = LList.Empty;
        while (arg0 != LList.Empty) {
            try {
                Pair arg02 = (Pair) arg0;
                Object arg03 = arg02.getCdr();
                obj = Pair.make(lists.car.apply1(arg02.getCar()), obj);
                arg0 = arg03;
            } catch (ClassCastException e) {
                throw new WrongType(e, "arg0", -2, arg0);
            }
        }
        return LList.reverseInPlace(obj);
    }

    static Object checkConditionTypeFieldAlist(Object the$Mntype$Mnfield$Mnalist) {
        boolean x;
        for (Object type$Mnfield$Mnalist = the$Mntype$Mnfield$Mnalist; !lists.isNull(type$Mnfield$Mnalist); type$Mnfield$Mnalist = lists.cdr.apply1(type$Mnfield$Mnalist)) {
            Object entry = lists.car.apply1(type$Mnfield$Mnalist);
            Object apply1 = lists.car.apply1(entry);
            try {
                condition.Mntype type = (condition.Mntype) apply1;
                Object field$Mnalist = lists.cdr.apply1(entry);
                Object obj = LList.Empty;
                Object arg0 = field$Mnalist;
                while (arg0 != LList.Empty) {
                    try {
                        Pair arg02 = (Pair) arg0;
                        Object arg03 = arg02.getCdr();
                        obj = Pair.make(lists.car.apply1(arg02.getCar()), obj);
                        arg0 = arg03;
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "arg0", -2, arg0);
                    }
                }
                LList fields = LList.reverseInPlace(obj);
                Object all$Mnfields = type.all$Mnfields;
                Object arg04 = srfi1.lsetDifference$V(Scheme.isEq, all$Mnfields, new Object[]{fields});
                while (arg04 != LList.Empty) {
                    try {
                        Pair arg05 = (Pair) arg04;
                        Object missing$Mnfield = arg05.getCar();
                        Object supertype = conditionTypeFieldSupertype(type, missing$Mnfield);
                        Object alist = the$Mntype$Mnfield$Mnalist;
                        while (true) {
                            Object apply12 = lists.car.apply1(lists.car.apply1(alist));
                            try {
                                try {
                                    x = isConditionSubtype((condition.Mntype) apply12, (condition.Mntype) supertype);
                                    if (x) {
                                        break;
                                    }
                                    alist = lists.cdr.apply1(alist);
                                } catch (ClassCastException e2) {
                                    throw new WrongType(e2, "condition-subtype?", 1, supertype);
                                }
                            } catch (ClassCastException e3) {
                                throw new WrongType(e3, "condition-subtype?", 0, apply12);
                            }
                        }
                        if (!x) {
                            misc.error$V("missing field in condition construction", new Object[]{type, missing$Mnfield});
                        }
                        arg04 = arg05.getCdr();
                    } catch (ClassCastException e4) {
                        throw new WrongType(e4, "arg0", -2, arg04);
                    }
                }
            } catch (ClassCastException e5) {
                throw new WrongType(e5, "type", -2, apply1);
            }
        }
        return Values.empty;
    }

    static boolean isMessageCondition(Object thing) {
        boolean x = isCondition(thing);
        if (x) {
            Object obj = $Ammessage;
            try {
                return isConditionHasType(thing, (condition.Mntype) obj);
            } catch (ClassCastException e) {
                throw new WrongType(e, "condition-has-type?", 1, obj);
            }
        }
        return x;
    }

    static Object conditionMessage(Object condition2) {
        try {
            condition conditionVar = (condition) condition2;
            Object obj = $Ammessage;
            try {
                return conditionRef(extractCondition(conditionVar, (condition.Mntype) obj), Lit5);
            } catch (ClassCastException e) {
                throw new WrongType(e, "extract-condition", 1, obj);
            }
        } catch (ClassCastException e2) {
            throw new WrongType(e2, "extract-condition", 0, condition2);
        }
    }

    static boolean isSeriousCondition(Object thing) {
        boolean x = isCondition(thing);
        if (x) {
            Object obj = $Amserious;
            try {
                return isConditionHasType(thing, (condition.Mntype) obj);
            } catch (ClassCastException e) {
                throw new WrongType(e, "condition-has-type?", 1, obj);
            }
        }
        return x;
    }

    static boolean isError(Object thing) {
        boolean x = isCondition(thing);
        if (x) {
            Object obj = $Amerror;
            try {
                return isConditionHasType(thing, (condition.Mntype) obj);
            } catch (ClassCastException e) {
                throw new WrongType(e, "condition-has-type?", 1, obj);
            }
        }
        return x;
    }
}
