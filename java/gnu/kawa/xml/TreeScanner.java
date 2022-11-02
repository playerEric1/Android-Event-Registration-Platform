package gnu.kawa.xml;

import gnu.lists.AbstractSequence;
import gnu.lists.NodePredicate;
import gnu.lists.PositionConsumer;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Procedure;
import gnu.mapping.WrongType;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/* loaded from: classes.dex */
public abstract class TreeScanner extends MethodProc implements Externalizable {
    public NodePredicate type;

    public abstract void scan(AbstractSequence abstractSequence, int i, PositionConsumer positionConsumer);

    /* JADX INFO: Access modifiers changed from: package-private */
    public TreeScanner() {
        setProperty(Procedure.validateApplyKey, "gnu.kawa.xml.CompileXmlFunctions:validateApplyTreeScanner");
    }

    public NodePredicate getNodePredicate() {
        return this.type;
    }

    @Override // gnu.mapping.Procedure
    public int numArgs() {
        return 4097;
    }

    @Override // gnu.mapping.Procedure
    public void apply(CallContext ctx) throws Throwable {
        PositionConsumer out = (PositionConsumer) ctx.consumer;
        Object node = ctx.getNextArg();
        ctx.lastArg();
        try {
            KNode spos = (KNode) node;
            scan(spos.sequence, spos.getPos(), out);
        } catch (ClassCastException e) {
            throw new WrongType(getDesc(), -4, node, "node()");
        }
    }

    @Override // java.io.Externalizable
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(this.type);
    }

    @Override // java.io.Externalizable
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.type = (NodePredicate) in.readObject();
    }

    public String getDesc() {
        String thisName = getClass().getName();
        int dot = thisName.lastIndexOf(46);
        if (dot > 0) {
            thisName = thisName.substring(dot + 1);
        }
        return thisName + "::" + this.type;
    }

    @Override // gnu.mapping.Procedure
    public String toString() {
        return "#<" + getClass().getName() + ' ' + this.type + '>';
    }
}
