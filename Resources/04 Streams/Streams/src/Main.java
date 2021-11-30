import customers.Customer;
import customers.Customers;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Customer> customers = new Customers().getCustomers();

        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }
}
