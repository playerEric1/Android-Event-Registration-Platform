package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.runtime.util.BoundingBox;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.FileUtil;
import com.google.appinventor.components.runtime.util.MediaUtil;
import com.google.appinventor.components.runtime.util.PaintUtil;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@UsesPermissions(permissionNames = "android.permission.INTERNET,android.permission.WRITE_EXTERNAL_STORAGE")
@DesignerComponent(category = ComponentCategory.ANIMATION, description = "<p>A two-dimensional touch-sensitive rectangular panel on which drawing can be done and sprites can be moved.</p> <p>The <code>BackgroundColor</code>, <code>PaintColor</code>, <code>BackgroundImage</code>, <code>Width</code>, and <code>Height</code> of the Canvas can be set in either the Designer or in the Blocks Editor.  The <code>Width</code> and <code>Height</code> are measured in pixels and must be positive.</p><p>Any location on the Canvas can be specified as a pair of (X, Y) values, where <ul> <li>X is the number of pixels away from the left edge of the Canvas</li><li>Y is the number of pixels away from the top edge of the Canvas</li></ul>.</p> <p>There are events to tell when and where a Canvas has been touched or a <code>Sprite</code> (<code>ImageSprite</code> or <code>Ball</code>) has been dragged.  There are also methods for drawing points, lines, and circles.</p>", version = 7)
@SimpleObject
/* loaded from: classes.dex */
public final class Canvas extends AndroidViewComponent implements ComponentContainer {
    private static final int DEFAULT_BACKGROUND_COLOR = -1;
    private static final float DEFAULT_LINE_WIDTH = 2.0f;
    private static final int DEFAULT_PAINT_COLOR = -16777216;
    private static final int FLING_INTERVAL = 1000;
    private static final String LOG_TAG = "Canvas";
    private static final int MIN_WIDTH_HEIGHT = 1;
    private int backgroundColor;
    private String backgroundImagePath;
    private final Activity context;
    private boolean drawn;
    private final GestureDetector mGestureDetector;
    private final MotionEventParser motionEventParser;
    private final Paint paint;
    private int paintColor;
    private final List<Sprite> sprites;
    private int textAlignment;
    private final CanvasView view;

    /* loaded from: classes.dex */
    class MotionEventParser {
        public static final int FINGER_HEIGHT = 24;
        public static final int FINGER_WIDTH = 24;
        private static final int HALF_FINGER_HEIGHT = 12;
        private static final int HALF_FINGER_WIDTH = 12;
        public static final int TAP_THRESHOLD = 30;
        private static final int UNSET = -1;
        private final List<Sprite> draggedSprites = new ArrayList();
        private float startX = -1.0f;
        private float startY = -1.0f;
        private float lastX = -1.0f;
        private float lastY = -1.0f;
        private boolean isDrag = false;
        private boolean drag = false;

        MotionEventParser() {
        }

        void parse(MotionEvent event) {
            int width = Canvas.this.Width();
            int height = Canvas.this.Height();
            float x = Math.max(0, (int) event.getX());
            float y = Math.max(0, (int) event.getY());
            BoundingBox rect = new BoundingBox(Math.max(0, ((int) x) - 12), Math.max(0, ((int) y) - 12), Math.min(width - 1, ((int) x) + 12), Math.min(height - 1, ((int) y) + 12));
            switch (event.getAction()) {
                case 0:
                    this.draggedSprites.clear();
                    this.startX = x;
                    this.startY = y;
                    this.lastX = x;
                    this.lastY = y;
                    this.drag = false;
                    this.isDrag = false;
                    for (Sprite sprite : Canvas.this.sprites) {
                        if (sprite.Enabled() && sprite.Visible() && sprite.intersectsWith(rect)) {
                            this.draggedSprites.add(sprite);
                            sprite.TouchDown(this.startX, this.startY);
                        }
                    }
                    Canvas.this.TouchDown(this.startX, this.startY);
                    return;
                case 1:
                    if (!this.drag) {
                        boolean handled = false;
                        for (Sprite sprite2 : this.draggedSprites) {
                            if (sprite2.Enabled() && sprite2.Visible()) {
                                sprite2.Touched(x, y);
                                sprite2.TouchUp(x, y);
                                handled = true;
                            }
                        }
                        Canvas.this.Touched(x, y, handled);
                    } else {
                        for (Sprite sprite3 : this.draggedSprites) {
                            if (sprite3.Enabled() && sprite3.Visible()) {
                                sprite3.Touched(x, y);
                                sprite3.TouchUp(x, y);
                            }
                        }
                    }
                    Canvas.this.TouchUp(x, y);
                    this.drag = false;
                    this.startX = -1.0f;
                    this.startY = -1.0f;
                    this.lastX = -1.0f;
                    this.lastY = -1.0f;
                    return;
                case 2:
                    if (this.startX == -1.0f || this.startY == -1.0f || this.lastX == -1.0f || this.lastY == -1.0f) {
                        Log.w(Canvas.LOG_TAG, "In Canvas.MotionEventParser.parse(), an ACTION_MOVE was passed without a preceding ACTION_DOWN: " + event);
                    }
                    if (this.isDrag || Math.abs(x - this.startX) >= 30.0f || Math.abs(y - this.startY) >= 30.0f) {
                        this.isDrag = true;
                        this.drag = true;
                        for (Sprite sprite4 : Canvas.this.sprites) {
                            if (!this.draggedSprites.contains(sprite4) && sprite4.Enabled() && sprite4.Visible() && sprite4.intersectsWith(rect)) {
                                this.draggedSprites.add(sprite4);
                            }
                        }
                        boolean handled2 = false;
                        for (Sprite sprite5 : this.draggedSprites) {
                            if (sprite5.Enabled() && sprite5.Visible()) {
                                sprite5.Dragged(this.startX, this.startY, this.lastX, this.lastY, x, y);
                                handled2 = true;
                            }
                        }
                        Canvas.this.Dragged(this.startX, this.startY, this.lastX, this.lastY, x, y, handled2);
                        this.lastX = x;
                        this.lastY = y;
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class CanvasView extends View {
        private BitmapDrawable backgroundDrawable;
        private Bitmap bitmap;
        private android.graphics.Canvas canvas;
        private Bitmap completeCache;
        private Bitmap scaledBackgroundBitmap;

        public CanvasView(Context context) {
            super(context);
            this.bitmap = Bitmap.createBitmap(32, 48, Bitmap.Config.ARGB_8888);
            this.canvas = new android.graphics.Canvas(this.bitmap);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Bitmap buildCache() {
            setDrawingCacheEnabled(true);
            destroyDrawingCache();
            Bitmap cache = getDrawingCache();
            if (cache == null) {
                int width = getWidth();
                int height = getHeight();
                Bitmap cache2 = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
                android.graphics.Canvas c = new android.graphics.Canvas(cache2);
                layout(0, 0, width, height);
                draw(c);
                return cache2;
            }
            return cache;
        }

        @Override // android.view.View
        public void onDraw(android.graphics.Canvas canvas0) {
            this.completeCache = null;
            super.onDraw(canvas0);
            canvas0.drawBitmap(this.bitmap, 0.0f, 0.0f, (Paint) null);
            for (Sprite sprite : Canvas.this.sprites) {
                sprite.onDraw(canvas0);
            }
            Canvas.this.drawn = true;
        }

        @Override // android.view.View
        protected void onSizeChanged(int w, int h, int oldW, int oldH) {
            int oldBitmapWidth = this.bitmap.getWidth();
            int oldBitmapHeight = this.bitmap.getHeight();
            if (w != oldBitmapWidth || h != oldBitmapHeight) {
                Bitmap oldBitmap = this.bitmap;
                try {
                    Bitmap scaledBitmap = Bitmap.createScaledBitmap(oldBitmap, w, h, false);
                    if (scaledBitmap.isMutable()) {
                        this.bitmap = scaledBitmap;
                        this.canvas = new android.graphics.Canvas(this.bitmap);
                    } else {
                        this.bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
                        this.canvas = new android.graphics.Canvas(this.bitmap);
                        Rect src = new Rect(0, 0, oldBitmapWidth, oldBitmapHeight);
                        RectF dst = new RectF(0.0f, 0.0f, w, h);
                        this.canvas.drawBitmap(oldBitmap, src, dst, (Paint) null);
                    }
                } catch (IllegalArgumentException e) {
                    Log.e(Canvas.LOG_TAG, "Bad values to createScaledBimap w = " + w + ", h = " + h);
                }
                this.scaledBackgroundBitmap = null;
            }
        }

        @Override // android.view.View
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            int preferredWidth;
            int preferredHeight;
            if (this.backgroundDrawable != null) {
                Bitmap bitmap = this.backgroundDrawable.getBitmap();
                preferredWidth = bitmap.getWidth();
                preferredHeight = bitmap.getHeight();
            } else {
                preferredWidth = 32;
                preferredHeight = 48;
            }
            setMeasuredDimension(getSize(widthMeasureSpec, preferredWidth), getSize(heightMeasureSpec, preferredHeight));
        }

        private int getSize(int measureSpec, int preferredSize) {
            int specMode = View.MeasureSpec.getMode(measureSpec);
            int specSize = View.MeasureSpec.getSize(measureSpec);
            if (specMode == 1073741824) {
                return specSize;
            }
            if (specMode != Integer.MIN_VALUE) {
                return preferredSize;
            }
            int result = Math.min(preferredSize, specSize);
            return result;
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent event) {
            Canvas.this.container.$form().dontGrabTouchEventsForComponent();
            Canvas.this.motionEventParser.parse(event);
            Canvas.this.mGestureDetector.onTouchEvent(event);
            return true;
        }

        void setBackgroundImage(String path) {
            Canvas canvas = Canvas.this;
            if (path == null) {
                path = "";
            }
            canvas.backgroundImagePath = path;
            this.backgroundDrawable = null;
            this.scaledBackgroundBitmap = null;
            if (!TextUtils.isEmpty(Canvas.this.backgroundImagePath)) {
                try {
                    this.backgroundDrawable = MediaUtil.getBitmapDrawable(Canvas.this.container.$form(), Canvas.this.backgroundImagePath);
                } catch (IOException e) {
                    Log.e(Canvas.LOG_TAG, "Unable to load " + Canvas.this.backgroundImagePath);
                }
            }
            setBackgroundDrawable(this.backgroundDrawable);
            if (this.backgroundDrawable == null) {
                super.setBackgroundColor(Canvas.this.backgroundColor);
            }
            clearDrawingLayer();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDrawingLayer() {
            this.canvas.drawColor(0, PorterDuff.Mode.CLEAR);
            invalidate();
        }

        @Override // android.view.View
        public void setBackgroundColor(int color) {
            Canvas.this.backgroundColor = color;
            if (this.backgroundDrawable == null) {
                super.setBackgroundColor(color);
            }
            clearDrawingLayer();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void drawTextAtAngle(String text, int x, int y, float angle) {
            this.canvas.save();
            this.canvas.rotate(-angle, x, y);
            this.canvas.drawText(text, x, y, Canvas.this.paint);
            this.canvas.restore();
            invalidate();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int getBackgroundPixelColor(int x, int y) {
            if (x < 0 || x >= this.bitmap.getWidth() || y < 0 || y >= this.bitmap.getHeight()) {
                return Component.COLOR_NONE;
            }
            try {
                int color = this.bitmap.getPixel(x, y);
                if (color == 0) {
                    if (this.backgroundDrawable == null) {
                        return Color.alpha(Canvas.this.backgroundColor) != 0 ? Canvas.this.backgroundColor : Component.COLOR_NONE;
                    }
                    if (this.scaledBackgroundBitmap == null) {
                        this.scaledBackgroundBitmap = Bitmap.createScaledBitmap(this.backgroundDrawable.getBitmap(), this.bitmap.getWidth(), this.bitmap.getHeight(), false);
                    }
                    return this.scaledBackgroundBitmap.getPixel(x, y);
                }
                return color;
            } catch (IllegalArgumentException e) {
                Log.e(Canvas.LOG_TAG, String.format("Returning COLOR_NONE (exception) from getBackgroundPixelColor.", new Object[0]));
                return Component.COLOR_NONE;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int getPixelColor(int x, int y) {
            if (x < 0 || x >= this.bitmap.getWidth() || y < 0 || y >= this.bitmap.getHeight()) {
                return Component.COLOR_NONE;
            }
            if (this.completeCache == null) {
                boolean anySpritesVisible = false;
                Iterator i$ = Canvas.this.sprites.iterator();
                while (true) {
                    if (!i$.hasNext()) {
                        break;
                    }
                    Sprite sprite = (Sprite) i$.next();
                    if (sprite.Visible()) {
                        anySpritesVisible = true;
                        break;
                    }
                }
                if (!anySpritesVisible) {
                    return getBackgroundPixelColor(x, y);
                }
                this.completeCache = buildCache();
            }
            try {
                return this.completeCache.getPixel(x, y);
            } catch (IllegalArgumentException e) {
                Log.e(Canvas.LOG_TAG, String.format("Returning COLOR_NONE (exception) from getPixelColor.", new Object[0]));
                return Component.COLOR_NONE;
            }
        }
    }

    public Canvas(ComponentContainer container) {
        super(container);
        this.backgroundImagePath = "";
        this.context = container.$context();
        this.view = new CanvasView(this.context);
        container.$add(this);
        this.paint = new Paint();
        this.paint.setStrokeWidth(DEFAULT_LINE_WIDTH);
        PaintColor(-16777216);
        BackgroundColor(-1);
        TextAlignment(0);
        FontSize(14.0f);
        this.sprites = new LinkedList();
        this.motionEventParser = new MotionEventParser();
        this.mGestureDetector = new GestureDetector(this.context, new FlingGestureListener());
    }

    @Override // com.google.appinventor.components.runtime.AndroidViewComponent
    public View getView() {
        return this.view;
    }

    public boolean ready() {
        return this.drawn;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addSprite(Sprite sprite) {
        for (int i = 0; i < this.sprites.size(); i++) {
            if (this.sprites.get(i).Z() > sprite.Z()) {
                this.sprites.add(i, sprite);
                return;
            }
        }
        this.sprites.add(sprite);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void removeSprite(Sprite sprite) {
        this.sprites.remove(sprite);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void changeSpriteLayer(Sprite sprite) {
        removeSprite(sprite);
        addSprite(sprite);
        this.view.invalidate();
    }

    @Override // com.google.appinventor.components.runtime.ComponentContainer
    public Activity $context() {
        return this.context;
    }

    @Override // com.google.appinventor.components.runtime.ComponentContainer
    public Form $form() {
        return this.container.$form();
    }

    @Override // com.google.appinventor.components.runtime.ComponentContainer
    public void $add(AndroidViewComponent component) {
        throw new UnsupportedOperationException("Canvas.$add() called");
    }

    @Override // com.google.appinventor.components.runtime.ComponentContainer
    public void setChildWidth(AndroidViewComponent component, int width) {
        throw new UnsupportedOperationException("Canvas.setChildWidth() called");
    }

    @Override // com.google.appinventor.components.runtime.ComponentContainer
    public void setChildHeight(AndroidViewComponent component, int height) {
        throw new UnsupportedOperationException("Canvas.setChildHeight() called");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void registerChange(Sprite sprite) {
        this.view.invalidate();
        findSpriteCollisions(sprite);
    }

    protected void findSpriteCollisions(Sprite movedSprite) {
        for (Sprite sprite : this.sprites) {
            if (sprite != movedSprite) {
                if (movedSprite.CollidingWith(sprite)) {
                    if (!movedSprite.Visible() || !movedSprite.Enabled() || !sprite.Visible() || !sprite.Enabled() || !Sprite.colliding(sprite, movedSprite)) {
                        movedSprite.NoLongerCollidingWith(sprite);
                        sprite.NoLongerCollidingWith(movedSprite);
                    }
                } else if (movedSprite.Visible() && movedSprite.Enabled() && sprite.Visible() && sprite.Enabled() && Sprite.colliding(sprite, movedSprite)) {
                    movedSprite.CollidedWith(sprite);
                    sprite.CollidedWith(movedSprite);
                }
            }
        }
    }

    @Override // com.google.appinventor.components.runtime.AndroidViewComponent, com.google.appinventor.components.runtime.VisibleComponent
    @SimpleProperty
    public void Width(int width) {
        if (width > 0 || width == -2 || width == -1) {
            super.Width(width);
        } else {
            this.container.$form().dispatchErrorOccurredEvent(this, "Width", ErrorMessages.ERROR_CANVAS_WIDTH_ERROR, new Object[0]);
        }
    }

    @Override // com.google.appinventor.components.runtime.AndroidViewComponent, com.google.appinventor.components.runtime.VisibleComponent
    @SimpleProperty
    public void Height(int height) {
        if (height > 0 || height == -2 || height == -1) {
            super.Height(height);
        } else {
            this.container.$form().dispatchErrorOccurredEvent(this, "Height", ErrorMessages.ERROR_CANVAS_HEIGHT_ERROR, new Object[0]);
        }
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The color of the canvas background.")
    public int BackgroundColor() {
        return this.backgroundColor;
    }

    @SimpleProperty
    @DesignerProperty(defaultValue = Component.DEFAULT_VALUE_COLOR_WHITE, editorType = PropertyTypeConstants.PROPERTY_TYPE_COLOR)
    public void BackgroundColor(int argb) {
        this.view.setBackgroundColor(argb);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The name of a file containing the background image for the canvas")
    public String BackgroundImage() {
        return this.backgroundImagePath;
    }

    @SimpleProperty
    @DesignerProperty(defaultValue = "", editorType = PropertyTypeConstants.PROPERTY_TYPE_ASSET)
    public void BackgroundImage(String path) {
        this.view.setBackgroundImage(path);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The color in which lines are drawn")
    public int PaintColor() {
        return this.paintColor;
    }

    @SimpleProperty
    @DesignerProperty(defaultValue = Component.DEFAULT_VALUE_COLOR_BLACK, editorType = PropertyTypeConstants.PROPERTY_TYPE_COLOR)
    public void PaintColor(int argb) {
        this.paintColor = argb;
        changePaint(this.paint, argb);
    }

    private void changePaint(Paint paint, int argb) {
        if (argb == 0) {
            PaintUtil.changePaint(paint, -16777216);
        } else if (argb == 16777215) {
            PaintUtil.changePaintTransparent(paint);
        } else {
            PaintUtil.changePaint(paint, argb);
        }
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The font size of text drawn on the canvas.")
    public float FontSize() {
        return this.paint.getTextSize();
    }

    @SimpleProperty
    @DesignerProperty(defaultValue = "14.0", editorType = PropertyTypeConstants.PROPERTY_TYPE_NON_NEGATIVE_FLOAT)
    public void FontSize(float size) {
        this.paint.setTextSize(size);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The width of lines drawn on the canvas.")
    public float LineWidth() {
        return this.paint.getStrokeWidth();
    }

    @SimpleProperty
    @DesignerProperty(defaultValue = "2.0", editorType = PropertyTypeConstants.PROPERTY_TYPE_NON_NEGATIVE_FLOAT)
    public void LineWidth(float width) {
        this.paint.setStrokeWidth(width);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, userVisible = false)
    public int TextAlignment() {
        return this.textAlignment;
    }

    @SimpleProperty(userVisible = false)
    @DesignerProperty(defaultValue = "1", editorType = PropertyTypeConstants.PROPERTY_TYPE_TEXTALIGNMENT)
    public void TextAlignment(int alignment) {
        this.textAlignment = alignment;
        switch (alignment) {
            case 0:
                this.paint.setTextAlign(Paint.Align.LEFT);
                return;
            case 1:
                this.paint.setTextAlign(Paint.Align.CENTER);
                return;
            case 2:
                this.paint.setTextAlign(Paint.Align.RIGHT);
                return;
            default:
                return;
        }
    }

    @SimpleEvent
    public void Touched(float x, float y, boolean touchedSprite) {
        EventDispatcher.dispatchEvent(this, "Touched", Float.valueOf(x), Float.valueOf(y), Boolean.valueOf(touchedSprite));
    }

    @SimpleEvent
    public void TouchDown(float x, float y) {
        EventDispatcher.dispatchEvent(this, "TouchDown", Float.valueOf(x), Float.valueOf(y));
    }

    @SimpleEvent
    public void TouchUp(float x, float y) {
        EventDispatcher.dispatchEvent(this, "TouchUp", Float.valueOf(x), Float.valueOf(y));
    }

    @SimpleEvent
    public void Flung(float x, float y, float speed, float heading, float xvel, float yvel, boolean flungSprite) {
        EventDispatcher.dispatchEvent(this, "Flung", Float.valueOf(x), Float.valueOf(y), Float.valueOf(speed), Float.valueOf(heading), Float.valueOf(xvel), Float.valueOf(yvel), Boolean.valueOf(flungSprite));
    }

    @SimpleEvent
    public void Dragged(float startX, float startY, float prevX, float prevY, float currentX, float currentY, boolean draggedSprite) {
        EventDispatcher.dispatchEvent(this, "Dragged", Float.valueOf(startX), Float.valueOf(startY), Float.valueOf(prevX), Float.valueOf(prevY), Float.valueOf(currentX), Float.valueOf(currentY), Boolean.valueOf(draggedSprite));
    }

    @SimpleFunction(description = "Clears anything drawn on this Canvas but not any background color or image.")
    public void Clear() {
        this.view.clearDrawingLayer();
    }

    @SimpleFunction
    public void DrawPoint(int x, int y) {
        this.view.canvas.drawPoint(x, y, this.paint);
        this.view.invalidate();
    }

    @SimpleFunction
    public void DrawCircle(int x, int y, float r) {
        this.view.canvas.drawCircle(x, y, r, this.paint);
        this.view.invalidate();
    }

    @SimpleFunction
    public void DrawLine(int x1, int y1, int x2, int y2) {
        this.view.canvas.drawLine(x1, y1, x2, y2, this.paint);
        this.view.invalidate();
    }

    @SimpleFunction(description = "Draws the specified text relative to the specified coordinates using the values of the FontSize and TextAlignment properties.")
    public void DrawText(String text, int x, int y) {
        this.view.canvas.drawText(text, x, y, this.paint);
        this.view.invalidate();
    }

    @SimpleFunction(description = "Draws the specified text starting at the specified coordinates at the specified angle using the values of the FontSize and TextAlignment properties.")
    public void DrawTextAtAngle(String text, int x, int y, float angle) {
        this.view.drawTextAtAngle(text, x, y, angle);
    }

    @SimpleFunction(description = "Gets the color of the specified point. This includes the background and any drawn points, lines, or circles but not sprites.")
    public int GetBackgroundPixelColor(int x, int y) {
        return this.view.getBackgroundPixelColor(x, y);
    }

    @SimpleFunction(description = "Sets the color of the specified point. This differs from DrawPoint by having an argument for color.")
    public void SetBackgroundPixelColor(int x, int y, int color) {
        Paint pixelPaint = new Paint();
        PaintUtil.changePaint(pixelPaint, color);
        this.view.canvas.drawPoint(x, y, pixelPaint);
        this.view.invalidate();
    }

    @SimpleFunction(description = "Gets the color of the specified point.")
    public int GetPixelColor(int x, int y) {
        return this.view.getPixelColor(x, y);
    }

    @SimpleFunction(description = "Saves a picture of this Canvas to the device's external storage. If an error occurs, the Screen's ErrorOccurred event will be called.")
    public String Save() {
        try {
            java.io.File file = FileUtil.getPictureFile("png");
            return saveFile(file, Bitmap.CompressFormat.PNG, "Save");
        } catch (FileUtil.FileException e) {
            this.container.$form().dispatchErrorOccurredEvent(this, "Save", e.getErrorMessageNumber(), new Object[0]);
            return "";
        } catch (IOException e2) {
            this.container.$form().dispatchErrorOccurredEvent(this, "Save", ErrorMessages.ERROR_MEDIA_FILE_ERROR, e2.getMessage());
            return "";
        }
    }

    @SimpleFunction(description = "Saves a picture of this Canvas to the device's external storage in the file named fileName. fileName must end with one of .jpg, .jpeg, or .png, which determines the file type.")
    public String SaveAs(String fileName) {
        Bitmap.CompressFormat format;
        if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")) {
            format = Bitmap.CompressFormat.JPEG;
        } else if (fileName.endsWith(".png")) {
            format = Bitmap.CompressFormat.PNG;
        } else if (!fileName.contains(".")) {
            fileName = fileName + ".png";
            format = Bitmap.CompressFormat.PNG;
        } else {
            this.container.$form().dispatchErrorOccurredEvent(this, "SaveAs", ErrorMessages.ERROR_MEDIA_IMAGE_FILE_FORMAT, new Object[0]);
            return "";
        }
        try {
            java.io.File file = FileUtil.getExternalFile(fileName);
            return saveFile(file, format, "SaveAs");
        } catch (FileUtil.FileException e) {
            this.container.$form().dispatchErrorOccurredEvent(this, "SaveAs", e.getErrorMessageNumber(), new Object[0]);
            return "";
        } catch (IOException e2) {
            this.container.$form().dispatchErrorOccurredEvent(this, "SaveAs", ErrorMessages.ERROR_MEDIA_FILE_ERROR, e2.getMessage());
            return "";
        }
    }

    private String saveFile(java.io.File file, Bitmap.CompressFormat format, String method) {
        FileOutputStream fos;
        Bitmap bitmap;
        try {
            fos = new FileOutputStream(file);
            bitmap = this.view.completeCache == null ? this.view.buildCache() : this.view.completeCache;
        } catch (FileNotFoundException e) {
            this.container.$form().dispatchErrorOccurredEvent(this, method, ErrorMessages.ERROR_MEDIA_CANNOT_OPEN, file.getAbsolutePath());
        } catch (IOException e2) {
            this.container.$form().dispatchErrorOccurredEvent(this, method, ErrorMessages.ERROR_MEDIA_FILE_ERROR, e2.getMessage());
        }
        try {
            boolean success = bitmap.compress(format, 100, fos);
            if (success) {
                return file.getAbsolutePath();
            }
            this.container.$form().dispatchErrorOccurredEvent(this, method, ErrorMessages.ERROR_CANVAS_BITMAP_ERROR, new Object[0]);
            return "";
        } finally {
            fos.close();
        }
    }

    /* loaded from: classes.dex */
    class FlingGestureListener extends GestureDetector.SimpleOnGestureListener {
        FlingGestureListener() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float x = Math.max(0, (int) e1.getX());
            float y = Math.max(0, (int) e1.getY());
            float vx = velocityX / 1000.0f;
            float vy = velocityY / 1000.0f;
            float speed = (float) Math.sqrt((vx * vx) + (vy * vy));
            float heading = (float) (-Math.toDegrees(Math.atan2(vy, vx)));
            int width = Canvas.this.Width();
            int height = Canvas.this.Height();
            BoundingBox rect = new BoundingBox(Math.max(0, ((int) x) - 12), Math.max(0, ((int) y) - 12), Math.min(width - 1, ((int) x) + 12), Math.min(height - 1, ((int) y) + 12));
            boolean spriteHandledFling = false;
            for (Sprite sprite : Canvas.this.sprites) {
                if (sprite.Enabled() && sprite.Visible() && sprite.intersectsWith(rect)) {
                    sprite.Flung(x, y, speed, heading, vx, vy);
                    spriteHandledFling = true;
                }
            }
            Canvas.this.Flung(x, y, speed, heading, vx, vy, spriteHandledFling);
            return true;
        }
    }
}
