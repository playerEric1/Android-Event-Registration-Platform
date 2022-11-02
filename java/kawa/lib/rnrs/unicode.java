package kawa.lib.rnrs;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.UnicodeUtils;
import gnu.lists.Consumer;
import gnu.lists.FString;
import gnu.mapping.CallContext;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.WrongType;
import gnu.text.Char;
import java.util.Locale;
import kawa.lib.misc;

/* compiled from: unicode.scm */
/* loaded from: classes.dex */
public class unicode extends ModuleBody {
    public static final ModuleMethod char$Mnalphabetic$Qu;
    public static final ModuleMethod char$Mnci$Eq$Qu;
    public static final ModuleMethod char$Mnci$Gr$Eq$Qu;
    public static final ModuleMethod char$Mnci$Gr$Qu;
    public static final ModuleMethod char$Mnci$Ls$Eq$Qu;
    public static final ModuleMethod char$Mnci$Ls$Qu;
    public static final ModuleMethod char$Mndowncase;
    public static final ModuleMethod char$Mnfoldcase;
    public static final ModuleMethod char$Mngeneral$Mncategory;
    public static final ModuleMethod char$Mnlower$Mncase$Qu;
    public static final ModuleMethod char$Mnnumeric$Qu;
    public static final ModuleMethod char$Mntitle$Mncase$Qu;
    public static final ModuleMethod char$Mntitlecase;
    public static final ModuleMethod char$Mnupcase;
    public static final ModuleMethod char$Mnupper$Mncase$Qu;
    public static final ModuleMethod char$Mnwhitespace$Qu;
    public static final ModuleMethod string$Mnci$Eq$Qu;
    public static final ModuleMethod string$Mnci$Gr$Eq$Qu;
    public static final ModuleMethod string$Mnci$Gr$Qu;
    public static final ModuleMethod string$Mnci$Ls$Eq$Qu;
    public static final ModuleMethod string$Mnci$Ls$Qu;
    public static final ModuleMethod string$Mndowncase;
    public static final ModuleMethod string$Mnfoldcase;
    public static final ModuleMethod string$Mnnormalize$Mnnfc;
    public static final ModuleMethod string$Mnnormalize$Mnnfd;
    public static final ModuleMethod string$Mnnormalize$Mnnfkc;
    public static final ModuleMethod string$Mnnormalize$Mnnfkd;
    public static final ModuleMethod string$Mntitlecase;
    public static final ModuleMethod string$Mnupcase;
    static final SimpleSymbol Lit28 = (SimpleSymbol) new SimpleSymbol("string-normalize-nfkc").readResolve();
    static final SimpleSymbol Lit27 = (SimpleSymbol) new SimpleSymbol("string-normalize-nfc").readResolve();
    static final SimpleSymbol Lit26 = (SimpleSymbol) new SimpleSymbol("string-normalize-nfkd").readResolve();
    static final SimpleSymbol Lit25 = (SimpleSymbol) new SimpleSymbol("string-normalize-nfd").readResolve();
    static final SimpleSymbol Lit24 = (SimpleSymbol) new SimpleSymbol("string-ci>=?").readResolve();
    static final SimpleSymbol Lit23 = (SimpleSymbol) new SimpleSymbol("string-ci<=?").readResolve();
    static final SimpleSymbol Lit22 = (SimpleSymbol) new SimpleSymbol("string-ci>?").readResolve();
    static final SimpleSymbol Lit21 = (SimpleSymbol) new SimpleSymbol("string-ci<?").readResolve();
    static final SimpleSymbol Lit20 = (SimpleSymbol) new SimpleSymbol("string-ci=?").readResolve();
    static final SimpleSymbol Lit19 = (SimpleSymbol) new SimpleSymbol("string-foldcase").readResolve();
    static final SimpleSymbol Lit18 = (SimpleSymbol) new SimpleSymbol("string-titlecase").readResolve();
    static final SimpleSymbol Lit17 = (SimpleSymbol) new SimpleSymbol("string-downcase").readResolve();
    static final SimpleSymbol Lit16 = (SimpleSymbol) new SimpleSymbol("string-upcase").readResolve();
    static final SimpleSymbol Lit15 = (SimpleSymbol) new SimpleSymbol("char-general-category").readResolve();
    static final SimpleSymbol Lit14 = (SimpleSymbol) new SimpleSymbol("char-ci>=?").readResolve();
    static final SimpleSymbol Lit13 = (SimpleSymbol) new SimpleSymbol("char-ci<=?").readResolve();
    static final SimpleSymbol Lit12 = (SimpleSymbol) new SimpleSymbol("char-ci>?").readResolve();
    static final SimpleSymbol Lit11 = (SimpleSymbol) new SimpleSymbol("char-ci<?").readResolve();
    static final SimpleSymbol Lit10 = (SimpleSymbol) new SimpleSymbol("char-ci=?").readResolve();
    static final SimpleSymbol Lit9 = (SimpleSymbol) new SimpleSymbol("char-foldcase").readResolve();
    static final SimpleSymbol Lit8 = (SimpleSymbol) new SimpleSymbol("char-title-case?").readResolve();
    static final SimpleSymbol Lit7 = (SimpleSymbol) new SimpleSymbol("char-lower-case?").readResolve();
    static final SimpleSymbol Lit6 = (SimpleSymbol) new SimpleSymbol("char-upper-case?").readResolve();
    static final SimpleSymbol Lit5 = (SimpleSymbol) new SimpleSymbol("char-whitespace?").readResolve();
    static final SimpleSymbol Lit4 = (SimpleSymbol) new SimpleSymbol("char-numeric?").readResolve();
    static final SimpleSymbol Lit3 = (SimpleSymbol) new SimpleSymbol("char-alphabetic?").readResolve();
    static final SimpleSymbol Lit2 = (SimpleSymbol) new SimpleSymbol("char-titlecase").readResolve();
    static final SimpleSymbol Lit1 = (SimpleSymbol) new SimpleSymbol("char-downcase").readResolve();
    static final SimpleSymbol Lit0 = (SimpleSymbol) new SimpleSymbol("char-upcase").readResolve();
    public static final unicode $instance = new unicode();

    static {
        unicode unicodeVar = $instance;
        char$Mnupcase = new ModuleMethod(unicodeVar, 1, Lit0, 4097);
        char$Mndowncase = new ModuleMethod(unicodeVar, 2, Lit1, 4097);
        char$Mntitlecase = new ModuleMethod(unicodeVar, 3, Lit2, 4097);
        char$Mnalphabetic$Qu = new ModuleMethod(unicodeVar, 4, Lit3, 4097);
        char$Mnnumeric$Qu = new ModuleMethod(unicodeVar, 5, Lit4, 4097);
        char$Mnwhitespace$Qu = new ModuleMethod(unicodeVar, 6, Lit5, 4097);
        char$Mnupper$Mncase$Qu = new ModuleMethod(unicodeVar, 7, Lit6, 4097);
        char$Mnlower$Mncase$Qu = new ModuleMethod(unicodeVar, 8, Lit7, 4097);
        char$Mntitle$Mncase$Qu = new ModuleMethod(unicodeVar, 9, Lit8, 4097);
        char$Mnfoldcase = new ModuleMethod(unicodeVar, 10, Lit9, 4097);
        char$Mnci$Eq$Qu = new ModuleMethod(unicodeVar, 11, Lit10, 8194);
        char$Mnci$Ls$Qu = new ModuleMethod(unicodeVar, 12, Lit11, 8194);
        char$Mnci$Gr$Qu = new ModuleMethod(unicodeVar, 13, Lit12, 8194);
        char$Mnci$Ls$Eq$Qu = new ModuleMethod(unicodeVar, 14, Lit13, 8194);
        char$Mnci$Gr$Eq$Qu = new ModuleMethod(unicodeVar, 15, Lit14, 8194);
        char$Mngeneral$Mncategory = new ModuleMethod(unicodeVar, 16, Lit15, 4097);
        string$Mnupcase = new ModuleMethod(unicodeVar, 17, Lit16, 4097);
        string$Mndowncase = new ModuleMethod(unicodeVar, 18, Lit17, 4097);
        string$Mntitlecase = new ModuleMethod(unicodeVar, 19, Lit18, 4097);
        string$Mnfoldcase = new ModuleMethod(unicodeVar, 20, Lit19, 4097);
        string$Mnci$Eq$Qu = new ModuleMethod(unicodeVar, 21, Lit20, 8194);
        string$Mnci$Ls$Qu = new ModuleMethod(unicodeVar, 22, Lit21, 8194);
        string$Mnci$Gr$Qu = new ModuleMethod(unicodeVar, 23, Lit22, 8194);
        string$Mnci$Ls$Eq$Qu = new ModuleMethod(unicodeVar, 24, Lit23, 8194);
        string$Mnci$Gr$Eq$Qu = new ModuleMethod(unicodeVar, 25, Lit24, 8194);
        string$Mnnormalize$Mnnfd = new ModuleMethod(unicodeVar, 26, Lit25, 4097);
        string$Mnnormalize$Mnnfkd = new ModuleMethod(unicodeVar, 27, Lit26, 4097);
        string$Mnnormalize$Mnnfc = new ModuleMethod(unicodeVar, 28, Lit27, 4097);
        string$Mnnormalize$Mnnfkc = new ModuleMethod(unicodeVar, 29, Lit28, 4097);
        $instance.run();
    }

    public unicode() {
        ModuleInfo.register(this);
    }

    @Override // gnu.expr.ModuleBody
    public final void run(CallContext $ctx) {
        Consumer consumer = $ctx.consumer;
    }

    public static Char charUpcase(Char ch) {
        return Char.make(Character.toUpperCase(ch.intValue()));
    }

    @Override // gnu.expr.ModuleBody
    public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
        switch (moduleMethod.selector) {
            case 1:
                if (obj instanceof Char) {
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                }
                return -786431;
            case 2:
                if (obj instanceof Char) {
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                }
                return -786431;
            case 3:
                if (obj instanceof Char) {
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                }
                return -786431;
            case 4:
                if (obj instanceof Char) {
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                }
                return -786431;
            case 5:
                if (obj instanceof Char) {
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                }
                return -786431;
            case 6:
                if (obj instanceof Char) {
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                }
                return -786431;
            case 7:
                if (obj instanceof Char) {
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                }
                return -786431;
            case 8:
                if (obj instanceof Char) {
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                }
                return -786431;
            case 9:
                if (obj instanceof Char) {
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                }
                return -786431;
            case 10:
                if (obj instanceof Char) {
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                }
                return -786431;
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            default:
                return super.match1(moduleMethod, obj, callContext);
            case 16:
                if (obj instanceof Char) {
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                }
                return -786431;
            case 17:
                if (obj instanceof CharSequence) {
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                }
                return -786431;
            case 18:
                if (obj instanceof CharSequence) {
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                }
                return -786431;
            case 19:
                if (obj instanceof CharSequence) {
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                }
                return -786431;
            case 20:
                if (obj instanceof CharSequence) {
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                }
                return -786431;
            case 26:
                if (obj instanceof CharSequence) {
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                }
                return -786431;
            case 27:
                if (obj instanceof CharSequence) {
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                }
                return -786431;
            case 28:
                if (obj instanceof CharSequence) {
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                }
                return -786431;
            case 29:
                if (obj instanceof CharSequence) {
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                }
                return -786431;
        }
    }

    public static Char charDowncase(Char ch) {
        return Char.make(Character.toLowerCase(ch.intValue()));
    }

    public static Char charTitlecase(Char ch) {
        return Char.make(Character.toTitleCase(ch.intValue()));
    }

    public static boolean isCharAlphabetic(Char ch) {
        return Character.isLetter(ch.intValue());
    }

    public static boolean isCharNumeric(Char ch) {
        return Character.isDigit(ch.intValue());
    }

    public static boolean isCharWhitespace(Char ch) {
        return UnicodeUtils.isWhitespace(ch.intValue());
    }

    public static boolean isCharUpperCase(Char ch) {
        return Character.isUpperCase(ch.intValue());
    }

    public static boolean isCharLowerCase(Char ch) {
        return Character.isLowerCase(ch.intValue());
    }

    public static boolean isCharTitleCase(Char ch) {
        return Character.isTitleCase(ch.intValue());
    }

    public static Char charFoldcase(Char ch) {
        int val = ch.intValue();
        boolean x = val == 304;
        if (x) {
            if (x) {
                return ch;
            }
        } else if (val == 305) {
            return ch;
        }
        return Char.make(Character.toLowerCase(Character.toUpperCase(val)));
    }

    public static boolean isCharCi$Eq(Char c1, Char c2) {
        return Character.toUpperCase(c1.intValue()) == Character.toUpperCase(c2.intValue());
    }

    @Override // gnu.expr.ModuleBody
    public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
        switch (moduleMethod.selector) {
            case 11:
                if (obj instanceof Char) {
                    callContext.value1 = obj;
                    if (obj2 instanceof Char) {
                        callContext.value2 = obj2;
                        callContext.proc = moduleMethod;
                        callContext.pc = 2;
                        return 0;
                    }
                    return -786430;
                }
                return -786431;
            case 12:
                if (obj instanceof Char) {
                    callContext.value1 = obj;
                    if (obj2 instanceof Char) {
                        callContext.value2 = obj2;
                        callContext.proc = moduleMethod;
                        callContext.pc = 2;
                        return 0;
                    }
                    return -786430;
                }
                return -786431;
            case 13:
                if (obj instanceof Char) {
                    callContext.value1 = obj;
                    if (obj2 instanceof Char) {
                        callContext.value2 = obj2;
                        callContext.proc = moduleMethod;
                        callContext.pc = 2;
                        return 0;
                    }
                    return -786430;
                }
                return -786431;
            case 14:
                if (obj instanceof Char) {
                    callContext.value1 = obj;
                    if (obj2 instanceof Char) {
                        callContext.value2 = obj2;
                        callContext.proc = moduleMethod;
                        callContext.pc = 2;
                        return 0;
                    }
                    return -786430;
                }
                return -786431;
            case 15:
                if (obj instanceof Char) {
                    callContext.value1 = obj;
                    if (obj2 instanceof Char) {
                        callContext.value2 = obj2;
                        callContext.proc = moduleMethod;
                        callContext.pc = 2;
                        return 0;
                    }
                    return -786430;
                }
                return -786431;
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            default:
                return super.match2(moduleMethod, obj, obj2, callContext);
            case 21:
                if (obj instanceof CharSequence) {
                    callContext.value1 = obj;
                    if (obj2 instanceof CharSequence) {
                        callContext.value2 = obj2;
                        callContext.proc = moduleMethod;
                        callContext.pc = 2;
                        return 0;
                    }
                    return -786430;
                }
                return -786431;
            case 22:
                if (obj instanceof CharSequence) {
                    callContext.value1 = obj;
                    if (obj2 instanceof CharSequence) {
                        callContext.value2 = obj2;
                        callContext.proc = moduleMethod;
                        callContext.pc = 2;
                        return 0;
                    }
                    return -786430;
                }
                return -786431;
            case 23:
                if (obj instanceof CharSequence) {
                    callContext.value1 = obj;
                    if (obj2 instanceof CharSequence) {
                        callContext.value2 = obj2;
                        callContext.proc = moduleMethod;
                        callContext.pc = 2;
                        return 0;
                    }
                    return -786430;
                }
                return -786431;
            case 24:
                if (obj instanceof CharSequence) {
                    callContext.value1 = obj;
                    if (obj2 instanceof CharSequence) {
                        callContext.value2 = obj2;
                        callContext.proc = moduleMethod;
                        callContext.pc = 2;
                        return 0;
                    }
                    return -786430;
                }
                return -786431;
            case 25:
                if (obj instanceof CharSequence) {
                    callContext.value1 = obj;
                    if (obj2 instanceof CharSequence) {
                        callContext.value2 = obj2;
                        callContext.proc = moduleMethod;
                        callContext.pc = 2;
                        return 0;
                    }
                    return -786430;
                }
                return -786431;
        }
    }

    public static boolean isCharCi$Ls(Char c1, Char c2) {
        return Character.toUpperCase(c1.intValue()) < Character.toUpperCase(c2.intValue());
    }

    public static boolean isCharCi$Gr(Char c1, Char c2) {
        return Character.toUpperCase(c1.intValue()) > Character.toUpperCase(c2.intValue());
    }

    public static boolean isCharCi$Ls$Eq(Char c1, Char c2) {
        return Character.toUpperCase(c1.intValue()) <= Character.toUpperCase(c2.intValue());
    }

    public static boolean isCharCi$Gr$Eq(Char c1, Char c2) {
        return Character.toUpperCase(c1.intValue()) >= Character.toUpperCase(c2.intValue());
    }

    public static Symbol charGeneralCategory(Char ch) {
        return UnicodeUtils.generalCategory(ch.intValue());
    }

    public static CharSequence stringUpcase(CharSequence str) {
        return new FString((CharSequence) str.toString().toUpperCase(Locale.ENGLISH));
    }

    public static CharSequence stringDowncase(CharSequence str) {
        return new FString((CharSequence) str.toString().toLowerCase(Locale.ENGLISH));
    }

    public static CharSequence stringTitlecase(CharSequence str) {
        return new FString((CharSequence) UnicodeUtils.capitalize(str == null ? null : str.toString()));
    }

    public static CharSequence stringFoldcase(CharSequence str) {
        return new FString((CharSequence) UnicodeUtils.foldCase(str));
    }

    public static boolean isStringCi$Eq(CharSequence str1, CharSequence str2) {
        return UnicodeUtils.foldCase(str1).equals(UnicodeUtils.foldCase(str2));
    }

    public static boolean isStringCi$Ls(CharSequence str1, CharSequence str2) {
        return UnicodeUtils.foldCase(str1).compareTo(UnicodeUtils.foldCase(str2)) < 0;
    }

    public static boolean isStringCi$Gr(CharSequence str1, CharSequence str2) {
        return UnicodeUtils.foldCase(str1).compareTo(UnicodeUtils.foldCase(str2)) > 0;
    }

    public static boolean isStringCi$Ls$Eq(CharSequence str1, CharSequence str2) {
        return UnicodeUtils.foldCase(str1).compareTo(UnicodeUtils.foldCase(str2)) <= 0;
    }

    public static boolean isStringCi$Gr$Eq(CharSequence str1, CharSequence str2) {
        return UnicodeUtils.foldCase(str1).compareTo(UnicodeUtils.foldCase(str2)) >= 0;
    }

    @Override // gnu.expr.ModuleBody
    public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
        switch (moduleMethod.selector) {
            case 11:
                try {
                    try {
                        return isCharCi$Eq((Char) obj, (Char) obj2) ? Boolean.TRUE : Boolean.FALSE;
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "char-ci=?", 2, obj2);
                    }
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "char-ci=?", 1, obj);
                }
            case 12:
                try {
                    try {
                        return isCharCi$Ls((Char) obj, (Char) obj2) ? Boolean.TRUE : Boolean.FALSE;
                    } catch (ClassCastException e3) {
                        throw new WrongType(e3, "char-ci<?", 2, obj2);
                    }
                } catch (ClassCastException e4) {
                    throw new WrongType(e4, "char-ci<?", 1, obj);
                }
            case 13:
                try {
                    try {
                        return isCharCi$Gr((Char) obj, (Char) obj2) ? Boolean.TRUE : Boolean.FALSE;
                    } catch (ClassCastException e5) {
                        throw new WrongType(e5, "char-ci>?", 2, obj2);
                    }
                } catch (ClassCastException e6) {
                    throw new WrongType(e6, "char-ci>?", 1, obj);
                }
            case 14:
                try {
                    try {
                        return isCharCi$Ls$Eq((Char) obj, (Char) obj2) ? Boolean.TRUE : Boolean.FALSE;
                    } catch (ClassCastException e7) {
                        throw new WrongType(e7, "char-ci<=?", 2, obj2);
                    }
                } catch (ClassCastException e8) {
                    throw new WrongType(e8, "char-ci<=?", 1, obj);
                }
            case 15:
                try {
                    try {
                        return isCharCi$Gr$Eq((Char) obj, (Char) obj2) ? Boolean.TRUE : Boolean.FALSE;
                    } catch (ClassCastException e9) {
                        throw new WrongType(e9, "char-ci>=?", 2, obj2);
                    }
                } catch (ClassCastException e10) {
                    throw new WrongType(e10, "char-ci>=?", 1, obj);
                }
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            default:
                return super.apply2(moduleMethod, obj, obj2);
            case 21:
                try {
                    try {
                        return isStringCi$Eq((CharSequence) obj, (CharSequence) obj2) ? Boolean.TRUE : Boolean.FALSE;
                    } catch (ClassCastException e11) {
                        throw new WrongType(e11, "string-ci=?", 2, obj2);
                    }
                } catch (ClassCastException e12) {
                    throw new WrongType(e12, "string-ci=?", 1, obj);
                }
            case 22:
                try {
                    try {
                        return isStringCi$Ls((CharSequence) obj, (CharSequence) obj2) ? Boolean.TRUE : Boolean.FALSE;
                    } catch (ClassCastException e13) {
                        throw new WrongType(e13, "string-ci<?", 2, obj2);
                    }
                } catch (ClassCastException e14) {
                    throw new WrongType(e14, "string-ci<?", 1, obj);
                }
            case 23:
                try {
                    try {
                        return isStringCi$Gr((CharSequence) obj, (CharSequence) obj2) ? Boolean.TRUE : Boolean.FALSE;
                    } catch (ClassCastException e15) {
                        throw new WrongType(e15, "string-ci>?", 2, obj2);
                    }
                } catch (ClassCastException e16) {
                    throw new WrongType(e16, "string-ci>?", 1, obj);
                }
            case 24:
                try {
                    try {
                        return isStringCi$Ls$Eq((CharSequence) obj, (CharSequence) obj2) ? Boolean.TRUE : Boolean.FALSE;
                    } catch (ClassCastException e17) {
                        throw new WrongType(e17, "string-ci<=?", 2, obj2);
                    }
                } catch (ClassCastException e18) {
                    throw new WrongType(e18, "string-ci<=?", 1, obj);
                }
            case 25:
                try {
                    try {
                        return isStringCi$Gr$Eq((CharSequence) obj, (CharSequence) obj2) ? Boolean.TRUE : Boolean.FALSE;
                    } catch (ClassCastException e19) {
                        throw new WrongType(e19, "string-ci>=?", 2, obj2);
                    }
                } catch (ClassCastException e20) {
                    throw new WrongType(e20, "string-ci>=?", 1, obj);
                }
        }
    }

    public static CharSequence stringNormalizeNfd(CharSequence str) {
        return (CharSequence) misc.error$V("unicode string normalization not available", new Object[0]);
    }

    public static CharSequence stringNormalizeNfkd(CharSequence str) {
        return (CharSequence) misc.error$V("unicode string normalization not available", new Object[0]);
    }

    public static CharSequence stringNormalizeNfc(CharSequence str) {
        return (CharSequence) misc.error$V("unicode string normalization not available", new Object[0]);
    }

    public static CharSequence stringNormalizeNfkc(CharSequence str) {
        return (CharSequence) misc.error$V("unicode string normalization not available", new Object[0]);
    }

    @Override // gnu.expr.ModuleBody
    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        switch (moduleMethod.selector) {
            case 1:
                try {
                    return charUpcase((Char) obj);
                } catch (ClassCastException e) {
                    throw new WrongType(e, "char-upcase", 1, obj);
                }
            case 2:
                try {
                    return charDowncase((Char) obj);
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "char-downcase", 1, obj);
                }
            case 3:
                try {
                    return charTitlecase((Char) obj);
                } catch (ClassCastException e3) {
                    throw new WrongType(e3, "char-titlecase", 1, obj);
                }
            case 4:
                try {
                    return isCharAlphabetic((Char) obj) ? Boolean.TRUE : Boolean.FALSE;
                } catch (ClassCastException e4) {
                    throw new WrongType(e4, "char-alphabetic?", 1, obj);
                }
            case 5:
                try {
                    return isCharNumeric((Char) obj) ? Boolean.TRUE : Boolean.FALSE;
                } catch (ClassCastException e5) {
                    throw new WrongType(e5, "char-numeric?", 1, obj);
                }
            case 6:
                try {
                    return isCharWhitespace((Char) obj) ? Boolean.TRUE : Boolean.FALSE;
                } catch (ClassCastException e6) {
                    throw new WrongType(e6, "char-whitespace?", 1, obj);
                }
            case 7:
                try {
                    return isCharUpperCase((Char) obj) ? Boolean.TRUE : Boolean.FALSE;
                } catch (ClassCastException e7) {
                    throw new WrongType(e7, "char-upper-case?", 1, obj);
                }
            case 8:
                try {
                    return isCharLowerCase((Char) obj) ? Boolean.TRUE : Boolean.FALSE;
                } catch (ClassCastException e8) {
                    throw new WrongType(e8, "char-lower-case?", 1, obj);
                }
            case 9:
                try {
                    return isCharTitleCase((Char) obj) ? Boolean.TRUE : Boolean.FALSE;
                } catch (ClassCastException e9) {
                    throw new WrongType(e9, "char-title-case?", 1, obj);
                }
            case 10:
                try {
                    return charFoldcase((Char) obj);
                } catch (ClassCastException e10) {
                    throw new WrongType(e10, "char-foldcase", 1, obj);
                }
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            default:
                return super.apply1(moduleMethod, obj);
            case 16:
                try {
                    return charGeneralCategory((Char) obj);
                } catch (ClassCastException e11) {
                    throw new WrongType(e11, "char-general-category", 1, obj);
                }
            case 17:
                try {
                    return stringUpcase((CharSequence) obj);
                } catch (ClassCastException e12) {
                    throw new WrongType(e12, "string-upcase", 1, obj);
                }
            case 18:
                try {
                    return stringDowncase((CharSequence) obj);
                } catch (ClassCastException e13) {
                    throw new WrongType(e13, "string-downcase", 1, obj);
                }
            case 19:
                try {
                    return stringTitlecase((CharSequence) obj);
                } catch (ClassCastException e14) {
                    throw new WrongType(e14, "string-titlecase", 1, obj);
                }
            case 20:
                try {
                    return stringFoldcase((CharSequence) obj);
                } catch (ClassCastException e15) {
                    throw new WrongType(e15, "string-foldcase", 1, obj);
                }
            case 26:
                try {
                    return stringNormalizeNfd((CharSequence) obj);
                } catch (ClassCastException e16) {
                    throw new WrongType(e16, "string-normalize-nfd", 1, obj);
                }
            case 27:
                try {
                    return stringNormalizeNfkd((CharSequence) obj);
                } catch (ClassCastException e17) {
                    throw new WrongType(e17, "string-normalize-nfkd", 1, obj);
                }
            case 28:
                try {
                    return stringNormalizeNfc((CharSequence) obj);
                } catch (ClassCastException e18) {
                    throw new WrongType(e18, "string-normalize-nfc", 1, obj);
                }
            case 29:
                try {
                    return stringNormalizeNfkc((CharSequence) obj);
                } catch (ClassCastException e19) {
                    throw new WrongType(e19, "string-normalize-nfkc", 1, obj);
                }
        }
    }
}
