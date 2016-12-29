package com.cloudwalkdigital.activation.evaluationapp.models;

/**
 * Created by henry on 14/07/2016.
 */
public class LeaderModel {
    public long id;
    public String fname;
    public String lname;
    public String email;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
