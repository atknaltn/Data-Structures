import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Driver {
    public static void main(String[] args) {
    	
    	System.out.println("Unit test for PART I");
    	
    	MyHeap<Integer> heap = new MyHeap<>();
    	System.out.println("Removing element from empty heap.");
    	try {
        	heap.remove();	
		} catch (Exception e) {
			System.err.println("Remove process cannot be done.");
		}
    	System.out.println("Adding some elements to the heap.");
        heap.add(20);
        heap.add(39);
        heap.add(28);
        heap.add(29);
        heap.add(66);
        heap.add(18);
        heap.add(8);
        heap.add(37);
        heap.add(76);
        heap.add(32);
        heap.add(74);
        heap.add(89);
        heap.add(26);
        heap.print();
        
        System.out.println("Removing some elements from heap");
        heap.remove();
        heap.remove();
        heap.remove();
        heap.remove();
        
        heap.print();
        MyHeap<Integer> heap2 = new MyHeap<>();
    	heap2.add(15);
    	heap2.add(9);
    	heap2.add(10);
    	heap2.add(2);
    	System.out.println("Creating Another heap: ");
    	heap2.print();
    	System.out.println("Merging two heaps: ");
    	heap.merge(heap2);
    	heap.print();
    	
    	System.out.println("Trying to reach out of bounds.");
    	try {
    		heap.remove(145);
		} catch (Exception e) {
			System.out.println("Index out of bounds. Exception are handled.");
		}
    	System.out.println("Removing 5th largest element from the heap. It should be 28");
    	heap.remove(5);
    	heap.print();
    	System.out.println("Is there 120 in de heap? ");
    	System.out.println(heap.search(120)); 
    	System.out.println("Is there 15 in de heap? ");
    	System.out.println(heap.search(15)); 
    	
    	System.out.println("Attempting to set an element without next() call in the iterator");
        MyIterator<Integer> iter = heap.Iter();
		try {
			iter.set(74);
		} catch (Exception e) {
			System.out.println("Please call next() method first.");
		}
        System.out.println("Printing elements using iterator");
        while (iter.hasNext()) {
            System.out.print(iter.next()+",");
        }
        System.out.println();
        System.out.println("Set with iterator the last element : 8 to 65");
        iter.set(65);
        heap.print();
        
    	System.out.println();
    	System.out.println();
    	System.out.println("Unit test for PART II");
    	
    	BSTHeapTree<Integer> heapTree = new BSTHeapTree<>();
    	ArrayList<Integer> arrayList = new ArrayList<>();
    	Random rand = new Random();
    	int randNum;
    	for (int i = 0; i < 3000; i++) {
			randNum=rand.nextInt(5000);
			heapTree.add(randNum);
			arrayList.add(randNum);
		}
    	Collections.sort(arrayList);
    	System.out.println("BSTHeap Mode: "+heapTree.findMode());
    	System.out.println("Array Mode: "+ mode(arrayList));
    	
    	for (int i = 0; i < 100; i++) {
			randNum=rand.nextInt(3000);
			try {
				System.out.println("Searched Item: "+arrayList.get(randNum)+" BST Frequency: "+ heapTree.find(arrayList.get(randNum)) + " Array Frequency: " + findOccurency(arrayList, arrayList.get(randNum)));
			} catch (Exception e) {
				System.out.println("Item Cannot be find. Exception handled.");
			}
		}
    	
    	for (int i = 0; i < 10; i++) {
    		randNum=rand.nextInt(3000)+5000;
    		try {
    			System.out.println("Searched Item: "+randNum+" BST Frequency: "+ heapTree.find(randNum) + " Array Frequency: " + findOccurency(arrayList,randNum));
			} catch (Exception e) {
				System.out.println("Item Cannot be find. Exception handled.");
			}
    		
		}
    		
    }
    /**
     * Finds mode of given array
     * @param array
     * @return
     */
    public static int mode(ArrayList<Integer> array) {
        int mode = 0;
        int maxCount = 0;
        for (int i = 0; i < array.size(); i++) {
          int value = array.get(i);
          int count = 1;
          for (int j = 0; j < array.size(); j++) {
              if (array.get(j) == value) 
                count++;
              if (count > maxCount) {
                  mode = value;
                  maxCount = count;
              }
          }
        }
        return mode;
      }
    /**
     * Finds frequency of given element.
     * @param array
     * @param num
     * @return
     */
    public static int findOccurency(ArrayList<Integer> array,int num) {
    	int count = 0;
    	for (int i = 0; i < array.size(); i++) {
          if (array.get(i)==num) {
			count++;
          }
        }
        return count;
	}
}
