package com.google.appinventor.components.runtime.util;

import java.text.DecimalFormat;

/* loaded from: classes.dex */
public final class YailNumberToString {
    private static final double BIGBOUND = 1000000.0d;
    private static final double SMALLBOUND = 1.0E-6d;
    private static final String decPattern = "#####0.0####";
    private static final DecimalFormat formatterDec = new DecimalFormat(decPattern);
    private static final String sciPattern = "0.####E0";
    private static final DecimalFormat formatterSci = new DecimalFormat(sciPattern);

    public static String format(double number) {
        double mag = Math.abs(number);
        return (mag >= BIGBOUND || mag <= SMALLBOUND) ? formatterSci.format(number) : formatterDec.format(number);
    }
}
