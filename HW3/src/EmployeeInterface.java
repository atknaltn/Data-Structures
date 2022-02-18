/**
 * Employee Interface
 */
public interface EmployeeInterface {
    public void inquireProducts();
    public void informAdmin(Product product)throws Exception;
    public void addProduct(String name,String model,String color,int stock)throws Exception;
    public boolean removeProduct(int wantedID,int quantity)throws Exception;
    public void sale(int wantedID,int quantity,Customer customer)throws Exception;
}
