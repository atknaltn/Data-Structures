import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.SortedSet;

/**
 * MyAVLTree
 * @author Atakan ALTIN
 */
public class MyAVLTree<E extends Comparable<E>> implements NavigableSet<E>{

    private AVLTree<E> data;
    /**
     * Creates an AVL tree.
     */
    public MyAVLTree(){
        data = new AVLTree<>();
    }
    /**
     * Calls AVL tree add function to insert the given element.
     * @param item The element to add.
     * @return True if the item is added.
     */
    public boolean insert(E item) {
        return data.add(item);
    }
    /**
     * AVL Tree iterator
     */
    public AVLTreeIter<E> iterator() {
        return new AVLTreeIter<>();
    }
    /**
     * Inner Iterator class.
     */
    @SuppressWarnings("unchecked")
    private class AVLTreeIter<E extends Comparable<E>> implements Iterator<E> {
        private ArrayList<E> elements;
        private int index=0;
        /**
         * Searches all items and add into to arraylist to navigate items.
         */
        public AVLTreeIter(){
            index=0;
            elements = new ArrayList<>();
            searchTree((BinaryTree<E>)data);
        }
        /**
         * Returns true if there is next item in the tree.
         */
        @Override
        public boolean hasNext() {
            return elements.size()!=0 && index < elements.size();
        }
        /**
         * Returns the next item of the tree
         */
        @Override
        public E next() {
            if (!hasNext()) {
                throw new IndexOutOfBoundsException();
            }
            return elements.get(index++);
        }
        /**
         * Searches all items in the tree.
         * @param root Root of the tree.
         */
        private void searchTree(BinaryTree<E> root){
            if (root==null) {
                return;                
            }
            elements.add(root.getData());
            searchTree(root.getLeftSubtree());
            searchTree(root.getRightSubtree());
        }
    }
    /**
     * Returns an avl tree with the elements which are smaller than given parameter
     */
    @Override
    public NavigableSet<E> headSet(E element,boolean inclusive){
        Iterator<E> iter = this.iterator();
        MyAVLTree<E> temp = new MyAVLTree<>();
        while (iter.hasNext()) {
            E temp2 = iter.next();
            if (inclusive) {
                if (temp2.compareTo(element) <= 0) {
                    temp.insert(temp2);
                }
            }
            else{
                if (temp2.compareTo(element) < 0) {
                    temp.insert(temp2);
                }
            }
        }
        return temp;
    }
    /**
     * Returns an avl tree with the elements which are smaller than given parameter
     */
    @Override
    public SortedSet<E> headSet(E element) {
        Iterator<E> iter = this.iterator();
        MyAVLTree<E> temp = new MyAVLTree<>();
        while (iter.hasNext()) {
            E temp2 = iter.next();
            if (temp2.compareTo(element) < 0) {
                temp.insert(temp2);
            }
        }
        return temp;
    }
    /**
     * Returns an avl tree with the elements which are bigger than given parameter
     */
    @Override
    public NavigableSet<E> tailSet(E element, boolean inclusive) {
        Iterator<E> iter = this.iterator();
        MyAVLTree<E> temp = new MyAVLTree<>();
        while (iter.hasNext()) {
            E temp2 = iter.next();
            if (inclusive) {
                if (temp2.compareTo(element) >= 0) {
                    temp.insert(temp2);
                }
            }
            else{
                if (temp2.compareTo(element) > 0) {
                    temp.insert(temp2);
                }
            }
        }
        return temp;
    }
    /**
     * Returns an avl tree with the elements which are bigger than given parameter
     */
    @Override
    public SortedSet<E> tailSet(E element) {
        Iterator<E> iter = this.iterator();
        MyAVLTree<E> temp = new MyAVLTree<>();
        while (iter.hasNext()) {
            E temp2 = iter.next();
            if (temp2.compareTo(element) > 0) {
                temp.insert(temp2);
                }
        }
        return temp;
    }

    @Override
    public String toString() {
        return data.toString();
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

    @Override
    public boolean remove(Object o) {
        // TODO Auto-generated method stub
        return false;
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
    public SortedSet<E> subSet(E fromElement, E toElement) {
        // TODO Auto-generated method stub
        return null;
    }











    
}