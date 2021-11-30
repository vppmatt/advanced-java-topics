package customers;

import java.util.Comparator;

public class Customer implements Comparable<Customer> {
    private int id;
    private String firstname;
    private String surname;
    private int age;
    private boolean active;
    private int group;

    public Customer(int id, String firstname, String surname, int age, boolean active, int group) {
        this.id = id;
        this.firstname = firstname;
        this.surname = surname;
        this.age = age;
        this.active = active;
        this.group = group;
    }

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public boolean isActive() {
        return active;
    }

    public int getGroup() {
        return group;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", active=" + active +
                ", group=" + group +
                '}';
    }

    @Override
    public int compareTo(Customer o) {
        return surname.compareTo(o.surname);
    }
}
