import customers.Customer;
import customers.Customers;

import java.util.List;

public class ChallengeExercise {
    public static void main(String[] args) {
        Customers customers = new Customers();

        //get all active customers under 21
        List<Customer> youngerActiveCustomers = customers.findCustomers(c -> c.getAge() < 21 && c.isActive());
        youngerActiveCustomers.forEach(System.out::println);


    }
}
