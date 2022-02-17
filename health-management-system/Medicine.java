/**
* A class of Medicine for the Prescriptions
*/
public class Medicine {
    private String name;
    private int cost;
    private int quantiy;
    private static int staticId = 10000;
    private int id;

    public Medicine(int id, String name, int quantiy,int cost){
        this.name=name;
        this.quantiy=quantiy;
        this.cost=cost;
        this.id=id;
    }

    public Medicine(String name, int cost){
        this.name=name;
        this.cost=cost;
        this.quantiy=0;
        this.id=staticId;
        staticId++;
    }

    @Override
    public String toString() {
        return String.format("\nName: %s\nMedicine ID: %d\nCost: %d\nQuantity: %d\n",name,id,cost,quantiy);
    }

    public void printDiscountedPrice(){
        System.out.printf("\nName: %s\nMedicine ID: %d\nCost: %d\nQuantity: %d\n",name,id,cost/2,quantiy);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return this.cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getQuantity() {
        return this.quantiy;
    }

    public void setQuantity(int quantiy) {
        this.quantiy = quantiy;
    }

    public int getId() {
        return this.id;
    }
}
