package com.example.hydry;

/**
 * Created by lenovo on 2017/10/1.
 * Class for user information
 */

public class Users {
    private String username;
    private String password;
    private String id;
    public void setUsername(String username){
        this.username=username;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public void setId(String id){
        this.id=id;
    }
    public String getId(){
        return this.id;
    }
    public String getUsername(){
        return this.username;
    }
    public String getPassword(){
        return this.password;
    }
}
