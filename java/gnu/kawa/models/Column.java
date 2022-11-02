package gnu.kawa.models;

import java.io.Serializable;

/* loaded from: classes.dex */
public class Column extends Box implements Viewable, Serializable {
    @Override // gnu.kawa.models.Box
    public int getAxis() {
        return 1;
    }
}
