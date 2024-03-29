package gnu.bytecode;

import com.google.appinventor.components.common.PropertyTypeConstants;
import gnu.kawa.util.AbstractWeakHashTable;
import gnu.text.PrettyWriter;
import java.io.PrintWriter;
import java.util.HashMap;

/* loaded from: classes.dex */
public abstract class Type implements java.lang.reflect.Type {
    public static final Method booleanValue_method;
    public static final ClassType boolean_ctype;
    public static final Method clone_method;
    public static final Method doubleValue_method;
    public static final ObjectType errorType;
    public static final Method floatValue_method;
    public static final Method intValue_method;
    public static final ClassType java_lang_Class_type;
    public static final ClassType javalangBooleanType;
    public static final ClassType javalangClassType;
    public static final ClassType javalangNumberType;
    public static final ClassType javalangObjectType;
    public static ClassType javalangStringType;
    public static final ClassType javalangThrowableType;
    public static final Method longValue_method;
    static ClassToTypeMap mapClassToType;
    public static final PrimType neverReturnsType;
    public static final ObjectType nullType;
    public static final ClassType number_type;
    public static final ClassType objectType;
    public static final ClassType pointer_type;
    public static final ClassType string_type;
    public static final ClassType throwable_type;
    public static final ClassType toStringType;
    public static final Method toString_method;
    public static final ClassType tostring_type;
    public static final Type[] typeArray0;
    ArrayType array_type;
    protected Class reflectClass;
    String signature;
    int size;
    String this_name;
    public static final PrimType byteType = new PrimType("byte", "B", 1, Byte.TYPE);
    public static final PrimType shortType = new PrimType("short", "S", 2, Short.TYPE);
    public static final PrimType intType = new PrimType("int", "I", 4, Integer.TYPE);
    public static final PrimType longType = new PrimType("long", "J", 8, Long.TYPE);
    public static final PrimType floatType = new PrimType(PropertyTypeConstants.PROPERTY_TYPE_FLOAT, "F", 4, Float.TYPE);
    public static final PrimType doubleType = new PrimType("double", "D", 8, Double.TYPE);
    public static final PrimType booleanType = new PrimType(PropertyTypeConstants.PROPERTY_TYPE_BOOLEAN, "Z", 1, Boolean.TYPE);
    public static final PrimType charType = new PrimType("char", "C", 2, Character.TYPE);
    public static final PrimType voidType = new PrimType("void", "V", 0, Void.TYPE);
    public static final PrimType byte_type = byteType;
    public static final PrimType short_type = shortType;
    public static final PrimType int_type = intType;
    public static final PrimType long_type = longType;
    public static final PrimType float_type = floatType;
    public static final PrimType double_type = doubleType;
    public static final PrimType boolean_type = booleanType;
    public static final PrimType char_type = charType;
    public static final PrimType void_type = voidType;
    static HashMap<String, Type> mapNameToType = new HashMap<>();

    public abstract Object coerceFromObject(Object obj);

    public abstract int compare(Type type);

    /* JADX INFO: Access modifiers changed from: protected */
    public Type() {
    }

    public Type getImplementationType() {
        return this;
    }

    public Type getRealType() {
        return this;
    }

    public boolean isExisting() {
        return true;
    }

    public static Type lookupType(String name) {
        Type type;
        HashMap<String, Type> map = mapNameToType;
        synchronized (map) {
            type = map.get(name);
        }
        return type;
    }

    public static Type getType(String name) {
        Type type;
        HashMap<String, Type> map = mapNameToType;
        synchronized (map) {
            type = map.get(name);
            if (type == null) {
                if (name.endsWith("[]")) {
                    type = ArrayType.make(name);
                } else {
                    ClassType cl = new ClassType(name);
                    cl.flags |= 16;
                    type = cl;
                }
                map.put(name, type);
            }
        }
        return type;
    }

    public static synchronized void registerTypeForClass(Class clas, Type type) {
        synchronized (Type.class) {
            ClassToTypeMap map = mapClassToType;
            if (map == null) {
                map = new ClassToTypeMap();
                mapClassToType = map;
            }
            type.reflectClass = clas;
            map.put(clas, type);
        }
    }

    public static synchronized Type make(Class reflectClass) {
        Type type;
        Type t;
        synchronized (Type.class) {
            if (mapClassToType == null || (t = mapClassToType.get(reflectClass)) == null) {
                if (reflectClass.isArray()) {
                    type = ArrayType.make(make(reflectClass.getComponentType()));
                } else if (reflectClass.isPrimitive()) {
                    throw new Error("internal error - primitive type not found");
                } else {
                    String name = reflectClass.getName();
                    HashMap<String, Type> map = mapNameToType;
                    synchronized (map) {
                        type = map.get(name);
                        if (type == null || (type.reflectClass != reflectClass && type.reflectClass != null)) {
                            ClassType cl = new ClassType(name);
                            cl.flags |= 16;
                            type = cl;
                            mapNameToType.put(name, type);
                        }
                    }
                }
                registerTypeForClass(reflectClass, type);
                t = type;
            }
        }
        return t;
    }

    public String getSignature() {
        return this.signature;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setSignature(String sig) {
        this.signature = sig;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Type(String nam, String sig) {
        this.this_name = nam;
        this.signature = sig;
    }

    public Type(Type type) {
        this.this_name = type.this_name;
        this.signature = type.signature;
        this.size = type.size;
        this.reflectClass = type.reflectClass;
    }

    public Type promote() {
        return this.size < 4 ? intType : this;
    }

    public final int getSize() {
        return this.size;
    }

    public int getSizeInWords() {
        return this.size > 4 ? 2 : 1;
    }

    public final boolean isVoid() {
        return this.size == 0;
    }

    public static PrimType signatureToPrimitive(char sig) {
        switch (sig) {
            case 'B':
                return byteType;
            case 'C':
                return charType;
            case 'D':
                return doubleType;
            case PrettyWriter.NEWLINE_FILL /* 70 */:
                return floatType;
            case 'I':
                return intType;
            case 'J':
                return longType;
            case PrettyWriter.NEWLINE_SPACE /* 83 */:
                return shortType;
            case 'V':
                return voidType;
            case 'Z':
                return booleanType;
            default:
                return null;
        }
    }

    public static Type signatureToType(String sig, int off, int len) {
        Type type;
        if (len == 0) {
            return null;
        }
        char c = sig.charAt(off);
        if (len != 1 || (type = signatureToPrimitive(c)) == null) {
            if (c == '[') {
                Type type2 = signatureToType(sig, off + 1, len - 1);
                if (type2 != null) {
                    return ArrayType.make(type2);
                }
                return null;
            } else if (c == 'L' && len > 2 && sig.indexOf(59, off) == (len - 1) + off) {
                return ClassType.make(sig.substring(off + 1, (len - 1) + off).replace('/', '.'));
            } else {
                return null;
            }
        }
        return type;
    }

    public static Type signatureToType(String sig) {
        return signatureToType(sig, 0, sig.length());
    }

    public static void printSignature(String sig, int off, int len, PrintWriter out) {
        if (len != 0) {
            char c = sig.charAt(off);
            if (len == 1) {
                Type type = signatureToPrimitive(c);
                if (type != null) {
                    out.print(type.getName());
                }
            } else if (c == '[') {
                printSignature(sig, off + 1, len - 1, out);
                out.print("[]");
            } else if (c == 'L' && len > 2 && sig.indexOf(59, off) == (len - 1) + off) {
                out.print(sig.substring(off + 1, (len - 1) + off).replace('/', '.'));
            } else {
                out.append((CharSequence) sig, off, len - off);
            }
        }
    }

    public static int signatureLength(String sig, int pos) {
        int end;
        int len = sig.length();
        if (len <= pos) {
            return -1;
        }
        char c = sig.charAt(pos);
        int arrays = 0;
        while (c == '[') {
            arrays++;
            pos++;
            c = sig.charAt(pos);
        }
        if (signatureToPrimitive(c) != null) {
            return arrays + 1;
        }
        if (c != 'L' || (end = sig.indexOf(59, pos)) <= 0) {
            return -1;
        }
        return ((arrays + end) + 1) - pos;
    }

    public static int signatureLength(String sig) {
        return signatureLength(sig, 0);
    }

    public static String signatureToName(String sig) {
        Type type;
        int len = sig.length();
        if (len == 0) {
            return null;
        }
        char c = sig.charAt(0);
        if (len == 1 && (type = signatureToPrimitive(c)) != null) {
            return type.getName();
        }
        if (c == '[') {
            int arrays = 1;
            if (1 < len && sig.charAt(1) == '[') {
                arrays = 1 + 1;
            }
            String sig2 = signatureToName(sig.substring(arrays));
            if (sig2 == null) {
                return null;
            }
            StringBuffer buf = new StringBuffer(50);
            buf.append(sig2);
            while (true) {
                arrays--;
                if (arrays >= 0) {
                    buf.append("[]");
                } else {
                    return buf.toString();
                }
            }
        } else if (c == 'L' && len > 2 && sig.indexOf(59) == len - 1) {
            return sig.substring(1, len - 1).replace('/', '.');
        } else {
            return null;
        }
    }

    public final String getName() {
        return this.this_name;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setName(String name) {
        this.this_name = name;
    }

    public static boolean isValidJavaTypeName(String name) {
        boolean in_name = false;
        int len = name.length();
        while (len > 2 && name.charAt(len - 1) == ']' && name.charAt(len - 2) == '[') {
            len -= 2;
        }
        int i = 0;
        while (i < len) {
            char ch = name.charAt(i);
            if (ch == '.') {
                if (!in_name) {
                    return false;
                }
                in_name = false;
            } else {
                if (in_name) {
                    if (!Character.isJavaIdentifierPart(ch)) {
                        return false;
                    }
                } else if (!Character.isJavaIdentifierStart(ch)) {
                    return false;
                }
                in_name = true;
            }
            i++;
        }
        return i == len;
    }

    public boolean isInstance(Object obj) {
        return getReflectClass().isInstance(obj);
    }

    public final boolean isSubtype(Type other) {
        int comp = compare(other);
        return comp == -1 || comp == 0;
    }

    public static Type lowestCommonSuperType(Type t1, Type t2) {
        if (t1 == neverReturnsType) {
            return t2;
        }
        if (t2 != neverReturnsType) {
            if (t1 == null || t2 == null) {
                return null;
            }
            if ((t1 instanceof PrimType) && (t2 instanceof PrimType)) {
                if (t1 != t2) {
                    Type t12 = ((PrimType) t1).promotedType();
                    if (t12 != ((PrimType) t2).promotedType()) {
                        return null;
                    }
                    return t12;
                }
                return t1;
            } else if (t1.isSubtype(t2)) {
                return t2;
            } else {
                if (!t2.isSubtype(t1)) {
                    if (!(t1 instanceof ClassType) || !(t2 instanceof ClassType)) {
                        return objectType;
                    }
                    ClassType c1 = (ClassType) t1;
                    ClassType c2 = (ClassType) t2;
                    if (c1.isInterface() || c2.isInterface()) {
                        return objectType;
                    }
                    return lowestCommonSuperType(c1.getSuperclass(), c2.getSuperclass());
                }
                return t1;
            }
        }
        return t1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static int swappedCompareResult(int code) {
        if (code == 1) {
            return -1;
        }
        if (code == -1) {
            return 1;
        }
        return code;
    }

    public static boolean isMoreSpecific(Type[] t1, Type[] t2) {
        if (t1.length != t2.length) {
            return false;
        }
        int i = t1.length;
        do {
            i--;
            if (i < 0) {
                return true;
            }
        } while (t1[i].isSubtype(t2[i]));
        return false;
    }

    public void emitIsInstance(CodeAttr code) {
        code.emitInstanceof(this);
    }

    public Object coerceToObject(Object obj) {
        return obj;
    }

    public void emitConvertFromPrimitive(Type stackType, CodeAttr code) {
        stackType.emitCoerceToObject(code);
    }

    public void emitCoerceToObject(CodeAttr code) {
    }

    public void emitCoerceFromObject(CodeAttr code) {
        throw new Error("unimplemented emitCoerceFromObject for " + this);
    }

    static {
        mapNameToType.put("byte", byteType);
        mapNameToType.put("short", shortType);
        mapNameToType.put("int", intType);
        mapNameToType.put("long", longType);
        mapNameToType.put(PropertyTypeConstants.PROPERTY_TYPE_FLOAT, floatType);
        mapNameToType.put("double", doubleType);
        mapNameToType.put(PropertyTypeConstants.PROPERTY_TYPE_BOOLEAN, booleanType);
        mapNameToType.put("char", charType);
        mapNameToType.put("void", voidType);
        neverReturnsType = new PrimType(voidType);
        neverReturnsType.this_name = "(never-returns)";
        nullType = new ObjectType("(type of null)");
        errorType = new ClassType("(error type)");
        javalangStringType = ClassType.make("java.lang.String");
        toStringType = new ClassType("java.lang.String");
        javalangObjectType = ClassType.make("java.lang.Object");
        objectType = javalangObjectType;
        javalangBooleanType = ClassType.make("java.lang.Boolean");
        javalangThrowableType = ClassType.make("java.lang.Throwable");
        typeArray0 = new Type[0];
        toString_method = objectType.getDeclaredMethod("toString", 0);
        javalangNumberType = ClassType.make("java.lang.Number");
        clone_method = Method.makeCloneMethod(objectType);
        intValue_method = javalangNumberType.addMethod("intValue", typeArray0, intType, 1);
        longValue_method = javalangNumberType.addMethod("longValue", typeArray0, longType, 1);
        floatValue_method = javalangNumberType.addMethod("floatValue", typeArray0, floatType, 1);
        doubleValue_method = javalangNumberType.addMethod("doubleValue", typeArray0, doubleType, 1);
        booleanValue_method = javalangBooleanType.addMethod("booleanValue", typeArray0, booleanType, 1);
        javalangClassType = ClassType.make("java.lang.Class");
        pointer_type = javalangObjectType;
        string_type = javalangStringType;
        tostring_type = toStringType;
        java_lang_Class_type = javalangClassType;
        boolean_ctype = javalangBooleanType;
        throwable_type = javalangThrowableType;
        number_type = javalangNumberType;
    }

    public Class getReflectClass() {
        return this.reflectClass;
    }

    public void setReflectClass(Class rclass) {
        this.reflectClass = rclass;
    }

    public String toString() {
        return "Type " + getName();
    }

    public int hashCode() {
        String name = toString();
        if (name == null) {
            return 0;
        }
        return name.hashCode();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class ClassToTypeMap extends AbstractWeakHashTable<Class, Type> {
        ClassToTypeMap() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // gnu.kawa.util.AbstractWeakHashTable
        public Class getKeyFromValue(Type type) {
            return type.reflectClass;
        }

        protected boolean matches(Class oldValue, Class newValue) {
            return oldValue == newValue;
        }
    }
}
