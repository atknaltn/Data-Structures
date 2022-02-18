/**
 * Company class to store significant datas.
 */
import java.io.*;
import java.util.*;

public class Company {  
    private String companyName;
    HashMap<Integer,User> allUsers;
    ArrayList<Product> products;

    /**
     * Company constuctor
     * @param companyName Company's name
     * @param filePath .csv file name
     */
    public Company(String companyName,String filePath){
        this.companyName=companyName;
        this.allUsers=new HashMap<>();
        products=new ArrayList<>();
        try {
            loadUsers();
        } catch (Exception e) {
            System.err.println("User Data cannot be loaded.");
        }
        try {
            convertCSV(filePath);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("CSV file cannot be opened!");
        }
    }
    /**
     * Main menu.
     */
    public void menu() {
        boolean exit=true;
        int selection;
        String id,password,name;
        while (exit) {
            System.out.println("*************** Welcome to "+getCompanyName()+ " ***************");
            System.out.println("1.LOGIN");
            System.out.println("2.REGISTER");
            System.out.println("3.EXIT");
            System.out.print("SELECTION: ");
            Scanner sc = new Scanner(System.in);
            selection=sc.nextInt();
            switch (selection) {
                case 1:
                    System.out.println();
                    System.out.print("ID: ");
                    id=sc.nextLine();
                    id=sc.nextLine();
                    System.out.print("PASSWORD: : ");
                    password=sc.nextLine();
                    User u = findUser(Integer.parseInt(id));
                    if (u!=null && u.getPassword().equals(password)) {
                        u.menu();
                    }
                    else
                        System.out.println("Wrong ID or Password!");
                    break;
                case 2:
                    boolean exit2=true;
                    while (exit2) {
                        System.out.println();
                        System.out.println("REGISTER AS...");
                        System.out.println("1.CUSTOMER");
                        System.out.println("2.TRADER");
                        System.out.print("SELECTION: ");
                        selection=sc.nextInt();
                        System.out.println();
                        System.out.print("NAME: ");
                        name=sc.nextLine();
                        name=sc.nextLine();
                        System.out.print("PASSWORD: ");
                        password=sc.nextLine();
                        switch (selection) {
                            case 1:
                                try {
                                    System.out.println("YOUR ID: "+addCustomer(name, password).getId());
                                    System.out.println("Please LOGIN with your id and password...");
                                    exit2=false;
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 2:
                                try {
                                    System.out.println("YOUR ID: "+addTrader(name, password).getId());
                                    System.out.println("Please LOGIN with your id and password...");
                                    exit2=false;
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            default:
                                System.err.println("WRONG COMMAND!");
                                break;
                        }
                    }
                    break;
                case 3:
                    exit=false;
                    break;
                default:
                    System.err.println("WRONG COMMAND!");
                    break;
            }
        }
    }
    /**
     * This function adds new customer. Each custımer has a unique id.
     * @param name customer's name
     * @param password customer's password
     * @return Added user.
     * @throws Exception
     */
    public User addCustomer(String name,String password) throws Exception {
        if (password.length()<8) {
            throw new Exception("Password length must be at least 8 characters.");
        }
        Customer c = new Customer(name, password,"Customer",this);
        allUsers.put(c.getId(), c);
        c.writeFile();
        return c;
    }

    /**
     * This function adds new trader. Each trader has a unique id.
     * @param name trader's name
     * @param password trader's password
     * @return Added user.
     * @throws Exception
     */
    public User addTrader(String name,String password) throws Exception {
        if (password.length()<8) {
            throw new Exception("Password length must be at least 8 characters.");
        }
        Trader t = new Trader(name, password,"Trader",this);
        allUsers.put(t.getId(), t);
        t.writeFile();
        return t;
    }
    /**
     * Finds user with the given id.
     * @param userID Users id.
     * @return Found user.
     */
    public User findUser(Integer userID) {
        return allUsers.get(userID);
    }
    /**
     * Finds trader by using given name.
     * @param name traders name
     * @return trader
     */
    protected boolean findTraderByName(String name){
        for (Integer key: allUsers.keySet()) {
            if (allUsers.get(key).getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
    /**
     * This fuction loads users from text file. If the users already exists in hasmap, it will not be added.
     * @throws IOException when the file io fails
     */
    public void loadUsers() throws IOException {
        File userFile = new File("users.txt");
        String row;
        if (userFile.isFile()) {
            BufferedReader userReader = new BufferedReader(new FileReader("users.txt"));
            while ((row = userReader.readLine()) != null) {
                    String[] data = row.split(";");
                    if (data[0].equals("Customer")) {
                        if (findUser(Integer.parseInt(data[2]))==null) {
                            Customer cTemp=new Customer(data[1],data[3],data[0],this);
                            cTemp.setId(Integer.parseInt(data[2]));
                            allUsers.put(Integer.parseInt(data[2]),cTemp);
                        }
                    }
                    else if (data[0].equals("Trader")) {
                        if (findUser(Integer.parseInt(data[2]))==null) {
                            Trader tTemp=new Trader(data[1],data[3],data[0],this);
                            tTemp.setId(Integer.parseInt(data[2]));
                            allUsers.put(Integer.parseInt(data[2]),tTemp);
                        }
                    }
                    else{
                        System.err.println("File structure is broken!");
                        throw new NoSuchElementException();
                    }
            }
            userReader.close();
        }
    }
    /**
     * This function converts .csv file to .txt file well-ordered for products.
     * @param filepath .csv file name
     * @throws IOException when the file io fails
     */
    public void convertCSV(String filepath) throws IOException{
        File csvFile = new File(filepath);
        FileWriter productWriter = new FileWriter("products.txt",false);
        String row;
        int count=0;
        String substr = "";
        if (csvFile.isFile()) {
            BufferedReader csvReader = new BufferedReader(new FileReader(filepath));
            while ((row = csvReader.readLine()) != null) {
                if (count!=0) {
                    String[] data = row.split(";");
                    for (int i = 0; i < data.length; i++) {
                        if (i==6) {
                            if (findTraderByName(data[i])==false) {
                                try {
                                    addTrader(data[i],"12345678");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        if (i==2) {
                            substr=data[i].substring(4,data[i].length()-4);
                            productWriter.write(substr+";");
                        }
                        else
                            productWriter.write(data[i]+";");
                    }
                    productWriter.write("\n");
                }
                count++;
            }
            csvReader.close();
            productWriter.close();
        }
    }
    /**
     * This function loads products which customer have searched.
     * @param input search word
     * @throws IOException when the file io fails
     */
    public void loadProducts(String input) throws IOException{
        File prodFile = new File("products.txt");
        String row;
        if (prodFile.isFile()) {
            BufferedReader prodReader = new BufferedReader(new FileReader("products.txt"));
            while ((row = prodReader.readLine()) != null) {
                String[] data = row.split(";");
                if (data[1].contains(input) && data[5].contains(input)) {
                    products.add(new Product(data[1],data[0],Double.parseDouble(data[3]),Double.parseDouble(data[4]),data[5],data[6],data[2]));
                }
            }
            prodReader.close();
        }
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    
}
