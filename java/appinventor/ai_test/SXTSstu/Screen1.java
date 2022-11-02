package appinventor.ai_test.SXTSstu;

import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.runtime.Button;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.HandlesEventDispatching;
import com.google.appinventor.components.runtime.HorizontalArrangement;
import com.google.appinventor.components.runtime.Label;
import com.google.appinventor.components.runtime.PasswordTextBox;
import com.google.appinventor.components.runtime.ReplApplication;
import com.google.appinventor.components.runtime.TextBox;
import com.google.appinventor.components.runtime.TinyDB;
import com.google.appinventor.components.runtime.TinyWebDB;
import com.google.appinventor.components.runtime.errors.YailRuntimeError;
import com.google.appinventor.components.runtime.util.RetValManager;
import com.google.appinventor.components.runtime.util.RuntimeErrorAlert;
import com.google.youngandroid.runtime;
import gnu.expr.Language;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.Apply;
import gnu.kawa.functions.Format;
import gnu.kawa.functions.GetNamedPart;
import gnu.kawa.reflect.Invoke;
import gnu.kawa.reflect.SlotGet;
import gnu.kawa.reflect.SlotSet;
import gnu.lists.Consumer;
import gnu.lists.FString;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.lists.VoidConsumer;
import gnu.mapping.CallContext;
import gnu.mapping.Environment;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import kawa.lang.Promise;
import kawa.lib.lists;
import kawa.lib.misc;
import kawa.lib.strings;
import kawa.standard.Scheme;
import kawa.standard.require;

/* compiled from: Screen1.yail */
/* loaded from: classes.dex */
public class Screen1 extends Form implements Runnable {
    static final SimpleSymbol Lit0;
    static final SimpleSymbol Lit1;
    static final SimpleSymbol Lit10;
    static final SimpleSymbol Lit11;
    static final PairWithPosition Lit12;
    static final IntNum Lit13;
    static final PairWithPosition Lit14;
    static final SimpleSymbol Lit15;
    static final SimpleSymbol Lit16;
    static final SimpleSymbol Lit17;
    static final SimpleSymbol Lit18;
    static final SimpleSymbol Lit19;
    static final SimpleSymbol Lit2;
    static final FString Lit20;
    static final SimpleSymbol Lit21;
    static final FString Lit22;
    static final FString Lit23;
    static final SimpleSymbol Lit24;
    static final SimpleSymbol Lit25;
    static final FString Lit26;
    static final FString Lit27;
    static final SimpleSymbol Lit28;
    static final FString Lit29;
    static final SimpleSymbol Lit3;
    static final FString Lit30;
    static final SimpleSymbol Lit31;
    static final FString Lit32;
    static final FString Lit33;
    static final SimpleSymbol Lit34;
    static final FString Lit35;
    static final FString Lit36;
    static final SimpleSymbol Lit37;
    static final FString Lit38;
    static final FString Lit39;
    static final IntNum Lit4;
    static final SimpleSymbol Lit40;
    static final SimpleSymbol Lit41;
    static final IntNum Lit42;
    static final SimpleSymbol Lit43;
    static final SimpleSymbol Lit44;
    static final IntNum Lit45;
    static final FString Lit46;
    static final SimpleSymbol Lit47;
    static final PairWithPosition Lit48;
    static final SimpleSymbol Lit49;
    static final SimpleSymbol Lit5;
    static final SimpleSymbol Lit50;
    static final FString Lit51;
    static final IntNum Lit52;
    static final SimpleSymbol Lit53;
    static final IntNum Lit54;
    static final FString Lit55;
    static final SimpleSymbol Lit56;
    static final PairWithPosition Lit57;
    static final PairWithPosition Lit58;
    static final SimpleSymbol Lit59;
    static final SimpleSymbol Lit6;
    static final FString Lit60;
    static final SimpleSymbol Lit61;
    static final FString Lit62;
    static final PairWithPosition Lit63;
    static final PairWithPosition Lit64;
    static final SimpleSymbol Lit7;
    static final SimpleSymbol Lit8;
    static final SimpleSymbol Lit9;
    public static Screen1 Screen1;
    static final ModuleMethod lambda$Fn1 = null;
    static final ModuleMethod lambda$Fn10 = null;
    static final ModuleMethod lambda$Fn11 = null;
    static final ModuleMethod lambda$Fn12 = null;
    static final ModuleMethod lambda$Fn2 = null;
    static final ModuleMethod lambda$Fn3 = null;
    static final ModuleMethod lambda$Fn4 = null;
    static final ModuleMethod lambda$Fn5 = null;
    static final ModuleMethod lambda$Fn6 = null;
    static final ModuleMethod lambda$Fn7 = null;
    static final ModuleMethod lambda$Fn8 = null;
    static final ModuleMethod lambda$Fn9 = null;
    public Boolean $Stdebug$Mnform$St;
    public final ModuleMethod $define;
    public Button Button1;
    public final ModuleMethod Button1$Click;
    public Button Button2;
    public final ModuleMethod Button2$Click;
    public HorizontalArrangement HorizontalArrangement2;
    public HorizontalArrangement HorizontalArrangement3;
    public Label Label1;
    public Label Label2;
    public PasswordTextBox PasswordTextBox1;
    public final ModuleMethod Screen1$Initialize;
    public TextBox TextBox1;
    public TinyDB TinyDB1;
    public TinyWebDB TinyWebDB1;
    public final ModuleMethod TinyWebDB1$GotValue;
    public final ModuleMethod add$Mnto$Mncomponents;
    public final ModuleMethod add$Mnto$Mnevents;
    public final ModuleMethod add$Mnto$Mnform$Mndo$Mnafter$Mncreation;
    public final ModuleMethod add$Mnto$Mnform$Mnenvironment;
    public final ModuleMethod add$Mnto$Mnglobal$Mnvar$Mnenvironment;
    public final ModuleMethod add$Mnto$Mnglobal$Mnvars;
    public final ModuleMethod android$Mnlog$Mnform;
    public LList components$Mnto$Mncreate;
    public final ModuleMethod dispatchEvent;
    public LList events$Mnto$Mnregister;
    public LList form$Mndo$Mnafter$Mncreation;
    public Environment form$Mnenvironment;
    public Symbol form$Mnname$Mnsymbol;
    public Environment global$Mnvar$Mnenvironment;
    public LList global$Mnvars$Mnto$Mncreate;
    public final ModuleMethod is$Mnbound$Mnin$Mnform$Mnenvironment;
    public final ModuleMethod lookup$Mnhandler;
    public final ModuleMethod lookup$Mnin$Mnform$Mnenvironment;
    public final ModuleMethod process$Mnexception;
    public final ModuleMethod send$Mnerror;
    static final SimpleSymbol Lit81 = (SimpleSymbol) new SimpleSymbol("any").readResolve();
    static final SimpleSymbol Lit80 = (SimpleSymbol) new SimpleSymbol("lookup-handler").readResolve();
    static final SimpleSymbol Lit79 = (SimpleSymbol) new SimpleSymbol("dispatchEvent").readResolve();
    static final SimpleSymbol Lit78 = (SimpleSymbol) new SimpleSymbol("send-error").readResolve();
    static final SimpleSymbol Lit77 = (SimpleSymbol) new SimpleSymbol("add-to-form-do-after-creation").readResolve();
    static final SimpleSymbol Lit76 = (SimpleSymbol) new SimpleSymbol("add-to-global-vars").readResolve();
    static final SimpleSymbol Lit75 = (SimpleSymbol) new SimpleSymbol("add-to-components").readResolve();
    static final SimpleSymbol Lit74 = (SimpleSymbol) new SimpleSymbol("add-to-events").readResolve();
    static final SimpleSymbol Lit73 = (SimpleSymbol) new SimpleSymbol("add-to-global-var-environment").readResolve();
    static final SimpleSymbol Lit72 = (SimpleSymbol) new SimpleSymbol("is-bound-in-form-environment").readResolve();
    static final SimpleSymbol Lit71 = (SimpleSymbol) new SimpleSymbol("lookup-in-form-environment").readResolve();
    static final SimpleSymbol Lit70 = (SimpleSymbol) new SimpleSymbol("add-to-form-environment").readResolve();
    static final SimpleSymbol Lit69 = (SimpleSymbol) new SimpleSymbol("android-log-form").readResolve();
    static final FString Lit68 = new FString("com.google.appinventor.components.runtime.TinyDB");
    static final FString Lit67 = new FString("com.google.appinventor.components.runtime.TinyDB");
    static final SimpleSymbol Lit66 = (SimpleSymbol) new SimpleSymbol("GotValue").readResolve();
    static final SimpleSymbol Lit65 = (SimpleSymbol) new SimpleSymbol("TinyWebDB1$GotValue").readResolve();

    static {
        SimpleSymbol simpleSymbol = (SimpleSymbol) new SimpleSymbol(PropertyTypeConstants.PROPERTY_TYPE_TEXT)
                .readResolve();
        Lit7 = simpleSymbol;
        Lit64 = PairWithPosition.make(simpleSymbol, PairWithPosition.make(Lit81, LList.Empty,
                "C:\\Users\\LEO\\AppData\\Local\\Temp\\1515225807420_0.2955864762492916-0\\youngandroidproject\\..\\src\\appinventor\\ai_test\\SXTSstu\\Screen1.yail",
                377117),
                "C:\\Users\\LEO\\AppData\\Local\\Temp\\1515225807420_0.2955864762492916-0\\youngandroidproject\\..\\src\\appinventor\\ai_test\\SXTSstu\\Screen1.yail",
                377111);
        Lit63 = PairWithPosition.make(Lit81, PairWithPosition.make(Lit81, LList.Empty,
                "C:\\Users\\LEO\\AppData\\Local\\Temp\\1515225807420_0.2955864762492916-0\\youngandroidproject\\..\\src\\appinventor\\ai_test\\SXTSstu\\Screen1.yail",
                376972),
                "C:\\Users\\LEO\\AppData\\Local\\Temp\\1515225807420_0.2955864762492916-0\\youngandroidproject\\..\\src\\appinventor\\ai_test\\SXTSstu\\Screen1.yail",
                376967);
        Lit62 = new FString("com.google.appinventor.components.runtime.TinyWebDB");
        Lit61 = (SimpleSymbol) new SimpleSymbol("ServiceURL").readResolve();
        Lit60 = new FString("com.google.appinventor.components.runtime.TinyWebDB");
        Lit59 = (SimpleSymbol) new SimpleSymbol("Button1$Click").readResolve();
        Lit58 = PairWithPosition.make(Lit7, PairWithPosition.make(Lit81, LList.Empty,
                "C:\\Users\\LEO\\AppData\\Local\\Temp\\1515225807420_0.2955864762492916-0\\youngandroidproject\\..\\src\\appinventor\\ai_test\\SXTSstu\\Screen1.yail",
                340206),
                "C:\\Users\\LEO\\AppData\\Local\\Temp\\1515225807420_0.2955864762492916-0\\youngandroidproject\\..\\src\\appinventor\\ai_test\\SXTSstu\\Screen1.yail",
                340200);
        Lit57 = PairWithPosition.make(Lit7, PairWithPosition.make(Lit81, LList.Empty,
                "C:\\Users\\LEO\\AppData\\Local\\Temp\\1515225807420_0.2955864762492916-0\\youngandroidproject\\..\\src\\appinventor\\ai_test\\SXTSstu\\Screen1.yail",
                340055),
                "C:\\Users\\LEO\\AppData\\Local\\Temp\\1515225807420_0.2955864762492916-0\\youngandroidproject\\..\\src\\appinventor\\ai_test\\SXTSstu\\Screen1.yail",
                340049);
        Lit56 = (SimpleSymbol) new SimpleSymbol("StoreValue").readResolve();
        Lit55 = new FString("com.google.appinventor.components.runtime.Button");
        Lit54 = IntNum.make(new int[] { -1 });
        Lit53 = (SimpleSymbol) new SimpleSymbol("FontBold").readResolve();
        Lit52 = IntNum.make(new int[] { Component.COLOR_GREEN });
        Lit51 = new FString("com.google.appinventor.components.runtime.Button");
        Lit50 = (SimpleSymbol) new SimpleSymbol("Click").readResolve();
        Lit49 = (SimpleSymbol) new SimpleSymbol("Button2$Click").readResolve();
        Lit48 = PairWithPosition.make(Lit7, LList.Empty,
                "C:\\Users\\LEO\\AppData\\Local\\Temp\\1515225807420_0.2955864762492916-0\\youngandroidproject\\..\\src\\appinventor\\ai_test\\SXTSstu\\Screen1.yail",
                270439);
        Lit47 = (SimpleSymbol) new SimpleSymbol("TinyWebDB1").readResolve();
        Lit46 = new FString("com.google.appinventor.components.runtime.Button");
        Lit45 = IntNum.make(new int[] { -1 });
        Lit44 = (SimpleSymbol) new SimpleSymbol("TextColor").readResolve();
        Lit43 = (SimpleSymbol) new SimpleSymbol("Shape").readResolve();
        Lit42 = IntNum.make(new int[] { Component.COLOR_GREEN });
        Lit41 = (SimpleSymbol) new SimpleSymbol("BackgroundColor").readResolve();
        Lit40 = (SimpleSymbol) new SimpleSymbol("Button2").readResolve();
        Lit39 = new FString("com.google.appinventor.components.runtime.Button");
        Lit38 = new FString("com.google.appinventor.components.runtime.PasswordTextBox");
        Lit37 = (SimpleSymbol) new SimpleSymbol("PasswordTextBox1").readResolve();
        Lit36 = new FString("com.google.appinventor.components.runtime.PasswordTextBox");
        Lit35 = new FString("com.google.appinventor.components.runtime.Label");
        Lit34 = (SimpleSymbol) new SimpleSymbol("Label2").readResolve();
        Lit33 = new FString("com.google.appinventor.components.runtime.Label");
        Lit32 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
        Lit31 = (SimpleSymbol) new SimpleSymbol("HorizontalArrangement3").readResolve();
        Lit30 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
        Lit29 = new FString("com.google.appinventor.components.runtime.TextBox");
        Lit28 = (SimpleSymbol) new SimpleSymbol("TextBox1").readResolve();
        Lit27 = new FString("com.google.appinventor.components.runtime.TextBox");
        Lit26 = new FString("com.google.appinventor.components.runtime.Label");
        Lit25 = (SimpleSymbol) new SimpleSymbol("Text").readResolve();
        Lit24 = (SimpleSymbol) new SimpleSymbol("Label1").readResolve();
        Lit23 = new FString("com.google.appinventor.components.runtime.Label");
        Lit22 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
        Lit21 = (SimpleSymbol) new SimpleSymbol("HorizontalArrangement2").readResolve();
        Lit20 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
        Lit19 = (SimpleSymbol) new SimpleSymbol("Initialize").readResolve();
        Lit18 = (SimpleSymbol) new SimpleSymbol("Screen1$Initialize").readResolve();
        Lit17 = (SimpleSymbol) new SimpleSymbol(PropertyTypeConstants.PROPERTY_TYPE_BOOLEAN).readResolve();
        Lit16 = (SimpleSymbol) new SimpleSymbol("Enabled").readResolve();
        Lit15 = (SimpleSymbol) new SimpleSymbol("Button1").readResolve();
        Lit14 = PairWithPosition.make(Lit81, PairWithPosition.make(Lit81, LList.Empty,
                "C:\\Users\\LEO\\AppData\\Local\\Temp\\1515225807420_0.2955864762492916-0\\youngandroidproject\\..\\src\\appinventor\\ai_test\\SXTSstu\\Screen1.yail",
                69789),
                "C:\\Users\\LEO\\AppData\\Local\\Temp\\1515225807420_0.2955864762492916-0\\youngandroidproject\\..\\src\\appinventor\\ai_test\\SXTSstu\\Screen1.yail",
                69784);
        Lit13 = IntNum.make(1);
        Lit12 = PairWithPosition.make(Lit7, PairWithPosition.make(Lit81, LList.Empty,
                "C:\\Users\\LEO\\AppData\\Local\\Temp\\1515225807420_0.2955864762492916-0\\youngandroidproject\\..\\src\\appinventor\\ai_test\\SXTSstu\\Screen1.yail",
                69774),
                "C:\\Users\\LEO\\AppData\\Local\\Temp\\1515225807420_0.2955864762492916-0\\youngandroidproject\\..\\src\\appinventor\\ai_test\\SXTSstu\\Screen1.yail",
                69768);
        Lit11 = (SimpleSymbol) new SimpleSymbol("GetValue").readResolve();
        Lit10 = (SimpleSymbol) new SimpleSymbol("TinyDB1").readResolve();
        Lit9 = (SimpleSymbol) new SimpleSymbol("Title").readResolve();
        Lit8 = (SimpleSymbol) new SimpleSymbol("Icon").readResolve();
        Lit6 = (SimpleSymbol) new SimpleSymbol("BackgroundImage").readResolve();
        Lit5 = (SimpleSymbol) new SimpleSymbol("number").readResolve();
        Lit4 = IntNum.make(3);
        Lit3 = (SimpleSymbol) new SimpleSymbol("AlignHorizontal").readResolve();
        Lit2 = (SimpleSymbol) new SimpleSymbol("*the-null-value*").readResolve();
        Lit1 = (SimpleSymbol) new SimpleSymbol("getMessage").readResolve();
        Lit0 = (SimpleSymbol) new SimpleSymbol("Screen1").readResolve();
    }

    public Screen1() {
        ModuleInfo.register(this);
        frame frameVar = new frame();
        frameVar.$main = this;
        this.android$Mnlog$Mnform = new ModuleMethod(frameVar, 1, Lit69, 4097);
        this.add$Mnto$Mnform$Mnenvironment = new ModuleMethod(frameVar, 2, Lit70, 8194);
        this.lookup$Mnin$Mnform$Mnenvironment = new ModuleMethod(frameVar, 3, Lit71, 8193);
        this.is$Mnbound$Mnin$Mnform$Mnenvironment = new ModuleMethod(frameVar, 5, Lit72, 4097);
        this.add$Mnto$Mnglobal$Mnvar$Mnenvironment = new ModuleMethod(frameVar, 6, Lit73, 8194);
        this.add$Mnto$Mnevents = new ModuleMethod(frameVar, 7, Lit74, 8194);
        this.add$Mnto$Mncomponents = new ModuleMethod(frameVar, 8, Lit75, 16388);
        this.add$Mnto$Mnglobal$Mnvars = new ModuleMethod(frameVar, 9, Lit76, 8194);
        this.add$Mnto$Mnform$Mndo$Mnafter$Mncreation = new ModuleMethod(frameVar, 10, Lit77, 4097);
        this.send$Mnerror = new ModuleMethod(frameVar, 11, Lit78, 4097);
        this.process$Mnexception = new ModuleMethod(frameVar, 12, "process-exception", 4097);
        this.dispatchEvent = new ModuleMethod(frameVar, 13, Lit79, 16388);
        this.lookup$Mnhandler = new ModuleMethod(frameVar, 14, Lit80, 8194);
        ModuleMethod moduleMethod = new ModuleMethod(frameVar, 15, null, 0);
        moduleMethod.setProperty("source-location",
                "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm:541");
        lambda$Fn1 = moduleMethod;
        this.$define = new ModuleMethod(frameVar, 16, "$define", 0);
        lambda$Fn2 = new ModuleMethod(frameVar, 17, null, 0);
        this.Screen1$Initialize = new ModuleMethod(frameVar, 18, Lit18, 0);
        lambda$Fn3 = new ModuleMethod(frameVar, 19, null, 0);
        lambda$Fn4 = new ModuleMethod(frameVar, 20, null, 0);
        lambda$Fn5 = new ModuleMethod(frameVar, 21, null, 0);
        lambda$Fn6 = new ModuleMethod(frameVar, 22, null, 0);
        lambda$Fn7 = new ModuleMethod(frameVar, 23, null, 0);
        lambda$Fn8 = new ModuleMethod(frameVar, 24, null, 0);
        this.Button2$Click = new ModuleMethod(frameVar, 25, Lit49, 0);
        lambda$Fn9 = new ModuleMethod(frameVar, 26, null, 0);
        lambda$Fn10 = new ModuleMethod(frameVar, 27, null, 0);
        this.Button1$Click = new ModuleMethod(frameVar, 28, Lit59, 0);
        lambda$Fn11 = new ModuleMethod(frameVar, 29, null, 0);
        lambda$Fn12 = new ModuleMethod(frameVar, 30, null, 0);
        this.TinyWebDB1$GotValue = new ModuleMethod(frameVar, 31, Lit65, 8194);
    }

    public Object lookupInFormEnvironment(Symbol symbol) {
        return lookupInFormEnvironment(symbol, Boolean.FALSE);
    }

    @Override // java.lang.Runnable
    public void run() {
        CallContext callContext = CallContext.getInstance();
        Consumer consumer = callContext.consumer;
        callContext.consumer = VoidConsumer.instance;
        try {
            run(callContext);
            th = null;
        } catch (Throwable th) {
            th = th;
        }
        ModuleBody.runCleanup(callContext, th, consumer);
    }

    public final void run(CallContext $ctx) {
        Consumer $result = $ctx.consumer;
        Object find = require.find("com.google.youngandroid.runtime");
        try {
            ((Runnable) find).run();
            this.$Stdebug$Mnform$St = Boolean.FALSE;
            this.form$Mnenvironment = Environment.make(Lit0.toString());
            FString stringAppend = strings.stringAppend(Lit0.toString(), "-global-vars");
            this.global$Mnvar$Mnenvironment = Environment.make(stringAppend == null ? null : stringAppend.toString());
            Screen1 = null;
            this.form$Mnname$Mnsymbol = Lit0;
            this.events$Mnto$Mnregister = LList.Empty;
            this.components$Mnto$Mncreate = LList.Empty;
            this.global$Mnvars$Mnto$Mncreate = LList.Empty;
            this.form$Mndo$Mnafter$Mncreation = LList.Empty;
            Object find2 = require.find("com.google.youngandroid.runtime");
            try {
                ((Runnable) find2).run();
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    runtime.setAndCoerceProperty$Ex(Lit0, Lit3, Lit4, Lit5);
                    runtime.setAndCoerceProperty$Ex(Lit0, Lit6, "timgFXSQT685.bmp", Lit7);
                    runtime.setAndCoerceProperty$Ex(Lit0, Lit8, "ico.png", Lit7);
                    Values.writeValues(runtime.setAndCoerceProperty$Ex(Lit0, Lit9, "如需修改密码请找顾老板", Lit7), $result);
                } else {
                    addToFormDoAfterCreation(new Promise(lambda$Fn2));
                }
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    runtime.addToCurrentFormEnvironment(Lit18, this.Screen1$Initialize);
                } else {
                    addToFormEnvironment(Lit18, this.Screen1$Initialize);
                }
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    EventDispatcher.registerEventForDelegation((HandlesEventDispatching) runtime.$Stthis$Mnform$St,
                            "Screen1", "Initialize");
                } else {
                    addToEvents(Lit0, Lit19);
                }
                this.HorizontalArrangement2 = null;
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit20, Lit21, Boolean.FALSE), $result);
                } else {
                    addToComponents(Lit0, Lit22, Lit21, Boolean.FALSE);
                }
                this.Label1 = null;
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(runtime.addComponentWithinRepl(Lit21, Lit23, Lit24, lambda$Fn3), $result);
                } else {
                    addToComponents(Lit21, Lit26, Lit24, lambda$Fn4);
                }
                this.TextBox1 = null;
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(runtime.addComponentWithinRepl(Lit21, Lit27, Lit28, Boolean.FALSE), $result);
                } else {
                    addToComponents(Lit21, Lit29, Lit28, Boolean.FALSE);
                }
                this.HorizontalArrangement3 = null;
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit30, Lit31, Boolean.FALSE), $result);
                } else {
                    addToComponents(Lit0, Lit32, Lit31, Boolean.FALSE);
                }
                this.Label2 = null;
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(runtime.addComponentWithinRepl(Lit31, Lit33, Lit34, lambda$Fn5), $result);
                } else {
                    addToComponents(Lit31, Lit35, Lit34, lambda$Fn6);
                }
                this.PasswordTextBox1 = null;
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(runtime.addComponentWithinRepl(Lit31, Lit36, Lit37, Boolean.FALSE), $result);
                } else {
                    addToComponents(Lit31, Lit38, Lit37, Boolean.FALSE);
                }
                this.Button2 = null;
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit39, Lit40, lambda$Fn7), $result);
                } else {
                    addToComponents(Lit0, Lit46, Lit40, lambda$Fn8);
                }
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    runtime.addToCurrentFormEnvironment(Lit49, this.Button2$Click);
                } else {
                    addToFormEnvironment(Lit49, this.Button2$Click);
                }
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    EventDispatcher.registerEventForDelegation((HandlesEventDispatching) runtime.$Stthis$Mnform$St,
                            "Button2", "Click");
                } else {
                    addToEvents(Lit40, Lit50);
                }
                this.Button1 = null;
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit51, Lit15, lambda$Fn9), $result);
                } else {
                    addToComponents(Lit0, Lit55, Lit15, lambda$Fn10);
                }
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    runtime.addToCurrentFormEnvironment(Lit59, this.Button1$Click);
                } else {
                    addToFormEnvironment(Lit59, this.Button1$Click);
                }
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    EventDispatcher.registerEventForDelegation((HandlesEventDispatching) runtime.$Stthis$Mnform$St,
                            "Button1", "Click");
                } else {
                    addToEvents(Lit15, Lit50);
                }
                this.TinyWebDB1 = null;
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit60, Lit47, lambda$Fn11), $result);
                } else {
                    addToComponents(Lit0, Lit62, Lit47, lambda$Fn12);
                }
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    runtime.addToCurrentFormEnvironment(Lit65, this.TinyWebDB1$GotValue);
                } else {
                    addToFormEnvironment(Lit65, this.TinyWebDB1$GotValue);
                }
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    EventDispatcher.registerEventForDelegation((HandlesEventDispatching) runtime.$Stthis$Mnform$St,
                            "TinyWebDB1", "GotValue");
                } else {
                    addToEvents(Lit47, Lit66);
                }
                this.TinyDB1 = null;
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit67, Lit10, Boolean.FALSE), $result);
                } else {
                    addToComponents(Lit0, Lit68, Lit10, Boolean.FALSE);
                }
                runtime.initRuntime();
            } catch (ClassCastException e) {
                throw new WrongType(e, "java.lang.Runnable.run()", 1, find2);
            }
        } catch (ClassCastException e2) {
            throw new WrongType(e2, "java.lang.Runnable.run()", 1, find);
        }
    }

    static Object lambda3() {
        runtime.setAndCoerceProperty$Ex(Lit0, Lit3, Lit4, Lit5);
        runtime.setAndCoerceProperty$Ex(Lit0, Lit6, "timgFXSQT685.bmp", Lit7);
        runtime.setAndCoerceProperty$Ex(Lit0, Lit8, "ico.png", Lit7);
        return runtime.setAndCoerceProperty$Ex(Lit0, Lit9, "", Lit7);
    }

    public Object Screen1$Initialize() {
        runtime.setThisForm();
        return runtime.callYailPrimitive(runtime.yail$Mnequal$Qu,
                LList.list2(runtime.callComponentMethod(Lit10, Lit11, LList.list2("if", "0"), Lit12), Lit13), Lit14,
                "=") != Boolean.FALSE ? runtime.setAndCoerceProperty$Ex(Lit15, Lit16, Boolean.FALSE, Lit17)
                        : Values.empty;
    }

    static Object lambda4() {
        return runtime.setAndCoerceProperty$Ex(Lit24, Lit25, "用户名", Lit7);
    }

    static Object lambda5() {
        return runtime.setAndCoerceProperty$Ex(Lit24, Lit25, "用户名", Lit7);
    }

    static Object lambda6() {
        return runtime.setAndCoerceProperty$Ex(Lit34, Lit25, "密   码", Lit7);
    }

    static Object lambda7() {
        return runtime.setAndCoerceProperty$Ex(Lit34, Lit25, "密   码", Lit7);
    }

    static Object lambda8() {
        runtime.setAndCoerceProperty$Ex(Lit40, Lit41, Lit42, Lit5);
        runtime.setAndCoerceProperty$Ex(Lit40, Lit43, Lit13, Lit5);
        runtime.setAndCoerceProperty$Ex(Lit40, Lit25, "登录", Lit7);
        return runtime.setAndCoerceProperty$Ex(Lit40, Lit44, Lit45, Lit5);
    }

    static Object lambda9() {
        runtime.setAndCoerceProperty$Ex(Lit40, Lit41, Lit42, Lit5);
        runtime.setAndCoerceProperty$Ex(Lit40, Lit43, Lit13, Lit5);
        runtime.setAndCoerceProperty$Ex(Lit40, Lit25, "登录", Lit7);
        return runtime.setAndCoerceProperty$Ex(Lit40, Lit44, Lit45, Lit5);
    }

    public Object Button2$Click() {
        runtime.setThisForm();
        return runtime.callComponentMethod(Lit47, Lit11, LList.list1(runtime.getProperty$1(Lit28, Lit25)), Lit48);
    }

    static Object lambda10() {
        runtime.setAndCoerceProperty$Ex(Lit15, Lit41, Lit52, Lit5);
        runtime.setAndCoerceProperty$Ex(Lit15, Lit53, Boolean.TRUE, Lit17);
        runtime.setAndCoerceProperty$Ex(Lit15, Lit43, Lit13, Lit5);
        runtime.setAndCoerceProperty$Ex(Lit15, Lit25, "注册", Lit7);
        return runtime.setAndCoerceProperty$Ex(Lit15, Lit44, Lit54, Lit5);
    }

    static Object lambda11() {
        runtime.setAndCoerceProperty$Ex(Lit15, Lit41, Lit52, Lit5);
        runtime.setAndCoerceProperty$Ex(Lit15, Lit53, Boolean.TRUE, Lit17);
        runtime.setAndCoerceProperty$Ex(Lit15, Lit43, Lit13, Lit5);
        runtime.setAndCoerceProperty$Ex(Lit15, Lit25, "注册", Lit7);
        return runtime.setAndCoerceProperty$Ex(Lit15, Lit44, Lit54, Lit5);
    }

    public Object Button1$Click() {
        runtime.setThisForm();
        runtime.callComponentMethod(Lit10, Lit56, LList.list2("if", "1"), Lit57);
        return runtime.callComponentMethod(Lit47, Lit56,
                LList.list2(runtime.getProperty$1(Lit28, Lit25), runtime.getProperty$1(Lit37, Lit25)), Lit58);
    }

    static Object lambda12() {
        return runtime.setAndCoerceProperty$Ex(Lit47, Lit61, "http://tinywebdb.gzjkw.net/db.php?user=GLApw&pw=gc&v=1",
                Lit7);
    }

    static Object lambda13() {
        return runtime.setAndCoerceProperty$Ex(Lit47, Lit61, "http://tinywebdb.gzjkw.net/db.php?user=GLApw&pw=gc&v=1",
                Lit7);
    }

    public Object TinyWebDB1$GotValue(Object $tagFromWebDB, Object $valueFromWebDB) {
        Object $tagFromWebDB2 = runtime.sanitizeComponentData($tagFromWebDB);
        Object $valueFromWebDB2 = runtime.sanitizeComponentData($valueFromWebDB);
        runtime.setThisForm();
        return runtime.callYailPrimitive(runtime.yail$Mnequal$Qu,
                LList.list2(runtime.getProperty$1(Lit37, Lit25), $valueFromWebDB2), Lit63, "=") != Boolean.FALSE
                        ? runtime.callYailPrimitive(runtime.open$Mnanother$Mnscreen$Mnwith$Mnstart$Mnvalue,
                                LList.list2("Screen2", $tagFromWebDB2), Lit64, "open another screen with start value")
                        : Values.empty;
    }

    /* compiled from: Screen1.yail */
    /* loaded from: classes.dex */
    public class frame extends ModuleBody {
        Screen1 $main;

        @Override // gnu.expr.ModuleBody
        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            switch (moduleMethod.selector) {
                case 1:
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                case 2:
                case 4:
                case 6:
                case 7:
                case 8:
                case 9:
                default:
                    return super.match1(moduleMethod, obj, callContext);
                case 3:
                    if (obj instanceof Symbol) {
                        callContext.value1 = obj;
                        callContext.proc = moduleMethod;
                        callContext.pc = 1;
                        return 0;
                    }
                    return -786431;
                case 5:
                    if (obj instanceof Symbol) {
                        callContext.value1 = obj;
                        callContext.proc = moduleMethod;
                        callContext.pc = 1;
                        return 0;
                    }
                    return -786431;
                case 10:
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                case 11:
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                case 12:
                    if (obj instanceof Screen1) {
                        callContext.value1 = obj;
                        callContext.proc = moduleMethod;
                        callContext.pc = 1;
                        return 0;
                    }
                    return -786431;
            }
        }

        @Override // gnu.expr.ModuleBody
        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            switch (moduleMethod.selector) {
                case 2:
                    if (obj instanceof Symbol) {
                        callContext.value1 = obj;
                        callContext.value2 = obj2;
                        callContext.proc = moduleMethod;
                        callContext.pc = 2;
                        return 0;
                    }
                    return -786431;
                case 3:
                    if (obj instanceof Symbol) {
                        callContext.value1 = obj;
                        callContext.value2 = obj2;
                        callContext.proc = moduleMethod;
                        callContext.pc = 2;
                        return 0;
                    }
                    return -786431;
                case 6:
                    if (obj instanceof Symbol) {
                        callContext.value1 = obj;
                        callContext.value2 = obj2;
                        callContext.proc = moduleMethod;
                        callContext.pc = 2;
                        return 0;
                    }
                    return -786431;
                case 7:
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.proc = moduleMethod;
                    callContext.pc = 2;
                    return 0;
                case 9:
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.proc = moduleMethod;
                    callContext.pc = 2;
                    return 0;
                case 14:
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.proc = moduleMethod;
                    callContext.pc = 2;
                    return 0;
                case 31:
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.proc = moduleMethod;
                    callContext.pc = 2;
                    return 0;
                default:
                    return super.match2(moduleMethod, obj, obj2, callContext);
            }
        }

        @Override // gnu.expr.ModuleBody
        public int match4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4,
                CallContext callContext) {
            switch (moduleMethod.selector) {
                case 8:
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.value3 = obj3;
                    callContext.value4 = obj4;
                    callContext.proc = moduleMethod;
                    callContext.pc = 4;
                    return 0;
                case 13:
                    if (obj instanceof Screen1) {
                        callContext.value1 = obj;
                        if (obj2 instanceof Component) {
                            callContext.value2 = obj2;
                            if (obj3 instanceof String) {
                                callContext.value3 = obj3;
                                if (obj4 instanceof String) {
                                    callContext.value4 = obj4;
                                    callContext.proc = moduleMethod;
                                    callContext.pc = 4;
                                    return 0;
                                }
                                return -786428;
                            }
                            return -786429;
                        }
                        return -786430;
                    }
                    return -786431;
                default:
                    return super.match4(moduleMethod, obj, obj2, obj3, obj4, callContext);
            }
        }

        @Override // gnu.expr.ModuleBody
        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            switch (moduleMethod.selector) {
                case 1:
                    this.$main.androidLogForm(obj);
                    return Values.empty;
                case 2:
                case 4:
                case 6:
                case 7:
                case 8:
                case 9:
                default:
                    return super.apply1(moduleMethod, obj);
                case 3:
                    try {
                        return this.$main.lookupInFormEnvironment((Symbol) obj);
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "lookup-in-form-environment", 1, obj);
                    }
                case 5:
                    try {
                        return this.$main.isBoundInFormEnvironment((Symbol) obj) ? Boolean.TRUE : Boolean.FALSE;
                    } catch (ClassCastException e2) {
                        throw new WrongType(e2, "is-bound-in-form-environment", 1, obj);
                    }
                case 10:
                    this.$main.addToFormDoAfterCreation(obj);
                    return Values.empty;
                case 11:
                    this.$main.sendError(obj);
                    return Values.empty;
                case 12:
                    this.$main.processException(obj);
                    return Values.empty;
            }
        }

        @Override // gnu.expr.ModuleBody
        public Object apply4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4) {
            switch (moduleMethod.selector) {
                case 8:
                    this.$main.addToComponents(obj, obj2, obj3, obj4);
                    return Values.empty;
                case 13:
                    try {
                        try {
                            try {
                                try {
                                    return this.$main.dispatchEvent((Component) obj, (String) obj2, (String) obj3,
                                            (Object[]) obj4) ? Boolean.TRUE : Boolean.FALSE;
                                } catch (ClassCastException e) {
                                    throw new WrongType(e, "dispatchEvent", 4, obj4);
                                }
                            } catch (ClassCastException e2) {
                                throw new WrongType(e2, "dispatchEvent", 3, obj3);
                            }
                        } catch (ClassCastException e3) {
                            throw new WrongType(e3, "dispatchEvent", 2, obj2);
                        }
                    } catch (ClassCastException e4) {
                        throw new WrongType(e4, "dispatchEvent", 1, obj);
                    }
                default:
                    return super.apply4(moduleMethod, obj, obj2, obj3, obj4);
            }
        }

        @Override // gnu.expr.ModuleBody
        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            switch (moduleMethod.selector) {
                case 2:
                    try {
                        this.$main.addToFormEnvironment((Symbol) obj, obj2);
                        return Values.empty;
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "add-to-form-environment", 1, obj);
                    }
                case 3:
                    try {
                        return this.$main.lookupInFormEnvironment((Symbol) obj, obj2);
                    } catch (ClassCastException e2) {
                        throw new WrongType(e2, "lookup-in-form-environment", 1, obj);
                    }
                case 6:
                    try {
                        this.$main.addToGlobalVarEnvironment((Symbol) obj, obj2);
                        return Values.empty;
                    } catch (ClassCastException e3) {
                        throw new WrongType(e3, "add-to-global-var-environment", 1, obj);
                    }
                case 7:
                    this.$main.addToEvents(obj, obj2);
                    return Values.empty;
                case 9:
                    this.$main.addToGlobalVars(obj, obj2);
                    return Values.empty;
                case 14:
                    return this.$main.lookupHandler(obj, obj2);
                case 31:
                    return this.$main.TinyWebDB1$GotValue(obj, obj2);
                default:
                    return super.apply2(moduleMethod, obj, obj2);
            }
        }

        @Override // gnu.expr.ModuleBody
        public Object apply0(ModuleMethod moduleMethod) {
            switch (moduleMethod.selector) {
                case 15:
                    return Screen1.lambda2();
                case 16:
                    this.$main.$define();
                    return Values.empty;
                case 17:
                    return Screen1.lambda3();
                case 18:
                    return this.$main.Screen1$Initialize();
                case 19:
                    return Screen1.lambda4();
                case 20:
                    return Screen1.lambda5();
                case 21:
                    return Screen1.lambda6();
                case 22:
                    return Screen1.lambda7();
                case 23:
                    return Screen1.lambda8();
                case 24:
                    return Screen1.lambda9();
                case 25:
                    return this.$main.Button2$Click();
                case 26:
                    return Screen1.lambda10();
                case 27:
                    return Screen1.lambda11();
                case 28:
                    return this.$main.Button1$Click();
                case 29:
                    return Screen1.lambda12();
                case 30:
                    return Screen1.lambda13();
                default:
                    return super.apply0(moduleMethod);
            }
        }

        @Override // gnu.expr.ModuleBody
        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            switch (moduleMethod.selector) {
                case 15:
                    callContext.proc = moduleMethod;
                    callContext.pc = 0;
                    return 0;
                case 16:
                    callContext.proc = moduleMethod;
                    callContext.pc = 0;
                    return 0;
                case 17:
                    callContext.proc = moduleMethod;
                    callContext.pc = 0;
                    return 0;
                case 18:
                    callContext.proc = moduleMethod;
                    callContext.pc = 0;
                    return 0;
                case 19:
                    callContext.proc = moduleMethod;
                    callContext.pc = 0;
                    return 0;
                case 20:
                    callContext.proc = moduleMethod;
                    callContext.pc = 0;
                    return 0;
                case 21:
                    callContext.proc = moduleMethod;
                    callContext.pc = 0;
                    return 0;
                case 22:
                    callContext.proc = moduleMethod;
                    callContext.pc = 0;
                    return 0;
                case 23:
                    callContext.proc = moduleMethod;
                    callContext.pc = 0;
                    return 0;
                case 24:
                    callContext.proc = moduleMethod;
                    callContext.pc = 0;
                    return 0;
                case 25:
                    callContext.proc = moduleMethod;
                    callContext.pc = 0;
                    return 0;
                case 26:
                    callContext.proc = moduleMethod;
                    callContext.pc = 0;
                    return 0;
                case 27:
                    callContext.proc = moduleMethod;
                    callContext.pc = 0;
                    return 0;
                case 28:
                    callContext.proc = moduleMethod;
                    callContext.pc = 0;
                    return 0;
                case 29:
                    callContext.proc = moduleMethod;
                    callContext.pc = 0;
                    return 0;
                case 30:
                    callContext.proc = moduleMethod;
                    callContext.pc = 0;
                    return 0;
                default:
                    return super.match0(moduleMethod, callContext);
            }
        }
    }

    public void androidLogForm(Object message) {
    }

    public void addToFormEnvironment(Symbol name, Object object) {
        androidLogForm(
                Format.formatToString(0, "Adding ~A to env ~A with value ~A", name, this.form$Mnenvironment, object));
        this.form$Mnenvironment.put(name, object);
    }

    public Object lookupInFormEnvironment(Symbol name, Object default$Mnvalue) {
        int i = ((this.form$Mnenvironment == null ? 1 : 0) + 1) & 1;
        if (i != 0) {
            if (!this.form$Mnenvironment.isBound(name)) {
                return default$Mnvalue;
            }
        } else if (i == 0) {
            return default$Mnvalue;
        }
        return this.form$Mnenvironment.get(name);
    }

    public boolean isBoundInFormEnvironment(Symbol name) {
        return this.form$Mnenvironment.isBound(name);
    }

    public void addToGlobalVarEnvironment(Symbol name, Object object) {
        androidLogForm(Format.formatToString(0, "Adding ~A to env ~A with value ~A", name,
                this.global$Mnvar$Mnenvironment, object));
        this.global$Mnvar$Mnenvironment.put(name, object);
    }

    public void addToEvents(Object component$Mnname, Object event$Mnname) {
        this.events$Mnto$Mnregister = lists.cons(lists.cons(component$Mnname, event$Mnname),
                this.events$Mnto$Mnregister);
    }

    public void addToComponents(Object container$Mnname, Object component$Mntype, Object component$Mnname,
            Object init$Mnthunk) {
        this.components$Mnto$Mncreate = lists.cons(
                LList.list4(container$Mnname, component$Mntype, component$Mnname, init$Mnthunk),
                this.components$Mnto$Mncreate);
    }

    public void addToGlobalVars(Object var, Object val$Mnthunk) {
        this.global$Mnvars$Mnto$Mncreate = lists.cons(LList.list2(var, val$Mnthunk), this.global$Mnvars$Mnto$Mncreate);
    }

    public void addToFormDoAfterCreation(Object thunk) {
        this.form$Mndo$Mnafter$Mncreation = lists.cons(thunk, this.form$Mndo$Mnafter$Mncreation);
    }

    public void sendError(Object error) {
        RetValManager.sendError(error == null ? null : error.toString());
    }

    public void processException(Object ex) {
        try {
            ReplApplication.reportError((Throwable) ex);
            Object apply1 = Scheme.applyToArgs.apply1(GetNamedPart.getNamedPart.apply2(ex, Lit1));
            RuntimeErrorAlert.alert(this, apply1 == null ? null : apply1.toString(),
                    ex instanceof YailRuntimeError ? ((YailRuntimeError) ex).getErrorType() : "Runtime Error",
                    "End Application");
        } catch (ClassCastException e) {
            throw new WrongType(e,
                    "com.google.appinventor.components.runtime.ReplApplication.reportError(java.lang.Throwable)", 1,
                    ex);
        }
    }

    @Override // com.google.appinventor.components.runtime.Form,
              // com.google.appinventor.components.runtime.HandlesEventDispatching
    public boolean dispatchEvent(Component componentObject, String registeredComponentName, String eventName,
            Object[] args) {
        SimpleSymbol registeredObject = misc.string$To$Symbol(registeredComponentName);
        if (isBoundInFormEnvironment(registeredObject)) {
            if (lookupInFormEnvironment(registeredObject) == componentObject) {
                Object handler = lookupHandler(registeredComponentName, eventName);
                try {
                    Scheme.apply.apply2(handler, LList.makeList(args, 0));
                    return true;
                } catch (Throwable exception) {
                    androidLogForm(exception.getMessage());
                    exception.printStackTrace();
                    processException(exception);
                    return false;
                }
            }
            return false;
        }
        EventDispatcher.unregisterEventForDelegation(this, registeredComponentName, eventName);
        return false;
    }

    public Object lookupHandler(Object componentName, Object eventName) {
        return lookupInFormEnvironment(misc.string$To$Symbol(
                EventDispatcher.makeFullEventName(componentName == null ? null : componentName.toString(),
                        eventName != null ? eventName.toString() : null)));
    }

    @Override // com.google.appinventor.components.runtime.Form
    public void $define() {
        Object obj;
        Language.setDefaults(Scheme.getInstance());
        try {
            run();
        } catch (Exception exception) {
            androidLogForm(exception.getMessage());
            processException(exception);
        }
        Screen1 = this;
        addToFormEnvironment(Lit0, this);
        LList events = this.events$Mnto$Mnregister;
        Object obj2 = events;
        while (obj2 != LList.Empty) {
            try {
                Pair arg0 = (Pair) obj2;
                Object event$Mninfo = arg0.getCar();
                Object apply1 = lists.car.apply1(event$Mninfo);
                String obj3 = apply1 == null ? null : apply1.toString();
                Object apply12 = lists.cdr.apply1(event$Mninfo);
                EventDispatcher.registerEventForDelegation(this, obj3, apply12 == null ? null : apply12.toString());
                obj2 = arg0.getCdr();
            } catch (ClassCastException e) {
                throw new WrongType(e, "arg0", -2, obj2);
            }
        }
        try {
            addToGlobalVars(Lit2, lambda$Fn1);
            LList var$Mnval$Mnpairs = lists.reverse(this.global$Mnvars$Mnto$Mncreate);
            Object obj4 = var$Mnval$Mnpairs;
            while (obj4 != LList.Empty) {
                try {
                    Pair arg02 = (Pair) obj4;
                    Object var$Mnval = arg02.getCar();
                    Object var = lists.car.apply1(var$Mnval);
                    Object val$Mnthunk = lists.cadr.apply1(var$Mnval);
                    try {
                        addToGlobalVarEnvironment((Symbol) var, Scheme.applyToArgs.apply1(val$Mnthunk));
                        obj4 = arg02.getCdr();
                    } catch (ClassCastException e2) {
                        throw new WrongType(e2, "add-to-global-var-environment", 0, var);
                    }
                } catch (ClassCastException e3) {
                    throw new WrongType(e3, "arg0", -2, obj4);
                }
            }
            Object reverse = lists.reverse(this.form$Mndo$Mnafter$Mncreation);
            while (reverse != LList.Empty) {
                try {
                    Pair arg03 = (Pair) reverse;
                    misc.force(arg03.getCar());
                    reverse = arg03.getCdr();
                } catch (ClassCastException e4) {
                    throw new WrongType(e4, "arg0", -2, reverse);
                }
            }
            LList component$Mndescriptors = lists.reverse(this.components$Mnto$Mncreate);
            Object obj5 = component$Mndescriptors;
            while (obj5 != LList.Empty) {
                try {
                    Pair arg04 = (Pair) obj5;
                    Object component$Mninfo = arg04.getCar();
                    Object component$Mnname = lists.caddr.apply1(component$Mninfo);
                    lists.cadddr.apply1(component$Mninfo);
                    Object component$Mntype = lists.cadr.apply1(component$Mninfo);
                    try {
                        Object component$Mncontainer = lookupInFormEnvironment(
                                (Symbol) lists.car.apply1(component$Mninfo));
                        Object component$Mnobject = Invoke.make.apply2(component$Mntype, component$Mncontainer);
                        SlotSet.set$Mnfield$Ex.apply3(this, component$Mnname, component$Mnobject);
                        try {
                            addToFormEnvironment((Symbol) component$Mnname, component$Mnobject);
                            obj5 = arg04.getCdr();
                        } catch (ClassCastException e5) {
                            throw new WrongType(e5, "add-to-form-environment", 0, component$Mnname);
                        }
                    } catch (ClassCastException e6) {
                        throw new WrongType(e6, "lookup-in-form-environment", 0, obj);
                    }
                } catch (ClassCastException e7) {
                    throw new WrongType(e7, "arg0", -2, obj5);
                }
            }
            Object obj6 = component$Mndescriptors;
            while (obj6 != LList.Empty) {
                try {
                    Pair arg05 = (Pair) obj6;
                    Object component$Mninfo2 = arg05.getCar();
                    lists.caddr.apply1(component$Mninfo2);
                    Object init$Mnthunk = lists.cadddr.apply1(component$Mninfo2);
                    if (init$Mnthunk != Boolean.FALSE) {
                        Scheme.applyToArgs.apply1(init$Mnthunk);
                    }
                    obj6 = arg05.getCdr();
                } catch (ClassCastException e8) {
                    throw new WrongType(e8, "arg0", -2, obj6);
                }
            }
            Object obj7 = component$Mndescriptors;
            while (obj7 != LList.Empty) {
                try {
                    Pair arg06 = (Pair) obj7;
                    Object component$Mninfo3 = arg06.getCar();
                    Object component$Mnname2 = lists.caddr.apply1(component$Mninfo3);
                    lists.cadddr.apply1(component$Mninfo3);
                    callInitialize(SlotGet.field.apply2(this, component$Mnname2));
                    obj7 = arg06.getCdr();
                } catch (ClassCastException e9) {
                    throw new WrongType(e9, "arg0", -2, obj7);
                }
            }
        } catch (YailRuntimeError exception2) {
            processException(exception2);
        }
    }

    public static SimpleSymbol lambda1symbolAppend$V(Object[] argsArray) {
        LList symbols = LList.makeList(argsArray, 0);
        Apply apply = Scheme.apply;
        ModuleMethod moduleMethod = strings.string$Mnappend;
        Object obj = LList.Empty;
        LList lList = symbols;
        while (lList != LList.Empty) {
            try {
                Pair arg0 = (Pair) lList;
                Object arg02 = arg0.getCdr();
                Object car = arg0.getCar();
                try {
                    obj = Pair.make(((Symbol) car).toString(), obj);
                    lList = arg02;
                } catch (ClassCastException e) {
                    throw new WrongType(e, "symbol->string", 1, car);
                }
            } catch (ClassCastException e2) {
                throw new WrongType(e2, "arg0", -2, lList);
            }
        }
        Object apply2 = apply.apply2(moduleMethod, LList.reverseInPlace(obj));
        try {
            return misc.string$To$Symbol((CharSequence) apply2);
        } catch (ClassCastException e3) {
            throw new WrongType(e3, "string->symbol", 1, apply2);
        }
    }

    static Object lambda2() {
        return null;
    }
}
