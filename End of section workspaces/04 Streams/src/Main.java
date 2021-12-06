import com.sun.xml.internal.bind.v2.runtime.output.SAXOutput;
import customers.Customer;
import customers.Customers;

import java.sql.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<Customer> customers = new Customers().getCustomers();

        //customers.parallelStream().forEach(System.out::println);
        customers.stream().map(c -> c.getId()).forEach(System.out::println);
        customers.stream().filter(c -> c.isActive()).forEach(System.out::println);
        Long howManyAges = customers.stream().map(c -> c.getAge()).distinct().count();
        System.out.println("There are " + howManyAges + " different ages");

        Optional<Customer> foundCustomer = customers.stream().filter(c -> c.getAge() > 100).findFirst();
        System.out.println(foundCustomer.isPresent());
        Customer c = foundCustomer.orElseGet( () -> new Customer(0,"","", 0,false,0));
        System.out.println(c);

        Customer oldestCustomer = customers.stream()
                .sorted( (c1,c2) -> Integer.compare(c2.getAge(),c1.getAge() ) )
                .findFirst().get();
        System.out.println("The oldest person is " + oldestCustomer);

        Integer ageTotal = customers.stream().map(cust -> cust.getAge()).reduce(0, (a,b) -> a+b);
        System.out.println("Total of all ages " + ageTotal );

        //find the first customer who is the oldest
        Customer firstOldest = customers.stream().reduce(customers.get(0), (c1,c2) -> c1.getAge() > c2.getAge() ? c1 : c2);
        System.out.println("First oldest " + firstOldest);

        List<Customer> activeCustomers = customers.stream().filter(cust1 -> cust1.isActive()).collect(Collectors.toList());

        List<Customer> finalChallenge = customers.stream()
                .filter(cx -> !cx.getFirstname().startsWith("A") && !cx.getFirstname().startsWith("B"))
                .filter(cx -> cx.getAge() % 5 == 0)
                .flatMap(cx -> {
                    if (cx.getAge() % 10 == 0) {
                        Customer[] threeCustomers = new Customer[] {cx,cx,cx};
                        Stream<Customer> customersToReturn = Arrays.stream( threeCustomers);
                        return customersToReturn;
                    }
                    else {
                        return Stream.of(cx);
                    }
                })
                .sorted( (c1,c2) -> c2.getSurname().compareTo(c1.getSurname()))
                .collect(Collectors.toList());

        finalChallenge.forEach(System.out::println);
    }

}
