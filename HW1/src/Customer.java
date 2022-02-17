public class Customer extends User implements CustomerInterface{
    private String phone;
    private String address;
    private Company company;
    private static int customerID=0;
    private int id;
    private MyList<Ordered> previousOrders;

    public Customer(Company company,String name, String surname,String email,String password) {
        super(name, surname, email,password);
        setCompany(company);
        setId(++customerID);
        previousOrders=new MyList<>();
    }
    
    public void signUp(){

        try {
            getCompany().findCustomer(this.getId());
            getCompany().getCustomers().add(this);
        } catch (Exception e) {
            System.out.println("YOU ARE ALREADY REGISTERED USER");
        }
    }
    @Override
    public boolean searchProducts(String name,String model,String color) throws Exception{
        Product tempProduct = new Product(name,model,color,0);
        for (int i = 0; i < getCompany().getBranches().size(); i++) {
            for (int j = 0; j < getCompany().getBranches().get(i).getProducts().size(); j++) {
                if (getCompany().getBranches().get(i).getProducts().get(j).equals(tempProduct)) {
                    System.out.println("*************************");
                    System.out.println();
                    System.out.println(getCompany().getBranches().get(i).getProducts().get(j));
                    System.out.println("*************************");
                    return true;
                }
            }
        }
        throw new Exception();
    }
    @Override
    public void seeListOfProducts() throws Exception{
        if ( getCompany().getBranches().size()==0) {
            throw new Exception();
        }else{
            for (int i = 0; i < getCompany().getBranches().size(); i++) {
                for (int j = 0; j < getCompany().getBranches().get(i).getProducts().size(); j++) {
                    System.out.println(getCompany().getBranches().get(i).getProducts().get(j));
                }
            } 
        }
    }
    @Override
    public boolean buy(int wantedID,int quantity)throws Exception{
        Ordered tempOrdered = new Ordered(quantity);
        for (int i = 0; i < getCompany().getBranches().size(); i++) {
            for (int j = 0; j < getCompany().getBranches().get(i).getProducts().size(); j++) {
                if (getCompany().getBranches().get(i).getProducts().get(j).getId()==wantedID) {
                    if (getCompany().getBranches().get(i).getProducts().get(j).getStock()>=quantity) {
                        getCompany().getBranches().get(i).getProducts().get(j).decreaseStock(quantity);
                        tempOrdered.setProduct(getCompany().getBranches().get(i).getProducts().get(j));
                        this.getPreviousOrders().add(tempOrdered);
                        return true;
                    }
                }
            }
        }
        throw new Exception();
    }
    @Override
    public void viewOrderList() throws Exception{
        if (getPreviousOrders().size()==0) {
            throw new Exception();
        }else{
            System.out.println("Customer Name: " + getName() + " " + getSurname() + "   " + "Customer Id: " + getId());
            for (int i = 0; i < getPreviousOrders().size(); i++) {
                System.out.println(getPreviousOrders().get(i));
            }
        }
    }
    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Company getCompany() {
        return this.company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public MyList<Ordered> getPreviousOrders() {
        return this.previousOrders;
    }

    public void setPreviousOrders(MyList<Ordered> previousOrders) {
        this.previousOrders = previousOrders;
    }
}