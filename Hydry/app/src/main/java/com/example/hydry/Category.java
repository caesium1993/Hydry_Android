package com.example.hydry;

/**
 * questions for arrived travellers
 */

public class Category {

    private String name;
    public  int imageId;
    public Category(String name, int imageId){
        this.name=name;
        this.imageId=imageId;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
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
