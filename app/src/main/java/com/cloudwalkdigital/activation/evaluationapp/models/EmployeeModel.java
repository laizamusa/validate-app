package com.cloudwalkdigital.activation.evaluationapp.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by jpjpjp28 on 14/04/2016.
 */
public class EmployeeModel {

    private long id;
    private String fname;
    private String lname;
    private String email;
    private String department;
    private String password = "12345";

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
