package com.cloudwalkdigital.activation.evaluationapp.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.cloudwalkdigital.activation.evaluationapp.models.AnswerModel;
import com.cloudwalkdigital.activation.evaluationapp.models.AsignQuestion;
import com.cloudwalkdigital.activation.evaluationapp.models.DataBaseHelper;
import com.cloudwalkdigital.activation.evaluationapp.models.EmployeeModel;
import com.cloudwalkdigital.activation.evaluationapp.models.EventModel;
import com.cloudwalkdigital.activation.evaluationapp.models.LeaderModel;
import com.cloudwalkdigital.activation.evaluationapp.models.QuestionModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by henry on 07/07/2016.
 */
public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;
    private Context cm;
    Globals g = Globals.getInstance();

    private DatabaseAccess(Context context) {
        cm = context;
        this.openHelper = new DataBaseHelper(context);
    }

    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    public void SaveSyncEmployee(List<EmployeeModel> empList){
        int startindex = 0;
        database.execSQL("delete from tblemployee");
        for(int x=1;x <= empList.size();x++){
            String addQuery = "INSERT INTO tblemployee (_id, emp_fname, emp_dept, emp_email, emp_pass, emp_lname) VALUES("+empList.get(startindex).getId()+",'"+empList.get(startindex).getFname()+"','"+empList.get(startindex).getDepartment()+"','"+empList.get(startindex).getEmail()+"','"+empList.get(startindex).getPassword()+"','"+empList.get(startindex).getLname()+"')";
            database.execSQL(addQuery);
            startindex++;
        }
    }

    public void SaveSyncEvents(List<EventModel> eList){
        int startindex = 0;
        database.execSQL("delete from tblevents");
        for(int x=1;x <= eList.size();x++){
            String addQuery = "INSERT INTO tblevents (_id, e_name, e_jo, e_date, evaluator, tls, nego, p_date, pe_date, e_time, e_area, activationsDate, endDate, inputDate) VALUES("+eList.get(startindex).getId()+",'"+eList.get(startindex).getName()+"','"+eList.get(startindex).getJonum()+"','"+eList.get(startindex).getEventdate()+"','"+eList.get(startindex).getEvaluator()+"','"+eList.get(startindex).getTls()+"','"+eList.get(startindex).getNego()+"','"+eList.get(startindex).getPredate()+"','"+eList.get(startindex).getPostdate()+"','"+eList.get(startindex).getEventtime()+"','"+eList.get(startindex).getEventArea()+"','"+eList.get(startindex).getActivationsDate()+"','"+eList.get(startindex).getEndDate()+"','"+eList.get(startindex).getInputDate()+"')";
            database.execSQL(addQuery);
            startindex++;
        }
    }



    public void SaveSyncTls(List<LeaderModel> leadList){
        int startindex = 0;
        database.execSQL("delete from tblteamleader");
        for(int x=1;x <= leadList.size();x++){
            String addQuery = "INSERT INTO tblteamleader (_id, tfname, tlname, temail) VALUES("+leadList.get(startindex).getId()+",'"+leadList.get(startindex).getFname()+"','"+leadList.get(startindex).getLname()+"','"+leadList.get(startindex).getEmail()+"')";
            database.execSQL(addQuery);
            startindex++;
        }
    }

    public void SaveSyncNego(List<LeaderModel> leadList){
        int startindex = 0;
        database.execSQL("delete from tblnegotiator");
        for(int x=1;x <= leadList.size();x++){
            String addQuery = "INSERT INTO tblnegotiator (_id, nfname, nlname, nemail) VALUES("+leadList.get(startindex).getId()+",'"+leadList.get(startindex).getFname()+"','"+leadList.get(startindex).getLname()+"','"+leadList.get(startindex).getEmail()+"')";
            database.execSQL(addQuery);
            startindex++;
        }
    }

    public void SaveSyncAsign(List<AsignQuestion> asignList){
        int startindex = 0;
        database.execSQL("delete from tblasignquestion");
        for(int x=1;x <= asignList.size();x++){
            String addQuery = "INSERT INTO tblasignquestion (_id, rater, qnum, qcat, ratee, qevent) VALUES("+asignList.get(startindex).getId()+",'"+asignList.get(startindex).getQrater()+"','"+asignList.get(startindex).getQnum()+"','"+asignList.get(startindex).getQcat()+",'"+asignList.get(startindex).getQratee()+"','"+asignList.get(startindex).getQevent()+"')";
            database.execSQL(addQuery);
            startindex++;
        }
    }

    public void SaveSyncQs(List<QuestionModel> qsList){
        int startindex = 0;
        database.execSQL("delete from tblquestions");
        for(int x=1;x <= qsList.size();x++){
//            String addQuery = "INSERT INTO tblquestions (_id, qname, qdept, qcat, qtype, qsub) VALUES("+qsList.get(startindex).getId()+",'"+qsList.get(startindex).getQuestion()+"','"+qsList.get(startindex).getQdept()+"','"+qsList.get(startindex).getQcat()+"','"+qsList.get(startindex).getQtype()+"','"+qsList.get(startindex).getQsub()+"')";
            ContentValues values = new ContentValues();
            values.put("_id", qsList.get(startindex).getId());
            values.put("qname", qsList.get(startindex).getQuestion());
            values.put("qdept", qsList.get(startindex).getQdept());
            values.put("qcat", qsList.get(startindex).getQcat());
            values.put("qtype", qsList.get(startindex).getQtype());
            values.put("qsub", qsList.get(startindex).getQsub());
            database.insertWithOnConflict("tblquestions", null, values, SQLiteDatabase.CONFLICT_REPLACE);
//            database.execSQL(addQuery);
            startindex++;
        }
    }

    public String resultJson(){
        Cursor cursor = database.rawQuery("SELECT * FROM tblrecords", null);
        JSONObject Root = new JSONObject();
        JSONArray RecordArray = new JSONArray();
        if(cursor.getCount() > 0){
            if (cursor != null)
                cursor.moveToFirst();
            int i = 0;
            while (!cursor.isAfterLast()) {
                JSONObject record = new JSONObject();
                try {
                    record.put("_id", cursor.getString(0));
                    record.put("eid", cursor.getString(1));
                    record.put("qcat", cursor.getString(2));
                    record.put("qevent", cursor.getString(3));
                    record.put("qid", cursor.getString(4));
                    record.put("qans", cursor.getString(5));
                    record.put("tlid", cursor.getString(7));
                    record.put("negoid", cursor.getString(8));

                    RecordArray.put(i, record);
                    i++;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                cursor.moveToNext();
            }
        }

        return RecordArray.toString();
    }

    public EmployeeModel getEmployee(String email) {

        Cursor cursor = database.query("tblemployee",new String[] {"_id", "emp_fname", "emp_lname", "emp_email", "emp_pass", "emp_dept"}, "emp_email" + "=?",new String[] {String.valueOf(email)},null,null,null);
        EmployeeModel employee = new EmployeeModel();
        if(cursor.getCount() > 0){
            if (cursor != null)
                cursor.moveToFirst();

            try{
                employee.setId(Integer.parseInt(cursor.getString(0)));
                employee.setFname(cursor.getString(1));
                employee.setLname(cursor.getString(2));
                employee.setDepartment(cursor.getString(5));
                employee.setEmail(cursor.getString(3));
                employee.setPassword(cursor.getString(4));
            }catch (Exception e){

            }
        }else{
            employee = null;
        }

        // return contact

        return employee;
    }

    public List<String> getAssignLead(int lead, int eventid){
        List<String> intList = null;
        Cursor cursor = database.rawQuery("SELECT * FROM tblevents WHERE _id="+eventid, null);

        if(cursor.getCount() > 0){
            if (cursor != null)
                cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                try{
                    if(cursor.getString(lead) != null){
                        JSONArray jsonArry = new JSONArray(cursor.getString(lead));
                        if (null == intList) {
                            intList = new ArrayList<String>();
                        }
                        for (int i = 0; i < jsonArry.length(); i++) {
                            intList.add(jsonArry.getString(i));
                        }
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
                cursor.moveToNext();
            }
        }else{
            intList = null;
        }
        cursor.close();
        return intList;
    }

    public List<LeaderModel> getLeaders(List<String> asignLead,String tablename){
        List<LeaderModel> feedItemList = new ArrayList<LeaderModel>();

        for (String qId : asignLead){
            String selectQuery = "SELECT * FROM "+ tablename +" WHERE _id=" + Integer.parseInt(qId);
            Cursor cursor = database.rawQuery(selectQuery, null);
            if(cursor.getCount() > 0){
                if (cursor != null)
                    cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    //Log.d("1.) GET QUESTION",cursor.getString(1));
                    LeaderModel qModel = new LeaderModel();
                    qModel.setId(Integer.parseInt(cursor.getString(0)));
                    qModel.setFname(cursor.getString(1));
                    qModel.setLname(cursor.getString(2));
                    qModel.setEmail(cursor.getString(4));
                    feedItemList.add(qModel);
                    cursor.moveToNext();
                }
            }
            cursor.close();
        }

        return feedItemList;
    }

    public List<EventModel> getEvents(String eid){
        Cursor cursor = database.rawQuery("SELECT * FROM tblevents", null);
        List<EventModel> feedItemList = new ArrayList<EventModel>();
        if(cursor.getCount() > 0){
            if (cursor != null)
                cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                try{
                    JSONArray jsonArry = new JSONArray(cursor.getString(5));
                    if (null == feedItemList) {
                        feedItemList = new ArrayList<EventModel>();
                    }
                    for (int i = 0; i < jsonArry.length(); i++) {
                        EventModel emodel = new EventModel();
                        if(jsonArry.getString(i).equalsIgnoreCase(eid)){
                            emodel.setId(Integer.parseInt(cursor.getString(0)));
                            emodel.setName(cursor.getString(1));
                            emodel.setJonum(cursor.getString(2));
                            emodel.setEventdate(cursor.getString(3));
                            emodel.setPredate(cursor.getString(9));
                            emodel.setPostdate(cursor.getString(10));
                            feedItemList.add(emodel);
                        }
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
                cursor.moveToNext();
            }

            cursor.close();
        }else{
            feedItemList = null;
        }

        return feedItemList;
    }

    public List<String> getAssign(){
        List<String> intList = new ArrayList<String>();
        EmployeeModel employee = new EmployeeModel();
        String selectQuery = "SELECT * FROM tblasignquestion"; // adjust query
//        String selectQuery = "SELECT * FROM tblasignquestion WHERE dept = '" + g.getEmployee().getDepartment() + "'";
        Cursor cursor = database.rawQuery(selectQuery, null);
        if(cursor.getCount() > 0){
            if (cursor != null)
                cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                intList.add(cursor.getString(2));
                cursor.moveToNext();
            }
            cursor.close();
        }
        return intList;
    }

    public List<AnswerModel> getAnswers(String qid){
        List<AnswerModel> feedanswer = new ArrayList<AnswerModel>();
        String selectQuery = "SELECT * FROM tblanswer WHERE qnum = '" + qid + "'";
        Cursor cursor = database.rawQuery(selectQuery, null);
        if(cursor.getCount() > 0){
            if (cursor != null)
                cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                //Log.d("1.) GET QUESTION",cursor.getString(1));
                AnswerModel aModel = new AnswerModel();
                aModel.setId(Integer.parseInt(cursor.getString(0)));
                aModel.setContent(cursor.getString(1));
                aModel.setQnum(Integer.parseInt(qid));
                feedanswer.add(aModel);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return feedanswer;
    }

    public void saveEvaluated(String emp_id, String event_id, String event_type){
        String selectQuery = "SELECT * FROM tblevaluates WHERE emp_id = '" + emp_id + "' AND event_id = '" + event_id + "' AND event_type = '" + event_type + "'";
        Cursor cursor = database.rawQuery(selectQuery, null);
        if(cursor.getCount() < 1){
            String addQuery = "INSERT INTO tblevaluates (emp_id, event_id, event_type) VALUES('"+emp_id+"','"+event_id+"','"+event_type+"')";
            database.execSQL(addQuery);
        }
        cursor.close();
    }

    public void saveAnswer(String empid, String qcat, String qid, String eventid, String aid, String tlid, String negoid){
        String selectQuery = "SELECT * FROM tblrecords WHERE eid = '" + empid + "' AND qans = '" + aid + "' AND qevent = '" + eventid + "'";
        Cursor cursor = database.rawQuery(selectQuery, null);
        if(cursor.getCount() > 0){
            if (cursor != null)
                cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                //Log.d("1.) GET QUESTION",cursor.getString(1));
                database.delete("tblrecords", "_id =" + Integer.parseInt(cursor.getString(0)), null);
                cursor.moveToNext();
            }
        }
        cursor.close();

        String addQuery = "INSERT INTO tblrecords (eid, qcat, qevent, qid, qans, tlid, negoid) VALUES('"+empid+"','"+qcat+"','"+eventid+"','"+qid+"','"+aid+"','"+tlid+"','"+negoid+"')";
        database.execSQL(addQuery);
    }

    public List<QuestionModel> getQuestion(String qcategory, List<String> questionId){
        List<QuestionModel> feedItemList = new ArrayList<QuestionModel>();
        List<String> preevent = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
        List<String> other = Arrays.asList("1", "2", "6", "8", "9", "10");
        List<String> listEvent = other;

        if(qcategory.equalsIgnoreCase("pre")){
            listEvent = preevent;
        }

        for (String qdept : listEvent) {
            for (String qId : questionId){
                String selectQuery = "SELECT * FROM tblquestions WHERE _id=" + Integer.parseInt(qId) + " AND qcat='" + qcategory + "' AND qdept='" + qdept + "'";
                //Log.d("selectQuery",selectQuery);
                Cursor cursor = database.rawQuery(selectQuery, null);
                if(cursor.getCount() > 0){
                    if (cursor != null)
                        cursor.moveToFirst();
                    while (!cursor.isAfterLast()) {
                        //Log.d("1.) GET QUESTION",cursor.getString(1));
                        QuestionModel qModel = new QuestionModel();
                        qModel.setId(Integer.parseInt(cursor.getString(0)));
                        qModel.setQuestion(cursor.getString(1));
                        qModel.setQdept(cursor.getString(2));
                        qModel.setQcat(cursor.getString(3));
                        qModel.setQtype(cursor.getString(4));
                        qModel.setQsub(cursor.getString(5));
                        feedItemList.add(qModel);
                        cursor.moveToNext();
                    }
                }
                cursor.close();
            }
        }
        return feedItemList;
    }

    public boolean checkEvent(String empid, String eventid, String eventype){
        boolean stat = false;
        Cursor cursor = database.rawQuery("SELECT _id, emp_id, event_id, event_type FROM tblevaluates WHERE emp_id = ? AND  event_id = ? AND  event_type = ?", new String[] { empid, eventid, eventype });
        if(cursor.getCount() > 0){
            if (cursor != null)
                cursor.moveToFirst();
            stat = true;
        }
        return stat;
    }

    public List<String> getQuotes() {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM tblemployee", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
}
