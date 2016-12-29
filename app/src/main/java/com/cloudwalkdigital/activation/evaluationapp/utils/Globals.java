package com.cloudwalkdigital.activation.evaluationapp.utils;

import com.cloudwalkdigital.activation.evaluationapp.models.EmployeeModel;
import com.cloudwalkdigital.activation.evaluationapp.models.QuestionModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by henry on 07/07/2016.
 */
public class Globals {
    private static Globals instance;
    EmployeeModel employee = null;
    List<QuestionModel> question = new ArrayList<QuestionModel>();

    public List<QuestionModel> getQuestion() {
        return question;
    }

    public void setQuestion(List<QuestionModel> question) {
        this.question = question;
    }

    public EmployeeModel getEmployee(){
        return this.employee;
    }

    public void setEmployee(EmployeeModel employee){
        this.employee = employee;
    }

    public static synchronized Globals getInstance(){
        if(instance == null){
            instance = new Globals();
        }
        return instance;
    }

}
