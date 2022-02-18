/**
 * HybridList Class
 */
import java.util.ListIterator;

public class HybridList<E> implements MyList<E>{
    private MyLinkedList<MyArrayList<E>> data;
    private int MAX_NUMBER;
    private int size=0;
    /**
     * Creates HybridList with specific ArrayList size.
     * @param num MAX_nUMBER for ArrayList
     */
    public HybridList(int num){
        setMAX_NUMBER(num);
        data=new MyLinkedList<>();
        data.addFirst(new MyArrayList<E>(getMAX_NUMBER()));
    }
    /**
     * Gets the specific element in the given index.
     * @return Element.
     */
    @Override
    public E get(int index) {
        return data.get(index/getMAX_NUMBER()).get(index%getMAX_NUMBER());
    }
    /**
     * Returns the size of HybridList.
     */
    @Override
    public int size() {
        return size;
    }
    /**
     * Add the given object to the last of the HybridList.
     * @param obj Element
     */
    public void add(E obj) {
        if (size%getMAX_NUMBER()==0) {
            data.addLast(new MyArrayList<>(getMAX_NUMBER()));
        }
        data.get(size/getMAX_NUMBER()).add(size%getMAX_NUMBER(),obj);
        size++;
    }
    /**
     * Adds the given object to the specific index of the Hybridlist.
     * @param index
     * @param obj
     */
    public void add(int index, E obj){
        if(index < 0 || index > size)
            throw new IndexOutOfBoundsException();
        if(size%getMAX_NUMBER() == 0){
            data.addLast(new MyArrayList<E>());
        }
        MyArrayList<E> currArrList=data.get(index/getMAX_NUMBER());
        E lastItem = currArrList.get(currArrList.size()-1);
        currArrList.add(index%getMAX_NUMBER(),obj);
        for(int i = index/getMAX_NUMBER(); i < size/getMAX_NUMBER(); i++){
            data.get(i+1).add(0,lastItem);
            lastItem=data.get(i).get(data.get(i).size()-1);
        }
        size++;
    }
    /**
     * Removes the element of given index from the HybridList.
     * @param index index
     * @return Removed Object.
     */
    public E remove(int index) {
        if(index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        E returnedItem=data.get(index/getMAX_NUMBER()).get(index%getMAX_NUMBER());
        for(int i=index;i<size-1;i++) {
            MyArrayList<E> temp = data.get(i/getMAX_NUMBER());
            if((i)%getMAX_NUMBER() == getMAX_NUMBER()-1)
                temp = data.get((i-1)/getMAX_NUMBER());
            temp.set(i%getMAX_NUMBER(), temp.get((i+1)%getMAX_NUMBER()));
        }
        size--;
        return returnedItem;
    }
    /**
     * Sets the element of given index.
     * @param index index
     * @param obj given object
     * @return element of the given index before it has changed.
     */
    public E set(int index,E obj){
        if (index<0 || index>=size) {
            throw new IndexOutOfBoundsException();
        }
        E temp = data.get(index/getMAX_NUMBER()).get(index%getMAX_NUMBER());
        data.get(index/getMAX_NUMBER()).set(index%getMAX_NUMBER(),obj);
        return temp;
    }
    /**
     * Returns the index of given object. It returns -1 if there is no such an element in the List.
     * @param obj Element
     * @return Index of given object
     */
    public int indexOf(E obj){
        for (int i = 0; i < data.size(); i++) {
            for (int j = 0; j < data.get(i).size(); j++) {
                if (data.get(i).get(j)==obj) {
                    return i*getMAX_NUMBER()+j;
                }
            }
        }
        return -1;
    }
    public int getMAX_NUMBER() {
        return this.MAX_NUMBER;
    }

    public void setMAX_NUMBER(int MAX_NUMBER) {
        this.MAX_NUMBER = MAX_NUMBER;
    }
}