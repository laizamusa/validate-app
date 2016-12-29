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
import com.cloudwalkdigital.activation.evaluationapp.activities.FinishActivity;
import com.cloudwalkdigital.activation.evaluationapp.constant.Constant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PostEventQsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private int mParam1;
    private String mParam2;
    public String quesId;
    private UserLoginTask mAuthTask = null;

    @Bind(R.id.choices1)
    TextView choices1;
    @Bind(R.id.questionHead_num)    TextView qNum;
    @Bind(R.id.questionHead_label)  TextView qLabel;
    @Bind(R.id.single)
    LinearLayout llSingle;
    @Bind(R.id.multiple)            LinearLayout llMultiple;
    @Bind(R.id.rb1)
    RadioButton rb1;
    @Bind(R.id.rb2)                 RadioButton rb2;
    @Bind(R.id.rb3)                 RadioButton rb3;
    @Bind(R.id.rb4)                 RadioButton rb4;
    @Bind(R.id.rb5)                 RadioButton rb5;
    @Bind(R.id.rb6)                 RadioButton rb6;
    @Bind(R.id.rb7)                 RadioButton rb7;
    @Bind(R.id.rb8)                 RadioButton rb8;
    @Bind(R.id.rb9)                 RadioButton rb9;
    @Bind(R.id.ck1)
    CheckBox ck1;
    @Bind(R.id.ck2)                 CheckBox ck2;
    @Bind(R.id.ck3)                 CheckBox ck3;
    @Bind(R.id.ck4)                 CheckBox ck4;
    @Bind(R.id.ck5)                 CheckBox ck5;
    @Bind(R.id.ck6)                 CheckBox ck6;
    @Bind(R.id.ck7)                 CheckBox ck7;
    @Bind(R.id.ck8)                 CheckBox ck8;
    @Bind(R.id.ck9)                 CheckBox ck9;

    public static final String PREFS_NAME = "MyPref";
    SharedPreferences sharedPreference;
    ViewPager vp;


    public PostEventQsFragment() {
        // Required empty public constructor
    }
    // TODO: Rename and change types and number of parameters
    public static PostEventQsFragment newInstance(int param1, String param2) {
        PostEventQsFragment fragment = new PostEventQsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
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
        //layout
        int questionNum = mParam1 + 1;
        switch(mParam2){
            case "Account Executive":
                qNum.setText(questionNum + ".");
                int layout = Integer.parseInt(Constant.accountsQuestionPostEvent[mParam1][0][0][0]);
                qLabel.setText(Constant.accountsQuestionPostEvent[mParam1][1][0][0]);
                switch(layout){
                    case 1:
                        llSingle.setVisibility(View.VISIBLE);
                        rb1.setVisibility(View.VISIBLE);
                        rb2.setVisibility(View.VISIBLE);
                        rb1.setText("Yes");
                        rb2.setText("No");
                        rb1.setContentDescription(Constant.accountsQuestionPostEvent[mParam1][2][0][1]);
                        rb2.setContentDescription(Constant.accountsQuestionPostEvent[mParam1][2][1][1]);
                        //choices1.setText(Constant.accountsQuestionPostEvent[mParam1][2][0][0]);
                        break;
                    case 2:
                        llMultiple.setVisibility(View.VISIBLE);
                        ck1.setVisibility(View.VISIBLE);
                        ck2.setVisibility(View.VISIBLE);
                        ck1.setText(Constant.accountsQuestionPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.accountsQuestionPostEvent[mParam1][2][1][0]);
                        ck1.setContentDescription(Constant.accountsQuestionPostEvent[mParam1][2][0][1]);
                        ck2.setContentDescription(Constant.accountsQuestionPostEvent[mParam1][2][1][1]);
                        break;
                    case 3:
                        llMultiple.setVisibility(View.VISIBLE);
                        ck1.setVisibility(View.VISIBLE);
                        ck2.setVisibility(View.VISIBLE);
                        ck3.setVisibility(View.VISIBLE);
                        ck1.setText(Constant.accountsQuestionPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.accountsQuestionPostEvent[mParam1][2][1][0]);
                        ck3.setText(Constant.accountsQuestionPostEvent[mParam1][2][2][0]);
                        ck1.setContentDescription(Constant.accountsQuestionPostEvent[mParam1][2][0][1]);
                        ck2.setContentDescription(Constant.accountsQuestionPostEvent[mParam1][2][1][1]);
                        ck3.setContentDescription(Constant.accountsQuestionPostEvent[mParam1][2][2][1]);
                        break;
                    case 4:
                        llMultiple.setVisibility(View.VISIBLE);
                        ck1.setVisibility(View.VISIBLE);
                        ck2.setVisibility(View.VISIBLE);
                        ck3.setVisibility(View.VISIBLE);
                        ck4.setVisibility(View.VISIBLE);
                        ck1.setText(Constant.accountsQuestionPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.accountsQuestionPostEvent[mParam1][2][1][0]);
                        ck3.setText(Constant.accountsQuestionPostEvent[mParam1][2][2][0]);
                        ck4.setText(Constant.accountsQuestionPostEvent[mParam1][2][3][0]);
                        ck1.setContentDescription(Constant.accountsQuestionPostEvent[mParam1][2][0][1]);
                        ck2.setContentDescription(Constant.accountsQuestionPostEvent[mParam1][2][1][1]);
                        ck3.setContentDescription(Constant.accountsQuestionPostEvent[mParam1][2][2][1]);
                        ck4.setContentDescription(Constant.accountsQuestionPostEvent[mParam1][2][3][1]);
                        break;
                    case 5:
                        llMultiple.setVisibility(View.VISIBLE);
                        ck1.setVisibility(View.VISIBLE);
                        ck2.setVisibility(View.VISIBLE);
                        ck3.setVisibility(View.VISIBLE);
                        ck4.setVisibility(View.VISIBLE);
                        ck5.setVisibility(View.VISIBLE);
                        ck1.setText(Constant.accountsQuestionPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.accountsQuestionPostEvent[mParam1][2][1][0]);
                        ck3.setText(Constant.accountsQuestionPostEvent[mParam1][2][2][0]);
                        ck4.setText(Constant.accountsQuestionPostEvent[mParam1][2][3][0]);
                        ck5.setText(Constant.accountsQuestionPostEvent[mParam1][2][4][0]);
                        ck1.setContentDescription(Constant.accountsQuestionPostEvent[mParam1][2][0][1]);
                        ck2.setContentDescription(Constant.accountsQuestionPostEvent[mParam1][2][1][1]);
                        ck3.setContentDescription(Constant.accountsQuestionPostEvent[mParam1][2][2][1]);
                        ck4.setContentDescription(Constant.accountsQuestionPostEvent[mParam1][2][3][1]);
                        ck5.setContentDescription(Constant.accountsQuestionPostEvent[mParam1][2][4][1]);
                        break;
                    case 6:
                        llMultiple.setVisibility(View.VISIBLE);
                        ck1.setVisibility(View.VISIBLE);
                        ck2.setVisibility(View.VISIBLE);
                        ck3.setVisibility(View.VISIBLE);
                        ck4.setVisibility(View.VISIBLE);
                        ck5.setVisibility(View.VISIBLE);
                        ck6.setVisibility(View.VISIBLE);
                        ck1.setText(Constant.accountsQuestionPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.accountsQuestionPostEvent[mParam1][2][1][0]);
                        ck3.setText(Constant.accountsQuestionPostEvent[mParam1][2][2][0]);
                        ck4.setText(Constant.accountsQuestionPostEvent[mParam1][2][3][0]);
                        ck5.setText(Constant.accountsQuestionPostEvent[mParam1][2][4][0]);
                        ck6.setText(Constant.accountsQuestionPostEvent[mParam1][2][5][0]);
                        ck1.setContentDescription(Constant.accountsQuestionPostEvent[mParam1][2][0][1]);
                        ck2.setContentDescription(Constant.accountsQuestionPostEvent[mParam1][2][1][1]);
                        ck3.setContentDescription(Constant.accountsQuestionPostEvent[mParam1][2][2][1]);
                        ck4.setContentDescription(Constant.accountsQuestionPostEvent[mParam1][2][3][1]);
                        ck5.setContentDescription(Constant.accountsQuestionPostEvent[mParam1][2][4][1]);
                        ck6.setContentDescription(Constant.accountsQuestionPostEvent[mParam1][2][5][1]);
                        break;
                    case 7:
                        llMultiple.setVisibility(View.VISIBLE);
                        ck1.setVisibility(View.VISIBLE);
                        ck2.setVisibility(View.VISIBLE);
                        ck3.setVisibility(View.VISIBLE);
                        ck4.setVisibility(View.VISIBLE);
                        ck5.setVisibility(View.VISIBLE);
                        ck6.setVisibility(View.VISIBLE);
                        ck7.setVisibility(View.VISIBLE);
                        ck1.setText(Constant.accountsQuestionPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.accountsQuestionPostEvent[mParam1][2][1][0]);
                        ck3.setText(Constant.accountsQuestionPostEvent[mParam1][2][2][0]);
                        ck4.setText(Constant.accountsQuestionPostEvent[mParam1][2][3][0]);
                        ck5.setText(Constant.accountsQuestionPostEvent[mParam1][2][4][0]);
                        ck6.setText(Constant.accountsQuestionPostEvent[mParam1][2][5][0]);
                        ck7.setText(Constant.accountsQuestionPostEvent[mParam1][2][6][0]);
                        ck1.setContentDescription(Constant.accountsQuestionPostEvent[mParam1][2][0][1]);
                        ck2.setContentDescription(Constant.accountsQuestionPostEvent[mParam1][2][1][1]);
                        ck3.setContentDescription(Constant.accountsQuestionPostEvent[mParam1][2][2][1]);
                        ck4.setContentDescription(Constant.accountsQuestionPostEvent[mParam1][2][3][1]);
                        ck5.setContentDescription(Constant.accountsQuestionPostEvent[mParam1][2][4][1]);
                        ck6.setContentDescription(Constant.accountsQuestionPostEvent[mParam1][2][5][1]);
                        ck7.setContentDescription(Constant.accountsQuestionPostEvent[mParam1][2][6][1]);
                        break;
                    case 8:
                        llMultiple.setVisibility(View.VISIBLE);
                        ck1.setVisibility(View.VISIBLE);
                        ck2.setVisibility(View.VISIBLE);
                        ck3.setVisibility(View.VISIBLE);
                        ck4.setVisibility(View.VISIBLE);
                        ck5.setVisibility(View.VISIBLE);
                        ck6.setVisibility(View.VISIBLE);
                        ck7.setVisibility(View.VISIBLE);
                        ck8.setVisibility(View.VISIBLE);
                        ck1.setText(Constant.accountsQuestionPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.accountsQuestionPostEvent[mParam1][2][1][0]);
                        ck3.setText(Constant.accountsQuestionPostEvent[mParam1][2][2][0]);
                        ck4.setText(Constant.accountsQuestionPostEvent[mParam1][2][3][0]);
                        ck5.setText(Constant.accountsQuestionPostEvent[mParam1][2][4][0]);
                        ck6.setText(Constant.accountsQuestionPostEvent[mParam1][2][5][0]);
                        ck7.setText(Constant.accountsQuestionPostEvent[mParam1][2][6][0]);
                        ck8.setText(Constant.accountsQuestionPostEvent[mParam1][2][7][0]);
                        ck1.setContentDescription(Constant.accountsQuestionPostEvent[mParam1][2][0][1]);
                        ck2.setContentDescription(Constant.accountsQuestionPostEvent[mParam1][2][1][1]);
                        ck3.setContentDescription(Constant.accountsQuestionPostEvent[mParam1][2][2][1]);
                        ck4.setContentDescription(Constant.accountsQuestionPostEvent[mParam1][2][3][1]);
                        ck5.setContentDescription(Constant.accountsQuestionPostEvent[mParam1][2][4][1]);
                        ck6.setContentDescription(Constant.accountsQuestionPostEvent[mParam1][2][5][1]);
                        ck7.setContentDescription(Constant.accountsQuestionPostEvent[mParam1][2][6][1]);
                        ck8.setContentDescription(Constant.accountsQuestionPostEvent[mParam1][2][7][1]);
                        break;
                    case 9:
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
                        ck1.setText(Constant.accountsQuestionPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.accountsQuestionPostEvent[mParam1][2][1][0]);
                        ck3.setText(Constant.accountsQuestionPostEvent[mParam1][2][2][0]);
                        ck4.setText(Constant.accountsQuestionPostEvent[mParam1][2][3][0]);
                        ck5.setText(Constant.accountsQuestionPostEvent[mParam1][2][4][0]);
                        ck6.setText(Constant.accountsQuestionPostEvent[mParam1][2][5][0]);
                        ck7.setText(Constant.accountsQuestionPostEvent[mParam1][2][6][0]);
                        ck8.setText(Constant.accountsQuestionPostEvent[mParam1][2][7][0]);
                        ck9.setText(Constant.accountsQuestionPostEvent[mParam1][2][8][0]);
                        ck1.setContentDescription(Constant.accountsQuestionPostEvent[mParam1][2][0][1]);
                        ck2.setContentDescription(Constant.accountsQuestionPostEvent[mParam1][2][1][1]);
                        ck3.setContentDescription(Constant.accountsQuestionPostEvent[mParam1][2][2][1]);
                        ck4.setContentDescription(Constant.accountsQuestionPostEvent[mParam1][2][3][1]);
                        ck5.setContentDescription(Constant.accountsQuestionPostEvent[mParam1][2][4][1]);
                        ck6.setContentDescription(Constant.accountsQuestionPostEvent[mParam1][2][5][1]);
                        ck7.setContentDescription(Constant.accountsQuestionPostEvent[mParam1][2][6][1]);
                        ck8.setContentDescription(Constant.accountsQuestionPostEvent[mParam1][2][7][1]);
                        ck9.setContentDescription(Constant.accountsQuestionPostEvent[mParam1][2][8][1]);
                        break;
                }
                break;
            case "Operations":
                qNum.setText(questionNum + ".");
                qLabel.setText(Constant.operationPostEvent[mParam1][1][0][0]);
                layout = Integer.parseInt(Constant.operationPostEvent[mParam1][0][0][0]);
                switch(layout){
                    case 1:
                        llSingle.setVisibility(View.VISIBLE);
                        rb1.setVisibility(View.VISIBLE);
                        rb2.setVisibility(View.VISIBLE);
                        choices1.setVisibility(View.VISIBLE);
                        rb1.setText("Yes");
                        rb2.setText("No");
                        choices1.setText(Constant.operationPostEvent[mParam1][2][0][0]);
                        break;
                    case 2:
                        llMultiple.setVisibility(View.VISIBLE);
                        ck1.setVisibility(View.VISIBLE);
                        ck2.setVisibility(View.VISIBLE);
                        ck1.setText(Constant.operationPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.operationPostEvent[mParam1][2][1][0]);
                        break;
                    case 3:
                        llMultiple.setVisibility(View.VISIBLE);
                        ck1.setVisibility(View.VISIBLE);
                        ck2.setVisibility(View.VISIBLE);
                        ck3.setVisibility(View.VISIBLE);
                        ck1.setText(Constant.operationPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.operationPostEvent[mParam1][2][1][0]);
                        ck3.setText(Constant.operationPostEvent[mParam1][2][2][0]);
                        break;
                    case 4:
                        llMultiple.setVisibility(View.VISIBLE);
                        ck1.setVisibility(View.VISIBLE);
                        ck2.setVisibility(View.VISIBLE);
                        ck3.setVisibility(View.VISIBLE);
                        ck4.setVisibility(View.VISIBLE);
                        ck1.setText(Constant.operationPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.operationPostEvent[mParam1][2][1][0]);
                        ck3.setText(Constant.operationPostEvent[mParam1][2][2][0]);
                        ck4.setText(Constant.operationPostEvent[mParam1][2][3][0]);
                        break;
                    case 5:
                        llMultiple.setVisibility(View.VISIBLE);
                        ck1.setVisibility(View.VISIBLE);
                        ck2.setVisibility(View.VISIBLE);
                        ck3.setVisibility(View.VISIBLE);
                        ck4.setVisibility(View.VISIBLE);
                        ck5.setVisibility(View.VISIBLE);
                        ck1.setText(Constant.operationPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.operationPostEvent[mParam1][2][1][0]);
                        ck3.setText(Constant.operationPostEvent[mParam1][2][2][0]);
                        ck4.setText(Constant.operationPostEvent[mParam1][2][3][0]);
                        ck5.setText(Constant.operationPostEvent[mParam1][2][4][0]);
                        break;
                    case 6:
                        llMultiple.setVisibility(View.VISIBLE);
                        ck1.setVisibility(View.VISIBLE);
                        ck2.setVisibility(View.VISIBLE);
                        ck3.setVisibility(View.VISIBLE);
                        ck4.setVisibility(View.VISIBLE);
                        ck5.setVisibility(View.VISIBLE);
                        ck6.setVisibility(View.VISIBLE);
                        ck1.setText(Constant.operationPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.operationPostEvent[mParam1][2][1][0]);
                        ck3.setText(Constant.operationPostEvent[mParam1][2][2][0]);
                        ck4.setText(Constant.operationPostEvent[mParam1][2][3][0]);
                        ck5.setText(Constant.operationPostEvent[mParam1][2][4][0]);
                        ck6.setText(Constant.operationPostEvent[mParam1][2][5][0]);
                        break;
                    case 7:
                        llMultiple.setVisibility(View.VISIBLE);
                        ck1.setVisibility(View.VISIBLE);
                        ck2.setVisibility(View.VISIBLE);
                        ck3.setVisibility(View.VISIBLE);
                        ck4.setVisibility(View.VISIBLE);
                        ck5.setVisibility(View.VISIBLE);
                        ck6.setVisibility(View.VISIBLE);
                        ck7.setVisibility(View.VISIBLE);
                        ck1.setText(Constant.operationPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.operationPostEvent[mParam1][2][1][0]);
                        ck3.setText(Constant.operationPostEvent[mParam1][2][2][0]);
                        ck4.setText(Constant.operationPostEvent[mParam1][2][3][0]);
                        ck5.setText(Constant.operationPostEvent[mParam1][2][4][0]);
                        ck6.setText(Constant.operationPostEvent[mParam1][2][5][0]);
                        ck7.setText(Constant.operationPostEvent[mParam1][2][6][0]);
                        break;
                    case 8:
                        llMultiple.setVisibility(View.VISIBLE);
                        ck1.setVisibility(View.VISIBLE);
                        ck2.setVisibility(View.VISIBLE);
                        ck3.setVisibility(View.VISIBLE);
                        ck4.setVisibility(View.VISIBLE);
                        ck5.setVisibility(View.VISIBLE);
                        ck6.setVisibility(View.VISIBLE);
                        ck7.setVisibility(View.VISIBLE);
                        ck8.setVisibility(View.VISIBLE);
                        ck1.setText(Constant.operationPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.operationPostEvent[mParam1][2][1][0]);
                        ck3.setText(Constant.operationPostEvent[mParam1][2][2][0]);
                        ck4.setText(Constant.operationPostEvent[mParam1][2][3][0]);
                        ck5.setText(Constant.operationPostEvent[mParam1][2][4][0]);
                        ck6.setText(Constant.operationPostEvent[mParam1][2][5][0]);
                        ck7.setText(Constant.operationPostEvent[mParam1][2][6][0]);
                        ck8.setText(Constant.operationPostEvent[mParam1][2][7][0]);
                        break;
                    case 9:
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
                        ck1.setText(Constant.operationPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.operationPostEvent[mParam1][2][1][0]);
                        ck3.setText(Constant.operationPostEvent[mParam1][2][2][0]);
                        ck4.setText(Constant.operationPostEvent[mParam1][2][3][0]);
                        ck5.setText(Constant.operationPostEvent[mParam1][2][4][0]);
                        ck6.setText(Constant.operationPostEvent[mParam1][2][5][0]);
                        ck7.setText(Constant.operationPostEvent[mParam1][2][6][0]);
                        ck8.setText(Constant.operationPostEvent[mParam1][2][7][0]);
                        ck9.setText(Constant.operationPostEvent[mParam1][2][8][0]);
                        break;
                }
                break;
            case "Project Manager":
                qNum.setText(questionNum + ".");
                qLabel.setText(Constant.pmPostEvent[mParam1][1][0][0]);
                layout = Integer.parseInt(Constant.pmPostEvent[mParam1][0][0][0]);
                switch(layout){
                    case 1:
                        llSingle.setVisibility(View.VISIBLE);
                        rb1.setVisibility(View.VISIBLE);
                        rb2.setVisibility(View.VISIBLE);
                        choices1.setVisibility(View.VISIBLE);
                        rb1.setText("Yes");
                        rb2.setText("No");
                        choices1.setText(Constant.pmPostEvent[mParam1][2][0][0]);
                        break;
                    case 2:
                        llMultiple.setVisibility(View.VISIBLE);
                        ck1.setVisibility(View.VISIBLE);
                        ck2.setVisibility(View.VISIBLE);
                        ck1.setText(Constant.pmPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.pmPostEvent[mParam1][2][1][0]);
                        break;
                    case 3:
                        llMultiple.setVisibility(View.VISIBLE);
                        ck1.setVisibility(View.VISIBLE);
                        ck2.setVisibility(View.VISIBLE);
                        ck3.setVisibility(View.VISIBLE);
                        ck1.setText(Constant.pmPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.pmPostEvent[mParam1][2][1][0]);
                        ck3.setText(Constant.pmPostEvent[mParam1][2][2][0]);
                        break;
                    case 4:
                        llMultiple.setVisibility(View.VISIBLE);
                        ck1.setVisibility(View.VISIBLE);
                        ck2.setVisibility(View.VISIBLE);
                        ck3.setVisibility(View.VISIBLE);
                        ck4.setVisibility(View.VISIBLE);
                        ck1.setText(Constant.pmPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.pmPostEvent[mParam1][2][1][0]);
                        ck3.setText(Constant.pmPostEvent[mParam1][2][2][0]);
                        ck4.setText(Constant.pmPostEvent[mParam1][2][3][0]);
                        break;
                    case 5:
                        llMultiple.setVisibility(View.VISIBLE);
                        ck1.setVisibility(View.VISIBLE);
                        ck2.setVisibility(View.VISIBLE);
                        ck3.setVisibility(View.VISIBLE);
                        ck4.setVisibility(View.VISIBLE);
                        ck5.setVisibility(View.VISIBLE);
                        ck1.setText(Constant.pmPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.pmPostEvent[mParam1][2][1][0]);
                        ck3.setText(Constant.pmPostEvent[mParam1][2][2][0]);
                        ck4.setText(Constant.pmPostEvent[mParam1][2][3][0]);
                        ck5.setText(Constant.pmPostEvent[mParam1][2][4][0]);
                        break;
                    case 6:
                        llMultiple.setVisibility(View.VISIBLE);
                        ck1.setVisibility(View.VISIBLE);
                        ck2.setVisibility(View.VISIBLE);
                        ck3.setVisibility(View.VISIBLE);
                        ck4.setVisibility(View.VISIBLE);
                        ck5.setVisibility(View.VISIBLE);
                        ck6.setVisibility(View.VISIBLE);
                        ck1.setText(Constant.pmPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.pmPostEvent[mParam1][2][1][0]);
                        ck3.setText(Constant.pmPostEvent[mParam1][2][2][0]);
                        ck4.setText(Constant.pmPostEvent[mParam1][2][3][0]);
                        ck5.setText(Constant.pmPostEvent[mParam1][2][4][0]);
                        ck6.setText(Constant.pmPostEvent[mParam1][2][5][0]);
                        break;
                    case 7:
                        llMultiple.setVisibility(View.VISIBLE);
                        ck1.setVisibility(View.VISIBLE);
                        ck2.setVisibility(View.VISIBLE);
                        ck3.setVisibility(View.VISIBLE);
                        ck4.setVisibility(View.VISIBLE);
                        ck5.setVisibility(View.VISIBLE);
                        ck6.setVisibility(View.VISIBLE);
                        ck7.setVisibility(View.VISIBLE);
                        ck1.setText(Constant.pmPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.pmPostEvent[mParam1][2][1][0]);
                        ck3.setText(Constant.pmPostEvent[mParam1][2][2][0]);
                        ck4.setText(Constant.pmPostEvent[mParam1][2][3][0]);
                        ck5.setText(Constant.pmPostEvent[mParam1][2][4][0]);
                        ck6.setText(Constant.pmPostEvent[mParam1][2][5][0]);
                        ck7.setText(Constant.pmPostEvent[mParam1][2][6][0]);
                        break;
                    case 8:
                        llMultiple.setVisibility(View.VISIBLE);
                        ck1.setVisibility(View.VISIBLE);
                        ck2.setVisibility(View.VISIBLE);
                        ck3.setVisibility(View.VISIBLE);
                        ck4.setVisibility(View.VISIBLE);
                        ck5.setVisibility(View.VISIBLE);
                        ck6.setVisibility(View.VISIBLE);
                        ck7.setVisibility(View.VISIBLE);
                        ck8.setVisibility(View.VISIBLE);
                        ck1.setText(Constant.pmPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.pmPostEvent[mParam1][2][1][0]);
                        ck3.setText(Constant.pmPostEvent[mParam1][2][2][0]);
                        ck4.setText(Constant.pmPostEvent[mParam1][2][3][0]);
                        ck5.setText(Constant.pmPostEvent[mParam1][2][4][0]);
                        ck6.setText(Constant.pmPostEvent[mParam1][2][5][0]);
                        ck7.setText(Constant.pmPostEvent[mParam1][2][6][0]);
                        ck8.setText(Constant.pmPostEvent[mParam1][2][7][0]);
                        break;
                    case 9:
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
                        ck1.setText(Constant.pmPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.pmPostEvent[mParam1][2][1][0]);
                        ck3.setText(Constant.pmPostEvent[mParam1][2][2][0]);
                        ck4.setText(Constant.pmPostEvent[mParam1][2][3][0]);
                        ck5.setText(Constant.pmPostEvent[mParam1][2][4][0]);
                        ck6.setText(Constant.pmPostEvent[mParam1][2][5][0]);
                        ck7.setText(Constant.pmPostEvent[mParam1][2][6][0]);
                        ck8.setText(Constant.pmPostEvent[mParam1][2][7][0]);
                        ck9.setText(Constant.pmPostEvent[mParam1][2][8][0]);
                        break;
                }
                break;
            case "Activations Head":
                qNum.setText(questionNum + ".");
                qLabel.setText(Constant.actvPostEvent[mParam1][1][0][0]);
                layout = Integer.parseInt(Constant.actvPostEvent[mParam1][0][0][0]);
                switch(layout){
                    case 1:
                        llSingle.setVisibility(View.VISIBLE);
                        rb1.setVisibility(View.VISIBLE);
                        rb2.setVisibility(View.VISIBLE);
                        choices1.setVisibility(View.VISIBLE);
                        rb1.setText("Yes");
                        rb2.setText("No");
                        choices1.setText(Constant.actvPostEvent[mParam1][2][0][0]);
                        break;
                    case 2:
                        llMultiple.setVisibility(View.VISIBLE);
                        ck1.setVisibility(View.VISIBLE);
                        ck2.setVisibility(View.VISIBLE);
                        ck1.setText(Constant.actvPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.actvPostEvent[mParam1][2][1][0]);
                        break;
                    case 3:
                        llMultiple.setVisibility(View.VISIBLE);
                        ck1.setVisibility(View.VISIBLE);
                        ck2.setVisibility(View.VISIBLE);
                        ck3.setVisibility(View.VISIBLE);
                        ck1.setText(Constant.actvPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.actvPostEvent[mParam1][2][1][0]);
                        ck3.setText(Constant.actvPostEvent[mParam1][2][2][0]);
                        break;
                    case 4:
                        llMultiple.setVisibility(View.VISIBLE);
                        ck1.setVisibility(View.VISIBLE);
                        ck2.setVisibility(View.VISIBLE);
                        ck3.setVisibility(View.VISIBLE);
                        ck4.setVisibility(View.VISIBLE);
                        ck1.setText(Constant.actvPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.actvPostEvent[mParam1][2][1][0]);
                        ck3.setText(Constant.actvPostEvent[mParam1][2][2][0]);
                        ck4.setText(Constant.actvPostEvent[mParam1][2][3][0]);
                        break;
                    case 5:
                        llMultiple.setVisibility(View.VISIBLE);
                        ck1.setVisibility(View.VISIBLE);
                        ck2.setVisibility(View.VISIBLE);
                        ck3.setVisibility(View.VISIBLE);
                        ck4.setVisibility(View.VISIBLE);
                        ck5.setVisibility(View.VISIBLE);
                        ck1.setText(Constant.actvPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.actvPostEvent[mParam1][2][1][0]);
                        ck3.setText(Constant.actvPostEvent[mParam1][2][2][0]);
                        ck4.setText(Constant.actvPostEvent[mParam1][2][3][0]);
                        ck5.setText(Constant.actvPostEvent[mParam1][2][4][0]);
                        break;
                    case 6:
                        llMultiple.setVisibility(View.VISIBLE);
                        ck1.setVisibility(View.VISIBLE);
                        ck2.setVisibility(View.VISIBLE);
                        ck3.setVisibility(View.VISIBLE);
                        ck4.setVisibility(View.VISIBLE);
                        ck5.setVisibility(View.VISIBLE);
                        ck6.setVisibility(View.VISIBLE);
                        ck1.setText(Constant.actvPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.actvPostEvent[mParam1][2][1][0]);
                        ck3.setText(Constant.actvPostEvent[mParam1][2][2][0]);
                        ck4.setText(Constant.actvPostEvent[mParam1][2][3][0]);
                        ck5.setText(Constant.actvPostEvent[mParam1][2][4][0]);
                        ck6.setText(Constant.actvPostEvent[mParam1][2][5][0]);
                        break;
                    case 7:
                        llMultiple.setVisibility(View.VISIBLE);
                        ck1.setVisibility(View.VISIBLE);
                        ck2.setVisibility(View.VISIBLE);
                        ck3.setVisibility(View.VISIBLE);
                        ck4.setVisibility(View.VISIBLE);
                        ck5.setVisibility(View.VISIBLE);
                        ck6.setVisibility(View.VISIBLE);
                        ck7.setVisibility(View.VISIBLE);
                        ck1.setText(Constant.actvPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.actvPostEvent[mParam1][2][1][0]);
                        ck3.setText(Constant.actvPostEvent[mParam1][2][2][0]);
                        ck4.setText(Constant.actvPostEvent[mParam1][2][3][0]);
                        ck5.setText(Constant.actvPostEvent[mParam1][2][4][0]);
                        ck6.setText(Constant.actvPostEvent[mParam1][2][5][0]);
                        ck7.setText(Constant.actvPostEvent[mParam1][2][6][0]);
                        break;
                    case 8:
                        llMultiple.setVisibility(View.VISIBLE);
                        ck1.setVisibility(View.VISIBLE);
                        ck2.setVisibility(View.VISIBLE);
                        ck3.setVisibility(View.VISIBLE);
                        ck4.setVisibility(View.VISIBLE);
                        ck5.setVisibility(View.VISIBLE);
                        ck6.setVisibility(View.VISIBLE);
                        ck7.setVisibility(View.VISIBLE);
                        ck8.setVisibility(View.VISIBLE);
                        ck1.setText(Constant.actvPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.actvPostEvent[mParam1][2][1][0]);
                        ck3.setText(Constant.actvPostEvent[mParam1][2][2][0]);
                        ck4.setText(Constant.actvPostEvent[mParam1][2][3][0]);
                        ck5.setText(Constant.actvPostEvent[mParam1][2][4][0]);
                        ck6.setText(Constant.actvPostEvent[mParam1][2][5][0]);
                        ck7.setText(Constant.actvPostEvent[mParam1][2][6][0]);
                        ck8.setText(Constant.actvPostEvent[mParam1][2][7][0]);
                        break;
                    case 9:
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
                        ck1.setText(Constant.actvPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.actvPostEvent[mParam1][2][1][0]);
                        ck3.setText(Constant.actvPostEvent[mParam1][2][2][0]);
                        ck4.setText(Constant.actvPostEvent[mParam1][2][3][0]);
                        ck5.setText(Constant.actvPostEvent[mParam1][2][4][0]);
                        ck6.setText(Constant.actvPostEvent[mParam1][2][5][0]);
                        ck7.setText(Constant.actvPostEvent[mParam1][2][6][0]);
                        ck8.setText(Constant.actvPostEvent[mParam1][2][7][0]);
                        ck9.setText(Constant.actvPostEvent[mParam1][2][8][0]);
                        break;
                }
                break;
            case "Setup Head":
                qNum.setText(questionNum + ".");
                qLabel.setText(Constant.setupPostEvent[mParam1][1][0][0]);
                layout = Integer.parseInt(Constant.setupPostEvent[mParam1][0][0][0]);
                switch(layout){
                    case 1:
                        llSingle.setVisibility(View.VISIBLE);
                        rb1.setVisibility(View.VISIBLE);
                        rb2.setVisibility(View.VISIBLE);
                        choices1.setVisibility(View.VISIBLE);
                        rb1.setText("Yes");
                        rb2.setText("No");
                        choices1.setText(Constant.setupPostEvent[mParam1][2][0][0]);
                        break;
                    case 2:
                        llMultiple.setVisibility(View.VISIBLE);
                        ck1.setVisibility(View.VISIBLE);
                        ck2.setVisibility(View.VISIBLE);
                        ck1.setText(Constant.setupPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.setupPostEvent[mParam1][2][1][0]);
                        break;
                    case 3:
                        llMultiple.setVisibility(View.VISIBLE);
                        ck1.setVisibility(View.VISIBLE);
                        ck2.setVisibility(View.VISIBLE);
                        ck3.setVisibility(View.VISIBLE);
                        ck1.setText(Constant.setupPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.setupPostEvent[mParam1][2][1][0]);
                        ck3.setText(Constant.setupPostEvent[mParam1][2][2][0]);
                        break;
                    case 4:
                        llMultiple.setVisibility(View.VISIBLE);
                        ck1.setVisibility(View.VISIBLE);
                        ck2.setVisibility(View.VISIBLE);
                        ck3.setVisibility(View.VISIBLE);
                        ck4.setVisibility(View.VISIBLE);
                        ck1.setText(Constant.setupPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.setupPostEvent[mParam1][2][1][0]);
                        ck3.setText(Constant.setupPostEvent[mParam1][2][2][0]);
                        ck4.setText(Constant.setupPostEvent[mParam1][2][3][0]);
                        break;
                    case 5:
                        llMultiple.setVisibility(View.VISIBLE);
                        ck1.setVisibility(View.VISIBLE);
                        ck2.setVisibility(View.VISIBLE);
                        ck3.setVisibility(View.VISIBLE);
                        ck4.setVisibility(View.VISIBLE);
                        ck5.setVisibility(View.VISIBLE);
                        ck1.setText(Constant.setupPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.setupPostEvent[mParam1][2][1][0]);
                        ck3.setText(Constant.setupPostEvent[mParam1][2][2][0]);
                        ck4.setText(Constant.setupPostEvent[mParam1][2][3][0]);
                        ck5.setText(Constant.setupPostEvent[mParam1][2][4][0]);
                        break;
                    case 6:
                        llMultiple.setVisibility(View.VISIBLE);
                        ck1.setVisibility(View.VISIBLE);
                        ck2.setVisibility(View.VISIBLE);
                        ck3.setVisibility(View.VISIBLE);
                        ck4.setVisibility(View.VISIBLE);
                        ck5.setVisibility(View.VISIBLE);
                        ck6.setVisibility(View.VISIBLE);
                        ck1.setText(Constant.setupPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.setupPostEvent[mParam1][2][1][0]);
                        ck3.setText(Constant.setupPostEvent[mParam1][2][2][0]);
                        ck4.setText(Constant.setupPostEvent[mParam1][2][3][0]);
                        ck5.setText(Constant.setupPostEvent[mParam1][2][4][0]);
                        ck6.setText(Constant.setupPostEvent[mParam1][2][5][0]);
                        break;
                    case 7:
                        llMultiple.setVisibility(View.VISIBLE);
                        ck1.setVisibility(View.VISIBLE);
                        ck2.setVisibility(View.VISIBLE);
                        ck3.setVisibility(View.VISIBLE);
                        ck4.setVisibility(View.VISIBLE);
                        ck5.setVisibility(View.VISIBLE);
                        ck6.setVisibility(View.VISIBLE);
                        ck7.setVisibility(View.VISIBLE);
                        ck1.setText(Constant.setupPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.setupPostEvent[mParam1][2][1][0]);
                        ck3.setText(Constant.setupPostEvent[mParam1][2][2][0]);
                        ck4.setText(Constant.setupPostEvent[mParam1][2][3][0]);
                        ck5.setText(Constant.setupPostEvent[mParam1][2][4][0]);
                        ck6.setText(Constant.setupPostEvent[mParam1][2][5][0]);
                        ck7.setText(Constant.setupPostEvent[mParam1][2][6][0]);
                        break;
                    case 8:
                        llMultiple.setVisibility(View.VISIBLE);
                        ck1.setVisibility(View.VISIBLE);
                        ck2.setVisibility(View.VISIBLE);
                        ck3.setVisibility(View.VISIBLE);
                        ck4.setVisibility(View.VISIBLE);
                        ck5.setVisibility(View.VISIBLE);
                        ck6.setVisibility(View.VISIBLE);
                        ck7.setVisibility(View.VISIBLE);
                        ck8.setVisibility(View.VISIBLE);
                        ck1.setText(Constant.setupPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.setupPostEvent[mParam1][2][1][0]);
                        ck3.setText(Constant.setupPostEvent[mParam1][2][2][0]);
                        ck4.setText(Constant.setupPostEvent[mParam1][2][3][0]);
                        ck5.setText(Constant.setupPostEvent[mParam1][2][4][0]);
                        ck6.setText(Constant.setupPostEvent[mParam1][2][5][0]);
                        ck7.setText(Constant.setupPostEvent[mParam1][2][6][0]);
                        ck8.setText(Constant.setupPostEvent[mParam1][2][7][0]);
                        break;
                    case 9:
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
                        ck1.setText(Constant.setupPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.setupPostEvent[mParam1][2][1][0]);
                        ck3.setText(Constant.setupPostEvent[mParam1][2][2][0]);
                        ck4.setText(Constant.setupPostEvent[mParam1][2][3][0]);
                        ck5.setText(Constant.setupPostEvent[mParam1][2][4][0]);
                        ck6.setText(Constant.setupPostEvent[mParam1][2][5][0]);
                        ck7.setText(Constant.setupPostEvent[mParam1][2][6][0]);
                        ck8.setText(Constant.setupPostEvent[mParam1][2][7][0]);
                        ck9.setText(Constant.setupPostEvent[mParam1][2][8][0]);
                        break;
                }
                break;
            case "CEO":
                qNum.setText(questionNum + ".");
                qLabel.setText(Constant.ceoPostEvent[mParam1][1][0][0]);
                layout = Integer.parseInt(Constant.ceoPostEvent[mParam1][0][0][0]);
                switch(layout){
                    case 1:
                        llSingle.setVisibility(View.VISIBLE);
                        rb1.setVisibility(View.VISIBLE);
                        rb2.setVisibility(View.VISIBLE);
                        choices1.setVisibility(View.VISIBLE);
                        rb1.setText("Yes");
                        rb2.setText("No");
                        choices1.setText(Constant.ceoPostEvent[mParam1][2][0][0]);
                        break;
                    case 2:
                        llMultiple.setVisibility(View.VISIBLE);
                        ck1.setVisibility(View.VISIBLE);
                        ck2.setVisibility(View.VISIBLE);
                        ck1.setText(Constant.ceoPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.ceoPostEvent[mParam1][2][1][0]);
                        break;
                    case 3:
                        llMultiple.setVisibility(View.VISIBLE);
                        ck1.setVisibility(View.VISIBLE);
                        ck2.setVisibility(View.VISIBLE);
                        ck3.setVisibility(View.VISIBLE);
                        ck1.setText(Constant.ceoPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.ceoPostEvent[mParam1][2][1][0]);
                        ck3.setText(Constant.ceoPostEvent[mParam1][2][2][0]);
                        break;
                    case 4:
                        llMultiple.setVisibility(View.VISIBLE);
                        ck1.setVisibility(View.VISIBLE);
                        ck2.setVisibility(View.VISIBLE);
                        ck3.setVisibility(View.VISIBLE);
                        ck4.setVisibility(View.VISIBLE);
                        ck1.setText(Constant.ceoPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.ceoPostEvent[mParam1][2][1][0]);
                        ck3.setText(Constant.ceoPostEvent[mParam1][2][2][0]);
                        ck4.setText(Constant.ceoPostEvent[mParam1][2][3][0]);
                        break;
                    case 5:
                        llMultiple.setVisibility(View.VISIBLE);
                        ck1.setVisibility(View.VISIBLE);
                        ck2.setVisibility(View.VISIBLE);
                        ck3.setVisibility(View.VISIBLE);
                        ck4.setVisibility(View.VISIBLE);
                        ck5.setVisibility(View.VISIBLE);
                        ck1.setText(Constant.ceoPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.ceoPostEvent[mParam1][2][1][0]);
                        ck3.setText(Constant.ceoPostEvent[mParam1][2][2][0]);
                        ck4.setText(Constant.ceoPostEvent[mParam1][2][3][0]);
                        ck5.setText(Constant.ceoPostEvent[mParam1][2][4][0]);
                        break;
                    case 6:
                        llMultiple.setVisibility(View.VISIBLE);
                        ck1.setVisibility(View.VISIBLE);
                        ck2.setVisibility(View.VISIBLE);
                        ck3.setVisibility(View.VISIBLE);
                        ck4.setVisibility(View.VISIBLE);
                        ck5.setVisibility(View.VISIBLE);
                        ck6.setVisibility(View.VISIBLE);
                        ck1.setText(Constant.ceoPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.ceoPostEvent[mParam1][2][1][0]);
                        ck3.setText(Constant.ceoPostEvent[mParam1][2][2][0]);
                        ck4.setText(Constant.ceoPostEvent[mParam1][2][3][0]);
                        ck5.setText(Constant.ceoPostEvent[mParam1][2][4][0]);
                        ck6.setText(Constant.ceoPostEvent[mParam1][2][5][0]);
                        break;
                    case 7:
                        llMultiple.setVisibility(View.VISIBLE);
                        ck1.setVisibility(View.VISIBLE);
                        ck2.setVisibility(View.VISIBLE);
                        ck3.setVisibility(View.VISIBLE);
                        ck4.setVisibility(View.VISIBLE);
                        ck5.setVisibility(View.VISIBLE);
                        ck6.setVisibility(View.VISIBLE);
                        ck7.setVisibility(View.VISIBLE);
                        ck1.setText(Constant.ceoPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.ceoPostEvent[mParam1][2][1][0]);
                        ck3.setText(Constant.ceoPostEvent[mParam1][2][2][0]);
                        ck4.setText(Constant.ceoPostEvent[mParam1][2][3][0]);
                        ck5.setText(Constant.ceoPostEvent[mParam1][2][4][0]);
                        ck6.setText(Constant.ceoPostEvent[mParam1][2][5][0]);
                        ck7.setText(Constant.ceoPostEvent[mParam1][2][6][0]);
                        break;
                    case 8:
                        llMultiple.setVisibility(View.VISIBLE);
                        ck1.setVisibility(View.VISIBLE);
                        ck2.setVisibility(View.VISIBLE);
                        ck3.setVisibility(View.VISIBLE);
                        ck4.setVisibility(View.VISIBLE);
                        ck5.setVisibility(View.VISIBLE);
                        ck6.setVisibility(View.VISIBLE);
                        ck7.setVisibility(View.VISIBLE);
                        ck8.setVisibility(View.VISIBLE);
                        ck1.setText(Constant.ceoPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.ceoPostEvent[mParam1][2][1][0]);
                        ck3.setText(Constant.ceoPostEvent[mParam1][2][2][0]);
                        ck4.setText(Constant.ceoPostEvent[mParam1][2][3][0]);
                        ck5.setText(Constant.ceoPostEvent[mParam1][2][4][0]);
                        ck6.setText(Constant.ceoPostEvent[mParam1][2][5][0]);
                        ck7.setText(Constant.ceoPostEvent[mParam1][2][6][0]);
                        ck8.setText(Constant.ceoPostEvent[mParam1][2][7][0]);
                        break;
                    case 9:
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
                        ck1.setText(Constant.ceoPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.ceoPostEvent[mParam1][2][1][0]);
                        ck3.setText(Constant.ceoPostEvent[mParam1][2][2][0]);
                        ck4.setText(Constant.ceoPostEvent[mParam1][2][3][0]);
                        ck5.setText(Constant.ceoPostEvent[mParam1][2][4][0]);
                        ck6.setText(Constant.ceoPostEvent[mParam1][2][5][0]);
                        ck7.setText(Constant.ceoPostEvent[mParam1][2][6][0]);
                        ck8.setText(Constant.ceoPostEvent[mParam1][2][7][0]);
                        ck9.setText(Constant.ceoPostEvent[mParam1][2][8][0]);
                        break;
                }
                break;
            case "Accounting":
                qNum.setText(questionNum + ".");
                qLabel.setText(Constant.actgPostEvent[mParam1][1][0][0]);
                layout = Integer.parseInt(Constant.actgPostEvent[mParam1][0][0][0]);
                switch(layout){
                    case 1:
                        llSingle.setVisibility(View.VISIBLE);
                        rb1.setVisibility(View.VISIBLE);
                        rb2.setVisibility(View.VISIBLE);
                        choices1.setVisibility(View.VISIBLE);
                        rb1.setText("Yes");
                        rb2.setText("No");
                        choices1.setText(Constant.actgPostEvent[mParam1][2][0][0]);
                        break;
                    case 2:
                        llMultiple.setVisibility(View.VISIBLE);
                        ck1.setVisibility(View.VISIBLE);
                        ck2.setVisibility(View.VISIBLE);
                        ck1.setText(Constant.actgPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.actgPostEvent[mParam1][2][1][0]);
                        break;
                    case 3:
                        llMultiple.setVisibility(View.VISIBLE);
                        ck1.setVisibility(View.VISIBLE);
                        ck2.setVisibility(View.VISIBLE);
                        ck3.setVisibility(View.VISIBLE);
                        ck1.setText(Constant.actgPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.actgPostEvent[mParam1][2][1][0]);
                        ck3.setText(Constant.actgPostEvent[mParam1][2][2][0]);
                        break;
                    case 4:
                        llMultiple.setVisibility(View.VISIBLE);
                        ck1.setVisibility(View.VISIBLE);
                        ck2.setVisibility(View.VISIBLE);
                        ck3.setVisibility(View.VISIBLE);
                        ck4.setVisibility(View.VISIBLE);
                        ck1.setText(Constant.actgPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.actgPostEvent[mParam1][2][1][0]);
                        ck3.setText(Constant.actgPostEvent[mParam1][2][2][0]);
                        ck4.setText(Constant.actgPostEvent[mParam1][2][3][0]);
                        break;
                    case 5:
                        llMultiple.setVisibility(View.VISIBLE);
                        ck1.setVisibility(View.VISIBLE);
                        ck2.setVisibility(View.VISIBLE);
                        ck3.setVisibility(View.VISIBLE);
                        ck4.setVisibility(View.VISIBLE);
                        ck5.setVisibility(View.VISIBLE);
                        ck1.setText(Constant.actgPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.actgPostEvent[mParam1][2][1][0]);
                        ck3.setText(Constant.actgPostEvent[mParam1][2][2][0]);
                        ck4.setText(Constant.actgPostEvent[mParam1][2][3][0]);
                        ck5.setText(Constant.actgPostEvent[mParam1][2][4][0]);
                        break;
                    case 6:
                        llMultiple.setVisibility(View.VISIBLE);
                        ck1.setVisibility(View.VISIBLE);
                        ck2.setVisibility(View.VISIBLE);
                        ck3.setVisibility(View.VISIBLE);
                        ck4.setVisibility(View.VISIBLE);
                        ck5.setVisibility(View.VISIBLE);
                        ck6.setVisibility(View.VISIBLE);
                        ck1.setText(Constant.actgPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.actgPostEvent[mParam1][2][1][0]);
                        ck3.setText(Constant.actgPostEvent[mParam1][2][2][0]);
                        ck4.setText(Constant.actgPostEvent[mParam1][2][3][0]);
                        ck5.setText(Constant.actgPostEvent[mParam1][2][4][0]);
                        ck6.setText(Constant.actgPostEvent[mParam1][2][5][0]);
                        break;
                    case 7:
                        llMultiple.setVisibility(View.VISIBLE);
                        ck1.setVisibility(View.VISIBLE);
                        ck2.setVisibility(View.VISIBLE);
                        ck3.setVisibility(View.VISIBLE);
                        ck4.setVisibility(View.VISIBLE);
                        ck5.setVisibility(View.VISIBLE);
                        ck6.setVisibility(View.VISIBLE);
                        ck7.setVisibility(View.VISIBLE);
                        ck1.setText(Constant.actgPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.actgPostEvent[mParam1][2][1][0]);
                        ck3.setText(Constant.actgPostEvent[mParam1][2][2][0]);
                        ck4.setText(Constant.actgPostEvent[mParam1][2][3][0]);
                        ck5.setText(Constant.actgPostEvent[mParam1][2][4][0]);
                        ck6.setText(Constant.actgPostEvent[mParam1][2][5][0]);
                        ck7.setText(Constant.actgPostEvent[mParam1][2][6][0]);
                        break;
                    case 8:
                        llMultiple.setVisibility(View.VISIBLE);
                        ck1.setVisibility(View.VISIBLE);
                        ck2.setVisibility(View.VISIBLE);
                        ck3.setVisibility(View.VISIBLE);
                        ck4.setVisibility(View.VISIBLE);
                        ck5.setVisibility(View.VISIBLE);
                        ck6.setVisibility(View.VISIBLE);
                        ck7.setVisibility(View.VISIBLE);
                        ck8.setVisibility(View.VISIBLE);
                        ck1.setText(Constant.actgPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.actgPostEvent[mParam1][2][1][0]);
                        ck3.setText(Constant.actgPostEvent[mParam1][2][2][0]);
                        ck4.setText(Constant.actgPostEvent[mParam1][2][3][0]);
                        ck5.setText(Constant.actgPostEvent[mParam1][2][4][0]);
                        ck6.setText(Constant.actgPostEvent[mParam1][2][5][0]);
                        ck7.setText(Constant.actgPostEvent[mParam1][2][6][0]);
                        ck8.setText(Constant.actgPostEvent[mParam1][2][7][0]);
                        break;
                    case 9:
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
                        ck1.setText(Constant.actgPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.actgPostEvent[mParam1][2][1][0]);
                        ck3.setText(Constant.actgPostEvent[mParam1][2][2][0]);
                        ck4.setText(Constant.actgPostEvent[mParam1][2][3][0]);
                        ck5.setText(Constant.actgPostEvent[mParam1][2][4][0]);
                        ck6.setText(Constant.actgPostEvent[mParam1][2][5][0]);
                        ck7.setText(Constant.actgPostEvent[mParam1][2][6][0]);
                        ck8.setText(Constant.actgPostEvent[mParam1][2][7][0]);
                        ck9.setText(Constant.actgPostEvent[mParam1][2][8][0]);
                        break;
                }
                break;
            case "Inventory Head":
                qNum.setText(questionNum + ".");
                qLabel.setText(Constant.invPostEvent[mParam1][1][0][0]);
                layout = Integer.parseInt(Constant.invPostEvent[mParam1][0][0][0]);
                switch(layout){
                    case 1:
                        llSingle.setVisibility(View.VISIBLE);
                        rb1.setVisibility(View.VISIBLE);
                        rb2.setVisibility(View.VISIBLE);
                        choices1.setVisibility(View.VISIBLE);
                        rb1.setText("Yes");
                        rb2.setText("No");
                        choices1.setText(Constant.invPostEvent[mParam1][2][0][0]);
                        break;
                    case 2:
                        llMultiple.setVisibility(View.VISIBLE);
                        ck1.setVisibility(View.VISIBLE);
                        ck2.setVisibility(View.VISIBLE);
                        ck1.setText(Constant.invPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.invPostEvent[mParam1][2][1][0]);
                        break;
                    case 3:
                        llMultiple.setVisibility(View.VISIBLE);
                        ck1.setVisibility(View.VISIBLE);
                        ck2.setVisibility(View.VISIBLE);
                        ck3.setVisibility(View.VISIBLE);
                        ck1.setText(Constant.invPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.invPostEvent[mParam1][2][1][0]);
                        ck3.setText(Constant.invPostEvent[mParam1][2][2][0]);
                        break;
                    case 4:
                        llMultiple.setVisibility(View.VISIBLE);
                        ck1.setVisibility(View.VISIBLE);
                        ck2.setVisibility(View.VISIBLE);
                        ck3.setVisibility(View.VISIBLE);
                        ck4.setVisibility(View.VISIBLE);
                        ck1.setText(Constant.invPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.invPostEvent[mParam1][2][1][0]);
                        ck3.setText(Constant.invPostEvent[mParam1][2][2][0]);
                        ck4.setText(Constant.invPostEvent[mParam1][2][3][0]);
                        break;
                    case 5:
                        llMultiple.setVisibility(View.VISIBLE);
                        ck1.setVisibility(View.VISIBLE);
                        ck2.setVisibility(View.VISIBLE);
                        ck3.setVisibility(View.VISIBLE);
                        ck4.setVisibility(View.VISIBLE);
                        ck5.setVisibility(View.VISIBLE);
                        ck1.setText(Constant.invPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.invPostEvent[mParam1][2][1][0]);
                        ck3.setText(Constant.invPostEvent[mParam1][2][2][0]);
                        ck4.setText(Constant.invPostEvent[mParam1][2][3][0]);
                        ck5.setText(Constant.invPostEvent[mParam1][2][4][0]);
                        break;
                    case 6:
                        llMultiple.setVisibility(View.VISIBLE);
                        ck1.setVisibility(View.VISIBLE);
                        ck2.setVisibility(View.VISIBLE);
                        ck3.setVisibility(View.VISIBLE);
                        ck4.setVisibility(View.VISIBLE);
                        ck5.setVisibility(View.VISIBLE);
                        ck6.setVisibility(View.VISIBLE);
                        ck1.setText(Constant.invPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.invPostEvent[mParam1][2][1][0]);
                        ck3.setText(Constant.invPostEvent[mParam1][2][2][0]);
                        ck4.setText(Constant.invPostEvent[mParam1][2][3][0]);
                        ck5.setText(Constant.invPostEvent[mParam1][2][4][0]);
                        ck6.setText(Constant.invPostEvent[mParam1][2][5][0]);
                        break;
                    case 7:
                        llMultiple.setVisibility(View.VISIBLE);
                        ck1.setVisibility(View.VISIBLE);
                        ck2.setVisibility(View.VISIBLE);
                        ck3.setVisibility(View.VISIBLE);
                        ck4.setVisibility(View.VISIBLE);
                        ck5.setVisibility(View.VISIBLE);
                        ck6.setVisibility(View.VISIBLE);
                        ck7.setVisibility(View.VISIBLE);
                        ck1.setText(Constant.invPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.invPostEvent[mParam1][2][1][0]);
                        ck3.setText(Constant.invPostEvent[mParam1][2][2][0]);
                        ck4.setText(Constant.invPostEvent[mParam1][2][3][0]);
                        ck5.setText(Constant.invPostEvent[mParam1][2][4][0]);
                        ck6.setText(Constant.invPostEvent[mParam1][2][5][0]);
                        ck7.setText(Constant.invPostEvent[mParam1][2][6][0]);
                        break;
                    case 8:
                        llMultiple.setVisibility(View.VISIBLE);
                        ck1.setVisibility(View.VISIBLE);
                        ck2.setVisibility(View.VISIBLE);
                        ck3.setVisibility(View.VISIBLE);
                        ck4.setVisibility(View.VISIBLE);
                        ck5.setVisibility(View.VISIBLE);
                        ck6.setVisibility(View.VISIBLE);
                        ck7.setVisibility(View.VISIBLE);
                        ck8.setVisibility(View.VISIBLE);
                        ck1.setText(Constant.invPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.invPostEvent[mParam1][2][1][0]);
                        ck3.setText(Constant.invPostEvent[mParam1][2][2][0]);
                        ck4.setText(Constant.invPostEvent[mParam1][2][3][0]);
                        ck5.setText(Constant.invPostEvent[mParam1][2][4][0]);
                        ck6.setText(Constant.invPostEvent[mParam1][2][5][0]);
                        ck7.setText(Constant.invPostEvent[mParam1][2][6][0]);
                        ck8.setText(Constant.invPostEvent[mParam1][2][7][0]);
                        break;
                    case 9:
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
                        ck1.setText(Constant.invPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.invPostEvent[mParam1][2][1][0]);
                        ck3.setText(Constant.invPostEvent[mParam1][2][2][0]);
                        ck4.setText(Constant.invPostEvent[mParam1][2][3][0]);
                        ck5.setText(Constant.invPostEvent[mParam1][2][4][0]);
                        ck6.setText(Constant.invPostEvent[mParam1][2][5][0]);
                        ck7.setText(Constant.invPostEvent[mParam1][2][6][0]);
                        ck8.setText(Constant.invPostEvent[mParam1][2][7][0]);
                        ck9.setText(Constant.invPostEvent[mParam1][2][8][0]);
                        break;
                }
                break;
            case "Human Resources Head":
                qNum.setText(questionNum + ".");
                qLabel.setText(Constant.hrPostEvent[mParam1][1][0][0]);
                layout = Integer.parseInt(Constant.hrPostEvent[mParam1][0][0][0]);
                switch(layout){
                    case 1:
                        llSingle.setVisibility(View.VISIBLE);
                        rb1.setVisibility(View.VISIBLE);
                        rb2.setVisibility(View.VISIBLE);
                        choices1.setVisibility(View.VISIBLE);
                        rb1.setText("Yes");
                        rb2.setText("No");
                        choices1.setText(Constant.hrPostEvent[mParam1][2][0][0]);
                        break;
                    case 2:
                        llMultiple.setVisibility(View.VISIBLE);
                        ck1.setVisibility(View.VISIBLE);
                        ck2.setVisibility(View.VISIBLE);
                        ck1.setText(Constant.hrPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.hrPostEvent[mParam1][2][1][0]);
                        break;
                    case 3:
                        llMultiple.setVisibility(View.VISIBLE);
                        ck1.setVisibility(View.VISIBLE);
                        ck2.setVisibility(View.VISIBLE);
                        ck3.setVisibility(View.VISIBLE);
                        ck1.setText(Constant.hrPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.hrPostEvent[mParam1][2][1][0]);
                        ck3.setText(Constant.hrPostEvent[mParam1][2][2][0]);
                        break;
                    case 4:
                        llMultiple.setVisibility(View.VISIBLE);
                        ck1.setVisibility(View.VISIBLE);
                        ck2.setVisibility(View.VISIBLE);
                        ck3.setVisibility(View.VISIBLE);
                        ck4.setVisibility(View.VISIBLE);
                        ck1.setText(Constant.hrPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.hrPostEvent[mParam1][2][1][0]);
                        ck3.setText(Constant.hrPostEvent[mParam1][2][2][0]);
                        ck4.setText(Constant.hrPostEvent[mParam1][2][3][0]);
                        break;
                    case 5:
                        llMultiple.setVisibility(View.VISIBLE);
                        ck1.setVisibility(View.VISIBLE);
                        ck2.setVisibility(View.VISIBLE);
                        ck3.setVisibility(View.VISIBLE);
                        ck4.setVisibility(View.VISIBLE);
                        ck5.setVisibility(View.VISIBLE);
                        ck1.setText(Constant.hrPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.hrPostEvent[mParam1][2][1][0]);
                        ck3.setText(Constant.hrPostEvent[mParam1][2][2][0]);
                        ck4.setText(Constant.hrPostEvent[mParam1][2][3][0]);
                        ck5.setText(Constant.hrPostEvent[mParam1][2][4][0]);
                        break;
                    case 6:
                        llMultiple.setVisibility(View.VISIBLE);
                        ck1.setVisibility(View.VISIBLE);
                        ck2.setVisibility(View.VISIBLE);
                        ck3.setVisibility(View.VISIBLE);
                        ck4.setVisibility(View.VISIBLE);
                        ck5.setVisibility(View.VISIBLE);
                        ck6.setVisibility(View.VISIBLE);
                        ck1.setText(Constant.hrPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.hrPostEvent[mParam1][2][1][0]);
                        ck3.setText(Constant.hrPostEvent[mParam1][2][2][0]);
                        ck4.setText(Constant.hrPostEvent[mParam1][2][3][0]);
                        ck5.setText(Constant.hrPostEvent[mParam1][2][4][0]);
                        ck6.setText(Constant.hrPostEvent[mParam1][2][5][0]);
                        break;
                    case 7:
                        llMultiple.setVisibility(View.VISIBLE);
                        ck1.setVisibility(View.VISIBLE);
                        ck2.setVisibility(View.VISIBLE);
                        ck3.setVisibility(View.VISIBLE);
                        ck4.setVisibility(View.VISIBLE);
                        ck5.setVisibility(View.VISIBLE);
                        ck6.setVisibility(View.VISIBLE);
                        ck7.setVisibility(View.VISIBLE);
                        ck1.setText(Constant.hrPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.hrPostEvent[mParam1][2][1][0]);
                        ck3.setText(Constant.hrPostEvent[mParam1][2][2][0]);
                        ck4.setText(Constant.hrPostEvent[mParam1][2][3][0]);
                        ck5.setText(Constant.hrPostEvent[mParam1][2][4][0]);
                        ck6.setText(Constant.hrPostEvent[mParam1][2][5][0]);
                        ck7.setText(Constant.hrPostEvent[mParam1][2][6][0]);
                        break;
                    case 8:
                        llMultiple.setVisibility(View.VISIBLE);
                        ck1.setVisibility(View.VISIBLE);
                        ck2.setVisibility(View.VISIBLE);
                        ck3.setVisibility(View.VISIBLE);
                        ck4.setVisibility(View.VISIBLE);
                        ck5.setVisibility(View.VISIBLE);
                        ck6.setVisibility(View.VISIBLE);
                        ck7.setVisibility(View.VISIBLE);
                        ck8.setVisibility(View.VISIBLE);
                        ck1.setText(Constant.hrPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.hrPostEvent[mParam1][2][1][0]);
                        ck3.setText(Constant.hrPostEvent[mParam1][2][2][0]);
                        ck4.setText(Constant.hrPostEvent[mParam1][2][3][0]);
                        ck5.setText(Constant.hrPostEvent[mParam1][2][4][0]);
                        ck6.setText(Constant.hrPostEvent[mParam1][2][5][0]);
                        ck7.setText(Constant.hrPostEvent[mParam1][2][6][0]);
                        ck8.setText(Constant.hrPostEvent[mParam1][2][7][0]);
                        break;
                    case 9:
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
                        ck1.setText(Constant.hrPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.hrPostEvent[mParam1][2][1][0]);
                        ck3.setText(Constant.hrPostEvent[mParam1][2][2][0]);
                        ck4.setText(Constant.hrPostEvent[mParam1][2][3][0]);
                        ck5.setText(Constant.hrPostEvent[mParam1][2][4][0]);
                        ck6.setText(Constant.hrPostEvent[mParam1][2][5][0]);
                        ck7.setText(Constant.hrPostEvent[mParam1][2][6][0]);
                        ck8.setText(Constant.hrPostEvent[mParam1][2][7][0]);
                        ck9.setText(Constant.hrPostEvent[mParam1][2][8][0]);
                        break;
                }
                break;
            case "Production Representative":
                qNum.setText(questionNum + ".");
                qLabel.setText(Constant.prPostEvent[mParam1][1][0][0]);
                layout = Integer.parseInt(Constant.prPostEvent[mParam1][0][0][0]);
                switch(layout){
                    case 1:
                        llSingle.setVisibility(View.VISIBLE);
                        rb1.setVisibility(View.VISIBLE);
                        rb2.setVisibility(View.VISIBLE);
                        choices1.setVisibility(View.VISIBLE);
                        rb1.setText("Yes");
                        rb2.setText("No");
                        choices1.setText(Constant.prPostEvent[mParam1][2][0][0]);
                        break;
                    case 2:
                        llMultiple.setVisibility(View.VISIBLE);
                        ck1.setVisibility(View.VISIBLE);
                        ck2.setVisibility(View.VISIBLE);
                        ck1.setText(Constant.prPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.prPostEvent[mParam1][2][1][0]);
                        break;
                    case 3:
                        llMultiple.setVisibility(View.VISIBLE);
                        ck1.setVisibility(View.VISIBLE);
                        ck2.setVisibility(View.VISIBLE);
                        ck3.setVisibility(View.VISIBLE);
                        ck1.setText(Constant.prPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.prPostEvent[mParam1][2][1][0]);
                        ck3.setText(Constant.prPostEvent[mParam1][2][2][0]);
                        break;
                    case 4:
                        llMultiple.setVisibility(View.VISIBLE);
                        ck1.setVisibility(View.VISIBLE);
                        ck2.setVisibility(View.VISIBLE);
                        ck3.setVisibility(View.VISIBLE);
                        ck4.setVisibility(View.VISIBLE);
                        ck1.setText(Constant.prPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.prPostEvent[mParam1][2][1][0]);
                        ck3.setText(Constant.prPostEvent[mParam1][2][2][0]);
                        ck4.setText(Constant.prPostEvent[mParam1][2][3][0]);
                        break;
                    case 5:
                        llMultiple.setVisibility(View.VISIBLE);
                        ck1.setVisibility(View.VISIBLE);
                        ck2.setVisibility(View.VISIBLE);
                        ck3.setVisibility(View.VISIBLE);
                        ck4.setVisibility(View.VISIBLE);
                        ck5.setVisibility(View.VISIBLE);
                        ck1.setText(Constant.prPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.prPostEvent[mParam1][2][1][0]);
                        ck3.setText(Constant.prPostEvent[mParam1][2][2][0]);
                        ck4.setText(Constant.prPostEvent[mParam1][2][3][0]);
                        ck5.setText(Constant.prPostEvent[mParam1][2][4][0]);
                        break;
                    case 6:
                        llMultiple.setVisibility(View.VISIBLE);
                        ck1.setVisibility(View.VISIBLE);
                        ck2.setVisibility(View.VISIBLE);
                        ck3.setVisibility(View.VISIBLE);
                        ck4.setVisibility(View.VISIBLE);
                        ck5.setVisibility(View.VISIBLE);
                        ck6.setVisibility(View.VISIBLE);
                        ck1.setText(Constant.prPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.prPostEvent[mParam1][2][1][0]);
                        ck3.setText(Constant.prPostEvent[mParam1][2][2][0]);
                        ck4.setText(Constant.prPostEvent[mParam1][2][3][0]);
                        ck5.setText(Constant.prPostEvent[mParam1][2][4][0]);
                        ck6.setText(Constant.prPostEvent[mParam1][2][5][0]);
                        break;
                    case 7:
                        llMultiple.setVisibility(View.VISIBLE);
                        ck1.setVisibility(View.VISIBLE);
                        ck2.setVisibility(View.VISIBLE);
                        ck3.setVisibility(View.VISIBLE);
                        ck4.setVisibility(View.VISIBLE);
                        ck5.setVisibility(View.VISIBLE);
                        ck6.setVisibility(View.VISIBLE);
                        ck7.setVisibility(View.VISIBLE);
                        ck1.setText(Constant.prPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.prPostEvent[mParam1][2][1][0]);
                        ck3.setText(Constant.prPostEvent[mParam1][2][2][0]);
                        ck4.setText(Constant.prPostEvent[mParam1][2][3][0]);
                        ck5.setText(Constant.prPostEvent[mParam1][2][4][0]);
                        ck6.setText(Constant.prPostEvent[mParam1][2][5][0]);
                        ck7.setText(Constant.prPostEvent[mParam1][2][6][0]);
                        break;
                    case 8:
                        llMultiple.setVisibility(View.VISIBLE);
                        ck1.setVisibility(View.VISIBLE);
                        ck2.setVisibility(View.VISIBLE);
                        ck3.setVisibility(View.VISIBLE);
                        ck4.setVisibility(View.VISIBLE);
                        ck5.setVisibility(View.VISIBLE);
                        ck6.setVisibility(View.VISIBLE);
                        ck7.setVisibility(View.VISIBLE);
                        ck8.setVisibility(View.VISIBLE);
                        ck1.setText(Constant.prPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.prPostEvent[mParam1][2][1][0]);
                        ck3.setText(Constant.prPostEvent[mParam1][2][2][0]);
                        ck4.setText(Constant.prPostEvent[mParam1][2][3][0]);
                        ck5.setText(Constant.prPostEvent[mParam1][2][4][0]);
                        ck6.setText(Constant.prPostEvent[mParam1][2][5][0]);
                        ck7.setText(Constant.prPostEvent[mParam1][2][6][0]);
                        ck8.setText(Constant.prPostEvent[mParam1][2][7][0]);
                        break;
                    case 9:
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
                        ck1.setText(Constant.prPostEvent[mParam1][2][0][0]);
                        ck2.setText(Constant.prPostEvent[mParam1][2][1][0]);
                        ck3.setText(Constant.prPostEvent[mParam1][2][2][0]);
                        ck4.setText(Constant.prPostEvent[mParam1][2][3][0]);
                        ck5.setText(Constant.prPostEvent[mParam1][2][4][0]);
                        ck6.setText(Constant.prPostEvent[mParam1][2][5][0]);
                        ck7.setText(Constant.prPostEvent[mParam1][2][6][0]);
                        ck8.setText(Constant.prPostEvent[mParam1][2][7][0]);
                        ck9.setText(Constant.prPostEvent[mParam1][2][8][0]);
                        break;
                }
                break;
        }
        return v;
    }

    @OnClick({R.id.rb1,R.id.rb2,R.id.rb3,R.id.rb4,R.id.rb5,R.id.rb6,R.id.rb7,R.id.rb8,R.id.rb9,R.id.ck1,R.id.ck2,R.id.ck3,R.id.ck4,R.id.ck5,R.id.ck6,R.id.ck7,R.id.ck8,R.id.ck9})
    public void getAnswer(View v){
        int questionNumMax = mParam1 + 1;
        String ansNum = "";
        SharedPreferences.Editor editor = sharedPreference.edit();
        int EVENTID = sharedPreference.getInt("EVENTID", 0);
        String EVENTCATEGORY = sharedPreference.getString("EVENTCATEGORY", "");
        String jsonArrayData = sharedPreference.getString("jsonData", "");
        String EID = sharedPreference.getString("EID", "");
        JSONObject records = new JSONObject();
        JSONArray recArray = new JSONArray();
        try{
            if(jsonArrayData != null){
                recArray = new JSONArray(jsonArrayData);
            }
            //records.put("eid",EID);
            //records.put("qcat",EVENTCATEGORY);
            //records.put("qevent",EVENTID);
            //records.put("qid",quesId);
            //records.put("qans",Integer.parseInt(v.getContentDescription().toString()));
        }catch(JSONException e){
            e.printStackTrace();
        }
        //recArray.put(records);
        //editor.putString("jsonData", recArray.toString());
        mAuthTask = new UserLoginTask(EID +"", EVENTCATEGORY.replace(" ", "")+"", ""+EVENTID, quesId+"", ""+Integer.parseInt(v.getContentDescription().toString()));
        mAuthTask.execute((Void) null);
        int currentPage = vp.getCurrentItem() + 1;
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
        }
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

    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String meid;
        private final String mqcat;
        private final String mqevent;
        private final String mqid;
        private final String mqans;
        private String mEvaluate = "";
        private String mRoles = "";
        String link;
        String data;
        BufferedReader bufferedReader;
        String result;

        UserLoginTask(String eid, String qcat, String qevent, String qid, String qans) {
            meid = eid;
            mqcat = qcat;
            mqevent = qevent;
            mqid = qid;
            mqans = qans;
        }

        @Override
        protected void onPreExecute() {
            // Things to be done before execution of long running operation. For
            // example showing ProgessDialog
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                data = URLEncoder.encode(meid, "UTF-8");
                data += "/" + URLEncoder.encode(mqcat, "UTF-8");
                data += "/" + URLEncoder.encode(mqevent, "UTF-8");
                data += "/" + URLEncoder.encode(mqid, "UTF-8");
                data += "/" + URLEncoder.encode(mqans, "UTF-8");
                link = Constant.baseUrl + "employee/syncdata/" + data;
                URL url = new URL(link);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                result = bufferedReader.readLine();

            } catch (Exception e) {
                e.printStackTrace();
                result = "";
            }
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            String jsonStr = result;
            mAuthTask = null;
            //Toast.makeText(getActivity(), "URL = " + link, Toast.LENGTH_LONG).show();
            //showProgress(false);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    String query_result = jsonObj.getString("query_result");
                    if (query_result.equals("SUCCESS")) {
                        String recordData = jsonObj.getString("record");
                        //Toast.makeText(getActivity(), "RECORD SEE"+ recordData, Toast.LENGTH_SHORT).show();
                    } else if (query_result.equals("FAILURE")) {
                        Toast.makeText(getActivity(), "RECORD NOT FOUND", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), "RECORD NOT FOUND.", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Error parsing JSON data.", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(getActivity(), "Couldn't get any JSON data.", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onCancelled(){
            mAuthTask = null;
            //showProgress(false);
        }
    }
}
