import java.util.NoSuchElementException;
/**
 * Custem Generic Heap Class
 * @author Atakan
 *
 * @param <E>
 */
public class MyHeap<E extends Comparable<E>>{
    private MyArrayList<E> data;
    private int size=0;
    
    /**
     * Creates empty heap.
     */
    public MyHeap(){
        data=new MyArrayList<E>();
    }
    /**
     * Swaps two value of given indexes.
     * @param source index of source
     * @param target index of target
     */
    private void swap(int source,int target){
        E temp = data.get(source);
        data.set(source,data.get(target));
        data.set(target,temp);
    }
    /**
     * Returns parent of given index.
     * @param index index
     * @return parent
     */
    private int getParentPos(int index) {
        return (index-1)/2;      
    }
    /**
     * Returns left child of given index.
     * @param index Parent
     * @return Left child
     */
    private int getLeftChildPos(int index) {
        return (2*index)+1;
    }
    /**
     * Returns right child of given index.
     * @param index parent
     * @return right child
     */
    private int getRigtChildPos(int index) {
        return (2*index)+2;
    }
    /**
     * Adds given element into the heap. It maintains heap data structure.
     * @param item element to add.
     * @return always true.
     */
    public boolean add(E item) {
        size++;
        data.add(item);

        int currentIndex = data.size()-1;
        while (data.get(currentIndex).compareTo(data.get(getParentPos(currentIndex)))>0) {
            swap(currentIndex, getParentPos(currentIndex));
            currentIndex=getParentPos(currentIndex);
        }
        return true;
    }
    /**
     * Remove the element in the root. It maintains heap data structure.
     * @return Removed Element.
     */
    public E remove() {
        int leftChild,rightChild,parent=0,maxChild;
        if (size==0) {
            throw new NoSuchElementException();
        }
        swap(data.size()-1,0);
        E temp=data.remove(data.size()-1);
        while (true) {
            leftChild=(2*parent)+1;
            rightChild=leftChild+1;
            if (leftChild>=data.size()) 
                break;
            maxChild=leftChild;
            if (rightChild<data.size() && data.get(rightChild).compareTo(data.get(leftChild))>0)
            	maxChild=rightChild;
            if (data.get(parent).compareTo(data.get(maxChild))<0) {
                swap(parent, maxChild);
                parent=maxChild;
            }
            else
                break;
        }
        size--;
        return temp;
    }
    private boolean hasLeftChild(int index) {
        return !((2*index)+1>=data.size());
    }
    private boolean hasRightChild(int index) {
        return !((2*index)+2>=data.size());
    }
    /**
     * Prints Heap on the Terminal Screen.
     */
    public void print(){
        for (int i = 0; i <= (data.size()-2) / 2; i++) {
            System.out.print(" PARENT : " + data.get(i));
            if (hasLeftChild(i))
                System.out.print( " LEFT CHILD : " + data.get(getLeftChildPos(i)));
            if (hasRightChild(i))
                System.out.print(" RIGHT CHILD :" + data.get(getRigtChildPos(i)));
            System.out.println();
        }
    }
    /**
     * Searches an element if it is in the heap or not.
     * @param element element to search
     * @return True if given element is in the heap. Otherwise false.
     */
	public boolean search(E element) {
    	MyIterator<E> iter = this.Iter();
    	while (iter.hasNext()){
			if (element.compareTo(iter.next())==0) {
				return true;
			}
		}
    	return false;
    }
	/**
	 * Merges two heaps by adding their elements each other.
	 * @param other
	 */
    public void merge(MyHeap<E> other) {
        MyIterator<E> iter = other.Iter();
        while (iter.hasNext()) {
            add(iter.next());
        }
    }
    /**
     * Removes the ith largest element. It uses buble sort algorithm. It creates sorted array, remove the element then adds
     * the all elements in a new heap.
     * @param ith ith largest element
     * @return False if ith is larger than size.Otherwise True.
     */
	public boolean remove (int ith) {
    	if (ith>=size) {
			throw new IndexOutOfBoundsException();
		}
        MyArrayList<E> temp = new MyArrayList<>();
        for (int i = 0; i < data.size(); i++) {
			temp.add(data.get(i));
		}

        for (int i = 0; i < temp.size()-1; i++) {
            for (int j = 0; j < temp.size()-i-1; j++) {
                if (temp.get(j).compareTo(temp.get(j+1))>0) {
                    E tempValue=temp.get(j);
                    temp.set(j,temp.get(j+1));
                    temp.set(j+1, tempValue);
                }
            }
        }
        temp.remove(temp.size()-ith);
        data=new MyArrayList<>();
        for (int i = 0; i < temp.size(); i++) {
			add(temp.get(i));
		}
        size=data.size();
        return true;
    }
    /**
     * Returns the iterator at the beginning of the heap data.
     * @return Iter
     */
    public MyIter Iter(){
        return new MyIter(0);
    }
    /**
     * Returns the iterator at the given index of the heap data.
     * @param index Index.
     * @return Iter
     */
    public MyIter Iter(int index){
        return new MyIter(index);
    }
    
    private void makeHeap() {
        int leftChild,rightChild,parent=0,maxChild;
        if (size==0) {
            throw new NoSuchElementException();
        }
        swap(data.size()-1,0);
        while (true) {
            leftChild=(2*parent)+1;
            rightChild=leftChild+1;
            if (leftChild>=data.size()) 
                break;
            maxChild=leftChild;
            if (rightChild<data.size() && data.get(rightChild).compareTo(data.get(leftChild))>0)
            	maxChild=rightChild;
            if (data.get(parent).compareTo(data.get(maxChild))<0) {
                swap(parent, maxChild);
                parent=maxChild;
            }
            else
                break;
        }
    }
    
    /**
     * Private Custom Iterator class
     * @author Atakan ALTIN
     *
     */
    private class MyIter implements MyIterator<E>{
        private int nextItem=0;
        private int lastItemReturned=0;
        private int index=0;
        public MyIter(int i){
            if (i < 0 || i > size) {
                throw new IndexOutOfBoundsException("Invalid Index: "+i);
            }
            lastItemReturned=-1;
            nextItem=i;
        }
        @Override
        public boolean hasNext() {
            return index<size;
        }
        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            lastItemReturned = nextItem;
            nextItem++;
            index++;
            return data.get(lastItemReturned);
        }
        @Override
		public void set(E item) {
            if (lastItemReturned==-1) {
                throw new IllegalStateException();
            }
            data.set(lastItemReturned,item);
            makeHeap();
        }
    }
}
