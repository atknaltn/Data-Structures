/**
 * Driver class for test all methods.
 */
public class Driver {

    public static void main(String[] args) {
        
        MyHashMap<String,Integer> mapIter = new MyHashMap<>();
        mapIter.put("Istanbul",1);
        mapIter.put("Ankara",2);
        mapIter.put("Izmir",3);
        mapIter.put("Bursa",4);
        mapIter.put("Antalya",5);

        System.out.println("Creating iterator with no paramater. It starts from beginning.");
        MapIterator<String> iter = mapIter.iterator();

        System.out.println("Moving from start to finish");
        while (iter.hasNext()) {
            System.out.println("- "+iter.next());
        }

        System.out.println("Reversing order. This is testing for prev funciton.");
        System.out.println("Moving from end to the beginning.");
        System.out.println("- "+iter.prev());
        System.out.println("- "+iter.prev());
        System.out.println("- "+iter.prev());
        System.out.println("- "+iter.prev());
        System.out.println("- "+iter.prev());

        System.out.println("Creating iterator with 'Izmir'. First going next then go back two steps.Also removig 'Bursa'.");
        mapIter.remove("Bursa");
        iter=mapIter.iterator("Izmir");
        System.out.println("- "+iter.next());
        System.out.println("- "+iter.prev());
        System.out.println("- "+iter.prev());

        System.out.println();
        System.out.println("--TEST CASES IN THE HOMEWORK PDF ('-1' MEANS NULL)--");
        HashTableCoalesced<Integer,String> map = new HashTableCoalesced<>();
        System.out.println("INSERT : "+3);
        map.put(3, "3");
        map.print();
        System.out.println("--------------------------------------------");
        System.out.println("INSERT : "+12);
        map.put(12, "12");
        map.print();
        System.out.println("--------------------------------------------");
        System.out.println("INSERT : "+13);
        map.put(13, "13");
        map.print();
        System.out.println("--------------------------------------------");
        System.out.println("INSERT : "+25);
        map.put(25, "25");
        map.print();
        System.out.println("--------------------------------------------");
        System.out.println("INSERT : "+23);
        map.put(23, "23");
        map.print();
        System.out.println("--------------------------------------------");
        System.out.println("INSERT : "+51);
        map.put(51, "51");
        map.print();
        System.out.println("--------------------------------------------");
        System.out.println("INSERT : "+42);
        map.put(42, "42");
        map.print();
        System.out.println("--------------------------------------------");
        System.out.println("DELETE : "+13);
        map.remove(13);
        map.print();

        System.out.println();
        System.out.println("SMALL-SİZE: 500");
        test(500);
        System.out.println();
        System.out.println("MEDIUM-SİZE: 1000");
        test(1000);
        System.out.println();
        System.out.println("LARGE-SİZE: 5000");
        test(5000);
    }

    public static void test(int size) {
        HashTableChainLinkedList<Integer,Integer> map1 = new HashTableChainLinkedList<>();
        HashTableChainTreeSet<Integer,Integer> map2 = new HashTableChainTreeSet<>();
        HashTableCoalesced<Integer,Integer> map3 = new HashTableCoalesced<>();
        long startTime=0,endTime=0,execTime=0;

        System.out.println("-------Adding "+size+" items-------");
        startTime=System.nanoTime();
        for (int i = 0; i <size; i++) {
            map1.put(i,i);
        }
        endTime=System.nanoTime();
        execTime=endTime-startTime;
        System.out.println("Execution time for LinkedList: " + execTime + " nano seconds");
        startTime=System.nanoTime();
        for (int i = 0; i <size; i++) {
            map2.put(i,i);
        }
        endTime=System.nanoTime();
        execTime=endTime-startTime;
        System.out.println("Execution time for TreeSet: " + execTime + " nano seconds");
        startTime=System.nanoTime();
        for (int i = 0; i <size; i++) {
            map3.put(i,i);
        }
        endTime=System.nanoTime();
        execTime=endTime-startTime;
        System.out.println("Execution time for Coalesed: " + execTime + " nano seconds");

        System.out.println("-------Getting "+size+" existing items-------");
        startTime=System.nanoTime();
        for (int i = 0; i <size; i++) {
            map1.get(i);
        }
        endTime=System.nanoTime();
        execTime=endTime-startTime;
        System.out.println("Execution time for LinkedList: " + execTime + " nano seconds");
        startTime=System.nanoTime();
        for (int i = 0; i <size; i++) {
            map2.get(i);
        }
        endTime=System.nanoTime();
        execTime=endTime-startTime;
        System.out.println("Execution time for TreeSet: " + execTime + " nano seconds");
        startTime=System.nanoTime();
        for (int i = 0; i <size; i++) {
            map3.get(i);
        }
        endTime=System.nanoTime();
        execTime=endTime-startTime;
        System.out.println("Execution time for Coalesed: " + execTime + " nano seconds");

        System.out.println("-------Removing "+size+" items-------");
        startTime=System.nanoTime();
        for (int i = 0; i <size; i++) {
            map1.remove(i);
        }
        endTime=System.nanoTime();
        execTime=endTime-startTime;
        System.out.println("Execution time for LinkedList: " + execTime + " nano seconds");
        startTime=System.nanoTime();
        for (int i = 0; i <size; i++) {
            map2.remove(i);
        }
        endTime=System.nanoTime();
        execTime=endTime-startTime;
        System.out.println("Execution time for TreeSet: " + execTime + " nano seconds");
        startTime=System.nanoTime();
        for (int i = 0; i <size; i++) {
            map3.remove(i);
        }
        endTime=System.nanoTime();
        execTime=endTime-startTime;
        System.out.println("Execution time for Coalesed: " + execTime + " nano seconds");

        System.out.println("-------Getting "+size+" non-existing items-------");
        startTime=System.nanoTime();
        for (int i = 0; i <size; i++) {
            map1.get(i);
        }
        endTime=System.nanoTime();
        execTime=endTime-startTime;
        System.out.println("Execution time for LinkedList: " + execTime + " nano seconds");
        startTime=System.nanoTime();
        for (int i = 0; i <size; i++) {
            map2.get(i);
        }
        endTime=System.nanoTime();
        execTime=endTime-startTime;
        System.out.println("Execution time for TreeSet: " + execTime + " nano seconds");
        startTime=System.nanoTime();
        for (int i = 0; i <size; i++) {
            map3.get(i);
        }
        endTime=System.nanoTime();
        execTime=endTime-startTime;
        System.out.println("Execution time for Coalesed: " + execTime + " nano seconds");

        System.out.println("-------Try to remove "+size+" non-existing items-------");
        startTime=System.nanoTime();
        for (int i = 0; i <size; i++) {
            map1.remove(i+size);
        }
        endTime=System.nanoTime();
        execTime=endTime-startTime;
        System.out.println("Execution time for LinkedList: " + execTime + " nano seconds");
        startTime=System.nanoTime();
        for (int i = 0; i <size; i++) {
            map2.remove(i+size);
        }
        endTime=System.nanoTime();
        execTime=endTime-startTime;
        System.out.println("Execution time for TreeSet: " + execTime + " nano seconds");
        startTime=System.nanoTime();
        for (int i = 0; i <size; i++) {
            map3.remove(i+size);
        }
        endTime=System.nanoTime();
        execTime=endTime-startTime;
        System.out.println("Execution time for Coalesed: " + execTime + " nano seconds");
    }
}