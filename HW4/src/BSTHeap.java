import java.util.NoSuchElementException;

/**
 * 
 * @author Atakan ALTIN
 *
 * @param <E>
 * 
 * Custom Heap Class especially created for BSTHeapTree class. Max size of heap is 7.
 */
public class BSTHeap<E extends Comparable<E>>{
	
	private static final int maxSize=7;
	private int size=0;
	private MyArrayList<BSTHeapData<E>> data;
	/**
	 * Creates empty heap.
	 */
	public BSTHeap() {
		data = new MyArrayList<>();
	}
	/**
	 * Adds element into heap and preserves heap data structure. It cannot add more than 7 items.
	 * @param item Element to add.
	 * @return
	 */
    public boolean add(E item) {
    	int index=search(item);
    	if (index!=-1) {
            data.get(index).increaseDataCount();
            return true;
		}
    	if (size>=maxSize) {
			return false;
		}
    	else {
            size++;
            data.add(new BSTHeapData<E>(item));
            int currentIndex = data.size()-1;
            while (data.get(currentIndex).getData().compareTo(data.get(getParentPos(currentIndex)).getData())>0) {
                swap(currentIndex, getParentPos(currentIndex));
                currentIndex=getParentPos(currentIndex);
            }
            return true;
    	}
    }

    /**
     * Prints Heap on the Terminal Screen.
     */
    public void print(){
        for (int i = 0; i <= (data.size()-2) / 2; i++) {
            System.out.print(" PARENT : " + data.get(i).getData() +"," + data.get(i).getDataCount());
            if (hasLeftChild(i))
                System.out.print( " LEFT CHILD : " + data.get(getLeftChildPos(i)).getData()+","+data.get(getLeftChildPos(i)).getDataCount());
            if (hasRightChild(i))
                System.out.print(" RIGHT CHILD :" + data.get(getRigtChildPos(i)).getData()+","+data.get(getRigtChildPos(i)).getDataCount());
            System.out.println();
        }
        System.out.println();
    }
    
    private void swap(int source,int target){
        BSTHeapData<E> temp = data.get(source);
        data.set(source,data.get(target));
        data.set(target,temp);
    }
    private int getParentPos(int index) {
        return (index-1)/2;      
    }
    private int getLeftChildPos(int index) {
        return (2*index)+1;
    }
    private int getRigtChildPos(int index) {
        return (2*index)+2;
    }
    public int getFreq(E item) {
    	if (search(item)==-1) {
			return -1;
		}
    	return data.get(search(item)).getDataCount();
    }
    /**
     * Remove the element in the heap. It maintains heap data structure.
     * @return Removed Element.
     */
    public boolean remove(E item) {
    	if (search(item)==-1) {
			return false;
		}
    	if (data.get(search(item)).getDataCount()>1) {
			data.get(search(item)).decreaseDataCount();
			return true;
		}else {
	        int leftChild,rightChild,parent=search(item),maxChild;
	        if (size==0) {
	            throw new NoSuchElementException();
	        }
	        swap(data.size()-1,parent);
	        while (true) {
	            leftChild=(2*parent)+1;
	            rightChild=leftChild+1;
	            if (leftChild>=data.size()) 
	                break;
	            maxChild=leftChild;
	            if (rightChild<data.size() && (data.get(rightChild).getData()).compareTo(data.get(leftChild).getData())>0)
	            	maxChild=rightChild;
	            if (data.get(parent).getData().compareTo(data.get(maxChild).getData())<0) {
	                swap(parent, maxChild);
	                parent=maxChild;
	            }
	            else
	                break;
	        }
	        size--;
	        data.remove(size);
	        return true;	
		}
    }
    /**
     * Remove the element in the root. It maintains heap data structure.
     * @return Removed Element.
     */
    public BSTHeapData<E> remove() {
    	if (data.get(0).dataCount>1) {
			data.get(0).decreaseDataCount();
			return data.get(0);
		}else {
	        int leftChild,rightChild,parent=0,maxChild;
	        if (size==0) {
	            throw new NoSuchElementException();
	        }
	        swap(data.size()-1,0);
	        BSTHeapData<E> temp=data.remove(data.size()-1);
	        while (true) {
	            leftChild=(2*parent)+1;
	            rightChild=leftChild+1;
	            if (leftChild>=data.size()) 
	                break;
	            maxChild=leftChild;
	            if (rightChild<data.size() && (data.get(rightChild).getData()).compareTo(data.get(leftChild).getData())>0)
	            	maxChild=rightChild;
	            if (data.get(parent).getData().compareTo(data.get(maxChild).getData())<0) {
	                swap(parent, maxChild);
	                parent=maxChild;
	            }
	            else
	                break;
	        }
	        size--;
	        return temp;
		}
    }
    
    private boolean hasLeftChild(int index) {
        return !((2*index)+1>=data.size());
    }
    private boolean hasRightChild(int index) {
        return !((2*index)+2>=data.size());
    }
	/**
	 * Gets root data.
	 * @return Data
	 */
    public E getRoot() {
		return data.get(0).getData();
	}
    public BSTHeapData<E> find(E element){
    	int index=search(element);
    	if (index==-1) {
			return null;
		}
    	return data.get(index);
    }
    /**
     * Searches element in the heap. If it finds returns its index. Otherwise returns -1.
     * @param element Item
     * @return index or -1
     */
	public int search(E element) {
    	int index=0;
    	MyIterator<E> iter = this.Iter();
    	while (iter.hasNext()){
			if (element.compareTo(iter.next())==0) {
				return index;
			}
    		index++;
		}
    	return -1;
    }
    public int size() {
    	return size;
    }
    /**
     * Finds most frequent data in the heap.
     * @return Frequncy.
     */
    public E findMostFreq(){
    	int index=0,min=0;
    	for(int i=0; i<data.size();i++) {
    		if (data.get(i).dataCount>min) {
				index=i;
				min=data.get(i).dataCount;
			}
    	}
    	return data.get(index).getData();
    }
    
    /**
     * Gets root data and frequency.
     * @return BSTHeapData
     */
    public BSTHeapData<E> rootData() {
		return data.get(0);
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
    /**
     * Data class for BSTHeap
     * @author Atakan ALTIN
     *
     * @param <E>
     */
	protected static class BSTHeapData<E>{
		private int dataCount = 1;
		private E data;
		
		public BSTHeapData(E item) {
			setData(item);
		}

		public E getData() {
			return data;
		}

		public void setData(E data) {
			this.data = data;
		}
		
		public int getDataCount() {
			return dataCount;
		}

		public void increaseDataCount() {
			this.dataCount++;
		}
		
		public void decreaseDataCount() {
			this.dataCount--;
		}
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
            if (rightChild<data.size() && data.get(rightChild).getData().compareTo(data.get(leftChild).getData())>0)
            	maxChild=rightChild;
            if (data.get(parent).getData().compareTo(data.get(maxChild).getData())<0) {
                swap(parent, maxChild);
                parent=maxChild;
            }
            else
                break;
        }
    }
    /**
     * Custom Iterator Class
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
            return data.get(lastItemReturned).getData();
        }
        @Override
		public void set(E item) {
            if (lastItemReturned==-1) {
                throw new IllegalStateException();
            }
            data.set(lastItemReturned,new BSTHeapData<E>(item));
            makeHeap();
        }
    }
}
