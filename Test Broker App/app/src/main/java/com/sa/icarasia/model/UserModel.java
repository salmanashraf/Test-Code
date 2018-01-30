package com.sa.icarasia.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

import butterknife.Optional;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class UserModel extends RealmObject implements Serializable{

    @PrimaryKey
    private int id;
    @Required
    private String email;
    @Required
    private String password;
    @Required
    private String usertype;
    @Required
    private String number;
    private String fname;
    private String lname;


    public UserModel(){

    }

    public UserModel(int id, String email, String password, String type, String number, String fname, String lname) {
        this.id = id;
        this.email = email;
        this.password = password;
        usertype = type;
        this.number = number;
        this.fname = fname;
        this.lname = lname;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }



}
