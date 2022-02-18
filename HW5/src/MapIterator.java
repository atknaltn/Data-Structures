/**
 * MapIterator interface.
 */
public interface MapIterator<K> {
    public boolean hasNext();
    public K next();
    public K prev();
}