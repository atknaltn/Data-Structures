/**
 * Order class to store customer and product information.
 */
public class Order {
    private Product product;
    private Customer customer;

    public Order(Product product,Customer customer){
        setProduct(product);
        setCustomer(customer);
    }
    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}
