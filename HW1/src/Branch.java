public class Branch{
    private MyList<Product> products;
    private MyList<Product> needToBeSupplied;
    private MyList<Employee> employees;
    private Company company;
    private String branchName;

    public Branch(Company company,String branchName){
        setCompany(company);
        setBranchName(branchName);
        products = new MyList<>();
        employees = new MyList<>();
        needToBeSupplied=new MyList<>();
    }

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
    public void listEmployees() {
        System.out.println();
        for (int i = 0; i < getEmployees().size(); i++) {
            System.out.println(i+".");
            System.out.println(getEmployees().get(i));
        }
        System.out.println();
    }

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

    public MyList<Product> getProducts() {
        return this.products;
    }

    public void setProducts(MyList<Product> products) {
        this.products = products;
    }

    public MyList<Employee> getEmployees() {
        return this.employees;
    }

    public void setEmployees(MyList<Employee> employees) {
        this.employees = employees;
    }

    public MyList<Product> getNeedToBeSupplied() {
        return this.needToBeSupplied;
    }

    public void setNeedToBeSupplied(MyList<Product> needToBeSupplied) {
        this.needToBeSupplied = needToBeSupplied;
    }
}
