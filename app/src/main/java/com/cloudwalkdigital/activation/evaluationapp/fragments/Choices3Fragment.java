package com.cloudwalkdigital.activation.evaluationapp.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.cloudwalkdigital.activation.evaluationapp.R;
import com.cloudwalkdigital.activation.evaluationapp.constant.Constant;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link Choices3Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Choices3Fragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private int mParam1;
    private String mParam2;

    @Bind(R.id.choices1) TextView choices1;
    @Bind(R.id.rb1_yes) RadioButton rb1_yes;
    @Bind(R.id.rb2_yes) RadioButton rb2_yes;

    public Choices3Fragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Choices3Fragment newInstance(int param1, String param2) {
        Choices3Fragment fragment = new Choices3Fragment();
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
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = null;

        RadioButton rbChoices1,rbChoices2,rbChoices3,rbChoices4,rbChoices5,rbChoices6,rbChoices7,rbChoices8;

        switch(Constant.accountsQuestion[mParam1][0][0][0]){
            case "1":
                v = inflater.inflate(R.layout.fragment_choices1, container, false);
                ButterKnife.bind(this,v);
                rb1_yes.setOnClickListener(this);
                rb2_yes.setOnClickListener(this);
                switch(mParam2){
                    case "accounts":
                        choices1.setText(Constant.accountsQuestion[mParam1][2][0][0]);
                        break;
                }
                break;
            case "2":
                v = inflater.inflate(R.layout.fragment_choices2, container, false);
                ButterKnife.bind(this,v);
                rb1_yes.setOnClickListener(this);
                rb2_yes.setOnClickListener(this);
                switch(mParam2){
                    case "accounts":
                        rb1_yes.setText(Constant.accountsQuestion[mParam1][2][0][0]);
                        rb2_yes.setText(Constant.accountsQuestion[mParam1][2][1][0]);
                        break;
                }
                break;
            case "3":
                v = inflater.inflate(R.layout.fragment_choices3, container, false);
                rbChoices1 = (RadioButton) v.findViewById(R.id.rbChoices1);
                rbChoices2 = (RadioButton) v.findViewById(R.id.rbChoices2);
                rbChoices3 = (RadioButton) v.findViewById(R.id.rbChoices3);
                rbChoices1.setOnClickListener(this);
                rbChoices2.setOnClickListener(this);
                rbChoices3.setOnClickListener(this);
                switch(mParam2){
                    case "accounts":
                        rbChoices1.setText(Constant.accountsQuestion[mParam1][2][0][0]);
                        rbChoices2.setText(Constant.accountsQuestion[mParam1][2][1][0]);
                        rbChoices3.setText(Constant.accountsQuestion[mParam1][2][2][0]);
                        break;
                }
                break;
            case "4":
                v = inflater.inflate(R.layout.fragment_choices4, container, false);
                rbChoices1 = (RadioButton) v.findViewById(R.id.rbChoices1);
                rbChoices2 = (RadioButton) v.findViewById(R.id.rbChoices2);
                rbChoices3 = (RadioButton) v.findViewById(R.id.rbChoices3);
                rbChoices4 = (RadioButton) v.findViewById(R.id.rbChoices4);
                rbChoices1.setOnClickListener(this);
                rbChoices1.setOnClickListener(this);
                rbChoices2.setOnClickListener(this);
                rbChoices3.setOnClickListener(this);
                rbChoices4.setOnClickListener(this);
                switch(mParam2){
                    case "accounts":
                        rbChoices1.setText(Constant.accountsQuestion[mParam1][2][0][0]);
                        rbChoices2.setText(Constant.accountsQuestion[mParam1][2][1][0]);
                        rbChoices3.setText(Constant.accountsQuestion[mParam1][2][2][0]);
                        rbChoices4.setText(Constant.accountsQuestion[mParam1][2][3][0]);
                        break;
                }
                break;
            case "5":
                v = inflater.inflate(R.layout.fragment_choices5, container, false);
                rbChoices1 = (RadioButton) v.findViewById(R.id.rbChoices1);
                rbChoices2 = (RadioButton) v.findViewById(R.id.rbChoices2);
                rbChoices3 = (RadioButton) v.findViewById(R.id.rbChoices3);
                rbChoices4 = (RadioButton) v.findViewById(R.id.rbChoices4);
                rbChoices5 = (RadioButton) v.findViewById(R.id.rbChoices5);
                rbChoices1.setOnClickListener(this);
                rbChoices2.setOnClickListener(this);
                rbChoices3.setOnClickListener(this);
                rbChoices4.setOnClickListener(this);
                rbChoices5.setOnClickListener(this);
                switch(mParam2){
                    case "accounts":
                        rbChoices1.setText(Constant.accountsQuestion[mParam1][2][0][0]);
                        rbChoices2.setText(Constant.accountsQuestion[mParam1][2][1][0]);
                        rbChoices3.setText(Constant.accountsQuestion[mParam1][2][2][0]);
                        rbChoices4.setText(Constant.accountsQuestion[mParam1][2][3][0]);
                        rbChoices5.setText(Constant.accountsQuestion[mParam1][2][4][0]);
                        break;
                }
                break;
            case "6":
                v = inflater.inflate(R.layout.fragment_choices6, container, false);
                rbChoices1 = (RadioButton) v.findViewById(R.id.rbChoices1);
                rbChoices2 = (RadioButton) v.findViewById(R.id.rbChoices2);
                rbChoices3 = (RadioButton) v.findViewById(R.id.rbChoices3);
                rbChoices4 = (RadioButton) v.findViewById(R.id.rbChoices4);
                rbChoices5 = (RadioButton) v.findViewById(R.id.rbChoices5);
                rbChoices6 = (RadioButton) v.findViewById(R.id.rbChoices6);
                rbChoices1.setOnClickListener(this);
                rbChoices2.setOnClickListener(this);
                rbChoices3.setOnClickListener(this);
                rbChoices4.setOnClickListener(this);
                rbChoices5.setOnClickListener(this);
                rbChoices6.setOnClickListener(this);
                switch(mParam2){
                    case "accounts":
                        rbChoices1.setText(Constant.accountsQuestion[mParam1][2][0][0]);
                        rbChoices2.setText(Constant.accountsQuestion[mParam1][2][1][0]);
                        rbChoices3.setText(Constant.accountsQuestion[mParam1][2][2][0]);
                        rbChoices4.setText(Constant.accountsQuestion[mParam1][2][3][0]);
                        rbChoices5.setText(Constant.accountsQuestion[mParam1][2][4][0]);
                        rbChoices6.setText(Constant.accountsQuestion[mParam1][2][5][0]);
                        break;
                }
                break;
            case "7":
                v = inflater.inflate(R.layout.fragment_choices7, container, false);
                rbChoices1 = (RadioButton) v.findViewById(R.id.rbChoices1);
                rbChoices2 = (RadioButton) v.findViewById(R.id.rbChoices2);
                rbChoices3 = (RadioButton) v.findViewById(R.id.rbChoices3);
                rbChoices4 = (RadioButton) v.findViewById(R.id.rbChoices4);
                rbChoices5 = (RadioButton) v.findViewById(R.id.rbChoices5);
                rbChoices6 = (RadioButton) v.findViewById(R.id.rbChoices6);
                rbChoices7 = (RadioButton) v.findViewById(R.id.rbChoices7);
                rbChoices1.setOnClickListener(this);
                rbChoices2.setOnClickListener(this);
                rbChoices3.setOnClickListener(this);
                rbChoices4.setOnClickListener(this);
                rbChoices5.setOnClickListener(this);
                rbChoices6.setOnClickListener(this);
                rbChoices7.setOnClickListener(this);
                switch(mParam2){
                    case "accounts":
                        rbChoices1.setText(Constant.accountsQuestion[mParam1][2][0][0]);
                        rbChoices2.setText(Constant.accountsQuestion[mParam1][2][1][0]);
                        rbChoices3.setText(Constant.accountsQuestion[mParam1][2][2][0]);
                        rbChoices4.setText(Constant.accountsQuestion[mParam1][2][3][0]);
                        rbChoices5.setText(Constant.accountsQuestion[mParam1][2][4][0]);
                        rbChoices6.setText(Constant.accountsQuestion[mParam1][2][5][0]);
                        rbChoices7.setText(Constant.accountsQuestion[mParam1][2][6][0]);
                        break;
                }
                break;
            case "8":
                v = inflater.inflate(R.layout.fragment_choices8, container, false);
                rbChoices1 = (RadioButton) v.findViewById(R.id.rbChoices1);
                rbChoices2 = (RadioButton) v.findViewById(R.id.rbChoices2);
                rbChoices3 = (RadioButton) v.findViewById(R.id.rbChoices3);
                rbChoices4 = (RadioButton) v.findViewById(R.id.rbChoices4);
                rbChoices5 = (RadioButton) v.findViewById(R.id.rbChoices5);
                rbChoices6 = (RadioButton) v.findViewById(R.id.rbChoices6);
                rbChoices7 = (RadioButton) v.findViewById(R.id.rbChoices7);
                rbChoices8 = (RadioButton) v.findViewById(R.id.rbChoices8);
                rbChoices1.setOnClickListener(this);
                rbChoices2.setOnClickListener(this);
                rbChoices3.setOnClickListener(this);
                rbChoices4.setOnClickListener(this);
                rbChoices5.setOnClickListener(this);
                rbChoices6.setOnClickListener(this);
                rbChoices7.setOnClickListener(this);
                rbChoices8.setOnClickListener(this);
                switch(mParam2){
                    case "accounts":
                        rbChoices1.setText(Constant.accountsQuestion[mParam1][2][0][0]);
                        rbChoices2.setText(Constant.accountsQuestion[mParam1][2][1][0]);
                        rbChoices3.setText(Constant.accountsQuestion[mParam1][2][2][0]);
                        rbChoices4.setText(Constant.accountsQuestion[mParam1][2][3][0]);
                        rbChoices5.setText(Constant.accountsQuestion[mParam1][2][4][0]);
                        rbChoices6.setText(Constant.accountsQuestion[mParam1][2][5][0]);
                        rbChoices7.setText(Constant.accountsQuestion[mParam1][2][6][0]);
                        rbChoices8.setText(Constant.accountsQuestion[mParam1][2][7][0]);
                        break;
                }
                break;
        }
        TextView qNum = (TextView) v.findViewById(R.id.questionHead_num);
        TextView question = (TextView) v.findViewById(R.id.questionHead_label);
        int questionNum = mParam1 + 1;
        switch(mParam2){
            case "accounts":
                qNum.setText(questionNum + ".");
                question.setText(Constant.accountsQuestion[mParam1][1][0][0]);
                break;
        }
        return v;
    }

    @Override
    public void onClick(View v) {
        int layout = Integer.parseInt(Constant.accountsQuestion[mParam1][0][0][0]);
        switch (v.getId()){
            case R.id.rb1_yes:
                if(layout != 1){
                    if(rb1_yes.isChecked()){
                        rb1_yes.setChecked(true);
                    }else{

                    }
                }
                break;
            case R.id.rb2_yes:
                if(layout != 1){
                    if(rb2_yes.isChecked()){
                        rb2_yes.setChecked(false);
                    }
                }
                break;
            case R.id.rbChoices3:
                Toast.makeText(getContext(),"Radio 3",Toast.LENGTH_LONG).show();
                break;
            case R.id.rbChoices4:
                Toast.makeText(getContext(),"Radio 4",Toast.LENGTH_LONG).show();
                break;
            case R.id.rbChoices5:
                Toast.makeText(getContext(),"Radio 5",Toast.LENGTH_LONG).show();
                break;
            case R.id.rbChoices6:
                Toast.makeText(getContext(),"Radio 6",Toast.LENGTH_LONG).show();
                break;
            case R.id.rbChoices7:
                Toast.makeText(getContext(),"Radio 7",Toast.LENGTH_LONG).show();
                break;
            case R.id.rbChoices8:
                Toast.makeText(getContext(),"Radio 8",Toast.LENGTH_LONG).show();
                break;
        }

    }
}

