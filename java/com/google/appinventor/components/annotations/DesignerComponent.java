package com.google.appinventor.components.annotations;

import com.google.appinventor.components.common.ComponentCategory;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes.dex */
public @interface DesignerComponent {
    ComponentCategory category() default ComponentCategory.UNINITIALIZED;

    String description() default "";

    String designerHelpDescription() default "";

    String iconName() default "";

    boolean nonVisible() default false;

    boolean showOnPalette() default true;

    int version();
}
