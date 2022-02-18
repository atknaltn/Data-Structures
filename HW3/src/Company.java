/**
 * Company Class
 */
public class Company {  
    private MyLinkedList<Branch> branches;
    private MyArrayList<Customer> customers; 
    private Admin admin;
    private String companyName;
    /**
     * It creates a company with an admin.
     * @param companyName Company's name
     * @param managerName Admin's name
     * @param managerSurname Admin's surname
     * @param managerEmail Admin's email
     * @param managerPassword Admin's password
     */
    public Company(String companyName,String managerName,String managerSurname,String managerEmail,String managerPassword) {
        branches = new MyLinkedList<>();
        customers = new MyArrayList<>();
        setCompanyName(companyName);
        admin = new Admin(this,managerName,managerSurname,managerEmail,managerPassword);
    }
    /**
     * Checks wheather there is such a user in the system.
     * @param email User's email
     * @param password User's password
     * @return User
     * @throws Exception If there is no such a user in the system. 
     */
    public User validateUser(String email,String password) throws Exception{
        Admin tempAdmin= new Admin(this,"","", email, password);
        Customer tempCustomer = new Customer(this,"","",email,password);
        Employee tempEmployee = new Employee(this.getBranches().get(0),"","", email, password);
        try {
            if (getAdmin().equals(tempAdmin)) {
                return getAdmin();
            }
            else if(getCustomers().indexOf(tempCustomer)!=-1){
                return getCustomers().get(getCustomers().indexOf(tempCustomer));
            }
            else{
                for (int i = 0; i < getBranches().size(); i++) {
                    if (getBranches().get(i).getEmployees().indexOf(tempEmployee)!=-1) {
                        return getBranches().get(i).getEmployees().get(getBranches().get(i).getEmployees().indexOf(tempEmployee));
                    }
                }
            }   
        } catch (Exception e) {
            System.out.println("Email or Password Wrong!");
        }
        throw new Exception();
    }
    /**
     * Finds customer for given id
     * @param customerID Customer's id.
     * @return Customer
     */
    public Customer findCustomer(int customerID){
        for (int i = 0; i < getCustomers().size(); i++) {
            if (getCustomers().get(i).getId()==customerID) {
                return getCustomers().get(i);
            }
        }
        return null;
    }
    /**
     * Finds branch for given name.
     * @param name Branch's name.
     * @return branch
     * @throws Exception If there is no such a branch.
     */
    public Branch findBranch(String name) throws Exception{
        for (int i = 0; i < getBranches().size(); i++) {
            if (getBranches().get(i).getBranchName().equals(name))
                return getBranches().get(i);
            }
        throw new Exception();
    }

    public MyLinkedList<Branch> getBranches() {
        return this.branches;
    }

    public void setBranches(MyLinkedList<Branch> branches) {
        this.branches = branches;
    }

    public MyArrayList<Customer> getCustomers() {
        return this.customers;
    }

    public void setCustomers(MyArrayList<Customer> customers) {
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