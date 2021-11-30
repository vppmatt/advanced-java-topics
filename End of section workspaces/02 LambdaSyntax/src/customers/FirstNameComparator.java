package customers;

import java.util.Comparator;

public class FirstNameComparator implements Comparator<Customer> {
    @Override
    public int compare(Customer o1, Customer o2) {
        return o1.getFirstname().compareTo(o2.getFirstname());
    }
}
