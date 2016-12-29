package com.cloudwalkdigital.activation.evaluationapp.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by henry on 08/07/2016.
 */
public class QuestionModel {

    private long id;
    private String question;
    private String qtype;
    private String qdept;
    private String qcat;
    private String qsub;
    List<AnswerModel> answers = new ArrayList<AnswerModel>();

    public List<AnswerModel> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerModel> answers) {
        this.answers = answers;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQtype() {
        return qtype;
    }

    public void setQtype(String qtype) {
        this.qtype = qtype;
    }

    public String getQdept() {
        return qdept;
    }

    public void setQdept(String qdept) {
        this.qdept = qdept;
    }

    public String getQcat() {
        return qcat;
    }

    public void setQcat(String qcat) {
        this.qcat = qcat;
    }

    public String getQsub() {
        return qsub;
    }

    public void setQsub(String qsub) {
        this.qsub = qsub;
    }
}
