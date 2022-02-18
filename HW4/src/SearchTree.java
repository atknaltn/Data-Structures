/**
 * 
 * @author Atakan ALTIN
 *
 * @param <E>
 * 
 * Custom Search Tree Interface
 */
public interface SearchTree <E>{
	
	    public int add(E element);
	    public boolean contains(E element);
	    public int find(E element);
	    public int remove(E element);

}
