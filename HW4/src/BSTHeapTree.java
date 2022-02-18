import java.util.NoSuchElementException;

/**
 * 
 * @author Atakan ALTIN
 *
 * @param <E>
 * 
 *  BSTHeapTree is a class that keeps the elements in a Binary Search Tree 
 *  where the nodes store max-Heap with a maximum depth of 2 (maximum number of elements
 *  included in a node is 7).
 */
public class BSTHeapTree<E extends Comparable<E>> implements SearchTree<E>{
	
	private Node<E> root;
	private Node<E> tempNode;
	private int addReturn;
	private E deleteReturn;
	private int freq=0;
	private E mode;
	/**
	 * Creates empty tree.
	 */
	public BSTHeapTree() {
		root = null;
	}
	/**
	 * Creates tree with given root.
	 * @param root Root to create a tree.
	 */
	protected BSTHeapTree(Node<E> root) {
		this.root = root;
	}
	/**
	 * Add element to binary tree. 
	 */
	@Override
	public int add(E element) {
		root = add(root,element);
		return addReturn;
	}
	/**
	 * Recursive add function to add an element into correct location.
	 * @param tempRoot Iterative Tree Root
	 * @param element Element to add
	 * @return Root Node.
	 */
	private Node<E> add (Node<E> tempRoot,E element) {
		if (tempRoot==null) {
			BSTHeap<E> tempHeap = new BSTHeap<>();
			tempHeap.add(element);
			return new Node<E>(tempHeap);
		}
		if (tempRoot.data.add(element)) {
			addReturn = tempRoot.data.find(element).getDataCount();
			return tempRoot;
		}else {
			if ((element.compareTo(tempRoot.data.getRoot()))<0) {
				tempRoot.left=add(tempRoot.left, element);
				return tempRoot;
			}else {
				tempRoot.right = add(tempRoot.right, element);
				return tempRoot;
			}
		}
	}
	/**
	 * Prints Tree on Terminal Screen
	 */
	public void print() {
		print(root);
	}
	/**
	 * Recursive print function to print all tree correctly.
	 * @param tempRoot Iterative Tree Root.
	 */
	public void print(Node<E> tempRoot) {
		tempRoot.data.print();
		if (tempRoot.left!=null) {
			System.err.println("LEFT");
			print(tempRoot.left);
		}
		if (tempRoot.right!=null) {
			System.out.println("RIGHT");
			print(tempRoot.right);
		}
	}
	/**
	 * Finds frequency of the given element in the tree.
	 * @param
	 */
	@Override
	public int find(E element) {
		return find(root,element);
	}
	/**
	 * Recursive find function to iterate all tree.
	 * @param tempRoot Iterative Tree Root
	 * @param element Element to find it's frequency.
	 * @return Frequency.
	 */
	private int find(Node<E> tempRoot, E element) {
		if (tempRoot==null) {
			throw new NoSuchElementException();
		}
		if (tempRoot.data.find(element)==null && element.compareTo(tempRoot.data.getRoot())<0) {
			return find(tempRoot.left,element);
		}
		else if (tempRoot.data.find(element)==null && element.compareTo(tempRoot.data.getRoot())>0 ) {
			return find(tempRoot.right,element);
		}
		else {
			return tempRoot.data.find(element).getDataCount();
		}
	}
	/**
	 * Deletes the given item from the BSTHeapTree.
	 */
	@Override
	public int remove(E element) {
		/*try {
			find(element);

		} catch (Exception e) {
			System.err.println("Item cannot be removed because item does not exist");
		}*/
		root=delete(root,element);
		mergeNodes(tempNode);
		return 1;
	}
	
	private Node<E> delete(Node<E> tempRoot,E element) {
		if (tempRoot==null) {
			return null;
		}
		if (tempRoot.left.data.remove(element)) {
			tempNode = tempRoot.left;
			tempRoot.left=null;
			return tempRoot;
		}
		else if (tempRoot.right.data.remove(element)) {
			tempNode = tempRoot.right;
			tempRoot.right=null;
			return tempRoot;
		}
		else if (tempRoot.data.remove(element)) {
			tempNode=tempRoot;
			return tempRoot;
		}
		else {
			if (element.compareTo(tempRoot.data.getRoot())<0) {
				return delete(tempRoot.left,element);
			}
			else {
				return delete(tempRoot.right,element);
			}
		}
	}
	private void mergeNodes(Node<E> tempRoot){
		if (tempRoot==null) {
			return;
		}
		int heapSize=tempRoot.data.size();
		for (int i = 0; i < heapSize; i++) {
			int dataCount= tempRoot.data.rootData().getDataCount();
			for (int j = 0; j<dataCount ; j++) {
				add(tempRoot.data.getRoot());
				tempRoot.data.remove();
			}
		}
		mergeNodes(tempRoot.left);
		mergeNodes(tempRoot.right);
	}
	/**
	 * Finds mode of tree.
	 * @return Mode
	 */
	public E findMode() {
		findMode(root);
		return mode;
	}
	/**
	 * Recursive mode function that finds the each node's frequency then finds mode.
	 * @param tempRoot
	 */
	private void findMode(Node<E> tempRoot) {
		if (tempRoot==null) {
			return;
		}
		E temp =tempRoot.data.findMostFreq();
		if (find(temp)>freq) {
			freq=find(temp);
			mode=temp;
		}
		findMode(tempRoot.left);
		findMode(tempRoot.right);
	}
    /**
     * Node Inner Class
    */
    private static class Node<E extends Comparable<E>>{
        private BSTHeap<E> data;
        private Node<E> left=null;
        private Node<E> right=null;
        /**
         * Creates Node with given item.
         * @param item Item to add.
         */
        private Node(BSTHeap<E> item){
            data = item;
        }

        @Override
        public boolean equals(Object obj) {
            return data==((Node)obj).data;
        }
    }
    /**
     * Returns true if the given element is in the array.Otherwise, returns false.
     * @param element
     * @return true or false
     */
	@Override
	public boolean contains(E element) {
		return find(element)!=-1;
	}
}
