/**
 * Custom iterator class MapIterator to iterate through the keys in a HashMap data structure in Java
 */
public class HashMapIterator<K,V> implements MapIterator<K>{

    private MyHashMap<K,V> map;
    private K[] keys;
    private int iterateCount;
    private int index;

    /**
     * One parameter constructor. Iterator starts from beginning.
     * @param hashMap HashMap data structure
     */
    @SuppressWarnings("unchecked")
    public HashMapIterator(MyHashMap<K,V> hashMap) {
        this.map=hashMap;
        this.index=-1;
        this.iterateCount=0;
        keys=(K[]) map.keySet().toArray();
        
        for (int i = 0; i < keys.length; i++) {
            System.out.print(keys[i]+",");
        }
        System.out.println();
    }
    /**
     * Two parameter constructer. Iterator starts from the given key and still iterate though all 
     * the keys in the Map.
     * @param map HashMap data structure.
     * @param key Beginnning key.
     */
    @SuppressWarnings("unchecked")
    public HashMapIterator(MyHashMap<K,V> hashMap,K key) {
        this.map=hashMap;
        this.iterateCount=0;
        keys=(K[]) map.keySet().toArray();
        for (int i = 0; i < keys.length; i++) {
            if (keys[i].equals(key)) {
                this.index = i;
                break;
            }
        }
    }
    /**
     * The method returns True if there are still not-iterated key/s in the Map, otherwise returns False.
     */
    @Override
    public boolean hasNext() {
        if (iterateCount>=keys.length) {
            return false;
        }
        return true;
    }
    /**
     * The function returns the next key in the Map. It returns the first key when there is no not-iterated 
     * key in the Map.
     * @return Next key
     */
    @Override
    public K next() {
        if (!hasNext()) {
            return keys[0];
        }
        if (index>=keys.length && hasNext()) {
            index=0;
        }
        iterateCount++;
        return keys[++index];
    }
    /**
     * The iterator points to the previous key in the Map. It returns the last key when the iterator 
     * is at the first key.
     * @return Previous key
     */
    @Override
    public K prev() {
        if (index==0) {
            index=keys.length;
        }
        iterateCount--;
        return keys[--index];
    }

}