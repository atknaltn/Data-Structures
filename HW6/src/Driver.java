import java.io.IOException;
import java.util.ArrayList;

/**
 * Driver class to test all functionalities.
 */
public class Driver {
    public static void main(String[] args) {
        test();
        System.out.println();
        System.out.println("UNIT TEST SUCCESFULLY COMPLETE!");
        System.out.println();
        Company company = new Company("Hepsiburada","e-commerce-samples.csv");
        company.menu();
    }
    public static void test() {
        
        System.out.println("Company is created...");
        Company c1 = new Company("test", "e-commerce-samples.csv");
        Customer c = new Customer("Atakan", "123", "Customer", c1);
        Trader t = new Trader("AAA","BBBB","Trader",c1);
        System.out.println("Registering as a customer with invalid password.");
        try {
            c1.addCustomer("Atakan", "Altin");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Registering as a trader with invalid password.");
        try {
            c1.addTrader("Atakan","asfdas");
        } catch (Exception e) {
            e.printStackTrace();
        }
        c.login();
        System.out.println("Searching product by meaningless name.");
        try {
            c1.loadProducts("sfadsa");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Searching product by meaningly name.");
        try {
            c1.loadProducts("Sofa");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Sorting products with all different algorithms.");
        c.sortByName();;
        c.sortByDiscount();
        c.sortByPrice();
        System.out.println("Try to buy product with invalid id");
        try {
            c.giveAnOrder("sadas",new ArrayList<Product>());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Try to buy product with valid id");
        try {
            c.giveAnOrder("ACCEGH6SFSXZA5XA",new ArrayList<Product>());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Try to filtering results by meaningless category");
        c.filterByCategory("asdffa");
        System.out.println("Entering invalid trader name when searching trader’s products.");
        try {
            c.loadTradersProducts("vsadasfda");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Try to add product with non-existing id.");
        try {
            t.addProduct("dsasda", "asdsa", "asdsa", "eqwwq", "hfd", t, "fasdsada");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Try to add product with existing id.");
        try {
            t.addProduct("dsasda", "ACCEGH6RPWY9DHAB", "asdsa", "eqwwq", "hfd", t, "fasdsada");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(" Try to remove product with non-existing id.");
        try {
            t.removeProduct("asfsa", "products.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Trying to remove product belong to another trader.");
        try {
            t.removeProduct("klsafjlfksa", "asdasfas");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(" Trying to edit product with invalid id.");
        try {
            t.editProduct("name", "asdsa", "115", "5", "description", t, "allCategories");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
