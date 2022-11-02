package gnu.q2.lang;

import gnu.mapping.InPort;
import gnu.mapping.Procedure1;

/* compiled from: Q2.java */
/* loaded from: classes.dex */
class Prompter extends Procedure1 {
    @Override // gnu.mapping.Procedure1, gnu.mapping.Procedure
    public Object apply1(Object arg) {
        InPort port = (InPort) arg;
        int line = port.getLineNumber() + 1;
        char state = port.readState;
        if (state == ']') {
            return "<!--Q2:" + line + "-->";
        }
        if (state == '\n') {
            state = '-';
        }
        return "#|--Q2:" + line + "|#" + state;
    }
}
