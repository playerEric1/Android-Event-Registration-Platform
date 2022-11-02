package kawa.lib.kawa;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.SetNamedPart;
import gnu.kawa.reflect.Invoke;
import gnu.kawa.reflect.StaticFieldLocation;
import gnu.kawa.util.GeneralHashTable;
import gnu.kawa.util.HashNode;
import gnu.lists.Consumer;
import gnu.lists.FVector;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.CallContext;
import gnu.mapping.Location;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import kawa.lib.lists;
import kawa.lib.misc;
import kawa.standard.thisRef;

/* compiled from: hashtable.scm */
/* loaded from: classes.dex */
public class hashtable extends ModuleBody {
    static final SimpleSymbol Lit1 = (SimpleSymbol) new SimpleSymbol("hashtable-check-mutable").readResolve();
    static final SimpleSymbol Lit0 = (SimpleSymbol) new SimpleSymbol("mutable").readResolve();
    public static final Class $Prvt$hashnode = HashNode.class;
    public static final hashtable $instance = new hashtable();
    public static final Location $Prvt$let$St = StaticFieldLocation.make("kawa.lib.std_syntax", "let$St");
    public static final Location $Prvt$do = StaticFieldLocation.make("kawa.lib.std_syntax", "do");
    public static final Class hashtable = HashTable.class;
    public static final ModuleMethod hashtable$Mncheck$Mnmutable = new ModuleMethod($instance, 1, Lit1, 4097);

    public hashtable() {
        ModuleInfo.register(this);
    }

    @Override // gnu.expr.ModuleBody
    public final void run(CallContext $ctx) {
        Consumer consumer = $ctx.consumer;
    }

    static {
        $instance.run();
    }

    /* compiled from: hashtable.scm */
    /* loaded from: classes.dex */
    public class HashTable extends GeneralHashTable {
        public Procedure equivalenceFunction;
        public Procedure hashFunction;
        public boolean mutable;

        private void $finit$() {
            this.mutable = true;
        }

        public HashTable(Procedure procedure, Procedure procedure2, int i) {
            super(i);
            $finit$();
            this.equivalenceFunction = procedure;
            this.hashFunction = procedure2;
        }

        public HashTable(Procedure procedure, Procedure procedure2) {
            $finit$();
            this.equivalenceFunction = procedure;
            this.hashFunction = procedure2;
        }

        public HashTable(HashTable hashTable, boolean z) {
            $finit$();
            Invoke.invokeSpecial.applyN(new Object[]{hashtable.hashtable, this, hashTable.equivalenceFunction.apply0(), hashTable.hashFunction.apply0(), Integer.valueOf(hashTable.size() + 100)});
            putAll(hashTable);
            SetNamedPart.setNamedPart.apply3(thisRef.thisSyntax, hashtable.Lit0, z ? Boolean.TRUE : Boolean.FALSE);
        }

        @Override // gnu.kawa.util.AbstractHashTable
        public int hash(Object key) {
            return ((Number) this.hashFunction.apply1(key)).intValue();
        }

        @Override // gnu.kawa.util.AbstractHashTable
        public boolean matches(Object value1, Object value2) {
            return this.equivalenceFunction.apply2(value1, value2) != Boolean.FALSE;
        }

        public void walk(Procedure proc) {
            Entry[] entryArr = this.table;
            try {
                HashNode[] table = (HashNode[]) entryArr;
                int length = table.length;
                for (int i = length - 1; i >= 0; i--) {
                    HashNode node = table[i];
                    while (node != null) {
                        proc.apply2(node.getKey(), node.getValue());
                        node = getEntryNext(node);
                    }
                }
            } catch (ClassCastException e) {
                throw new WrongType(e, "table", -2, entryArr);
            }
        }

        public Object fold(Procedure proc, Object acc) {
            Entry[] entryArr = this.table;
            try {
                HashNode[] table = (HashNode[]) entryArr;
                int length = table.length;
                for (int i = length - 1; i >= 0; i--) {
                    HashNode node = table[i];
                    while (node != null) {
                        acc = proc.apply3(node.getKey(), node.getValue(), acc);
                        node = getEntryNext(node);
                    }
                }
                return acc;
            } catch (ClassCastException e) {
                throw new WrongType(e, "table", -2, entryArr);
            }
        }

        public FVector keysVector() {
            FVector v = new FVector();
            Entry[] entryArr = this.table;
            try {
                HashNode[] table = (HashNode[]) entryArr;
                int length = table.length;
                for (int i = length - 1; i >= 0; i--) {
                    HashNode node = table[i];
                    while (node != null) {
                        v.add(node.getKey());
                        node = getEntryNext(node);
                    }
                }
                return v;
            } catch (ClassCastException e) {
                throw new WrongType(e, "table", -2, entryArr);
            }
        }

        public Pair entriesVectorPair() {
            FVector keys = new FVector();
            FVector vals = new FVector();
            Entry[] entryArr = this.table;
            try {
                HashNode[] table = (HashNode[]) entryArr;
                int length = table.length;
                for (int i = length - 1; i >= 0; i--) {
                    HashNode node = table[i];
                    while (node != null) {
                        keys.add(node.getKey());
                        vals.add(node.getValue());
                        node = getEntryNext(node);
                    }
                }
                return lists.cons(keys, vals);
            } catch (ClassCastException e) {
                throw new WrongType(e, "table", -2, entryArr);
            }
        }

        public Object toAlist() {
            LList result = LList.Empty;
            Entry[] entryArr = this.table;
            try {
                HashNode[] table = (HashNode[]) entryArr;
                int length = table.length;
                for (int i = length - 1; i >= 0; i--) {
                    HashNode node = table[i];
                    while (node != null) {
                        result = lists.cons(lists.cons(node.getKey(), node.getValue()), result);
                        node = getEntryNext(node);
                    }
                }
                return result;
            } catch (ClassCastException e) {
                throw new WrongType(e, "table", -2, entryArr);
            }
        }

        public LList toNodeList() {
            LList result = LList.Empty;
            Entry[] entryArr = this.table;
            try {
                HashNode[] table = (HashNode[]) entryArr;
                int length = table.length;
                Pair pair = result;
                for (int i = length - 1; i >= 0; i--) {
                    HashNode node = table[i];
                    pair = pair;
                    while (node != null) {
                        Pair result2 = lists.cons(node, pair);
                        node = getEntryNext(node);
                        pair = result2;
                    }
                }
                return pair;
            } catch (ClassCastException e) {
                throw new WrongType(e, "table", -2, entryArr);
            }
        }

        public HashNode[] toNodeArray() {
            int n = size();
            HashNode[] result = new HashNode[n];
            int i = 0;
            Entry[] entryArr = this.table;
            try {
                HashNode[] table = (HashNode[]) entryArr;
                int length = table.length;
                int i2 = length - 1;
                while (i2 >= 0) {
                    HashNode node = table[i2];
                    int i3 = i;
                    while (node != null) {
                        result[i3] = node;
                        int i4 = i3 + 1;
                        node = getEntryNext(node);
                        i3 = i4;
                    }
                    i2--;
                    i = i3;
                }
                return result;
            } catch (ClassCastException e) {
                throw new WrongType(e, "table", -2, entryArr);
            }
        }

        public void putAll(HashTable other) {
            Entry[] entryArr = other.table;
            try {
                HashNode[] table = (HashNode[]) entryArr;
                int length = table.length;
                for (int i = length - 1; i >= 0; i--) {
                    HashNode node = table[i];
                    while (node != null) {
                        put(node.getKey(), node.getValue());
                        node = other.getEntryNext(node);
                    }
                }
            } catch (ClassCastException e) {
                throw new WrongType(e, "table", -2, entryArr);
            }
        }

        @Override // java.util.AbstractMap
        public Object clone() {
            return new HashTable(this, true);
        }
    }

    public static void hashtableCheckMutable(HashTable ht) {
        if (ht.mutable) {
            return;
        }
        misc.error$V("cannot modify non-mutable hashtable", new Object[0]);
    }

    @Override // gnu.expr.ModuleBody
    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        if (moduleMethod.selector == 1) {
            try {
                hashtableCheckMutable((HashTable) obj);
                return Values.empty;
            } catch (ClassCastException e) {
                throw new WrongType(e, "hashtable-check-mutable", 1, obj);
            }
        }
        return super.apply1(moduleMethod, obj);
    }

    @Override // gnu.expr.ModuleBody
    public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
        if (moduleMethod.selector == 1) {
            if (obj instanceof HashTable) {
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            }
            return -786431;
        }
        return super.match1(moduleMethod, obj, callContext);
    }
}
