package appinventor.ai_test.SXTSstu;

import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.runtime.Button;
import com.google.appinventor.components.runtime.Canvas;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.HandlesEventDispatching;
import com.google.appinventor.components.runtime.HorizontalArrangement;
import com.google.appinventor.components.runtime.Label;
import com.google.appinventor.components.runtime.ReplApplication;
import com.google.appinventor.components.runtime.Spinner;
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
import gnu.kawa.functions.AddOp;
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

/* compiled from: Screen2.yail */
/* loaded from: classes.dex */
public class Screen2 extends Form implements Runnable {
    static final SimpleSymbol Lit0;
    static final SimpleSymbol Lit1;
    static final SimpleSymbol Lit10;
    static final PairWithPosition Lit100;
    static final PairWithPosition Lit101;
    static final SimpleSymbol Lit11;
    static final SimpleSymbol Lit12;
    static final PairWithPosition Lit13;
    static final SimpleSymbol Lit14;
    static final SimpleSymbol Lit15;
    static final FString Lit16;
    static final SimpleSymbol Lit17;
    static final FString Lit18;
    static final FString Lit19;
    static final SimpleSymbol Lit2;
    static final SimpleSymbol Lit20;
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
    static final IntNum Lit44;
    static final SimpleSymbol Lit45;
    static final IntNum Lit46;
    static final FString Lit47;
    static final SimpleSymbol Lit48;
    static final SimpleSymbol Lit49;
    static final SimpleSymbol Lit5;
    static final PairWithPosition Lit50;
    static final PairWithPosition Lit51;
    static final PairWithPosition Lit52;
    static final SimpleSymbol Lit53;
    static final PairWithPosition Lit54;
    static final PairWithPosition Lit55;
    static final SimpleSymbol Lit56;
    static final SimpleSymbol Lit57;
    static final FString Lit58;
    static final SimpleSymbol Lit59;
    static final IntNum Lit6;
    static final IntNum Lit60;
    static final SimpleSymbol Lit61;
    static final IntNum Lit62;
    static final SimpleSymbol Lit63;
    static final IntNum Lit64;
    static final FString Lit65;
    static final FString Lit66;
    static final SimpleSymbol Lit67;
    static final SimpleSymbol Lit68;
    static final FString Lit69;
    static final SimpleSymbol Lit7;
    static final SimpleSymbol Lit70;
    static final PairWithPosition Lit71;
    static final PairWithPosition Lit72;
    static final PairWithPosition Lit73;
    static final SimpleSymbol Lit74;
    static final IntNum Lit75;
    static final IntNum Lit76;
    static final PairWithPosition Lit77;
    static final SimpleSymbol Lit78;
    static final PairWithPosition Lit79;
    static final SimpleSymbol Lit8;
    static final SimpleSymbol Lit80;
    static final SimpleSymbol Lit81;
    static final FString Lit82;
    static final FString Lit83;
    static final PairWithPosition Lit84;
    static final IntNum Lit85;
    static final PairWithPosition Lit86;
    static final PairWithPosition Lit87;
    static final PairWithPosition Lit88;
    static final PairWithPosition Lit89;
    static final SimpleSymbol Lit9;
    static final PairWithPosition Lit90;
    static final PairWithPosition Lit91;
    static final PairWithPosition Lit92;
    static final SimpleSymbol Lit93;
    static final FString Lit94;
    static final FString Lit95;
    static final PairWithPosition Lit96;
    static final SimpleSymbol Lit97;
    static final SimpleSymbol Lit98;
    static final PairWithPosition Lit99;
    public static Screen2 Screen2;
    static final ModuleMethod lambda$Fn1 = null;
    static final ModuleMethod lambda$Fn10 = null;
    static final ModuleMethod lambda$Fn11 = null;
    static final ModuleMethod lambda$Fn12 = null;
    static final ModuleMethod lambda$Fn13 = null;
    static final ModuleMethod lambda$Fn14 = null;
    static final ModuleMethod lambda$Fn15 = null;
    static final ModuleMethod lambda$Fn16 = null;
    static final ModuleMethod lambda$Fn17 = null;
    static final ModuleMethod lambda$Fn18 = null;
    static final ModuleMethod lambda$Fn19 = null;
    static final ModuleMethod lambda$Fn2 = null;
    static final ModuleMethod lambda$Fn20 = null;
    static final ModuleMethod lambda$Fn21 = null;
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
    public Canvas Canvas1;
    public HorizontalArrangement HorizontalArrangement1;
    public HorizontalArrangement HorizontalArrangement2;
    public HorizontalArrangement HorizontalArrangement4;
    public Label Label1;
    public Label Label2;
    public final ModuleMethod Screen2$Initialize;
    public Spinner Spinner1;
    public Spinner Spinner2;
    public TinyDB TinyDB1;
    public TinyWebDB TinyWebDB1;
    public final ModuleMethod TinyWebDB1$GotValue;
    public TinyWebDB TinyWebDB2;
    public final ModuleMethod TinyWebDB2$GotValue;
    public TinyWebDB TinyWebDB3;
    public final ModuleMethod TinyWebDB3$GotValue;
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
    static final SimpleSymbol Lit117 = (SimpleSymbol) new SimpleSymbol("any").readResolve();
    static final SimpleSymbol Lit116 = (SimpleSymbol) new SimpleSymbol("lookup-handler").readResolve();
    static final SimpleSymbol Lit115 = (SimpleSymbol) new SimpleSymbol("dispatchEvent").readResolve();
    static final SimpleSymbol Lit114 = (SimpleSymbol) new SimpleSymbol("send-error").readResolve();
    static final SimpleSymbol Lit113 = (SimpleSymbol) new SimpleSymbol("add-to-form-do-after-creation").readResolve();
    static final SimpleSymbol Lit112 = (SimpleSymbol) new SimpleSymbol("add-to-global-vars").readResolve();
    static final SimpleSymbol Lit111 = (SimpleSymbol) new SimpleSymbol("add-to-components").readResolve();
    static final SimpleSymbol Lit110 = (SimpleSymbol) new SimpleSymbol("add-to-events").readResolve();
    static final SimpleSymbol Lit109 = (SimpleSymbol) new SimpleSymbol("add-to-global-var-environment").readResolve();
    static final SimpleSymbol Lit108 = (SimpleSymbol) new SimpleSymbol("is-bound-in-form-environment").readResolve();
    static final SimpleSymbol Lit107 = (SimpleSymbol) new SimpleSymbol("lookup-in-form-environment").readResolve();
    static final SimpleSymbol Lit106 = (SimpleSymbol) new SimpleSymbol("add-to-form-environment").readResolve();
    static final SimpleSymbol Lit105 = (SimpleSymbol) new SimpleSymbol("android-log-form").readResolve();
    static final FString Lit104 = new FString("com.google.appinventor.components.runtime.TinyDB");
    static final FString Lit103 = new FString("com.google.appinventor.components.runtime.TinyDB");
    static final SimpleSymbol Lit102 = (SimpleSymbol) new SimpleSymbol("TinyWebDB3$GotValue").readResolve();

    static {
        SimpleSymbol simpleSymbol = (SimpleSymbol) new SimpleSymbol(PropertyTypeConstants.PROPERTY_TYPE_TEXT).readResolve();
        Lit9 = simpleSymbol;
        SimpleSymbol simpleSymbol2 = (SimpleSymbol) new SimpleSymbol("number").readResolve();
        Lit7 = simpleSymbol2;
        Lit101 = PairWithPosition.make(simpleSymbol, PairWithPosition.make(simpleSymbol2, PairWithPosition.make(Lit7, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\1515225807420_0.2955864762492916-0\\youngandroidproject\\..\\src\\appinventor\\ai_test\\SXTSstu\\Screen2.yail", 463390), "C:\\Users\\LEO\\AppData\\Local\\Temp\\1515225807420_0.2955864762492916-0\\youngandroidproject\\..\\src\\appinventor\\ai_test\\SXTSstu\\Screen2.yail", 463383), "C:\\Users\\LEO\\AppData\\Local\\Temp\\1515225807420_0.2955864762492916-0\\youngandroidproject\\..\\src\\appinventor\\ai_test\\SXTSstu\\Screen2.yail", 463377);
        Lit100 = PairWithPosition.make(Lit9, PairWithPosition.make(Lit9, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\1515225807420_0.2955864762492916-0\\youngandroidproject\\..\\src\\appinventor\\ai_test\\SXTSstu\\Screen2.yail", 463352), "C:\\Users\\LEO\\AppData\\Local\\Temp\\1515225807420_0.2955864762492916-0\\youngandroidproject\\..\\src\\appinventor\\ai_test\\SXTSstu\\Screen2.yail", 463346);
        Lit99 = PairWithPosition.make(Lit9, PairWithPosition.make(Lit117, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\1515225807420_0.2955864762492916-0\\youngandroidproject\\..\\src\\appinventor\\ai_test\\SXTSstu\\Screen2.yail", 463337), "C:\\Users\\LEO\\AppData\\Local\\Temp\\1515225807420_0.2955864762492916-0\\youngandroidproject\\..\\src\\appinventor\\ai_test\\SXTSstu\\Screen2.yail", 463331);
        Lit98 = (SimpleSymbol) new SimpleSymbol(PropertyTypeConstants.PROPERTY_TYPE_BOOLEAN).readResolve();
        Lit97 = (SimpleSymbol) new SimpleSymbol("Enabled").readResolve();
        Lit96 = PairWithPosition.make(Lit117, PairWithPosition.make(Lit117, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\1515225807420_0.2955864762492916-0\\youngandroidproject\\..\\src\\appinventor\\ai_test\\SXTSstu\\Screen2.yail", 462951), "C:\\Users\\LEO\\AppData\\Local\\Temp\\1515225807420_0.2955864762492916-0\\youngandroidproject\\..\\src\\appinventor\\ai_test\\SXTSstu\\Screen2.yail", 462946);
        Lit95 = new FString("com.google.appinventor.components.runtime.TinyWebDB");
        Lit94 = new FString("com.google.appinventor.components.runtime.TinyWebDB");
        Lit93 = (SimpleSymbol) new SimpleSymbol("TinyWebDB2$GotValue").readResolve();
        Lit92 = PairWithPosition.make(Lit9, PairWithPosition.make(Lit7, PairWithPosition.make(Lit7, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\1515225807420_0.2955864762492916-0\\youngandroidproject\\..\\src\\appinventor\\ai_test\\SXTSstu\\Screen2.yail", 426997), "C:\\Users\\LEO\\AppData\\Local\\Temp\\1515225807420_0.2955864762492916-0\\youngandroidproject\\..\\src\\appinventor\\ai_test\\SXTSstu\\Screen2.yail", 426990), "C:\\Users\\LEO\\AppData\\Local\\Temp\\1515225807420_0.2955864762492916-0\\youngandroidproject\\..\\src\\appinventor\\ai_test\\SXTSstu\\Screen2.yail", 426984);
        Lit91 = PairWithPosition.make(Lit7, PairWithPosition.make(Lit7, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\1515225807420_0.2955864762492916-0\\youngandroidproject\\..\\src\\appinventor\\ai_test\\SXTSstu\\Screen2.yail", 426757), "C:\\Users\\LEO\\AppData\\Local\\Temp\\1515225807420_0.2955864762492916-0\\youngandroidproject\\..\\src\\appinventor\\ai_test\\SXTSstu\\Screen2.yail", 426749);
        Lit90 = PairWithPosition.make(Lit9, PairWithPosition.make(Lit117, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\1515225807420_0.2955864762492916-0\\youngandroidproject\\..\\src\\appinventor\\ai_test\\SXTSstu\\Screen2.yail", 426638), "C:\\Users\\LEO\\AppData\\Local\\Temp\\1515225807420_0.2955864762492916-0\\youngandroidproject\\..\\src\\appinventor\\ai_test\\SXTSstu\\Screen2.yail", 426632);
        Lit89 = PairWithPosition.make(Lit7, PairWithPosition.make(Lit7, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\1515225807420_0.2955864762492916-0\\youngandroidproject\\..\\src\\appinventor\\ai_test\\SXTSstu\\Screen2.yail", 426616), "C:\\Users\\LEO\\AppData\\Local\\Temp\\1515225807420_0.2955864762492916-0\\youngandroidproject\\..\\src\\appinventor\\ai_test\\SXTSstu\\Screen2.yail", 426608);
        Lit88 = PairWithPosition.make(Lit9, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\1515225807420_0.2955864762492916-0\\youngandroidproject\\..\\src\\appinventor\\ai_test\\SXTSstu\\Screen2.yail", 426423);
        Lit87 = PairWithPosition.make(Lit9, PairWithPosition.make(Lit9, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\1515225807420_0.2955864762492916-0\\youngandroidproject\\..\\src\\appinventor\\ai_test\\SXTSstu\\Screen2.yail", 426406), "C:\\Users\\LEO\\AppData\\Local\\Temp\\1515225807420_0.2955864762492916-0\\youngandroidproject\\..\\src\\appinventor\\ai_test\\SXTSstu\\Screen2.yail", 426400);
        Lit86 = PairWithPosition.make(Lit7, PairWithPosition.make(Lit7, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\1515225807420_0.2955864762492916-0\\youngandroidproject\\..\\src\\appinventor\\ai_test\\SXTSstu\\Screen2.yail", 426183), "C:\\Users\\LEO\\AppData\\Local\\Temp\\1515225807420_0.2955864762492916-0\\youngandroidproject\\..\\src\\appinventor\\ai_test\\SXTSstu\\Screen2.yail", 426175);
        Lit85 = IntNum.make(10);
        Lit84 = PairWithPosition.make(Lit117, PairWithPosition.make(Lit117, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\1515225807420_0.2955864762492916-0\\youngandroidproject\\..\\src\\appinventor\\ai_test\\SXTSstu\\Screen2.yail", 426072), "C:\\Users\\LEO\\AppData\\Local\\Temp\\1515225807420_0.2955864762492916-0\\youngandroidproject\\..\\src\\appinventor\\ai_test\\SXTSstu\\Screen2.yail", 426067);
        Lit83 = new FString("com.google.appinventor.components.runtime.TinyWebDB");
        Lit82 = new FString("com.google.appinventor.components.runtime.TinyWebDB");
        Lit81 = (SimpleSymbol) new SimpleSymbol("GotValue").readResolve();
        Lit80 = (SimpleSymbol) new SimpleSymbol("TinyWebDB1$GotValue").readResolve();
        Lit79 = PairWithPosition.make(Lit9, PairWithPosition.make(Lit117, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\1515225807420_0.2955864762492916-0\\youngandroidproject\\..\\src\\appinventor\\ai_test\\SXTSstu\\Screen2.yail", 389860), "C:\\Users\\LEO\\AppData\\Local\\Temp\\1515225807420_0.2955864762492916-0\\youngandroidproject\\..\\src\\appinventor\\ai_test\\SXTSstu\\Screen2.yail", 389854);
        Lit78 = (SimpleSymbol) new SimpleSymbol("TinyDB1").readResolve();
        Lit77 = PairWithPosition.make(Lit9, PairWithPosition.make(Lit7, PairWithPosition.make(Lit7, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\1515225807420_0.2955864762492916-0\\youngandroidproject\\..\\src\\appinventor\\ai_test\\SXTSstu\\Screen2.yail", 389744), "C:\\Users\\LEO\\AppData\\Local\\Temp\\1515225807420_0.2955864762492916-0\\youngandroidproject\\..\\src\\appinventor\\ai_test\\SXTSstu\\Screen2.yail", 389737), "C:\\Users\\LEO\\AppData\\Local\\Temp\\1515225807420_0.2955864762492916-0\\youngandroidproject\\..\\src\\appinventor\\ai_test\\SXTSstu\\Screen2.yail", 389731);
        Lit76 = IntNum.make(30);
        Lit75 = IntNum.make(50);
        Lit74 = (SimpleSymbol) new SimpleSymbol("DrawText").readResolve();
        Lit73 = PairWithPosition.make(Lit9, PairWithPosition.make(Lit117, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\1515225807420_0.2955864762492916-0\\youngandroidproject\\..\\src\\appinventor\\ai_test\\SXTSstu\\Screen2.yail", 389571), "C:\\Users\\LEO\\AppData\\Local\\Temp\\1515225807420_0.2955864762492916-0\\youngandroidproject\\..\\src\\appinventor\\ai_test\\SXTSstu\\Screen2.yail", 389565);
        Lit72 = PairWithPosition.make(Lit9, PairWithPosition.make(Lit9, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\1515225807420_0.2955864762492916-0\\youngandroidproject\\..\\src\\appinventor\\ai_test\\SXTSstu\\Screen2.yail", 389548), "C:\\Users\\LEO\\AppData\\Local\\Temp\\1515225807420_0.2955864762492916-0\\youngandroidproject\\..\\src\\appinventor\\ai_test\\SXTSstu\\Screen2.yail", 389542);
        Lit71 = PairWithPosition.make(Lit9, PairWithPosition.make(Lit9, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\1515225807420_0.2955864762492916-0\\youngandroidproject\\..\\src\\appinventor\\ai_test\\SXTSstu\\Screen2.yail", 389524), "C:\\Users\\LEO\\AppData\\Local\\Temp\\1515225807420_0.2955864762492916-0\\youngandroidproject\\..\\src\\appinventor\\ai_test\\SXTSstu\\Screen2.yail", 389518);
        Lit70 = (SimpleSymbol) new SimpleSymbol("Clear").readResolve();
        Lit69 = new FString("com.google.appinventor.components.runtime.TinyWebDB");
        Lit68 = (SimpleSymbol) new SimpleSymbol("ServiceURL").readResolve();
        Lit67 = (SimpleSymbol) new SimpleSymbol("TinyWebDB1").readResolve();
        Lit66 = new FString("com.google.appinventor.components.runtime.TinyWebDB");
        Lit65 = new FString("com.google.appinventor.components.runtime.Canvas");
        Lit64 = IntNum.make(250);
        Lit63 = (SimpleSymbol) new SimpleSymbol("Height").readResolve();
        Lit62 = IntNum.make(315);
        Lit61 = (SimpleSymbol) new SimpleSymbol("Width").readResolve();
        Lit60 = IntNum.make((int) Component.COLOR_NONE);
        Lit59 = (SimpleSymbol) new SimpleSymbol("Canvas1").readResolve();
        Lit58 = new FString("com.google.appinventor.components.runtime.Canvas");
        Lit57 = (SimpleSymbol) new SimpleSymbol("Click").readResolve();
        Lit56 = (SimpleSymbol) new SimpleSymbol("Button1$Click").readResolve();
        Lit55 = PairWithPosition.make(Lit9, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\1515225807420_0.2955864762492916-0\\youngandroidproject\\..\\src\\appinventor\\ai_test\\SXTSstu\\Screen2.yail", 307829);
        Lit54 = PairWithPosition.make(Lit9, PairWithPosition.make(Lit117, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\1515225807420_0.2955864762492916-0\\youngandroidproject\\..\\src\\appinventor\\ai_test\\SXTSstu\\Screen2.yail", 307675), "C:\\Users\\LEO\\AppData\\Local\\Temp\\1515225807420_0.2955864762492916-0\\youngandroidproject\\..\\src\\appinventor\\ai_test\\SXTSstu\\Screen2.yail", 307669);
        Lit53 = (SimpleSymbol) new SimpleSymbol("StoreValue").readResolve();
        Lit52 = PairWithPosition.make(Lit9, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\1515225807420_0.2955864762492916-0\\youngandroidproject\\..\\src\\appinventor\\ai_test\\SXTSstu\\Screen2.yail", 307508);
        Lit51 = PairWithPosition.make(Lit9, PairWithPosition.make(Lit9, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\1515225807420_0.2955864762492916-0\\youngandroidproject\\..\\src\\appinventor\\ai_test\\SXTSstu\\Screen2.yail", 307491), "C:\\Users\\LEO\\AppData\\Local\\Temp\\1515225807420_0.2955864762492916-0\\youngandroidproject\\..\\src\\appinventor\\ai_test\\SXTSstu\\Screen2.yail", 307485);
        Lit50 = PairWithPosition.make(Lit9, PairWithPosition.make(Lit9, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\1515225807420_0.2955864762492916-0\\youngandroidproject\\..\\src\\appinventor\\ai_test\\SXTSstu\\Screen2.yail", 307467), "C:\\Users\\LEO\\AppData\\Local\\Temp\\1515225807420_0.2955864762492916-0\\youngandroidproject\\..\\src\\appinventor\\ai_test\\SXTSstu\\Screen2.yail", 307461);
        Lit49 = (SimpleSymbol) new SimpleSymbol("Selection").readResolve();
        Lit48 = (SimpleSymbol) new SimpleSymbol("TinyWebDB2").readResolve();
        Lit47 = new FString("com.google.appinventor.components.runtime.Button");
        Lit46 = IntNum.make(new int[]{-1});
        Lit45 = (SimpleSymbol) new SimpleSymbol("TextColor").readResolve();
        Lit44 = IntNum.make(1);
        Lit43 = (SimpleSymbol) new SimpleSymbol("Shape").readResolve();
        Lit42 = IntNum.make(new int[]{Component.COLOR_GREEN});
        Lit41 = (SimpleSymbol) new SimpleSymbol("BackgroundColor").readResolve();
        Lit40 = (SimpleSymbol) new SimpleSymbol("Button1").readResolve();
        Lit39 = new FString("com.google.appinventor.components.runtime.Button");
        Lit38 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
        Lit37 = (SimpleSymbol) new SimpleSymbol("HorizontalArrangement4").readResolve();
        Lit36 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
        Lit35 = new FString("com.google.appinventor.components.runtime.Spinner");
        Lit34 = (SimpleSymbol) new SimpleSymbol("Spinner2").readResolve();
        Lit33 = new FString("com.google.appinventor.components.runtime.Spinner");
        Lit32 = new FString("com.google.appinventor.components.runtime.Label");
        Lit31 = (SimpleSymbol) new SimpleSymbol("Label2").readResolve();
        Lit30 = new FString("com.google.appinventor.components.runtime.Label");
        Lit29 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
        Lit28 = (SimpleSymbol) new SimpleSymbol("HorizontalArrangement2").readResolve();
        Lit27 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
        Lit26 = new FString("com.google.appinventor.components.runtime.Spinner");
        Lit25 = (SimpleSymbol) new SimpleSymbol("ElementsFromString").readResolve();
        Lit24 = (SimpleSymbol) new SimpleSymbol("Spinner1").readResolve();
        Lit23 = new FString("com.google.appinventor.components.runtime.Spinner");
        Lit22 = new FString("com.google.appinventor.components.runtime.Label");
        Lit21 = (SimpleSymbol) new SimpleSymbol("Text").readResolve();
        Lit20 = (SimpleSymbol) new SimpleSymbol("Label1").readResolve();
        Lit19 = new FString("com.google.appinventor.components.runtime.Label");
        Lit18 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
        Lit17 = (SimpleSymbol) new SimpleSymbol("HorizontalArrangement1").readResolve();
        Lit16 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
        Lit15 = (SimpleSymbol) new SimpleSymbol("Initialize").readResolve();
        Lit14 = (SimpleSymbol) new SimpleSymbol("Screen2$Initialize").readResolve();
        Lit13 = PairWithPosition.make(Lit9, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\1515225807420_0.2955864762492916-0\\youngandroidproject\\..\\src\\appinventor\\ai_test\\SXTSstu\\Screen2.yail", 69786);
        Lit12 = (SimpleSymbol) new SimpleSymbol("GetValue").readResolve();
        Lit11 = (SimpleSymbol) new SimpleSymbol("TinyWebDB3").readResolve();
        Lit10 = (SimpleSymbol) new SimpleSymbol("Title").readResolve();
        Lit8 = (SimpleSymbol) new SimpleSymbol("BackgroundImage").readResolve();
        Lit6 = IntNum.make(3);
        Lit5 = (SimpleSymbol) new SimpleSymbol("AlignHorizontal").readResolve();
        Lit4 = IntNum.make(0);
        Lit3 = (SimpleSymbol) new SimpleSymbol("g$done").readResolve();
        Lit2 = (SimpleSymbol) new SimpleSymbol("*the-null-value*").readResolve();
        Lit1 = (SimpleSymbol) new SimpleSymbol("getMessage").readResolve();
        Lit0 = (SimpleSymbol) new SimpleSymbol("Screen2").readResolve();
    }

    public Screen2() {
        ModuleInfo.register(this);
        frame frameVar = new frame();
        frameVar.$main = this;
        this.android$Mnlog$Mnform = new ModuleMethod(frameVar, 1, Lit105, 4097);
        this.add$Mnto$Mnform$Mnenvironment = new ModuleMethod(frameVar, 2, Lit106, 8194);
        this.lookup$Mnin$Mnform$Mnenvironment = new ModuleMethod(frameVar, 3, Lit107, 8193);
        this.is$Mnbound$Mnin$Mnform$Mnenvironment = new ModuleMethod(frameVar, 5, Lit108, 4097);
        this.add$Mnto$Mnglobal$Mnvar$Mnenvironment = new ModuleMethod(frameVar, 6, Lit109, 8194);
        this.add$Mnto$Mnevents = new ModuleMethod(frameVar, 7, Lit110, 8194);
        this.add$Mnto$Mncomponents = new ModuleMethod(frameVar, 8, Lit111, 16388);
        this.add$Mnto$Mnglobal$Mnvars = new ModuleMethod(frameVar, 9, Lit112, 8194);
        this.add$Mnto$Mnform$Mndo$Mnafter$Mncreation = new ModuleMethod(frameVar, 10, Lit113, 4097);
        this.send$Mnerror = new ModuleMethod(frameVar, 11, Lit114, 4097);
        this.process$Mnexception = new ModuleMethod(frameVar, 12, "process-exception", 4097);
        this.dispatchEvent = new ModuleMethod(frameVar, 13, Lit115, 16388);
        this.lookup$Mnhandler = new ModuleMethod(frameVar, 14, Lit116, 8194);
        ModuleMethod moduleMethod = new ModuleMethod(frameVar, 15, null, 0);
        moduleMethod.setProperty("source-location", "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm:541");
        lambda$Fn1 = moduleMethod;
        this.$define = new ModuleMethod(frameVar, 16, "$define", 0);
        lambda$Fn2 = new ModuleMethod(frameVar, 17, null, 0);
        lambda$Fn3 = new ModuleMethod(frameVar, 18, null, 0);
        this.Screen2$Initialize = new ModuleMethod(frameVar, 19, Lit14, 0);
        lambda$Fn4 = new ModuleMethod(frameVar, 20, null, 0);
        lambda$Fn5 = new ModuleMethod(frameVar, 21, null, 0);
        lambda$Fn6 = new ModuleMethod(frameVar, 22, null, 0);
        lambda$Fn7 = new ModuleMethod(frameVar, 23, null, 0);
        lambda$Fn8 = new ModuleMethod(frameVar, 24, null, 0);
        lambda$Fn9 = new ModuleMethod(frameVar, 25, null, 0);
        lambda$Fn10 = new ModuleMethod(frameVar, 26, null, 0);
        lambda$Fn11 = new ModuleMethod(frameVar, 27, null, 0);
        lambda$Fn12 = new ModuleMethod(frameVar, 28, null, 0);
        lambda$Fn13 = new ModuleMethod(frameVar, 29, null, 0);
        this.Button1$Click = new ModuleMethod(frameVar, 30, Lit56, 0);
        lambda$Fn14 = new ModuleMethod(frameVar, 31, null, 0);
        lambda$Fn15 = new ModuleMethod(frameVar, 32, null, 0);
        lambda$Fn16 = new ModuleMethod(frameVar, 33, null, 0);
        lambda$Fn17 = new ModuleMethod(frameVar, 34, null, 0);
        this.TinyWebDB1$GotValue = new ModuleMethod(frameVar, 35, Lit80, 8194);
        lambda$Fn18 = new ModuleMethod(frameVar, 36, null, 0);
        lambda$Fn19 = new ModuleMethod(frameVar, 37, null, 0);
        this.TinyWebDB2$GotValue = new ModuleMethod(frameVar, 38, Lit93, 8194);
        lambda$Fn20 = new ModuleMethod(frameVar, 39, null, 0);
        lambda$Fn21 = new ModuleMethod(frameVar, 40, null, 0);
        this.TinyWebDB3$GotValue = new ModuleMethod(frameVar, 41, Lit102, 8194);
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
            Screen2 = null;
            this.form$Mnname$Mnsymbol = Lit0;
            this.events$Mnto$Mnregister = LList.Empty;
            this.components$Mnto$Mncreate = LList.Empty;
            this.global$Mnvars$Mnto$Mncreate = LList.Empty;
            this.form$Mndo$Mnafter$Mncreation = LList.Empty;
            Object find2 = require.find("com.google.youngandroid.runtime");
            try {
                ((Runnable) find2).run();
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(runtime.addGlobalVarToCurrentFormEnvironment(Lit3, Lit4), $result);
                } else {
                    addToGlobalVars(Lit3, lambda$Fn2);
                }
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    runtime.setAndCoerceProperty$Ex(Lit0, Lit5, Lit6, Lit7);
                    runtime.setAndCoerceProperty$Ex(Lit0, Lit8, "timgQMU1Y2E8.bmp", Lit9);
                    Values.writeValues(runtime.setAndCoerceProperty$Ex(Lit0, Lit10, "Screen2", Lit9), $result);
                } else {
                    addToFormDoAfterCreation(new Promise(lambda$Fn3));
                }
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    runtime.addToCurrentFormEnvironment(Lit14, this.Screen2$Initialize);
                } else {
                    addToFormEnvironment(Lit14, this.Screen2$Initialize);
                }
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    EventDispatcher.registerEventForDelegation((HandlesEventDispatching) runtime.$Stthis$Mnform$St, "Screen2", "Initialize");
                } else {
                    addToEvents(Lit0, Lit15);
                }
                this.HorizontalArrangement1 = null;
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit16, Lit17, Boolean.FALSE), $result);
                } else {
                    addToComponents(Lit0, Lit18, Lit17, Boolean.FALSE);
                }
                this.Label1 = null;
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(runtime.addComponentWithinRepl(Lit17, Lit19, Lit20, lambda$Fn4), $result);
                } else {
                    addToComponents(Lit17, Lit22, Lit20, lambda$Fn5);
                }
                this.Spinner1 = null;
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(runtime.addComponentWithinRepl(Lit17, Lit23, Lit24, lambda$Fn6), $result);
                } else {
                    addToComponents(Lit17, Lit26, Lit24, lambda$Fn7);
                }
                this.HorizontalArrangement2 = null;
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit27, Lit28, Boolean.FALSE), $result);
                } else {
                    addToComponents(Lit0, Lit29, Lit28, Boolean.FALSE);
                }
                this.Label2 = null;
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(runtime.addComponentWithinRepl(Lit28, Lit30, Lit31, lambda$Fn8), $result);
                } else {
                    addToComponents(Lit28, Lit32, Lit31, lambda$Fn9);
                }
                this.Spinner2 = null;
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(runtime.addComponentWithinRepl(Lit28, Lit33, Lit34, lambda$Fn10), $result);
                } else {
                    addToComponents(Lit28, Lit35, Lit34, lambda$Fn11);
                }
                this.HorizontalArrangement4 = null;
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit36, Lit37, Boolean.FALSE), $result);
                } else {
                    addToComponents(Lit0, Lit38, Lit37, Boolean.FALSE);
                }
                this.Button1 = null;
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(runtime.addComponentWithinRepl(Lit37, Lit39, Lit40, lambda$Fn12), $result);
                } else {
                    addToComponents(Lit37, Lit47, Lit40, lambda$Fn13);
                }
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    runtime.addToCurrentFormEnvironment(Lit56, this.Button1$Click);
                } else {
                    addToFormEnvironment(Lit56, this.Button1$Click);
                }
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    EventDispatcher.registerEventForDelegation((HandlesEventDispatching) runtime.$Stthis$Mnform$St, "Button1", "Click");
                } else {
                    addToEvents(Lit40, Lit57);
                }
                this.Canvas1 = null;
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit58, Lit59, lambda$Fn14), $result);
                } else {
                    addToComponents(Lit0, Lit65, Lit59, lambda$Fn15);
                }
                this.TinyWebDB1 = null;
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit66, Lit67, lambda$Fn16), $result);
                } else {
                    addToComponents(Lit0, Lit69, Lit67, lambda$Fn17);
                }
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    runtime.addToCurrentFormEnvironment(Lit80, this.TinyWebDB1$GotValue);
                } else {
                    addToFormEnvironment(Lit80, this.TinyWebDB1$GotValue);
                }
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    EventDispatcher.registerEventForDelegation((HandlesEventDispatching) runtime.$Stthis$Mnform$St, "TinyWebDB1", "GotValue");
                } else {
                    addToEvents(Lit67, Lit81);
                }
                this.TinyWebDB2 = null;
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit82, Lit48, lambda$Fn18), $result);
                } else {
                    addToComponents(Lit0, Lit83, Lit48, lambda$Fn19);
                }
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    runtime.addToCurrentFormEnvironment(Lit93, this.TinyWebDB2$GotValue);
                } else {
                    addToFormEnvironment(Lit93, this.TinyWebDB2$GotValue);
                }
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    EventDispatcher.registerEventForDelegation((HandlesEventDispatching) runtime.$Stthis$Mnform$St, "TinyWebDB2", "GotValue");
                } else {
                    addToEvents(Lit48, Lit81);
                }
                this.TinyWebDB3 = null;
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit94, Lit11, lambda$Fn20), $result);
                } else {
                    addToComponents(Lit0, Lit95, Lit11, lambda$Fn21);
                }
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    runtime.addToCurrentFormEnvironment(Lit102, this.TinyWebDB3$GotValue);
                } else {
                    addToFormEnvironment(Lit102, this.TinyWebDB3$GotValue);
                }
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    EventDispatcher.registerEventForDelegation((HandlesEventDispatching) runtime.$Stthis$Mnform$St, "TinyWebDB3", "GotValue");
                } else {
                    addToEvents(Lit11, Lit81);
                }
                this.TinyDB1 = null;
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(runtime.addComponentWithinRepl(Lit0, Lit103, Lit78, Boolean.FALSE), $result);
                } else {
                    addToComponents(Lit0, Lit104, Lit78, Boolean.FALSE);
                }
                runtime.initRuntime();
            } catch (ClassCastException e) {
                throw new WrongType(e, "java.lang.Runnable.run()", 1, find2);
            }
        } catch (ClassCastException e2) {
            throw new WrongType(e2, "java.lang.Runnable.run()", 1, find);
        }
    }

    static IntNum lambda3() {
        return Lit4;
    }

    static Object lambda4() {
        runtime.setAndCoerceProperty$Ex(Lit0, Lit5, Lit6, Lit7);
        runtime.setAndCoerceProperty$Ex(Lit0, Lit8, "timgQMU1Y2E8.bmp", Lit9);
        return runtime.setAndCoerceProperty$Ex(Lit0, Lit10, "Screen2", Lit9);
    }

    public Object Screen2$Initialize() {
        runtime.setThisForm();
        return runtime.callComponentMethod(Lit11, Lit12, LList.list1(runtime.callYailPrimitive(runtime.get$Mnstart$Mnvalue, LList.Empty, LList.Empty, "get start value")), Lit13);
    }

    static Object lambda5() {
        return runtime.setAndCoerceProperty$Ex(Lit20, Lit21, "学科", Lit9);
    }

    static Object lambda6() {
        return runtime.setAndCoerceProperty$Ex(Lit20, Lit21, "学科", Lit9);
    }

    static Object lambda7() {
        return runtime.setAndCoerceProperty$Ex(Lit24, Lit25, "语文,政治,地理,英语", Lit9);
    }

    static Object lambda8() {
        return runtime.setAndCoerceProperty$Ex(Lit24, Lit25, "语文,政治,地理,英语", Lit9);
    }

    static Object lambda10() {
        return runtime.setAndCoerceProperty$Ex(Lit31, Lit21, "场次", Lit9);
    }

    static Object lambda9() {
        return runtime.setAndCoerceProperty$Ex(Lit31, Lit21, "场次", Lit9);
    }

    static Object lambda11() {
        return runtime.setAndCoerceProperty$Ex(Lit34, Lit25, "第一场,第二场,第三场,第四场", Lit9);
    }

    static Object lambda12() {
        return runtime.setAndCoerceProperty$Ex(Lit34, Lit25, "第一场,第二场,第三场,第四场", Lit9);
    }

    static Object lambda13() {
        runtime.setAndCoerceProperty$Ex(Lit40, Lit41, Lit42, Lit7);
        runtime.setAndCoerceProperty$Ex(Lit40, Lit43, Lit44, Lit7);
        runtime.setAndCoerceProperty$Ex(Lit40, Lit21, "报名", Lit9);
        return runtime.setAndCoerceProperty$Ex(Lit40, Lit45, Lit46, Lit7);
    }

    static Object lambda14() {
        runtime.setAndCoerceProperty$Ex(Lit40, Lit41, Lit42, Lit7);
        runtime.setAndCoerceProperty$Ex(Lit40, Lit43, Lit44, Lit7);
        runtime.setAndCoerceProperty$Ex(Lit40, Lit21, "报名", Lit9);
        return runtime.setAndCoerceProperty$Ex(Lit40, Lit45, Lit46, Lit7);
    }

    public Object Button1$Click() {
        runtime.setThisForm();
        runtime.callComponentMethod(Lit48, Lit12, LList.list1(runtime.callYailPrimitive(strings.string$Mnappend, LList.list2("num", runtime.callYailPrimitive(strings.string$Mnappend, LList.list2(runtime.getProperty$1(Lit24, Lit49), runtime.getProperty$1(Lit34, Lit49)), Lit50, "join")), Lit51, "join")), Lit52);
        runtime.callComponentMethod(Lit11, Lit53, LList.list2(runtime.callYailPrimitive(runtime.get$Mnstart$Mnvalue, LList.Empty, LList.Empty, "get start value"), Lit44), Lit54);
        return runtime.callComponentMethod(Lit11, Lit12, LList.list1(runtime.callYailPrimitive(runtime.get$Mnstart$Mnvalue, LList.Empty, LList.Empty, "get start value")), Lit55);
    }

    static Object lambda15() {
        runtime.setAndCoerceProperty$Ex(Lit59, Lit41, Lit60, Lit7);
        runtime.setAndCoerceProperty$Ex(Lit59, Lit61, Lit62, Lit7);
        return runtime.setAndCoerceProperty$Ex(Lit59, Lit63, Lit64, Lit7);
    }

    static Object lambda16() {
        runtime.setAndCoerceProperty$Ex(Lit59, Lit41, Lit60, Lit7);
        runtime.setAndCoerceProperty$Ex(Lit59, Lit61, Lit62, Lit7);
        return runtime.setAndCoerceProperty$Ex(Lit59, Lit63, Lit64, Lit7);
    }

    static Object lambda17() {
        return runtime.setAndCoerceProperty$Ex(Lit67, Lit68, "http://tinywebdb.gzjkw.net/db.php?user=GLAdt&pw=GC&v=1", Lit9);
    }

    static Object lambda18() {
        return runtime.setAndCoerceProperty$Ex(Lit67, Lit68, "http://tinywebdb.gzjkw.net/db.php?user=GLAdt&pw=GC&v=1", Lit9);
    }

    public Object TinyWebDB1$GotValue(Object $tagFromWebDB, Object $valueFromWebDB) {
        Object $tagFromWebDB2 = runtime.sanitizeComponentData($tagFromWebDB);
        Object $valueFromWebDB2 = runtime.sanitizeComponentData($valueFromWebDB);
        runtime.setThisForm();
        runtime.callComponentMethod(Lit59, Lit70, LList.Empty, LList.Empty);
        runtime.callComponentMethod(Lit67, Lit53, LList.list2($tagFromWebDB2, runtime.callYailPrimitive(strings.string$Mnappend, LList.list2($valueFromWebDB2, runtime.callYailPrimitive(strings.string$Mnappend, LList.list2(",", runtime.callYailPrimitive(runtime.get$Mnstart$Mnvalue, LList.Empty, LList.Empty, "get start value")), Lit71, "join")), Lit72, "join")), Lit73);
        runtime.callComponentMethod(Lit59, Lit74, LList.list3("恭喜报名成功        你是有慧眼的", Lit75, Lit76), Lit77);
        return runtime.callComponentMethod(Lit78, Lit53, LList.list2("dt", $tagFromWebDB2), Lit79);
    }

    static Object lambda19() {
        return runtime.setAndCoerceProperty$Ex(Lit48, Lit68, "http://tinywebdb.gzjkw.net/db.php?user=GLAdt&pw=GC&v=1", Lit9);
    }

    static Object lambda20() {
        return runtime.setAndCoerceProperty$Ex(Lit48, Lit68, "http://tinywebdb.gzjkw.net/db.php?user=GLAdt&pw=GC&v=1", Lit9);
    }

    public Object TinyWebDB2$GotValue(Object $tagFromWebDB, Object $valueFromWebDB) {
        Object $tagFromWebDB2 = runtime.sanitizeComponentData($tagFromWebDB);
        Object $valueFromWebDB2 = runtime.sanitizeComponentData($valueFromWebDB);
        runtime.setThisForm();
        if (runtime.callYailPrimitive(runtime.yail$Mnequal$Qu, LList.list2(runtime.lookupGlobalVarInCurrentFormEnvironment(Lit3, runtime.$Stthe$Mnnull$Mnvalue$St), Lit4), Lit84, "=") != Boolean.FALSE) {
            if (runtime.callYailPrimitive(Scheme.numLss, LList.list2($valueFromWebDB2, Lit85), Lit86, "<") != Boolean.FALSE) {
                runtime.callComponentMethod(Lit67, Lit12, LList.list1(runtime.callYailPrimitive(strings.string$Mnappend, LList.list2(runtime.getProperty$1(Lit24, Lit49), runtime.getProperty$1(Lit34, Lit49)), Lit87, "join")), Lit88);
                runtime.callComponentMethod(Lit48, Lit53, LList.list2($tagFromWebDB2, runtime.callYailPrimitive(AddOp.$Pl, LList.list2($valueFromWebDB2, Lit44), Lit89, "+")), Lit90);
                runtime.addGlobalVarToCurrentFormEnvironment(Lit3, Lit44);
            }
            if (runtime.callYailPrimitive(Scheme.numGEq, LList.list2($valueFromWebDB2, Lit85), Lit91, ">=") != Boolean.FALSE) {
                runtime.callComponentMethod(Lit59, Lit70, LList.Empty, LList.Empty);
                return runtime.callComponentMethod(Lit59, Lit74, LList.list3("人数已满     换一场试试吧", Lit75, Lit76), Lit92);
            }
            return Values.empty;
        }
        return Values.empty;
    }

    static Object lambda21() {
        return runtime.setAndCoerceProperty$Ex(Lit11, Lit68, "http://tinywebdb.gzjkw.net/db.php?user=GLAdt&pw=GC&v=1", Lit9);
    }

    static Object lambda22() {
        return runtime.setAndCoerceProperty$Ex(Lit11, Lit68, "http://tinywebdb.gzjkw.net/db.php?user=GLAdt&pw=GC&v=1", Lit9);
    }

    public Object TinyWebDB3$GotValue(Object $tagFromWebDB, Object $valueFromWebDB) {
        runtime.sanitizeComponentData($tagFromWebDB);
        Object $valueFromWebDB2 = runtime.sanitizeComponentData($valueFromWebDB);
        runtime.setThisForm();
        if (runtime.callYailPrimitive(runtime.yail$Mnequal$Qu, LList.list2($valueFromWebDB2, Lit44), Lit96, "=") != Boolean.FALSE) {
            runtime.setAndCoerceProperty$Ex(Lit40, Lit97, Boolean.FALSE, Lit98);
            runtime.callComponentMethod(Lit59, Lit70, LList.Empty, LList.Empty);
            runtime.callComponentMethod(Lit59, Lit74, LList.list3(runtime.callYailPrimitive(strings.string$Mnappend, LList.list2("您已报名", runtime.callComponentMethod(Lit78, Lit12, LList.list2("dt", "未知错误"), Lit99)), Lit100, "join"), Lit75, Lit76), Lit101);
            return runtime.addGlobalVarToCurrentFormEnvironment(Lit3, Lit44);
        }
        return Values.empty;
    }

    /* compiled from: Screen2.yail */
    /* loaded from: classes.dex */
    public class frame extends ModuleBody {
        Screen2 $main;

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
                    if (obj instanceof Screen2) {
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
                case 35:
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.proc = moduleMethod;
                    callContext.pc = 2;
                    return 0;
                case 38:
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.proc = moduleMethod;
                    callContext.pc = 2;
                    return 0;
                case 41:
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
        public int match4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4, CallContext callContext) {
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
                    if (obj instanceof Screen2) {
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
                                    return this.$main.dispatchEvent((Component) obj, (String) obj2, (String) obj3, (Object[]) obj4) ? Boolean.TRUE : Boolean.FALSE;
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
                case 35:
                    return this.$main.TinyWebDB1$GotValue(obj, obj2);
                case 38:
                    return this.$main.TinyWebDB2$GotValue(obj, obj2);
                case 41:
                    return this.$main.TinyWebDB3$GotValue(obj, obj2);
                default:
                    return super.apply2(moduleMethod, obj, obj2);
            }
        }

        @Override // gnu.expr.ModuleBody
        public Object apply0(ModuleMethod moduleMethod) {
            switch (moduleMethod.selector) {
                case 15:
                    return Screen2.lambda2();
                case 16:
                    this.$main.$define();
                    return Values.empty;
                case 17:
                    return Screen2.lambda3();
                case 18:
                    return Screen2.lambda4();
                case 19:
                    return this.$main.Screen2$Initialize();
                case 20:
                    return Screen2.lambda5();
                case 21:
                    return Screen2.lambda6();
                case 22:
                    return Screen2.lambda7();
                case 23:
                    return Screen2.lambda8();
                case 24:
                    return Screen2.lambda9();
                case 25:
                    return Screen2.lambda10();
                case 26:
                    return Screen2.lambda11();
                case 27:
                    return Screen2.lambda12();
                case 28:
                    return Screen2.lambda13();
                case 29:
                    return Screen2.lambda14();
                case 30:
                    return this.$main.Button1$Click();
                case 31:
                    return Screen2.lambda15();
                case 32:
                    return Screen2.lambda16();
                case 33:
                    return Screen2.lambda17();
                case 34:
                    return Screen2.lambda18();
                case 35:
                case 38:
                default:
                    return super.apply0(moduleMethod);
                case 36:
                    return Screen2.lambda19();
                case 37:
                    return Screen2.lambda20();
                case 39:
                    return Screen2.lambda21();
                case 40:
                    return Screen2.lambda22();
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
                case 31:
                    callContext.proc = moduleMethod;
                    callContext.pc = 0;
                    return 0;
                case 32:
                    callContext.proc = moduleMethod;
                    callContext.pc = 0;
                    return 0;
                case 33:
                    callContext.proc = moduleMethod;
                    callContext.pc = 0;
                    return 0;
                case 34:
                    callContext.proc = moduleMethod;
                    callContext.pc = 0;
                    return 0;
                case 35:
                case 38:
                default:
                    return super.match0(moduleMethod, callContext);
                case 36:
                    callContext.proc = moduleMethod;
                    callContext.pc = 0;
                    return 0;
                case 37:
                    callContext.proc = moduleMethod;
                    callContext.pc = 0;
                    return 0;
                case 39:
                    callContext.proc = moduleMethod;
                    callContext.pc = 0;
                    return 0;
                case 40:
                    callContext.proc = moduleMethod;
                    callContext.pc = 0;
                    return 0;
            }
        }
    }

    public void androidLogForm(Object message) {
    }

    public void addToFormEnvironment(Symbol name, Object object) {
        androidLogForm(Format.formatToString(0, "Adding ~A to env ~A with value ~A", name, this.form$Mnenvironment, object));
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
        androidLogForm(Format.formatToString(0, "Adding ~A to env ~A with value ~A", name, this.global$Mnvar$Mnenvironment, object));
        this.global$Mnvar$Mnenvironment.put(name, object);
    }

    public void addToEvents(Object component$Mnname, Object event$Mnname) {
        this.events$Mnto$Mnregister = lists.cons(lists.cons(component$Mnname, event$Mnname), this.events$Mnto$Mnregister);
    }

    public void addToComponents(Object container$Mnname, Object component$Mntype, Object component$Mnname, Object init$Mnthunk) {
        this.components$Mnto$Mncreate = lists.cons(LList.list4(container$Mnname, component$Mntype, component$Mnname, init$Mnthunk), this.components$Mnto$Mncreate);
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
            RuntimeErrorAlert.alert(this, apply1 == null ? null : apply1.toString(), ex instanceof YailRuntimeError ? ((YailRuntimeError) ex).getErrorType() : "Runtime Error", "End Application");
        } catch (ClassCastException e) {
            throw new WrongType(e, "com.google.appinventor.components.runtime.ReplApplication.reportError(java.lang.Throwable)", 1, ex);
        }
    }

    @Override // com.google.appinventor.components.runtime.Form, com.google.appinventor.components.runtime.HandlesEventDispatching
    public boolean dispatchEvent(Component componentObject, String registeredComponentName, String eventName, Object[] args) {
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
        return lookupInFormEnvironment(misc.string$To$Symbol(EventDispatcher.makeFullEventName(componentName == null ? null : componentName.toString(), eventName != null ? eventName.toString() : null)));
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
        Screen2 = this;
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
                        Object component$Mncontainer = lookupInFormEnvironment((Symbol) lists.car.apply1(component$Mninfo));
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
