package com.cloudwalkdigital.activation.evaluationapp.models;

/**
 * Created by henry on 22/07/2016.
 */
public class AsignQuestion {
    private long id;
    private String Department;
    private String Qnum;
    private String Qcat;
    private String Qrater;
    private String Qratee;
    private String qevent;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public String getQnum() {
        return Qnum;
    }

    public void setQnum(String qnum) {
        Qnum = qnum;
    }

    public String getQcat() {
        return Qcat;
    }

    public void setQcat(String qcat) {
        Qcat = qcat;
    }

    public String getQrater() {
        return Qrater;
    }

    public void setRater(String Rater) {
        Qrater = Rater;
    }

    public String getQratee() {
        return Qratee;
    }

    public void setRatee(String Ratee) {
        Qratee = Ratee;
    }

    public String getQevent() {
        return qevent;
    }

    public void setQevent(String qEvent) {
        qevent = qEvent;
    }
}
