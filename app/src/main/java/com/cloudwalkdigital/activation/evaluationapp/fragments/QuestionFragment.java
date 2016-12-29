package com.cloudwalkdigital.activation.evaluationapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.cloudwalkdigital.activation.evaluationapp.R;
import com.cloudwalkdigital.activation.evaluationapp.activities.AdminDashboardActivity;
import com.cloudwalkdigital.activation.evaluationapp.activities.FinishActivity;
import com.cloudwalkdigital.activation.evaluationapp.adapter.QuestionAdapter;
import com.cloudwalkdigital.activation.evaluationapp.constant.Constant;
import com.cloudwalkdigital.activation.evaluationapp.models.AnswerModel;
import com.cloudwalkdigital.activation.evaluationapp.models.LeaderModel;
import com.cloudwalkdigital.activation.evaluationapp.models.QuestionModel;
import com.cloudwalkdigital.activation.evaluationapp.utils.DatabaseAccess;
import com.cloudwalkdigital.activation.evaluationapp.utils.Globals;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link QuestionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuestionFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_position = "param1";
    private static final String ARG_LIST = "param2";
    private List<QuestionModel> mQuestion;

    // TODO: Rename and change types of parameters
    private int mPosition;
    private String mParam2;
    public String quesId;
    Globals g = Globals.getInstance();

    @Bind(R.id.choices1)            TextView choices1;
    @Bind(R.id.questionHead_num)    TextView qNum;
    @Bind(R.id.questionHead_label)  TextView qLabel;
    @Bind(R.id.single)              LinearLayout llSingle;
    @Bind(R.id.multiple)            LinearLayout llMultiple;
    @Bind(R.id.rb1)                 RadioButton rb1;
    @Bind(R.id.rb2)                 RadioButton rb2;
    @Bind(R.id.rb3)                 RadioButton rb3;
    @Bind(R.id.rb4)                 RadioButton rb4;
    @Bind(R.id.rb5)                 RadioButton rb5;
    @Bind(R.id.rb6)                 RadioButton rb6;
    @Bind(R.id.rb7)                 RadioButton rb7;
    @Bind(R.id.rb8)                 RadioButton rb8;
    @Bind(R.id.rb9)                 RadioButton rb9;
    @Bind(R.id.rb10)                RadioButton rb10;
    @Bind(R.id.ck1)                 CheckBox ck1;
    @Bind(R.id.ck2)                 CheckBox ck2;
    @Bind(R.id.ck3)                 CheckBox ck3;
    @Bind(R.id.ck4)                 CheckBox ck4;
    @Bind(R.id.ck5)                 CheckBox ck5;
    @Bind(R.id.ck6)                 CheckBox ck6;
    @Bind(R.id.ck7)                 CheckBox ck7;
    @Bind(R.id.ck8)                 CheckBox ck8;
    @Bind(R.id.ck9)                 CheckBox ck9;
    @Bind(R.id.ck10)                CheckBox ck10;
    @Bind(R.id.leadinfo)            TextView leadinfo;
    @Bind(R.id.tck1)                RadioButton tck1;
    @Bind(R.id.tck2)                RadioButton tck2;
    @Bind(R.id.tck3)                RadioButton tck3;
    @Bind(R.id.tck4)                RadioButton tck4;
    @Bind(R.id.tck5)                RadioButton tck5;

    String tlstype = "";

    public static final String PREFS_NAME = "MyPref";
    SharedPreferences sharedPreference;
    DatabaseAccess databaseAccess;
    ViewPager vp;

    public QuestionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     */
    // TODO: Rename and change types and number of parameters
    public static QuestionFragment newInstance(int position) {
        QuestionFragment fragment = new QuestionFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_position, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPosition = getArguments().getInt(ARG_position);
            sharedPreference = getActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        }
        vp = (ViewPager) getActivity().findViewById(R.id.vp_container);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.answer_sheet, container, false);
        ButterKnife.bind(this,v);
        List<AnswerModel> feedItemList;
        List<LeaderModel> leadTeam = null;
        List<String> asignleadteam;
        int EVENTID = sharedPreference.getInt("EVENTID", 0);
        int questionNum = mPosition + 1;
        qNum.setText(questionNum + ".");
        qLabel.setText(g.getQuestion().get(mPosition).getQuestion());
        quesId = g.getQuestion().get(mPosition).getId()+"";
        databaseAccess = DatabaseAccess.getInstance(getActivity());
        databaseAccess.open();
        feedItemList = databaseAccess.getAnswers(g.getQuestion().get(mPosition).getId()+"");
        int layout = feedItemList.size();
        int deptInfo = Integer.parseInt(g.getQuestion().get(mPosition).getQdept());
        leadinfo.setVisibility(View.GONE);
        if(deptInfo == 5 || deptInfo == 3) {
            switch(deptInfo){
                case 3:
                    leadinfo.setVisibility(View.VISIBLE);
                    leadinfo.setText("Select Negotiator's");
                    asignleadteam = databaseAccess.getAssignLead(8,EVENTID);
                    if(asignleadteam != null){
                        leadTeam = databaseAccess.getLeaders(asignleadteam, "tblnegotiator");
                    }
                    tlstype = "nego";
                    break;
                case 5:
                    leadinfo.setVisibility(View.VISIBLE);
                    leadinfo.setText("Select Team Leader's");
                    asignleadteam = databaseAccess.getAssignLead(7,EVENTID);
                    if(asignleadteam != null){
                        leadTeam = databaseAccess.getLeaders(asignleadteam, "tblteamleader");
                    }
                    tlstype = "tl";
                    break;
            }

            switch (leadTeam.size()){
                case 1:
                    tck1.setVisibility(View.VISIBLE);
                    tck1.setText(leadTeam.get(0).getFname()+" "+leadTeam.get(0).getLname());
                    tck1.setContentDescription(leadTeam.get(0).getId()+"");
                    break;
                case 2:
                    tck1.setVisibility(View.VISIBLE);
                    tck2.setVisibility(View.VISIBLE);
                    tck1.setText(leadTeam.get(0).getFname()+" "+leadTeam.get(0).getLname());
                    tck2.setText(leadTeam.get(1).getFname()+" "+leadTeam.get(1).getLname());
                    tck1.setContentDescription(leadTeam.get(0).getId()+"");
                    tck2.setContentDescription(leadTeam.get(1).getId()+"");
                    break;
                case 3:
                    tck1.setVisibility(View.VISIBLE);
                    tck2.setVisibility(View.VISIBLE);
                    tck3.setVisibility(View.VISIBLE);
                    tck1.setText(leadTeam.get(0).getFname()+" "+leadTeam.get(0).getLname());
                    tck2.setText(leadTeam.get(1).getFname()+" "+leadTeam.get(1).getLname());
                    tck3.setText(leadTeam.get(2).getFname()+" "+leadTeam.get(2).getLname());
                    tck1.setContentDescription(leadTeam.get(0).getId()+"");
                    tck2.setContentDescription(leadTeam.get(1).getId()+"");
                    tck3.setContentDescription(leadTeam.get(2).getId()+"");
                    break;
                case 4:
                    tck1.setVisibility(View.VISIBLE);
                    tck2.setVisibility(View.VISIBLE);
                    tck3.setVisibility(View.VISIBLE);
                    tck4.setVisibility(View.VISIBLE);
                    tck1.setText(leadTeam.get(0).getFname()+" "+leadTeam.get(0).getLname());
                    tck2.setText(leadTeam.get(1).getFname()+" "+leadTeam.get(1).getLname());
                    tck3.setText(leadTeam.get(2).getFname()+" "+leadTeam.get(2).getLname());
                    tck4.setText(leadTeam.get(3).getFname()+" "+leadTeam.get(3).getLname());
                    tck1.setContentDescription(leadTeam.get(0).getId()+"");
                    tck2.setContentDescription(leadTeam.get(1).getId()+"");
                    tck3.setContentDescription(leadTeam.get(2).getId()+"");
                    tck4.setContentDescription(leadTeam.get(3).getId()+"");
                    break;
                case 5:
                    tck1.setVisibility(View.VISIBLE);
                    tck2.setVisibility(View.VISIBLE);
                    tck3.setVisibility(View.VISIBLE);
                    tck4.setVisibility(View.VISIBLE);
                    tck5.setVisibility(View.VISIBLE);
                    tck1.setText(leadTeam.get(0).getFname()+" "+leadTeam.get(0).getLname());
                    tck2.setText(leadTeam.get(1).getFname()+" "+leadTeam.get(1).getLname());
                    tck3.setText(leadTeam.get(2).getFname()+" "+leadTeam.get(2).getLname());
                    tck4.setText(leadTeam.get(3).getFname()+" "+leadTeam.get(3).getLname());
                    tck5.setText(leadTeam.get(4).getFname()+" "+leadTeam.get(4).getLname());
                    tck1.setContentDescription(leadTeam.get(0).getId()+"");
                    tck2.setContentDescription(leadTeam.get(1).getId()+"");
                    tck3.setContentDescription(leadTeam.get(2).getId()+"");
                    tck4.setContentDescription(leadTeam.get(3).getId()+"");
                    tck5.setContentDescription(leadTeam.get(4).getId()+"");
                    break;
            }
        }
        databaseAccess.close();
        choices1.setText(g.getQuestion().get(mPosition).getQsub());
        switch(layout){
            case 1:
                llSingle.setVisibility(View.VISIBLE);
                rb1.setVisibility(View.VISIBLE);
                rb2.setVisibility(View.VISIBLE);
                choices1.setVisibility(View.VISIBLE);
                rb1.setText(feedItemList.get(0).getContent());
                rb2.setText(feedItemList.get(1).getContent());
                rb1.setContentDescription(feedItemList.get(0).getId()+"");
                rb2.setContentDescription(feedItemList.get(1).getId()+"");
                //choices1.setText(Constant.accountsQuestion[mParam1][2][0][0]);
                break;
            case 2:
                if(g.getQuestion().get(mPosition).getQtype().equalsIgnoreCase("M")){
                    llMultiple.setVisibility(View.VISIBLE);
                    ck1.setVisibility(View.VISIBLE);
                    ck2.setVisibility(View.VISIBLE);
                    ck1.setText(feedItemList.get(0).getContent());
                    ck2.setText(feedItemList.get(1).getContent());
                    ck1.setContentDescription(feedItemList.get(0).getId()+"");
                    ck2.setContentDescription(feedItemList.get(1).getId()+"");
                }else{
                    llSingle.setVisibility(View.VISIBLE);
                    rb1.setVisibility(View.VISIBLE);
                    rb2.setVisibility(View.VISIBLE);
                    rb1.setText(feedItemList.get(0).getContent());
                    rb2.setText(feedItemList.get(1).getContent());
                    rb1.setContentDescription(feedItemList.get(0).getId()+"");
                    rb2.setContentDescription(feedItemList.get(1).getId()+"");
                }
                break;
            case 3:
                if(g.getQuestion().get(mPosition).getQtype().equalsIgnoreCase("M")){
                    llMultiple.setVisibility(View.VISIBLE);
                    ck1.setVisibility(View.VISIBLE);
                    ck2.setVisibility(View.VISIBLE);
                    ck3.setVisibility(View.VISIBLE);
                    ck1.setText(feedItemList.get(0).getContent());
                    ck2.setText(feedItemList.get(1).getContent());
                    ck3.setText(feedItemList.get(2).getContent());
                    ck1.setContentDescription(feedItemList.get(0).getId()+"");
                    ck2.setContentDescription(feedItemList.get(1).getId()+"");
                    ck3.setContentDescription(feedItemList.get(2).getId()+"");
                }else{
                    llSingle.setVisibility(View.VISIBLE);
                    rb1.setVisibility(View.VISIBLE);
                    rb2.setVisibility(View.VISIBLE);
                    rb3.setVisibility(View.VISIBLE);
                    rb1.setText(feedItemList.get(0).getContent());
                    rb2.setText(feedItemList.get(1).getContent());
                    rb3.setText(feedItemList.get(2).getContent());
                    rb1.setContentDescription(feedItemList.get(0).getId()+"");
                    rb2.setContentDescription(feedItemList.get(1).getId()+"");
                    rb3.setContentDescription(feedItemList.get(2).getId()+"");
                }
                break;
            case 4:
                if(g.getQuestion().get(mPosition).getQtype().equalsIgnoreCase("M")){
                    llMultiple.setVisibility(View.VISIBLE);
                    ck1.setVisibility(View.VISIBLE);
                    ck2.setVisibility(View.VISIBLE);
                    ck3.setVisibility(View.VISIBLE);
                    ck4.setVisibility(View.VISIBLE);
                    ck1.setText(feedItemList.get(0).getContent());
                    ck2.setText(feedItemList.get(1).getContent());
                    ck3.setText(feedItemList.get(2).getContent());
                    ck4.setText(feedItemList.get(3).getContent());
                    ck1.setContentDescription(feedItemList.get(0).getId()+"");
                    ck2.setContentDescription(feedItemList.get(1).getId()+"");
                    ck3.setContentDescription(feedItemList.get(2).getId()+"");
                    ck4.setContentDescription(feedItemList.get(3).getId()+"");
                }else{
                    llSingle.setVisibility(View.VISIBLE);
                    rb1.setVisibility(View.VISIBLE);
                    rb2.setVisibility(View.VISIBLE);
                    rb3.setVisibility(View.VISIBLE);
                    rb4.setVisibility(View.VISIBLE);
                    rb1.setText(feedItemList.get(0).getContent());
                    rb2.setText(feedItemList.get(1).getContent());
                    rb3.setText(feedItemList.get(2).getContent());
                    rb4.setText(feedItemList.get(3).getContent());
                    rb1.setContentDescription(feedItemList.get(0).getId()+"");
                    rb2.setContentDescription(feedItemList.get(1).getId()+"");
                    rb3.setContentDescription(feedItemList.get(2).getId()+"");
                    rb4.setContentDescription(feedItemList.get(3).getId()+"");
                }
                break;
            case 5:
                if(g.getQuestion().get(mPosition).getQtype().equalsIgnoreCase("M")){
                    llMultiple.setVisibility(View.VISIBLE);
                    ck1.setVisibility(View.VISIBLE);
                    ck2.setVisibility(View.VISIBLE);
                    ck3.setVisibility(View.VISIBLE);
                    ck4.setVisibility(View.VISIBLE);
                    ck5.setVisibility(View.VISIBLE);
                    ck1.setText(feedItemList.get(0).getContent());
                    ck2.setText(feedItemList.get(1).getContent());
                    ck3.setText(feedItemList.get(2).getContent());
                    ck4.setText(feedItemList.get(3).getContent());
                    ck5.setText(feedItemList.get(4).getContent());
                    ck1.setContentDescription(feedItemList.get(0).getId()+"");
                    ck2.setContentDescription(feedItemList.get(1).getId()+"");
                    ck3.setContentDescription(feedItemList.get(2).getId()+"");
                    ck4.setContentDescription(feedItemList.get(3).getId()+"");
                    ck5.setContentDescription(feedItemList.get(4).getId()+"");
                }else{
                    llSingle.setVisibility(View.VISIBLE);
                    rb1.setVisibility(View.VISIBLE);
                    rb2.setVisibility(View.VISIBLE);
                    rb3.setVisibility(View.VISIBLE);
                    rb4.setVisibility(View.VISIBLE);
                    rb5.setVisibility(View.VISIBLE);
                    rb1.setText(feedItemList.get(0).getContent());
                    rb2.setText(feedItemList.get(1).getContent());
                    rb3.setText(feedItemList.get(2).getContent());
                    rb4.setText(feedItemList.get(3).getContent());
                    rb5.setText(feedItemList.get(4).getContent());
                    rb1.setContentDescription(feedItemList.get(0).getId()+"");
                    rb2.setContentDescription(feedItemList.get(1).getId()+"");
                    rb3.setContentDescription(feedItemList.get(2).getId()+"");
                    rb4.setContentDescription(feedItemList.get(3).getId()+"");
                    rb5.setContentDescription(feedItemList.get(4).getId()+"");
                }
                break;
            case 6:
                if(g.getQuestion().get(mPosition).getQtype().equalsIgnoreCase("M")){
                    llMultiple.setVisibility(View.VISIBLE);
                    ck1.setVisibility(View.VISIBLE);
                    ck2.setVisibility(View.VISIBLE);
                    ck3.setVisibility(View.VISIBLE);
                    ck4.setVisibility(View.VISIBLE);
                    ck5.setVisibility(View.VISIBLE);
                    ck6.setVisibility(View.VISIBLE);
                    ck1.setText(feedItemList.get(0).getContent());
                    ck2.setText(feedItemList.get(1).getContent());
                    ck3.setText(feedItemList.get(2).getContent());
                    ck4.setText(feedItemList.get(3).getContent());
                    ck5.setText(feedItemList.get(4).getContent());
                    ck6.setText(feedItemList.get(5).getContent());
                    ck1.setContentDescription(feedItemList.get(0).getId()+"");
                    ck2.setContentDescription(feedItemList.get(1).getId()+"");
                    ck3.setContentDescription(feedItemList.get(2).getId()+"");
                    ck4.setContentDescription(feedItemList.get(3).getId()+"");
                    ck5.setContentDescription(feedItemList.get(4).getId()+"");
                    ck6.setContentDescription(feedItemList.get(5).getId()+"");
                }else{
                    llSingle.setVisibility(View.VISIBLE);
                    rb1.setVisibility(View.VISIBLE);
                    rb2.setVisibility(View.VISIBLE);
                    rb3.setVisibility(View.VISIBLE);
                    rb4.setVisibility(View.VISIBLE);
                    rb5.setVisibility(View.VISIBLE);
                    rb6.setVisibility(View.VISIBLE);
                    rb1.setText(feedItemList.get(0).getContent());
                    rb2.setText(feedItemList.get(1).getContent());
                    rb3.setText(feedItemList.get(2).getContent());
                    rb4.setText(feedItemList.get(3).getContent());
                    rb5.setText(feedItemList.get(4).getContent());
                    rb6.setText(feedItemList.get(5).getContent());
                    rb1.setContentDescription(feedItemList.get(0).getId()+"");
                    rb2.setContentDescription(feedItemList.get(1).getId()+"");
                    rb3.setContentDescription(feedItemList.get(2).getId()+"");
                    rb4.setContentDescription(feedItemList.get(3).getId()+"");
                    rb5.setContentDescription(feedItemList.get(4).getId()+"");
                    rb6.setContentDescription(feedItemList.get(5).getId()+"");
                }
                break;
            case 7:
                if(g.getQuestion().get(mPosition).getQtype().equalsIgnoreCase("M")){
                    llMultiple.setVisibility(View.VISIBLE);
                    ck1.setVisibility(View.VISIBLE);
                    ck2.setVisibility(View.VISIBLE);
                    ck3.setVisibility(View.VISIBLE);
                    ck4.setVisibility(View.VISIBLE);
                    ck5.setVisibility(View.VISIBLE);
                    ck6.setVisibility(View.VISIBLE);
                    ck7.setVisibility(View.VISIBLE);
                    ck1.setText(feedItemList.get(0).getContent());
                    ck2.setText(feedItemList.get(1).getContent());
                    ck3.setText(feedItemList.get(2).getContent());
                    ck4.setText(feedItemList.get(3).getContent());
                    ck5.setText(feedItemList.get(4).getContent());
                    ck6.setText(feedItemList.get(5).getContent());
                    ck7.setText(feedItemList.get(6).getContent());
                    ck1.setContentDescription(feedItemList.get(0).getId()+"");
                    ck2.setContentDescription(feedItemList.get(1).getId()+"");
                    ck3.setContentDescription(feedItemList.get(2).getId()+"");
                    ck4.setContentDescription(feedItemList.get(3).getId()+"");
                    ck5.setContentDescription(feedItemList.get(4).getId()+"");
                    ck6.setContentDescription(feedItemList.get(5).getId()+"");
                    ck7.setContentDescription(feedItemList.get(6).getId()+"");
                }else{
                    llSingle.setVisibility(View.VISIBLE);
                    rb1.setVisibility(View.VISIBLE);
                    rb2.setVisibility(View.VISIBLE);
                    rb3.setVisibility(View.VISIBLE);
                    rb4.setVisibility(View.VISIBLE);
                    rb5.setVisibility(View.VISIBLE);
                    rb6.setVisibility(View.VISIBLE);
                    rb7.setVisibility(View.VISIBLE);
                    rb1.setText(feedItemList.get(0).getContent());
                    rb2.setText(feedItemList.get(1).getContent());
                    rb3.setText(feedItemList.get(2).getContent());
                    rb4.setText(feedItemList.get(3).getContent());
                    rb5.setText(feedItemList.get(4).getContent());
                    rb6.setText(feedItemList.get(5).getContent());
                    rb7.setText(feedItemList.get(6).getContent());
                    rb1.setContentDescription(feedItemList.get(0).getId()+"");
                    rb2.setContentDescription(feedItemList.get(1).getId()+"");
                    rb3.setContentDescription(feedItemList.get(2).getId()+"");
                    rb4.setContentDescription(feedItemList.get(3).getId()+"");
                    rb5.setContentDescription(feedItemList.get(4).getId()+"");
                    rb6.setContentDescription(feedItemList.get(5).getId()+"");
                    rb7.setContentDescription(feedItemList.get(6).getId()+"");
                }
                break;
            case 8:
                if(g.getQuestion().get(mPosition).getQtype().equalsIgnoreCase("M")){
                    llMultiple.setVisibility(View.VISIBLE);
                    ck1.setVisibility(View.VISIBLE);
                    ck2.setVisibility(View.VISIBLE);
                    ck3.setVisibility(View.VISIBLE);
                    ck4.setVisibility(View.VISIBLE);
                    ck5.setVisibility(View.VISIBLE);
                    ck6.setVisibility(View.VISIBLE);
                    ck7.setVisibility(View.VISIBLE);
                    ck8.setVisibility(View.VISIBLE);
                    ck1.setText(feedItemList.get(0).getContent());
                    ck2.setText(feedItemList.get(1).getContent());
                    ck3.setText(feedItemList.get(2).getContent());
                    ck4.setText(feedItemList.get(3).getContent());
                    ck5.setText(feedItemList.get(4).getContent());
                    ck6.setText(feedItemList.get(5).getContent());
                    ck7.setText(feedItemList.get(6).getContent());
                    ck8.setText(feedItemList.get(7).getContent());
                    ck1.setContentDescription(feedItemList.get(0).getId()+"");
                    ck2.setContentDescription(feedItemList.get(1).getId()+"");
                    ck3.setContentDescription(feedItemList.get(2).getId()+"");
                    ck4.setContentDescription(feedItemList.get(3).getId()+"");
                    ck5.setContentDescription(feedItemList.get(4).getId()+"");
                    ck6.setContentDescription(feedItemList.get(5).getId()+"");
                    ck7.setContentDescription(feedItemList.get(6).getId()+"");
                    ck8.setContentDescription(feedItemList.get(7).getId()+"");
                }else{
                    llSingle.setVisibility(View.VISIBLE);
                    rb1.setVisibility(View.VISIBLE);
                    rb2.setVisibility(View.VISIBLE);
                    rb3.setVisibility(View.VISIBLE);
                    rb4.setVisibility(View.VISIBLE);
                    rb5.setVisibility(View.VISIBLE);
                    rb6.setVisibility(View.VISIBLE);
                    rb7.setVisibility(View.VISIBLE);
                    rb8.setVisibility(View.VISIBLE);
                    rb1.setText(feedItemList.get(0).getContent());
                    rb2.setText(feedItemList.get(1).getContent());
                    rb3.setText(feedItemList.get(2).getContent());
                    rb4.setText(feedItemList.get(3).getContent());
                    rb5.setText(feedItemList.get(4).getContent());
                    rb6.setText(feedItemList.get(5).getContent());
                    rb7.setText(feedItemList.get(6).getContent());
                    rb8.setText(feedItemList.get(7).getContent());
                    rb1.setContentDescription(feedItemList.get(0).getId()+"");
                    rb2.setContentDescription(feedItemList.get(1).getId()+"");
                    rb3.setContentDescription(feedItemList.get(2).getId()+"");
                    rb4.setContentDescription(feedItemList.get(3).getId()+"");
                    rb5.setContentDescription(feedItemList.get(4).getId()+"");
                    rb6.setContentDescription(feedItemList.get(5).getId()+"");
                    rb7.setContentDescription(feedItemList.get(6).getId()+"");
                    rb8.setContentDescription(feedItemList.get(7).getId()+"");
                }
                break;
            case 9:
                if(g.getQuestion().get(mPosition).getQtype().equalsIgnoreCase("M")){
                    llMultiple.setVisibility(View.VISIBLE);
                    ck1.setVisibility(View.VISIBLE);
                    ck2.setVisibility(View.VISIBLE);
                    ck3.setVisibility(View.VISIBLE);
                    ck4.setVisibility(View.VISIBLE);
                    ck5.setVisibility(View.VISIBLE);
                    ck6.setVisibility(View.VISIBLE);
                    ck7.setVisibility(View.VISIBLE);
                    ck8.setVisibility(View.VISIBLE);
                    ck9.setVisibility(View.VISIBLE);
                    ck1.setText(feedItemList.get(0).getContent());
                    ck2.setText(feedItemList.get(1).getContent());
                    ck3.setText(feedItemList.get(2).getContent());
                    ck4.setText(feedItemList.get(3).getContent());
                    ck5.setText(feedItemList.get(4).getContent());
                    ck6.setText(feedItemList.get(5).getContent());
                    ck7.setText(feedItemList.get(6).getContent());
                    ck8.setText(feedItemList.get(7).getContent());
                    ck9.setText(feedItemList.get(8).getContent());
                    ck1.setContentDescription(feedItemList.get(0).getId()+"");
                    ck2.setContentDescription(feedItemList.get(1).getId()+"");
                    ck3.setContentDescription(feedItemList.get(2).getId()+"");
                    ck4.setContentDescription(feedItemList.get(3).getId()+"");
                    ck5.setContentDescription(feedItemList.get(4).getId()+"");
                    ck6.setContentDescription(feedItemList.get(5).getId()+"");
                    ck7.setContentDescription(feedItemList.get(6).getId()+"");
                    ck8.setContentDescription(feedItemList.get(7).getId()+"");
                    ck9.setContentDescription(feedItemList.get(8).getId()+"");
                }else{
                    llSingle.setVisibility(View.VISIBLE);
                    rb1.setVisibility(View.VISIBLE);
                    rb2.setVisibility(View.VISIBLE);
                    rb3.setVisibility(View.VISIBLE);
                    rb4.setVisibility(View.VISIBLE);
                    rb5.setVisibility(View.VISIBLE);
                    rb6.setVisibility(View.VISIBLE);
                    rb7.setVisibility(View.VISIBLE);
                    rb8.setVisibility(View.VISIBLE);
                    rb9.setVisibility(View.VISIBLE);
                    rb1.setText(feedItemList.get(0).getContent());
                    rb2.setText(feedItemList.get(1).getContent());
                    rb3.setText(feedItemList.get(2).getContent());
                    rb4.setText(feedItemList.get(3).getContent());
                    rb5.setText(feedItemList.get(4).getContent());
                    rb6.setText(feedItemList.get(5).getContent());
                    rb7.setText(feedItemList.get(6).getContent());
                    rb8.setText(feedItemList.get(7).getContent());
                    rb9.setText(feedItemList.get(8).getContent());
                    rb1.setContentDescription(feedItemList.get(0).getId()+"");
                    rb2.setContentDescription(feedItemList.get(1).getId()+"");
                    rb3.setContentDescription(feedItemList.get(2).getId()+"");
                    rb4.setContentDescription(feedItemList.get(3).getId()+"");
                    rb5.setContentDescription(feedItemList.get(4).getId()+"");
                    rb6.setContentDescription(feedItemList.get(5).getId()+"");
                    rb7.setContentDescription(feedItemList.get(6).getId()+"");
                    rb8.setContentDescription(feedItemList.get(7).getId()+"");
                    rb9.setContentDescription(feedItemList.get(8).getId()+"");
                }
                break;
            case 10:
                if(g.getQuestion().get(mPosition).getQtype().equalsIgnoreCase("M")){
                    llMultiple.setVisibility(View.VISIBLE);
                    ck1.setVisibility(View.VISIBLE);
                    ck2.setVisibility(View.VISIBLE);
                    ck3.setVisibility(View.VISIBLE);
                    ck4.setVisibility(View.VISIBLE);
                    ck5.setVisibility(View.VISIBLE);
                    ck6.setVisibility(View.VISIBLE);
                    ck7.setVisibility(View.VISIBLE);
                    ck8.setVisibility(View.VISIBLE);
                    ck9.setVisibility(View.VISIBLE);
                    ck10.setVisibility(View.VISIBLE);
                    ck1.setText(feedItemList.get(0).getContent());
                    ck2.setText(feedItemList.get(1).getContent());
                    ck3.setText(feedItemList.get(2).getContent());
                    ck4.setText(feedItemList.get(3).getContent());
                    ck5.setText(feedItemList.get(4).getContent());
                    ck6.setText(feedItemList.get(5).getContent());
                    ck7.setText(feedItemList.get(6).getContent());
                    ck8.setText(feedItemList.get(7).getContent());
                    ck9.setText(feedItemList.get(8).getContent());
                    ck10.setText(feedItemList.get(9).getContent());
                    ck1.setContentDescription(feedItemList.get(0).getId()+"");
                    ck2.setContentDescription(feedItemList.get(1).getId()+"");
                    ck3.setContentDescription(feedItemList.get(2).getId()+"");
                    ck4.setContentDescription(feedItemList.get(3).getId()+"");
                    ck5.setContentDescription(feedItemList.get(4).getId()+"");
                    ck6.setContentDescription(feedItemList.get(5).getId()+"");
                    ck7.setContentDescription(feedItemList.get(6).getId()+"");
                    ck8.setContentDescription(feedItemList.get(7).getId()+"");
                    ck9.setContentDescription(feedItemList.get(8).getId()+"");
                    ck10.setContentDescription(feedItemList.get(9).getId()+"");
                }else{
                    llSingle.setVisibility(View.VISIBLE);
                    rb1.setVisibility(View.VISIBLE);
                    rb2.setVisibility(View.VISIBLE);
                    rb3.setVisibility(View.VISIBLE);
                    rb4.setVisibility(View.VISIBLE);
                    rb5.setVisibility(View.VISIBLE);
                    rb6.setVisibility(View.VISIBLE);
                    rb7.setVisibility(View.VISIBLE);
                    rb8.setVisibility(View.VISIBLE);
                    rb9.setVisibility(View.VISIBLE);
                    rb10.setVisibility(View.VISIBLE);
                    rb1.setText(feedItemList.get(0).getContent());
                    rb2.setText(feedItemList.get(1).getContent());
                    rb3.setText(feedItemList.get(2).getContent());
                    rb4.setText(feedItemList.get(3).getContent());
                    rb5.setText(feedItemList.get(4).getContent());
                    rb6.setText(feedItemList.get(5).getContent());
                    rb7.setText(feedItemList.get(6).getContent());
                    rb8.setText(feedItemList.get(7).getContent());
                    rb9.setText(feedItemList.get(8).getContent());
                    rb10.setText(feedItemList.get(9).getContent());
                    rb1.setContentDescription(feedItemList.get(0).getId()+"");
                    rb2.setContentDescription(feedItemList.get(1).getId()+"");
                    rb3.setContentDescription(feedItemList.get(2).getId()+"");
                    rb4.setContentDescription(feedItemList.get(3).getId()+"");
                    rb5.setContentDescription(feedItemList.get(4).getId()+"");
                    rb6.setContentDescription(feedItemList.get(5).getId()+"");
                    rb7.setContentDescription(feedItemList.get(6).getId()+"");
                    rb8.setContentDescription(feedItemList.get(7).getId()+"");
                    rb9.setContentDescription(feedItemList.get(8).getId()+"");
                    rb10.setContentDescription(feedItemList.get(9).getId()+"");
                }
                break;
        }
        return v;
    }

    @OnClick({R.id.ck1,R.id.ck2,R.id.ck3,R.id.ck4,R.id.ck5,R.id.ck6,R.id.ck7,R.id.ck8,R.id.ck9,R.id.ck10,R.id.tck1,R.id.tck2,R.id.tck3,R.id.tck4,R.id.tck5})
    public void getAnswer(View v){
        int questionNumMax = mPosition + 1;
        String ansNum = "";
        SharedPreferences.Editor editor = sharedPreference.edit();
        int currentPage = vp.getCurrentItem() + 1;
        int EVENTID = sharedPreference.getInt("EVENTID", 0);
        String EVENTCATEGORY = sharedPreference.getString("EVENTCATEGORY", "");
        databaseAccess = DatabaseAccess.getInstance(getActivity());
        databaseAccess.open();
        String tlid = "";
        String negoid = "";
        boolean saveAnswer = true;
        boolean movePage = true;
        switch(v.getId()){
            case R.id.ck1:
                movePage = false;
                break;
            case R.id.ck2:
                movePage = false;
                break;
            case R.id.ck3:
                movePage = false;
                break;
            case R.id.ck4:
                movePage = false;
                break;
            case R.id.ck5:
                movePage = false;
                break;
            case R.id.ck6:
                movePage = false;
                break;
            case R.id.ck7:
                movePage = false;
                break;
            case R.id.ck8:
                movePage = false;
                break;
            case R.id.ck9:
                movePage = false;
                break;
            case R.id.ck10:
                movePage = false;
                break;
            case R.id.tck1:
                if(tlstype.equalsIgnoreCase("nego")){
                    if(tck1.isChecked()){
                        negoid = v.getContentDescription().toString();
                    }
                }else if(tlstype.equalsIgnoreCase("tl")){
                    if(tck1.isChecked()){
                        tlid = v.getContentDescription().toString();
                    }
                }
                movePage = false;
                saveAnswer = false;
                break;
            case R.id.tck2:
                if(tlstype.equalsIgnoreCase("nego")){
                    if(tck2.isChecked()){
                        negoid = v.getContentDescription().toString();
                    }
                }else if(tlstype.equalsIgnoreCase("tl")){
                    if(tck2.isChecked()){
                        tlid = v.getContentDescription().toString();
                    }
                }
                movePage = false;
                saveAnswer = false;
                break;
            case R.id.tck3:
                if(tlstype.equalsIgnoreCase("nego")){
                    if(tck3.isChecked()){
                        negoid = v.getContentDescription().toString();
                    }
                }else if(tlstype.equalsIgnoreCase("tl")){
                    if(tck3.isChecked()){
                        tlid = v.getContentDescription().toString();
                    }
                }
                movePage = false;
                saveAnswer = false;
                break;
            case R.id.tck4:
                if(tlstype.equalsIgnoreCase("nego")){
                    if(tck4.isChecked()){
                        negoid = v.getContentDescription().toString();
                    }
                }else if(tlstype.equalsIgnoreCase("tl")){
                    if(tck4.isChecked()){
                        tlid = v.getContentDescription().toString();
                    }
                }
                movePage = false;
                saveAnswer = false;
                break;
            case R.id.tck5:
                if(tlstype.equalsIgnoreCase("nego")){
                    if(tck5.isChecked()){
                        negoid = v.getContentDescription().toString();
                    }
                }else if(tlstype.equalsIgnoreCase("tl")){
                    if(tck5.isChecked()){
                        tlid = v.getContentDescription().toString();
                    }
                }
                movePage = false;
                saveAnswer = false;
                break;
        }
        if(ck1.isChecked() || ck2.isChecked() || ck3.isChecked() || ck4.isChecked() || ck5.isChecked() || ck6.isChecked() || ck7.isChecked() || ck8.isChecked() || ck9.isChecked() || ck10.isChecked()){
            if(saveAnswer){
                databaseAccess.saveAnswer(g.getEmployee().getId()+"",EVENTCATEGORY,quesId,EVENTID+"",v.getContentDescription().toString(),tlid,negoid);
            }
        }
        databaseAccess.close();
        editor.commit();
        if(vp.getCurrentItem() <= vp.getAdapter().getCount()){
            if(movePage){
                vp.setCurrentItem(currentPage);
            }
        }
        if(questionNumMax == vp.getAdapter().getCount()){
            if(movePage){
                startActivity(new Intent(getActivity(), FinishActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                getActivity().finish();
            }
        }
    }

    @OnClick({R.id.rb1,R.id.rb2,R.id.rb3,R.id.rb4,R.id.rb5,R.id.rb6,R.id.rb7,R.id.rb8,R.id.rb9,R.id.rb10,R.id.tck1,R.id.tck2,R.id.tck3,R.id.tck4,R.id.tck5})
    public void getAnswerRadio(View v){
        int questionNumMax = mPosition + 1;
        String ansNum = "";
        SharedPreferences.Editor editor = sharedPreference.edit();
        int currentPage = vp.getCurrentItem() + 1;
        int EVENTID = sharedPreference.getInt("EVENTID", 0);
        String EVENTCATEGORY = sharedPreference.getString("EVENTCATEGORY", "");
        databaseAccess = DatabaseAccess.getInstance(getActivity());
        databaseAccess.open();
        RadioButton rbbtn = (RadioButton) v;
        String tlid = "";
        String negoid = "";
        boolean movePage = true;
        boolean saveAnswer = true;
        switch (v.getId()){
            case R.id.tck1:
                if(tlstype.equalsIgnoreCase("nego")){
                    if(rbbtn.isChecked()){
                        negoid = v.getContentDescription().toString();
                    }
                }else if(tlstype.equalsIgnoreCase("tl")){
                    if(rbbtn.isChecked()){
                        tlid = v.getContentDescription().toString();
                    }
                }
                movePage = false;
                saveAnswer = false;
                break;
            case R.id.tck2:
                if(tlstype.equalsIgnoreCase("nego")){
                    if(rbbtn.isChecked()){
                        negoid = v.getContentDescription().toString();
                    }
                }else if(tlstype.equalsIgnoreCase("tl")){
                    if(rbbtn.isChecked()){
                        tlid = v.getContentDescription().toString();
                    }
                }
                movePage = false;
                saveAnswer = false;
                break;
            case R.id.tck3:
                if(tlstype.equalsIgnoreCase("nego")){
                    if(rbbtn.isChecked()){
                        negoid = v.getContentDescription().toString();
                    }
                }else if(tlstype.equalsIgnoreCase("tl")){
                    if(rbbtn.isChecked()){
                        tlid = v.getContentDescription().toString();
                    }
                }
                movePage = false;
                saveAnswer = false;
                break;
            case R.id.tck4:
                if(tlstype.equalsIgnoreCase("nego")){
                    if(rbbtn.isChecked()){
                        negoid = v.getContentDescription().toString();
                    }
                }else if(tlstype.equalsIgnoreCase("tl")){
                    if(rbbtn.isChecked()){
                        tlid = v.getContentDescription().toString();
                    }
                }
                movePage = false;
                saveAnswer = false;
                break;
            case R.id.tck5:
                if(tlstype.equalsIgnoreCase("nego")){
                    if(rbbtn.isChecked()){
                        negoid = v.getContentDescription().toString();
                    }
                }else if(tlstype.equalsIgnoreCase("tl")){
                    if(rbbtn.isChecked()){
                        tlid = v.getContentDescription().toString();
                    }
                }
                movePage = false;
                saveAnswer = false;
                break;
        }
        if(rbbtn.isChecked()){
            databaseAccess.saveAnswer(g.getEmployee().getId()+"",EVENTCATEGORY,quesId,EVENTID+"",v.getContentDescription().toString(),tlid,negoid);
//            Toast.makeText(getContext(),"RB Selected",Toast.LENGTH_SHORT).show();
        }
        databaseAccess.close();
        editor.commit();
        if(vp.getCurrentItem() <= vp.getAdapter().getCount()){
            if(movePage){
                vp.setCurrentItem(currentPage);
            }
        }
        if(questionNumMax == vp.getAdapter().getCount()){
            if(movePage){
                startActivity(new Intent(getActivity(), FinishActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                getActivity().finish();
            }
        }
    }

}

