import java.util.*;

/**
 * MySkipList
 * @author Atakan ALTIN
 */
public class MySkipList<E extends Comparable<E>> implements NavigableSet<E> {

    private static final double LOG2 = Math.log(2.0);
    private int maxLevel = 4;
    private int maxCap = (int) (Math.pow(2, maxLevel) - 1);
    private int size = 0;
    private SLNode<E> head;
    static final int MIN = Integer.MIN_VALUE;
	private Random rand = new Random();

    @SuppressWarnings("unchecked")
    public MySkipList(){
        size = 0;
		maxLevel = 0;
		maxCap = computeMaxCap(maxLevel);
		head = new SLNode(maxLevel, MIN);
    }

	/**
	 * Search for an item in the skip-list
	 * @param target The item being sought
	 * @return An SLNode array which references the predecessors of the target at each level.
	 */
	@SuppressWarnings("unchecked")
	private SLNode<E>[] search(E target){
		SLNode<E>[] pred = (SLNode<E>[]) new SLNode[maxLevel];
		SLNode<E> current = head;
		for(int i = current.links.length - 1; i >= 0; i--){
			while(current.links[i] != null
					&& current.links[i].data.compareTo(target) < 0){
				current = current.links[i];
			}
			pred[i] = current;
		}
		return pred;
	}

	/**
	 * Find an object in the skip-list
	 * @param target The item being sought
	 * @return A reference to the object in the skip-list that matches
	 * 		   the target. If not found, null is returned 
	 */
	public E find(E target){
		SLNode<E>[] pred = search(target);
		if(pred[0].links != null &&
				pred[0].links[0].data.compareTo(target) == 0){
			return pred[0].links[0].data;
		} else {
			return null;
		}
	}
	
    private static class SLNode<E> {
        SLNode<E>[] links;
        E data;
        @SuppressWarnings("unchecked")
        public SLNode(int level,E data){
            links = (SLNode<E>[]) new SLNode[level];
            this.data=data;
        }
        public String toString(){
			return (data.toString() + " |" + links.length + "|"); 
		}
    }

    /**
	 * This function inserts the given item into the skip-list
	 * @param item The element to add
	 * @return true if the item is added
	 */
	public boolean insert(E item){
		size++;
		SLNode<E>[] pred = search(item);
		if(size > maxCap){
			maxLevel++;
			maxCap = computeMaxCap(maxLevel);
			head.links = Arrays.copyOf(head.links, maxLevel);
			pred = Arrays.copyOf(pred, maxLevel);
			pred[maxLevel - 1] = head;
		}
		SLNode<E> newNode = new SLNode<E>(logRandom(), item);
		for(int i = 0; i < newNode.links.length; i++){
			newNode.links[i] = pred[i].links[i];
			pred[i].links[i] = newNode;
		}
		return true;
	}
	
	/**
	 * This function deletes the given element
	 * @param item The element to delete
	 * @return true if the item is removed
	 */
	public boolean delete(E item){
		SLNode<E>[] pred = search(item);
		if(pred[0].links != null &&
				pred[0].links[0].data.compareTo(item) != 0){
			return false; //item is not in the list
		} else { 
			size--; //don't re-adjust maxCap and level, as we may have nodes at these levels
			SLNode<E> deleteNode = pred[0];
			for(int i = 0; i < deleteNode.links.length; i++){
				if(pred[i].links[i] != null)
					pred[i].links[i] = pred[i].links[i].links[i];
			}
			return true;
		}
	}

	/**
	 * Method to generate a logarithmic distributed integer between 1 and maxLevel.
	 *  I.E. 1/2 of the values are 1, 1/4 are 2, etc.
	 * @return a random logarithmic distributed int between 1 and maxLevel
	 */
	private int logRandom(){
		int r = rand.nextInt(maxCap);
		int k = (int) (Math.log(r + 1) / LOG2);
		if(k > maxLevel - 1)
			k = maxLevel - 1;
		return maxLevel - k;
	}

    /**
	 * Recompute the max cap
	 * @param level
	 * @return
	 */
	private int computeMaxCap(int level){
		return (int) Math.pow(2, level) - 1;
	}

	public String toString(){
		if(size == 0)
			return "Empty";
		StringBuilder sc = new StringBuilder();
		SLNode itr = head;
		sc.append("MaxLevel: " + maxLevel);
		int lineMaker = 0;
		while(itr.links[0] != null){
			itr = itr.links[0];
			sc.append(" --> " + itr.toString());
			lineMaker++;
			if(lineMaker == 10){
				sc.append("\n");
				lineMaker = 0;
			}
		}
		return sc.toString();
	}
    /**
     * Skip-list iterator
     */
    @Override
    public SkipListIter<E> iterator() {
        return new SkipListIter<>();
    }
    /**
     * Inner iterator class
     */
    @SuppressWarnings("unchecked")
    private class SkipListIter<E extends Comparable<E>> implements Iterator<E> {
        private ArrayList<E> data;
        private int index=0;
        private int controlSize;
        /**
         * Constructor adds all items into arraylist to reverse the item order.
         */
        public SkipListIter(){
            data = new ArrayList<>();
            this.controlSize=size;
            SLNode<E> iter = (SLNode<E>)head;
            while (iter.links[0]!=null) {
                data.add(iter.links[0].data);
                index++;
                iter = iter.links[0];                
            }
        }
        /**
         * Returns true if there is next item in the list
         */
        @Override
        public boolean hasNext() {
            return index!=0;
        }
        /**
         * Returns the next item of the list
         */
        @Override
        public E next(){
            if (controlSize!=size) {
                throw new NoSuchElementException("You cannot add items during iteration.");
            }
            if (!hasNext()) {
                throw new IndexOutOfBoundsException();
            }
            return data.get(--index);
        }
    }

    @Override
    public Comparator<? super E> comparator() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public E first() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public E last() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean contains(Object o) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Object[] toArray() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean add(E e) {
        return insert(e);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean remove(Object o) {
        return delete((E)o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public E lower(E e) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public E floor(E e) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public E ceiling(E e) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public E higher(E e) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public E pollFirst() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public E pollLast() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public NavigableSet<E> descendingSet() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Iterator<E> descendingIterator() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public NavigableSet<E> subSet(E fromElement, boolean fromInclusive, E toElement, boolean toInclusive) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public NavigableSet<E> headSet(E toElement, boolean inclusive) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public NavigableSet<E> tailSet(E fromElement, boolean inclusive) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SortedSet<E> subSet(E fromElement, E toElement) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SortedSet<E> headSet(E toElement) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SortedSet<E> tailSet(E fromElement) {
        // TODO Auto-generated method stub
        return null;
    }


















   
    
}