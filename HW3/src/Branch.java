/**
 * Branch Class
 */
public class Branch{
    private HybridList<Product> products;
    private HybridList<Product> needToBeSupplied;
    private MyArrayList<Employee> employees;
    private Company company;
    private String branchName;
    /**
     * Creates branch in the company
     * @param company branch's company
     * @param branchName branch name
     */
    public Branch(Company company,String branchName){
        setCompany(company);
        setBranchName(branchName);
        products = new HybridList<>(5);
        employees = new MyArrayList<>();
        needToBeSupplied=new HybridList<>(5);
    }
    /**
     * Prints informations about branch.
     */
    @Override
    public String toString() {
        return String.format("BRANCH NAME: %s\nNUMBER OF EMPLOYEES: %d\nNUMBER OF PRODUCTS: %d\n",getBranchName(),getEmployees().size(),getProducts().size());
    }

    @Override
    public boolean equals(Object obj) {
        return getBranchName().equals(((Branch)obj).getBranchName());
    }

    public Company getCompany() {
        return this.company;
    }
    /**
     * Lists all employees in the branch.
     */
    public void listEmployees() {
        System.out.println();
        for (int i = 0; i < getEmployees().size(); i++) {
            System.out.println(i+".");
            System.out.println(getEmployees().get(i));
        }
        System.out.println();
    }
    /**
     * Finds employee for given id.
     * @param id Employee's id
     * @return Employee
     */
    public Employee findEmployee(int id) {
        for (int i = 0; i < getEmployees().size(); i++) {
            if (getEmployees().get(i).getEmployeeID()==id) {
                return getEmployees().get(i);
            }
        }
        return null;
    }
    public void setCompany(Company company) {
        this.company = company;
    }

    public String getBranchName() {
        return this.branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public HybridList<Product> getProducts() {
        return this.products;
    }

    public void setProducts(HybridList<Product> products) {
        this.products = products;
    }

    public MyArrayList<Employee> getEmployees() {
        return this.employees;
    }

    public void setEmployees(MyArrayList<Employee> employees) {
        this.employees = employees;
    }

    public HybridList<Product> getNeedToBeSupplied() {
        return this.needToBeSupplied;
    }

    public void setNeedToBeSupplied(HybridList<Product> needToBeSupplied) {
        this.needToBeSupplied = needToBeSupplied;
    }
}
