package com.cloudwalkdigital.activation.evaluationapp.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cloudwalkdigital.activation.evaluationapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link AccountsFragment_q1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountsFragment_q1 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AccountsFragment_q1() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static AccountsFragment_q1 newInstance(String param1) {
        AccountsFragment_q1 fragment = new AccountsFragment_q1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_accounts_fragment_q1, container, false);
    }
}
