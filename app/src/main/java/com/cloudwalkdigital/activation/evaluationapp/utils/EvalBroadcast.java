package com.cloudwalkdigital.activation.evaluationapp.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.cloudwalkdigital.activation.evaluationapp.constant.Constant;
import com.cloudwalkdigital.activation.evaluationapp.models.EmployeeModel;
import com.cloudwalkdigital.activation.evaluationapp.models.EventModel;
import com.cloudwalkdigital.activation.evaluationapp.models.LeaderModel;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by henry on 14/07/2016.
 */
public class EvalBroadcast extends BroadcastReceiver {
    static int noOfTimes = 0;
    public static final String PREFS_NAME = "MyPref";
    DatabaseAccess databaseAccess;
    SharedPreferences sharedPreference;
    SharedPreferences.Editor editor;

    @Override
    public void onReceive(final Context context, Intent intent) {
        noOfTimes++;
        boolean checksend = false;
//        Toast.makeText(context, "BC Service Running for " + noOfTimes + " times", Toast.LENGTH_SHORT).show();
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        sharedPreference = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        client.post(Constant.baseUrl+"admin/unSyncData/",params,new AsyncHttpResponseHandler(){
            @Override
            public void onSuccess(String response) {
                //System.out.println(response);
                if(response.equalsIgnoreCase("yes")){

                    Intent intnt = new Intent(context, EvalService.class);
                    // Set unsynced count in intent data
                    intnt.putExtra("intntdata", "Unsynced Rows Count data");
                    // Call MyService
                    context.startService(intnt);
                }
            }

            @Override
            public void onFailure(int statusCode, Throwable error,
                                  String content) {
                // TODO Auto-generated method stub
                if(statusCode == 404){
                    Toast.makeText(context, "404", Toast.LENGTH_SHORT).show();
                }else if(statusCode == 500){
                    Toast.makeText(context, "500", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context, "Network Problem!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        AsyncHttpClient client2 = new AsyncHttpClient();
        RequestParams params2 = new RequestParams();
        databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();
        String rec = databaseAccess.resultJson();
        params2.put("record",rec);
        databaseAccess.close();
        client2.post(Constant.baseUrl+"admin/saveRecord/",params2,new AsyncHttpResponseHandler(){
            @Override
            public void onSuccess(String response) {
                //System.out.println(response);

            }

            @Override
            public void onFailure(int statusCode, Throwable error,
                                  String content) {
                // TODO Auto-generated method stub
                if(statusCode == 404){
                    Toast.makeText(context, "404", Toast.LENGTH_SHORT).show();
                }else if(statusCode == 500){
                    Toast.makeText(context, "500", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context, "Network Problem", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
