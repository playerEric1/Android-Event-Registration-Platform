package com.google.appinventor.components.runtime;

/* loaded from: classes.dex */
public interface HandlesEventDispatching {
    boolean canDispatchEvent(Component component, String str);

    boolean dispatchEvent(Component component, String str, String str2, Object[] objArr);
}
