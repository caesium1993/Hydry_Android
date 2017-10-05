package com.example.hydry;

/**
 * Created by caesium on 3/10/2017.
 */

public class Telecommunication {
    private String id;
    private String name;
    private String prepaid;
    private String postpaid;

    public Telecommunication(String name, String prepaid, String postpaid) {
        this.name = name;
        this.prepaid = prepaid;
        this.postpaid = postpaid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrepaid() {
        return prepaid;
    }

    public void setPrepaid(String prepaid) {
        this.prepaid = prepaid;
    }

    public String getPostpaid() {
        return postpaid;
    }

    public void setPostpaid(String postpaid) {
        this.postpaid = postpaid;
    }
}
