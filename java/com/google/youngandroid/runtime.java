package com.google.youngandroid;

import android.content.Context;
import android.os.Handler;
import android.text.format.Formatter;
import com.google.appinventor.components.common.ComponentConstants;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.common.YaVersion;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.ComponentContainer;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.errors.YailRuntimeError;
import com.google.appinventor.components.runtime.util.CsvUtil;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.PropertyUtil;
import com.google.appinventor.components.runtime.util.RetValManager;
import com.google.appinventor.components.runtime.util.YailList;
import com.google.appinventor.components.runtime.util.YailNumberToString;
import gnu.bytecode.ClassType;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.expr.Special;
import gnu.kawa.functions.AddOp;
import gnu.kawa.functions.Apply;
import gnu.kawa.functions.Arithmetic;
import gnu.kawa.functions.BitwiseOp;
import gnu.kawa.functions.DivideOp;
import gnu.kawa.functions.Format;
import gnu.kawa.functions.GetNamedPart;
import gnu.kawa.functions.IsEqual;
import gnu.kawa.functions.MultiplyOp;
import gnu.kawa.lispexpr.LangObjType;
import gnu.kawa.lispexpr.LispLanguage;
import gnu.kawa.reflect.Invoke;
import gnu.kawa.reflect.SlotGet;
import gnu.kawa.reflect.SlotSet;
import gnu.kawa.xml.XDataType;
import gnu.lists.Consumer;
import gnu.lists.FString;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.Environment;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.DFloNum;
import gnu.math.DateTime;
import gnu.math.IntNum;
import gnu.math.Numeric;
import gnu.math.RealNum;
import gnu.text.Char;
import gnu.text.PrettyWriter;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;
import kawa.lang.Macro;
import kawa.lang.Quote;
import kawa.lang.SyntaxForms;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxRule;
import kawa.lang.SyntaxRules;
import kawa.lang.SyntaxTemplate;
import kawa.lang.TemplateScope;
import kawa.lib.lists;
import kawa.lib.misc;
import kawa.lib.numbers;
import kawa.lib.ports;
import kawa.lib.strings;
import kawa.lib.thread;
import kawa.standard.Scheme;
import kawa.standard.expt;
import kawa.standard.syntax_case;

/* compiled from: runtime1714060078409101109.scm */
/* loaded from: classes.dex */
public class runtime extends ModuleBody implements Runnable {
    public static final ModuleMethod $Pcset$Mnand$Mncoerce$Mnproperty$Ex;
    public static final ModuleMethod $Pcset$Mnsubform$Mnlayout$Mnproperty$Ex;
    public static Object $Stalpha$Mnopaque$St;
    public static Object $Stcolor$Mnalpha$Mnposition$St;
    public static Object $Stcolor$Mnblue$Mnposition$St;
    public static Object $Stcolor$Mngreen$Mnposition$St;
    public static Object $Stcolor$Mnred$Mnposition$St;
    public static Boolean $Stdebug$St;
    public static final ModuleMethod $Stformat$Mninexact$St;
    public static Object $Stinit$Mnthunk$Mnenvironment$St;
    public static String $Stjava$Mnexception$Mnmessage$St;
    public static final Macro $Stlist$Mnfor$Mnruntime$St;
    public static Object $Stmax$Mncolor$Mncomponent$St;
    public static Object $Stnon$Mncoercible$Mnvalue$St;
    public static IntNum $Stnum$Mnconnections$St;
    public static DFloNum $Stpi$St;
    public static Random $Strandom$Mnnumber$Mngenerator$St;
    public static IntNum $Strepl$Mnport$St;
    public static String $Strepl$Mnserver$Mnaddress$St;
    public static Boolean $Strun$Mntelnet$Mnrepl$St;
    public static Object $Sttest$Mnenvironment$St;
    public static Object $Sttest$Mnglobal$Mnvar$Mnenvironment$St;
    public static String $Stthe$Mnempty$Mnstring$Mnprinted$Mnrep$St;
    public static Object $Stthe$Mnnull$Mnvalue$Mnprinted$Mnrep$St;
    public static Object $Stthe$Mnnull$Mnvalue$St;
    public static Object $Stthis$Mnform$St;
    public static Object $Stthis$Mnis$Mnthe$Mnrepl$St;
    public static Object $Stui$Mnhandler$St;
    public static SimpleSymbol $Styail$Mnlist$St;
    public static final runtime $instance;
    public static final Class CsvUtil;
    public static final Class Double;
    public static final Class Float;
    public static final Class Integer;
    public static final Class JavaCollection;
    public static final Class JavaIterator;
    public static final Class KawaEnvironment;
    static final SimpleSymbol Lit0;
    static final SimpleSymbol Lit1;
    static final SimpleSymbol Lit10;
    static final SimpleSymbol Lit11;
    static final SimpleSymbol Lit12;
    static final SimpleSymbol Lit13;
    static final DFloNum Lit14;
    static final DFloNum Lit15;
    static final IntNum Lit16;
    static final IntNum Lit17;
    static final IntNum Lit18;
    static final IntNum Lit19;
    static final PairWithPosition Lit2;
    static final DFloNum Lit20;
    static final DFloNum Lit21;
    static final IntNum Lit22;
    static final DFloNum Lit23;
    static final DFloNum Lit24;
    static final IntNum Lit25;
    static final SimpleSymbol Lit26;
    static final SimpleSymbol Lit27;
    static final IntNum Lit28;
    static final IntNum Lit29;
    static final SimpleSymbol Lit3;
    static final IntNum Lit30;
    static final IntNum Lit31;
    static final IntNum Lit32;
    static final IntNum Lit33;
    static final IntNum Lit34;
    static final SimpleSymbol Lit35;
    static final SimpleSymbol Lit36;
    static final SimpleSymbol Lit37;
    static final SimpleSymbol Lit38;
    static final SyntaxPattern Lit39;
    static final SimpleSymbol Lit4;
    static final SyntaxTemplate Lit40;
    static final SimpleSymbol Lit41;
    static final SyntaxRules Lit42;
    static final SimpleSymbol Lit43;
    static final SimpleSymbol Lit44;
    static final SimpleSymbol Lit45;
    static final SimpleSymbol Lit46;
    static final SimpleSymbol Lit47;
    static final SimpleSymbol Lit48;
    static final SyntaxRules Lit49;
    static final SimpleSymbol Lit5;
    static final SimpleSymbol Lit50;
    static final SimpleSymbol Lit51;
    static final SimpleSymbol Lit52;
    static final SimpleSymbol Lit53;
    static final SimpleSymbol Lit54;
    static final SimpleSymbol Lit55;
    static final SimpleSymbol Lit56;
    static final SyntaxRules Lit57;
    static final SimpleSymbol Lit58;
    static final SyntaxRules Lit59;
    static final SimpleSymbol Lit6;
    static final SimpleSymbol Lit60;
    static final SyntaxRules Lit61;
    static final SimpleSymbol Lit62;
    static final SyntaxRules Lit63;
    static final SimpleSymbol Lit64;
    static final SyntaxRules Lit65;
    static final SimpleSymbol Lit66;
    static final SyntaxRules Lit67;
    static final SimpleSymbol Lit68;
    static final SyntaxRules Lit69;
    static final SimpleSymbol Lit7;
    static final SimpleSymbol Lit70;
    static final SyntaxRules Lit71;
    static final SimpleSymbol Lit72;
    static final SyntaxRules Lit73;
    static final SimpleSymbol Lit74;
    static final SimpleSymbol Lit75;
    static final SyntaxPattern Lit76;
    static final SyntaxTemplate Lit77;
    static final SimpleSymbol Lit78;
    static final SyntaxRules Lit79;
    static final SimpleSymbol Lit8;
    static final SimpleSymbol Lit80;
    static final SyntaxRules Lit81;
    static final SimpleSymbol Lit82;
    static final SyntaxPattern Lit83;
    static final SyntaxTemplate Lit84;
    static final SyntaxTemplate Lit85;
    static final SimpleSymbol Lit9;
    public static final Class Long;
    public static final Class Pattern;
    public static final Class Short;
    public static final ClassType SimpleForm;
    public static final Class String;
    public static final Class YailList;
    public static final Class YailNumberToString;
    public static final Class YailRuntimeError;
    public static final ModuleMethod acos$Mndegrees;
    public static final Macro add$Mncomponent;
    public static final ModuleMethod add$Mncomponent$Mnwithin$Mnrepl;
    public static final ModuleMethod add$Mnglobal$Mnvar$Mnto$Mncurrent$Mnform$Mnenvironment;
    public static final ModuleMethod add$Mninit$Mnthunk;
    public static final ModuleMethod add$Mnto$Mncurrent$Mnform$Mnenvironment;
    public static final ModuleMethod all$Mncoercible$Qu;
    public static final Macro and$Mndelayed;
    public static final ModuleMethod android$Mnlog;
    public static final ModuleMethod appinventor$Mnnumber$Mn$Grstring;
    public static final ModuleMethod array$Mn$Grlist;
    public static final ModuleMethod as$Mnnumber;
    public static final ModuleMethod asin$Mndegrees;
    public static final ModuleMethod atan$Mndegrees;
    public static final ModuleMethod atan2$Mndegrees;
    public static final ModuleMethod boolean$Mn$Grstring;
    public static final ModuleMethod call$MnInitialize$Mnof$Mncomponents;
    public static final ModuleMethod call$Mncomponent$Mnmethod;
    public static final ModuleMethod call$Mncomponent$Mntype$Mnmethod;
    public static final ModuleMethod call$Mnwith$Mncoerced$Mnargs;
    public static final ModuleMethod call$Mnyail$Mnprimitive;
    public static final ModuleMethod clarify;
    public static final ModuleMethod clarify1;
    public static final ModuleMethod clear$Mncurrent$Mnform;
    public static final ModuleMethod clear$Mninit$Mnthunks;
    public static Object clip$Mnto$Mnjava$Mnint$Mnrange;
    public static final ModuleMethod close$Mnapplication;
    public static final ModuleMethod close$Mnscreen;
    public static final ModuleMethod close$Mnscreen$Mnwith$Mnplain$Mntext;
    public static final ModuleMethod close$Mnscreen$Mnwith$Mnvalue;
    public static final ModuleMethod coerce$Mnarg;
    public static final ModuleMethod coerce$Mnargs;
    public static final ModuleMethod coerce$Mnto$Mnboolean;
    public static final ModuleMethod coerce$Mnto$Mncomponent;
    public static final ModuleMethod coerce$Mnto$Mncomponent$Mnand$Mnverify;
    public static final ModuleMethod coerce$Mnto$Mncomponent$Mnof$Mntype;
    public static final ModuleMethod coerce$Mnto$Mninstant;
    public static final ModuleMethod coerce$Mnto$Mnnumber;
    public static final ModuleMethod coerce$Mnto$Mnstring;
    public static final ModuleMethod coerce$Mnto$Mntext;
    public static final ModuleMethod coerce$Mnto$Mnyail$Mnlist;
    public static final ModuleMethod convert$Mnto$Mnstrings;
    public static final ModuleMethod cos$Mndegrees;
    public static final Macro def;
    public static final Macro define$Mnevent;
    public static final Macro define$Mnevent$Mnhelper;
    public static final Macro define$Mnform;
    public static final Macro define$Mnform$Mninternal;
    public static final Macro define$Mnrepl$Mnform;
    public static final ModuleMethod degrees$Mn$Grradians;
    public static final ModuleMethod degrees$Mn$Grradians$Mninternal;
    public static final ModuleMethod delete$Mnfrom$Mncurrent$Mnform$Mnenvironment;
    public static final Macro do$Mnafter$Mnform$Mncreation;
    public static final Macro foreach;
    public static final ModuleMethod format$Mnas$Mndecimal;
    public static final Macro forrange;
    public static final Macro gen$Mnevent$Mnname;
    public static final Macro gen$Mnsimple$Mncomponent$Mntype;
    public static final ModuleMethod generate$Mnruntime$Mntype$Mnerror;
    public static final Macro get$Mncomponent;
    public static Object get$Mndisplay$Mnrepresentation;
    public static final ModuleMethod get$Mninit$Mnthunk;
    public static final ModuleMethod get$Mnplain$Mnstart$Mntext;
    public static final ModuleMethod get$Mnproperty;
    public static final ModuleMethod get$Mnproperty$Mnand$Mncheck;
    public static final ModuleMethod get$Mnserver$Mnaddress$Mnfrom$Mnwifi;
    public static final ModuleMethod get$Mnstart$Mnvalue;
    public static final Macro get$Mnvar;
    static Numeric highest;
    public static final ModuleMethod in$Mnui;
    public static final ModuleMethod init$Mnruntime;
    public static final ModuleMethod insert$Mnyail$Mnlist$Mnheader;
    public static final ModuleMethod is$Mncoercible$Qu;
    public static final ModuleMethod is$Mnnumber$Qu;
    public static final ModuleMethod java$Mncollection$Mn$Grkawa$Mnlist;
    public static final ModuleMethod java$Mncollection$Mn$Gryail$Mnlist;
    public static final ModuleMethod kawa$Mnlist$Mn$Gryail$Mnlist;
    static final ModuleMethod lambda$Fn4;
    static final ModuleMethod lambda$Fn9;
    public static final Macro lexical$Mnvalue;
    public static final ModuleMethod lookup$Mncomponent;
    public static final ModuleMethod lookup$Mnglobal$Mnvar$Mnin$Mncurrent$Mnform$Mnenvironment;
    public static final ModuleMethod lookup$Mnin$Mncurrent$Mnform$Mnenvironment;
    static Numeric lowest;
    public static final ModuleMethod make$Mncolor;
    public static final ModuleMethod make$Mndisjunct;
    public static final ModuleMethod make$Mnexact$Mnyail$Mninteger;
    public static final ModuleMethod make$Mnyail$Mnlist;
    public static final ModuleMethod open$Mnanother$Mnscreen;
    public static final ModuleMethod open$Mnanother$Mnscreen$Mnwith$Mnstart$Mnvalue;
    public static final Macro or$Mndelayed;
    public static final ModuleMethod padded$Mnstring$Mn$Grnumber;
    public static final ModuleMethod pair$Mnok$Qu;
    public static final ModuleMethod process$Mnand$Mndelayed;
    public static final ModuleMethod process$Mnor$Mndelayed;
    public static final Macro process$Mnrepl$Mninput;
    public static final ModuleMethod radians$Mn$Grdegrees;
    public static final ModuleMethod radians$Mn$Grdegrees$Mninternal;
    public static final ModuleMethod random$Mnfraction;
    public static final ModuleMethod random$Mninteger;
    public static final ModuleMethod random$Mnset$Mnseed;
    public static final ModuleMethod remove$Mncomponent;
    public static final ModuleMethod rename$Mncomponent;
    public static final ModuleMethod rename$Mnin$Mncurrent$Mnform$Mnenvironment;
    public static final ModuleMethod reset$Mncurrent$Mnform$Mnenvironment;
    public static final ModuleMethod sanitize$Mnatomic;
    public static final ModuleMethod sanitize$Mncomponent$Mndata;
    public static final ModuleMethod send$Mnto$Mnblock;
    public static final ModuleMethod set$Mnand$Mncoerce$Mnproperty$Ex;
    public static final ModuleMethod set$Mnand$Mncoerce$Mnproperty$Mnand$Mncheck$Ex;
    public static final ModuleMethod set$Mnform$Mnname;
    public static final Macro set$Mnlexical$Ex;
    public static final ModuleMethod set$Mnthis$Mnform;
    public static final Macro set$Mnvar$Ex;
    public static final ModuleMethod set$Mnyail$Mnlist$Mncontents$Ex;
    public static final ModuleMethod show$Mnarglist$Mnno$Mnparens;
    public static final ModuleMethod signal$Mnruntime$Mnerror;
    public static final String simple$Mncomponent$Mnpackage$Mnname;
    public static final ModuleMethod sin$Mndegrees;
    public static final ModuleMethod split$Mncolor;
    public static final ModuleMethod string$Mncontains;
    public static final ModuleMethod string$Mnempty$Qu;
    public static final ModuleMethod string$Mnreplace;
    public static final ModuleMethod string$Mnreplace$Mnall;
    public static final ModuleMethod string$Mnsplit;
    public static final ModuleMethod string$Mnsplit$Mnat$Mnany;
    public static final ModuleMethod string$Mnsplit$Mnat$Mnfirst;
    public static final ModuleMethod string$Mnsplit$Mnat$Mnfirst$Mnof$Mnany;
    public static final ModuleMethod string$Mnsplit$Mnat$Mnspaces;
    public static final ModuleMethod string$Mnstarts$Mnat;
    public static final ModuleMethod string$Mnsubstring;
    public static final ModuleMethod string$Mnto$Mnlower$Mncase;
    public static final ModuleMethod string$Mnto$Mnupper$Mncase;
    public static final ModuleMethod string$Mntrim;
    public static final ModuleMethod symbol$Mnappend;
    public static final ModuleMethod tan$Mndegrees;
    public static final ModuleMethod text$Mndeobsfucate;
    public static final ModuleMethod type$Mn$Grclass;

    /* renamed from: while  reason: not valid java name */
    public static final Macro f0while;
    public static final ModuleMethod yail$Mnalist$Mnlookup;
    public static final ModuleMethod yail$Mnatomic$Mnequal$Qu;
    public static final ModuleMethod yail$Mnceiling;
    public static final ModuleMethod yail$Mndivide;
    public static final ModuleMethod yail$Mnequal$Qu;
    public static final ModuleMethod yail$Mnfloor;
    public static final ModuleMethod yail$Mnfor$Mneach;
    public static final ModuleMethod yail$Mnfor$Mnrange;
    public static final ModuleMethod yail$Mnfor$Mnrange$Mnwith$Mnnumeric$Mnchecked$Mnargs;
    public static final ModuleMethod yail$Mnlist$Mn$Grkawa$Mnlist;
    public static final ModuleMethod yail$Mnlist$Mnadd$Mnto$Mnlist$Ex;
    public static final ModuleMethod yail$Mnlist$Mnappend$Ex;
    public static final ModuleMethod yail$Mnlist$Mncandidate$Qu;
    public static final ModuleMethod yail$Mnlist$Mncontents;
    public static final ModuleMethod yail$Mnlist$Mncopy;
    public static final ModuleMethod yail$Mnlist$Mnempty$Qu;
    public static final ModuleMethod yail$Mnlist$Mnfrom$Mncsv$Mnrow;
    public static final ModuleMethod yail$Mnlist$Mnfrom$Mncsv$Mntable;
    public static final ModuleMethod yail$Mnlist$Mnget$Mnitem;
    public static final ModuleMethod yail$Mnlist$Mnindex;
    public static final ModuleMethod yail$Mnlist$Mninsert$Mnitem$Ex;
    public static final ModuleMethod yail$Mnlist$Mnlength;
    public static final ModuleMethod yail$Mnlist$Mnmember$Qu;
    public static final ModuleMethod yail$Mnlist$Mnpick$Mnrandom;
    public static final ModuleMethod yail$Mnlist$Mnremove$Mnitem$Ex;
    public static final ModuleMethod yail$Mnlist$Mnset$Mnitem$Ex;
    public static final ModuleMethod yail$Mnlist$Mnto$Mncsv$Mnrow;
    public static final ModuleMethod yail$Mnlist$Mnto$Mncsv$Mntable;
    public static final ModuleMethod yail$Mnlist$Qu;
    public static final ModuleMethod yail$Mnnot;
    public static final ModuleMethod yail$Mnnot$Mnequal$Qu;
    public static final ModuleMethod yail$Mnnumber$Mnrange;
    public static final ModuleMethod yail$Mnround;
    static final SimpleSymbol Lit335 = (SimpleSymbol) new SimpleSymbol("add-to-components").readResolve();
    static final SimpleSymbol Lit334 = (SimpleSymbol) new SimpleSymbol("init-components").readResolve();
    static final SimpleSymbol Lit333 = (SimpleSymbol) new SimpleSymbol("reverse").readResolve();
    static final SimpleSymbol Lit332 = (SimpleSymbol) new SimpleSymbol("init-global-variables").readResolve();
    static final SimpleSymbol Lit331 = (SimpleSymbol) new SimpleSymbol("*the-null-value*").readResolve();
    static final SimpleSymbol Lit330 = (SimpleSymbol) new SimpleSymbol("register-events").readResolve();
    static final SimpleSymbol Lit329 = (SimpleSymbol) new SimpleSymbol("try-catch").readResolve();
    static final SimpleSymbol Lit328 = (SimpleSymbol) new SimpleSymbol("symbols").readResolve();
    static final SimpleSymbol Lit327 = (SimpleSymbol) new SimpleSymbol("symbol->string").readResolve();
    static final SimpleSymbol Lit326 = (SimpleSymbol) new SimpleSymbol("string-append").readResolve();
    static final SimpleSymbol Lit325 = (SimpleSymbol) new SimpleSymbol("apply").readResolve();
    static final SimpleSymbol Lit324 = (SimpleSymbol) new SimpleSymbol("field").readResolve();
    static final SimpleSymbol Lit323 = (SimpleSymbol) new SimpleSymbol("cadddr").readResolve();
    static final SimpleSymbol Lit322 = (SimpleSymbol) new SimpleSymbol("caddr").readResolve();
    static final SimpleSymbol Lit321 = (SimpleSymbol) new SimpleSymbol("component-descriptors").readResolve();
    static final SimpleSymbol Lit320 = (SimpleSymbol) new SimpleSymbol("component-object").readResolve();
    static final SimpleSymbol Lit319 = (SimpleSymbol) new SimpleSymbol("component-container").readResolve();
    static final SimpleSymbol Lit318 = (SimpleSymbol) new SimpleSymbol("cadr").readResolve();
    static final SimpleSymbol Lit317 = (SimpleSymbol) new SimpleSymbol("component-info").readResolve();
    static final SimpleSymbol Lit316 = (SimpleSymbol) new SimpleSymbol("var-val-pairs").readResolve();
    static final SimpleSymbol Lit315 = (SimpleSymbol) new SimpleSymbol("add-to-global-var-environment").readResolve();
    static final SimpleSymbol Lit314 = (SimpleSymbol) new SimpleSymbol("var-val").readResolve();
    static final SimpleSymbol Lit313 = (SimpleSymbol) new SimpleSymbol("car").readResolve();
    static final SimpleSymbol Lit312 = (SimpleSymbol) new SimpleSymbol("for-each").readResolve();
    static final SimpleSymbol Lit311 = (SimpleSymbol) new SimpleSymbol("events").readResolve();
    static final SimpleSymbol Lit310 = (SimpleSymbol) new SimpleSymbol("event-info").readResolve();
    static final SimpleSymbol Lit309 = (SimpleSymbol) new SimpleSymbol("registerEventForDelegation").readResolve();
    static final SimpleSymbol Lit308 = (SimpleSymbol) new SimpleSymbol("SimpleEventDispatcher").readResolve();
    static final SimpleSymbol Lit307 = (SimpleSymbol) new SimpleSymbol("define-alias").readResolve();
    static final SimpleSymbol Lit306 = (SimpleSymbol) new SimpleSymbol("componentName").readResolve();
    static final SimpleSymbol Lit305 = (SimpleSymbol) new SimpleSymbol("string->symbol").readResolve();
    static final SimpleSymbol Lit304 = (SimpleSymbol) new SimpleSymbol("lookup-handler").readResolve();
    static final SimpleSymbol Lit303 = (SimpleSymbol) new SimpleSymbol("com.google.appinventor.components.runtime.HandlesEventDispatching").readResolve();
    static final SimpleSymbol Lit302 = (SimpleSymbol) new SimpleSymbol("com.google.appinventor.components.runtime.EventDispatcher").readResolve();
    static final SimpleSymbol Lit301 = (SimpleSymbol) new SimpleSymbol("process-exception").readResolve();
    static final SimpleSymbol Lit300 = (SimpleSymbol) new SimpleSymbol("exception").readResolve();
    static final SimpleSymbol Lit299 = (SimpleSymbol) new SimpleSymbol("args").readResolve();
    static final SimpleSymbol Lit298 = (SimpleSymbol) new SimpleSymbol("handler").readResolve();
    static final SimpleSymbol Lit297 = (SimpleSymbol) new SimpleSymbol("eventName").readResolve();
    static final SimpleSymbol Lit296 = (SimpleSymbol) new SimpleSymbol("componentObject").readResolve();
    static final SimpleSymbol Lit295 = (SimpleSymbol) new SimpleSymbol("lookup-in-form-environment").readResolve();
    static final SimpleSymbol Lit294 = (SimpleSymbol) new SimpleSymbol("eq?").readResolve();
    static final SimpleSymbol Lit293 = (SimpleSymbol) new SimpleSymbol("registeredObject").readResolve();
    static final SimpleSymbol Lit292 = (SimpleSymbol) new SimpleSymbol("is-bound-in-form-environment").readResolve();
    static final SimpleSymbol Lit291 = (SimpleSymbol) new SimpleSymbol("registeredComponentName").readResolve();
    static final SimpleSymbol Lit290 = (SimpleSymbol) new SimpleSymbol("java.lang.String").readResolve();
    static final SimpleSymbol Lit289 = (SimpleSymbol) new SimpleSymbol("as").readResolve();
    static final SimpleSymbol Lit288 = (SimpleSymbol) new SimpleSymbol("YailRuntimeError").readResolve();
    static final SimpleSymbol Lit287 = (SimpleSymbol) new SimpleSymbol("getMessage").readResolve();
    static final SimpleSymbol Lit286 = (SimpleSymbol) new SimpleSymbol("this").readResolve();
    static final SimpleSymbol Lit285 = (SimpleSymbol) new SimpleSymbol("send-error").readResolve();
    static final SimpleSymbol Lit284 = (SimpleSymbol) new SimpleSymbol("when").readResolve();
    static final SimpleSymbol Lit283 = (SimpleSymbol) new SimpleSymbol("ex").readResolve();
    static final SimpleSymbol Lit282 = (SimpleSymbol) new SimpleSymbol("error").readResolve();
    static final SimpleSymbol Lit281 = (SimpleSymbol) new SimpleSymbol("thunk").readResolve();
    static final SimpleSymbol Lit280 = (SimpleSymbol) new SimpleSymbol("form-do-after-creation").readResolve();
    static final SimpleSymbol Lit279 = (SimpleSymbol) new SimpleSymbol("add-to-form-do-after-creation").readResolve();
    static final SimpleSymbol Lit278 = (SimpleSymbol) new SimpleSymbol("val-thunk").readResolve();
    static final SimpleSymbol Lit277 = (SimpleSymbol) new SimpleSymbol("var").readResolve();
    static final SimpleSymbol Lit276 = (SimpleSymbol) new SimpleSymbol("global-vars-to-create").readResolve();
    static final SimpleSymbol Lit275 = (SimpleSymbol) new SimpleSymbol("init-thunk").readResolve();
    static final SimpleSymbol Lit274 = (SimpleSymbol) new SimpleSymbol("component-type").readResolve();
    static final SimpleSymbol Lit273 = (SimpleSymbol) new SimpleSymbol("container-name").readResolve();
    static final SimpleSymbol Lit272 = (SimpleSymbol) new SimpleSymbol("components-to-create").readResolve();
    static final SimpleSymbol Lit271 = (SimpleSymbol) new SimpleSymbol("set!").readResolve();
    static final SimpleSymbol Lit270 = (SimpleSymbol) new SimpleSymbol("event-name").readResolve();
    static final SimpleSymbol Lit269 = (SimpleSymbol) new SimpleSymbol("component-name").readResolve();
    static final SimpleSymbol Lit268 = (SimpleSymbol) new SimpleSymbol("cons").readResolve();
    static final SimpleSymbol Lit267 = (SimpleSymbol) new SimpleSymbol("events-to-register").readResolve();
    static final SimpleSymbol Lit266 = (SimpleSymbol) new SimpleSymbol("add-to-events").readResolve();
    static final SimpleSymbol Lit265 = (SimpleSymbol) new SimpleSymbol("gnu.lists.LList").readResolve();
    static final SimpleSymbol Lit264 = (SimpleSymbol) new SimpleSymbol("global-var-environment").readResolve();
    static final SimpleSymbol Lit263 = (SimpleSymbol) new SimpleSymbol("format").readResolve();
    static final SimpleSymbol Lit262 = (SimpleSymbol) new SimpleSymbol("make").readResolve();
    static final SimpleSymbol Lit261 = (SimpleSymbol) new SimpleSymbol("isBound").readResolve();
    static final SimpleSymbol Lit260 = (SimpleSymbol) new SimpleSymbol("default-value").readResolve();
    static final SimpleSymbol Lit259 = (SimpleSymbol) new SimpleSymbol("gnu.mapping.Symbol").readResolve();
    static final SimpleSymbol Lit258 = (SimpleSymbol) new SimpleSymbol("object").readResolve();
    static final SimpleSymbol Lit257 = (SimpleSymbol) new SimpleSymbol("form-environment").readResolve();
    static final SimpleSymbol Lit256 = (SimpleSymbol) new SimpleSymbol("name").readResolve();
    static final SimpleSymbol Lit255 = (SimpleSymbol) new SimpleSymbol("android-log-form").readResolve();
    static final SimpleSymbol Lit254 = (SimpleSymbol) new SimpleSymbol("::").readResolve();
    static final SimpleSymbol Lit253 = (SimpleSymbol) new SimpleSymbol("add-to-form-environment").readResolve();
    static final SimpleSymbol Lit252 = (SimpleSymbol) new SimpleSymbol("gnu.mapping.Environment").readResolve();
    static final SimpleSymbol Lit251 = (SimpleSymbol) new SimpleSymbol("message").readResolve();
    static final SimpleSymbol Lit250 = (SimpleSymbol) new SimpleSymbol(LispLanguage.quasiquote_sym).readResolve();
    static final SimpleSymbol Lit249 = (SimpleSymbol) new SimpleSymbol("$lookup$").readResolve();
    static final SimpleSymbol Lit248 = (SimpleSymbol) new SimpleSymbol("*debug-form*").readResolve();
    static final SimpleSymbol Lit247 = (SimpleSymbol) new SimpleSymbol("define").readResolve();
    static final SimpleSymbol Lit246 = (SimpleSymbol) new SimpleSymbol("let").readResolve();
    static final SimpleSymbol Lit245 = (SimpleSymbol) new SimpleSymbol("add-to-global-vars").readResolve();
    static final SimpleSymbol Lit244 = (SimpleSymbol) new SimpleSymbol(LispLanguage.quote_sym).readResolve();
    static final SimpleSymbol Lit243 = (SimpleSymbol) new SimpleSymbol("*this-is-the-repl*").readResolve();
    static final SimpleSymbol Lit242 = (SimpleSymbol) new SimpleSymbol("delay").readResolve();
    static final SimpleSymbol Lit241 = (SimpleSymbol) new SimpleSymbol("begin").readResolve();
    static final SimpleSymbol Lit240 = (SimpleSymbol) new SimpleSymbol("if").readResolve();
    static final SimpleSymbol Lit239 = (SimpleSymbol) new SimpleSymbol("lambda").readResolve();
    static final SimpleSymbol Lit238 = (SimpleSymbol) new SimpleSymbol("loop").readResolve();
    static final SimpleSymbol Lit237 = (SimpleSymbol) new SimpleSymbol("_").readResolve();
    static final SimpleSymbol Lit236 = (SimpleSymbol) new SimpleSymbol("clarify1").readResolve();
    static final SimpleSymbol Lit235 = (SimpleSymbol) new SimpleSymbol("clarify").readResolve();
    static final SimpleSymbol Lit234 = (SimpleSymbol) new SimpleSymbol("set-this-form").readResolve();
    static final SimpleSymbol Lit233 = (SimpleSymbol) new SimpleSymbol("init-runtime").readResolve();
    static final SimpleSymbol Lit232 = (SimpleSymbol) new SimpleSymbol("rename-component").readResolve();
    static final SimpleSymbol Lit231 = (SimpleSymbol) new SimpleSymbol("remove-component").readResolve();
    static final SimpleSymbol Lit230 = (SimpleSymbol) new SimpleSymbol("set-form-name").readResolve();
    static final SimpleSymbol Lit229 = (SimpleSymbol) new SimpleSymbol("clear-current-form").readResolve();
    static final SimpleSymbol Lit228 = (SimpleSymbol) new SimpleSymbol("send-to-block").readResolve();
    static final SimpleSymbol Lit227 = (SimpleSymbol) new SimpleSymbol("in-ui").readResolve();
    static final SyntaxRules Lit226 = new SyntaxRules(new Object[]{Lit237}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\f\u000f\b", new Object[0], 2), "\u0001\u0001", "\u0011\u0018\u0004\t\u0003\b\u0011\u0018\f\b\u000b", new Object[]{Lit227, Lit242}, 0)}, 2);
    static final SimpleSymbol Lit225 = (SimpleSymbol) new SimpleSymbol("process-repl-input").readResolve();
    static final SimpleSymbol Lit224 = (SimpleSymbol) new SimpleSymbol("get-server-address-from-wifi").readResolve();
    static final SimpleSymbol Lit223 = (SimpleSymbol) new SimpleSymbol("close-screen-with-plain-text").readResolve();
    static final SimpleSymbol Lit222 = (SimpleSymbol) new SimpleSymbol("get-plain-start-text").readResolve();
    static final SimpleSymbol Lit221 = (SimpleSymbol) new SimpleSymbol("close-screen-with-value").readResolve();
    static final SimpleSymbol Lit220 = (SimpleSymbol) new SimpleSymbol("get-start-value").readResolve();
    static final SimpleSymbol Lit219 = (SimpleSymbol) new SimpleSymbol("open-another-screen-with-start-value").readResolve();
    static final SimpleSymbol Lit218 = (SimpleSymbol) new SimpleSymbol("open-another-screen").readResolve();
    static final SimpleSymbol Lit217 = (SimpleSymbol) new SimpleSymbol("close-application").readResolve();
    static final SimpleSymbol Lit216 = (SimpleSymbol) new SimpleSymbol("close-screen").readResolve();
    static final SimpleSymbol Lit215 = (SimpleSymbol) new SimpleSymbol("split-color").readResolve();
    static final SimpleSymbol Lit214 = (SimpleSymbol) new SimpleSymbol("make-color").readResolve();
    static final SimpleSymbol Lit213 = (SimpleSymbol) new SimpleSymbol("make-exact-yail-integer").readResolve();
    static final SimpleSymbol Lit212 = (SimpleSymbol) new SimpleSymbol("text-deobsfucate").readResolve();
    static final SimpleSymbol Lit211 = (SimpleSymbol) new SimpleSymbol("string-empty?").readResolve();
    static final SimpleSymbol Lit210 = (SimpleSymbol) new SimpleSymbol("string-replace-all").readResolve();
    static final SimpleSymbol Lit209 = (SimpleSymbol) new SimpleSymbol("string-trim").readResolve();
    static final SimpleSymbol Lit208 = (SimpleSymbol) new SimpleSymbol("string-substring").readResolve();
    static final SimpleSymbol Lit207 = (SimpleSymbol) new SimpleSymbol("string-split-at-spaces").readResolve();
    static final SimpleSymbol Lit206 = (SimpleSymbol) new SimpleSymbol("string-split-at-any").readResolve();
    static final SimpleSymbol Lit205 = (SimpleSymbol) new SimpleSymbol("string-split").readResolve();
    static final SimpleSymbol Lit204 = (SimpleSymbol) new SimpleSymbol("string-split-at-first-of-any").readResolve();
    static final SimpleSymbol Lit203 = (SimpleSymbol) new SimpleSymbol("string-split-at-first").readResolve();
    static final SimpleSymbol Lit202 = (SimpleSymbol) new SimpleSymbol("string-contains").readResolve();
    static final SimpleSymbol Lit201 = (SimpleSymbol) new SimpleSymbol("string-starts-at").readResolve();
    static final SimpleSymbol Lit200 = (SimpleSymbol) new SimpleSymbol("array->list").readResolve();
    static final SimpleSymbol Lit199 = (SimpleSymbol) new SimpleSymbol("make-disjunct").readResolve();
    static final SimpleSymbol Lit198 = (SimpleSymbol) new SimpleSymbol("pair-ok?").readResolve();
    static final SimpleSymbol Lit197 = (SimpleSymbol) new SimpleSymbol("yail-alist-lookup").readResolve();
    static final SimpleSymbol Lit196 = (SimpleSymbol) new SimpleSymbol("yail-number-range").readResolve();
    static final SimpleSymbol Lit195 = (SimpleSymbol) new SimpleSymbol("yail-for-range-with-numeric-checked-args").readResolve();
    static final SimpleSymbol Lit194 = (SimpleSymbol) new SimpleSymbol("yail-for-range").readResolve();
    static final SimpleSymbol Lit193 = (SimpleSymbol) new SimpleSymbol("yail-for-each").readResolve();
    static final SimpleSymbol Lit192 = (SimpleSymbol) new SimpleSymbol("yail-list-pick-random").readResolve();
    static final SimpleSymbol Lit191 = (SimpleSymbol) new SimpleSymbol("yail-list-member?").readResolve();
    static final SimpleSymbol Lit190 = (SimpleSymbol) new SimpleSymbol("yail-list-add-to-list!").readResolve();
    static final SimpleSymbol Lit189 = (SimpleSymbol) new SimpleSymbol("yail-list-append!").readResolve();
    static final SimpleSymbol Lit188 = (SimpleSymbol) new SimpleSymbol("yail-list-insert-item!").readResolve();
    static final SimpleSymbol Lit187 = (SimpleSymbol) new SimpleSymbol("yail-list-remove-item!").readResolve();
    static final SimpleSymbol Lit186 = (SimpleSymbol) new SimpleSymbol("yail-list-set-item!").readResolve();
    static final SimpleSymbol Lit185 = (SimpleSymbol) new SimpleSymbol("yail-list-get-item").readResolve();
    static final SimpleSymbol Lit184 = (SimpleSymbol) new SimpleSymbol("yail-list-index").readResolve();
    static final SimpleSymbol Lit183 = (SimpleSymbol) new SimpleSymbol("yail-list-length").readResolve();
    static final SimpleSymbol Lit182 = (SimpleSymbol) new SimpleSymbol("yail-list-from-csv-row").readResolve();
    static final SimpleSymbol Lit181 = (SimpleSymbol) new SimpleSymbol("yail-list-from-csv-table").readResolve();
    static final SimpleSymbol Lit180 = (SimpleSymbol) new SimpleSymbol("convert-to-strings").readResolve();
    static final SimpleSymbol Lit179 = (SimpleSymbol) new SimpleSymbol("yail-list-to-csv-row").readResolve();
    static final SimpleSymbol Lit178 = (SimpleSymbol) new SimpleSymbol("yail-list-to-csv-table").readResolve();
    static final SimpleSymbol Lit177 = (SimpleSymbol) new SimpleSymbol("yail-list-copy").readResolve();
    static final SimpleSymbol Lit176 = (SimpleSymbol) new SimpleSymbol("make-yail-list").readResolve();
    static final SimpleSymbol Lit175 = (SimpleSymbol) new SimpleSymbol("yail-list-empty?").readResolve();
    static final SimpleSymbol Lit174 = (SimpleSymbol) new SimpleSymbol("yail-list->kawa-list").readResolve();
    static final SimpleSymbol Lit173 = (SimpleSymbol) new SimpleSymbol("kawa-list->yail-list").readResolve();
    static final SimpleSymbol Lit172 = (SimpleSymbol) new SimpleSymbol("insert-yail-list-header").readResolve();
    static final SimpleSymbol Lit171 = (SimpleSymbol) new SimpleSymbol("set-yail-list-contents!").readResolve();
    static final SimpleSymbol Lit170 = (SimpleSymbol) new SimpleSymbol("yail-list-contents").readResolve();
    static final SimpleSymbol Lit169 = (SimpleSymbol) new SimpleSymbol("yail-list-candidate?").readResolve();
    static final SimpleSymbol Lit168 = (SimpleSymbol) new SimpleSymbol("yail-list?").readResolve();
    static final SimpleSymbol Lit167 = (SimpleSymbol) new SimpleSymbol("is-number?").readResolve();
    static final SimpleSymbol Lit166 = (SimpleSymbol) new SimpleSymbol("format-as-decimal").readResolve();
    static final SimpleSymbol Lit165 = (SimpleSymbol) new SimpleSymbol("string-to-lower-case").readResolve();
    static final SimpleSymbol Lit164 = (SimpleSymbol) new SimpleSymbol("string-to-upper-case").readResolve();
    static final SimpleSymbol Lit163 = (SimpleSymbol) new SimpleSymbol("atan2-degrees").readResolve();
    static final SimpleSymbol Lit162 = (SimpleSymbol) new SimpleSymbol("atan-degrees").readResolve();
    static final SimpleSymbol Lit161 = (SimpleSymbol) new SimpleSymbol("acos-degrees").readResolve();
    static final SimpleSymbol Lit160 = (SimpleSymbol) new SimpleSymbol("asin-degrees").readResolve();
    static final SimpleSymbol Lit159 = (SimpleSymbol) new SimpleSymbol("tan-degrees").readResolve();
    static final SimpleSymbol Lit158 = (SimpleSymbol) new SimpleSymbol("cos-degrees").readResolve();
    static final SimpleSymbol Lit157 = (SimpleSymbol) new SimpleSymbol("sin-degrees").readResolve();
    static final SimpleSymbol Lit156 = (SimpleSymbol) new SimpleSymbol("radians->degrees").readResolve();
    static final SimpleSymbol Lit155 = (SimpleSymbol) new SimpleSymbol("degrees->radians").readResolve();
    static final SimpleSymbol Lit154 = (SimpleSymbol) new SimpleSymbol("radians->degrees-internal").readResolve();
    static final SimpleSymbol Lit153 = (SimpleSymbol) new SimpleSymbol("degrees->radians-internal").readResolve();
    static final SimpleSymbol Lit152 = (SimpleSymbol) new SimpleSymbol("yail-divide").readResolve();
    static final SimpleSymbol Lit151 = (SimpleSymbol) new SimpleSymbol("random-integer").readResolve();
    static final SimpleSymbol Lit150 = (SimpleSymbol) new SimpleSymbol("random-fraction").readResolve();
    static final SimpleSymbol Lit149 = (SimpleSymbol) new SimpleSymbol("random-set-seed").readResolve();
    static final SimpleSymbol Lit148 = (SimpleSymbol) new SimpleSymbol("yail-round").readResolve();
    static final SimpleSymbol Lit147 = (SimpleSymbol) new SimpleSymbol("yail-ceiling").readResolve();
    static final SimpleSymbol Lit146 = (SimpleSymbol) new SimpleSymbol("yail-floor").readResolve();
    static final SimpleSymbol Lit145 = (SimpleSymbol) new SimpleSymbol("process-or-delayed").readResolve();
    static final SimpleSymbol Lit144 = (SimpleSymbol) new SimpleSymbol("process-and-delayed").readResolve();
    static final SimpleSymbol Lit143 = (SimpleSymbol) new SimpleSymbol("yail-not-equal?").readResolve();
    static final SimpleSymbol Lit142 = (SimpleSymbol) new SimpleSymbol("as-number").readResolve();
    static final SimpleSymbol Lit141 = (SimpleSymbol) new SimpleSymbol("yail-atomic-equal?").readResolve();
    static final SimpleSymbol Lit140 = (SimpleSymbol) new SimpleSymbol("yail-equal?").readResolve();
    static final SimpleSymbol Lit139 = (SimpleSymbol) new SimpleSymbol("appinventor-number->string").readResolve();
    static final SimpleSymbol Lit138 = (SimpleSymbol) new SimpleSymbol("*format-inexact*").readResolve();
    static final SimpleSymbol Lit137 = (SimpleSymbol) new SimpleSymbol("padded-string->number").readResolve();
    static final SimpleSymbol Lit136 = (SimpleSymbol) new SimpleSymbol("boolean->string").readResolve();
    static final SimpleSymbol Lit135 = (SimpleSymbol) new SimpleSymbol("all-coercible?").readResolve();
    static final SimpleSymbol Lit134 = (SimpleSymbol) new SimpleSymbol("is-coercible?").readResolve();
    static final SimpleSymbol Lit133 = (SimpleSymbol) new SimpleSymbol("coerce-to-boolean").readResolve();
    static final SimpleSymbol Lit132 = (SimpleSymbol) new SimpleSymbol("coerce-to-yail-list").readResolve();
    static final SimpleSymbol Lit131 = (SimpleSymbol) new SimpleSymbol("string-replace").readResolve();
    static final SimpleSymbol Lit130 = (SimpleSymbol) new SimpleSymbol("coerce-to-string").readResolve();
    static final SimpleSymbol Lit129 = (SimpleSymbol) new SimpleSymbol("coerce-to-number").readResolve();
    static final SimpleSymbol Lit128 = (SimpleSymbol) new SimpleSymbol("type->class").readResolve();
    static final SimpleSymbol Lit127 = (SimpleSymbol) new SimpleSymbol("coerce-to-component-of-type").readResolve();
    static final SimpleSymbol Lit126 = (SimpleSymbol) new SimpleSymbol("coerce-to-component").readResolve();
    static final SimpleSymbol Lit125 = (SimpleSymbol) new SimpleSymbol("coerce-to-instant").readResolve();
    static final SimpleSymbol Lit124 = (SimpleSymbol) new SimpleSymbol("coerce-to-text").readResolve();
    static final SimpleSymbol Lit123 = (SimpleSymbol) new SimpleSymbol("coerce-arg").readResolve();
    static final SimpleSymbol Lit122 = (SimpleSymbol) new SimpleSymbol("coerce-args").readResolve();
    static final SimpleSymbol Lit121 = (SimpleSymbol) new SimpleSymbol("show-arglist-no-parens").readResolve();
    static final SimpleSymbol Lit120 = (SimpleSymbol) new SimpleSymbol("generate-runtime-type-error").readResolve();
    static final SimpleSymbol Lit119 = (SimpleSymbol) new SimpleSymbol("%set-subform-layout-property!").readResolve();
    static final SimpleSymbol Lit118 = (SimpleSymbol) new SimpleSymbol("%set-and-coerce-property!").readResolve();
    static final SimpleSymbol Lit117 = (SimpleSymbol) new SimpleSymbol("call-with-coerced-args").readResolve();
    static final SimpleSymbol Lit116 = (SimpleSymbol) new SimpleSymbol("yail-not").readResolve();
    static final SimpleSymbol Lit115 = (SimpleSymbol) new SimpleSymbol("signal-runtime-error").readResolve();
    static final SimpleSymbol Lit114 = (SimpleSymbol) new SimpleSymbol("sanitize-atomic").readResolve();
    static final SimpleSymbol Lit113 = (SimpleSymbol) new SimpleSymbol("java-collection->kawa-list").readResolve();
    static final SimpleSymbol Lit112 = (SimpleSymbol) new SimpleSymbol("java-collection->yail-list").readResolve();
    static final SimpleSymbol Lit111 = (SimpleSymbol) new SimpleSymbol("sanitize-component-data").readResolve();
    static final SimpleSymbol Lit110 = (SimpleSymbol) new SimpleSymbol("call-yail-primitive").readResolve();
    static final SimpleSymbol Lit109 = (SimpleSymbol) new SimpleSymbol("call-component-type-method").readResolve();
    static final SimpleSymbol Lit108 = (SimpleSymbol) new SimpleSymbol("call-component-method").readResolve();
    static final SyntaxRules Lit107 = new SyntaxRules(new Object[]{Lit237}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\r\u000f\b\b\b", new Object[0], 2), "\u0001\u0003", "\u0011\u0018\u0004\u0011\u0018\f\t\u0010\b\u0011\u0018\u0014\t\u0003A\u0011\u0018\u001c\u0011\r\u000b\u0018$\u0018,", new Object[]{Lit246, Lit238, Lit240, Lit241, PairWithPosition.make(PairWithPosition.make(Lit238, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 3186698), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 3186698), PairWithPosition.make(Lit331, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 3190792)}, 1)}, 2);
    static final SimpleSymbol Lit106 = (SimpleSymbol) new SimpleSymbol("while").readResolve();
    static final SyntaxRules Lit105 = new SyntaxRules(new Object[]{Lit237}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\f\u000f\f\u0017\f\u001f\f'\b", new Object[0], 5), "\u0001\u0001\u0001\u0001\u0001", "\u0011\u0018\u0004A\u0011\u0018\f\u0011\b\u0003\b\u000b\t\u0013\t\u001b\b#", new Object[]{Lit194, Lit239}, 0)}, 5);
    static final SimpleSymbol Lit104 = (SimpleSymbol) new SimpleSymbol("forrange").readResolve();
    static final SyntaxRules Lit103 = new SyntaxRules(new Object[]{Lit237}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\f\u000f\f\u0017\b", new Object[0], 3), "\u0001\u0001\u0001", "\u0011\u0018\u0004A\u0011\u0018\f\u0011\b\u0003\b\u000b\b\u0013", new Object[]{Lit193, Lit239}, 0)}, 3);
    static final SimpleSymbol Lit102 = (SimpleSymbol) new SimpleSymbol("foreach").readResolve();
    static final SimpleSymbol Lit101 = (SimpleSymbol) new SimpleSymbol("reset-current-form-environment").readResolve();
    static final SimpleSymbol Lit100 = (SimpleSymbol) new SimpleSymbol("lookup-global-var-in-current-form-environment").readResolve();
    static final SimpleSymbol Lit99 = (SimpleSymbol) new SimpleSymbol("add-global-var-to-current-form-environment").readResolve();
    static final SimpleSymbol Lit98 = (SimpleSymbol) new SimpleSymbol("rename-in-current-form-environment").readResolve();
    static final SimpleSymbol Lit97 = (SimpleSymbol) new SimpleSymbol("delete-from-current-form-environment").readResolve();
    static final SimpleSymbol Lit96 = (SimpleSymbol) new SimpleSymbol("lookup-in-current-form-environment").readResolve();
    static final SimpleSymbol Lit95 = (SimpleSymbol) new SimpleSymbol("add-to-current-form-environment").readResolve();
    static final SyntaxRules Lit94 = new SyntaxRules(new Object[]{Lit237}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\r\u0007\u0000\b\b", new Object[0], 1), "\u0003", "\u0011\u0018\u0004\u0011\u0018\f1\u0011\u0018\u0014\b\u0005\u0003\b\u0011\u0018\u001c\b\u0011\u0018$\b\u0011\u0018\u0014\b\u0005\u0003", new Object[]{Lit240, Lit243, Lit241, Lit279, Lit242}, 1)}, 1);
    static final SimpleSymbol Lit93 = (SimpleSymbol) new SimpleSymbol("do-after-form-creation").readResolve();
    static final SyntaxRules Lit92 = new SyntaxRules(new Object[]{Lit237}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018<\f\u0007\r\u000f\b\b\b\r\u0017\u0010\b\b", new Object[0], 3), "\u0001\u0003\u0003", "\u0011\u0018\u0004\b\u0011\u0018\f\u0011\u0018\u0014¡\u0011\u0018\u001c)\u0011\u0018$\b\u0003\b\u0011\u0018,\u0019\b\r\u000b\b\u0015\u0013\b\u0011\u00184)\u0011\u0018$\b\u0003\b\u0011\u0018,\t\u0010\b\u0011\u0018,\u0019\b\r\u000b\b\u0015\u0013", new Object[]{Lit241, Lit240, Lit243, Lit99, Lit244, Lit239, Lit245}, 1), new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\f\u000f\b", new Object[0], 2), "\u0001\u0001", "\u0011\u0018\u0004\b\u0011\u0018\f\u0011\u0018\u0014Y\u0011\u0018\u001c)\u0011\u0018$\b\u0003\b\u000b\b\u0011\u0018,)\u0011\u0018$\b\u0003\b\u0011\u00184\t\u0010\b\u000b", new Object[]{Lit241, Lit240, Lit243, Lit99, Lit244, Lit245, Lit239}, 0)}, 3);
    static final SimpleSymbol Lit91 = (SimpleSymbol) new SimpleSymbol("def").readResolve();
    static final SyntaxTemplate Lit90 = new SyntaxTemplate("\u0001\u0001\u0001\u0001\u0000", "\b\u0011\u0018\u0004\u0011\u0018\f\u0091\u0011\u0018\u0014\u0011\u0018\u001c)\u0011\u0018$\b\u000b\b\u0011\u0018$\b\u0013\b\u0011\u0018,)\u0011\u0018$\b\u000b\b\u0011\u0018$\b\u0013", new Object[]{Lit240, Lit243, PairWithPosition.make(Lit249, Pair.make(Lit302, Pair.make(Pair.make(Lit250, Pair.make(Lit309, LList.Empty)), LList.Empty)), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2588689), PairWithPosition.make(Lit289, PairWithPosition.make(Lit303, PairWithPosition.make((SimpleSymbol) new SimpleSymbol("*this-form*").readResolve(), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2592855), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2592789), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2592785), Lit244, Lit266}, 0);
    static final SyntaxTemplate Lit89 = new SyntaxTemplate("\u0001\u0001\u0001\u0001\u0000", "\t\u001b\b\"", new Object[0], 0);
    static final SyntaxTemplate Lit88 = new SyntaxTemplate("\u0001\u0001\u0001\u0001\u0000", "\u0013", new Object[0], 0);
    static final SimpleSymbol Lit87 = (SimpleSymbol) new SimpleSymbol("$").readResolve();
    static final SyntaxTemplate Lit86 = new SyntaxTemplate("\u0001\u0001\u0001\u0001\u0000", "\u000b", new Object[0], 0);

    public runtime() {
        ModuleInfo.register(this);
    }

    public static Object lookupGlobalVarInCurrentFormEnvironment(Symbol symbol) {
        return lookupGlobalVarInCurrentFormEnvironment(symbol, Boolean.FALSE);
    }

    public static Object lookupInCurrentFormEnvironment(Symbol symbol) {
        return lookupInCurrentFormEnvironment(symbol, Boolean.FALSE);
    }

    @Override // gnu.expr.ModuleBody
    public final void run(CallContext $ctx) {
        Consumer consumer = $ctx.consumer;
        $Stdebug$St = Boolean.FALSE;
        $Stthis$Mnis$Mnthe$Mnrepl$St = Boolean.FALSE;
        $Stinit$Mnthunk$Mnenvironment$St = Environment.make("init-thunk-environment");
        $Sttest$Mnenvironment$St = Environment.make("test-env");
        $Sttest$Mnglobal$Mnvar$Mnenvironment$St = Environment.make("test-global-var-env");
        $Stthe$Mnnull$Mnvalue$St = null;
        $Stthe$Mnnull$Mnvalue$Mnprinted$Mnrep$St = "*nothing*";
        $Stthe$Mnempty$Mnstring$Mnprinted$Mnrep$St = "*empty-string*";
        $Stnon$Mncoercible$Mnvalue$St = Lit2;
        $Stjava$Mnexception$Mnmessage$St = "An internal system error occurred: ";
        get$Mndisplay$Mnrepresentation = lambda$Fn4;
        $Strandom$Mnnumber$Mngenerator$St = new Random();
        Object apply2 = AddOp.$Mn.apply2(expt.expt(Lit18, Lit19), Lit16);
        try {
            highest = (Numeric) apply2;
            Object apply1 = AddOp.$Mn.apply1(highest);
            try {
                lowest = (Numeric) apply1;
                clip$Mnto$Mnjava$Mnint$Mnrange = lambda$Fn9;
                $Stpi$St = Lit21;
                $Styail$Mnlist$St = Lit26;
                $Stmax$Mncolor$Mncomponent$St = numbers.exact(Lit28);
                $Stcolor$Mnalpha$Mnposition$St = numbers.exact(Lit30);
                $Stcolor$Mnred$Mnposition$St = numbers.exact(Lit31);
                $Stcolor$Mngreen$Mnposition$St = numbers.exact(Lit29);
                $Stcolor$Mnblue$Mnposition$St = numbers.exact(Lit17);
                $Stalpha$Mnopaque$St = numbers.exact(Lit28);
                $Strun$Mntelnet$Mnrepl$St = Boolean.TRUE;
                $Stnum$Mnconnections$St = Lit16;
                $Strepl$Mnserver$Mnaddress$St = "NONE";
                $Strepl$Mnport$St = Lit34;
                $Stui$Mnhandler$St = null;
                $Stthis$Mnform$St = null;
            } catch (ClassCastException e) {
                throw new WrongType(e, "lowest", -2, apply1);
            }
        } catch (ClassCastException e2) {
            throw new WrongType(e2, "highest", -2, apply2);
        }
    }

    public static void androidLog(Object message) {
    }

    @Override // gnu.expr.ModuleBody
    public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
        switch (moduleMethod.selector) {
            case 9:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 10:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 11:
            case 12:
            case 13:
            case 15:
            case 17:
            case 18:
            case 20:
            case 21:
            case 22:
            case 25:
            case 27:
            case 29:
            case 30:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 41:
            case XDataType.NAME_TYPE_CODE /* 43 */:
            case XDataType.NCNAME_TYPE_CODE /* 44 */:
            case XDataType.ID_TYPE_CODE /* 45 */:
            case XDataType.IDREF_TYPE_CODE /* 46 */:
            case 48:
            case 49:
            case 53:
            case 58:
            case 67:
            case 68:
            case PrettyWriter.NEWLINE_FILL /* 70 */:
            case 71:
            case 72:
            case PrettyWriter.NEWLINE_MISER /* 77 */:
            case PrettyWriter.NEWLINE_LINEAR /* 78 */:
            case 80:
            case 91:
            case 94:
            case 99:
            case 104:
            case DateTime.TIME_MASK /* 112 */:
            case 113:
            case 114:
            case 115:
            case 116:
            case 117:
            case 118:
            case 119:
            case 121:
            case 122:
            case 123:
            case 124:
            case 125:
            case 129:
            case 130:
            case 131:
            case 132:
            case 133:
            case 134:
            case 136:
            case 138:
            case 140:
            case ComponentConstants.VIDEOPLAYER_PREFERRED_HEIGHT /* 144 */:
            case 145:
            case 147:
            case 148:
            case 150:
            case 152:
            case 153:
            case 154:
            case 155:
            case 158:
            case 159:
            case ComponentConstants.TEXTBOX_PREFERRED_WIDTH /* 160 */:
            default:
                return super.match1(moduleMethod, obj, callContext);
            case 14:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 16:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 19:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 23:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 24:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 26:
                if (obj instanceof Symbol) {
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                }
                return -786431;
            case 28:
                if (obj instanceof Symbol) {
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                }
                return -786431;
            case 31:
                if (obj instanceof Symbol) {
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                }
                return -786431;
            case 37:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 38:
                if (obj instanceof Collection) {
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                }
                return -786431;
            case 39:
                if (obj instanceof Collection) {
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                }
                return -786431;
            case 40:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case XDataType.NMTOKEN_TYPE_CODE /* 42 */:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case XDataType.ENTITY_TYPE_CODE /* 47 */:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 50:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 51:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 52:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 54:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 55:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 56:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 57:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 59:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 60:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 61:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 62:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 63:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 64:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 65:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 66:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 69:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 73:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 74:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 75:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case PrettyWriter.NEWLINE_LITERAL /* 76 */:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 79:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 81:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case PrettyWriter.NEWLINE_MANDATORY /* 82 */:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case PrettyWriter.NEWLINE_SPACE /* 83 */:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 84:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 85:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 86:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 87:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 88:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 89:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 90:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 92:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 93:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 95:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 96:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 97:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 98:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 100:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case ErrorMessages.ERROR_LOCATION_SENSOR_LATITUDE_NOT_FOUND /* 101 */:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case ErrorMessages.ERROR_LOCATION_SENSOR_LONGITUDE_NOT_FOUND /* 102 */:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 103:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 105:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 106:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 107:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 108:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case YaVersion.YOUNG_ANDROID_VERSION /* 109 */:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 110:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 111:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 120:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 126:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 127:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case DateTime.TIMEZONE_MASK /* 128 */:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 135:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 137:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 139:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 141:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 142:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 143:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 146:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 149:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 151:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 156:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 157:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 161:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 162:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
        }
    }

    static {
        SimpleSymbol simpleSymbol = (SimpleSymbol) new SimpleSymbol("define-event-helper").readResolve();
        Lit78 = simpleSymbol;
        Lit85 = new SyntaxTemplate("\u0001\u0001\u0001\u0001\u0000", "\u0018\u0004", new Object[]{PairWithPosition.make(simpleSymbol, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2564108)}, 0);
        Lit84 = new SyntaxTemplate("\u0001\u0001\u0001\u0001\u0000", "\u0018\u0004", new Object[]{PairWithPosition.make(Lit241, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2560010)}, 0);
        Lit83 = new SyntaxPattern("\f\u0007\f\u000f\f\u0017\f\u001f#", new Object[0], 5);
        Lit82 = (SimpleSymbol) new SimpleSymbol("define-event").readResolve();
        Object[] objArr = {Lit237};
        SyntaxPattern syntaxPattern = new SyntaxPattern("\f\u0018\r\u0007\u0000\b\b", new Object[0], 1);
        SimpleSymbol simpleSymbol2 = (SimpleSymbol) new SimpleSymbol("list").readResolve();
        Lit7 = simpleSymbol2;
        Lit81 = new SyntaxRules(objArr, new SyntaxRule[]{new SyntaxRule(syntaxPattern, "\u0003", "\u0011\u0018\u0004\b\u0005\u0003", new Object[]{simpleSymbol2}, 1)}, 1);
        Lit80 = (SimpleSymbol) new SimpleSymbol("*list-for-runtime*").readResolve();
        Lit79 = new SyntaxRules(new Object[]{Lit237}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007,\r\u000f\b\b\b,\r\u0017\u0010\b\b\b", new Object[0], 3), "\u0001\u0003\u0003", "\u0011\u0018\u0004Ù\u0011\u0018\f)\t\u0003\b\r\u000b\b\u0011\u0018\u0014Q\b\r\t\u000b\b\u0011\u0018\u001c\b\u000b\b\u0015\u0013\b\u0011\u0018$\u0011\u0018,Y\u0011\u00184)\u0011\u0018<\b\u0003\b\u0003\b\u0011\u0018D)\u0011\u0018<\b\u0003\b\u0003", new Object[]{Lit241, Lit247, Lit246, Lit111, Lit240, Lit243, Lit95, Lit244, Lit253}, 1)}, 3);
        SimpleSymbol simpleSymbol3 = (SimpleSymbol) new SimpleSymbol("symbol-append").readResolve();
        Lit74 = simpleSymbol3;
        Lit77 = new SyntaxTemplate("\u0001\u0001\u0001", "\u0011\u0018\u0004\t\u000b\u0011\u0018\f\b\u0013", new Object[]{simpleSymbol3, PairWithPosition.make(Lit244, PairWithPosition.make(Lit87, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2330691), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2330691)}, 0);
        Lit76 = new SyntaxPattern("\f\u0007\f\u000f\f\u0017\b", new Object[0], 3);
        Lit75 = (SimpleSymbol) new SimpleSymbol("gen-event-name").readResolve();
        Object[] objArr2 = {Lit237};
        SyntaxPattern syntaxPattern2 = new SyntaxPattern("\f\u0018\f\u0007\f\u000f\f\u0017\f\u001f\b", new Object[0], 4);
        SimpleSymbol simpleSymbol4 = Lit247;
        PairWithPosition make = PairWithPosition.make(Lit253, PairWithPosition.make(Lit256, PairWithPosition.make(Lit254, PairWithPosition.make(Lit259, PairWithPosition.make(Lit258, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1265732), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1265713), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1265710), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1265705), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1265680);
        PairWithPosition make2 = PairWithPosition.make(Lit255, PairWithPosition.make(PairWithPosition.make(Lit263, PairWithPosition.make(Boolean.FALSE, PairWithPosition.make("Adding ~A to env ~A with value ~A", PairWithPosition.make(Lit256, PairWithPosition.make(Lit257, PairWithPosition.make(Lit258, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1269857), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1269840), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1269835), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1269799), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1269796), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1269788), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1269788), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1269770);
        SimpleSymbol simpleSymbol5 = Lit249;
        SimpleSymbol simpleSymbol6 = Lit252;
        SimpleSymbol simpleSymbol7 = Lit250;
        SimpleSymbol simpleSymbol8 = (SimpleSymbol) new SimpleSymbol("put").readResolve();
        Lit0 = simpleSymbol8;
        SimpleSymbol simpleSymbol9 = Lit247;
        PairWithPosition make3 = PairWithPosition.make(Lit295, PairWithPosition.make(Lit256, PairWithPosition.make(Lit254, PairWithPosition.make(Lit259, PairWithPosition.make(Special.optional, PairWithPosition.make(PairWithPosition.make(Lit260, PairWithPosition.make(Boolean.FALSE, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1282145), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1282130), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1282130), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1282119), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1282100), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1282097), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1282092), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1282064);
        SimpleSymbol simpleSymbol10 = Lit240;
        PairWithPosition make4 = PairWithPosition.make((SimpleSymbol) new SimpleSymbol("and").readResolve(), PairWithPosition.make(PairWithPosition.make((SimpleSymbol) new SimpleSymbol("not").readResolve(), PairWithPosition.make(PairWithPosition.make(Lit294, PairWithPosition.make(Lit257, PairWithPosition.make(null, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1286190), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1286173), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1286168), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1286168), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1286163), PairWithPosition.make(PairWithPosition.make(PairWithPosition.make(Lit249, Pair.make(Lit252, Pair.make(Pair.make(Lit250, Pair.make(Lit261, LList.Empty)), LList.Empty)), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1290260), PairWithPosition.make(Lit257, PairWithPosition.make(Lit256, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1290309), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1290292), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1290259), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1290259), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1286163), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1286158);
        SimpleSymbol simpleSymbol11 = Lit249;
        SimpleSymbol simpleSymbol12 = Lit252;
        SimpleSymbol simpleSymbol13 = Lit250;
        SimpleSymbol simpleSymbol14 = (SimpleSymbol) new SimpleSymbol("get").readResolve();
        Lit1 = simpleSymbol14;
        SimpleSymbol simpleSymbol15 = Lit247;
        PairWithPosition make5 = PairWithPosition.make((SimpleSymbol) new SimpleSymbol("dispatchEvent").readResolve(), PairWithPosition.make(Lit296, PairWithPosition.make(Lit254, PairWithPosition.make((SimpleSymbol) new SimpleSymbol("com.google.appinventor.components.runtime.Component").readResolve(), PairWithPosition.make(Lit291, PairWithPosition.make(Lit254, PairWithPosition.make(Lit290, PairWithPosition.make(Lit297, PairWithPosition.make(Lit254, PairWithPosition.make(Lit290, PairWithPosition.make(Lit299, PairWithPosition.make(Lit254, PairWithPosition.make((SimpleSymbol) new SimpleSymbol("java.lang.Object[]").readResolve(), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1646631), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1646628), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1646623), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1642540), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1642537), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1642527), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1638458), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1638455), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1638431), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1634354), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1634351), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1634335), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1634320);
        SimpleSymbol simpleSymbol16 = Lit254;
        SimpleSymbol simpleSymbol17 = (SimpleSymbol) new SimpleSymbol(PropertyTypeConstants.PROPERTY_TYPE_BOOLEAN).readResolve();
        Lit6 = simpleSymbol17;
        SimpleSymbol simpleSymbol18 = Lit246;
        PairWithPosition make6 = PairWithPosition.make(PairWithPosition.make(Lit293, PairWithPosition.make(PairWithPosition.make(Lit305, PairWithPosition.make(Lit291, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1671220), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1671204), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1671204), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1671186), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1671185);
        SimpleSymbol simpleSymbol19 = Lit240;
        PairWithPosition make7 = PairWithPosition.make(Lit292, PairWithPosition.make(Lit293, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1675316), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1675286);
        SimpleSymbol simpleSymbol20 = Lit240;
        PairWithPosition make8 = PairWithPosition.make(Lit294, PairWithPosition.make(PairWithPosition.make(Lit295, PairWithPosition.make(Lit293, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1679419), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1679391), PairWithPosition.make(Lit296, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1679437), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1679391), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1679386);
        SimpleSymbol simpleSymbol21 = Lit246;
        PairWithPosition make9 = PairWithPosition.make(PairWithPosition.make(Lit298, PairWithPosition.make(PairWithPosition.make(Lit304, PairWithPosition.make(Lit291, PairWithPosition.make(Lit297, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1683536), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1683512), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1683496), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1683496), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1683487), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1683486);
        SimpleSymbol simpleSymbol22 = Lit329;
        SimpleSymbol simpleSymbol23 = Lit241;
        SimpleSymbol simpleSymbol24 = Lit325;
        SimpleSymbol simpleSymbol25 = Lit298;
        SimpleSymbol simpleSymbol26 = Lit249;
        SimpleSymbol simpleSymbol27 = Lit265;
        SimpleSymbol simpleSymbol28 = Lit250;
        SimpleSymbol simpleSymbol29 = (SimpleSymbol) new SimpleSymbol("makeList").readResolve();
        Lit27 = simpleSymbol29;
        PairWithPosition make10 = PairWithPosition.make(simpleSymbol26, Pair.make(simpleSymbol27, Pair.make(Pair.make(simpleSymbol28, Pair.make(simpleSymbol29, LList.Empty)), LList.Empty)), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1720372);
        SimpleSymbol simpleSymbol30 = Lit299;
        IntNum make11 = IntNum.make(0);
        Lit17 = make11;
        Lit73 = new SyntaxRules(objArr2, new SyntaxRule[]{new SyntaxRule(syntaxPattern2, "\u0001\u0001\u0001\u0001", "\u0011\u0018\u0004)\u0011\u0018\f\b\u0013)\u0011\u0018\u0014\b\u0003)\u0011\u0018\u001c\b\u000b\u0011\u0018$\u0011\u0018,\u0011\u00184Ñ\u0011\u0018<\u0011\u0018D\u0011\u0018L\u0011\u0018T\b\u0011\u0018\\\b\u0011\u0018d\b\u0011\u0018l\b\u000b\u0011\u0018t\u0011\u0018|\u0011\u0018\u0084ā\u0011\u0018<\u0011\u0018\u008c\u0011\u0018L\u0011\u0018T\b\u0011\u0018\u0094\b\u0011\u0018\u009cI\u0011\u0018d\b\u0011\u0018l\b\u000b\u0018¤\u0011\u0018¬a\u0011\u0018<\t\u000b\u0011\u0018L\t\u0003\u0018´\u0091\u0011\u0018<\u0011\u0018¼\u0011\u0018L\u0011\u0018Ä\b\u0011\u0018l\b\u000b\u0011\u0018Ì\u0011\u0018Ô\u0011\u0018Ü\u0011\u0018ä\u0011\u0018ì\u0011\u0018ô\u0011\u0018ü\u0011\u0018Ą\u0011\u0018Č¡\u0011\u0018<\u0011\u0018Ĕ\u0011\u0018Ĝ\u0011\u0018Ĥ\b\u0011\u0018Ĭ\t\u001b\u0018Ĵ\u0011\u0018ļ\u0011\u0018ń\b\u0011\u0018<\u0011\u0018Ō\u0011\u0018L\u0011\u0018Ŕ\u0011\u0018Ŝ\u0011\u0018Ť\u0011\u0018Ŭ\u0011\u0018Ŵ\u0011\u0018ż\u0011\u0018Ƅ9\u0011\u0018ƌ\t\u000b\u0018ƔY\u0011\u0018Ɯ)\u0011\u0018l\b\u000b\u0018Ƥ\u0018Ƭ", new Object[]{Lit241, (SimpleSymbol) new SimpleSymbol("module-extends").readResolve(), (SimpleSymbol) new SimpleSymbol("module-name").readResolve(), (SimpleSymbol) new SimpleSymbol("module-static").readResolve(), PairWithPosition.make((SimpleSymbol) new SimpleSymbol("require").readResolve(), PairWithPosition.make((SimpleSymbol) new SimpleSymbol("<com.google.youngandroid.runtime>").readResolve(), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1212433), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1212424), PairWithPosition.make(Lit247, PairWithPosition.make(Lit248, PairWithPosition.make(Boolean.FALSE, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1220637), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1220624), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1220616), PairWithPosition.make(Lit247, PairWithPosition.make(PairWithPosition.make(Lit255, PairWithPosition.make(Lit251, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1228834), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1228816), PairWithPosition.make(PairWithPosition.make(Lit284, PairWithPosition.make(Lit248, PairWithPosition.make(PairWithPosition.make(PairWithPosition.make(Lit249, Pair.make((SimpleSymbol) new SimpleSymbol("android.util.Log").readResolve(), Pair.make(Pair.make(Lit250, Pair.make((SimpleSymbol) new SimpleSymbol("i").readResolve(), LList.Empty)), LList.Empty)), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1232926), PairWithPosition.make("YAIL", PairWithPosition.make(Lit251, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1232952), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1232945), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1232925), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1232925), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1232912), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1232906), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1232906), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1228816), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1228808), Lit247, Lit257, Lit254, Lit252, PairWithPosition.make(Lit249, Pair.make(Lit252, Pair.make(Pair.make(Lit250, Pair.make(Lit262, LList.Empty)), LList.Empty)), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1257483), Lit327, Lit244, PairWithPosition.make(simpleSymbol4, PairWithPosition.make(make, PairWithPosition.make(make2, PairWithPosition.make(PairWithPosition.make(PairWithPosition.make(simpleSymbol5, Pair.make(simpleSymbol6, Pair.make(Pair.make(simpleSymbol7, Pair.make(simpleSymbol8, LList.Empty)), LList.Empty)), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1273867), PairWithPosition.make(Lit257, PairWithPosition.make(Lit256, PairWithPosition.make(Lit258, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1273917), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1273912), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1273895), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1273866), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1273866), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1269770), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1265680), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1265672), PairWithPosition.make(simpleSymbol9, PairWithPosition.make(make3, PairWithPosition.make(PairWithPosition.make(simpleSymbol10, PairWithPosition.make(make4, PairWithPosition.make(PairWithPosition.make(PairWithPosition.make(simpleSymbol11, Pair.make(simpleSymbol12, Pair.make(Pair.make(simpleSymbol13, Pair.make(simpleSymbol14, LList.Empty)), LList.Empty)), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1294351), PairWithPosition.make(Lit257, PairWithPosition.make(Lit256, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1294396), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1294379), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1294350), PairWithPosition.make(Lit260, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1298446), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1294350), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1286158), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1286154), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1286154), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1282064), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1282056), PairWithPosition.make(Lit247, PairWithPosition.make(PairWithPosition.make(Lit292, PairWithPosition.make(Lit256, PairWithPosition.make(Lit254, PairWithPosition.make(Lit259, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1306678), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1306675), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1306670), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1306640), PairWithPosition.make(PairWithPosition.make(PairWithPosition.make(Lit249, Pair.make(Lit252, Pair.make(Pair.make(Lit250, Pair.make(Lit261, LList.Empty)), LList.Empty)), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1310731), PairWithPosition.make(Lit257, PairWithPosition.make(Lit256, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1310780), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1310763), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1310730), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1310730), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1306640), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1306632), Lit264, PairWithPosition.make(Lit249, Pair.make(Lit252, Pair.make(Pair.make(Lit250, Pair.make(Lit262, LList.Empty)), LList.Empty)), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1323019), Lit326, PairWithPosition.make("-global-vars", LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1331241), PairWithPosition.make(Lit247, PairWithPosition.make(PairWithPosition.make(Lit315, PairWithPosition.make(Lit256, PairWithPosition.make(Lit254, PairWithPosition.make(Lit259, PairWithPosition.make(Lit258, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1339466), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1339447), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1339444), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1339439), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1339408), PairWithPosition.make(PairWithPosition.make(Lit255, PairWithPosition.make(PairWithPosition.make(Lit263, PairWithPosition.make(Boolean.FALSE, PairWithPosition.make("Adding ~A to env ~A with value ~A", PairWithPosition.make(Lit256, PairWithPosition.make(Lit264, PairWithPosition.make(Lit258, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1343591), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1343568), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1343563), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1343527), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1343524), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1343516), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1343516), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1343498), PairWithPosition.make(PairWithPosition.make(PairWithPosition.make(Lit249, Pair.make(Lit252, Pair.make(Pair.make(Lit250, Pair.make(Lit0, LList.Empty)), LList.Empty)), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1347595), PairWithPosition.make(Lit264, PairWithPosition.make(Lit256, PairWithPosition.make(Lit258, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1347651), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1347646), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1347623), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1347594), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1347594), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1343498), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1339408), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1339400), PairWithPosition.make(null, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1364008), (SimpleSymbol) new SimpleSymbol("form-name-symbol").readResolve(), Lit259, PairWithPosition.make(Lit247, PairWithPosition.make(Lit267, PairWithPosition.make(Lit254, PairWithPosition.make(Lit265, PairWithPosition.make(PairWithPosition.make(Lit244, PairWithPosition.make(LList.Empty, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1388600), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1388600), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1388599), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1388583), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1388580), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1388560), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1388552), PairWithPosition.make(Lit247, PairWithPosition.make(Lit272, PairWithPosition.make(Lit254, PairWithPosition.make(Lit265, PairWithPosition.make(PairWithPosition.make(Lit244, PairWithPosition.make(LList.Empty, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1409082), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1409082), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1409081), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1409065), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1409062), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1409040), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1409032), PairWithPosition.make(Lit247, PairWithPosition.make(PairWithPosition.make(Lit266, PairWithPosition.make(Lit269, PairWithPosition.make(Lit270, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1425454), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1425439), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1425424), PairWithPosition.make(PairWithPosition.make(Lit271, PairWithPosition.make(Lit267, PairWithPosition.make(PairWithPosition.make(Lit268, PairWithPosition.make(PairWithPosition.make(Lit268, PairWithPosition.make(Lit269, PairWithPosition.make(Lit270, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1433643), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1433628), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1433622), PairWithPosition.make(Lit267, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1437718), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1433622), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1433616), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1433616), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1429520), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1429514), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1429514), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1425424), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1425416), PairWithPosition.make(Lit247, PairWithPosition.make(PairWithPosition.make(Lit335, PairWithPosition.make(Lit273, PairWithPosition.make(Lit274, PairWithPosition.make(Lit269, PairWithPosition.make(Lit275, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1454160), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1454145), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1454130), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1454115), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1454096), PairWithPosition.make(PairWithPosition.make(Lit271, PairWithPosition.make(Lit272, PairWithPosition.make(PairWithPosition.make(Lit268, PairWithPosition.make(PairWithPosition.make(Lit7, PairWithPosition.make(Lit273, PairWithPosition.make(Lit274, PairWithPosition.make(Lit269, PairWithPosition.make(Lit275, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1462345), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1462330), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1462315), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1462300), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1462294), PairWithPosition.make(Lit272, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1466390), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1462294), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1462288), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1462288), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1458192), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1458186), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1458186), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1454096), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1454088), PairWithPosition.make(Lit247, PairWithPosition.make(Lit276, PairWithPosition.make(Lit254, PairWithPosition.make(Lit265, PairWithPosition.make(PairWithPosition.make(Lit244, PairWithPosition.make(LList.Empty, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1478715), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1478715), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1478714), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1478698), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1478695), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1478672), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1478664), PairWithPosition.make(Lit247, PairWithPosition.make(PairWithPosition.make(Lit245, PairWithPosition.make(Lit277, PairWithPosition.make(Lit278, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1490984), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1490980), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1490960), PairWithPosition.make(PairWithPosition.make(Lit271, PairWithPosition.make(Lit276, PairWithPosition.make(PairWithPosition.make(Lit268, PairWithPosition.make(PairWithPosition.make(Lit7, PairWithPosition.make(Lit277, PairWithPosition.make(Lit278, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1499168), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1499164), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1499158), PairWithPosition.make(Lit276, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1503254), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1499158), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1499152), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1499152), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1495056), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1495050), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1495050), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1490960), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1490952), PairWithPosition.make(Lit247, PairWithPosition.make(Lit280, PairWithPosition.make(Lit254, PairWithPosition.make(Lit265, PairWithPosition.make(PairWithPosition.make(Lit244, PairWithPosition.make(LList.Empty, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1523772), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1523772), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1523771), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1523755), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1523752), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1523728), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1523720), PairWithPosition.make(Lit247, PairWithPosition.make(PairWithPosition.make(Lit279, PairWithPosition.make(Lit281, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1531951), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1531920), PairWithPosition.make(PairWithPosition.make(Lit271, PairWithPosition.make(Lit280, PairWithPosition.make(PairWithPosition.make(Lit268, PairWithPosition.make(Lit281, PairWithPosition.make(Lit280, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1544214), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1540118), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1540112), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1540112), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1536016), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1536010), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1536010), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1531920), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1531912), PairWithPosition.make(Lit247, PairWithPosition.make(PairWithPosition.make(Lit285, PairWithPosition.make(Lit282, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1552412), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1552400), PairWithPosition.make(PairWithPosition.make(PairWithPosition.make(Lit249, Pair.make((SimpleSymbol) new SimpleSymbol("com.google.appinventor.components.runtime.util.RetValManager").readResolve(), Pair.make(Pair.make(Lit250, Pair.make((SimpleSymbol) new SimpleSymbol("sendError").readResolve(), LList.Empty)), LList.Empty)), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1556491), PairWithPosition.make(Lit282, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1556562), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1556490), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1556490), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1552400), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1552392), PairWithPosition.make(Lit301, PairWithPosition.make(Lit283, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1564707), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1564688), PairWithPosition.make(Lit307, PairWithPosition.make(Lit288, PairWithPosition.make((SimpleSymbol) new SimpleSymbol("<com.google.appinventor.components.runtime.errors.YailRuntimeError>").readResolve(), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1568809), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1568792), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1568778), PairWithPosition.make(PairWithPosition.make(Lit249, Pair.make((SimpleSymbol) new SimpleSymbol("com.google.appinventor.components.runtime.ReplApplication").readResolve(), Pair.make(Pair.make(Lit250, Pair.make((SimpleSymbol) new SimpleSymbol("reportError").readResolve(), LList.Empty)), LList.Empty)), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1576971), PairWithPosition.make(Lit283, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1577041), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1576970), Lit240, PairWithPosition.make(PairWithPosition.make(Lit284, PairWithPosition.make(PairWithPosition.make(PairWithPosition.make(Lit249, Pair.make(PairWithPosition.make(Lit286, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1585173), Pair.make(Pair.make(Lit250, Pair.make((SimpleSymbol) new SimpleSymbol("toastAllowed").readResolve(), LList.Empty)), LList.Empty)), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1585173), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1585172), PairWithPosition.make(PairWithPosition.make(Lit241, PairWithPosition.make(PairWithPosition.make(Lit285, PairWithPosition.make(PairWithPosition.make(PairWithPosition.make(Lit249, Pair.make(Lit283, Pair.make(Pair.make(Lit250, Pair.make(Lit287, LList.Empty)), LList.Empty)), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1589288), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1589287), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1589287), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1589275), PairWithPosition.make(PairWithPosition.make(PairWithPosition.make(Lit249, Pair.make(PairWithPosition.make(PairWithPosition.make(Lit249, Pair.make((SimpleSymbol) new SimpleSymbol("android.widget.Toast").readResolve(), Pair.make(Pair.make(Lit250, Pair.make((SimpleSymbol) new SimpleSymbol("makeText").readResolve(), LList.Empty)), LList.Empty)), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1593373), PairWithPosition.make(PairWithPosition.make(Lit286, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1593403), PairWithPosition.make(PairWithPosition.make(PairWithPosition.make(Lit249, Pair.make(Lit283, Pair.make(Pair.make(Lit250, Pair.make(Lit287, LList.Empty)), LList.Empty)), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1593411), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1593410), PairWithPosition.make(IntNum.make(5), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1593426), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1593410), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1593403), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1593372), Pair.make(Pair.make(Lit250, Pair.make((SimpleSymbol) new SimpleSymbol("show").readResolve(), LList.Empty)), LList.Empty)), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1593372), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1593371), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1593371), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1589275), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1589268), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1589268), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1585172), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1585166), PairWithPosition.make(PairWithPosition.make(PairWithPosition.make(Lit249, Pair.make((SimpleSymbol) new SimpleSymbol("com.google.appinventor.components.runtime.util.RuntimeErrorAlert").readResolve(), Pair.make(Pair.make(Lit250, Pair.make((SimpleSymbol) new SimpleSymbol("alert").readResolve(), LList.Empty)), LList.Empty)), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1601551), PairWithPosition.make(PairWithPosition.make(Lit286, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1605647), PairWithPosition.make(PairWithPosition.make(PairWithPosition.make(Lit249, Pair.make(Lit283, Pair.make(Pair.make(Lit250, Pair.make(Lit287, LList.Empty)), LList.Empty)), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1609744), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1609743), PairWithPosition.make(PairWithPosition.make(Lit240, PairWithPosition.make(PairWithPosition.make((SimpleSymbol) new SimpleSymbol(GetNamedPart.INSTANCEOF_METHOD_NAME).readResolve(), PairWithPosition.make(Lit283, PairWithPosition.make(Lit288, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1613857), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1613854), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1613843), PairWithPosition.make(PairWithPosition.make(PairWithPosition.make(Lit249, Pair.make(PairWithPosition.make(Lit289, PairWithPosition.make(Lit288, PairWithPosition.make(Lit283, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1613897), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1613880), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1613876), Pair.make(Pair.make(Lit250, Pair.make((SimpleSymbol) new SimpleSymbol("getErrorType").readResolve(), LList.Empty)), LList.Empty)), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1613876), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1613875), PairWithPosition.make("Runtime Error", LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1613915), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1613875), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1613843), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1613839), PairWithPosition.make("End Application", LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1617935), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1613839), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1609743), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1605647), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1601550), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1601550), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1585166), PairWithPosition.make(simpleSymbol15, PairWithPosition.make(make5, PairWithPosition.make(simpleSymbol16, PairWithPosition.make(simpleSymbol17, PairWithPosition.make(PairWithPosition.make(simpleSymbol18, PairWithPosition.make(make6, PairWithPosition.make(PairWithPosition.make(simpleSymbol19, PairWithPosition.make(make7, PairWithPosition.make(PairWithPosition.make(simpleSymbol20, PairWithPosition.make(make8, PairWithPosition.make(PairWithPosition.make(simpleSymbol21, PairWithPosition.make(make9, PairWithPosition.make(PairWithPosition.make(simpleSymbol22, PairWithPosition.make(PairWithPosition.make(simpleSymbol23, PairWithPosition.make(PairWithPosition.make(simpleSymbol24, PairWithPosition.make(simpleSymbol25, PairWithPosition.make(PairWithPosition.make(make10, PairWithPosition.make(simpleSymbol30, PairWithPosition.make(make11, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1720402), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1720397), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1720371), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1720371), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1720363), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1720356), PairWithPosition.make(Boolean.TRUE, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1724452), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1720356), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1716258), PairWithPosition.make(PairWithPosition.make(Lit300, PairWithPosition.make((SimpleSymbol) new SimpleSymbol("java.lang.Throwable").readResolve(), PairWithPosition.make(PairWithPosition.make(Lit241, PairWithPosition.make(PairWithPosition.make(Lit255, PairWithPosition.make(PairWithPosition.make(PairWithPosition.make(Lit249, Pair.make(Lit300, Pair.make(Pair.make(Lit250, Pair.make(Lit287, LList.Empty)), LList.Empty)), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1736760), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1736759), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1736759), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1736741), PairWithPosition.make(PairWithPosition.make(PairWithPosition.make(Lit249, Pair.make(Lit300, Pair.make(Pair.make(Lit250, Pair.make((SimpleSymbol) new SimpleSymbol("printStackTrace").readResolve(), LList.Empty)), LList.Empty)), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1744934), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1744933), PairWithPosition.make(PairWithPosition.make(Lit301, PairWithPosition.make(Lit300, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1749048), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1749029), PairWithPosition.make(Boolean.FALSE, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1753125), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1749029), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1744933), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1736741), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1732643), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1732643), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1728557), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1728546), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1728546), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1716258), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1712161), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1712161), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1683486), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1683481), PairWithPosition.make(Boolean.FALSE, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1757209), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1683481), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1679386), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1679382), PairWithPosition.make(PairWithPosition.make(Lit241, PairWithPosition.make(PairWithPosition.make(PairWithPosition.make(Lit249, Pair.make(Lit302, Pair.make(Pair.make(Lit250, Pair.make((SimpleSymbol) new SimpleSymbol("unregisterEventForDelegation").readResolve(), LList.Empty)), LList.Empty)), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1769497), PairWithPosition.make(PairWithPosition.make(Lit289, PairWithPosition.make(Lit303, PairWithPosition.make(PairWithPosition.make(Lit286, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1773664), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1773664), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1773598), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1773594), PairWithPosition.make(Lit291, PairWithPosition.make(Lit297, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1777714), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1777690), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1773594), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1769496), PairWithPosition.make(Boolean.FALSE, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1781784), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1769496), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1765398), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1765398), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1679382), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1675286), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1675282), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1675282), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1671185), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1671180), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1671180), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1646654), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1646651), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1634320), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1634312), PairWithPosition.make(Lit247, PairWithPosition.make(PairWithPosition.make(Lit304, PairWithPosition.make(Lit306, PairWithPosition.make(Lit297, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1789998), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1789984), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1789968), PairWithPosition.make(PairWithPosition.make(Lit295, PairWithPosition.make(PairWithPosition.make(Lit305, PairWithPosition.make(PairWithPosition.make(PairWithPosition.make(Lit249, Pair.make(Lit302, Pair.make(Pair.make(Lit250, Pair.make((SimpleSymbol) new SimpleSymbol("makeFullEventName").readResolve(), LList.Empty)), LList.Empty)), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1802253), PairWithPosition.make(Lit306, PairWithPosition.make(Lit297, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1806363), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1806349), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1802252), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1802252), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1798155), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1798155), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1794058), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1794058), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1789968), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1789960), PairWithPosition.make((SimpleSymbol) new SimpleSymbol("$define").readResolve(), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1822736), (SimpleSymbol) new SimpleSymbol("void").readResolve(), PairWithPosition.make(Lit247, PairWithPosition.make(PairWithPosition.make(Lit330, PairWithPosition.make(Lit311, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1835043), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1835026), PairWithPosition.make(PairWithPosition.make(Lit307, PairWithPosition.make(Lit308, PairWithPosition.make((SimpleSymbol) new SimpleSymbol("<com.google.appinventor.components.runtime.EventDispatcher>").readResolve(), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1843214), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1839130), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1839116), PairWithPosition.make(PairWithPosition.make(Lit312, PairWithPosition.make(PairWithPosition.make(Lit239, PairWithPosition.make(PairWithPosition.make(Lit310, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1847326), PairWithPosition.make(PairWithPosition.make(PairWithPosition.make(Lit249, Pair.make(Lit308, Pair.make(Pair.make(Lit250, Pair.make(Lit309, LList.Empty)), LList.Empty)), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1855513), PairWithPosition.make(PairWithPosition.make(Lit289, PairWithPosition.make(Lit303, PairWithPosition.make(PairWithPosition.make(Lit286, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1859679), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1859679), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1859613), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1859609), PairWithPosition.make(PairWithPosition.make(Lit313, PairWithPosition.make(Lit310, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1863710), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1863705), PairWithPosition.make(PairWithPosition.make((SimpleSymbol) new SimpleSymbol("cdr").readResolve(), PairWithPosition.make(Lit310, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1867806), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1867801), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1867801), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1863705), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1859609), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1855512), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1855512), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1847326), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1847318), PairWithPosition.make(Lit311, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1871894), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1847318), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1847308), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1847308), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1839116), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1835026), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1835018), PairWithPosition.make(Lit247, PairWithPosition.make(PairWithPosition.make(Lit332, PairWithPosition.make(Lit316, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1884201), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1884178), PairWithPosition.make(PairWithPosition.make(Lit312, PairWithPosition.make(PairWithPosition.make(Lit239, PairWithPosition.make(PairWithPosition.make(Lit314, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1892382), PairWithPosition.make(PairWithPosition.make(Lit246, PairWithPosition.make(PairWithPosition.make(PairWithPosition.make(Lit277, PairWithPosition.make(PairWithPosition.make(Lit313, PairWithPosition.make(Lit314, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1896488), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1896483), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1896483), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1896478), PairWithPosition.make(PairWithPosition.make(Lit278, PairWithPosition.make(PairWithPosition.make(Lit318, PairWithPosition.make(Lit314, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1900591), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1900585), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1900585), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1900574), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1900574), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1896477), PairWithPosition.make(PairWithPosition.make(Lit315, PairWithPosition.make(Lit277, PairWithPosition.make(PairWithPosition.make(Lit278, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1904701), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1904701), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1904697), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1904666), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1904666), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1896477), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1896472), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1896472), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1892382), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1892374), PairWithPosition.make(Lit316, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1908758), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1892374), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1892364), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1892364), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1884178), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1884170), PairWithPosition.make(Lit247, PairWithPosition.make(PairWithPosition.make(Lit334, PairWithPosition.make(Lit321, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1921059), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1921042), PairWithPosition.make(PairWithPosition.make(Lit312, PairWithPosition.make(PairWithPosition.make(Lit239, PairWithPosition.make(PairWithPosition.make(Lit317, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1925150), PairWithPosition.make(PairWithPosition.make(Lit246, PairWithPosition.make(PairWithPosition.make(PairWithPosition.make(Lit269, PairWithPosition.make(PairWithPosition.make(Lit322, PairWithPosition.make(Lit317, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1929269), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1929262), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1929262), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1929246), PairWithPosition.make(PairWithPosition.make(Lit275, PairWithPosition.make(PairWithPosition.make(Lit323, PairWithPosition.make(Lit317, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1933362), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1933354), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1933354), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1933342), PairWithPosition.make(PairWithPosition.make(Lit274, PairWithPosition.make(PairWithPosition.make(Lit318, PairWithPosition.make(Lit317, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1937460), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1937454), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1937454), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1937438), PairWithPosition.make(PairWithPosition.make(Lit319, PairWithPosition.make(PairWithPosition.make(Lit295, PairWithPosition.make(PairWithPosition.make(Lit313, PairWithPosition.make(Lit317, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1941588), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1941583), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1941583), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1941555), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1941555), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1941534), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1941534), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1937438), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1933342), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1929245), PairWithPosition.make(PairWithPosition.make(Lit246, PairWithPosition.make(PairWithPosition.make(PairWithPosition.make(Lit320, PairWithPosition.make(PairWithPosition.make(Lit262, PairWithPosition.make(Lit274, PairWithPosition.make(Lit319, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1957959), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1957944), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1957938), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1957938), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1957920), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1957919), PairWithPosition.make(PairWithPosition.make(Lit271, PairWithPosition.make(PairWithPosition.make(Lit324, PairWithPosition.make(PairWithPosition.make(Lit286, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1966121), PairWithPosition.make(Lit269, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1966128), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1966121), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1966114), PairWithPosition.make(Lit320, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1966144), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1966114), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1966108), PairWithPosition.make(PairWithPosition.make(Lit253, PairWithPosition.make(Lit269, PairWithPosition.make(Lit320, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1978436), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1978421), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1978396), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1978396), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1966108), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1957919), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1957914), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1957914), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1929245), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1929240), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1929240), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1925150), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1925142), PairWithPosition.make(Lit321, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1982486), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1925142), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1925132), PairWithPosition.make(PairWithPosition.make(Lit312, PairWithPosition.make(PairWithPosition.make(Lit239, PairWithPosition.make(PairWithPosition.make(Lit317, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2019358), PairWithPosition.make(PairWithPosition.make(Lit246, PairWithPosition.make(PairWithPosition.make(PairWithPosition.make(Lit269, PairWithPosition.make(PairWithPosition.make(Lit322, PairWithPosition.make(Lit317, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2023477), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2023470), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2023470), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2023454), PairWithPosition.make(PairWithPosition.make(Lit275, PairWithPosition.make(PairWithPosition.make(Lit323, PairWithPosition.make(Lit317, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2027570), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2027562), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2027562), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2027550), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2027550), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2023453), PairWithPosition.make(PairWithPosition.make(Lit284, PairWithPosition.make(Lit275, PairWithPosition.make(PairWithPosition.make(Lit275, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2035755), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2035755), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2035744), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2035738), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2035738), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2023453), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2023448), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2023448), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2019358), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2019350), PairWithPosition.make(Lit321, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2039830), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2019350), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2019340), PairWithPosition.make(PairWithPosition.make(Lit312, PairWithPosition.make(PairWithPosition.make(Lit239, PairWithPosition.make(PairWithPosition.make(Lit317, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2048030), PairWithPosition.make(PairWithPosition.make(Lit246, PairWithPosition.make(PairWithPosition.make(PairWithPosition.make(Lit269, PairWithPosition.make(PairWithPosition.make(Lit322, PairWithPosition.make(Lit317, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2052149), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2052142), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2052142), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2052126), PairWithPosition.make(PairWithPosition.make(Lit275, PairWithPosition.make(PairWithPosition.make(Lit323, PairWithPosition.make(Lit317, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2056242), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2056234), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2056234), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2056222), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2056222), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2052125), PairWithPosition.make(PairWithPosition.make(PairWithPosition.make(Lit249, Pair.make(PairWithPosition.make(Lit286, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2064411), Pair.make(Pair.make(Lit250, Pair.make((SimpleSymbol) new SimpleSymbol("callInitialize").readResolve(), LList.Empty)), LList.Empty)), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2064411), PairWithPosition.make(PairWithPosition.make(Lit324, PairWithPosition.make(PairWithPosition.make(Lit286, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2064440), PairWithPosition.make(Lit269, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2064447), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2064440), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2064433), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2064433), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2064410), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2064410), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2052125), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2052120), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2052120), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2048030), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2048022), PairWithPosition.make(Lit321, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2068502), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2048022), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2048012), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2048012), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2019340), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1925132), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1921042), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1921034), PairWithPosition.make(Lit247, PairWithPosition.make(PairWithPosition.make(Lit74, Lit328, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2080786), PairWithPosition.make(PairWithPosition.make(Lit305, PairWithPosition.make(PairWithPosition.make(Lit325, PairWithPosition.make(Lit326, PairWithPosition.make(PairWithPosition.make((SimpleSymbol) new SimpleSymbol("map").readResolve(), PairWithPosition.make(Lit327, PairWithPosition.make(Lit328, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2093096), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2093081), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2093076), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2093076), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2088980), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2088973), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2088973), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2084876), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2084876), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2080786), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2080778), PairWithPosition.make(PairWithPosition.make(Lit249, Pair.make((SimpleSymbol) new SimpleSymbol("gnu.expr.Language").readResolve(), Pair.make(Pair.make(Lit250, Pair.make((SimpleSymbol) new SimpleSymbol("setDefaults").readResolve(), LList.Empty)), LList.Empty)), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2113547), PairWithPosition.make(PairWithPosition.make(PairWithPosition.make(Lit249, Pair.make((SimpleSymbol) new SimpleSymbol("kawa.standard.Scheme").readResolve(), Pair.make(Pair.make(Lit250, Pair.make((SimpleSymbol) new SimpleSymbol("getInstance").readResolve(), LList.Empty)), LList.Empty)), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2113578), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2113577), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2113577), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2113546), PairWithPosition.make(Lit329, PairWithPosition.make(PairWithPosition.make((SimpleSymbol) new SimpleSymbol("invoke").readResolve(), PairWithPosition.make(PairWithPosition.make(Lit286, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2150419), PairWithPosition.make(PairWithPosition.make(Lit244, PairWithPosition.make((SimpleSymbol) new SimpleSymbol("run").readResolve(), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2150427), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2150427), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2150426), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2150419), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2150411), PairWithPosition.make(PairWithPosition.make(Lit300, PairWithPosition.make((SimpleSymbol) new SimpleSymbol("java.lang.Exception").readResolve(), PairWithPosition.make(PairWithPosition.make(Lit255, PairWithPosition.make(PairWithPosition.make(PairWithPosition.make(Lit249, Pair.make(Lit300, Pair.make(Pair.make(Lit250, Pair.make(Lit287, LList.Empty)), LList.Empty)), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2158623), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2158622), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2158622), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2158604), PairWithPosition.make(PairWithPosition.make(Lit301, PairWithPosition.make(Lit300, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2162719), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2162700), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2162700), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2158604), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2154518), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2154507), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2154507), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2150411), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2146314), Lit271, PairWithPosition.make(PairWithPosition.make(Lit286, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2166810), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2166810), Lit253, PairWithPosition.make(PairWithPosition.make(Lit286, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2175022), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2175022), PairWithPosition.make(PairWithPosition.make(Lit330, PairWithPosition.make(Lit267, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2183195), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2183178), PairWithPosition.make(PairWithPosition.make(Lit329, PairWithPosition.make(PairWithPosition.make(Lit241, PairWithPosition.make(PairWithPosition.make(Lit245, PairWithPosition.make(PairWithPosition.make(Lit244, PairWithPosition.make(Lit331, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2215970), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2215970), PairWithPosition.make(PairWithPosition.make(Lit239, PairWithPosition.make(LList.Empty, PairWithPosition.make(null, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2215998), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2215995), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2215987), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2215987), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2215969), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2215949), PairWithPosition.make(PairWithPosition.make(Lit332, PairWithPosition.make(PairWithPosition.make(Lit333, PairWithPosition.make(Lit276, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2240557), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2240548), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2240548), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2240525), PairWithPosition.make(PairWithPosition.make(Lit312, PairWithPosition.make((SimpleSymbol) new SimpleSymbol("force").readResolve(), PairWithPosition.make(PairWithPosition.make(Lit333, PairWithPosition.make(Lit280, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2244646), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2244637), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2244637), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2244631), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2244621), PairWithPosition.make(PairWithPosition.make(Lit334, PairWithPosition.make(PairWithPosition.make(Lit333, PairWithPosition.make(Lit272, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2248743), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2248734), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2248734), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2248717), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2248717), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2244621), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2240525), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2215949), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2195467), PairWithPosition.make(PairWithPosition.make(Lit300, PairWithPosition.make((SimpleSymbol) new SimpleSymbol("com.google.appinventor.components.runtime.errors.YailRuntimeError").readResolve(), PairWithPosition.make(PairWithPosition.make(Lit301, PairWithPosition.make(Lit300, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2261033), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2261014), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2261014), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2252822), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2252811), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2252811), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2195467), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2191370), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2191370), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 2183178)}, 0)}, 4);
        Lit72 = (SimpleSymbol) new SimpleSymbol("define-form-internal").readResolve();
        Lit71 = new SyntaxRules(new Object[]{Lit237}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\f\u000f\b", new Object[0], 2), "\u0001\u0001", "\u0011\u0018\u0004\t\u0003\t\u000b\u0018\f", new Object[]{Lit72, PairWithPosition.make(PairWithPosition.make(Lit244, PairWithPosition.make((SimpleSymbol) new SimpleSymbol("com.google.appinventor.components.runtime.ReplForm").readResolve(), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1175602), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1175602), PairWithPosition.make(Boolean.TRUE, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1175653), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1175601)}, 0)}, 2);
        Lit70 = (SimpleSymbol) new SimpleSymbol("define-repl-form").readResolve();
        Lit69 = new SyntaxRules(new Object[]{Lit237}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\f\u000f\b", new Object[0], 2), "\u0001\u0001", "\u0011\u0018\u0004\t\u0003\t\u000b\u0018\f", new Object[]{Lit72, PairWithPosition.make(PairWithPosition.make(Lit244, PairWithPosition.make((SimpleSymbol) new SimpleSymbol("com.google.appinventor.components.runtime.Form").readResolve(), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1155122), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1155122), PairWithPosition.make(Boolean.FALSE, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1155169), "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 1155121)}, 0)}, 2);
        Lit68 = (SimpleSymbol) new SimpleSymbol("define-form").readResolve();
        Lit67 = new SyntaxRules(new Object[]{Lit237}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\r\u0007\u0000\b\b", new Object[0], 1), "\u0003", "\u0011\u0018\u0004\b\u0005\u0011\u0018\f\t\u0010\b\u0003", new Object[]{Lit145, Lit239}, 1)}, 1);
        Lit66 = (SimpleSymbol) new SimpleSymbol("or-delayed").readResolve();
        Lit65 = new SyntaxRules(new Object[]{Lit237}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\r\u0007\u0000\b\b", new Object[0], 1), "\u0003", "\u0011\u0018\u0004\b\u0005\u0011\u0018\f\t\u0010\b\u0003", new Object[]{Lit144, Lit239}, 1)}, 1);
        Lit64 = (SimpleSymbol) new SimpleSymbol("and-delayed").readResolve();
        Lit63 = new SyntaxRules(new Object[]{Lit237}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\f\u000f\b", new Object[0], 2), "\u0001\u0001", "\u0011\u0018\u0004\t\u0003\b\u000b", new Object[]{Lit271}, 0)}, 2);
        Lit62 = (SimpleSymbol) new SimpleSymbol("set-lexical!").readResolve();
        Lit61 = new SyntaxRules(new Object[]{Lit237}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\b", new Object[0], 1), "\u0001", "\u0003", new Object[0], 0)}, 1);
        Lit60 = (SimpleSymbol) new SimpleSymbol("lexical-value").readResolve();
        Lit59 = new SyntaxRules(new Object[]{Lit237}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\f\u000f\b", new Object[0], 2), "\u0001\u0001", "\u0011\u0018\u0004)\u0011\u0018\f\b\u0003\b\u000b", new Object[]{Lit99, Lit244}, 0)}, 2);
        Lit58 = (SimpleSymbol) new SimpleSymbol("set-var!").readResolve();
        Lit57 = new SyntaxRules(new Object[]{Lit237}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\b", new Object[0], 1), "\u0001", "\u0011\u0018\u0004)\u0011\u0018\f\b\u0003\u0018\u0014", new Object[]{Lit100, Lit244, PairWithPosition.make(Lit331, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 962623)}, 0)}, 1);
        Lit56 = (SimpleSymbol) new SimpleSymbol("get-var").readResolve();
        Lit55 = (SimpleSymbol) new SimpleSymbol("set-and-coerce-property-and-check!").readResolve();
        Lit54 = (SimpleSymbol) new SimpleSymbol("get-property-and-check").readResolve();
        Lit53 = (SimpleSymbol) new SimpleSymbol("coerce-to-component-and-verify").readResolve();
        Lit52 = (SimpleSymbol) new SimpleSymbol("get-property").readResolve();
        Lit51 = (SimpleSymbol) new SimpleSymbol("set-and-coerce-property!").readResolve();
        Lit50 = (SimpleSymbol) new SimpleSymbol("lookup-component").readResolve();
        Lit49 = new SyntaxRules(new Object[]{Lit237}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\b", new Object[0], 1), "\u0001", "\u0011\u0018\u0004\b\u0011\u0018\f\b\u0003", new Object[]{Lit96, Lit244}, 0)}, 1);
        Lit48 = (SimpleSymbol) new SimpleSymbol("get-component").readResolve();
        Lit47 = (SimpleSymbol) new SimpleSymbol("clear-init-thunks").readResolve();
        Lit46 = (SimpleSymbol) new SimpleSymbol("get-init-thunk").readResolve();
        Lit45 = (SimpleSymbol) new SimpleSymbol("add-init-thunk").readResolve();
        Lit44 = (SimpleSymbol) new SimpleSymbol("call-Initialize-of-components").readResolve();
        Lit43 = (SimpleSymbol) new SimpleSymbol("add-component-within-repl").readResolve();
        Object[] objArr3 = {Lit237};
        SyntaxPattern syntaxPattern3 = new SyntaxPattern("\f\u0018\f\u0007\f\u000f\f\u0017\b", new Object[0], 3);
        SimpleSymbol simpleSymbol31 = (SimpleSymbol) new SimpleSymbol("gen-simple-component-type").readResolve();
        Lit38 = simpleSymbol31;
        Lit42 = new SyntaxRules(objArr3, new SyntaxRule[]{new SyntaxRule(syntaxPattern3, "\u0001\u0001\u0001", "\u0011\u0018\u0004\u0081\u0011\u0018\f\t\u0013\u0011\u0018\u0014)\u0011\u0018\u001c\b\u000b\u0018$\b\u0011\u0018,\u0011\u00184¹\u0011\u0018<)\u0011\u0018D\b\u0003)\u0011\u0018\u001c\b\u000b)\u0011\u0018D\b\u0013\u0018L\b\u0011\u0018T)\u0011\u0018D\b\u0003)\u0011\u0018\u001c\b\u000b)\u0011\u0018D\b\u0013\u0018\\", new Object[]{Lit241, Lit247, Lit254, simpleSymbol31, PairWithPosition.make(null, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 221261), Lit240, Lit243, Lit43, Lit244, PairWithPosition.make(Boolean.FALSE, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 241703), Lit335, PairWithPosition.make(Boolean.FALSE, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 258079)}, 0), new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\f\u000f\f\u0017\r\u001f\u0018\b\b", new Object[0], 4), "\u0001\u0001\u0001\u0003", "\u0011\u0018\u0004\u0081\u0011\u0018\f\t\u0013\u0011\u0018\u0014)\u0011\u0018\u001c\b\u000b\u0018$\b\u0011\u0018,\u0011\u00184ñ\u0011\u0018<)\u0011\u0018D\b\u0003)\u0011\u0018\u001c\b\u000b)\u0011\u0018D\b\u0013\b\u0011\u0018L\t\u0010\b\u001d\u001b\b\u0011\u0018T)\u0011\u0018D\b\u0003)\u0011\u0018\u001c\b\u000b)\u0011\u0018D\b\u0013\b\u0011\u0018L\t\u0010\b\u001d\u001b", new Object[]{Lit241, Lit247, Lit254, Lit38, PairWithPosition.make(null, LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 270413), Lit240, Lit243, Lit43, Lit244, Lit239, Lit335}, 1)}, 4);
        Lit41 = (SimpleSymbol) new SimpleSymbol("add-component").readResolve();
        Lit40 = new SyntaxTemplate("\u0001\u0001", "\u000b", new Object[0], 0);
        Lit39 = new SyntaxPattern("\f\u0007\f\u000f\b", new Object[0], 2);
        Lit37 = (SimpleSymbol) new SimpleSymbol("android-log").readResolve();
        Lit36 = (SimpleSymbol) new SimpleSymbol("post").readResolve();
        Lit35 = (SimpleSymbol) new SimpleSymbol("getDhcpInfo").readResolve();
        Lit34 = IntNum.make(9999);
        Lit33 = IntNum.make(4);
        Lit32 = IntNum.make(3);
        Lit31 = IntNum.make(16);
        Lit30 = IntNum.make(24);
        Lit29 = IntNum.make(8);
        Lit28 = IntNum.make(255);
        Lit26 = (SimpleSymbol) new SimpleSymbol("*list*").readResolve();
        Lit25 = IntNum.make(360);
        Lit24 = DFloNum.make(6.2831853d);
        Lit23 = DFloNum.make(6.2831853d);
        Lit22 = IntNum.make(180);
        Lit21 = DFloNum.make(3.14159265d);
        Lit20 = DFloNum.make(0.0d);
        Lit19 = IntNum.make(30);
        Lit18 = IntNum.make(2);
        Lit16 = IntNum.make(1);
        Lit15 = DFloNum.make(Double.NEGATIVE_INFINITY);
        Lit14 = DFloNum.make(Double.POSITIVE_INFINITY);
        Lit13 = (SimpleSymbol) new SimpleSymbol("Form").readResolve();
        Lit12 = (SimpleSymbol) new SimpleSymbol("Screen").readResolve();
        Lit11 = (SimpleSymbol) new SimpleSymbol("com.google.appinventor.components.runtime.").readResolve();
        Lit10 = (SimpleSymbol) new SimpleSymbol("any").readResolve();
        Lit9 = (SimpleSymbol) new SimpleSymbol("component").readResolve();
        Lit8 = (SimpleSymbol) new SimpleSymbol("InstantInTime").readResolve();
        Lit5 = (SimpleSymbol) new SimpleSymbol(PropertyTypeConstants.PROPERTY_TYPE_TEXT).readResolve();
        Lit4 = (SimpleSymbol) new SimpleSymbol("number").readResolve();
        Lit3 = (SimpleSymbol) new SimpleSymbol("remove").readResolve();
        Lit2 = PairWithPosition.make((SimpleSymbol) new SimpleSymbol("non-coercible").readResolve(), LList.Empty, "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm", 3399712);
        JavaIterator = Iterator.class;
        JavaCollection = Collection.class;
        YailRuntimeError = YailRuntimeError.class;
        YailNumberToString = YailNumberToString.class;
        YailList = YailList.class;
        Pattern = Pattern.class;
        String = String.class;
        Short = Short.class;
        Long = Long.class;
        KawaEnvironment = Environment.class;
        Integer = Integer.class;
        Float = Float.class;
        Double = Double.class;
        CsvUtil = CsvUtil.class;
        SimpleForm = ClassType.make("com.google.appinventor.components.runtime.Form");
        $instance = new runtime();
        runtime runtimeVar = $instance;
        android$Mnlog = new ModuleMethod(runtimeVar, 9, Lit37, 4097);
        simple$Mncomponent$Mnpackage$Mnname = "com.google.appinventor.components.runtime";
        SimpleSymbol simpleSymbol32 = Lit38;
        ModuleMethod moduleMethod = new ModuleMethod(runtimeVar, 10, null, 4097);
        moduleMethod.setProperty("source-location", "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm:35");
        gen$Mnsimple$Mncomponent$Mntype = Macro.make(simpleSymbol32, moduleMethod, $instance);
        add$Mncomponent = Macro.make(Lit41, Lit42, $instance);
        add$Mncomponent$Mnwithin$Mnrepl = new ModuleMethod(runtimeVar, 11, Lit43, 16388);
        call$MnInitialize$Mnof$Mncomponents = new ModuleMethod(runtimeVar, 12, Lit44, -4096);
        add$Mninit$Mnthunk = new ModuleMethod(runtimeVar, 13, Lit45, 8194);
        get$Mninit$Mnthunk = new ModuleMethod(runtimeVar, 14, Lit46, 4097);
        clear$Mninit$Mnthunks = new ModuleMethod(runtimeVar, 15, Lit47, 0);
        get$Mncomponent = Macro.make(Lit48, Lit49, $instance);
        lookup$Mncomponent = new ModuleMethod(runtimeVar, 16, Lit50, 4097);
        set$Mnand$Mncoerce$Mnproperty$Ex = new ModuleMethod(runtimeVar, 17, Lit51, 16388);
        get$Mnproperty = new ModuleMethod(runtimeVar, 18, Lit52, 8194);
        coerce$Mnto$Mncomponent$Mnand$Mnverify = new ModuleMethod(runtimeVar, 19, Lit53, 4097);
        get$Mnproperty$Mnand$Mncheck = new ModuleMethod(runtimeVar, 20, Lit54, 12291);
        set$Mnand$Mncoerce$Mnproperty$Mnand$Mncheck$Ex = new ModuleMethod(runtimeVar, 21, Lit55, 20485);
        get$Mnvar = Macro.make(Lit56, Lit57, $instance);
        set$Mnvar$Ex = Macro.make(Lit58, Lit59, $instance);
        lexical$Mnvalue = Macro.make(Lit60, Lit61, $instance);
        set$Mnlexical$Ex = Macro.make(Lit62, Lit63, $instance);
        and$Mndelayed = Macro.make(Lit64, Lit65, $instance);
        or$Mndelayed = Macro.make(Lit66, Lit67, $instance);
        define$Mnform = Macro.make(Lit68, Lit69, $instance);
        define$Mnrepl$Mnform = Macro.make(Lit70, Lit71, $instance);
        define$Mnform$Mninternal = Macro.make(Lit72, Lit73, $instance);
        symbol$Mnappend = new ModuleMethod(runtimeVar, 22, Lit74, -4096);
        SimpleSymbol simpleSymbol33 = Lit75;
        ModuleMethod moduleMethod2 = new ModuleMethod(runtimeVar, 23, null, 4097);
        moduleMethod2.setProperty("source-location", "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm:566");
        gen$Mnevent$Mnname = Macro.make(simpleSymbol33, moduleMethod2, $instance);
        define$Mnevent$Mnhelper = Macro.make(Lit78, Lit79, $instance);
        $Stlist$Mnfor$Mnruntime$St = Macro.make(Lit80, Lit81, $instance);
        SimpleSymbol simpleSymbol34 = Lit82;
        ModuleMethod moduleMethod3 = new ModuleMethod(runtimeVar, 24, null, 4097);
        moduleMethod3.setProperty("source-location", "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm:622");
        define$Mnevent = Macro.make(simpleSymbol34, moduleMethod3, $instance);
        def = Macro.make(Lit91, Lit92, $instance);
        do$Mnafter$Mnform$Mncreation = Macro.make(Lit93, Lit94, $instance);
        add$Mnto$Mncurrent$Mnform$Mnenvironment = new ModuleMethod(runtimeVar, 25, Lit95, 8194);
        lookup$Mnin$Mncurrent$Mnform$Mnenvironment = new ModuleMethod(runtimeVar, 26, Lit96, 8193);
        delete$Mnfrom$Mncurrent$Mnform$Mnenvironment = new ModuleMethod(runtimeVar, 28, Lit97, 4097);
        rename$Mnin$Mncurrent$Mnform$Mnenvironment = new ModuleMethod(runtimeVar, 29, Lit98, 8194);
        add$Mnglobal$Mnvar$Mnto$Mncurrent$Mnform$Mnenvironment = new ModuleMethod(runtimeVar, 30, Lit99, 8194);
        lookup$Mnglobal$Mnvar$Mnin$Mncurrent$Mnform$Mnenvironment = new ModuleMethod(runtimeVar, 31, Lit100, 8193);
        reset$Mncurrent$Mnform$Mnenvironment = new ModuleMethod(runtimeVar, 33, Lit101, 0);
        foreach = Macro.make(Lit102, Lit103, $instance);
        forrange = Macro.make(Lit104, Lit105, $instance);
        f0while = Macro.make(Lit106, Lit107, $instance);
        call$Mncomponent$Mnmethod = new ModuleMethod(runtimeVar, 34, Lit108, 16388);
        call$Mncomponent$Mntype$Mnmethod = new ModuleMethod(runtimeVar, 35, Lit109, 20485);
        call$Mnyail$Mnprimitive = new ModuleMethod(runtimeVar, 36, Lit110, 16388);
        sanitize$Mncomponent$Mndata = new ModuleMethod(runtimeVar, 37, Lit111, 4097);
        java$Mncollection$Mn$Gryail$Mnlist = new ModuleMethod(runtimeVar, 38, Lit112, 4097);
        java$Mncollection$Mn$Grkawa$Mnlist = new ModuleMethod(runtimeVar, 39, Lit113, 4097);
        sanitize$Mnatomic = new ModuleMethod(runtimeVar, 40, Lit114, 4097);
        signal$Mnruntime$Mnerror = new ModuleMethod(runtimeVar, 41, Lit115, 8194);
        yail$Mnnot = new ModuleMethod(runtimeVar, 42, Lit116, 4097);
        call$Mnwith$Mncoerced$Mnargs = new ModuleMethod(runtimeVar, 43, Lit117, 16388);
        $Pcset$Mnand$Mncoerce$Mnproperty$Ex = new ModuleMethod(runtimeVar, 44, Lit118, 16388);
        $Pcset$Mnsubform$Mnlayout$Mnproperty$Ex = new ModuleMethod(runtimeVar, 45, Lit119, 12291);
        generate$Mnruntime$Mntype$Mnerror = new ModuleMethod(runtimeVar, 46, Lit120, 8194);
        show$Mnarglist$Mnno$Mnparens = new ModuleMethod(runtimeVar, 47, Lit121, 4097);
        coerce$Mnargs = new ModuleMethod(runtimeVar, 48, Lit122, 12291);
        coerce$Mnarg = new ModuleMethod(runtimeVar, 49, Lit123, 8194);
        coerce$Mnto$Mntext = new ModuleMethod(runtimeVar, 50, Lit124, 4097);
        coerce$Mnto$Mninstant = new ModuleMethod(runtimeVar, 51, Lit125, 4097);
        coerce$Mnto$Mncomponent = new ModuleMethod(runtimeVar, 52, Lit126, 4097);
        coerce$Mnto$Mncomponent$Mnof$Mntype = new ModuleMethod(runtimeVar, 53, Lit127, 8194);
        type$Mn$Grclass = new ModuleMethod(runtimeVar, 54, Lit128, 4097);
        coerce$Mnto$Mnnumber = new ModuleMethod(runtimeVar, 55, Lit129, 4097);
        coerce$Mnto$Mnstring = new ModuleMethod(runtimeVar, 56, Lit130, 4097);
        ModuleMethod moduleMethod4 = new ModuleMethod(runtimeVar, 57, null, 4097);
        moduleMethod4.setProperty("source-location", "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm:1200");
        lambda$Fn4 = moduleMethod4;
        string$Mnreplace = new ModuleMethod(runtimeVar, 58, Lit131, 8194);
        coerce$Mnto$Mnyail$Mnlist = new ModuleMethod(runtimeVar, 59, Lit132, 4097);
        coerce$Mnto$Mnboolean = new ModuleMethod(runtimeVar, 60, Lit133, 4097);
        is$Mncoercible$Qu = new ModuleMethod(runtimeVar, 61, Lit134, 4097);
        all$Mncoercible$Qu = new ModuleMethod(runtimeVar, 62, Lit135, 4097);
        boolean$Mn$Grstring = new ModuleMethod(runtimeVar, 63, Lit136, 4097);
        padded$Mnstring$Mn$Grnumber = new ModuleMethod(runtimeVar, 64, Lit137, 4097);
        $Stformat$Mninexact$St = new ModuleMethod(runtimeVar, 65, Lit138, 4097);
        appinventor$Mnnumber$Mn$Grstring = new ModuleMethod(runtimeVar, 66, Lit139, 4097);
        yail$Mnequal$Qu = new ModuleMethod(runtimeVar, 67, Lit140, 8194);
        yail$Mnatomic$Mnequal$Qu = new ModuleMethod(runtimeVar, 68, Lit141, 8194);
        as$Mnnumber = new ModuleMethod(runtimeVar, 69, Lit142, 4097);
        yail$Mnnot$Mnequal$Qu = new ModuleMethod(runtimeVar, 70, Lit143, 8194);
        process$Mnand$Mndelayed = new ModuleMethod(runtimeVar, 71, Lit144, -4096);
        process$Mnor$Mndelayed = new ModuleMethod(runtimeVar, 72, Lit145, -4096);
        yail$Mnfloor = new ModuleMethod(runtimeVar, 73, Lit146, 4097);
        yail$Mnceiling = new ModuleMethod(runtimeVar, 74, Lit147, 4097);
        yail$Mnround = new ModuleMethod(runtimeVar, 75, Lit148, 4097);
        random$Mnset$Mnseed = new ModuleMethod(runtimeVar, 76, Lit149, 4097);
        random$Mnfraction = new ModuleMethod(runtimeVar, 77, Lit150, 0);
        random$Mninteger = new ModuleMethod(runtimeVar, 78, Lit151, 8194);
        ModuleMethod moduleMethod5 = new ModuleMethod(runtimeVar, 79, null, 4097);
        moduleMethod5.setProperty("source-location", "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm:1423");
        lambda$Fn9 = moduleMethod5;
        yail$Mndivide = new ModuleMethod(runtimeVar, 80, Lit152, 8194);
        degrees$Mn$Grradians$Mninternal = new ModuleMethod(runtimeVar, 81, Lit153, 4097);
        radians$Mn$Grdegrees$Mninternal = new ModuleMethod(runtimeVar, 82, Lit154, 4097);
        degrees$Mn$Grradians = new ModuleMethod(runtimeVar, 83, Lit155, 4097);
        radians$Mn$Grdegrees = new ModuleMethod(runtimeVar, 84, Lit156, 4097);
        sin$Mndegrees = new ModuleMethod(runtimeVar, 85, Lit157, 4097);
        cos$Mndegrees = new ModuleMethod(runtimeVar, 86, Lit158, 4097);
        tan$Mndegrees = new ModuleMethod(runtimeVar, 87, Lit159, 4097);
        asin$Mndegrees = new ModuleMethod(runtimeVar, 88, Lit160, 4097);
        acos$Mndegrees = new ModuleMethod(runtimeVar, 89, Lit161, 4097);
        atan$Mndegrees = new ModuleMethod(runtimeVar, 90, Lit162, 4097);
        atan2$Mndegrees = new ModuleMethod(runtimeVar, 91, Lit163, 8194);
        string$Mnto$Mnupper$Mncase = new ModuleMethod(runtimeVar, 92, Lit164, 4097);
        string$Mnto$Mnlower$Mncase = new ModuleMethod(runtimeVar, 93, Lit165, 4097);
        format$Mnas$Mndecimal = new ModuleMethod(runtimeVar, 94, Lit166, 8194);
        is$Mnnumber$Qu = new ModuleMethod(runtimeVar, 95, Lit167, 4097);
        yail$Mnlist$Qu = new ModuleMethod(runtimeVar, 96, Lit168, 4097);
        yail$Mnlist$Mncandidate$Qu = new ModuleMethod(runtimeVar, 97, Lit169, 4097);
        yail$Mnlist$Mncontents = new ModuleMethod(runtimeVar, 98, Lit170, 4097);
        set$Mnyail$Mnlist$Mncontents$Ex = new ModuleMethod(runtimeVar, 99, Lit171, 8194);
        insert$Mnyail$Mnlist$Mnheader = new ModuleMethod(runtimeVar, 100, Lit172, 4097);
        kawa$Mnlist$Mn$Gryail$Mnlist = new ModuleMethod(runtimeVar, ErrorMessages.ERROR_LOCATION_SENSOR_LATITUDE_NOT_FOUND, Lit173, 4097);
        yail$Mnlist$Mn$Grkawa$Mnlist = new ModuleMethod(runtimeVar, ErrorMessages.ERROR_LOCATION_SENSOR_LONGITUDE_NOT_FOUND, Lit174, 4097);
        yail$Mnlist$Mnempty$Qu = new ModuleMethod(runtimeVar, 103, Lit175, 4097);
        make$Mnyail$Mnlist = new ModuleMethod(runtimeVar, 104, Lit176, -4096);
        yail$Mnlist$Mncopy = new ModuleMethod(runtimeVar, 105, Lit177, 4097);
        yail$Mnlist$Mnto$Mncsv$Mntable = new ModuleMethod(runtimeVar, 106, Lit178, 4097);
        yail$Mnlist$Mnto$Mncsv$Mnrow = new ModuleMethod(runtimeVar, 107, Lit179, 4097);
        convert$Mnto$Mnstrings = new ModuleMethod(runtimeVar, 108, Lit180, 4097);
        yail$Mnlist$Mnfrom$Mncsv$Mntable = new ModuleMethod(runtimeVar, YaVersion.YOUNG_ANDROID_VERSION, Lit181, 4097);
        yail$Mnlist$Mnfrom$Mncsv$Mnrow = new ModuleMethod(runtimeVar, 110, Lit182, 4097);
        yail$Mnlist$Mnlength = new ModuleMethod(runtimeVar, 111, Lit183, 4097);
        yail$Mnlist$Mnindex = new ModuleMethod(runtimeVar, DateTime.TIME_MASK, Lit184, 8194);
        yail$Mnlist$Mnget$Mnitem = new ModuleMethod(runtimeVar, 113, Lit185, 8194);
        yail$Mnlist$Mnset$Mnitem$Ex = new ModuleMethod(runtimeVar, 114, Lit186, 12291);
        yail$Mnlist$Mnremove$Mnitem$Ex = new ModuleMethod(runtimeVar, 115, Lit187, 8194);
        yail$Mnlist$Mninsert$Mnitem$Ex = new ModuleMethod(runtimeVar, 116, Lit188, 12291);
        yail$Mnlist$Mnappend$Ex = new ModuleMethod(runtimeVar, 117, Lit189, 8194);
        yail$Mnlist$Mnadd$Mnto$Mnlist$Ex = new ModuleMethod(runtimeVar, 118, Lit190, -4095);
        yail$Mnlist$Mnmember$Qu = new ModuleMethod(runtimeVar, 119, Lit191, 8194);
        yail$Mnlist$Mnpick$Mnrandom = new ModuleMethod(runtimeVar, 120, Lit192, 4097);
        yail$Mnfor$Mneach = new ModuleMethod(runtimeVar, 121, Lit193, 8194);
        yail$Mnfor$Mnrange = new ModuleMethod(runtimeVar, 122, Lit194, 16388);
        yail$Mnfor$Mnrange$Mnwith$Mnnumeric$Mnchecked$Mnargs = new ModuleMethod(runtimeVar, 123, Lit195, 16388);
        yail$Mnnumber$Mnrange = new ModuleMethod(runtimeVar, 124, Lit196, 8194);
        yail$Mnalist$Mnlookup = new ModuleMethod(runtimeVar, 125, Lit197, 12291);
        pair$Mnok$Qu = new ModuleMethod(runtimeVar, 126, Lit198, 4097);
        make$Mndisjunct = new ModuleMethod(runtimeVar, 127, Lit199, 4097);
        array$Mn$Grlist = new ModuleMethod(runtimeVar, DateTime.TIMEZONE_MASK, Lit200, 4097);
        string$Mnstarts$Mnat = new ModuleMethod(runtimeVar, 129, Lit201, 8194);
        string$Mncontains = new ModuleMethod(runtimeVar, 130, Lit202, 8194);
        string$Mnsplit$Mnat$Mnfirst = new ModuleMethod(runtimeVar, 131, Lit203, 8194);
        string$Mnsplit$Mnat$Mnfirst$Mnof$Mnany = new ModuleMethod(runtimeVar, 132, Lit204, 8194);
        string$Mnsplit = new ModuleMethod(runtimeVar, 133, Lit205, 8194);
        string$Mnsplit$Mnat$Mnany = new ModuleMethod(runtimeVar, 134, Lit206, 8194);
        string$Mnsplit$Mnat$Mnspaces = new ModuleMethod(runtimeVar, 135, Lit207, 4097);
        string$Mnsubstring = new ModuleMethod(runtimeVar, 136, Lit208, 12291);
        string$Mntrim = new ModuleMethod(runtimeVar, 137, Lit209, 4097);
        string$Mnreplace$Mnall = new ModuleMethod(runtimeVar, 138, Lit210, 12291);
        string$Mnempty$Qu = new ModuleMethod(runtimeVar, 139, Lit211, 4097);
        text$Mndeobsfucate = new ModuleMethod(runtimeVar, 140, Lit212, 8194);
        make$Mnexact$Mnyail$Mninteger = new ModuleMethod(runtimeVar, 141, Lit213, 4097);
        make$Mncolor = new ModuleMethod(runtimeVar, 142, Lit214, 4097);
        split$Mncolor = new ModuleMethod(runtimeVar, 143, Lit215, 4097);
        close$Mnscreen = new ModuleMethod(runtimeVar, ComponentConstants.VIDEOPLAYER_PREFERRED_HEIGHT, Lit216, 0);
        close$Mnapplication = new ModuleMethod(runtimeVar, 145, Lit217, 0);
        open$Mnanother$Mnscreen = new ModuleMethod(runtimeVar, 146, Lit218, 4097);
        open$Mnanother$Mnscreen$Mnwith$Mnstart$Mnvalue = new ModuleMethod(runtimeVar, 147, Lit219, 8194);
        get$Mnstart$Mnvalue = new ModuleMethod(runtimeVar, 148, Lit220, 0);
        close$Mnscreen$Mnwith$Mnvalue = new ModuleMethod(runtimeVar, 149, Lit221, 4097);
        get$Mnplain$Mnstart$Mntext = new ModuleMethod(runtimeVar, 150, Lit222, 0);
        close$Mnscreen$Mnwith$Mnplain$Mntext = new ModuleMethod(runtimeVar, 151, Lit223, 4097);
        get$Mnserver$Mnaddress$Mnfrom$Mnwifi = new ModuleMethod(runtimeVar, 152, Lit224, 0);
        process$Mnrepl$Mninput = Macro.make(Lit225, Lit226, $instance);
        in$Mnui = new ModuleMethod(runtimeVar, 153, Lit227, 8194);
        send$Mnto$Mnblock = new ModuleMethod(runtimeVar, 154, Lit228, 8194);
        clear$Mncurrent$Mnform = new ModuleMethod(runtimeVar, 155, Lit229, 0);
        set$Mnform$Mnname = new ModuleMethod(runtimeVar, 156, Lit230, 4097);
        remove$Mncomponent = new ModuleMethod(runtimeVar, 157, Lit231, 4097);
        rename$Mncomponent = new ModuleMethod(runtimeVar, 158, Lit232, 8194);
        init$Mnruntime = new ModuleMethod(runtimeVar, 159, Lit233, 0);
        set$Mnthis$Mnform = new ModuleMethod(runtimeVar, ComponentConstants.TEXTBOX_PREFERRED_WIDTH, Lit234, 0);
        clarify = new ModuleMethod(runtimeVar, 161, Lit235, 4097);
        clarify1 = new ModuleMethod(runtimeVar, 162, Lit236, 4097);
    }

    static Object lambda13(Object stx) {
        Object[] allocVars = SyntaxPattern.allocVars(2, null);
        if (Lit39.match(stx, allocVars, 0)) {
            Object[] objArr = new Object[3];
            objArr[0] = "com.google.appinventor.components.runtime";
            objArr[1] = ".";
            Object execute = Lit40.execute(allocVars, TemplateScope.make());
            try {
                objArr[2] = ((Symbol) execute).toString();
                return SyntaxForms.makeWithTemplate(stx, strings.stringAppend(objArr));
            } catch (ClassCastException e) {
                throw new WrongType(e, "symbol->string", 1, execute);
            }
        }
        return syntax_case.error("syntax-case", stx);
    }

    public static Object addComponentWithinRepl(Object container$Mnname, Object component$Mntype, Object componentName, Object initPropsThunk) {
        frame frameVar = new frame();
        frameVar.component$Mnname = componentName;
        frameVar.init$Mnprops$Mnthunk = initPropsThunk;
        try {
            Object lookupInCurrentFormEnvironment = lookupInCurrentFormEnvironment((Symbol) container$Mnname);
            try {
                ComponentContainer container = (ComponentContainer) lookupInCurrentFormEnvironment;
                Object obj = frameVar.component$Mnname;
                try {
                    frameVar.existing$Mncomponent = lookupInCurrentFormEnvironment((Symbol) obj);
                    frameVar.component$Mnto$Mnadd = Invoke.make.apply2(component$Mntype, container);
                    Object obj2 = frameVar.component$Mnname;
                    try {
                        addToCurrentFormEnvironment((Symbol) obj2, frameVar.component$Mnto$Mnadd);
                        return addInitThunk(frameVar.component$Mnname, frameVar.lambda$Fn1);
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "add-to-current-form-environment", 0, obj2);
                    }
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "lookup-in-current-form-environment", 0, obj);
                }
            } catch (ClassCastException e3) {
                throw new WrongType(e3, "container", -2, lookupInCurrentFormEnvironment);
            }
        } catch (ClassCastException e4) {
            throw new WrongType(e4, "lookup-in-current-form-environment", 0, container$Mnname);
        }
    }

    @Override // gnu.expr.ModuleBody
    public int match4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4, CallContext callContext) {
        switch (moduleMethod.selector) {
            case 11:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.value4 = obj4;
                callContext.proc = moduleMethod;
                callContext.pc = 4;
                return 0;
            case 17:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.value4 = obj4;
                callContext.proc = moduleMethod;
                callContext.pc = 4;
                return 0;
            case 34:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.value4 = obj4;
                callContext.proc = moduleMethod;
                callContext.pc = 4;
                return 0;
            case 36:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.value4 = obj4;
                callContext.proc = moduleMethod;
                callContext.pc = 4;
                return 0;
            case XDataType.NAME_TYPE_CODE /* 43 */:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.value4 = obj4;
                callContext.proc = moduleMethod;
                callContext.pc = 4;
                return 0;
            case XDataType.NCNAME_TYPE_CODE /* 44 */:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.value4 = obj4;
                callContext.proc = moduleMethod;
                callContext.pc = 4;
                return 0;
            case 122:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.value4 = obj4;
                callContext.proc = moduleMethod;
                callContext.pc = 4;
                return 0;
            case 123:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.value4 = obj4;
                callContext.proc = moduleMethod;
                callContext.pc = 4;
                return 0;
            default:
                return super.match4(moduleMethod, obj, obj2, obj3, obj4, callContext);
        }
    }

    /* compiled from: runtime1714060078409101109.scm */
    /* loaded from: classes.dex */
    public class frame extends ModuleBody {
        Object component$Mnname;
        Object component$Mnto$Mnadd;
        Object existing$Mncomponent;
        Object init$Mnprops$Mnthunk;
        final ModuleMethod lambda$Fn1;

        public frame() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 1, null, 0);
            moduleMethod.setProperty("source-location", "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm:94");
            this.lambda$Fn1 = moduleMethod;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 1 ? lambda1() : super.apply0(moduleMethod);
        }

        Object lambda1() {
            if (this.init$Mnprops$Mnthunk != Boolean.FALSE) {
                Scheme.applyToArgs.apply1(this.init$Mnprops$Mnthunk);
            }
            if (this.existing$Mncomponent != Boolean.FALSE) {
                runtime.androidLog(Format.formatToString(0, "Copying component properties for ~A", this.component$Mnname));
                Object obj = this.existing$Mncomponent;
                try {
                    Component component = (Component) obj;
                    Object obj2 = this.component$Mnto$Mnadd;
                    try {
                        return PropertyUtil.copyComponentProperties(component, (Component) obj2);
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "com.google.appinventor.components.runtime.util.PropertyUtil.copyComponentProperties(com.google.appinventor.components.runtime.Component,com.google.appinventor.components.runtime.Component)", 2, obj2);
                    }
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "com.google.appinventor.components.runtime.util.PropertyUtil.copyComponentProperties(com.google.appinventor.components.runtime.Component,com.google.appinventor.components.runtime.Component)", 1, obj);
                }
            }
            return Values.empty;
        }

        @Override // gnu.expr.ModuleBody
        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector == 1) {
                callContext.proc = moduleMethod;
                callContext.pc = 0;
                return 0;
            }
            return super.match0(moduleMethod, callContext);
        }
    }

    public static Object call$MnInitializeOfComponents$V(Object[] argsArray) {
        LList component$Mnnames = LList.makeList(argsArray, 0);
        Object obj = component$Mnnames;
        while (obj != LList.Empty) {
            try {
                Pair arg0 = (Pair) obj;
                Object init$Mnthunk = getInitThunk(arg0.getCar());
                if (init$Mnthunk != Boolean.FALSE) {
                    Scheme.applyToArgs.apply1(init$Mnthunk);
                }
                obj = arg0.getCdr();
            } catch (ClassCastException e) {
                throw new WrongType(e, "arg0", -2, obj);
            }
        }
        Object arg02 = component$Mnnames;
        while (arg02 != LList.Empty) {
            try {
                Pair arg03 = (Pair) arg02;
                Object component$Mnname = arg03.getCar();
                try {
                    ((Form) $Stthis$Mnform$St).callInitialize(lookupInCurrentFormEnvironment((Symbol) component$Mnname));
                    arg02 = arg03.getCdr();
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "lookup-in-current-form-environment", 0, component$Mnname);
                }
            } catch (ClassCastException e3) {
                throw new WrongType(e3, "arg0", -2, arg02);
            }
        }
        return Values.empty;
    }

    @Override // gnu.expr.ModuleBody
    public int matchN(ModuleMethod moduleMethod, Object[] objArr, CallContext callContext) {
        switch (moduleMethod.selector) {
            case 12:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 21:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 22:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 35:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 71:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 72:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 104:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 118:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            default:
                return super.matchN(moduleMethod, objArr, callContext);
        }
    }

    public static Object addInitThunk(Object component$Mnname, Object thunk) {
        return Invoke.invokeStatic.applyN(new Object[]{KawaEnvironment, Lit0, $Stinit$Mnthunk$Mnenvironment$St, component$Mnname, thunk});
    }

    @Override // gnu.expr.ModuleBody
    public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
        switch (moduleMethod.selector) {
            case 13:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 18:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 25:
                if (obj instanceof Symbol) {
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.proc = moduleMethod;
                    callContext.pc = 2;
                    return 0;
                }
                return -786431;
            case 26:
                if (obj instanceof Symbol) {
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.proc = moduleMethod;
                    callContext.pc = 2;
                    return 0;
                }
                return -786431;
            case 29:
                if (obj instanceof Symbol) {
                    callContext.value1 = obj;
                    if (obj2 instanceof Symbol) {
                        callContext.value2 = obj2;
                        callContext.proc = moduleMethod;
                        callContext.pc = 2;
                        return 0;
                    }
                    return -786430;
                }
                return -786431;
            case 30:
                if (obj instanceof Symbol) {
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.proc = moduleMethod;
                    callContext.pc = 2;
                    return 0;
                }
                return -786431;
            case 31:
                if (obj instanceof Symbol) {
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.proc = moduleMethod;
                    callContext.pc = 2;
                    return 0;
                }
                return -786431;
            case 41:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case XDataType.IDREF_TYPE_CODE /* 46 */:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 49:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 53:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 58:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 67:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 68:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case PrettyWriter.NEWLINE_FILL /* 70 */:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case PrettyWriter.NEWLINE_LINEAR /* 78 */:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 80:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 91:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 94:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 99:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case DateTime.TIME_MASK /* 112 */:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 113:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 115:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 117:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 119:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 121:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 124:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 129:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 130:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 131:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 132:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 133:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 134:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 140:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 147:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 153:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 154:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 158:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            default:
                return super.match2(moduleMethod, obj, obj2, callContext);
        }
    }

    public static Object getInitThunk(Object component$Mnname) {
        Object obj = $Stinit$Mnthunk$Mnenvironment$St;
        try {
            try {
                boolean x = ((Environment) obj).isBound((Symbol) component$Mnname);
                if (x) {
                    return Invoke.invokeStatic.apply4(KawaEnvironment, Lit1, $Stinit$Mnthunk$Mnenvironment$St, component$Mnname);
                }
                return x ? Boolean.TRUE : Boolean.FALSE;
            } catch (ClassCastException e) {
                throw new WrongType(e, "gnu.mapping.Environment.isBound(gnu.mapping.Symbol)", 2, component$Mnname);
            }
        } catch (ClassCastException e2) {
            throw new WrongType(e2, "gnu.mapping.Environment.isBound(gnu.mapping.Symbol)", 1, obj);
        }
    }

    public static void clearInitThunks() {
        $Stinit$Mnthunk$Mnenvironment$St = Environment.make("init-thunk-environment");
    }

    @Override // gnu.expr.ModuleBody
    public int match0(ModuleMethod moduleMethod, CallContext callContext) {
        switch (moduleMethod.selector) {
            case 15:
                callContext.proc = moduleMethod;
                callContext.pc = 0;
                return 0;
            case 33:
                callContext.proc = moduleMethod;
                callContext.pc = 0;
                return 0;
            case PrettyWriter.NEWLINE_MISER /* 77 */:
                callContext.proc = moduleMethod;
                callContext.pc = 0;
                return 0;
            case ComponentConstants.VIDEOPLAYER_PREFERRED_HEIGHT /* 144 */:
                callContext.proc = moduleMethod;
                callContext.pc = 0;
                return 0;
            case 145:
                callContext.proc = moduleMethod;
                callContext.pc = 0;
                return 0;
            case 148:
                callContext.proc = moduleMethod;
                callContext.pc = 0;
                return 0;
            case 150:
                callContext.proc = moduleMethod;
                callContext.pc = 0;
                return 0;
            case 152:
                callContext.proc = moduleMethod;
                callContext.pc = 0;
                return 0;
            case 155:
                callContext.proc = moduleMethod;
                callContext.pc = 0;
                return 0;
            case 159:
                callContext.proc = moduleMethod;
                callContext.pc = 0;
                return 0;
            case ComponentConstants.TEXTBOX_PREFERRED_WIDTH /* 160 */:
                callContext.proc = moduleMethod;
                callContext.pc = 0;
                return 0;
            default:
                return super.match0(moduleMethod, callContext);
        }
    }

    public static Object lookupComponent(Object comp$Mnname) {
        try {
            Object verified = lookupInCurrentFormEnvironment((Symbol) comp$Mnname, Boolean.FALSE);
            return verified != Boolean.FALSE ? verified : Lit2;
        } catch (ClassCastException e) {
            throw new WrongType(e, "lookup-in-current-form-environment", 0, comp$Mnname);
        }
    }

    public static Object setAndCoerceProperty$Ex(Object component, Object prop$Mnsym, Object property$Mnvalue, Object property$Mntype) {
        return $PcSetAndCoerceProperty$Ex(coerceToComponentAndVerify(component), prop$Mnsym, property$Mnvalue, property$Mntype);
    }

    public static Object getProperty$1(Object component, Object prop$Mnname) {
        return sanitizeComponentData(Invoke.invoke.apply2(coerceToComponentAndVerify(component), prop$Mnname));
    }

    public static Object coerceToComponentAndVerify(Object possible$Mncomponent) {
        Object component = coerceToComponent(possible$Mncomponent);
        return !(component instanceof Component) ? signalRuntimeError(strings.stringAppend("Cannot find the component: ", ((Procedure) get$Mndisplay$Mnrepresentation).apply1(possible$Mncomponent)), "Problem with application") : component;
    }

    public static Object getPropertyAndCheck(Object possible$Mncomponent, Object component$Mntype, Object prop$Mnname) {
        Object component = coerceToComponentOfType(possible$Mncomponent, component$Mntype);
        return !(component instanceof Component) ? signalRuntimeError(Format.formatToString(0, "Property getter was expecting a ~A component but got a ~A instead.", component$Mntype, possible$Mncomponent.getClass().getSimpleName()), "Problem with application") : sanitizeComponentData(Invoke.invoke.apply2(component, prop$Mnname));
    }

    @Override // gnu.expr.ModuleBody
    public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
        switch (moduleMethod.selector) {
            case 20:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            case XDataType.ID_TYPE_CODE /* 45 */:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            case 48:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            case 114:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            case 116:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            case 125:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            case 136:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            case 138:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            default:
                return super.match3(moduleMethod, obj, obj2, obj3, callContext);
        }
    }

    public static Object setAndCoercePropertyAndCheck$Ex(Object possible$Mncomponent, Object comp$Mntype, Object prop$Mnsym, Object property$Mnvalue, Object property$Mntype) {
        Object component = coerceToComponentOfType(possible$Mncomponent, comp$Mntype);
        return !(component instanceof Component) ? signalRuntimeError(Format.formatToString(0, "Property setter was expecting a ~A component but got a ~A instead.", comp$Mntype, possible$Mncomponent.getClass().getSimpleName()), "Problem with application") : $PcSetAndCoerceProperty$Ex(component, prop$Mnsym, property$Mnvalue, property$Mntype);
    }

    public static SimpleSymbol symbolAppend$V(Object[] argsArray) {
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

    static Object lambda14(Object stx) {
        Object[] allocVars = SyntaxPattern.allocVars(3, null);
        if (Lit76.match(stx, allocVars, 0)) {
            return SyntaxForms.makeWithTemplate(stx, Lit77.execute(allocVars, TemplateScope.make()));
        }
        return syntax_case.error("syntax-case", stx);
    }

    static Object lambda15(Object stx) {
        Object consX;
        Object[] allocVars = SyntaxPattern.allocVars(5, null);
        if (Lit83.match(stx, allocVars, 0)) {
            TemplateScope make = TemplateScope.make();
            consX = LList.consX(new Object[]{symbolAppend$V(new Object[]{Lit86.execute(allocVars, make), Lit87, Lit88.execute(allocVars, make)}), Lit89.execute(allocVars, make)});
            return Quote.append$V(new Object[]{Lit84.execute(allocVars, make), Pair.make(Quote.append$V(new Object[]{Lit85.execute(allocVars, make), consX}), Lit90.execute(allocVars, make))});
        }
        return syntax_case.error("syntax-case", stx);
    }

    @Override // gnu.expr.ModuleBody
    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        switch (moduleMethod.selector) {
            case 9:
                androidLog(obj);
                return Values.empty;
            case 10:
                return lambda13(obj);
            case 11:
            case 12:
            case 13:
            case 15:
            case 17:
            case 18:
            case 20:
            case 21:
            case 22:
            case 25:
            case 27:
            case 29:
            case 30:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 41:
            case XDataType.NAME_TYPE_CODE /* 43 */:
            case XDataType.NCNAME_TYPE_CODE /* 44 */:
            case XDataType.ID_TYPE_CODE /* 45 */:
            case XDataType.IDREF_TYPE_CODE /* 46 */:
            case 48:
            case 49:
            case 53:
            case 58:
            case 67:
            case 68:
            case PrettyWriter.NEWLINE_FILL /* 70 */:
            case 71:
            case 72:
            case PrettyWriter.NEWLINE_MISER /* 77 */:
            case PrettyWriter.NEWLINE_LINEAR /* 78 */:
            case 80:
            case 91:
            case 94:
            case 99:
            case 104:
            case DateTime.TIME_MASK /* 112 */:
            case 113:
            case 114:
            case 115:
            case 116:
            case 117:
            case 118:
            case 119:
            case 121:
            case 122:
            case 123:
            case 124:
            case 125:
            case 129:
            case 130:
            case 131:
            case 132:
            case 133:
            case 134:
            case 136:
            case 138:
            case 140:
            case ComponentConstants.VIDEOPLAYER_PREFERRED_HEIGHT /* 144 */:
            case 145:
            case 147:
            case 148:
            case 150:
            case 152:
            case 153:
            case 154:
            case 155:
            case 158:
            case 159:
            case ComponentConstants.TEXTBOX_PREFERRED_WIDTH /* 160 */:
            default:
                return super.apply1(moduleMethod, obj);
            case 14:
                return getInitThunk(obj);
            case 16:
                return lookupComponent(obj);
            case 19:
                return coerceToComponentAndVerify(obj);
            case 23:
                return lambda14(obj);
            case 24:
                return lambda15(obj);
            case 26:
                try {
                    return lookupInCurrentFormEnvironment((Symbol) obj);
                } catch (ClassCastException e) {
                    throw new WrongType(e, "lookup-in-current-form-environment", 1, obj);
                }
            case 28:
                try {
                    return deleteFromCurrentFormEnvironment((Symbol) obj);
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "delete-from-current-form-environment", 1, obj);
                }
            case 31:
                try {
                    return lookupGlobalVarInCurrentFormEnvironment((Symbol) obj);
                } catch (ClassCastException e3) {
                    throw new WrongType(e3, "lookup-global-var-in-current-form-environment", 1, obj);
                }
            case 37:
                return sanitizeComponentData(obj);
            case 38:
                try {
                    return javaCollection$To$YailList((Collection) obj);
                } catch (ClassCastException e4) {
                    throw new WrongType(e4, "java-collection->yail-list", 1, obj);
                }
            case 39:
                try {
                    return javaCollection$To$KawaList((Collection) obj);
                } catch (ClassCastException e5) {
                    throw new WrongType(e5, "java-collection->kawa-list", 1, obj);
                }
            case 40:
                return sanitizeAtomic(obj);
            case XDataType.NMTOKEN_TYPE_CODE /* 42 */:
                return yailNot(obj) ? Boolean.TRUE : Boolean.FALSE;
            case XDataType.ENTITY_TYPE_CODE /* 47 */:
                return showArglistNoParens(obj);
            case 50:
                return coerceToText(obj);
            case 51:
                return coerceToInstant(obj);
            case 52:
                return coerceToComponent(obj);
            case 54:
                return type$To$Class(obj);
            case 55:
                return coerceToNumber(obj);
            case 56:
                return coerceToString(obj);
            case 57:
                return lambda4(obj);
            case 59:
                return coerceToYailList(obj);
            case 60:
                return coerceToBoolean(obj);
            case 61:
                return isIsCoercible(obj) ? Boolean.TRUE : Boolean.FALSE;
            case 62:
                return isAllCoercible(obj);
            case 63:
                return boolean$To$String(obj);
            case 64:
                return paddedString$To$Number(obj);
            case 65:
                return $StFormatInexact$St(obj);
            case 66:
                return appinventorNumber$To$String(obj);
            case 69:
                return asNumber(obj);
            case 73:
                return yailFloor(obj);
            case 74:
                return yailCeiling(obj);
            case 75:
                return yailRound(obj);
            case PrettyWriter.NEWLINE_LITERAL /* 76 */:
                return randomSetSeed(obj);
            case 79:
                return lambda9(obj);
            case 81:
                return degrees$To$RadiansInternal(obj);
            case PrettyWriter.NEWLINE_MANDATORY /* 82 */:
                return radians$To$DegreesInternal(obj);
            case PrettyWriter.NEWLINE_SPACE /* 83 */:
                return degrees$To$Radians(obj);
            case 84:
                return radians$To$Degrees(obj);
            case 85:
                return Double.valueOf(sinDegrees(obj));
            case 86:
                return Double.valueOf(cosDegrees(obj));
            case 87:
                return Double.valueOf(tanDegrees(obj));
            case 88:
                return asinDegrees(obj);
            case 89:
                return acosDegrees(obj);
            case 90:
                return atanDegrees(obj);
            case 92:
                return stringToUpperCase(obj);
            case 93:
                return stringToLowerCase(obj);
            case 95:
                return isIsNumber(obj);
            case 96:
                return isYailList(obj);
            case 97:
                return isYailListCandidate(obj);
            case 98:
                return yailListContents(obj);
            case 100:
                return insertYailListHeader(obj);
            case ErrorMessages.ERROR_LOCATION_SENSOR_LATITUDE_NOT_FOUND /* 101 */:
                return kawaList$To$YailList(obj);
            case ErrorMessages.ERROR_LOCATION_SENSOR_LONGITUDE_NOT_FOUND /* 102 */:
                return yailList$To$KawaList(obj);
            case 103:
                return isYailListEmpty(obj);
            case 105:
                return yailListCopy(obj);
            case 106:
                return yailListToCsvTable(obj);
            case 107:
                return yailListToCsvRow(obj);
            case 108:
                return convertToStrings(obj);
            case YaVersion.YOUNG_ANDROID_VERSION /* 109 */:
                return yailListFromCsvTable(obj);
            case 110:
                return yailListFromCsvRow(obj);
            case 111:
                return Integer.valueOf(yailListLength(obj));
            case 120:
                return yailListPickRandom(obj);
            case 126:
                return isPairOk(obj);
            case 127:
                return makeDisjunct(obj);
            case DateTime.TIMEZONE_MASK /* 128 */:
                return array$To$List(obj);
            case 135:
                return stringSplitAtSpaces(obj);
            case 137:
                return stringTrim(obj);
            case 139:
                return isStringEmpty(obj) ? Boolean.TRUE : Boolean.FALSE;
            case 141:
                return makeExactYailInteger(obj);
            case 142:
                return makeColor(obj);
            case 143:
                return splitColor(obj);
            case 146:
                openAnotherScreen(obj);
                return Values.empty;
            case 149:
                closeScreenWithValue(obj);
                return Values.empty;
            case 151:
                closeScreenWithPlainText(obj);
                return Values.empty;
            case 156:
                return setFormName(obj);
            case 157:
                return removeComponent(obj);
            case 161:
                return clarify(obj);
            case 162:
                return clarify1(obj);
        }
    }

    public static Object addToCurrentFormEnvironment(Symbol name, Object object) {
        return $Stthis$Mnform$St != null ? Invoke.invokeStatic.applyN(new Object[]{KawaEnvironment, Lit0, SlotGet.getSlotValue(false, $Stthis$Mnform$St, "form-environment", "form$Mnenvironment", "getFormEnvironment", "isFormEnvironment", Scheme.instance), name, object}) : Invoke.invokeStatic.applyN(new Object[]{KawaEnvironment, Lit0, $Sttest$Mnenvironment$St, name, object});
    }

    public static Object lookupInCurrentFormEnvironment(Symbol name, Object default$Mnvalue) {
        Object env = $Stthis$Mnform$St != null ? SlotGet.getSlotValue(false, $Stthis$Mnform$St, "form-environment", "form$Mnenvironment", "getFormEnvironment", "isFormEnvironment", Scheme.instance) : $Sttest$Mnenvironment$St;
        try {
            if (!((Environment) env).isBound(name)) {
                return default$Mnvalue;
            }
            Object default$Mnvalue2 = Invoke.invokeStatic.apply4(KawaEnvironment, Lit1, env, name);
            return default$Mnvalue2;
        } catch (ClassCastException e) {
            throw new WrongType(e, "gnu.mapping.Environment.isBound(gnu.mapping.Symbol)", 1, env);
        }
    }

    public static Object deleteFromCurrentFormEnvironment(Symbol name) {
        return $Stthis$Mnform$St != null ? Invoke.invokeStatic.apply4(KawaEnvironment, Lit3, SlotGet.getSlotValue(false, $Stthis$Mnform$St, "form-environment", "form$Mnenvironment", "getFormEnvironment", "isFormEnvironment", Scheme.instance), name) : Invoke.invokeStatic.apply4(KawaEnvironment, Lit3, $Sttest$Mnenvironment$St, name);
    }

    public static Object renameInCurrentFormEnvironment(Symbol old$Mnname, Symbol new$Mnname) {
        if (Scheme.isEqv.apply2(old$Mnname, new$Mnname) == Boolean.FALSE) {
            Object old$Mnvalue = lookupInCurrentFormEnvironment(old$Mnname);
            if ($Stthis$Mnform$St != null) {
                Invoke.invokeStatic.applyN(new Object[]{KawaEnvironment, Lit0, SlotGet.getSlotValue(false, $Stthis$Mnform$St, "form-environment", "form$Mnenvironment", "getFormEnvironment", "isFormEnvironment", Scheme.instance), new$Mnname, old$Mnvalue});
            } else {
                Invoke.invokeStatic.applyN(new Object[]{KawaEnvironment, Lit0, $Sttest$Mnenvironment$St, new$Mnname, old$Mnvalue});
            }
            return deleteFromCurrentFormEnvironment(old$Mnname);
        }
        return Values.empty;
    }

    public static Object addGlobalVarToCurrentFormEnvironment(Symbol name, Object object) {
        if ($Stthis$Mnform$St != null) {
            Invoke.invokeStatic.applyN(new Object[]{KawaEnvironment, Lit0, SlotGet.getSlotValue(false, $Stthis$Mnform$St, "global-var-environment", "global$Mnvar$Mnenvironment", "getGlobalVarEnvironment", "isGlobalVarEnvironment", Scheme.instance), name, object});
            return null;
        }
        Invoke.invokeStatic.applyN(new Object[]{KawaEnvironment, Lit0, $Sttest$Mnglobal$Mnvar$Mnenvironment$St, name, object});
        return null;
    }

    public static Object lookupGlobalVarInCurrentFormEnvironment(Symbol name, Object default$Mnvalue) {
        Object env = $Stthis$Mnform$St != null ? SlotGet.getSlotValue(false, $Stthis$Mnform$St, "global-var-environment", "global$Mnvar$Mnenvironment", "getGlobalVarEnvironment", "isGlobalVarEnvironment", Scheme.instance) : $Sttest$Mnglobal$Mnvar$Mnenvironment$St;
        try {
            if (!((Environment) env).isBound(name)) {
                return default$Mnvalue;
            }
            Object default$Mnvalue2 = Invoke.invokeStatic.apply4(KawaEnvironment, Lit1, env, name);
            return default$Mnvalue2;
        } catch (ClassCastException e) {
            throw new WrongType(e, "gnu.mapping.Environment.isBound(gnu.mapping.Symbol)", 1, env);
        }
    }

    public static Object resetCurrentFormEnvironment() {
        if ($Stthis$Mnform$St != null) {
            Object form$Mnname = SlotGet.getSlotValue(false, $Stthis$Mnform$St, "form-name-symbol", "form$Mnname$Mnsymbol", "getFormNameSymbol", "isFormNameSymbol", Scheme.instance);
            try {
                SlotSet.set$Mnfield$Ex.apply3($Stthis$Mnform$St, "form-environment", Environment.make(((Symbol) form$Mnname).toString()));
                try {
                    addToCurrentFormEnvironment((Symbol) form$Mnname, $Stthis$Mnform$St);
                    SlotSet slotSet = SlotSet.set$Mnfield$Ex;
                    Object obj = $Stthis$Mnform$St;
                    Object[] objArr = new Object[2];
                    try {
                        objArr[0] = ((Symbol) form$Mnname).toString();
                        objArr[1] = "-global-vars";
                        FString stringAppend = strings.stringAppend(objArr);
                        slotSet.apply3(obj, "global-var-environment", Environment.make(stringAppend == null ? null : stringAppend.toString()));
                        return Invoke.invoke.apply3(Environment.getCurrent(), "addParent", SlotGet.getSlotValue(false, $Stthis$Mnform$St, "form-environment", "form$Mnenvironment", "getFormEnvironment", "isFormEnvironment", Scheme.instance));
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "symbol->string", 1, form$Mnname);
                    }
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "add-to-current-form-environment", 0, form$Mnname);
                }
            } catch (ClassCastException e3) {
                throw new WrongType(e3, "symbol->string", 1, form$Mnname);
            }
        }
        $Sttest$Mnenvironment$St = Environment.make("test-env");
        Invoke.invoke.apply3(Environment.getCurrent(), "addParent", $Sttest$Mnenvironment$St);
        $Sttest$Mnglobal$Mnvar$Mnenvironment$St = Environment.make("test-global-var-env");
        return Values.empty;
    }

    public static Object callComponentMethod(Object component$Mnname, Object method$Mnname, Object arglist, Object typelist) {
        Object result;
        Object consX;
        Object consX2;
        Object coerced$Mnargs = coerceArgs(method$Mnname, arglist, typelist);
        if (isAllCoercible(coerced$Mnargs) != Boolean.FALSE) {
            Apply apply = Scheme.apply;
            Invoke invoke = Invoke.invoke;
            Object[] objArr = new Object[2];
            try {
                objArr[0] = lookupInCurrentFormEnvironment((Symbol) component$Mnname);
                consX = LList.consX(new Object[]{method$Mnname, Quote.append$V(new Object[]{coerced$Mnargs, LList.Empty})});
                objArr[1] = consX;
                consX2 = LList.consX(objArr);
                result = apply.apply2(invoke, consX2);
            } catch (ClassCastException e) {
                throw new WrongType(e, "lookup-in-current-form-environment", 0, component$Mnname);
            }
        } else {
            result = generateRuntimeTypeError(method$Mnname, arglist);
        }
        return sanitizeComponentData(result);
    }

    public static Object callComponentTypeMethod(Object possible$Mncomponent, Object component$Mntype, Object method$Mnname, Object arglist, Object typelist) {
        Object result;
        Object consX;
        Object consX2;
        Object coerced$Mnargs = coerceArgs(method$Mnname, arglist, lists.cdr.apply1(typelist));
        Object component$Mnvalue = coerceToComponentOfType(possible$Mncomponent, component$Mntype);
        if (!(component$Mnvalue instanceof Component)) {
            return generateRuntimeTypeError(method$Mnname, LList.list1(((Procedure) get$Mndisplay$Mnrepresentation).apply1(possible$Mncomponent)));
        }
        if (isAllCoercible(coerced$Mnargs) != Boolean.FALSE) {
            Apply apply = Scheme.apply;
            Invoke invoke = Invoke.invoke;
            consX = LList.consX(new Object[]{method$Mnname, Quote.append$V(new Object[]{coerced$Mnargs, LList.Empty})});
            consX2 = LList.consX(new Object[]{component$Mnvalue, consX});
            result = apply.apply2(invoke, consX2);
        } else {
            result = generateRuntimeTypeError(method$Mnname, arglist);
        }
        return sanitizeComponentData(result);
    }

    public static Object callYailPrimitive(Object prim, Object arglist, Object typelist, Object codeblocks$Mnname) {
        Object coerced$Mnargs = coerceArgs(codeblocks$Mnname, arglist, typelist);
        return isAllCoercible(coerced$Mnargs) != Boolean.FALSE ? Scheme.apply.apply2(prim, coerced$Mnargs) : generateRuntimeTypeError(codeblocks$Mnname, arglist);
    }

    public static Object sanitizeComponentData(Object data) {
        if (!strings.isString(data) && isYailList(data) == Boolean.FALSE) {
            if (lists.isList(data)) {
                return kawaList$To$YailList(data);
            }
            if (data instanceof Collection) {
                try {
                    return javaCollection$To$YailList((Collection) data);
                } catch (ClassCastException e) {
                    throw new WrongType(e, "java-collection->yail-list", 0, data);
                }
            }
            return sanitizeAtomic(data);
        }
        return data;
    }

    public static Object javaCollection$To$YailList(Collection collection) {
        return kawaList$To$YailList(javaCollection$To$KawaList(collection));
    }

    public static Object javaCollection$To$KawaList(Collection collection) {
        LList lList = LList.Empty;
        for (Object obj : collection) {
            lList = lists.cons(sanitizeComponentData(obj), lList);
        }
        try {
            return LList.reverseInPlace(lList);
        } catch (ClassCastException e) {
            throw new WrongType(e, "reverse!", 1, lList);
        }
    }

    public static Object sanitizeAtomic(Object arg) {
        if (arg == null || Values.empty == arg) {
            return null;
        }
        if (numbers.isNumber(arg)) {
            Numeric numericarg = Arithmetic.asNumeric(arg);
            return numericarg;
        }
        return arg;
    }

    public static Object signalRuntimeError(Object message, Object error$Mntype) {
        throw new YailRuntimeError(message == null ? null : message.toString(), error$Mntype != null ? error$Mntype.toString() : null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean yailNot(Object foo) {
        return ((foo != Boolean.FALSE ? 1 : 0) + 1) & 1;
    }

    public static Object callWithCoercedArgs(Object func, Object arglist, Object typelist, Object codeblocks$Mnname) {
        Object coerced$Mnargs = coerceArgs(codeblocks$Mnname, arglist, typelist);
        return isAllCoercible(coerced$Mnargs) != Boolean.FALSE ? Scheme.apply.apply2(func, coerced$Mnargs) : generateRuntimeTypeError(codeblocks$Mnname, arglist);
    }

    public static Object $PcSetAndCoerceProperty$Ex(Object comp, Object prop$Mnname, Object property$Mnvalue, Object property$Mntype) {
        androidLog(Format.formatToString(0, "coercing for setting property ~A -- value ~A to type ~A", prop$Mnname, property$Mnvalue, property$Mntype));
        Object coerced$Mnarg = coerceArg(property$Mnvalue, property$Mntype);
        androidLog(Format.formatToString(0, "coerced property value was: ~A ", coerced$Mnarg));
        return isAllCoercible(LList.list1(coerced$Mnarg)) != Boolean.FALSE ? Invoke.invoke.apply3(comp, prop$Mnname, coerced$Mnarg) : generateRuntimeTypeError(prop$Mnname, LList.list1(property$Mnvalue));
    }

    public static Object $PcSetSubformLayoutProperty$Ex(Object layout, Object prop$Mnname, Object value) {
        return Invoke.invoke.apply3(layout, prop$Mnname, value);
    }

    public static Object generateRuntimeTypeError(Object proc$Mnname, Object arglist) {
        androidLog(Format.formatToString(0, "arglist is: ~A ", arglist));
        Object string$Mnname = coerceToString(proc$Mnname);
        Object[] objArr = new Object[4];
        objArr[0] = "The operation ";
        objArr[1] = string$Mnname;
        Object[] objArr2 = new Object[2];
        objArr2[0] = " cannot accept the argument~P: ";
        try {
            objArr2[1] = Integer.valueOf(lists.length((LList) arglist));
            objArr[2] = Format.formatToString(0, objArr2);
            objArr[3] = showArglistNoParens(arglist);
            return signalRuntimeError(strings.stringAppend(objArr), strings.stringAppend("Bad arguments to ", string$Mnname));
        } catch (ClassCastException e) {
            throw new WrongType(e, "length", 1, arglist);
        }
    }

    public static Object showArglistNoParens(Object args) {
        Object s = ((Procedure) get$Mndisplay$Mnrepresentation).apply1(args);
        try {
            try {
                return strings.substring((CharSequence) s, 1, strings.stringLength((CharSequence) s) - 1);
            } catch (ClassCastException e) {
                throw new WrongType(e, "string-length", 1, s);
            }
        } catch (ClassCastException e2) {
            throw new WrongType(e2, "substring", 1, s);
        }
    }

    public static Object coerceArgs(Object procedure$Mnname, Object arglist, Object typelist) {
        if (lists.isNull(typelist)) {
            return lists.isNull(arglist) ? arglist : signalRuntimeError(strings.stringAppend("The procedure ", procedure$Mnname, " expects no arguments, but it was called with the arguments: ", showArglistNoParens(arglist)), strings.stringAppend("Wrong number of arguments for", procedure$Mnname));
        }
        try {
            try {
                if (lists.length((LList) arglist) != lists.length((LList) typelist)) {
                    return signalRuntimeError(strings.stringAppend("The arguments ", showArglistNoParens(arglist), " are the wrong number of arguments for ", ((Procedure) get$Mndisplay$Mnrepresentation).apply1(procedure$Mnname)), strings.stringAppend("Wrong number of arguments for", ((Procedure) get$Mndisplay$Mnrepresentation).apply1(procedure$Mnname)));
                }
                Object obj = LList.Empty;
                Object arg0 = arglist;
                Object obj2 = typelist;
                while (arg0 != LList.Empty && obj2 != LList.Empty) {
                    try {
                        Pair arg02 = (Pair) arg0;
                        try {
                            Pair arg1 = (Pair) obj2;
                            Object arg03 = arg02.getCdr();
                            Object arg12 = arg1.getCdr();
                            obj = Pair.make(coerceArg(arg02.getCar(), arg1.getCar()), obj);
                            obj2 = arg12;
                            arg0 = arg03;
                        } catch (ClassCastException e) {
                            throw new WrongType(e, "arg1", -2, obj2);
                        }
                    } catch (ClassCastException e2) {
                        throw new WrongType(e2, "arg0", -2, arg0);
                    }
                }
                return LList.reverseInPlace(obj);
            } catch (ClassCastException e3) {
                throw new WrongType(e3, "length", 1, typelist);
            }
        } catch (ClassCastException e4) {
            throw new WrongType(e4, "length", 1, arglist);
        }
    }

    public static Object coerceArg(Object arg, Object type) {
        Object arg2 = sanitizeAtomic(arg);
        if (IsEqual.apply(type, Lit4)) {
            return coerceToNumber(arg2);
        }
        if (IsEqual.apply(type, Lit5)) {
            return coerceToText(arg2);
        }
        if (IsEqual.apply(type, Lit6)) {
            return coerceToBoolean(arg2);
        }
        if (IsEqual.apply(type, Lit7)) {
            return coerceToYailList(arg2);
        }
        if (IsEqual.apply(type, Lit8)) {
            return coerceToInstant(arg2);
        }
        if (IsEqual.apply(type, Lit9)) {
            return coerceToComponent(arg2);
        }
        return !IsEqual.apply(type, Lit10) ? coerceToComponentOfType(arg2, type) : arg2;
    }

    public static Object coerceToText(Object arg) {
        return arg == null ? Lit2 : coerceToString(arg);
    }

    public static Object coerceToInstant(Object arg) {
        return arg instanceof Calendar ? arg : Lit2;
    }

    public static Object coerceToComponent(Object arg) {
        if (!strings.isString(arg)) {
            return arg instanceof Component ? arg : misc.isSymbol(arg) ? lookupComponent(arg) : Lit2;
        } else if (strings.isString$Eq(arg, "")) {
            return null;
        } else {
            try {
                return lookupComponent(misc.string$To$Symbol((CharSequence) arg));
            } catch (ClassCastException e) {
                throw new WrongType(e, "string->symbol", 1, arg);
            }
        }
    }

    public static Object coerceToComponentOfType(Object arg, Object type) {
        Object component = coerceToComponent(arg);
        return (component == Lit2 || Scheme.apply.apply2(Scheme.instanceOf, LList.list2(arg, type$To$Class(type))) == Boolean.FALSE) ? Lit2 : component;
    }

    public static Object type$To$Class(Object type$Mnname) {
        Object[] objArr = new Object[2];
        objArr[0] = Lit11;
        if (type$Mnname == Lit12) {
            type$Mnname = Lit13;
        }
        objArr[1] = type$Mnname;
        return symbolAppend$V(objArr);
    }

    public static Object coerceToNumber(Object arg) {
        if (!numbers.isNumber(arg)) {
            if (strings.isString(arg)) {
                Object x = paddedString$To$Number(arg);
                if (x == Boolean.FALSE) {
                    x = Lit2;
                }
                return x;
            }
            return Lit2;
        }
        return arg;
    }

    public static Object coerceToString(Object arg) {
        frame0 frame0Var = new frame0();
        frame0Var.arg = arg;
        if (frame0Var.arg == null) {
            return "*nothing*";
        }
        if (strings.isString(frame0Var.arg)) {
            return frame0Var.arg;
        }
        if (numbers.isNumber(frame0Var.arg)) {
            return appinventorNumber$To$String(frame0Var.arg);
        }
        if (misc.isBoolean(frame0Var.arg)) {
            return boolean$To$String(frame0Var.arg);
        }
        if (isYailList(frame0Var.arg) != Boolean.FALSE) {
            return coerceToString(yailList$To$KawaList(frame0Var.arg));
        }
        if (lists.isList(frame0Var.arg)) {
            Object arg0 = frame0Var.arg;
            Object obj = LList.Empty;
            while (arg0 != LList.Empty) {
                try {
                    Pair arg02 = (Pair) arg0;
                    Object arg03 = arg02.getCdr();
                    obj = Pair.make(coerceToString(arg02.getCar()), obj);
                    arg0 = arg03;
                } catch (ClassCastException e) {
                    throw new WrongType(e, "arg0", -2, arg0);
                }
            }
            frame0Var.pieces = LList.reverseInPlace(obj);
            return ports.callWithOutputString(frame0Var.lambda$Fn2);
        }
        return ports.callWithOutputString(frame0Var.lambda$Fn3);
    }

    /* compiled from: runtime1714060078409101109.scm */
    /* loaded from: classes.dex */
    public class frame0 extends ModuleBody {
        Object arg;
        final ModuleMethod lambda$Fn2;
        final ModuleMethod lambda$Fn3;
        LList pieces;

        public frame0() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 2, null, 4097);
            moduleMethod.setProperty("source-location", "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm:1187");
            this.lambda$Fn2 = moduleMethod;
            ModuleMethod moduleMethod2 = new ModuleMethod(this, 3, null, 4097);
            moduleMethod2.setProperty("source-location", "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm:1188");
            this.lambda$Fn3 = moduleMethod2;
        }

        void lambda2(Object port) {
            ports.display(this.pieces, port);
        }

        @Override // gnu.expr.ModuleBody
        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            switch (moduleMethod.selector) {
                case 2:
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                case 3:
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                default:
                    return super.match1(moduleMethod, obj, callContext);
            }
        }

        @Override // gnu.expr.ModuleBody
        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            switch (moduleMethod.selector) {
                case 2:
                    lambda2(obj);
                    return Values.empty;
                case 3:
                    lambda3(obj);
                    return Values.empty;
                default:
                    return super.apply1(moduleMethod, obj);
            }
        }

        void lambda3(Object port) {
            ports.display(this.arg, port);
        }
    }

    static Object lambda4(Object arg) {
        frame1 frame1Var = new frame1();
        frame1Var.arg = arg;
        if (Scheme.numEqu.apply2(frame1Var.arg, Lit14) != Boolean.FALSE) {
            return "+infinity";
        }
        if (Scheme.numEqu.apply2(frame1Var.arg, Lit15) != Boolean.FALSE) {
            return "-infinity";
        }
        if (frame1Var.arg == null) {
            return "*nothing*";
        }
        if (misc.isSymbol(frame1Var.arg)) {
            Object obj = frame1Var.arg;
            try {
                return ((Symbol) obj).toString();
            } catch (ClassCastException e) {
                throw new WrongType(e, "symbol->string", 1, obj);
            }
        } else if (strings.isString(frame1Var.arg)) {
            if (strings.isString$Eq(frame1Var.arg, "")) {
                return "*empty-string*";
            }
            return frame1Var.arg;
        } else if (numbers.isNumber(frame1Var.arg)) {
            return appinventorNumber$To$String(frame1Var.arg);
        } else {
            if (misc.isBoolean(frame1Var.arg)) {
                return boolean$To$String(frame1Var.arg);
            }
            if (isYailList(frame1Var.arg) != Boolean.FALSE) {
                return ((Procedure) get$Mndisplay$Mnrepresentation).apply1(yailList$To$KawaList(frame1Var.arg));
            }
            if (lists.isList(frame1Var.arg)) {
                Object arg0 = frame1Var.arg;
                Object obj2 = LList.Empty;
                while (arg0 != LList.Empty) {
                    try {
                        Pair arg02 = (Pair) arg0;
                        Object arg03 = arg02.getCdr();
                        obj2 = Pair.make(((Procedure) get$Mndisplay$Mnrepresentation).apply1(arg02.getCar()), obj2);
                        arg0 = arg03;
                    } catch (ClassCastException e2) {
                        throw new WrongType(e2, "arg0", -2, arg0);
                    }
                }
                frame1Var.pieces = LList.reverseInPlace(obj2);
                return ports.callWithOutputString(frame1Var.lambda$Fn5);
            }
            return ports.callWithOutputString(frame1Var.lambda$Fn6);
        }
    }

    /* compiled from: runtime1714060078409101109.scm */
    /* loaded from: classes.dex */
    public class frame1 extends ModuleBody {
        Object arg;
        final ModuleMethod lambda$Fn5;
        final ModuleMethod lambda$Fn6;
        LList pieces;

        public frame1() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 4, null, 4097);
            moduleMethod.setProperty("source-location", "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm:1215");
            this.lambda$Fn5 = moduleMethod;
            ModuleMethod moduleMethod2 = new ModuleMethod(this, 5, null, 4097);
            moduleMethod2.setProperty("source-location", "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm:1216");
            this.lambda$Fn6 = moduleMethod2;
        }

        void lambda5(Object port) {
            ports.display(this.pieces, port);
        }

        @Override // gnu.expr.ModuleBody
        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            switch (moduleMethod.selector) {
                case 4:
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                case 5:
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                default:
                    return super.match1(moduleMethod, obj, callContext);
            }
        }

        @Override // gnu.expr.ModuleBody
        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            switch (moduleMethod.selector) {
                case 4:
                    lambda5(obj);
                    return Values.empty;
                case 5:
                    lambda6(obj);
                    return Values.empty;
                default:
                    return super.apply1(moduleMethod, obj);
            }
        }

        void lambda6(Object port) {
            ports.display(this.arg, port);
        }
    }

    public static Object stringReplace(Object original, Object replacement$Mntable) {
        if (lists.isNull(replacement$Mntable)) {
            return original;
        }
        return strings.isString$Eq(original, lists.caar.apply1(replacement$Mntable)) ? lists.cadar.apply1(replacement$Mntable) : stringReplace(original, lists.cdr.apply1(replacement$Mntable));
    }

    public static Object coerceToYailList(Object arg) {
        return isYailList(arg) != Boolean.FALSE ? arg : Lit2;
    }

    public static Object coerceToBoolean(Object arg) {
        return misc.isBoolean(arg) ? arg : Lit2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean isIsCoercible(Object x) {
        return ((x == Lit2 ? 1 : 0) + 1) & 1;
    }

    public static Object isAllCoercible(Object args) {
        if (lists.isNull(args)) {
            return Boolean.TRUE;
        }
        boolean x = isIsCoercible(lists.car.apply1(args));
        if (x) {
            return isAllCoercible(lists.cdr.apply1(args));
        }
        return x ? Boolean.TRUE : Boolean.FALSE;
    }

    public static String boolean$To$String(Object b) {
        return b != Boolean.FALSE ? "true" : "false";
    }

    public static Object paddedString$To$Number(Object s) {
        return numbers.string$To$Number(s.toString().trim(), 10);
    }

    public static String $StFormatInexact$St(Object n) {
        try {
            return YailNumberToString.format(((Number) n).doubleValue());
        } catch (ClassCastException e) {
            throw new WrongType(e, "com.google.appinventor.components.runtime.util.YailNumberToString.format(double)", 1, n);
        }
    }

    /* compiled from: runtime1714060078409101109.scm */
    /* loaded from: classes.dex */
    public class frame2 extends ModuleBody {
        final ModuleMethod lambda$Fn7;
        final ModuleMethod lambda$Fn8;
        Object n;

        public frame2() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 6, null, 4097);
            moduleMethod.setProperty("source-location", "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm:1279");
            this.lambda$Fn7 = moduleMethod;
            ModuleMethod moduleMethod2 = new ModuleMethod(this, 7, null, 4097);
            moduleMethod2.setProperty("source-location", "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm:1280");
            this.lambda$Fn8 = moduleMethod2;
        }

        void lambda7(Object port) {
            ports.display(this.n, port);
        }

        @Override // gnu.expr.ModuleBody
        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            switch (moduleMethod.selector) {
                case 6:
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                case 7:
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                default:
                    return super.match1(moduleMethod, obj, callContext);
            }
        }

        @Override // gnu.expr.ModuleBody
        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            switch (moduleMethod.selector) {
                case 6:
                    lambda7(obj);
                    return Values.empty;
                case 7:
                    lambda8(obj);
                    return Values.empty;
                default:
                    return super.apply1(moduleMethod, obj);
            }
        }

        void lambda8(Object port) {
            ports.display(this.n, port);
        }
    }

    public static Object appinventorNumber$To$String(Object n) {
        frame2 frame2Var = new frame2();
        frame2Var.n = n;
        if (numbers.isReal(frame2Var.n)) {
            if (numbers.isInteger(frame2Var.n)) {
                return ports.callWithOutputString(frame2Var.lambda$Fn8);
            }
            if (numbers.isExact(frame2Var.n)) {
                Object obj = frame2Var.n;
                try {
                    return appinventorNumber$To$String(Arithmetic.toInexact((Number) obj));
                } catch (ClassCastException e) {
                    throw new WrongType(e, "exact->inexact", 1, obj);
                }
            }
            return $StFormatInexact$St(frame2Var.n);
        }
        return ports.callWithOutputString(frame2Var.lambda$Fn7);
    }

    public static Object isYailEqual(Object x1, Object x2) {
        boolean x = lists.isNull(x1);
        if (!x ? !x : !lists.isNull(x2)) {
            return Boolean.TRUE;
        }
        boolean x3 = lists.isNull(x1);
        if (!x3 ? !lists.isNull(x2) : !x3) {
            return Boolean.FALSE;
        }
        int i = ((lists.isPair(x1) ? 1 : 0) + 1) & 1;
        if (i == 0 ? i != 0 : !lists.isPair(x2)) {
            return isYailAtomicEqual(x1, x2);
        }
        int i2 = ((lists.isPair(x1) ? 1 : 0) + 1) & 1;
        if (i2 == 0 ? !lists.isPair(x2) : i2 != 0) {
            return Boolean.FALSE;
        }
        Object x4 = isYailEqual(lists.car.apply1(x1), lists.car.apply1(x2));
        if (x4 == Boolean.FALSE) {
            return x4;
        }
        return isYailEqual(lists.cdr.apply1(x1), lists.cdr.apply1(x2));
    }

    public static Object isYailAtomicEqual(Object x1, Object x2) {
        boolean x = IsEqual.apply(x1, x2);
        if (x) {
            return x ? Boolean.TRUE : Boolean.FALSE;
        }
        Object nx1 = asNumber(x1);
        if (nx1 != Boolean.FALSE) {
            Object nx2 = asNumber(x2);
            if (nx2 != Boolean.FALSE) {
                nx2 = Scheme.numEqu.apply2(nx1, nx2);
            }
            return nx2;
        }
        return nx1;
    }

    public static Object asNumber(Object x) {
        Object nx = coerceToNumber(x);
        return nx == Lit2 ? Boolean.FALSE : nx;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean isYailNotEqual(Object x1, Object x2) {
        return ((isYailEqual(x1, x2) != Boolean.FALSE ? 1 : 0) + 1) & 1;
    }

    public static Object processAndDelayed$V(Object[] argsArray) {
        for (Object makeList = LList.makeList(argsArray, 0); !lists.isNull(makeList); makeList = lists.cdr.apply1(makeList)) {
            Object conjunct = Scheme.applyToArgs.apply1(lists.car.apply1(makeList));
            Object coerced$Mnconjunct = coerceToBoolean(conjunct);
            if (!isIsCoercible(coerced$Mnconjunct)) {
                return signalRuntimeError(strings.stringAppend("The AND operation cannot accept the argument ", ((Procedure) get$Mndisplay$Mnrepresentation).apply1(conjunct), " because it is neither true nor false"), strings.stringAppend("Bad argument to AND" instanceof Object[] ? "Bad argument to AND" : new Object[]{"Bad argument to AND"}));
            } else if (coerced$Mnconjunct == Boolean.FALSE) {
                return coerced$Mnconjunct;
            }
        }
        return Boolean.TRUE;
    }

    public static Object processOrDelayed$V(Object[] argsArray) {
        for (Object makeList = LList.makeList(argsArray, 0); !lists.isNull(makeList); makeList = lists.cdr.apply1(makeList)) {
            Object disjunct = Scheme.applyToArgs.apply1(lists.car.apply1(makeList));
            Object coerced$Mndisjunct = coerceToBoolean(disjunct);
            if (!isIsCoercible(coerced$Mndisjunct)) {
                return signalRuntimeError(strings.stringAppend("The OR operation cannot accept the argument ", ((Procedure) get$Mndisplay$Mnrepresentation).apply1(disjunct), " because it is neither true nor false"), strings.stringAppend("Bad argument to OR" instanceof Object[] ? "Bad argument to OR" : new Object[]{"Bad argument to OR"}));
            } else if (coerced$Mndisjunct != Boolean.FALSE) {
                return coerced$Mndisjunct;
            }
        }
        return Boolean.FALSE;
    }

    public static Number yailFloor(Object x) {
        try {
            return Arithmetic.toExact(numbers.floor(LangObjType.coerceRealNum(x)));
        } catch (ClassCastException e) {
            throw new WrongType(e, "floor", 1, x);
        }
    }

    public static Number yailCeiling(Object x) {
        try {
            return Arithmetic.toExact(numbers.ceiling(LangObjType.coerceRealNum(x)));
        } catch (ClassCastException e) {
            throw new WrongType(e, "ceiling", 1, x);
        }
    }

    public static Number yailRound(Object x) {
        try {
            return Arithmetic.toExact(numbers.round(LangObjType.coerceRealNum(x)));
        } catch (ClassCastException e) {
            throw new WrongType(e, "round", 1, x);
        }
    }

    public static Object randomSetSeed(Object seed) {
        if (numbers.isNumber(seed)) {
            try {
                $Strandom$Mnnumber$Mngenerator$St.setSeed(((Number) seed).longValue());
                return Values.empty;
            } catch (ClassCastException e) {
                throw new WrongType(e, "java.util.Random.setSeed(long)", 2, seed);
            }
        } else if (strings.isString(seed)) {
            return randomSetSeed(paddedString$To$Number(seed));
        } else {
            if (lists.isList(seed)) {
                return randomSetSeed(lists.car.apply1(seed));
            }
            if (Boolean.TRUE == seed) {
                return randomSetSeed(Lit16);
            }
            if (Boolean.FALSE == seed) {
                return randomSetSeed(Lit17);
            }
            return randomSetSeed(Lit17);
        }
    }

    public static double randomFraction() {
        return $Strandom$Mnnumber$Mngenerator$St.nextDouble();
    }

    public static Object randomInteger(Object low, Object high) {
        try {
            RealNum low2 = numbers.ceiling(LangObjType.coerceRealNum(low));
            try {
                RealNum low3 = numbers.floor(LangObjType.coerceRealNum(high));
                while (Scheme.numGrt.apply2(low2, low3) != Boolean.FALSE) {
                    RealNum high2 = low2;
                    low2 = low3;
                    low3 = high2;
                }
                Object clow = ((Procedure) clip$Mnto$Mnjava$Mnint$Mnrange).apply1(low2);
                Object chigh = ((Procedure) clip$Mnto$Mnjava$Mnint$Mnrange).apply1(low3);
                AddOp addOp = AddOp.$Pl;
                Random random = $Strandom$Mnnumber$Mngenerator$St;
                Object apply2 = AddOp.$Pl.apply2(Lit16, AddOp.$Mn.apply2(chigh, clow));
                try {
                    Object apply22 = addOp.apply2(Integer.valueOf(random.nextInt(((Number) apply2).intValue())), clow);
                    try {
                        return Arithmetic.toExact((Number) apply22);
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "inexact->exact", 1, apply22);
                    }
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "java.util.Random.nextInt(int)", 2, apply2);
                }
            } catch (ClassCastException e3) {
                throw new WrongType(e3, "floor", 1, high);
            }
        } catch (ClassCastException e4) {
            throw new WrongType(e4, "ceiling", 1, low);
        }
    }

    static Object lambda9(Object x) {
        return numbers.max(lowest, numbers.min(x, highest));
    }

    public static Object yailDivide(Object n, Object d) {
        return Scheme.numEqu.apply2(d, Lit17) != Boolean.FALSE ? DivideOp.$Sl.apply2(n, Lit20) : DivideOp.$Sl.apply2(n, d);
    }

    public static Object degrees$To$RadiansInternal(Object degrees) {
        return DivideOp.$Sl.apply2(MultiplyOp.$St.apply2(degrees, Lit21), Lit22);
    }

    public static Object radians$To$DegreesInternal(Object radians) {
        return DivideOp.$Sl.apply2(MultiplyOp.$St.apply2(radians, Lit22), Lit21);
    }

    public static Object degrees$To$Radians(Object degrees) {
        Object rads = DivideOp.modulo.apply2(degrees$To$RadiansInternal(degrees), Lit23);
        if (Scheme.numGEq.apply2(rads, Lit21) == Boolean.FALSE) {
            return rads;
        }
        return AddOp.$Mn.apply2(rads, Lit24);
    }

    public static Object radians$To$Degrees(Object radians) {
        return DivideOp.modulo.apply2(radians$To$DegreesInternal(radians), Lit25);
    }

    public static double sinDegrees(Object degrees) {
        Object degrees$To$RadiansInternal = degrees$To$RadiansInternal(degrees);
        try {
            return numbers.sin(((Number) degrees$To$RadiansInternal).doubleValue());
        } catch (ClassCastException e) {
            throw new WrongType(e, "sin", 1, degrees$To$RadiansInternal);
        }
    }

    public static double cosDegrees(Object degrees) {
        Object degrees$To$RadiansInternal = degrees$To$RadiansInternal(degrees);
        try {
            return numbers.cos(((Number) degrees$To$RadiansInternal).doubleValue());
        } catch (ClassCastException e) {
            throw new WrongType(e, "cos", 1, degrees$To$RadiansInternal);
        }
    }

    public static double tanDegrees(Object degrees) {
        Object degrees$To$RadiansInternal = degrees$To$RadiansInternal(degrees);
        try {
            return numbers.tan(((Number) degrees$To$RadiansInternal).doubleValue());
        } catch (ClassCastException e) {
            throw new WrongType(e, "tan", 1, degrees$To$RadiansInternal);
        }
    }

    public static Object asinDegrees(Object y) {
        try {
            return radians$To$DegreesInternal(Double.valueOf(numbers.asin(((Number) y).doubleValue())));
        } catch (ClassCastException e) {
            throw new WrongType(e, "asin", 1, y);
        }
    }

    public static Object acosDegrees(Object y) {
        try {
            return radians$To$DegreesInternal(Double.valueOf(numbers.acos(((Number) y).doubleValue())));
        } catch (ClassCastException e) {
            throw new WrongType(e, "acos", 1, y);
        }
    }

    public static Object atanDegrees(Object ratio) {
        return radians$To$DegreesInternal(numbers.atan.apply1(ratio));
    }

    public static Object atan2Degrees(Object y, Object x) {
        return radians$To$DegreesInternal(numbers.atan.apply2(y, x));
    }

    public static String stringToUpperCase(Object s) {
        return s.toString().toUpperCase();
    }

    public static String stringToLowerCase(Object s) {
        return s.toString().toLowerCase();
    }

    public static Object formatAsDecimal(Object number, Object places) {
        if (Scheme.numEqu.apply2(places, Lit17) != Boolean.FALSE) {
            return yailRound(number);
        }
        boolean x = numbers.isInteger(places);
        if (!x ? !x : Scheme.numGrt.apply2(places, Lit17) == Boolean.FALSE) {
            return Format.formatToString(0, strings.stringAppend("~,", appinventorNumber$To$String(places), "f"), number);
        }
        return signalRuntimeError(strings.stringAppend("format-as-decimal was called with ", ((Procedure) get$Mndisplay$Mnrepresentation).apply1(places), " as the number of decimal places.  This number must be a non-negative integer."), strings.stringAppend("Bad number of decimal places for format as decimal" instanceof Object[] ? "Bad number of decimal places for format as decimal" : new Object[]{"Bad number of decimal places for format as decimal"}));
    }

    public static Boolean isIsNumber(Object arg) {
        boolean x;
        boolean x2 = numbers.isNumber(arg);
        return (!x2 ? !(!(x = strings.isString(arg)) ? x : paddedString$To$Number(arg) != Boolean.FALSE) : x2) ? Boolean.FALSE : Boolean.TRUE;
    }

    public static Object isYailList(Object x) {
        Object x2 = isYailListCandidate(x);
        return x2 != Boolean.FALSE ? x instanceof YailList ? Boolean.TRUE : Boolean.FALSE : x2;
    }

    public static Object isYailListCandidate(Object x) {
        boolean x2 = lists.isPair(x);
        return x2 ? IsEqual.apply(lists.car.apply1(x), Lit26) ? Boolean.TRUE : Boolean.FALSE : x2 ? Boolean.TRUE : Boolean.FALSE;
    }

    public static Object yailListContents(Object yail$Mnlist) {
        return lists.cdr.apply1(yail$Mnlist);
    }

    public static void setYailListContents$Ex(Object yail$Mnlist, Object contents) {
        try {
            ((Pair) yail$Mnlist).setCdr(contents);
        } catch (ClassCastException e) {
            throw new WrongType(e, "set-cdr!", 1, yail$Mnlist);
        }
    }

    public static Object insertYailListHeader(Object x) {
        return Invoke.invokeStatic.apply3(YailList, Lit27, x);
    }

    public static Object kawaList$To$YailList(Object x) {
        if (lists.isNull(x)) {
            return new YailList();
        }
        if (lists.isPair(x)) {
            if (isYailList(x) == Boolean.FALSE) {
                Object obj = LList.Empty;
                Object arg0 = x;
                while (arg0 != LList.Empty) {
                    try {
                        Pair arg02 = (Pair) arg0;
                        Object arg03 = arg02.getCdr();
                        obj = Pair.make(kawaList$To$YailList(arg02.getCar()), obj);
                        arg0 = arg03;
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "arg0", -2, arg0);
                    }
                }
                return YailList.makeList((List) LList.reverseInPlace(obj));
            }
            return x;
        }
        return sanitizeAtomic(x);
    }

    public static Object yailList$To$KawaList(Object data) {
        if (isYailList(data) == Boolean.FALSE) {
            return data;
        }
        Object arg0 = yailListContents(data);
        Object obj = LList.Empty;
        while (arg0 != LList.Empty) {
            try {
                Pair arg02 = (Pair) arg0;
                Object arg03 = arg02.getCdr();
                obj = Pair.make(yailList$To$KawaList(arg02.getCar()), obj);
                arg0 = arg03;
            } catch (ClassCastException e) {
                throw new WrongType(e, "arg0", -2, arg0);
            }
        }
        return LList.reverseInPlace(obj);
    }

    public static Object isYailListEmpty(Object x) {
        Object x2 = isYailList(x);
        return x2 != Boolean.FALSE ? lists.isNull(yailListContents(x)) ? Boolean.TRUE : Boolean.FALSE : x2;
    }

    public static YailList makeYailList$V(Object[] argsArray) {
        LList args = LList.makeList(argsArray, 0);
        return YailList.makeList((List) args);
    }

    public static Object yailListCopy(Object yl) {
        if (isYailListEmpty(yl) != Boolean.FALSE) {
            return new YailList();
        }
        if (lists.isPair(yl)) {
            Object arg0 = yailListContents(yl);
            Object obj = LList.Empty;
            while (arg0 != LList.Empty) {
                try {
                    Pair arg02 = (Pair) arg0;
                    Object arg03 = arg02.getCdr();
                    obj = Pair.make(yailListCopy(arg02.getCar()), obj);
                    arg0 = arg03;
                } catch (ClassCastException e) {
                    throw new WrongType(e, "arg0", -2, arg0);
                }
            }
            return YailList.makeList((List) LList.reverseInPlace(obj));
        }
        return yl;
    }

    public static Object yailListToCsvTable(Object yl) {
        if (isYailList(yl) == Boolean.FALSE) {
            return signalRuntimeError("Argument value to \"list to csv table\" must be a list", "Expecting list");
        }
        Apply apply = Scheme.apply;
        ModuleMethod moduleMethod = make$Mnyail$Mnlist;
        Object arg0 = yailListContents(yl);
        Object obj = LList.Empty;
        while (arg0 != LList.Empty) {
            try {
                Pair arg02 = (Pair) arg0;
                Object arg03 = arg02.getCdr();
                obj = Pair.make(convertToStrings(arg02.getCar()), obj);
                arg0 = arg03;
            } catch (ClassCastException e) {
                throw new WrongType(e, "arg0", -2, arg0);
            }
        }
        Object apply2 = apply.apply2(moduleMethod, LList.reverseInPlace(obj));
        try {
            return CsvUtil.toCsvTable((YailList) apply2);
        } catch (ClassCastException e2) {
            throw new WrongType(e2, "com.google.appinventor.components.runtime.util.CsvUtil.toCsvTable(com.google.appinventor.components.runtime.util.YailList)", 1, apply2);
        }
    }

    public static Object yailListToCsvRow(Object yl) {
        if (isYailList(yl) == Boolean.FALSE) {
            return signalRuntimeError("Argument value to \"list to csv row\" must be a list", "Expecting list");
        }
        Object convertToStrings = convertToStrings(yl);
        try {
            return CsvUtil.toCsvRow((YailList) convertToStrings);
        } catch (ClassCastException e) {
            throw new WrongType(e, "com.google.appinventor.components.runtime.util.CsvUtil.toCsvRow(com.google.appinventor.components.runtime.util.YailList)", 1, convertToStrings);
        }
    }

    public static Object convertToStrings(Object yl) {
        if (isYailListEmpty(yl) == Boolean.FALSE) {
            if (isYailList(yl) == Boolean.FALSE) {
                return makeYailList$V(new Object[]{yl});
            }
            Apply apply = Scheme.apply;
            ModuleMethod moduleMethod = make$Mnyail$Mnlist;
            Object arg0 = yailListContents(yl);
            Object obj = LList.Empty;
            while (arg0 != LList.Empty) {
                try {
                    Pair arg02 = (Pair) arg0;
                    Object arg03 = arg02.getCdr();
                    obj = Pair.make(coerceToString(arg02.getCar()), obj);
                    arg0 = arg03;
                } catch (ClassCastException e) {
                    throw new WrongType(e, "arg0", -2, arg0);
                }
            }
            return apply.apply2(moduleMethod, LList.reverseInPlace(obj));
        }
        return yl;
    }

    public static Object yailListFromCsvTable(Object str) {
        String obj;
        if (str == null) {
            obj = null;
        } else {
            try {
                obj = str.toString();
            } catch (Exception exception) {
                return signalRuntimeError("Cannot parse text argument to \"list from csv table\" as a CSV-formatted table", exception.getMessage());
            }
        }
        return CsvUtil.fromCsvTable(obj);
    }

    public static Object yailListFromCsvRow(Object str) {
        String obj;
        if (str == null) {
            obj = null;
        } else {
            try {
                obj = str.toString();
            } catch (Exception exception) {
                return signalRuntimeError("Cannot parse text argument to \"list from csv row\" as CSV-formatted row", exception.getMessage());
            }
        }
        return CsvUtil.fromCsvRow(obj);
    }

    public static int yailListLength(Object yail$Mnlist) {
        Object yailListContents = yailListContents(yail$Mnlist);
        try {
            return lists.length((LList) yailListContents);
        } catch (ClassCastException e) {
            throw new WrongType(e, "length", 1, yailListContents);
        }
    }

    public static Object yailListIndex(Object object, Object yail$Mnlist) {
        Object obj = Lit16;
        for (Object yailListContents = yailListContents(yail$Mnlist); !lists.isNull(yailListContents); yailListContents = lists.cdr.apply1(yailListContents)) {
            if (isYailEqual(object, lists.car.apply1(yailListContents)) == Boolean.FALSE) {
                obj = AddOp.$Pl.apply2(obj, Lit16);
            } else {
                return obj;
            }
        }
        return Lit17;
    }

    public static Object yailListGetItem(Object yail$Mnlist, Object index) {
        if (Scheme.numLss.apply2(index, Lit16) != Boolean.FALSE) {
            signalRuntimeError(Format.formatToString(0, "Select list item: Attempt to get item number ~A, of the list ~A.  The minimum valid item number is 1.", index, ((Procedure) get$Mndisplay$Mnrepresentation).apply1(yail$Mnlist)), "List index smaller than 1");
        }
        int len = yailListLength(yail$Mnlist);
        if (Scheme.numGrt.apply2(index, Integer.valueOf(len)) != Boolean.FALSE) {
            return signalRuntimeError(Format.formatToString(0, "Select list item: Attempt to get item number ~A of a list of length ~A: ~A", index, Integer.valueOf(len), ((Procedure) get$Mndisplay$Mnrepresentation).apply1(yail$Mnlist)), "Select list item: List index too large");
        }
        Object yailListContents = yailListContents(yail$Mnlist);
        Object apply2 = AddOp.$Mn.apply2(index, Lit16);
        try {
            return lists.listRef(yailListContents, ((Number) apply2).intValue());
        } catch (ClassCastException e) {
            throw new WrongType(e, "list-ref", 2, apply2);
        }
    }

    public static void yailListSetItem$Ex(Object yail$Mnlist, Object index, Object value) {
        if (Scheme.numLss.apply2(index, Lit16) != Boolean.FALSE) {
            signalRuntimeError(Format.formatToString(0, "Replace list item: Attempt to replace item number ~A of the list ~A.  The minimum valid item number is 1.", index, ((Procedure) get$Mndisplay$Mnrepresentation).apply1(yail$Mnlist)), "List index smaller than 1");
        }
        int len = yailListLength(yail$Mnlist);
        if (Scheme.numGrt.apply2(index, Integer.valueOf(len)) != Boolean.FALSE) {
            signalRuntimeError(Format.formatToString(0, "Replace list item: Attempt to replace item number ~A of a list of length ~A: ~A", index, Integer.valueOf(len), ((Procedure) get$Mndisplay$Mnrepresentation).apply1(yail$Mnlist)), "List index too large");
        }
        Object yailListContents = yailListContents(yail$Mnlist);
        Object apply2 = AddOp.$Mn.apply2(index, Lit16);
        try {
            Object listTail = lists.listTail(yailListContents, ((Number) apply2).intValue());
            try {
                ((Pair) listTail).setCar(value);
            } catch (ClassCastException e) {
                throw new WrongType(e, "set-car!", 1, listTail);
            }
        } catch (ClassCastException e2) {
            throw new WrongType(e2, "list-tail", 2, apply2);
        }
    }

    public static void yailListRemoveItem$Ex(Object yail$Mnlist, Object index) {
        Object index2 = coerceToNumber(index);
        if (index2 == Lit2) {
            signalRuntimeError(Format.formatToString(0, "Remove list item: index -- ~A -- is not a number", ((Procedure) get$Mndisplay$Mnrepresentation).apply1(index)), "Bad list index");
        }
        if (isYailListEmpty(yail$Mnlist) != Boolean.FALSE) {
            signalRuntimeError(Format.formatToString(0, "Remove list item: Attempt to remove item ~A of an empty list", ((Procedure) get$Mndisplay$Mnrepresentation).apply1(index)), "Invalid list operation");
        }
        if (Scheme.numLss.apply2(index2, Lit16) != Boolean.FALSE) {
            signalRuntimeError(Format.formatToString(0, "Remove list item: Attempt to remove item ~A of the list ~A.  The minimum valid item number is 1.", index2, ((Procedure) get$Mndisplay$Mnrepresentation).apply1(yail$Mnlist)), "List index smaller than 1");
        }
        int len = yailListLength(yail$Mnlist);
        if (Scheme.numGrt.apply2(index2, Integer.valueOf(len)) != Boolean.FALSE) {
            signalRuntimeError(Format.formatToString(0, "Remove list item: Attempt to remove item ~A of a list of length ~A: ~A", index2, Integer.valueOf(len), ((Procedure) get$Mndisplay$Mnrepresentation).apply1(yail$Mnlist)), "List index too large");
        }
        Object apply2 = AddOp.$Mn.apply2(index2, Lit16);
        try {
            Object pair$Mnpointing$Mnto$Mndeletion = lists.listTail(yail$Mnlist, ((Number) apply2).intValue());
            try {
                ((Pair) pair$Mnpointing$Mnto$Mndeletion).setCdr(lists.cddr.apply1(pair$Mnpointing$Mnto$Mndeletion));
            } catch (ClassCastException e) {
                throw new WrongType(e, "set-cdr!", 1, pair$Mnpointing$Mnto$Mndeletion);
            }
        } catch (ClassCastException e2) {
            throw new WrongType(e2, "list-tail", 2, apply2);
        }
    }

    public static void yailListInsertItem$Ex(Object yail$Mnlist, Object index, Object item) {
        Object index2 = coerceToNumber(index);
        if (index2 == Lit2) {
            signalRuntimeError(Format.formatToString(0, "Insert list item: index (~A) is not a number", ((Procedure) get$Mndisplay$Mnrepresentation).apply1(index)), "Bad list index");
        }
        if (Scheme.numLss.apply2(index2, Lit16) != Boolean.FALSE) {
            signalRuntimeError(Format.formatToString(0, "Insert list item: Attempt to insert item ~A into the list ~A.  The minimum valid item number is 1.", index2, ((Procedure) get$Mndisplay$Mnrepresentation).apply1(yail$Mnlist)), "List index smaller than 1");
        }
        int len$Pl1 = yailListLength(yail$Mnlist) + 1;
        if (Scheme.numGrt.apply2(index2, Integer.valueOf(len$Pl1)) != Boolean.FALSE) {
            signalRuntimeError(Format.formatToString(0, "Insert list item: Attempt to insert item ~A into the list ~A.  The maximum valid item number is ~A.", index2, ((Procedure) get$Mndisplay$Mnrepresentation).apply1(yail$Mnlist), Integer.valueOf(len$Pl1)), "List index too large");
        }
        Object contents = yailListContents(yail$Mnlist);
        if (Scheme.numEqu.apply2(index2, Lit16) != Boolean.FALSE) {
            setYailListContents$Ex(yail$Mnlist, lists.cons(item, contents));
            return;
        }
        Object apply2 = AddOp.$Mn.apply2(index2, Lit18);
        try {
            Object at$Mnitem = lists.listTail(contents, ((Number) apply2).intValue());
            try {
                ((Pair) at$Mnitem).setCdr(lists.cons(item, lists.cdr.apply1(at$Mnitem)));
            } catch (ClassCastException e) {
                throw new WrongType(e, "set-cdr!", 1, at$Mnitem);
            }
        } catch (ClassCastException e2) {
            throw new WrongType(e2, "list-tail", 2, apply2);
        }
    }

    public static void yailListAppend$Ex(Object yail$Mnlist$MnA, Object yail$Mnlist$MnB) {
        Object yailListContents = yailListContents(yail$Mnlist$MnA);
        try {
            Object listTail = lists.listTail(yail$Mnlist$MnA, lists.length((LList) yailListContents));
            try {
                ((Pair) listTail).setCdr(lambda10listCopy(yailListContents(yail$Mnlist$MnB)));
            } catch (ClassCastException e) {
                throw new WrongType(e, "set-cdr!", 1, listTail);
            }
        } catch (ClassCastException e2) {
            throw new WrongType(e2, "length", 1, yailListContents);
        }
    }

    public static Object lambda10listCopy(Object l) {
        return lists.isNull(l) ? LList.Empty : lists.cons(lists.car.apply1(l), lambda10listCopy(lists.cdr.apply1(l)));
    }

    public static void yailListAddToList$Ex$V(Object yail$Mnlist, Object[] argsArray) {
        LList items = LList.makeList(argsArray, 0);
        yailListAppend$Ex(yail$Mnlist, Scheme.apply.apply2(make$Mnyail$Mnlist, items));
    }

    @Override // gnu.expr.ModuleBody
    public Object applyN(ModuleMethod moduleMethod, Object[] objArr) {
        switch (moduleMethod.selector) {
            case 12:
                return call$MnInitializeOfComponents$V(objArr);
            case 21:
                return setAndCoercePropertyAndCheck$Ex(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4]);
            case 22:
                return symbolAppend$V(objArr);
            case 35:
                return callComponentTypeMethod(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4]);
            case 71:
                return processAndDelayed$V(objArr);
            case 72:
                return processOrDelayed$V(objArr);
            case 104:
                return makeYailList$V(objArr);
            case 118:
                Object obj = objArr[0];
                int length = objArr.length - 1;
                Object[] objArr2 = new Object[length];
                while (true) {
                    length--;
                    if (length < 0) {
                        yailListAddToList$Ex$V(obj, objArr2);
                        return Values.empty;
                    }
                    objArr2[length] = objArr[length + 1];
                }
            default:
                return super.applyN(moduleMethod, objArr);
        }
    }

    public static Boolean isYailListMember(Object object, Object yail$Mnlist) {
        Object result = lists.member(object, yailListContents(yail$Mnlist), yail$Mnequal$Qu);
        return result != Boolean.FALSE ? Boolean.TRUE : Boolean.FALSE;
    }

    public static Object yailListPickRandom(Object yail$Mnlist) {
        if (isYailListEmpty(yail$Mnlist) != Boolean.FALSE) {
            signalRuntimeError(Format.formatToString(0, "Pick random item: Attempt to pick a random element from an empty list" instanceof Object[] ? "Pick random item: Attempt to pick a random element from an empty list" : new Object[]{"Pick random item: Attempt to pick a random element from an empty list"}), "Invalid list operation");
        }
        return yailListGetItem(yail$Mnlist, randomInteger(Lit16, Integer.valueOf(yailListLength(yail$Mnlist))));
    }

    public static Object yailForEach(Object proc, Object yail$Mnlist) {
        Object verified$Mnlist = coerceToYailList(yail$Mnlist);
        if (verified$Mnlist == Lit2) {
            return signalRuntimeError(Format.formatToString(0, "The second argument to foreach is not a list.  The second argument is: ~A", ((Procedure) get$Mndisplay$Mnrepresentation).apply1(yail$Mnlist)), "Bad list argument to foreach");
        }
        Object arg0 = yailListContents(verified$Mnlist);
        while (arg0 != LList.Empty) {
            try {
                Pair arg02 = (Pair) arg0;
                Scheme.applyToArgs.apply2(proc, arg02.getCar());
                arg0 = arg02.getCdr();
            } catch (ClassCastException e) {
                throw new WrongType(e, "arg0", -2, arg0);
            }
        }
        return null;
    }

    public static Object yailForRange(Object proc, Object start, Object end, Object step) {
        Object nstart = coerceToNumber(start);
        Object nend = coerceToNumber(end);
        Object nstep = coerceToNumber(step);
        if (nstart == Lit2) {
            signalRuntimeError(Format.formatToString(0, "For range: the start value -- ~A -- is not a number", ((Procedure) get$Mndisplay$Mnrepresentation).apply1(start)), "Bad start value");
        }
        if (nend == Lit2) {
            signalRuntimeError(Format.formatToString(0, "For range: the end value -- ~A -- is not a number", ((Procedure) get$Mndisplay$Mnrepresentation).apply1(end)), "Bad end value");
        }
        if (nstep == Lit2) {
            signalRuntimeError(Format.formatToString(0, "For range: the step value -- ~A -- is not a number", ((Procedure) get$Mndisplay$Mnrepresentation).apply1(step)), "Bad step value");
        }
        return yailForRangeWithNumericCheckedArgs(proc, nstart, nend, nstep);
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0049, code lost:
        if (r3 != false) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x006f, code lost:
        if (r3 == false) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:?, code lost:
        return null;
     */
    /* JADX WARN: Removed duplicated region for block: B:27:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00ad  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00b0 A[LOOP:0: B:29:0x0080->B:45:0x00b0, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.Object yailForRangeWithNumericCheckedArgs(java.lang.Object r9, java.lang.Object r10, java.lang.Object r11, java.lang.Object r12) {
        /*
            Method dump skipped, instructions count: 242
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.youngandroid.runtime.yailForRangeWithNumericCheckedArgs(java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object):java.lang.Object");
    }

    @Override // gnu.expr.ModuleBody
    public Object apply4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4) {
        switch (moduleMethod.selector) {
            case 11:
                return addComponentWithinRepl(obj, obj2, obj3, obj4);
            case 17:
                return setAndCoerceProperty$Ex(obj, obj2, obj3, obj4);
            case 34:
                return callComponentMethod(obj, obj2, obj3, obj4);
            case 36:
                return callYailPrimitive(obj, obj2, obj3, obj4);
            case XDataType.NAME_TYPE_CODE /* 43 */:
                return callWithCoercedArgs(obj, obj2, obj3, obj4);
            case XDataType.NCNAME_TYPE_CODE /* 44 */:
                return $PcSetAndCoerceProperty$Ex(obj, obj2, obj3, obj4);
            case 122:
                return yailForRange(obj, obj2, obj3, obj4);
            case 123:
                return yailForRangeWithNumericCheckedArgs(obj, obj2, obj3, obj4);
            default:
                return super.apply4(moduleMethod, obj, obj2, obj3, obj4);
        }
    }

    public static Object yailNumberRange(Object low, Object high) {
        try {
            try {
                return kawaList$To$YailList(lambda11loop(Arithmetic.toExact(numbers.ceiling(LangObjType.coerceRealNum(low))), Arithmetic.toExact(numbers.floor(LangObjType.coerceRealNum(high)))));
            } catch (ClassCastException e) {
                throw new WrongType(e, "floor", 1, high);
            }
        } catch (ClassCastException e2) {
            throw new WrongType(e2, "ceiling", 1, low);
        }
    }

    public static Object lambda11loop(Object a, Object b) {
        return Scheme.numGrt.apply2(a, b) != Boolean.FALSE ? LList.Empty : lists.cons(a, lambda11loop(AddOp.$Pl.apply2(a, Lit16), b));
    }

    public static Object yailAlistLookup(Object key, Object yail$Mnlist$Mnof$Mnpairs, Object obj) {
        androidLog(Format.formatToString(0, "List alist lookup key is  ~A and table is ~A", key, yail$Mnlist$Mnof$Mnpairs));
        for (Object pairs$Mnto$Mncheck = yailListContents(yail$Mnlist$Mnof$Mnpairs); !lists.isNull(pairs$Mnto$Mncheck); pairs$Mnto$Mncheck = lists.cdr.apply1(pairs$Mnto$Mncheck)) {
            if (isPairOk(lists.car.apply1(pairs$Mnto$Mncheck)) == Boolean.FALSE) {
                return signalRuntimeError(Format.formatToString(0, "Lookup in pairs: the list ~A is not a well-formed list of pairs", ((Procedure) get$Mndisplay$Mnrepresentation).apply1(yail$Mnlist$Mnof$Mnpairs)), "Invalid list of pairs");
            }
            if (IsEqual.apply(key, lists.car.apply1(yailListContents(lists.car.apply1(pairs$Mnto$Mncheck))))) {
                return lists.cadr.apply1(yailListContents(lists.car.apply1(pairs$Mnto$Mncheck)));
            }
        }
        return obj;
    }

    public static Object isPairOk(Object candidate$Mnpair) {
        Object x = isYailList(candidate$Mnpair);
        if (x != Boolean.FALSE) {
            Object yailListContents = yailListContents(candidate$Mnpair);
            try {
                return lists.length((LList) yailListContents) == 2 ? Boolean.TRUE : Boolean.FALSE;
            } catch (ClassCastException e) {
                throw new WrongType(e, "length", 1, yailListContents);
            }
        }
        return x;
    }

    public static Object makeDisjunct(Object x) {
        if (lists.isNull(lists.cdr.apply1(x))) {
            Object apply1 = lists.car.apply1(x);
            return Pattern.quote(apply1 != null ? apply1.toString() : null);
        }
        Object[] objArr = new Object[2];
        Object apply12 = lists.car.apply1(x);
        objArr[0] = Pattern.quote(apply12 != null ? apply12.toString() : null);
        objArr[1] = strings.stringAppend("|", makeDisjunct(lists.cdr.apply1(x)));
        return strings.stringAppend(objArr);
    }

    public static Object array$To$List(Object arr) {
        try {
            return insertYailListHeader(LList.makeList((Object[]) arr, 0));
        } catch (ClassCastException e) {
            throw new WrongType(e, "gnu.lists.LList.makeList(java.lang.Object[],int)", 1, arr);
        }
    }

    public static int stringStartsAt(Object text, Object piece) {
        return text.toString().indexOf(piece.toString()) + 1;
    }

    public static Boolean stringContains(Object text, Object piece) {
        return stringStartsAt(text, piece) == 0 ? Boolean.FALSE : Boolean.TRUE;
    }

    public static Object stringSplitAtFirst(Object text, Object at) {
        return array$To$List(text.toString().split(Pattern.quote(at == null ? null : at.toString()), 2));
    }

    public static Object stringSplitAtFirstOfAny(Object text, Object at) {
        if (lists.isNull(yailListContents(at))) {
            return signalRuntimeError("split at first of any: The list of places to split at is empty.", "Invalid text operation");
        }
        String obj = text.toString();
        Object makeDisjunct = makeDisjunct(yailListContents(at));
        return array$To$List(obj.split(makeDisjunct == null ? null : makeDisjunct.toString(), 2));
    }

    public static Object stringSplit(Object text, Object at) {
        return array$To$List(text.toString().split(Pattern.quote(at == null ? null : at.toString())));
    }

    public static Object stringSplitAtAny(Object text, Object at) {
        if (lists.isNull(yailListContents(at))) {
            return signalRuntimeError("split at any: The list of places to split at is empty.", "Invalid text operation");
        }
        String obj = text.toString();
        Object makeDisjunct = makeDisjunct(yailListContents(at));
        return array$To$List(obj.split(makeDisjunct == null ? null : makeDisjunct.toString(), -1));
    }

    public static Object stringSplitAtSpaces(Object text) {
        return array$To$List(text.toString().trim().split("\\s+", -1));
    }

    public static Object stringSubstring(Object wholestring, Object start, Object length) {
        try {
            int len = strings.stringLength((CharSequence) wholestring);
            if (Scheme.numLss.apply2(start, Lit16) != Boolean.FALSE) {
                return signalRuntimeError(Format.formatToString(0, "Segment: Start is less than 1 (~A).", start), "Invalid text operation");
            }
            if (Scheme.numLss.apply2(length, Lit17) != Boolean.FALSE) {
                return signalRuntimeError(Format.formatToString(0, "Segment: Length is negative (~A).", length), "Invalid text operation");
            }
            if (Scheme.numGrt.apply2(AddOp.$Pl.apply2(AddOp.$Mn.apply2(start, Lit16), length), Integer.valueOf(len)) != Boolean.FALSE) {
                return signalRuntimeError(Format.formatToString(0, "Segment: Start (~A) + length (~A) - 1 exceeds text length (~A).", start, length, Integer.valueOf(len)), "Invalid text operation");
            }
            try {
                CharSequence charSequence = (CharSequence) wholestring;
                Object apply2 = AddOp.$Mn.apply2(start, Lit16);
                try {
                    int intValue = ((Number) apply2).intValue();
                    Object apply22 = AddOp.$Pl.apply2(AddOp.$Mn.apply2(start, Lit16), length);
                    try {
                        return strings.substring(charSequence, intValue, ((Number) apply22).intValue());
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "substring", 3, apply22);
                    }
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "substring", 2, apply2);
                }
            } catch (ClassCastException e3) {
                throw new WrongType(e3, "substring", 1, wholestring);
            }
        } catch (ClassCastException e4) {
            throw new WrongType(e4, "string-length", 1, wholestring);
        }
    }

    public static String stringTrim(Object text) {
        return text.toString().trim();
    }

    public static String stringReplaceAll(Object text, Object substring, Object replacement) {
        return text.toString().replaceAll(Pattern.quote(substring.toString()), replacement.toString());
    }

    @Override // gnu.expr.ModuleBody
    public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
        switch (moduleMethod.selector) {
            case 20:
                return getPropertyAndCheck(obj, obj2, obj3);
            case XDataType.ID_TYPE_CODE /* 45 */:
                return $PcSetSubformLayoutProperty$Ex(obj, obj2, obj3);
            case 48:
                return coerceArgs(obj, obj2, obj3);
            case 114:
                yailListSetItem$Ex(obj, obj2, obj3);
                return Values.empty;
            case 116:
                yailListInsertItem$Ex(obj, obj2, obj3);
                return Values.empty;
            case 125:
                return yailAlistLookup(obj, obj2, obj3);
            case 136:
                return stringSubstring(obj, obj2, obj3);
            case 138:
                return stringReplaceAll(obj, obj2, obj3);
            default:
                return super.apply3(moduleMethod, obj, obj2, obj3);
        }
    }

    public static boolean isStringEmpty(Object text) {
        try {
            return strings.stringLength((CharSequence) text) == 0;
        } catch (ClassCastException e) {
            throw new WrongType(e, "string-length", 1, text);
        }
    }

    public static Object textDeobsfucate(Object text, Object confounder) {
        Object obj = confounder;
        while (strings.stringLength((CharSequence) obj) < strings.stringLength((CharSequence) text)) {
            try {
                try {
                    obj = strings.stringAppend(obj, obj);
                } catch (ClassCastException e) {
                    throw new WrongType(e, "string-length", 1, text);
                }
            } catch (ClassCastException e2) {
                throw new WrongType(e2, "string-length", 1, obj);
            }
        }
        Object obj2 = Lit17;
        LList lList = LList.Empty;
        try {
            Integer valueOf = Integer.valueOf(strings.stringLength((CharSequence) text));
            while (Scheme.numGEq.apply2(obj2, Integer.valueOf(strings.stringLength((CharSequence) text))) == Boolean.FALSE) {
                try {
                    try {
                        try {
                            int c = Char.make(strings.stringRef((CharSequence) text, ((Number) obj2).intValue())).intValue();
                            Object b = BitwiseOp.and.apply2(BitwiseOp.xor.apply2(Integer.valueOf(c), AddOp.$Mn.apply2(valueOf, obj2)), Lit28);
                            Object b2 = BitwiseOp.and.apply2(BitwiseOp.xor.apply2(Integer.valueOf(c >> 8), obj2), Lit28);
                            Object b3 = BitwiseOp.and.apply2(BitwiseOp.ior.apply2(BitwiseOp.ashiftl.apply2(b2, Lit29), b), Lit28);
                            try {
                                try {
                                    Object b4 = BitwiseOp.and.apply2(BitwiseOp.xor.apply2(b3, Integer.valueOf(Char.make(strings.stringRef((CharSequence) obj, ((Number) obj2).intValue())).intValue())), Lit28);
                                    Pair acc = lists.cons(b4, lList);
                                    obj2 = AddOp.$Pl.apply2(Lit16, obj2);
                                    lList = acc;
                                } catch (ClassCastException e3) {
                                    throw new WrongType(e3, "string-ref", 2, obj2);
                                }
                            } catch (ClassCastException e4) {
                                throw new WrongType(e4, "string-ref", 1, obj);
                            }
                        } catch (ClassCastException e5) {
                            throw new WrongType(e5, "string-ref", 2, obj2);
                        }
                    } catch (ClassCastException e6) {
                        throw new WrongType(e6, "string-ref", 1, text);
                    }
                } catch (ClassCastException e7) {
                    throw new WrongType(e7, "string-length", 1, text);
                }
            }
            try {
                LList reverse = lists.reverse(lList);
                Object obj3 = LList.Empty;
                while (reverse != LList.Empty) {
                    try {
                        Pair arg0 = (Pair) reverse;
                        Object arg02 = arg0.getCdr();
                        Object car = arg0.getCar();
                        try {
                            obj3 = Pair.make(Char.make(((Number) car).intValue()), obj3);
                            reverse = arg02;
                        } catch (ClassCastException e8) {
                            throw new WrongType(e8, "integer->char", 1, car);
                        }
                    } catch (ClassCastException e9) {
                        throw new WrongType(e9, "arg0", -2, reverse);
                    }
                }
                return strings.list$To$String(LList.reverseInPlace(obj3));
            } catch (ClassCastException e10) {
                throw new WrongType(e10, "reverse", 1, lList);
            }
        } catch (ClassCastException e11) {
            throw new WrongType(e11, "string-length", 1, text);
        }
    }

    public static Number makeExactYailInteger(Object x) {
        Object coerceToNumber = coerceToNumber(x);
        try {
            return numbers.exact(numbers.round(LangObjType.coerceRealNum(coerceToNumber)));
        } catch (ClassCastException e) {
            throw new WrongType(e, "round", 1, coerceToNumber);
        }
    }

    public static Object makeColor(Object color$Mncomponents) {
        Number alpha;
        Number red = makeExactYailInteger(yailListGetItem(color$Mncomponents, Lit16));
        Number green = makeExactYailInteger(yailListGetItem(color$Mncomponents, Lit18));
        Number blue = makeExactYailInteger(yailListGetItem(color$Mncomponents, Lit32));
        if (yailListLength(color$Mncomponents) <= 3) {
            Object obj = $Stalpha$Mnopaque$St;
            try {
                alpha = (Number) obj;
            } catch (ClassCastException e) {
                throw new WrongType(e, "alpha", -2, obj);
            }
        } else {
            alpha = makeExactYailInteger(yailListGetItem(color$Mncomponents, Lit33));
        }
        return BitwiseOp.ior.apply2(BitwiseOp.ior.apply2(BitwiseOp.ior.apply2(BitwiseOp.ashiftl.apply2(BitwiseOp.and.apply2(alpha, $Stmax$Mncolor$Mncomponent$St), $Stcolor$Mnalpha$Mnposition$St), BitwiseOp.ashiftl.apply2(BitwiseOp.and.apply2(red, $Stmax$Mncolor$Mncomponent$St), $Stcolor$Mnred$Mnposition$St)), BitwiseOp.ashiftl.apply2(BitwiseOp.and.apply2(green, $Stmax$Mncolor$Mncomponent$St), $Stcolor$Mngreen$Mnposition$St)), BitwiseOp.ashiftl.apply2(BitwiseOp.and.apply2(blue, $Stmax$Mncolor$Mncomponent$St), $Stcolor$Mnblue$Mnposition$St));
    }

    public static Object splitColor(Object color) {
        Number intcolor = makeExactYailInteger(color);
        return kawaList$To$YailList(LList.list4(BitwiseOp.and.apply2(BitwiseOp.ashiftr.apply2(intcolor, $Stcolor$Mnred$Mnposition$St), $Stmax$Mncolor$Mncomponent$St), BitwiseOp.and.apply2(BitwiseOp.ashiftr.apply2(intcolor, $Stcolor$Mngreen$Mnposition$St), $Stmax$Mncolor$Mncomponent$St), BitwiseOp.and.apply2(BitwiseOp.ashiftr.apply2(intcolor, $Stcolor$Mnblue$Mnposition$St), $Stmax$Mncolor$Mncomponent$St), BitwiseOp.and.apply2(BitwiseOp.ashiftr.apply2(intcolor, $Stcolor$Mnalpha$Mnposition$St), $Stmax$Mncolor$Mncomponent$St)));
    }

    public static void closeScreen() {
        Form.finishActivity();
    }

    public static void closeApplication() {
        Form.finishApplication();
    }

    public static void openAnotherScreen(Object screen$Mnname) {
        Object coerceToString = coerceToString(screen$Mnname);
        Form.switchForm(coerceToString == null ? null : coerceToString.toString());
    }

    public static void openAnotherScreenWithStartValue(Object screen$Mnname, Object start$Mnvalue) {
        Object coerceToString = coerceToString(screen$Mnname);
        Form.switchFormWithStartValue(coerceToString == null ? null : coerceToString.toString(), start$Mnvalue);
    }

    public static Object getStartValue() {
        return sanitizeComponentData(Form.getStartValue());
    }

    public static void closeScreenWithValue(Object result) {
        Form.finishActivityWithResult(result);
    }

    public static String getPlainStartText() {
        return Form.getStartText();
    }

    public static void closeScreenWithPlainText(Object string) {
        Form.finishActivityWithTextResult(string == null ? null : string.toString());
    }

    public static String getServerAddressFromWifi() {
        Object slotValue = SlotGet.getSlotValue(false, Scheme.applyToArgs.apply1(GetNamedPart.getNamedPart.apply2(((Context) $Stthis$Mnform$St).getSystemService(Context.WIFI_SERVICE), Lit35)), "ipAddress", "ipAddress", "getIpAddress", "isIpAddress", Scheme.instance);
        try {
            return Formatter.formatIpAddress(((Number) slotValue).intValue());
        } catch (ClassCastException e) {
            throw new WrongType(e, "android.text.format.Formatter.formatIpAddress(int)", 1, slotValue);
        }
    }

    public static Object inUi(Object blockid, Object promise) {
        frame3 frame3Var = new frame3();
        frame3Var.blockid = blockid;
        frame3Var.promise = promise;
        $Stthis$Mnis$Mnthe$Mnrepl$St = Boolean.TRUE;
        return Scheme.applyToArgs.apply2(GetNamedPart.getNamedPart.apply2($Stui$Mnhandler$St, Lit36), thread.runnable(frame3Var.lambda$Fn10));
    }

    /* compiled from: runtime1714060078409101109.scm */
    /* loaded from: classes.dex */
    public class frame3 extends ModuleBody {
        Object blockid;
        final ModuleMethod lambda$Fn10;
        Object promise;

        public frame3() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 8, null, 0);
            moduleMethod.setProperty("source-location", "C:\\Users\\LEO\\AppData\\Local\\Temp\\runtime1714060078409101109.scm:2287");
            this.lambda$Fn10 = moduleMethod;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 8 ? lambda12() : super.apply0(moduleMethod);
        }

        Object lambda12() {
            Pair list2;
            Object obj = this.blockid;
            try {
                try {
                    list2 = LList.list2("OK", ((Procedure) runtime.get$Mndisplay$Mnrepresentation).apply1(misc.force(this.promise)));
                } catch (YailRuntimeError exception) {
                    runtime.androidLog(exception.getMessage());
                    list2 = LList.list2("NOK", exception.getMessage());
                }
            } catch (Exception exception2) {
                runtime.androidLog(exception2.getMessage());
                exception2.printStackTrace();
                list2 = LList.list2("NOK", exception2.getMessage());
            }
            return runtime.sendToBlock(obj, list2);
        }

        @Override // gnu.expr.ModuleBody
        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector == 8) {
                callContext.proc = moduleMethod;
                callContext.pc = 0;
                return 0;
            }
            return super.match0(moduleMethod, callContext);
        }
    }

    public static Object sendToBlock(Object blockid, Object message) {
        Object good = lists.car.apply1(message);
        Object value = lists.cadr.apply1(message);
        RetValManager.appendReturnValue(blockid == null ? null : blockid.toString(), good == null ? null : good.toString(), value != null ? value.toString() : null);
        return Values.empty;
    }

    public static Object clearCurrentForm() {
        if ($Stthis$Mnform$St != null) {
            clearInitThunks();
            resetCurrentFormEnvironment();
            EventDispatcher.unregisterAllEventsForDelegation();
            return Invoke.invoke.apply2($Stthis$Mnform$St, "clear");
        }
        return Values.empty;
    }

    public static Object setFormName(Object form$Mnname) {
        return Invoke.invoke.apply3($Stthis$Mnform$St, "setFormName", form$Mnname);
    }

    public static Object removeComponent(Object component$Mnname) {
        try {
            SimpleSymbol component$Mnsymbol = misc.string$To$Symbol((CharSequence) component$Mnname);
            Object component$Mnobject = lookupInCurrentFormEnvironment(component$Mnsymbol);
            deleteFromCurrentFormEnvironment(component$Mnsymbol);
            return $Stthis$Mnform$St != null ? Invoke.invoke.apply3($Stthis$Mnform$St, "deleteComponent", component$Mnobject) : Values.empty;
        } catch (ClassCastException e) {
            throw new WrongType(e, "string->symbol", 1, component$Mnname);
        }
    }

    public static Object renameComponent(Object old$Mncomponent$Mnname, Object new$Mncomponent$Mnname) {
        try {
            try {
                return renameInCurrentFormEnvironment(misc.string$To$Symbol((CharSequence) old$Mncomponent$Mnname), misc.string$To$Symbol((CharSequence) new$Mncomponent$Mnname));
            } catch (ClassCastException e) {
                throw new WrongType(e, "string->symbol", 1, new$Mncomponent$Mnname);
            }
        } catch (ClassCastException e2) {
            throw new WrongType(e2, "string->symbol", 1, old$Mncomponent$Mnname);
        }
    }

    @Override // gnu.expr.ModuleBody
    public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
        switch (moduleMethod.selector) {
            case 13:
                return addInitThunk(obj, obj2);
            case 18:
                return getProperty$1(obj, obj2);
            case 25:
                try {
                    return addToCurrentFormEnvironment((Symbol) obj, obj2);
                } catch (ClassCastException e) {
                    throw new WrongType(e, "add-to-current-form-environment", 1, obj);
                }
            case 26:
                try {
                    return lookupInCurrentFormEnvironment((Symbol) obj, obj2);
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "lookup-in-current-form-environment", 1, obj);
                }
            case 29:
                try {
                    try {
                        return renameInCurrentFormEnvironment((Symbol) obj, (Symbol) obj2);
                    } catch (ClassCastException e3) {
                        throw new WrongType(e3, "rename-in-current-form-environment", 2, obj2);
                    }
                } catch (ClassCastException e4) {
                    throw new WrongType(e4, "rename-in-current-form-environment", 1, obj);
                }
            case 30:
                try {
                    return addGlobalVarToCurrentFormEnvironment((Symbol) obj, obj2);
                } catch (ClassCastException e5) {
                    throw new WrongType(e5, "add-global-var-to-current-form-environment", 1, obj);
                }
            case 31:
                try {
                    return lookupGlobalVarInCurrentFormEnvironment((Symbol) obj, obj2);
                } catch (ClassCastException e6) {
                    throw new WrongType(e6, "lookup-global-var-in-current-form-environment", 1, obj);
                }
            case 41:
                return signalRuntimeError(obj, obj2);
            case XDataType.IDREF_TYPE_CODE /* 46 */:
                return generateRuntimeTypeError(obj, obj2);
            case 49:
                return coerceArg(obj, obj2);
            case 53:
                return coerceToComponentOfType(obj, obj2);
            case 58:
                return stringReplace(obj, obj2);
            case 67:
                return isYailEqual(obj, obj2);
            case 68:
                return isYailAtomicEqual(obj, obj2);
            case PrettyWriter.NEWLINE_FILL /* 70 */:
                return isYailNotEqual(obj, obj2) ? Boolean.TRUE : Boolean.FALSE;
            case PrettyWriter.NEWLINE_LINEAR /* 78 */:
                return randomInteger(obj, obj2);
            case 80:
                return yailDivide(obj, obj2);
            case 91:
                return atan2Degrees(obj, obj2);
            case 94:
                return formatAsDecimal(obj, obj2);
            case 99:
                setYailListContents$Ex(obj, obj2);
                return Values.empty;
            case DateTime.TIME_MASK /* 112 */:
                return yailListIndex(obj, obj2);
            case 113:
                return yailListGetItem(obj, obj2);
            case 115:
                yailListRemoveItem$Ex(obj, obj2);
                return Values.empty;
            case 117:
                yailListAppend$Ex(obj, obj2);
                return Values.empty;
            case 119:
                return isYailListMember(obj, obj2);
            case 121:
                return yailForEach(obj, obj2);
            case 124:
                return yailNumberRange(obj, obj2);
            case 129:
                return Integer.valueOf(stringStartsAt(obj, obj2));
            case 130:
                return stringContains(obj, obj2);
            case 131:
                return stringSplitAtFirst(obj, obj2);
            case 132:
                return stringSplitAtFirstOfAny(obj, obj2);
            case 133:
                return stringSplit(obj, obj2);
            case 134:
                return stringSplitAtAny(obj, obj2);
            case 140:
                return textDeobsfucate(obj, obj2);
            case 147:
                openAnotherScreenWithStartValue(obj, obj2);
                return Values.empty;
            case 153:
                return inUi(obj, obj2);
            case 154:
                return sendToBlock(obj, obj2);
            case 158:
                return renameComponent(obj, obj2);
            default:
                return super.apply2(moduleMethod, obj, obj2);
        }
    }

    public static void initRuntime() {
        setThisForm();
        $Stui$Mnhandler$St = new Handler();
    }

    public static void setThisForm() {
        $Stthis$Mnform$St = Form.getActiveForm();
    }

    @Override // gnu.expr.ModuleBody
    public Object apply0(ModuleMethod moduleMethod) {
        switch (moduleMethod.selector) {
            case 15:
                clearInitThunks();
                return Values.empty;
            case 33:
                return resetCurrentFormEnvironment();
            case PrettyWriter.NEWLINE_MISER /* 77 */:
                return Double.valueOf(randomFraction());
            case ComponentConstants.VIDEOPLAYER_PREFERRED_HEIGHT /* 144 */:
                closeScreen();
                return Values.empty;
            case 145:
                closeApplication();
                return Values.empty;
            case 148:
                return getStartValue();
            case 150:
                return getPlainStartText();
            case 152:
                return getServerAddressFromWifi();
            case 155:
                return clearCurrentForm();
            case 159:
                initRuntime();
                return Values.empty;
            case ComponentConstants.TEXTBOX_PREFERRED_WIDTH /* 160 */:
                setThisForm();
                return Values.empty;
            default:
                return super.apply0(moduleMethod);
        }
    }

    public static Object clarify(Object sl) {
        return clarify1(yailListContents(sl));
    }

    public static Object clarify1(Object sl) {
        String sp;
        if (lists.isNull(sl)) {
            return LList.Empty;
        }
        if (IsEqual.apply(lists.car.apply1(sl), "")) {
            sp = "<empty>";
        } else {
            sp = IsEqual.apply(lists.car.apply1(sl), " ") ? "<space>" : lists.car.apply1(sl);
        }
        return lists.cons(sp, clarify1(lists.cdr.apply1(sl)));
    }
}
