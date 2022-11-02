package com.google.appinventor.components.runtime;

import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.runtime.errors.YailRuntimeError;
import com.google.appinventor.components.runtime.util.Dates;
import com.google.appinventor.components.runtime.util.TimerInternal;
import java.util.Calendar;

@DesignerComponent(category = ComponentCategory.SENSORS, description = "Non-visible component that provides the instant in time using the internal clock on the phone. It can fire a timer at regularly set intervals and perform time calculations, manipulations, and conversions. Methods to format the date and time are also available.", iconName = "images/clock.png", nonVisible = true, version = 1)
@SimpleObject
/* loaded from: classes.dex */
public final class Clock extends AndroidNonvisibleComponent implements Component, AlarmHandler, OnStopListener, OnResumeListener, OnDestroyListener, Deleteable {
    private static final boolean DEFAULT_ENABLED = true;
    private static final int DEFAULT_INTERVAL = 1000;
    private boolean onScreen;
    private boolean timerAlwaysFires;
    private TimerInternal timerInternal;

    public Clock(ComponentContainer container) {
        super(container.$form());
        this.timerAlwaysFires = true;
        this.onScreen = false;
        this.timerInternal = new TimerInternal(this, true, DEFAULT_INTERVAL);
        this.form.registerForOnResume(this);
        this.form.registerForOnStop(this);
        this.form.registerForOnDestroy(this);
        if (this.form instanceof ReplForm) {
            this.onScreen = true;
        }
    }

    public Clock() {
        super(null);
        this.timerAlwaysFires = true;
        this.onScreen = false;
    }

    @SimpleEvent(description = "Timer has gone off.")
    public void Timer() {
        if (this.timerAlwaysFires || this.onScreen) {
            EventDispatcher.dispatchEvent(this, "Timer", new Object[0]);
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Interval between timer events in ms")
    public int TimerInterval() {
        return this.timerInternal.Interval();
    }

    @SimpleProperty
    @DesignerProperty(defaultValue = "1000", editorType = PropertyTypeConstants.PROPERTY_TYPE_NON_NEGATIVE_INTEGER)
    public void TimerInterval(int interval) {
        this.timerInternal.Interval(interval);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Fires timer if true")
    public boolean TimerEnabled() {
        return this.timerInternal.Enabled();
    }

    @SimpleProperty
    @DesignerProperty(defaultValue = "True", editorType = PropertyTypeConstants.PROPERTY_TYPE_BOOLEAN)
    public void TimerEnabled(boolean enabled) {
        this.timerInternal.Enabled(enabled);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Will fire even when application is not showing on the screen if true")
    public boolean TimerAlwaysFires() {
        return this.timerAlwaysFires;
    }

    @SimpleProperty
    @DesignerProperty(defaultValue = "True", editorType = PropertyTypeConstants.PROPERTY_TYPE_BOOLEAN)
    public void TimerAlwaysFires(boolean always) {
        this.timerAlwaysFires = always;
    }

    @Override // com.google.appinventor.components.runtime.AlarmHandler
    public void alarm() {
        Timer();
    }

    @SimpleFunction(description = "The phone's internal time")
    public static long SystemTime() {
        return Dates.Timer();
    }

    @SimpleFunction(description = "The current instant in time read from phone's clock")
    public static Calendar Now() {
        return Dates.Now();
    }

    @SimpleFunction(description = "An instant specified by MM/DD/YYYY hh:mm:ss or MM/DD/YYYY or hh:mm")
    public static Calendar MakeInstant(String from) {
        try {
            return Dates.DateValue(from);
        } catch (IllegalArgumentException e) {
            throw new YailRuntimeError("Argument to MakeInstant should have form MM/DD/YYYY, hh:mm:ss, or MM/DD/YYYY or hh:mm", "Sorry to be so picky.");
        }
    }

    @SimpleFunction(description = "An instant in time specified by the milliseconds since 1970.")
    public static Calendar MakeInstantFromMillis(long millis) {
        Calendar instant = Dates.Now();
        instant.setTimeInMillis(millis);
        return instant;
    }

    @SimpleFunction(description = "The instant in time measured as milliseconds since 1970.")
    public static long GetMillis(Calendar instant) {
        return instant.getTimeInMillis();
    }

    @SimpleFunction(description = "An instant in time some seconds after the argument")
    public static Calendar AddSeconds(Calendar instant, int seconds) {
        Calendar newInstant = (Calendar) instant.clone();
        Dates.DateAdd(newInstant, 13, seconds);
        return newInstant;
    }

    @SimpleFunction(description = "An instant in time some minutes after the argument")
    public static Calendar AddMinutes(Calendar instant, int minutes) {
        Calendar newInstant = (Calendar) instant.clone();
        Dates.DateAdd(newInstant, 12, minutes);
        return newInstant;
    }

    @SimpleFunction(description = "An instant in time some hours after the argument")
    public static Calendar AddHours(Calendar instant, int hours) {
        Calendar newInstant = (Calendar) instant.clone();
        Dates.DateAdd(newInstant, 11, hours);
        return newInstant;
    }

    @SimpleFunction(description = "An instant in time some days after the argument")
    public static Calendar AddDays(Calendar instant, int days) {
        Calendar newInstant = (Calendar) instant.clone();
        Dates.DateAdd(newInstant, 5, days);
        return newInstant;
    }

    @SimpleFunction(description = "An instant in time some weeks after the argument")
    public static Calendar AddWeeks(Calendar instant, int weeks) {
        Calendar newInstant = (Calendar) instant.clone();
        Dates.DateAdd(newInstant, 3, weeks);
        return newInstant;
    }

    @SimpleFunction(description = "An instant in time some months after the argument")
    public static Calendar AddMonths(Calendar instant, int months) {
        Calendar newInstant = (Calendar) instant.clone();
        Dates.DateAdd(newInstant, 2, months);
        return newInstant;
    }

    @SimpleFunction(description = "An instant in time some years after the argument")
    public static Calendar AddYears(Calendar instant, int years) {
        Calendar newInstant = (Calendar) instant.clone();
        Dates.DateAdd(newInstant, 1, years);
        return newInstant;
    }

    @SimpleFunction(description = "Milliseconds elapsed between instants")
    public static long Duration(Calendar start, Calendar end) {
        return end.getTimeInMillis() - start.getTimeInMillis();
    }

    @SimpleFunction(description = "The second of the minute")
    public static int Second(Calendar instant) {
        return Dates.Second(instant);
    }

    @SimpleFunction(description = "The minute of the hour")
    public static int Minute(Calendar instant) {
        return Dates.Minute(instant);
    }

    @SimpleFunction(description = "The hour of the day")
    public static int Hour(Calendar instant) {
        return Dates.Hour(instant);
    }

    @SimpleFunction(description = "The day of the month")
    public static int DayOfMonth(Calendar instant) {
        return Dates.Day(instant);
    }

    @SimpleFunction(description = "The day of the week represented as a number from 1 (Sunday) to 7 (Saturday)")
    public static int Weekday(Calendar instant) {
        return Dates.Weekday(instant);
    }

    @SimpleFunction(description = "The name of the day of the week")
    public static String WeekdayName(Calendar instant) {
        return Dates.WeekdayName(instant);
    }

    @SimpleFunction(description = "The month of the year represented as a number from 1 to 12)")
    public static int Month(Calendar instant) {
        return Dates.Month(instant) + 1;
    }

    @SimpleFunction(description = "The name of the month")
    public static String MonthName(Calendar instant) {
        return Dates.MonthName(instant);
    }

    @SimpleFunction(description = "The year")
    public static int Year(Calendar instant) {
        return Dates.Year(instant);
    }

    @SimpleFunction(description = "Text representing the date and time of an instant")
    public static String FormatDateTime(Calendar instant) {
        return Dates.FormatDateTime(instant);
    }

    @SimpleFunction(description = "Text representing the date of an instant")
    public static String FormatDate(Calendar instant) {
        return Dates.FormatDate(instant);
    }

    @SimpleFunction(description = "Text representing the time of an instant")
    public static String FormatTime(Calendar instant) {
        return Dates.FormatTime(instant);
    }

    @Override // com.google.appinventor.components.runtime.OnStopListener
    public void onStop() {
        this.onScreen = false;
    }

    @Override // com.google.appinventor.components.runtime.OnResumeListener
    public void onResume() {
        this.onScreen = true;
    }

    @Override // com.google.appinventor.components.runtime.OnDestroyListener
    public void onDestroy() {
        this.timerInternal.Enabled(false);
    }

    @Override // com.google.appinventor.components.runtime.Deleteable
    public void onDelete() {
        this.timerInternal.Enabled(false);
    }
}
