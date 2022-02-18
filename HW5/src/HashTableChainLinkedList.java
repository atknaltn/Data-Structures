import java.util.LinkedList;

/**
 * HashTableChainLinkedList
 */
public class HashTableChainLinkedList<K,V> implements KWHashMap<K,V>{

    /** The table */
    private LinkedList<Entry<K, V>>[] table;
    /** The number of keys */
    private int numKeys;
    /** The capacity */
    private static final int CAPACITY = 101;
    /** The maximum load factor */
    private static final double LOAD_THRESHOLD = 3.0;
    // Constructor
    @SuppressWarnings("unchecked")
    public HashTableChainLinkedList() {
        table = new LinkedList[CAPACITY];
    }

    @Override
    public V get(K key) {
        int index = key.hashCode() % table.length;
        if (index < 0)
            index += table.length;
        if (table[index] == null)
            return null; // key is not in the table.
        // Search the list at table[index] to find the key.
        for (Entry<K, V> nextItem : table[index]) {
            if (nextItem.getKey().equals(key))
                return nextItem.getValue();
        }
        // assert: key is not in the table.
        return null;
    }

    @Override
    public boolean isEmpty() {
        
        return false;
    }

    /** Method put for class HashtableChain.
         @post This key‐value pair is inserted in the
        table and numKeys is incremented. If the key is already
        in the table, its value is changed to the argument
        value and numKeys is not changed.
        @param key The key of item being inserted
        @param value The value for this key
        @return The old value associated with this key if
        found; otherwise, null
    */
    @Override
    public V put(K key, V value) {

        int index = key.hashCode() % table.length;
        if (index < 0)
            index += table.length;
        if (table[index] == null) {
            // Create a new linked list at table[index].
            table[index] = new LinkedList<>();
        }
        
        // Search the list at table[index] to find the key.
        for (Entry<K, V> nextItem : table[index]) {
            // If the search is successful, replace the old value.
            if (nextItem.getKey().equals(key)) {
                // Replace value for this key.
                V oldVal = nextItem.getValue();
                nextItem.setValue(value);
                return oldVal; 
            }
        }
        // assert: key is not in the table, add new item.
        table[index].addFirst(new Entry<>(key, value));
        numKeys++;
        if (numKeys > (LOAD_THRESHOLD * table.length))
        rehash();
        return null;
    }

    @Override
    public V remove(K key) {
        int index = key.hashCode() % table.length;
        if (index < 0)
            index += table.length;
        if (table[index] == null) 
            return null;
        // Search the list at table[index] to find the key.
        for (Entry<K, V> nextItem : table[index]) {
            // If the search is successful, replace the old value.
            if (nextItem.getKey().equals(key)) {
                V oldVal = nextItem.getValue();
                table[index].remove(nextItem);
                numKeys--;
                if (table[index].size()==0) {
                    table[index]=null;
                }
                return oldVal; 
            }
        }      
        return null;
    }

    @Override
    public int size() {
        return numKeys;
    }

    @SuppressWarnings("unchecked")
    private void rehash() {
        // Save a reference to oldTable.
        LinkedList<Entry<K, V>>[] oldTable = table;
        // Double capacity of this table.
        table = new LinkedList[2 * oldTable.length + 1];
        // Reinsert all items in oldTable into expanded table.
        numKeys = 0;
        for (int i = 0; i < oldTable.length; i++) {
            if (oldTable[i] != null) {
                for (Entry<K,V> nextItem : oldTable[i]) {
                    put(nextItem.getKey(), nextItem.getValue());
                }
            }
        }
    }
       
    private static class Entry<K, V> {
        /** The key */
        private final K key;
        /** The value */
        private V value;
        /** Creates a new key‐value pair.
         @param key The key
        @param value The value
        */
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
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