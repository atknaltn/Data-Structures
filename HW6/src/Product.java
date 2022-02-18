/**
 * Product class to store product datas.
 */
import java.util.*;

public class Product implements Comparable<Product>{
    private String name;
    private String id;
    private String description;
    private double price;
    private double discountedPrice;
    private double discountPercentage;
    private String trader;
    private String allCategories;
    private LinkedList<String> category;

    /**
     * Product Constructor.
     */
    public Product(String name,String id,double price,double discountedPrice,String description,String trader,String allCategories) {
        this.name=name;
        this.id=id;
        this.description=description;
        this.price=price;
        this.discountedPrice=discountedPrice;
        this.trader=trader;
        this.discountPercentage=100-((discountedPrice/price)*100);
        this.allCategories=allCategories;
        category=new LinkedList<>();
        loadCategories();
    }
    /**
     * It gives all category string and parse it to individual categories.
     */
    private void loadCategories(){
        String[] tempArr = allCategories.split(" >> ");
        for (int i = 0; i < tempArr.length; i++) {
            category.add(tempArr[i]);
        }
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscountedPrice() {
        return this.discountedPrice;
    }

    public void setDiscountedPrice(double discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public String getTrader() {
        return this.trader;
    }

    public void setTrader(String trader) {
        this.trader = trader;
    }

    public double getDiscountPercentage() {
        return this.discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    
    public String getAllCategories() {
        return this.allCategories;
    }

    public void setAllCategories(String allCategories) {
        this.allCategories = allCategories;
    }

    public LinkedList<String> getCategory() {
        return this.category;
    }

    public void setCategory(LinkedList<String> category) {
        this.category = category;
    }
    /**
     * Compares two products.
     */
    @Override
    public int compareTo(Product o) {
        if (getPrice()>o.getPrice())
            return 1;
        else if (getPrice()<o.getPrice())
            return -1;
        else 
            return 0;
    }
}

