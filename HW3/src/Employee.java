/**
 * Employee Class
 */
public class Employee extends User implements EmployeeInterface{
    private Branch branch;
    private int employeeID;
    private static int id=0;
    /**
     * Creates employee in a specific branch
     * @param branch branch
     * @param name Employee's name
     * @param surname Employee's surname
     * @param email Employee's email
     * @param password Employee's password
     */
    public Employee(Branch branch,String name, String surname,String email,String password) {
        super(name, surname, email,password);
        setBranch(branch);
        setEmployeeID(++id);
    }
    /**
     * Prints employee's informations.
     */
    @Override
    public String toString() {
        return String.format("Employee Name: %s %s\nEmployee Branch: %s\nEmployee ID: %d\n",getName(),getSurname(),getBranch().getBranchName(),getEmployeeID());
    }
    /**
     * Provides employees to see product states.
     */
    @Override
    public void inquireProducts() {
        try {
            listProducts();   
        } catch (Exception e) {
            System.out.println("THERE ARE NO PRODUCTS RIGHT NOW");
        }
    }
    /**
     * Provides employees to inform the manager for products to need to be supplied.
     */
    @Override
    public void informAdmin(Product product){
        branch.getNeedToBeSupplied().add(product);
    }
    /**
     * Adds product to the inventory.
     */
    @Override
    public void addProduct(String name,String model,String color,int stock){  
        Product tempProduct = new Product(name, model, color, stock);
        if (!searchProducts(name, model, color, stock)) {
            branch.getProducts().add(tempProduct);
        }
    }
    /**
     * Removes product from the inventory.
     */
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
    /**
     * Provides employees to sell products to specific customers.
     * @param wantedID Product id
     * @param quantity Product quantity.
     * @param customer Customer to sell product.
     */
    @Override
    public void sale(int wantedID,int quantity,Customer customer)throws Exception{
        try {
            removeProduct(wantedID, quantity);   
        } catch (Exception e) {
            System.out.println("There are not enough product to be removed.");
        }
        Ordered tempOrdered = new Ordered(quantity);
        tempOrdered.setProduct(findProduct(wantedID));
        customer.getPreviousOrders().add(tempOrdered);
    }
    /**
     * Provides employees to find specific product.
     * @param name product name
     * @param model product model
     * @param color product color
     * @param stock product stock
     * @return
     */
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
    /**
     * Provides employees to find products with their id.
     * @param productID product id
     * @return Product
     * @throws Exception If there is no such a product.
     */
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
    /**
     * Provides Employees to see all product in their branch.
     * @throws Exception If there is no product in the branch inventory.
     */
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
