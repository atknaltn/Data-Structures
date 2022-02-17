public class Product{

    private int stock;
    private static int productID=0;
    private int id;
    private String name;
    private String model;
    private String color;

    public Product(String name,String model,String color,int stock){
        setName(name);
        setModel(model);
        setColor(color);
        setStock(stock);
        setId(++productID);
    }
    @Override
    public String toString() {
        return String.format("Product Name: %s\nProduct Model: %s\nProduct Color: %s\nStock: %d\nProduct Id: %d\n",
        getName(),getModel(),getColor(),getStock(),getId());
    }

    @Override
    public boolean equals(Object obj) {
        return getName().equals(((Product)obj).getName()) && getModel().equals(((Product)obj).getModel()) 
        && getColor().equals(((Product)obj).getColor());
    }  
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return this.stock;
    }

    public void increaseStock(int quantity) {
        setStock(getStock()+quantity);
    }
    public void decreaseStock(int quantity) {
        setStock(getStock()-quantity);
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}