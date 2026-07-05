package se.gewalli.entities;

import jakarta.persistence.Entity;

@Entity(name="Customers")
public class Customer {
    @jakarta.persistence.Id
    public int id;
    public String firstname;
    public String lastname;
    public int version;
    protected Customer(){}

    public Customer(int id, String firstName, String lastName, int version) {
        this.id = id;
        this.firstname = firstName;
        this.lastname = lastName;
        this.version = version;
    }
}
