package com.smartcanteen.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "menu")
public class MenuItem {

    @Id
    private String id;

    private String name;
    private String category; // Veg / Non-Veg
    private double price;
    private int rating;
    private boolean popular;

    public MenuItem() {
    }

    public MenuItem(String name, String category, double price, int rating, boolean popular) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.rating = rating;
        this.popular = popular;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }
    public int getRating() { return rating; }
    public boolean isPopular() { return popular; }

    public void setId(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setCategory(String category) { this.category = category; }
    public void setPrice(double price) { this.price = price; }
    public void setRating(int rating) { this.rating = rating; }
    public void setPopular(boolean popular) { this.popular = popular; }
}
