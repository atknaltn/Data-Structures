import java.io.*;
import java.util.*;

public class Trader extends User{
    private Queue<Order> orders;
    /**
     * Trader consructor
     */
    public Trader(String name,String password,String role,Company company){
        super(name, password,company);
        this.role=role;
    }
    /**
     * Shows the customer's menu. Scanning inputs are in the here.
     */
    @Override
    public void menu() {
        boolean exit=true,exit2=true;
        int selection;
        String name,id,price,discountedPrice,description,allCategories;
        Scanner sc = new Scanner(System.in);
        while (exit) {
            System.out.println();
            System.out.println("WELCOME, "+getName());
            System.out.println();
            System.out.println("1.ADD PRODUCTS");
            System.out.println("2.REMOVE PRODUCTS");
            System.out.println("3.EDIT PRODUCTS");
            System.out.println("4.LIST OF ORDERS");
            System.out.println("5.MEET/CANCEL ORDERS");
            System.out.println("6.EXIT");
            System.out.println("SELECTION: ");
            selection=sc.nextInt();
            switch (selection) {
                case 1:
                    System.out.print("ENTER PRODUCT'S NAME: ");
                    name=sc.nextLine();
                    name=sc.nextLine();
                    System.out.print("ENTER PRODUCT'S ID: ");
                    id=sc.nextLine();
                    System.out.print("ENTER PRODUCT'S PRICE: ");
                    price=sc.nextLine();
                    System.out.print("ENTER PRODUCT'S DISCOUNTED PRICE: ");
                    discountedPrice=sc.nextLine();
                    System.out.print("ENTER PRODUCT'S CATEGORY(ex. FURNITURE >> LIVING ROOM): ");
                    allCategories=sc.nextLine();
                    System.out.print("ENTER PRODUCT'S DESCRIPTION: ");
                    description=sc.nextLine();
                    try {
                        addProduct(name, id, price, discountedPrice, description, this, allCategories);
                        System.out.println("Product is added successfully!");
                    } catch (IOException e) {
                        System.err.println("Product cannot be added,check product id!");
                    }
                    break;
                case 2:
                    System.out.print("ENTER PRODUCT ID YOU WANT TO REMOVE: ");
                    id=sc.nextLine();
                    id=sc.nextLine();
                    try {
                        if (checkOwnership(id)==false) {
                            System.err.println("Product cannot be removed!");
                        }else{
                            removeProduct(id,"products.txt");
                            System.out.println("Product is removed successfully!");
                        }
                    } catch (IOException e) {
                        System.err.println("Product cannot be removed!");
                    }
                    break;
                case 3:
                    System.out.print("ENTER PRODUCT ID YOU WANT TO EDIT: ");
                    id=sc.nextLine();
                    id=sc.nextLine();
                    try {
                        if (checkOwnership(id)) {
                            System.out.print("CHANGE PRODUCT'S NAME: ");
                            name=sc.nextLine();
                            System.out.print("CHANGE PRODUCT'S PRICE: ");
                            price=sc.nextLine();
                            System.out.print("CHANGE PRODUCT'S DISCOUNTED PRICE: ");
                            discountedPrice=sc.nextLine();
                            System.out.print("CHANGE PRODUCT'S CATEGORY(ex. FURNITURE >> LIVING ROOM): ");
                            allCategories=sc.nextLine();
                            System.out.print("CHANGE PRODUCT'S DESCRIPTION: ");
                            description=sc.nextLine(); 
                            editProduct(name, id, price, discountedPrice, description, this, allCategories);
                        }
                        else
                        System.err.println("Product cannot be edited.");
                    } catch (IOException e) {
                        System.err.println("Product cannot be edited.");
                    }
                    break;
                case 4:
                    try {
                        loadOrders();
                        printOrders();
                    } catch (IOException e) {
                        System.err.println("Orders cannot be loaded.");
                    }
                    break;
                case 5:
                    while (exit2) {
                        try {
                            loadOrders();
                            printOrders();
                            System.out.println();
                            System.out.println("1.MEET AN ORDER");
                            System.out.println("2.CANCEL AN ORDER");
                            System.out.println("3.EXIT");
                            System.out.println("SELECTION :");
                            selection=sc.nextInt();
                            switch (selection) {
                                case 1:
                                    System.out.print("ENTER PRODUCT ID YOU WANT TO MEET: ");
                                    id=sc.nextLine();
                                    id=sc.nextLine();
                                    try {
                                        if (checkInTheOrder(id) && checkOwnership(id)) {
                                            removeProduct(id,"orders.txt");
                                            removeProduct(id,"products.txt");
                                        }
                                        else{
                                            System.err.println("Product cannot be met.");
                                        }
                                    } catch (IOException e) {
                                        System.err.println("Product cannot be met.");
                                    }
                                    break;
                                case 2:
                                    System.out.print("ENTER PRODUCT ID YOU WANT TO CANCEL: ");
                                    id=sc.nextLine();
                                    id=sc.nextLine();
                                    try {
                                        removeProduct(id,"orders.txt");
                                    } catch (IOException e) {
                                        System.err.println("Product cannot be canceled.");
                                    }
                                    break;
                                case 3:
                                    exit2=false;
                                    break;
                                default:
                                System.out.println("Wrong command!");
                                    break;
                            }
                        } catch (IOException e1) {
                            System.err.println("Orders cannot be loaded.");
                        }
                    }
                    break;
                case 6:
                    exit=false;
                    break;
                default:
                    break;
            }
        }
    }
    /**
     * Checks the given product is in the order queue or not.
     * @param id product id 
     * @return true if given product is in the queue
     */
    private boolean checkInTheOrder(String id){
        Iterator<Order> iter = orders.iterator();
        while (iter.hasNext()) {
            Order o = iter.next();
            if (o.getProduct().getId().equals(id)) {
                return true;
            }
        }
        return false;
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
        FileWriter traderWriter = new FileWriter("users.txt",true);
        traderWriter.write(getRole()+";");
        traderWriter.write(getName()+";");
        traderWriter.write(getId()+";");
        traderWriter.write(getPassword()+"\n");
        traderWriter.close();
    }
    /**
     * Prints the orders of customers.
     */
    private void printOrders(){
        Iterator<Order> iter = orders.iterator();
        while (iter.hasNext()) {
            Order o = iter.next();
            System.out.println("******************************************************");
            System.out.println("Customer name: "+o.getCustomer().getName()+ " Customer ID: "+ o.getCustomer().getId());
            System.out.println("Product Informations: "+o.getProduct().getId()+";"
            +o.getProduct().getName()+";"
            +o.getProduct().getAllCategories()
            +";"+o.getProduct().getPrice()
            +"; %"+String.format("%.2f", o.getProduct().getDiscountPercentage()));
            System.out.println("******************************************************");
        }
    }
    /**
     * Creates new product and adds it to .txt file.
     * @param name product name
     * @param id product id
     * @param price product price
     * @param discountedPrice product discountedPrice
     * @param description product description
     * @param trader product trader
     * @param allCategories product's categories
     * @throws IOException if the file io fails.
     */
    public void addProduct(String name,String id,String price,String discountedPrice,String description,Trader trader,String allCategories) throws IOException {
        if (checkProductID(id)==true) {
            throw new IOException();
        }
        FileWriter writer = new FileWriter("products.txt",true);
        writer.write(id+";");
        writer.write(name+";");
        writer.write(allCategories+";");
        writer.write(price+";");
        writer.write(discountedPrice+";");
        writer.write(description+";");
        writer.write(trader.getName()+"\n");
        writer.close();
    }
    /**
     * Removes the given product from .txt file.
     * @param id product id
     * @param filePath .txt file
     * @throws IOException if the file io fails.
     */
    public void removeProduct(String id,String filePath) throws IOException {
        File oldProd = new File(filePath);
        File newProd = new File("temp.txt");
        FileWriter productWriter = new FileWriter("temp.txt",false);
        String row;
        if (oldProd.isFile()) {
            BufferedReader prodReader = new BufferedReader(new FileReader(filePath));
            while ((row = prodReader.readLine()) != null) {
                String[] data = row.split(";");
                if (data[0].equals(id)==false) {
                    for (int i = 0; i < data.length; i++) {
                        productWriter.write(data[i]+";");
                    }
                     productWriter.write("\n");
                }
            }
            oldProd.delete();
            newProd.renameTo(oldProd);
            prodReader.close();
            productWriter.close();
        }
    }
    /**
     * Edits the given product by removing and-readding with up to date data.
     */
    public void editProduct(String name,String id,String price,String discountedPrice,String description,Trader trader,String allCategories) throws IOException {
        removeProduct(id,"products.txt");
        addProduct(name, id, price, discountedPrice, description, trader, allCategories);
    }
    /**
     * Checks if the given product is in the file or not.
     * @param input product id
     * @return 
     * @throws IOException if the file io fails.
     */
    private boolean checkProductID(String input) throws IOException {
        File prodFile = new File("products.txt");
        String row;
        if (prodFile.isFile()) {
            BufferedReader prodReader = new BufferedReader(new FileReader("products.txt"));
            while ((row = prodReader.readLine()) != null) {
                String[] data = row.split(";");
                if (data[0].equals(input)) {
                    return true;
                }
            }
            prodReader.close();
        }
        return false;
    }
    /**
     * Checks if given product is in the file and belong to the this trader. 
     * @param input product id.
     * @return true if given product is in the file and belong to this trader.
     * @throws IOException if the file io fails.
     */
    private boolean checkOwnership(String input) throws IOException {
        File prodFile = new File("products.txt");
        String row;
        if (prodFile.isFile()) {
            BufferedReader prodReader = new BufferedReader(new FileReader("products.txt"));
            while ((row = prodReader.readLine()) != null) {
                String[] data = row.split(";");
                if (data[0].equals(input) && data[6].equals(this.getName())) {
                    return true;
                }
            }
            prodReader.close();
        }
        return false;
    }
    /**
     * It loads orders to the order queue from order.txt.
     * @throws IOException if the file io fails.
     */
    public void loadOrders() throws IOException {
        orders = new LinkedList<>();
        File prodFile = new File("orders.txt");
        String row;
        if (prodFile.isFile()) {
            BufferedReader prodReader = new BufferedReader(new FileReader("orders.txt"));
            while ((row = prodReader.readLine()) != null) {
                String[] data = row.split(";");
                if (data[6].equals(this.getName())) {
                    User c = getCompany().findUser(Integer.parseInt(data[7]));
                    orders.add(new Order(new Product(data[1],data[0],Double.parseDouble(data[3]),Double.parseDouble(data[4]),data[5],data[6],data[2]),(Customer) c));
                }
            }
            prodReader.close();
        }
    }
}
