import java.util.HashMap;

/**
 * MyHashMap
 */
public class MyHashMap<K,V> extends HashMap<K,V>{

    public HashMapIterator<K,V> iterator(){
        return new HashMapIterator<>(this);
    }
    public HashMapIterator<K,V> iterator(K key){
        return new HashMapIterator<>(this,key);
    }
}