/**
 * Ordered Class
 */
public class Ordered {
    private Product product;

    private int quantity;

    /**
     * Prints the information of specific customer's orders.
     */
    @Override
    public String toString() {
        return String.format("Product Name: %s\nProduct Model: %s\nProduct Color: %s\nQuantity: %s\nProduct Id: %d\n",
        getProduct().getName(),getProduct().getModel(),getProduct().getColor(),getQuantity(),getProduct().getId());
    }

    public Ordered(int quantity){
        setQuantity(quantity);
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
