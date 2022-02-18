import java.io.*;
import java.util.*;

/**
 * Customer
 */
public class Customer extends User{
    public Customer(String name,String password,String role,Company company){
        super(name, password,company);
        this.role=role;
    }
    /**
     * Shows the customer's menu. Scanning inputs are in the here.
     */
    @Override
    public void menu() {
        boolean exit=true,exit2=true,exit3=true;
        int selection;
        String input;
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.println("WELCOME, "+getName());
        while (exit2) {
            System.out.println();
            System.out.println("1.SEARCH PRODUCTS");
            System.out.println("2.ALL PRODUCTS OF A TRADER");
            System.out.println("3.EXIT");
            System.out.println("SELECTION: ");
            selection=sc.nextInt();
            switch (selection) {
                case 1:
                    System.out.println();
                    System.out.println("PRODUCT NAME: ");
                    input=sc.nextLine();
                    input=sc.nextLine();
                    try {
                        getCompany().loadProducts(input);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    while (exit) {
                        System.out.println("HOW WOULD YOU LIKE TO VIEW THE PRODUCTS ?");
                        System.out.println("1.SORT BY NAME");
                        System.out.println("2.SORT BY PRICE");
                        System.out.println("3.SORT BY DISCOUNT PERCENTAGE");
                        System.out.println("4.EXIT");
                        System.out.println("SELECTION: ");
                        selection=sc.nextInt();
                        switch (selection) {
                            case 1:
                                sortByName();
                                break;
                            case 2:
                                sortByPrice();
                                break;
                            case 3:
                                sortByDiscount();
                                break;
                            case 4:
                                exit=false;
                                break;
                            default:
                                System.err.println("WRONG COMMAND!");   
                                break;
                        }
                        while (exit3) {
                            System.out.println();
                            System.out.println("1.ADD PRODUCT TO CART");
                            System.out.println("2.FILTER BY CATEGORY");
                            System.out.println("3.FILTER BY PRICE");
                            System.out.println("4.EXIT");
                            System.out.print("SELECTION: ");
                            selection=sc.nextInt();
                            switch (selection) {
                                case 1:
                                    System.out.print("ENTER THE PRODUCT ID: ");
                                    input=sc.nextLine();
                                    input=sc.nextLine();
                                    try {
                                        if (giveAnOrder(input,getCompany().products)==false) {
                                            System.out.println("Invalid Product!");
                                        }
                                    } catch (IOException e) {
                                        System.err.println("The product could not be ordered.");
                                    }
                                    break;
                                case 2:
                                    System.out.println("ENTER A CATEGORY: ");
                                    input=sc.nextLine();
                                    input=sc.nextLine();
                                    filterByCategory(input);
                                    break;
                                case 3:
                                    double lowerPrice,upperprice;
                                    System.out.print("ENTER LOWER PRICE: ");
                                    lowerPrice=sc.nextInt();
                                    System.out.print("ENTER UPPER PRICE: ");
                                    upperprice=sc.nextInt();
                                    for (int i = 0; i < getCompany().products.size(); i++) {
                                        if (getCompany().products.get(i).getPrice()<= upperprice &&
                                        getCompany().products.get(i).getPrice()>= lowerPrice) {
                                            System.out.println(getCompany().products.get(i).getId()+";"
                                            +getCompany().products.get(i).getName()+";"
                                            +getCompany().products.get(i).getAllCategories()
                                            +";"+getCompany().products.get(i).getPrice()+";"+getCompany().products.get(i).getDiscountedPrice()
                                            +"; %"+String.format("%.2f", getCompany().products.get(i).getDiscountPercentage())+";"+getCompany().products.get(i).getTrader());
                                        }
                                    }
                                    break;      
                                case 4:
                                    exit3 = false;
                                    break;        
                                default:
                                    System.err.println("WRONG COMMAND!"); 
                                    break;
                            }
                        }
                    }
                    break;
                case 2:
                    System.out.println();
                    System.out.println("ENTER THE TRADER NAME: ");
                    input = sc.nextLine();
                    input = sc.nextLine();
                    try {
                        ArrayList<Product> temp= loadTradersProducts(input);
                        System.out.print("ENTER THE PRODUCT ID YOU WANT TO BUY: ");
                        input=sc.nextLine();
                        try {
                            if (giveAnOrder(input,temp)==false) {
                                System.out.println("Invalid Product!");
                            }
                        } catch (IOException e) {
                            System.err.println("The product could not be ordered.");
                        }
                    } catch (IOException e) {
                        System.err.println("Trader cannot be found.");
                    }
                    break;
                case 3:
                    exit2=false;
                    break;
                default:
                    System.out.println("WRONG COMMAND!");
                    break;
            }
        }
    }

    /**
     * Filters the search results by given category.
     * @param category Category will be filtered.
     */
    public void filterByCategory(String category){
        for (int i = 0; i < getCompany().products.size(); i++) {
            for (String categ : getCompany().products.get(i).getCategory()) {
                if (categ.equals(category)) {
                    System.out.println(getCompany().products.get(i).getId()+";"
                    +getCompany().products.get(i).getName()+";"
                    +getCompany().products.get(i).getAllCategories()
                    +";"+getCompany().products.get(i).getPrice()+";"+getCompany().products.get(i).getDiscountedPrice()
                    +"; %"+String.format("%.2f", getCompany().products.get(i).getDiscountPercentage())+";"+getCompany().products.get(i).getTrader());
                }
            }
        }
    }

    /**
     * It provides customers to give an order
     * @param id ID of the product.
     * @param products Product data 
     * @return false if the id is invalid
     * @throws IOException when the file io fails
     */
    public boolean giveAnOrder(String id,ArrayList<Product> products) throws IOException {
        int index = checkProductID(id,products); 
        if (index==-1) {
            return false;
        }else{
            FileWriter writer = new FileWriter("orders.txt",true);
            writer.write(products.get(index).getId()+";");
            writer.write(products.get(index).getName()+";");
            writer.write(products.get(index).getAllCategories()+";");
            writer.write(products.get(index).getPrice()+";");
            writer.write(products.get(index).getDiscountedPrice()+";");
            writer.write(products.get(index).getDescription()+";");
            writer.write(products.get(index).getTrader()+";");
            writer.write(this.getId()+"\n");
            writer.close();
            return true;
        }
    }

    /**
     * 
     * @param id ID of the product.
     * @param products Product data 
     * @return false if the prduct is not in the array.
     */
    private int checkProductID(String id,ArrayList<Product> products){
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }
    /**
     * When the user search the prodcuts by trader name this function handle it.
     * @param traderName Traders name
     * @return arraylist containing the trader's products.
     * @throws IOException when the file io fails
     */
    public ArrayList<Product> loadTradersProducts(String traderName) throws IOException {
        ArrayList<Product> products = new ArrayList<>();
        File prodFile = new File("products.txt");
        String row;
        if (prodFile.isFile()) {
            BufferedReader prodReader = new BufferedReader(new FileReader("products.txt"));
            while ((row = prodReader.readLine()) != null) {
                String[] data = row.split(";");
                if (data[6].equals(traderName)) {
                    products.add(new Product(data[1],data[0],Double.parseDouble(data[3]),Double.parseDouble(data[4]),data[5],data[6],data[2]));
                }
            }
            prodReader.close();
        }
        if (products.size()==0) {
            throw new IOException();
        }
        else{
            Iterator<Product> iter = products.iterator();
            while (iter.hasNext()) {
                Product p = iter.next();
                System.out.println(p.getId()+";"+p.getName()+";"+p.getAllCategories()+";"+p.getPrice()+";"+p.getDiscountedPrice()
                +"; %"+String.format("%.2f",p.getDiscountPercentage())+";"+p.getTrader());
            }
        }
        return products;
    }

    public void sortByName() {
        mergeSort(getCompany().products);
        printProducts();
    }

    public void sortByPrice() {
        bubbleSort(getCompany().products);
        printProducts();
    }

    public void sortByDiscount(){
        insertionSort(getCompany().products);
        printProducts();
    }

    private void printProducts(){
        for (int i = 0; i < getCompany().products.size() ;i++) {
            System.out.println(getCompany().products.get(i).getId()+";"
            +getCompany().products.get(i).getName()+";"+getCompany().products.get(i).getAllCategories()+";"+getCompany().products.get(i).getPrice()
            +";"+getCompany().products.get(i).getDiscountedPrice()+"; %"+String.format("%.2f", getCompany().products.get(i).getDiscountPercentage())+";"+getCompany().products.get(i).getTrader());
        }
    }

    /**
     * It sorts the procuts by using insertion sort algorithm.
     * @param input products
     */
    private void insertionSort(ArrayList<Product> input)
    {
        for (int i = 1; i < input.size(); ++i) {
            Product key = input.get(i);
            int j = i - 1;
            while (j >= 0 && input.get(j).getDiscountPercentage() > key.getDiscountPercentage()) {
                input.set(j+1,input.get(j));
                j = j - 1;
            }
            input.set(j+1,key);
        }
    }

    /**
     * It sorts the procuts by using bubble sort algorithm.
     * @param input products
     */
    private void bubbleSort(ArrayList<Product> input)
    {
        int i, j;
        Product temp;
        boolean swapped;
        for (i = 0; i < input.size() - 1; i++)
        {
            swapped = false;
            for (j = 0; j < input.size() - i - 1; j++)
            {
                if (input.get(j).getPrice()>input.get(j+1).getPrice())
                {
                    temp=input.get(j);
                    input.set(j,input.get(j+1));
                    input.set(j+1,temp);
                    swapped = true;
                }
            }
            if (swapped == false)
                break;
        }
    }

    /**
     * It sorts the procuts by using merge sort algorithm.
     * @param input products
    */
    private void mergeSort(ArrayList<Product> input) {
        if (input.size() != 1) {
            ArrayList<Product> left = new ArrayList<Product>();
            ArrayList<Product> right = new ArrayList<Product>();
            boolean flag = true;
            while (!input.isEmpty()) {
                if (flag) {
                    left.add(input.remove(0));
                } else {
                    right.add(input.remove(0));
                }
                flag = !flag;
            }
            mergeSort(left);
            mergeSort(right);
            input.addAll(merge(left, right));
        }
    }
    /**
     * Helper function for merge sort.
     * @param left left sub array
     * @param right right sub array
     * @return Sorted array.
     */
    private ArrayList<Product> merge(ArrayList<Product> left, ArrayList<Product> right) {
        ArrayList<Product> merged = new ArrayList<>();
        while (!left.isEmpty() && !right.isEmpty()) {
            if (left.get(0).getName().compareTo(right.get(0).getName()) <= 0) {
                merged.add(left.remove(0));
            } else {
                merged.add(right.remove(0));
            }
        }
        merged.addAll(left);
        merged.addAll(right);
        return merged;
    }
    /**
     * Checks if the two user are the same or not;
     */
    @Override
    public int compareTo(User otherUser) {
        if (this.getId().equals(otherUser.getId())) {
            return 1;
        }
        return 0;
    }
    /**
     * This funciton saves customer information into a file.
     * @throws IOException
     */
    public void writeFile() throws IOException{
        FileWriter customerWriter = new FileWriter("users.txt",true);
        customerWriter.write(getRole()+";");
        customerWriter.write(getName()+";");
        customerWriter.write(getId()+";");
        customerWriter.write(getPassword()+"\n");
        customerWriter.close();
    }
}