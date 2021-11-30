package customers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

public class Customers {

    private List<Customer> customers;

    //NAMES generated from https://www.name-generator.org.uk/quick/
    private String names[] = {"Carolina Bruce", "Alys Hampton", "Kye Hackett", "Wendy Finnegan", "Nathanial Gibbs",
            "Aayan Blackwell", "Sky Harvey", "Luca Garza", "Christy Stevenson", "Jasmin Mac", "Amanah Acevedo",
            "Iqra Paine", "Carolyn Cook", "Eshaan Vaughan", "Kenny Chambers", "Ayoub Smith", "Amisha Carty",
            "Penelope Morley", "Kareena Holding", "Agatha Beard", "Caprice Webster", "Pauline Doyle",
            "Haider Cleveland", "Aleena Croft", "Iosif Lowe", "Glen Archer", "Aj Flynn", "Kaja Sharples",
            "Asim Cardenas", "Kezia Holden", "Aubrey Stephens", "Atif Sosa", "Jac Frame", "Jevon Solis", "Issa Deleon",
            "Damian Greenaway", "Kourtney Tapia", "Michaela Cote", "Lyra Kendall", "Usaamah Noel", "Nada Williamson",
            "Tina Gilbert", "Luciano Matthews", "Safiyyah Magana", "Sacha Ashton",
            "Donnie Mcdermott", "Meera Sheehan", "Estelle Wagner", "Donell Vo", "Finnlay Perez", "Justine Meza",
            "Cleveland Friedman", "Whitney Richard", "Theodor Rees", "Emmanuel Bridges", "Winston Tate", "Brandon Kent",
            "Anayah Duarte", "Nabeel Barajas", "Morwenna Woodard", "Camron Lamb", "Daanyaal Pineda", "Elsie Andrews",
            "Lorelei Shepard", "Alexandros Whitehead", "Kareem Rosa", "Cayden Werner", "Catrina Bloggs",
            "Paula O'Quinn", " Sianna May", " Mara Strickland", " Eamonn Greaves", " Tyrique O'Moore", "Jae Everett",
            "Inigo Laing", "Ellie-May Haley", "Gwen Snow", "Gene Whiteley", "Judy Snyder", "Eshaal Schofield",
            "Austin Gibson", "Parker Coates", "Elina Jacobson", "Roseanne Rowland", "Ranveer Delgado",
            "Scarlett-Rose Rennie", "Brooke Collins", "Leanne Edwards", "Diana Holland", "Teigan Beattie",
            "Kieran Melton", "Yvie Wharton", "Colm Navarro", "Lillie-May Sims", "Rita Paul", "Elise Rodriquez",
            "Jarod Lister", "Josef Harwood", "Pierce Sanchez", "Michelle Davey"};

    public Customers() {
        Random random = new Random();
        customers= new ArrayList<>();
        for(int i = 0; i < 100; i++) {
            Customer customer = new Customer(i, names[i].split(" ")[0], names[i].split(" ")[1],
                    random.nextInt(100), random.nextBoolean() ,random.nextInt(5) );
            customers.add(customer);
        }
    }

    public List<Customer> getCustomers() {

        return customers;
    }

}
