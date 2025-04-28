package com.starfoxKiosk.user.menu.domain;

public class Menu {

    private int id;
    private String name;
    private String categoryName;
    private int categoryId;
    private int price;

    public Menu(int id, String name, String categoryName, int categoryId, int price) {
        this.id = id;
        this.name = name;
        this.categoryName = categoryName;
        this.categoryId = categoryId;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
