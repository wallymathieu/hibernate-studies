package se.gewalli.entities;

import jakarta.persistence.Entity;

@Entity(name="Products")
public class Product {
    @jakarta.persistence.Id
    public int id;
    public float cost;
    public String name;
    public int version;
    protected Product() {
    }

    public Product(int id, float cost, String name, int version) {
        this.id = id;
        this.cost = cost;
        this.name = name;
        this.version = version;
    }
}
