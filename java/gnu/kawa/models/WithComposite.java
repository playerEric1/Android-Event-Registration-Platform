package gnu.kawa.models;

import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

/* loaded from: classes.dex */
public class WithComposite implements Paintable {
    Composite[] composite;
    Paintable[] paintable;

    public static WithComposite make(Paintable paintable, Composite composite) {
        WithComposite comp = new WithComposite();
        comp.paintable = new Paintable[]{paintable};
        comp.composite = new Composite[]{composite};
        return comp;
    }

    public static WithComposite make(Paintable[] paintable, Composite[] composite) {
        WithComposite comp = new WithComposite();
        comp.paintable = paintable;
        comp.composite = composite;
        return comp;
    }

    public static WithComposite make(Object[] arguments) {
        int n = 0;
        int i = arguments.length;
        while (true) {
            i--;
            if (i < 0) {
                break;
            } else if (arguments[i] instanceof Paintable) {
                n++;
            }
        }
        Paintable[] paintable = new Paintable[n];
        Composite[] composite = new Composite[n];
        Composite comp = null;
        int j = 0;
        for (int i2 = 0; i2 < arguments.length; i2++) {
            Object arg = arguments[i2];
            if (arg instanceof Paintable) {
                paintable[j] = (Paintable) arguments[i2];
                composite[j] = comp;
                j++;
            } else {
                comp = (Composite) arg;
            }
        }
        return make(paintable, composite);
    }

    @Override // gnu.kawa.models.Paintable
    public void paint(Graphics2D graphics) {
        Composite saved = graphics.getComposite();
        Composite prev = saved;
        try {
            int n = this.paintable.length;
            for (int i = 0; i < n; i++) {
                Composite cur = this.composite[i];
                if (cur != null && cur != prev) {
                    graphics.setComposite(cur);
                    prev = cur;
                }
                this.paintable[i].paint(graphics);
            }
        } finally {
            if (prev != saved) {
                graphics.setComposite(saved);
            }
        }
    }

    @Override // gnu.kawa.models.Paintable
    public Rectangle2D getBounds2D() {
        int n = this.paintable.length;
        if (n == 0) {
            return null;
        }
        Rectangle2D bounds = this.paintable[0].getBounds2D();
        for (int i = 1; i < n; i++) {
            bounds = bounds.createUnion(this.paintable[i].getBounds2D());
        }
        return bounds;
    }

    @Override // gnu.kawa.models.Paintable
    public Paintable transform(AffineTransform tr) {
        int n = this.paintable.length;
        Paintable[] transformed = new Paintable[n];
        for (int i = 0; i < n; i++) {
            transformed[i] = this.paintable[i].transform(tr);
        }
        return make(transformed, this.composite);
    }
}
