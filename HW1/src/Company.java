public class Company {  
    private MyList<Branch> branches;
    private MyList<Customer> customers; 
    private Admin admin;
    private String companyName;

    public Company(String companyName,String managerName,String managerSurname,String managerEmail,String managerPassword) {
        branches = new MyList<>();
        customers = new MyList<>();
        setCompanyName(companyName);
        admin = new Admin(this,managerName,managerSurname,managerEmail,managerPassword);
    }

    public User validateUser(String email,String password) throws Exception{
        Admin tempAdmin= new Admin(this,"","", email, password);
        Customer tempCustomer = new Customer(this,"","",email,password);
        Employee tempEmployee = new Employee(this.getBranches().get(0),"","", email, password);
        try {
            if (getAdmin().equals(tempAdmin)) {
                return getAdmin();
            }
            else if(getCustomers().contains(tempCustomer)){
                return getCustomers().get(getCustomers().findIndex((tempCustomer)));
            }
            else{
                for (int i = 0; i < getBranches().size(); i++) {
                    if (getBranches().get(i).getEmployees().contains(tempEmployee)) {
                        return getBranches().get(i).getEmployees().get(getBranches().get(i).getEmployees().findIndex(tempEmployee));
                    }
                }
            }   
        } catch (Exception e) {
            System.out.println("Email or Password Wrong!");
        }
        throw new Exception();
    }

    public Customer findCustomer(int customerID) throws Exception{
        for (int i = 0; i < getCustomers().size(); i++) {
            if (getCustomers().get(i).getId()==customerID) {
                return getCustomers().get(i);
            }
        }
        throw new Exception();
    }

    public Branch findBranch(String name) throws Exception{
        for (int i = 0; i < getBranches().size(); i++) {
            if (getBranches().get(i).getBranchName().equals(name))
                return getBranches().get(i);
            }
        throw new Exception();
    }

    public MyList<Branch> getBranches() {
        return this.branches;
    }

    public void setBranches(MyList<Branch> branches) {
        this.branches = branches;
    }

    public MyList<Customer> getCustomers() {
        return this.customers;
    }

    public void setCustomers(MyList<Customer> customers) {
        this.customers = customers;
    }

    public Admin getAdmin() {
        return this.admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
    public String getCompanyName() {
        return this.companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}