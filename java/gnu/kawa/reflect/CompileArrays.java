package gnu.kawa.reflect;

import gnu.bytecode.ArrayType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.expr.InlineCalls;
import gnu.expr.Inlineable;
import gnu.expr.Target;
import gnu.kawa.lispexpr.LangPrimType;
import gnu.mapping.Procedure;
import gnu.mapping.Values;
import gnu.text.PrettyWriter;

/* loaded from: classes.dex */
public class CompileArrays implements Inlineable {
    public char code;
    Procedure proc;

    public CompileArrays(Procedure proc, char code) {
        this.proc = proc;
        this.code = code;
    }

    public static CompileArrays getForArrayGet(Object proc) {
        return new CompileArrays((Procedure) proc, 'G');
    }

    public static CompileArrays getForArraySet(Object proc) {
        return new CompileArrays((Procedure) proc, 'S');
    }

    public static CompileArrays getForArrayLength(Object proc) {
        return new CompileArrays((Procedure) proc, 'L');
    }

    public static CompileArrays getForArrayNew(Object proc) {
        return new CompileArrays((Procedure) proc, 'N');
    }

    @Override // gnu.expr.Inlineable
    public void compile(ApplyExp exp, Compilation comp, Target target) {
        switch (this.code) {
            case 'G':
                compileArrayGet((ArrayGet) this.proc, exp, comp, target);
                return;
            case PrettyWriter.NEWLINE_LINEAR /* 78 */:
                compileArrayNew((ArrayNew) this.proc, exp, comp, target);
                return;
            case PrettyWriter.NEWLINE_SPACE /* 83 */:
                compileArraySet((ArraySet) this.proc, exp, comp, target);
                return;
            default:
                compileArrayLength((ArrayLength) this.proc, exp, comp, target);
                return;
        }
    }

    public static void compileArrayGet(ArrayGet proc, ApplyExp exp, Compilation comp, Target target) {
        Type element_type = proc.element_type;
        Expression[] args = exp.getArgs();
        args[0].compile(comp, ArrayType.make(element_type));
        args[1].compile(comp, Type.int_type);
        CodeAttr code = comp.getCode();
        code.emitArrayLoad(element_type);
        target.compileFromStack(comp, element_type);
    }

    public static void compileArraySet(ArraySet proc, ApplyExp exp, Compilation comp, Target target) {
        Type element_type = proc.element_type;
        Expression[] args = exp.getArgs();
        args[0].compile(comp, ArrayType.make(element_type));
        args[1].compile(comp, Type.int_type);
        args[2].compile(comp, element_type);
        comp.getCode().emitArrayStore(element_type);
        comp.compileConstant(Values.empty, target);
    }

    public static void compileArrayNew(ArrayNew proc, ApplyExp exp, Compilation comp, Target target) {
        Type element_type = proc.element_type;
        exp.getArgs()[0].compile(comp, Type.intType);
        CodeAttr code = comp.getCode();
        code.emitNewArray(element_type.getImplementationType());
        target.compileFromStack(comp, ArrayType.make(element_type));
    }

    public static void compileArrayLength(ArrayLength proc, ApplyExp exp, Compilation comp, Target target) {
        Type element_type = proc.element_type;
        exp.getArgs()[0].compile(comp, ArrayType.make(element_type));
        CodeAttr code = comp.getCode();
        code.emitArrayLength();
        target.compileFromStack(comp, LangPrimType.intType);
    }

    public static Expression validateArrayNew(ApplyExp exp, InlineCalls visitor, Type required, Procedure proc) {
        exp.visitArgs(visitor);
        exp.setType(ArrayType.make(((ArrayNew) proc).element_type));
        return exp;
    }

    public static Expression validateArrayLength(ApplyExp exp, InlineCalls visitor, Type required, Procedure proc) {
        exp.visitArgs(visitor);
        exp.setType(LangPrimType.intType);
        return exp;
    }

    public static Expression validateArrayGet(ApplyExp exp, InlineCalls visitor, Type required, Procedure proc) {
        exp.visitArgs(visitor);
        exp.setType(((ArrayGet) proc).element_type);
        return exp;
    }

    public static Expression validateArraySet(ApplyExp exp, InlineCalls visitor, Type required, Procedure proc) {
        exp.visitArgs(visitor);
        exp.setType(Type.void_type);
        return exp;
    }
}
