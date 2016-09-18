package com.awingcorsair.simplepassword.Model;

import java.io.Serializable;

/**
 * Created by Mao on 2016/9/17.
 */
public class Record implements Serializable{
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
    private String website;
    private String username;
    private String password;
    private String note;

    public Record(){}

    public Record(int id,String website,String username,String password,String note){
        this.id=id;
        this.website=website;
        this.username=username;
        this.password=password;
        this.note=note;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
