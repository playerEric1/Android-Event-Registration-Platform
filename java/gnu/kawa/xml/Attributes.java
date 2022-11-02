package gnu.kawa.xml;

import gnu.lists.Consumer;
import gnu.lists.PositionConsumer;
import gnu.lists.SeqPosition;
import gnu.lists.TreeList;
import gnu.lists.TreePosition;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Values;

/* loaded from: classes.dex */
public class Attributes extends MethodProc {
    public static final Attributes attributes = new Attributes();

    @Override // gnu.mapping.Procedure
    public int numArgs() {
        return 4097;
    }

    public static void attributes(TreeList tlist, int index, Consumer consumer) {
        int attr = tlist.gotoAttributesStart(index);
        System.out.print("Attributes called, at:" + attr + " ");
        tlist.dump();
        while (attr >= 0) {
            int ipos = attr << 1;
            int kind = tlist.getNextKind(ipos);
            if (kind == 35) {
                int next = tlist.nextDataIndex(attr);
                if (consumer instanceof PositionConsumer) {
                    ((PositionConsumer) consumer).writePosition(tlist, ipos);
                } else {
                    tlist.consumeIRange(attr, next, consumer);
                }
                attr = next;
            } else {
                return;
            }
        }
    }

    public static void attributes(Object node, Consumer consumer) {
        if (node instanceof TreeList) {
            attributes((TreeList) node, 0, consumer);
        } else if ((node instanceof SeqPosition) && !(node instanceof TreePosition)) {
            SeqPosition pos = (SeqPosition) node;
            if (pos.sequence instanceof TreeList) {
                attributes((TreeList) pos.sequence, pos.ipos >> 1, consumer);
            }
        }
    }

    @Override // gnu.mapping.Procedure
    public void apply(CallContext ctx) {
        Consumer consumer = ctx.consumer;
        Object node = ctx.getNextArg();
        ctx.lastArg();
        if (node instanceof Values) {
            TreeList tlist = (TreeList) node;
            int index = 0;
            while (true) {
                int kind = tlist.getNextKind(index << 1);
                if (kind != 0) {
                    if (kind == 32) {
                        attributes(tlist.getPosNext(index << 1), consumer);
                    } else {
                        attributes(tlist, index, consumer);
                    }
                    index = tlist.nextDataIndex(index);
                } else {
                    return;
                }
            }
        } else {
            attributes(node, consumer);
        }
    }
}
