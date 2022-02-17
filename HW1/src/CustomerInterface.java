public interface CustomerInterface {
    public boolean searchProducts(String name,String model,String color) throws Exception;
    public void seeListOfProducts() throws Exception;
    public boolean buy(int wantedID,int quantity)throws Exception;
    public void viewOrderList() throws Exception;
}
