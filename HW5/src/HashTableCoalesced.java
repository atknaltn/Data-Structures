/**
 * HashTableCoalesced
 */
public class HashTableCoalesced<K,V> implements KWHashMap<K,V>{

    private Entry<K, V>[] table;
    private static final int START_CAPACITY = 10;

    private double LOAD_THRESHOLD = 0.75;
    private int numKeys;
    private int numDeletes;
    private final Entry<K, V> DELETED = new Entry<>(null, null);
    @SuppressWarnings("unchecked")
    // Constructor 
    public HashTableCoalesced() {
        table = new Entry[START_CAPACITY]; 
    }

    /** Finds either the target key or the first empty slot in the
    *search chain using quadratic probing probing.
    *@pre The table is not full.
    *@param key The key of the target object
    *@return The position of the target or the first empty slot if
    *the target is not in the table.
    */

    private int find(K key) {
        // Calculate the starting index.
        int index = key.hashCode() % table.length;
        int nextIndex=-99;
        if (index < 0){
            index += table.length;
        }
             // Make it positive.
        // Increment index until an empty slot is reached
        // or the key is found.
        int k = 1;
        while ((table[index] != null) && (!key.equals(table[index].getKey()))) {
            if (table[index].next==-1) {
                nextIndex=index;
            }
            index = (index+k) % table.length; 
            k+=2;
            if (index<0) {
                index+=table.length;
            }
        }
        if (nextIndex!=-99) {
            table[nextIndex].next=index;
        }
        return index;
    }

    /** Method get for class HashtableOpen.
    *@param key The key being sought
    *@return the value associated with this key if found;
    *otherwise, null
    */
    @Override
    public V get(K key) {
        // Find the first table element that is empty
        // or the table element that contains the key.
        int index = find(key);
        // If the search is successful, return the value.
        if (table[index] != null)
            return table[index].getValue();
        else
            return null; // key not found.
    }

    /** Method put for class HashtableOpen.
    *@post This key‐value pair is inserted in the
    *table and numKeys is incremented. If the key is already
    *in the table, its value is changed to the argument
    *value and numKeys is not changed. If the LOAD_THRESHOLD
    *is exceeded, the table is expanded.
    *@param key The key of item being inserted
    *@param value The value for this key
    *@return Old value associated with this key if found;
    *otherwise, null
    */
    @Override
    public V put(K key, V value) {
        // Find the first table element that is empty
        // or the table element that contains the key.
        int index = find(key);
        // If an empty element was found, insert new entry.
        if (table[index] == null) {
            table[index] = new Entry<>(key, value);
            numKeys++;
            // Check whether rehash is needed.
            double loadFactor = (double) (numKeys + numDeletes) / table.length;
            if (loadFactor > LOAD_THRESHOLD)
                rehash();
            return null;
        }
        // assert: table element that contains the key was found.
        // Replace value for this key.
        V oldVal = table[index].getValue();
        table[index].setValue(value);
        return oldVal;
    }

    @Override
    public V remove(K key) {
        int index = find(key);
        if (table[index]==null) {
            return null;
        }
        V oldValue=table[index].value;
        if (table[index].next==-1) {
            table[index]=null;
        }
        else{
            int temp=0;
            while (table[index].next!=-1) {
                table[index].key=table[table[index].next].key;
                if (table[index].next!=-1) {
                    if (table[table[index].next].next==-1) {
                       temp=index; 
                    }
                    index=table[index].next;

                }
            }
            table[index]=null;
            table[temp].next=-1;
        }
        return oldValue;
    }

    @SuppressWarnings("unchecked")
    /** Expands table size when loadFactor exceeds LOAD_THRESHOLD
    *@post The size of the table is doubled and is an odd integer.
    *Each nondeleted entry from the original table is
    *reinserted into the expanded table.
    *The value of numKeys is reset to the number of items
    *actually inserted; numDeletes is reset to 0.
    */
    private void rehash() {
        // Save a reference to oldTable.
        Entry<K, V>[] oldTable = table;
        // Double capacity of this table.
        table = new Entry[2 * oldTable.length + 1];
        // Reinsert all items in oldTable into expanded table.
        numKeys = 0;
        numDeletes = 0;
        for (int i = 0; i < oldTable.length; i++) {
            if ((oldTable[i] != null) && (oldTable[i] != DELETED)) {
                // Insert entry in expanded table
                put(oldTable[i].getKey(), oldTable[i].getValue());
            }
        }
    }

    @Override
    public boolean isEmpty() {
        return numKeys==0;
    }

    @Override
    public int size() {
        return numKeys;
    }

    public void print() {
        for (int i = 0; i < table.length; i++) {
            if (table[i]!=null) {
                System.out.println("Index: "+i+" Hash: "+table[i].getKey().hashCode()%START_CAPACITY+" Key: "+table[i].key + " Next: " + table[i].next);
            }   
        }
    }
    private static class Entry<K, V> {
        /** The key */
        private K key;
        /** The value */
        private V value;
        /** Next Index */
        private int next;

        /** Creates a new key‐value pair.
         @param key The key
        @param value The value
        */
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
            this.next=-1;
        }
        /** Retrieves the key.
         @return The key
        */
        public K getKey() {
            return key;
        }
        /** Retrieves the value.
         @return The value
        */
        public V getValue() {
            return value;
        }
        /** Sets the value.
         @param val The new value
        @return The old value
        */
        public V setValue(V val) {
            V oldVal = value;
            value = val;
            return oldVal;
        }
    }
}