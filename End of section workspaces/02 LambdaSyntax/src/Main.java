import customers.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Customer> customers = new Customers().getCustomers();

        //sort by surname
//        Collections.sort(customers);

        //sort by firstname
//        Collections.sort(customers, new FirstNameComparator());

        //sort by age
//        Collections.sort(customers, new AgeComparator());

        //sort by ID - AOC example
//        Comparator<Customer> idSort = new Comparator<Customer>() {
//            @Override
//            public int compare(Customer o1, Customer o2) {
//                return Integer.compare(o1.getId(), o2.getId());
//            }
//        };
//        Collections.sort(customers, idSort);

        //sort by ID - lambda example
        Comparator<Customer> idSort = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());

//        for (Customer customer : customers) {
//            System.out.println(customer);
//        }

        customers.forEach( c -> System.out.println(c));

    }


}
