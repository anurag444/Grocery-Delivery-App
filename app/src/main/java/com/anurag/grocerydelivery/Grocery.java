package com.anurag.grocerydelivery;

public class Grocery {

    private String title,quantity,price;
    private int image;

    public Grocery(String title, String quantity, int image,String price) {
        this.title = title;
        this.quantity = quantity;
        this.image = image;
        this.price=price;
    }

    public String getTitle() {
        return title;
    }

    public String getQuantity() {
        return quantity;
    }

    public int getImage() {
        return image;
    }

    public String getPrice() {
        return price;
    }
}
