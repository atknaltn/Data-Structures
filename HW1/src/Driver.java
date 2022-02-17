import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        Company company = new Company("FURNITURE WORLD", "Atakan", "Altın", "admin", "admin");
        company.getBranches().add(new Branch(company,"ISTANBUL"));
        company.getBranches().add(new Branch(company,"IZMIR"));
        company.getBranches().add(new Branch(company,"ESKISEHIR"));
        company.getBranches().add(new Branch(company,"KOCAELI"));
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
                        }
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
                            company.findBranch(branchName).getEmployees().remove(company.findBranch(branchName).getEmployees().get(id-1));
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
