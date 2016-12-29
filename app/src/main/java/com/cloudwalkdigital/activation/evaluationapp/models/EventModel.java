package com.cloudwalkdigital.activation.evaluationapp.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by henry on 10/05/2016.
 */
public class EventModel {

    public long id;
    public String name;
    public String jonum;
    public String eventdate;
    public String eventarea;
    public String postdate;
    public String predate;
    public String eventtime;
    public String evaluator;
    public String tls;
    public String nego;
    public String activationsDate;
    public String endDate;
    public String inputDate;

    public String getPostdate() {
        return postdate;
    }

    public void setPostdate(String postdate) { this.postdate = postdate; }

    public String getPredate() {
        return predate;
    }

    public void setPredate(String predate) {
        this.predate = predate;
    }

    public String getEvaluator() {
        return evaluator;
    }

    public void setEvaluator(String evaluator) {
        this.evaluator = evaluator;
    }

    public String getTls() {
        return tls;
    }

    public void setTls(String tls) {
        this.tls = tls;
    }

    public String getNego() {
        return nego;
    }

    public void setNego(String nego) {
        this.nego = nego;
    }

    public String getEventtime() {
        return eventtime;
    }

    public void setEventtime(String eventtime) {
        this.eventtime = eventtime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJonum() {
        return jonum;
    }

    public void setJonum(String jonum) {
        this.jonum = jonum;
    }

    public String getEventdate() {
        return eventdate;
    }

    public void setEventdate(String eventdate) {
        this.eventdate = eventdate;
    }

    public String getEventArea() {
        return eventarea;
    }

    public void setEventArea(String eventarea) {
        this.eventarea = eventarea;
    }

    public String getActivationsDate() {
        return activationsDate;
    }

    public void setActivationsDate(String activationsDate) {
        this.activationsDate = activationsDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String EndDate) {
        this.endDate = EndDate;
    }

    public String getInputDate() {
        return inputDate;
    }

    public void setInputDate(String InputDate) {
        this.inputDate = InputDate;
    }
}
