/**
 * MyLinkedList Class
 */
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class MyLinkedList<E> implements MyList<E>{

    private Node<E> head = null;
    private Node<E> tail=null;
    private int size=0;

    /**
     * Gets the data in the given index.
     * @return Data in the given index.
     */
    @Override
    public E get(int index) {
        return listIter(index).next();
    }
    /**
     * Gets the first element.
     * @return first element
     */
    public E getFirst() {
        return head.data;
    }
    /**
     * Gets the last element
     * @return last element.
     */
    public E getLast() {
        return tail.data;
    }
    /**
     * Removes the given object from the list.
     * @param obj Object to be removed.
     * @return True if object is in the array. False if object does not exist.
     */
    public boolean remove(E obj){
        MyListIter iter = listIter();
        while (iter.hasNext()) {
            if (iter.next().equals(obj)) {
                iter.remove();
                return true;
            }
        }
        return false;
    }
    /** Add an item at the specifiedindex.
        @param index The index atwhich the object isto be inserted
        @param obj The object to be inserted
        @throws IndexOutOfBoundsException if the index is out of range
    */
    public void add(int index, E obj)
    {
        listIter(index).add(obj);
    }
    /**
     * Adds the given item to first node of the list.
     * @param obj Object
     */
    public void addFirst(E obj){
        add(0, obj);
    }
    /**
     * Adds the given item to last node of the list.
     * @param obj Object
     */
    public void addLast(E obj){
        add(size, obj);
    }
    /**
     * Returns the iterator at the beginning of the list.
     * @return Iter
     */
    public MyListIter listIter(){
        return new MyListIter(0);
    }
    /**
     * Returns the iterator at the given index of the list.
     * @param index Index.
     * @return Iter
     */
    public MyListIter listIter(int index){
        return new MyListIter(index);
    }
    /**
     * Returns the size of the list.
     * @return size
     */
    @Override
    public int size() {
        return size;
    }
    /**
     * Returns the index of the given object in the list. If object does not exist in the list, returns -1.
     * @param obj Object
     * @return Index.
     */
    public int indexOf(E obj){
        int i=0;
        MyListIter iter = listIter();
        while (iter.hasNext()) {
            if (iter.next()==obj) {
                return i;
            }
            i++;
        }
        return -1;
    }
    /**
     * Node Inner Class
     */
    private static class Node<E>{
        private E data;
        private Node<E> next=null;
        private Node<E> prev=null;

        private Node(E item){
            data = item;
        }
        @Override
        public boolean equals(Object obj) {
            return data==((Node)obj).data;
        }
    }
    /**
     * Inner Iterator Class.
     */
    private class MyListIter implements ListIterator<E>{
        private MyLinkedList.Node<E> nextItem;
        private Node<E> lastItemReturned;
        private int index = 0;
        /**
         * Creates an iterator with the given index.
         * @param i Index
         */
        public MyListIter(int i){
            if (i < 0 || i > size) {
                throw new IndexOutOfBoundsException("Invalid Index: "+i);
            }
            lastItemReturned=null;
            if (i==size) {
                index=size;
                nextItem=null;
            }
            else{  
                nextItem = head;
                for (int index = 0; index < i; index++) {
                    nextItem=nextItem.next;
                }
            }
        }
        /**
         * Adds the given element to the list.
         * @param obj Object
         */
        @Override
        public void add(E obj) {
            if (head==null) {
                head=new Node<E>(obj);
                tail=head;
            }
            else if (nextItem==head) {
                Node<E> newNode = new Node<E>(obj);
                newNode.next=nextItem;
                nextItem.prev=newNode;
                head=newNode;
            }
            else if (nextItem==null) {
                Node<E> newNode = new Node<E>(obj);
                tail.next=newNode;
                newNode.prev=tail;
                tail=newNode;
            }
            else{
                Node<E> newNode = new Node<E>(obj);
                newNode.prev=nextItem.prev;
                nextItem.prev.next=newNode;
                newNode.next=nextItem;
                nextItem.prev=newNode;
            }
            size++;
            index++;
        }
        /**
         * Checks if the current node has a next node.
         * @return True if current node has a next node. Otherwise returns false.
         */
        @Override
        public boolean hasNext() {
            return nextItem!=null;
        }
        /**
         * Checks if the current node has a previous node.
         * @return True if current node has a previous node. Otherwise returns false.
         */
        @Override
        public boolean hasPrevious() {
            return nextItem!=head;
        }
        /**
         * Returns the next node of the list.
         * @return Next Node.
         */
        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            lastItemReturned = nextItem;
            nextItem=nextItem.next;
            index++;
            return lastItemReturned.data;
        }
        /**
         * Returns the next index of the current node.
         * @return Index
         */
        @Override
        public int nextIndex() {
            return index;
        }
        /**
         * Returns the previous node of the list.
         * @return Previous Node.
         */
        @Override
        public E previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            if (nextItem==null) {
                nextItem=tail;
            }
            else{
                nextItem=nextItem.prev;
            }
            lastItemReturned=nextItem;
            index--;
            return lastItemReturned.data;
        }
        /**
         * Returns the previous index of the current node.
         * @return Index
         */
        @Override
        public int previousIndex() {
            return --index;
        }
        /**
         * Removes the last returned item.
         */
        @Override
        public void remove() { 
            if (lastItemReturned==null) {
                throw new IllegalStateException();
            }
            if (lastItemReturned==head) {
                head=lastItemReturned.next;
                lastItemReturned.next.prev=null;
            }
            else if (lastItemReturned==tail) {
                tail=lastItemReturned.prev;
                lastItemReturned.prev.next=null;
            }else{
                lastItemReturned.prev.next=lastItemReturned.next;
                lastItemReturned.next.prev=lastItemReturned.prev;
            }
            lastItemReturned=null;
            size--;
        }
        /**
         * Replaces the last returned item with given object.
         * @param obj Object.
         */
        @Override
        public void set(E obj) {
            if (lastItemReturned==null) {
                throw new IllegalStateException();
            }
            lastItemReturned.data=obj;
        }
    }
}