package com.cloudwalkdigital.activation.evaluationapp.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.cloudwalkdigital.activation.evaluationapp.R;
import com.cloudwalkdigital.activation.evaluationapp.constant.Constant;
import com.cloudwalkdigital.activation.evaluationapp.models.QuestionModel;
import com.cloudwalkdigital.activation.evaluationapp.utils.DatabaseAccess;
import com.cloudwalkdigital.activation.evaluationapp.utils.Globals;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FinishActivity extends AppCompatActivity {
    public static final String PREFS_NAME = "MyPref";
    SharedPreferences sharedPreference;
    SharedPreferences.Editor editor;
    private Question mAuthTask = null;
    Globals g = Globals.getInstance();
    DatabaseAccess databaseAccess;

    String evaluator = "EVALUATOR";
    String project = "PROJECT";
    String category = "CATEGORY";
    String jsonArrayData = "jsonArrayData";
    String link;
    String data;
    BufferedReader bufferedReader;
    String result;

    @Bind(R.id.label_finish) TextView txtFinish;
    @Bind(R.id.toolbar) Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);
        ButterKnife.bind(this);
        sharedPreference = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        evaluator = sharedPreference.getString("EVALUATOR", "");
        project = sharedPreference.getString("EVENTNAME", "");
        category = sharedPreference.getString("EVENTCATEGORY", "");
        jsonArrayData = sharedPreference.getString("jsonData", "");
        int EVENTID = sharedPreference.getInt("EVENTID", 0);
        txtFinish.setText(category+" Completed");
        toolbar.setTitle(project+" Completed");

        Toast.makeText(getApplicationContext(),""+g.getQuestion().size(),Toast.LENGTH_SHORT).show();
        int pageNum = 1;
        databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();
        databaseAccess.saveEvaluated(g.getEmployee().getId()+"",EVENTID+"",category);
        databaseAccess.close();
        //t1.setText(""+sharedPreference.getInt("a"+pageNum, 0));
    }

    @OnClick(R.id.iv_x)
    public void back(){
        startActivity(new Intent(getApplicationContext(), QuestionActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
        finish();
    }

    @OnClick(R.id.nxtCat)
    public void nxtCategory(){
        editor = sharedPreference.edit();

        switch(category){
            case "Pre Event":
                mAuthTask = new Question("eprop");
                mAuthTask.execute((Void) null);
                break;
            case "Event Proper":
                mAuthTask = new Question("post");
                mAuthTask.execute((Void) null);
                break;
            case "Post Event":
                mAuthTask = new Question("pre");
                mAuthTask.execute((Void) null);
                break;
        }
    }

    @OnClick(R.id.nxtEvent)
    public void nxtEvent(){
        startActivity(new Intent(getApplicationContext(), ProjectsActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
        finish();
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
//            Log.d("THIS QUESTION",g.getQuestion().size()+"");
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
