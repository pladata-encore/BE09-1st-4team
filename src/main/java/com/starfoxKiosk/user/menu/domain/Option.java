package com.starfoxKiosk.user.menu.domain;

import java.util.ArrayList;
import java.util.List;

public class Option {

    private String name;
    private List<String> types;
    private String selectedType;

    public Option(String name, String type) {
        this.name = name;
        this.types = new ArrayList<>();
        types.add(type);
    }

    public Option(String name, List<String> types) {
        this.name = name;
        this.types = types;
        this.selectedType = null;
    }

    public Option(String name, List<String> types, String selectedType) {
        this.name = name;
        this.types = types;
        this.selectedType = selectedType;
    }

    public String getSelectedType() {
        return selectedType;
    }

    public List<String> getTypes() {
        return types;
    }

    public String getName() {
        return name;
    }

    public void setSelectedType(String selectedType) {
        this.selectedType = selectedType;
    }

    public void addType(String type) {
        types.add(type);
    }


}
