package com.starfoxKiosk.user.menu.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MenuWithOptions extends Menu {

    private int count = 0;
    private String size = "";
    private int totalPrice = 0;
    private List<Option> options;
    private Set<String> optionSet;

    public MenuWithOptions(int id, String name, String categoryName, int categoryId, int price) {
        super(id, name, categoryName, categoryId, price);
        options = new ArrayList<>();
        optionSet = new HashSet<>();
    }

    public void addOption(String name, String type) {
        if (optionSet.contains(name)) {
            for (Option option : options) {
                option.addType(type);
                return;
            }
        } else {
            optionSet.add(name);
            options.add(new Option(name, type));
        }
    }

    public int getCount() {
        return count;
    }

    public List<Option> getOptions() {
        return options;
    }

    public Set<String> getOptionSet() {
        return optionSet;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSize() {
        return size;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        String s = "";

        s += this.getName() + ", ";
        s += getCount() + "개";
        s += " " + getTotalPrice() + "원";
        s += "\n";
        for (Option option : options) {
            s += option.getName() + " : " + option.getTypes() + "\n";
        }
        return s;
    }
}
