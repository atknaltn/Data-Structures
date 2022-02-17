public class Employee extends User implements EmployeeInterface{
    private Branch branch;
    private int employeeID;
    private static int id=0;

    public Employee(Branch branch,String name, String surname,String email,String password) {
        super(name, surname, email,password);
        setBranch(branch);
        setEmployeeID(++id);
    }

    @Override
    public String toString() {
        return String.format("Employee Name: %s %s\nEmployee Branch: %s\nEmployee ID: %d\n",getName(),getSurname(),getBranch().getBranchName(),getEmployeeID());
    }
    @Override
    public void inquireProducts() {
        try {
            listProducts();   
        } catch (Exception e) {
            System.out.println("THERE ARE NO PRODUCTS RIGHT NOW");
        }
    }

    @Override
    public void informAdmin(Product product) throws Exception{
        if (!branch.getNeedToBeSupplied().add(product)) {
            throw new Exception();
        }
    }

    @Override
    public void addProduct(String name,String model,String color,int stock)throws Exception{  
        Product tempProduct = new Product(name, model, color, stock);
        if (!searchProducts(name, model, color, stock)) {
            if (!branch.getProducts().add(tempProduct)) {
                throw new Exception();
            }
        }
    }

    @Override
    public boolean removeProduct(int wantedID,int quantity)throws Exception {
        for (int j = 0; j < branch.getProducts().size(); j++) {
            if (branch.getProducts().get(j).getId()==wantedID) {
                if (branch.getProducts().get(j).getStock()>=quantity) {
                    branch.getProducts().get(j).decreaseStock(quantity);
                    return true;
                }
            }
        }
        throw new Exception();
    }
    
    @Override
    public void sale(int wantedID,int quantity,Customer customer)throws Exception{
        try {
            removeProduct(wantedID, quantity);   
        } catch (Exception e) {
            System.out.println("There are not enough product to be removed.");
        }
        Ordered tempOrdered = new Ordered(quantity);
        tempOrdered.setProduct(findProduct(wantedID));
        if (!customer.getPreviousOrders().add(tempOrdered)) {
            throw new Exception();
        }
    }

    public boolean searchProducts(String name,String model,String color,int stock) {
        Product tempProduct = new Product(name,model,color,stock);
        for (int i = 0; i < branch.getProducts().size(); i++) {
            if (branch.getProducts().get(i).equals(tempProduct)) {
                branch.getProducts().get(i).setStock(branch.getProducts().get(i).getStock()+stock);
                return true;
            }
        }
        return false;
    }

    public Product findProduct(int productID) throws Exception{
        if (branch.getProducts().size()==0) {
            throw new Exception();
        }
        for (int i = 0; i < branch.getProducts().size();i++) {
            if (branch.getProducts().get(i).getId()==productID){
                return branch.getProducts().get(i);
            }            
        }
        throw new Exception();
    }

    public void listProducts() throws Exception{    
        if (branch.getProducts().size()==0) {
            throw new Exception();
        }
        for (int i = 0; i < branch.getProducts().size(); i++) {
            System.out.println(branch.getProducts().get(i));
        }
    }
    public Branch getBranch() {
        return this.branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public int getEmployeeID() {
        return this.employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }
}
