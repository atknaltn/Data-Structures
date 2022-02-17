import java.util.Random;
import java.util.Scanner;
/**
 * Includes driver code.
 */
public class Main{
	public static void main( String args[] ){
		/*Create hospital*/
		Hospital hospital = new Hospital("Group 2");
		hospital.createDefaultData();
		System.out.println("\n-------------------------------------------------");
		System.out.println("| Welcome to " + hospital.getName() + " Hospital Management System | ");
		System.out.println("-------------------------------------------------");
		/*Main menu*/
		String mail = "";
		String password = "";
		int selection;
		Scanner scanner = new Scanner(System.in);
		boolean exit = true;
		while(exit){
			System.out.println("\n - Main Menu - ");
			System.out.println("\n 1 - Login");
			System.out.println(" 2 - Compare BST and Balanced BST");
			System.out.println(" 0 - Exit");
			System.out.print("Please enter: ");
			selection = scanner.nextInt();
			switch (selection){
				case 1:
					System.out.print("Email: ");
					mail = scanner.nextLine();
					mail = scanner.nextLine();
					System.out.print("Password: ");
					password = scanner.nextLine();
					hospital.login(mail, password);
					break;
				case 2:
					compare();
					break;
				case 0:
					System.out.println(" --> GOODBYE <-- ");
					exit = false;
					break;
				default:
					System.out.println(" WARNING: Wrong command.");
					break;
			}
		}
	}

	/**
	 * To evaluate whether the performance is improved or not.
	 * Add 50000 Doctor object to AVL Tree and BST and find last item
	 */
    public static void compare() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 5;
        Random random = new Random();
    	AVLTree<User> avlHolder=new AVLTree<>();
    	BinarySearchTree<User> binaryHolder=new BinarySearchTree<>();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        StringBuilder last = new StringBuilder(targetStringLength);
    	int j = 0;
    	for(j=0;j<50000;j++){
    	    buffer = new StringBuilder(targetStringLength);
            for(int i = 0; i < targetStringLength; i++) {
            	int randomLimitedInt = leftLimit + (int)(random.nextFloat() * (rightLimit - leftLimit + 1));
            	buffer.append((char) randomLimitedInt);
			}
			if(j==9999)
			  	last.append(buffer);

			avlHolder.add(new Doctor(buffer.toString(), buffer.toString()));
	      	binaryHolder.add(new Doctor(buffer.toString(), buffer.toString()));
    	}
        String generatedString = buffer.toString();
    	double start=0,end=0;
        start=System.nanoTime();
    	binaryHolder.find(new Doctor(last.toString(),last.toString()));
   	 	end=System.nanoTime();
        System.out.println("\nLast item found in Binary Search Tree in "+(end-start)+" nanoseconds");
        start=System.nanoTime();
    	avlHolder.find(new Doctor(last.toString(),last.toString()));
    	end=System.nanoTime();
        System.out.println("Last item found in AVL Tree in "+(end-start)+" nanoseconds");
    }
}