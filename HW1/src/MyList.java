/**
 * Resizable-array implementation of the List interface.
 */
public class MyList<E> implements List<E>{

    private E[] data;
    private int capacity=10;
    private int size=0 ;
    /**
     * This constructor creates MyList with given size.
     * @param size Size of the MyList
     */
    @SuppressWarnings("unchecked")
    public MyList(int size) {
        this.size=size;
        data = (E[])new Object[this.capacity];
    }
    /**
    * This constructor creates MyList with default capacity 10. 
    * 
    */
    @SuppressWarnings("unchecked")
    public MyList() {
        data = (E[])new Object[this.capacity];
    }
    /**
     * This function adds the given element into collection's data array. If capacity is full then it increases capacity by 1.
     * @param e Element,data
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean add(Object e) {
        int i;
        if (size>=capacity) {
            capacity=capacity+1;
        }
        if (data==null) {
            data = (E[])new Object[this.capacity];
        }
        E[] tempArr = (E[])new Object[capacity];
        for (i = 0; i < size; i++) {
            tempArr[i]=data[i];
        }
        tempArr[size]=(E)e;
        size=size+1;
        data=tempArr;
        return true;
    }
    /**
     * Removes all of the elements from this collection.
     * 
     */
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            data[i]=null;
        }
        data=null;
        size=0;
        capacity=10;
    }
    /**
     * Returns true if this collection contains the specified element.
     * @param e Element,data
     */
    @Override
    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return true;
            }
        }    
        return false;
    }
    /**
     * Returns true if this collection contains no elements.
     */
    @Override
    public boolean isEmpty() {
        return size==0;
    }
    /**
     * Removes a single instance of the specified element from this collection, if it is present
     * @param e Element,data
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean remove(E e) {
        int index= findIndex(e);
        if (index!=-1) {
            if (size==1) {
                size=0;
                return true;
            }else{
                E[] tempArr = (E[])new Object[size-1];
                for (int i = 0,j=0; i < size; i++) {
                    if (i!=index) {
                        tempArr[j++] = data[i];
                    }
                }
                data=tempArr;
                size=tempArr.length;
                return true;
            }
        }else
            return false;
    }
    /**
     * Returns the index of given data.
     * @param e Element,data
     * @return
     */
    public int findIndex(E e){
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }
    /**
    * Return size of the collection
    */
    @Override
    public int size() {
        return size;
    }

    /**
    * It returns the data in the given index.
    * @param index Index.
    */
    @Override
    public E get(int index) {
        return data[index];
    }
}