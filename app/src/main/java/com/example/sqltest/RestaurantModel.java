package com.example.sqltest;

public class RestaurantModel {

    private int id;
    private String name;
    private int localization;
    private boolean isActive;

    public RestaurantModel(int id, String name, int localization, boolean isActive) {
        this.id = id;
        this.name = name;
        this.localization = localization;
        this.isActive = isActive;
    }

    public RestaurantModel() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getLocalization() {
        return localization;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocalization(int localization) {
        this.localization = localization;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "RestaurantModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", localization=" + localization +
                ", isActive=" + isActive +
                '}';
    }
}
