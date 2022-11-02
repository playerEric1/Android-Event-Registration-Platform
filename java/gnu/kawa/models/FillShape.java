package gnu.kawa.models;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

/* loaded from: classes.dex */
public class FillShape implements Paintable {
    Shape shape;

    public FillShape(Shape shape) {
        this.shape = shape;
    }

    @Override // gnu.kawa.models.Paintable
    public void paint(Graphics2D graphics) {
        graphics.fill(this.shape);
    }

    @Override // gnu.kawa.models.Paintable
    public Rectangle2D getBounds2D() {
        return this.shape.getBounds2D();
    }

    @Override // gnu.kawa.models.Paintable
    public Paintable transform(AffineTransform tr) {
        return new FillShape(tr.createTransformedShape(this.shape));
    }
}
