import java.util.Iterator;
import java.util.Random;
/**
 * Test class
 * @author Atakan ALTIN
 */
public class Driver {
    public static void main(String[] args) {
        MySkipList<Integer> list = new MySkipList<>();
        System.out.println("------------- Adding elements to SkipList --------------\n");
        list.insert(10);
        list.insert(5);
        list.insert(20);
        list.insert(40);
        list.insert(15);
        list.insert(30);
        list.insert(25);
        list.insert(50);
        list.insert(45);
        list.insert(35);
        System.out.println(list);
        System.out.println("\n\n-------------Removing 35(exist) from SkipList--------------\n");
        list.delete(35);
        System.out.println(list);

        System.out.println("\n\n-------------Removing 27(non-exist) from SkipList--------------\n");
        try {
            list.delete(27);
        } catch (Exception e) {
            System.err.println("Element is not exist.");
        }

        System.out.println(list);

        System.out.println("\n\n-------------ITERATOR TEST--------------\n");
        Iterator<Integer> iter = list.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
        System.out.println("\n\n-------------Trying to reach next element after iteration done for all items--------------\n");
        try {
            System.out.println(iter.next());
        } catch (Exception e) {
            System.err.println("Iterator Cannot Reach This Element.");
        }
        System.out.println("------------Adding Elements to AVL Tree--------------");
        MyAVLTree<Integer> avltree = new MyAVLTree<>();
        avltree.add(3);
        avltree.add(9);
        avltree.add(5);
        avltree.add(2);
        avltree.add(6);
        avltree.add(10);
        System.out.println(avltree);
        System.out.println("------------Deleting Elements is not tested because it is not asked--------------");
        System.out.println("\n\n-------------ITERATOR TEST--------------\n");
        Iterator<Integer> avlIter = avltree.iterator();
        while (avlIter.hasNext()) {
            System.out.println(avlIter.next());
        }
        System.out.println("\n\n-------------Trying to reach next element after iteration done for all items--------------\n");
        try {
            System.out.println(avlIter.next());
        } catch (Exception e) {
            System.err.println("Iterator Cannot Reach This Element.");
        }
        MyAVLTree<Integer> heads = (MyAVLTree<Integer>) avltree.headSet(5,true);
        MyAVLTree<Integer> tails = (MyAVLTree<Integer>) avltree.tailSet(5,false);
        System.out.println("------------Testing Headset for parameter 5(inclusive)--------------");
        Iterator<Integer> avlIter2 = heads.iterator();
        while (avlIter2.hasNext()) {
            System.out.println(avlIter2.next());
        }
        System.out.println("------------Testing Tailset for parameter 5(exclusive)--------------");
        Iterator<Integer> avlIter3 = tails.iterator();
        while (avlIter3.hasNext()) {
            System.out.println(avlIter3.next());
        }
        MyAVLTree<Integer> testHead = (MyAVLTree<Integer>) avltree.headSet(33,true);
        System.out.println("------------Testing Headset for parameter 33(Non-existing)--------------");
        Iterator<Integer> avlIter4 = testHead.iterator();
        while (avlIter3.hasNext()) {
            System.out.println(avlIter4.next());
        }
        MyAVLTree<Integer> testTail = (MyAVLTree<Integer>) avltree.tailSet(33,true);
        System.out.println("------------Testing Tailset for parameter 33(Non-existing)--------------");
        Iterator<Integer> avlIter5 = testTail.iterator();
        while (avlIter3.hasNext()) {
            System.out.println(avlIter5.next());
        }
        System.out.println("\n\n------------Creating Ordinary Binary Search Tree--------------");
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.add(1);
        tree.add(2);
        tree.add(3);
        tree.add(4);
        tree.add(5);
        System.out.println(tree);

        System.out.println("------------Creating AVL Tree--------------");
        AVLTree<Integer> tree2 = new AVLTree<>();
        tree2.add(4);
        tree2.add(3);
        tree2.add(5);
        tree2.add(2);
        tree2.add(1);
        System.out.println(tree2);
        System.out.println();
        System.out.println("------------Creating Red-Black Tree--------------");

        RedBlackTree<Integer> rbTree = new RedBlackTree<>();
        for (int i = 0; i < 11; i++) {
            rbTree.add(i);
        }
        System.out.println(rbTree);
        System.out.println();
        System.out.println("Binary Tree: ");
        controlAVLandRB(tree);
        System.out.println();
        System.out.println("AVL Tree: ");
        controlAVLandRB(tree2);
        System.out.println();
        System.out.println("Red-Black Tree: ");
        controlAVLandRB(rbTree);
        System.out.println();

        System.out.println("-----------PART3-------------");
        treeTest(10000);
        System.out.println();
        treeTest(20000);
        System.out.println();
        treeTest(40000);
        System.out.println();
        treeTest(80000);
    }

    public static void treeTest(int limit) {
        Random random = new Random();
        long startTime,endTime;
        long avgBinary=0,avgRB=0,avgTwoThree=0,avgBtree=0,avgSkip=0;
        for (int i = 0; i <10; i++) {
            int[] randomNumbers = random.ints(0, 1000000).distinct().limit(limit+100).toArray();
            BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>();
            RedBlackTree<Integer> redBlackTree= new RedBlackTree<>();
            TwoThreeTree<Integer> twoThreeTree = new TwoThreeTree<>();
            BTree<Integer> bTree = new BTree<>(4);
            MySkipList<Integer> skipList = new MySkipList<>();
            for (int j = 0; j < limit; j++) {
                binarySearchTree.add(randomNumbers[j]);
                redBlackTree.add(randomNumbers[j]);
                twoThreeTree.add(randomNumbers[j]);
                bTree.add(randomNumbers[j]);
                skipList.insert(randomNumbers[j]);
            }
            startTime = System.nanoTime();
            for (int j = limit; j < limit+100; j++) {
                binarySearchTree.add(randomNumbers[j]);
            }
            endTime=System.nanoTime();
            avgBinary+=(endTime-startTime);

            startTime = System.nanoTime();
            for (int j = limit; j < limit+100; j++) {
                redBlackTree.add(randomNumbers[j]);
            }
            endTime=System.nanoTime();
            avgRB+=(endTime-startTime);

            startTime = System.nanoTime();
            for (int j = limit; j < limit+100; j++) {
                twoThreeTree.add(randomNumbers[j]);
            }
            endTime=System.nanoTime();
            avgTwoThree+=(endTime-startTime);

            startTime = System.nanoTime();
            for (int j = limit; j < limit+100; j++) {
                bTree.add(randomNumbers[j]);
            }
            endTime=System.nanoTime();
            avgBtree+=(endTime-startTime);

            startTime = System.nanoTime();
            for (int j = limit; j < limit+100; j++) {
                skipList.insert(randomNumbers[j]);
            }
            endTime=System.nanoTime();
            avgSkip+=(endTime-startTime);
        }
        avgBinary/=10;
        avgRB/=10;
        avgTwoThree/=10;
        avgBtree/=10;
        avgSkip/=10;
        System.out.println("TESTING FOR  " + limit + " ITEMS:");
        System.out.println("BINARY SEARCH TREE AVG. INSERTION TIME: " + avgBinary + " nano seconds");
        System.out.println("RED-BLACK TREE AVG. INSERTION TIME: "+ avgRB + " nano seconds");
        System.out.println("TWO THREE TREE AVG. INSERTION TIME: "+ avgTwoThree + " nano seconds");
        System.out.println("B-TREE AVG. INSERTION TIME: "+ avgBtree + " nano seconds");
        System.out.println("SKIP LIST AVG. INSERTION TIME: "+ avgSkip + " nano seconds");
    }

    public static <E extends Comparable<E>> void controlAVLandRB(BinarySearchTree<E> tree) {
        if (isAVL(tree))
            System.out.println("GIVEN TREE IS AN AVL TREE.");
        else
            System.out.println("GIVEN TREE IS NOT AN AVL TREE.");
        if (isRedBlack(tree)) 
            System.out.println("GIVEN TREE IS A RED-BLACK TREE.");
        else 
            System.out.println("GIVEN TREE IS NOT A RED-BLACK TREE.");
    }

    private static <E extends Comparable<E>> boolean isAVL(BinaryTree<E> tree){
        int leftHeight,rightHeight;
        if (tree==null) {
            return true;
        }
        leftHeight = heightOfTree(tree.getLeftSubtree());
        rightHeight = heightOfTree(tree.getRightSubtree());
        if (Math.abs(leftHeight-rightHeight)<=1 && isAVL(tree.getLeftSubtree()) && isAVL(tree.getRightSubtree()))
            return true;
        return false;
    }
    /**
     * Calculates height of the tree
     * @param <E> Generic Element
     * @param tree Tree to calculate height
     * @return Heigt of the tree
     */
    private static <E> int heightOfTree(BinaryTree<E> tree)
    {
        if (tree == null)
            return 0;
        else
        {
            int leftDepth = heightOfTree(tree.getLeftSubtree());
            int rightDepth = heightOfTree(tree.getRightSubtree());
            if (leftDepth > rightDepth)
                return (leftDepth + 1);
             else
                return (rightDepth + 1);
        }
    }
    /**
     * Checks if the given tree is red-black.
     * @param <E> generic Element
     * @param tree Tree to check 
     * @return True if the tree is red-black
     */
    private static <E extends Comparable<E>> boolean isRedBlack(BinaryTree<E> tree) {
        return !tree.root.isRed() && childBlacknessControl(tree) && blacknessBalance(tree)!=-1;
    }
    /**
     * Checks if the both childs of the parent is black or not
     * @param <E> generic Element
     * @param tree parent to check
     * @return True if the both childs is black
     */
    private static <E extends Comparable<E>> boolean childBlacknessControl(BinaryTree<E> tree){
        if (tree.root==null) {
            return true;
        }
        if (tree.root.isRed()) {
            if (tree.root.left.isRed() || tree.root.right.isRed()) {
                return false;
            }
        }
        childBlacknessControl(tree.getLeftSubtree());
        childBlacknessControl(tree.getRightSubtree());
        return true;
    }
    /**
     * Checks if the both sides of three has equal black nodes.
     * @param <E> generic Element
     * @param tree parent to check
     * @return True if the both sides has equal black node.
     */
    private static <E extends Comparable<E>> int blacknessBalance(BinaryTree<E> tree){
        if (tree.root == null)
              return 0;
        else {
                int lNode=0,rNode=0;
                if (tree.root.left.isRed()==false)
                    lNode = 1;
                if (tree.root.right.isRed()==false)
                    rNode = 1;
                int leftBlackHeight = blacknessBalance(tree.getLeftSubtree()) + lNode;
                int rightBlackHeight = blacknessBalance(tree.getRightSubtree()) + rNode;
                
                if (leftBlackHeight != rightBlackHeight)
                    return -1;
                return 1;
         }
    }
}
