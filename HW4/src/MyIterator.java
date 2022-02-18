/**
 * 
 * @author Atakan ALTIN
 *
 * @param <E>
 * 
 * Custom Iterator Interface for implementing set method.
 */
public interface MyIterator <E>{
	
	public boolean hasNext();
	public E next();
	public void set(E item);
	
}
