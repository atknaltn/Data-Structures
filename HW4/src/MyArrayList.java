public class MyArrayList<E> implements MyList<E>{

    private E[] data;
    private int capacity=10;
    private int size=0 ;
    /**
     * This constructor creates MyArrayList with given size.
     * @param size Size of the MyArrayList
     */
    @SuppressWarnings("unchecked")
    public MyArrayList(int capacity) {
        this.capacity=capacity;
        data = (E[])new Object[this.capacity];
    }
    /**
    * This constructor creates MyList with default capacity 10. 
    * 
    */
    @SuppressWarnings("unchecked")
    public MyArrayList() {
        data = (E[])new Object[this.capacity];
    }
    /**
     * This function adds the given element into collection's data array. If capacity is full then it increases capacity by 1.
     * @param e Element,data
     */
    public boolean add(E e) {
        if (size>=capacity) {
            reallocate();
        }
        data[size]=(E)e;
        size++;
        return true;
    }
    /**
     * 
     * @param index index to add
     * @param e element to add
     * @return
     */
    public boolean add(int index, E e) {
        if (index<0 || index>=size) {
            throw new IndexOutOfBoundsException();
        }
        if (size>=capacity) {
            reallocate();
        }
        for (int i = size; i > index; i--) {
            data[i]=data[i-1];
        }
        data[index]=e;
        size++;
        return true;
    }
    /**
     * When the list get fulled this function allocates new memory.
     */
    @SuppressWarnings("unchecked")
    private void reallocate(){
        int i;
        capacity*=2;
        E[] tempArr = (E[])new Object[capacity];
        for (i = 0; i < size; i++) {
            tempArr[i]=data[i];
        }
        data=tempArr;
    }
    /**
     * 
     * @param obj element to find index
     * @return index, if element is not in the array it returns -1.
     */
    public int indexOf(E obj) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(obj)) {
                return i;                
            }
        }
        return -1;
    }
    /**
     * Removes a single instance of the specified element from this collection, if it is present
     * @param index Element,data
     */
    public E remove (int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(index);
        }
        E temp = data[index];
        for (int i = index + 1; i < size; i++) {
            data[i-1] = data[i];
        }
        size--;
        return temp;
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
        if (index<0 || index >=size) {
            throw new IndexOutOfBoundsException();
        }
        return data[index];
    }
    /**
     * 
     * @param index Index
     * @param anEntry Given element.
     * @return Deleted Element.
     */
    public E set(int index, E anEntry) {
        if (index<0 || index >=size) {
            throw new IndexOutOfBoundsException();
        }
        E temp = data[index];
        data[index]=anEntry;
        return temp;
    }
    /**
     * 
     * @return last element in the array
     */
    public E getLast() {
        return data[size-1];
    }
}