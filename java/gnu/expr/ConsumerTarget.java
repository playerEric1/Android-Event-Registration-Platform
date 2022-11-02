package gnu.expr;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.PrimType;
import gnu.bytecode.Scope;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.kawa.reflect.OccurrenceType;
import gnu.text.PrettyWriter;

/* loaded from: classes.dex */
public class ConsumerTarget extends Target {
    Variable consumer;
    boolean isContextTarget;

    public ConsumerTarget(Variable consumer) {
        this.consumer = consumer;
    }

    public Variable getConsumerVariable() {
        return this.consumer;
    }

    public final boolean isContextTarget() {
        return this.isContextTarget;
    }

    public static Target makeContextTarget(Compilation comp) {
        CodeAttr code = comp.getCode();
        comp.loadCallContext();
        code.emitGetField(Compilation.typeCallContext.getDeclaredField("consumer"));
        Scope scope = code.getCurrentScope();
        Variable result = scope.addVariable(code, Compilation.typeConsumer, "$result");
        code.emitStore(result);
        ConsumerTarget target = new ConsumerTarget(result);
        target.isContextTarget = true;
        return target;
    }

    public static void compileUsingConsumer(Expression exp, Compilation comp, Target target) {
        if ((target instanceof ConsumerTarget) || (target instanceof IgnoreTarget)) {
            exp.compile(comp, target);
            return;
        }
        ClassType typeValues = Compilation.typeValues;
        compileUsingConsumer(exp, comp, target, typeValues.getDeclaredMethod("make", 0), typeValues.getDeclaredMethod("canonicalize", 0));
    }

    public static void compileUsingConsumer(Expression exp, Compilation comp, Target target, Method makeMethod, Method resultMethod) {
        Type ctype;
        CodeAttr code = comp.getCode();
        Scope scope = code.pushScope();
        if (makeMethod.getName() == "<init>") {
            ClassType cltype = makeMethod.getDeclaringClass();
            ctype = cltype;
            code.emitNew(cltype);
            code.emitDup(ctype);
            code.emitInvoke(makeMethod);
        } else {
            ctype = makeMethod.getReturnType();
            code.emitInvokeStatic(makeMethod);
        }
        Variable consumer = scope.addVariable(code, ctype, null);
        ConsumerTarget ctarget = new ConsumerTarget(consumer);
        code.emitStore(consumer);
        exp.compile(comp, ctarget);
        code.emitLoad(consumer);
        if (resultMethod != null) {
            code.emitInvoke(resultMethod);
        }
        code.popScope();
        if (resultMethod != null) {
            ctype = resultMethod.getReturnType();
        }
        target.compileFromStack(comp, ctype);
    }

    @Override // gnu.expr.Target
    public void compileFromStack(Compilation comp, Type stackType) {
        compileFromStack(comp, stackType, -1);
    }

    void compileFromStack(Compilation comp, Type stackType, int consumerPushed) {
        char sig;
        CodeAttr code = comp.getCode();
        String methodName = null;
        Method method = null;
        Type methodArg = null;
        boolean islong = false;
        Type stackType2 = stackType.getImplementationType();
        if (stackType2 instanceof PrimType) {
            sig = stackType2.getSignature().charAt(0);
            switch (sig) {
                case 'B':
                case 'I':
                case PrettyWriter.NEWLINE_SPACE /* 83 */:
                    methodName = "writeInt";
                    methodArg = Type.intType;
                    break;
                case 'C':
                    methodName = "append";
                    methodArg = Type.charType;
                    break;
                case 'D':
                    methodName = "writeDouble";
                    methodArg = Type.doubleType;
                    islong = true;
                    break;
                case PrettyWriter.NEWLINE_FILL /* 70 */:
                    methodName = "writeFloat";
                    methodArg = Type.floatType;
                    break;
                case 'J':
                    methodName = "writeLong";
                    methodArg = Type.longType;
                    islong = true;
                    break;
                case 'V':
                    return;
                case 'Z':
                    methodName = "writeBoolean";
                    methodArg = Type.booleanType;
                    break;
            }
        } else {
            sig = 0;
            if (consumerPushed == 1 || OccurrenceType.itemCountIsOne(stackType2)) {
                methodName = "writeObject";
                methodArg = Type.pointer_type;
            } else {
                Method method2 = Compilation.typeValues.getDeclaredMethod("writeValues", 2);
                code.emitLoad(this.consumer);
                if (consumerPushed == 0) {
                    code.emitSwap();
                }
                code.emitInvokeStatic(method2);
                return;
            }
        }
        if (consumerPushed < 0) {
            if (islong) {
                code.pushScope();
                Variable temp = code.addLocal(stackType2);
                code.emitStore(temp);
                code.emitLoad(this.consumer);
                code.emitLoad(temp);
                code.popScope();
            } else {
                code.emitLoad(this.consumer);
                code.emitSwap();
            }
        }
        if (0 == 0 && methodName != null) {
            Type[] methodArgs = {methodArg};
            method = Compilation.typeConsumer.getDeclaredMethod(methodName, methodArgs);
        }
        if (method != null) {
            code.emitInvokeInterface(method);
        }
        if (sig == 'C') {
            code.emitPop(1);
        }
    }

    public boolean compileWrite(Expression exp, Compilation comp) {
        Type stackType = exp.getType();
        Type implType = stackType.getImplementationType();
        if ((!(implType instanceof PrimType) || implType.isVoid()) && !OccurrenceType.itemCountIsOne(implType)) {
            return false;
        }
        comp.getCode().emitLoad(this.consumer);
        Target starget = StackTarget.getInstance(implType);
        exp.compile(comp, starget);
        compileFromStack(comp, implType, 1);
        return true;
    }

    @Override // gnu.expr.Target
    public Type getType() {
        return Compilation.scmSequenceType;
    }
}
