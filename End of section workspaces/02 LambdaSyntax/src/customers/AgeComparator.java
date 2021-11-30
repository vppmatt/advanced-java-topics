package customers;

import java.util.Comparator;

public class AgeComparator implements Comparator<Customer> {
    @Override
    public int compare(Customer o1, Customer o2) {
        return Integer.compare(o1.getAge(), o2.getAge());
    }
}
