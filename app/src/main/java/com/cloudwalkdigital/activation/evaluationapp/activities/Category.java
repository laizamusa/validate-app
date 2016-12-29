package com.cloudwalkdigital.activation.evaluationapp.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cloudwalkdigital.activation.evaluationapp.R;
import com.cloudwalkdigital.activation.evaluationapp.models.EmployeeModel;
import com.cloudwalkdigital.activation.evaluationapp.models.QuestionModel;
import com.cloudwalkdigital.activation.evaluationapp.utils.DatabaseAccess;
import com.cloudwalkdigital.activation.evaluationapp.utils.Globals;

import java.io.BufferedReader;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Category extends AppCompatActivity {

    @Bind(R.id.toolbar)     Toolbar toolbar;
    @Bind(R.id.tool_label)  TextView tLabel;

    public static final String PREFS_NAME = "MyPref";
    SharedPreferences sharedPreference;
    SharedPreferences.Editor editor;
    DatabaseAccess databaseAccess;
    Globals g = Globals.getInstance();
    public static int EVENT_ID = 0;
    private Question mAuthTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        sharedPreference = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        tLabel.setText(sharedPreference.getString("EVENTNAME", ""));
        EVENT_ID = sharedPreference.getInt("EVENTID", 0);
    }

    @OnClick(R.id.iv_x)
    public void selectProject(){
        startActivity(new Intent(getApplicationContext(), ProjectsActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
        finish();
    }

    @OnClick(R.id.ll_event_proper)
    public void eProper(){
        databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();

        boolean checkEvent = databaseAccess.checkEvent(g.getEmployee().getId()+"",EVENT_ID+"","Pre Event");
        if(checkEvent){
            mAuthTask = new Question("eprop");
            mAuthTask.execute((Void) null);
        }else{
            Toast.makeText(getApplicationContext(),"Please Evaluate Previous Category",Toast.LENGTH_SHORT).show();
        }
        databaseAccess.close();
    }

    @OnClick(R.id.ll_preevent)
    public void pEvent(){
        mAuthTask = new Question("pre");
        mAuthTask.execute((Void) null);
//        finish();
    }

    @OnClick(R.id.ll_postevent)
    public void poEvent(){
        databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();

        boolean checkEvent = databaseAccess.checkEvent(g.getEmployee().getId()+"",EVENT_ID+"","Event Proper");
        boolean checkEvent2 = databaseAccess.checkEvent(g.getEmployee().getId()+"",EVENT_ID+"","Pre Event");
        if(checkEvent && checkEvent2){
            mAuthTask = new Question("post");
            mAuthTask.execute((Void) null);
        }else{
            Toast.makeText(getApplicationContext(),"Please Evaluate Previous Category",Toast.LENGTH_SHORT).show();
        }
        databaseAccess.close();
    }

    public class Question extends AsyncTask<Void, Void, Boolean> {
        public static final String PREFS_NAME = "MyPref";
        SharedPreferences sharedPreference;
        SharedPreferences.Editor editor;
        private final String questype;
        String result;
        DatabaseAccess databaseAccess;
        List<QuestionModel> feedItemList;
        List<String> questionId;
        Question(String qtype) {
            questype = qtype;
        }

        @Override
        protected void onPreExecute() {
            // Things to be done before execution of long running operation. For
            // example showing ProgessDialog
            sharedPreference = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
            databaseAccess.open();
            questionId = databaseAccess.getAssign();
            feedItemList = databaseAccess.getQuestion(questype,questionId);
            databaseAccess.close();
            switch(questype){
                case "pre":
                    result = "Pre Event";
                    break;
                case "eprop":
                    result = "Event Proper";
                    break;
                case "post":
                    result = "Post Event";
                    break;
            }
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            g.setQuestion(feedItemList);
            Log.d("THIS QUESTION",g.getQuestion().size()+"");
            editor = sharedPreference.edit();
            editor.putString("EVENTCATEGORY", result);
            editor.commit();
            startActivity(new Intent(getApplicationContext(), QuestionActivity.class));
        }

        @Override
        protected void onCancelled(){
            //showProgress(false);
        }
    }
}
