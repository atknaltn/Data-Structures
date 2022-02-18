import java.util.Scanner;

public class Driver {
    public static void test() {
        //testing list structures
        MyArrayList<Integer> list1 = new MyArrayList<>();
        MyLinkedList<String> list2= new MyLinkedList<>();
        HybridList<String> list3 = new HybridList<>(5);

        list1.add(1);
        list1.add(2);
        list1.add(0,3);
        list1.remove(2);
        try {
            list1.remove(45);
        } catch (Exception e) {
            e.printStackTrace();
        }
        list1.indexOf(1);
        try {
            list1.indexOf(21);
        } catch (Exception e) {
            e.printStackTrace();
        }
        list1.get(0);
        try {
            list1.get(74);   
        } catch (Exception e) {
            e.printStackTrace();
        }
        list1.set(0,27);   
        
        list2.addLast("1");
        list2.add(1,"2");
        list2.addFirst("3");
        list2.remove("2");
        try {
            list2.remove("45");   
        } catch (Exception e) {
            e.printStackTrace();
        }
        list2.indexOf("1");
        try {
            list2.indexOf("21");
        } catch (Exception e) {
            e.printStackTrace();
        }
        list2.get(0);
        try {
            list2.get(74);    
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        list3.add("1");
        list3.add("2");
        list3.add(0,"3");
        list3.remove(2);
        try {
            list3.remove(45);   
        } catch (Exception e) {
            e.printStackTrace();
        }
        list3.indexOf("1");
        try {
            list3.indexOf("21");   
        } catch (Exception e) {
            e.printStackTrace();
        }
        list3.get(0);
        try {
            list3.get(74);   
        } catch (Exception e) {
            e.printStackTrace();
        }
        list3.set(0,"27");   
       
        //adding default requirements
        Company company = new Company("test company", "test admin", "test admin", "test admin", "test admin");
        Customer customer1 =new Customer(company, "testcustomer", "testcustomer", "testcustomer", "testcustomer");
        company.getCustomers().add(customer1);
        Branch br1=new Branch(company, "test1");
        Branch br2=new Branch(company, "test2");
        Branch br3=new Branch(company, "test3");
        company.getBranches().addLast(br3);
        company.getBranches().addFirst(br1);
        company.getBranches().add(1,br2);
        Employee emp1=new Employee(company.getBranches().get(0), 
        "testemployee1", "testemployee1", "testemployee1", "testemployee1");
        Employee emp2=new Employee(company.getBranches().get(1), "testemployee2", 
        "testemployee2", "testemployee2", "testemployee2");
        br1.getEmployees().add(emp1);
        br2.getEmployees().add(emp2);

        //trying to remove employee from empty branches.
        try {
            company.getAdmin().removeBranchEmployee(br3, emp1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //tying to find branch that doesn't exist
        try {
            company.findBranch("test4");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //finding existent customer
        company.findCustomer(customer1.getId());
        //trying to find customer that doesn not exist
        company.findCustomer(999);
        //trying to validate users
        try {
            company.validateUser("invalid", "invalid");
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        try {
            company.validateUser(customer1.getEmail(), customer1.getPassword());
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        //admin can add employees
        company.getAdmin().addBranchEmployee(br1, emp2);
        //admin can remove employees
        company.getAdmin().removeBranchEmployee(br1, emp2);
        //trying to remove emp2 again
        company.getAdmin().removeBranchEmployee(br1, emp2);
        //admin can remove employees
        try {
            company.getAdmin().removeBranches(br1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //trying to remove br1 again
        try {
            company.getAdmin().removeBranches(br1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //admin can query product state
        try {
            company.getAdmin().queryProductState();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //admin can list branches
        try {
            System.out.println("listing branches...");
            company.getAdmin().listBranches();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //admin can log into system
        company.getAdmin().logIn("test admin", "test admin");
        //admin may fail when logging into system if their informations are wrong
        company.getAdmin().logIn("id", "password");
        //employees can add products
        emp1.addProduct("testproduct1", "testproduct1", "testproduct1",5);
        emp2.addProduct("testproduct2", "testproduct2", "testproduct2",7);
        //trying to reach product that doesnt existent
        try {
            emp1.findProduct(999);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //employees can inform admin about products 
        Product p1 = new Product("testproduct3", "testproduct3", "testproduct3", 7);
        emp1.informAdmin(p1);
        //employees can inquire products
        emp1.inquireProducts();
        //employees can sale products with given product id
        try {
            emp1.sale(1, 5, customer1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //trying to sell product that doesnt exist
        try {
            emp1.sale(7, 5, customer1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //employees can list all products, it throws exception when there are no products at all.
        try {
            emp1.listProducts();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //employees can remove products
        try {
            emp1.removeProduct(1, 7);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //trying to remove product that doesnt exist
        try {
            emp1.removeProduct(15, 7);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //employees can search products.
        emp1.searchProducts("name", "model", "color", 5);
        //employees can login into the system.
        emp1.logIn("id", "password");
        //customer can buy products
        try {
            customer1.buy(1, 5);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //trying to buy product that doesnt exist.
        try {
            customer1.buy(45, 5);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //customers can login to the system
        customer1.logIn("id", "password");
        //customers can search products
        try {
            customer1.searchProducts("testproduct2", "testproduct2", "testproduct2");
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        //trying to reach product that doesnt exist.
        try {
            customer1.searchProducts("name", "model", "color");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //customers can see list of products. throws exception when there is no product.
        try {
            customer1.seeListOfProducts();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //customers can view their previous orders.throws exception when there is no product.
        try {
            customer1.viewOrderList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //customers can sign up to the system.
        customer1.signUp();
    }
    public static void main(String[] args) {
        test();
        System.out.println();
        System.out.println("ALL EXCEPTIONS ARE HANDLED.");
        Company company = new Company("FURNITURE WORLD", "Atakan", "Altın", "admin", "admin");
        company.getBranches().addLast(new Branch(company,"ISTANBUL"));
        company.getBranches().addLast(new Branch(company,"IZMIR"));
        company.getBranches().addLast(new Branch(company,"ESKISEHIR"));
        company.getBranches().addLast(new Branch(company,"KOCAELI"));
        try {
            company.findBranch("ISTANBUL").getEmployees().add(new Employee(company.getBranches().get(0),"Kuzey","Tekinoglu","kuzey", "123"));
            company.findBranch("IZMIR").getEmployees().add(new Employee(company.getBranches().get(0),"Guney","Tekinoglu","guney", "123"));
            company.findBranch("ESKISEHIR").getEmployees().add(new Employee(company.getBranches().get(0),"Cemre","Cayak","cemre", "123"));
            company.findBranch("KOCAELI").getEmployees().add(new Employee(company.getBranches().get(0),"Banu","Sinaner","banu", "123"));
            company.findBranch("ISTANBUL").getProducts().add(new Product("OFFICE CHAIR","MODEL1","RED",10));
            company.findBranch("ISTANBUL").getProducts().add(new Product("OFFICE CHAIR","MODEL3","BLACK",17));
            company.findBranch("ISTANBUL").getProducts().add(new Product("OFFICE CHAIR","MODEL6","YELLOW",10));
            company.findBranch("ISTANBUL").getProducts().add(new Product("OFFICE CHAIR","MODEL5","BROWN",22));
            company.findBranch("IZMIR").getProducts().add(new Product("BOOKCASE","MODEL1","BLACK",19));
            company.findBranch("IZMIR").getProducts().add(new Product("BOOKCASE","MODEL11","BLACK",56));
            company.findBranch("IZMIR").getProducts().add(new Product("BOOKCASE","MODEL7","BLACK",26));
            company.findBranch("IZMIR").getProducts().add(new Product("BOOKCASE","MODEL3","BLACK",35));
            company.findBranch("ESKISEHIR").getProducts().add(new Product("OFFICE DESK","MODEL2","BROWN",20));
            company.findBranch("ESKISEHIR").getProducts().add(new Product("OFFICE DESK","MODEL3","YELLOW",25));
            company.findBranch("ESKISEHIR").getProducts().add(new Product("OFFICE DESK","MODEL4","BLUE",37));
            company.findBranch("ESKISEHIR").getProducts().add(new Product("OFFICE DESK","MODEL5","LIGHT BLUE",41));
            company.findBranch("KOCAELI").getProducts().add(new Product("MEETING TABLE","MODEL10","WHITE",91));
            company.findBranch("KOCAELI").getProducts().add(new Product("MEETING TABLE","MODEL9","BLUE",89));
            company.findBranch("KOCAELI").getProducts().add(new Product("MEETING TABLE","MODEL8","BLACK",77));
            company.findBranch("KOCAELI").getProducts().add(new Product("MEETING TABLE","MODEL6","BROWN",101));   
        } catch (Exception e) {
            System.out.println("The product cannot be added.");
        }
        company.getCustomers().add(new Customer(company,"Ezel", "Bayraktar", "ezel","123"));
        company.getCustomers().add(new Customer(company,"Ramiz", "Karaeski", "ramiz","123"));
        mainMenu(company);
    }

    public static void mainMenu(Company company) {
        int selection;
        boolean exit=true;
        Scanner scanner = new Scanner(System.in);
        while (exit) {
            System.out.println("/**************** Welcome to " + company.getCompanyName()+" Corporation ****************/");
            System.out.println("1- LOGIN AS CUSTOMER");
            System.out.println("2- LOGIN AS EMPLOYEEE");
            System.out.println("3- LOGIN AS ADMINISTRATOR");
            System.out.println("4- EXIT");
            System.out.print("SELECTION: ");
            selection = scanner.nextInt();
            switch (selection) {
                case 1:
                    customerLogin(company);
                    break;
                case 2:
                    employeeMenu(company);
                    break;
                case 3:
                    adminMenu(company);
                    break;
                case 4:
                    exit=false;
                    break;
                default:        
                    System.out.println("Wrong Selection!, Try Again");
                    break;
            }
        }
    }
    public static void customerLogin(Company company) {
        int selection;
        boolean exit=true;
        Customer customer;
        String name,surname,email,password;
        Scanner scanner=new Scanner(System.in);
        while (exit) {
            System.out.println("1- LOGIN");
            System.out.println("2- SIGN UP");
            System.out.println("3- EXIT");
            System.out.print("SELECTION: ");
            selection=scanner.nextInt();
            switch (selection) {
                case 1:
                    System.out.print("E-MAIL: ");
                    email=scanner.nextLine();
                    email=scanner.nextLine();
                    System.out.print("PASSWORD: ");
                    password=scanner.nextLine();
                    try {
                        customer =(Customer) company.validateUser(email, password);   
                        System.out.println("Welcome "+customer.getName()+ " " + customer.getSurname());
                        customerMenu(customer);
                        exit=false;
                    } catch (Exception e) {
                        System.out.println("E-mail or Password Wrong!!!");
                    }
                    break;
                case 2:
                    System.out.print("NAME: ");
                    name=scanner.nextLine();
                    name=scanner.nextLine();
                    System.out.print("SURNAME: ");
                    surname=scanner.nextLine();
                    System.out.print("E-MAIL: ");
                    email=scanner.nextLine();
                    System.out.print("PASSWORD: ");
                    password=scanner.nextLine();
                    customer = new Customer(company,name, surname, email, password);
                    customer.signUp();
                    System.out.println("Welcome, "+customer.getName()+". Your Customer Id: " + customer.getId());
                    System.out.println("You can login to the system now.");
                    break;
                case 3:
                    exit=false;
                    break;
                default:
                    System.out.println("Wrong Selection!, Try Again");
                    break;
            }
        }
    }
    public static void customerMenu(Customer customer) {
        int selection,wantedID,quantity;
        boolean exit = true;
        String name,model,color;
        Scanner scanner=new Scanner(System.in);
        while (exit) {
            System.out.println("1- SEARCH PRODUCTS");
            System.out.println("2- LIST ALL PRODUCTS");
            System.out.println("3- GIVE AN ORDER (ONLINE)");
            System.out.println("4- VIEW PREVIOUS ORDERS");
            System.out.println("5- EXIT");
            System.out.print("SELECTION: ");
            selection=scanner.nextInt();
            switch (selection) {
                case 1:
                    System.out.print("NAME: ");
                    name=scanner.nextLine();
                    name=scanner.nextLine();
                    System.out.print("MODEL: ");
                    model=scanner.nextLine();
                    System.out.print("COLOR: ");
                    color=scanner.nextLine();
                    try {
                        customer.searchProducts(name, model, color);
                    } catch (Exception e) {
                        System.out.println("The product that you look for could not be found");
                    }
                    break;
                case 2:
                    try {
                        customer.seeListOfProducts();   
                    } catch (Exception e) {
                        System.out.println("There are no products.");
                    }
                    break;
                case 3:
                    try {
                        customer.seeListOfProducts();   
                    } catch (Exception e) {
                        System.out.println("There are no products.");
                    }
                    System.out.print("Please Enter The Product Id That You Want To Buy: ");
                    wantedID=scanner.nextInt();
                    System.out.print("Please Enter The Quantity That You Want To Buy: ");
                    quantity=scanner.nextInt();
                    try {
                        customer.buy(wantedID,quantity);
                        System.out.println("**********The Product Has Been Purchased Succesfully.**********");
                    } catch (Exception e) {
                        System.out.println("**********There Is No Enough Stock.**********");
                    }
                    break;
                case 4:
                    try {
                        customer.viewOrderList();
                    } catch (Exception e) {
                        System.out.println("There Are no orders right now.");
                    }
                    break;
                case 5:
                    exit=false;
                    break;
                default:
                    System.out.println("Wrong Selection!, Try Again");
                    break;
            }
        }
    }
    public static void employeeMenu(Company company) {
        int selection,stock,wantedID,quantity,customerID;
        boolean exit=true;
        String email,password;
        Customer customer = null;
        Employee employee = null;
        Scanner scanner = new Scanner(System.in);
        String name,surname,model,color;
        while(exit){
            System.out.print("E-MAIL: ");
            email=scanner.nextLine();
            System.out.print("PASSWORD: ");
            password=scanner.nextLine();
            employee=new Employee(company.getBranches().get(0),"", "", email, password);
            try {
                employee =(Employee) company.validateUser(email, password);   
                System.out.println("Welcome "+employee.getName()+ " , " + employee.getSurname());
                exit=false;
            } catch (Exception e) {
                System.out.println("E-mail or Password Wrong!!!");
            }
        }
        exit = true;
        while (exit) {
            System.out.println("1- ADD PRODUCT");
            System.out.println("2- REMOVE PRODUCT");
            System.out.println("3- SALE");
            System.out.println("4- INQUIRE ALL PRODUCTS");
            System.out.println("5- INFORM ADMIN FOR MISSING PRODUCTS");
            System.out.println("6- EXIT");
            System.out.print("SELECTION: ");
            selection=scanner.nextInt();
            switch (selection) {
                case 1:
                    System.out.print("NAME: ");
                    name=scanner.nextLine();
                    name=scanner.nextLine();
                    System.out.print("MODEL: ");
                    model=scanner.nextLine();
                    System.out.print("COLOR: ");
                    color=scanner.nextLine();
                    System.out.print("STOCK: ");
                    stock=scanner.nextInt();
                    try {
                        employee.addProduct(name,model,color,stock);
                    } catch (Exception e) {
                        System.out.println("The product cannot be added.");
                    }
                    break;
                case 2:
                    try {
                        employee.listProducts();
                        System.out.print("Please Enter The Product Id That You Want To Remove: ");
                        wantedID=scanner.nextInt();
                        System.out.print("Please Enter The Quantity That You Want To Remove: ");
                        quantity=scanner.nextInt();
                        try {
                            employee.removeProduct(wantedID, quantity);   
                        } catch (Exception e) {
                            System.out.println("The product cannot be removed.");
                        }
                    } catch (Exception e) {
                        System.out.println("There are no products right now.");
                    }
                    break;
                case 3:
                    try {
                        employee.listProducts();
                        System.out.print("Please Enter The Product Id That You Want To Sale: ");
                        wantedID=scanner.nextInt();
                        System.out.print("Please Enter The Quantity That You Want To Sale: ");
                        quantity=scanner.nextInt();
                        System.out.println();
                        System.out.println("*******************************");
                        for (int i = 0; i < company.getCustomers().size(); i++) {
                            System.out.println("Customer Name: "+company.getCustomers().get(i).getName()+ " Customer ID: "+company.getCustomers().get(i).getId());
                        }
                        System.out.println("*******************************");
                        System.out.println();
                        System.out.println("Enter the Customer Id that you want to sale him/her(Enter -1 to Subscribe a new Customer): ");
                        customerID=scanner.nextInt();
                        if (customerID==-1) {
                            System.out.print("NAME: ");
                            name=scanner.nextLine();
                            name=scanner.nextLine();
                            System.out.print("SURNAME: ");
                            surname=scanner.nextLine();
                            System.out.print("E-MAIL: ");
                            email=scanner.nextLine();
                            System.out.print("PASSWORD: ");
                            password=scanner.nextLine();
                            customer = new Customer(company,name, surname, email, password);
                            customer.signUp();
                            customerID=customer.getId();
                        }
                        else
                            customer=company.findCustomer(customerID);
                        if (company.findCustomer(customerID)==null) {
                            System.out.println("Wrong Customer ID");
                        }else{
                            employee.sale(wantedID, quantity, company.findCustomer(customerID));
                        }
                    } catch (Exception e) {
                        System.out.println("Something went wrong when try to sale.");
                    }
                    break;
                case 4:
                    employee.inquireProducts();
                    break;
                case 5:
                    employee.inquireProducts();
                    System.out.print("ENTER THE PRODUCT ID THAT YOU WANT TO INFORM ADMIN ABOUT THE LACK OF PRODUCTS: ");
                    wantedID=scanner.nextInt();
                    try {
                        employee.informAdmin(employee.findProduct(wantedID)); //try catch
                    } catch (Exception e) {
                        System.out.println("The product that you look for cannot be found.");
                    }
                    break;
                case 6:
                    exit = false;
                    break;
                default:
                    break;
            }
        }
    }
    public static void adminMenu(Company company) {
        int selection,id;
        boolean exit = true;
        String branchName,name,surname,email,password;
        Admin admin=null;
        Scanner scanner = new Scanner(System.in);
        while (exit) {
            while(exit){
                System.out.print("E-MAIL: ");
                email=scanner.nextLine();
                System.out.print("PASSWORD: ");
                password=scanner.nextLine();
                admin=new Admin(company,"", "", email, password);
                try {
                    admin =(Admin)company.validateUser(email, password);
                    System.out.println("Welcome "+admin.getName()+ " , " + admin.getSurname());
                    exit=false;
                } catch (Exception e) {
                    System.out.println("E-mail or Password Wrong!!!");
                } //phone adress eksik
            }
        }
        exit = true;
        while (exit) {
            System.out.println("1- ADD A BRANCH");
            System.out.println("2- REMOVE  A BRANCH");
            System.out.println("3- ADD AN EMPLOYEE");
            System.out.println("4- REMOVE AN EMPLOYEE");
            System.out.println("5- QUERY PRODUCT STATE");
            System.out.println("6- EXIT");
            System.out.print("SELECTION: ");
            selection=scanner.nextInt();
            switch (selection) {
                case 1:
                    System.out.println("NAME: ");
                    name=scanner.nextLine();
                    try {
                        admin.addBranches(new Branch(company,name));
                    } catch (Exception e) {
                        System.out.println("Branch cannot be added.");
                    }
                    break;
                case 2:
                    try {
                        admin.listBranches();
                        System.out.print("ENTER THE NAME OF BRANCH THAT YOU WANT TO REMOVE: ");
                        name=scanner.nextLine();
                        name=scanner.nextLine();
                        Branch branch = new Branch(company,name);
                        try {
                            admin.removeBranches(branch);     
                        } catch (Exception e) {
                            System.out.println("Branch cannot be removed.");
                        } 
                    } catch (Exception e) {
                        System.out.println("There are no branches.");
                    }
                    break;
                case 3:
                    try {
                        admin.listBranches();
                        System.out.print("ENTER THE NAME OF BRANCH THAT YOU WANT TO ADD AN EMPLOYEE: ");
                        branchName=scanner.nextLine();
                        branchName=scanner.nextLine();
                        if (company.findBranch(branchName)!=null) {
                            System.out.println("ENTER THE EMPLOYEE INFORMATION: ");
                            System.out.print("NAME: ");
                            name=scanner.nextLine();
                            System.out.print("SURNAME: ");
                            surname=scanner.nextLine();
                            System.out.print("E-MAIL: ");
                            email=scanner.nextLine();
                            System.out.print("PASSWORD: ");
                            password=scanner.nextLine();
                            try {
                                company.findBranch(branchName).getEmployees().add(new Employee(company.findBranch(branchName), name, surname, email, password));
                            } catch (Exception e) {
                                System.out.println("Branch that you enter cannot be found");
                            }
                        }
                        else 
                            System.out.println("Warning");
                    } catch (Exception e) {
                        System.out.println("There are no branches.");
                    }
                    break;
                case 4:
                    try {
                        admin.listBranches();
                        System.out.print("ENTER THE NAME OF BRANCH THAT YOU WANT TO REMOVE AN EMPLOYEE: ");
                        branchName=scanner.nextLine();
                        branchName=scanner.nextLine();
                        try {
                            company.findBranch(branchName).listEmployees();
                            System.out.print("ENTER THE EMPLOYEE ID THAT YOU WANT TO FIRE: ");
                            id=scanner.nextInt();
                            company.findBranch(branchName).getEmployees().remove(company.findBranch(branchName).getEmployees().indexOf(company.findBranch(branchName).getEmployees().get(id-1)));
                        } catch (Exception e) {
                            System.out.println("There are no such a branch.");
                        }
                    } catch (Exception e) {
                        System.out.println("There are no branches right now.");
                    }
                    break;
                case 5:
                    System.out.println("HERE IS THE MISSING PRODUCTS: ");
                    try {
                        admin.queryProductState();
                    } catch (Exception e) {
                        System.out.println("There are no product that need to be supplied");
                    }
                    break;
                case 6:
                    exit=false;
                    break;
                
                default:
                    System.out.println("Wrong Selection");
                    break;
            }
        }
    }
}
