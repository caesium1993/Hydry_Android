package com.example.hydry;

/**
 * questions for arrived travellers
 */

public class Category {

    private String name;
    //private int imageId;
    public Category(String name){
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                '}';
    }
}
