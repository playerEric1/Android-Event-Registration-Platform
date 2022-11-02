package gnu.q2.lang;

import gnu.bytecode.Type;
import gnu.expr.Special;
import gnu.kawa.reflect.Invoke;
import gnu.lists.Consumable;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Procedure;
import gnu.mapping.Values;
import java.util.Vector;

/* loaded from: classes.dex */
public class Q2Apply extends MethodProc {
    public static Q2Apply q2Apply = new Q2Apply();

    @Override // gnu.mapping.Procedure
    public void apply(CallContext ctx) throws Throwable {
        Procedure proc;
        Special endMarker = Special.dfault;
        Object arg = ctx.getNextArg(endMarker);
        if ((arg instanceof Procedure) || (arg instanceof Type) || (arg instanceof Class)) {
            Vector vec = new Vector();
            if (arg instanceof Procedure) {
                proc = (Procedure) arg;
            } else {
                vec.add(arg);
                proc = Invoke.make;
            }
            while (true) {
                Object arg2 = ctx.getNextArg(endMarker);
                if (arg2 == endMarker) {
                    break;
                } else if (arg2 instanceof Values) {
                    Object[] vals = ((Values) arg2).getValues();
                    for (Object obj : vals) {
                        vec.add(obj);
                    }
                } else {
                    vec.add(arg2);
                }
            }
            Object arg3 = proc.applyN(vec.toArray());
            if (arg3 instanceof Consumable) {
                ((Consumable) arg3).consume(ctx.consumer);
                return;
            } else {
                ctx.writeValue(arg3);
                return;
            }
        }
        while (arg != endMarker) {
            if (arg instanceof Consumable) {
                ((Consumable) arg).consume(ctx.consumer);
            } else {
                ctx.writeValue(arg);
            }
            arg = ctx.getNextArg(endMarker);
        }
    }
}
