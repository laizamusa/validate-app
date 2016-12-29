package com.cloudwalkdigital.activation.evaluationapp.models;

/**
 * Created by henry on 08/07/2016.
 */
public class AnswerModel {
    private long id;
    private String content;
    private long qnum;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getQnum() {
        return qnum;
    }

    public void setQnum(long qnum) {
        this.qnum = qnum;
    }
}
