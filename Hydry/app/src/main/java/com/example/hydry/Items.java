package com.example.hydry;

/**
 * Created by lenovo on 2017/10/5.
 * Class for Coles items and Woolworth items
 */

public class Items {
    private String itemname;
    private String itemdescription;
    private String id;
    private String company;
    private String prefered;
    private int itemimage;

    public Items(String itemname, String itemdescription, String prefered, int itemimage) {
        this.itemname = itemname;
        this.itemdescription = itemdescription;
        this.prefered = prefered;
        this.itemimage = itemimage;
    }

    public void setItemimage(int itemimage){this.itemimage=itemimage;}
    public void setItemname(String itemname){
        this.itemname=itemname;
    }
    public void setItemdescription(String itemdescription){
        this.itemdescription=itemdescription;
    }
    public void setCompany(String company){this.company=company;}
    public void setPrefered(String prefered) {this.prefered=prefered;}
    public void setId(String id){
        this.id=id;
    }
    public String getId(){
        return this.id;
    }
    public String getItemname(){
        return this.itemname;
    }
    public String getItemdescription(){
        return this.itemdescription;
    }
    public String getCompany() {return this.company;}
    public String getPrefered(){return this.prefered;}
    public int getItemimage(){return this.itemimage;}
}
