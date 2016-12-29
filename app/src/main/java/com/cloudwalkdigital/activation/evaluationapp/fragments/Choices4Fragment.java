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

import com.cloudwalkdigital.activation.evaluationapp.R;
import com.cloudwalkdigital.activation.evaluationapp.constant.Constant;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link Choices4Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Choices4Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private int mParam1;
    private String mParam2;


    public Choices4Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Choices4Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Choices4Fragment newInstance(int param1, String param2) {
        Choices4Fragment fragment = new Choices4Fragment();
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
        View v = inflater.inflate(R.layout.fragment_choices4, container, false);
        RadioButton rbChoices1 = (RadioButton) v.findViewById(R.id.rbChoices1);
        RadioButton rbChoices2 = (RadioButton) v.findViewById(R.id.rbChoices2);
        RadioButton rbChoices3 = (RadioButton) v.findViewById(R.id.rbChoices3);
        TextView qNum = (TextView) v.findViewById(R.id.questionHead_num);
        TextView question = (TextView) v.findViewById(R.id.questionHead_label);
        int questionNum = mParam1 + 1;
        switch(mParam2){
            case "accounts":
                qNum.setText(questionNum + ".");
                question.setText(Constant.accountsQuestion[mParam1][1][0][0]);
                rbChoices1.setText(Constant.accountsQuestion[mParam1][2][0][0]);
                rbChoices2.setText(Constant.accountsQuestion[mParam1][2][1][0]);
                rbChoices3.setText(Constant.accountsQuestion[mParam1][2][2][0]);
                break;
        }
        return v;
    }

}
