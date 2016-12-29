package com.cloudwalkdigital.activation.evaluationapp.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.cloudwalkdigital.activation.evaluationapp.R;
import com.cloudwalkdigital.activation.evaluationapp.activities.CreateEmployeeActivity;
import com.cloudwalkdigital.activation.evaluationapp.adapter.EmployeeAdapter;
import com.cloudwalkdigital.activation.evaluationapp.models.EmployeeModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by jpjpjp28 on 14/04/2016.
 */
public class EmployeesFragment extends Fragment {

    private Realm realm;
    private RealmConfiguration realmConfig;

    String name, jobTitle, role;
    private List<EmployeeModel> listEmployeeModels;
    private EmployeeAdapter employeeAdapter;

    @Bind(R.id.rv_list) RecyclerView rvList;

    public EmployeesFragment() {
        // Required pty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard_create_employee, container, false);
        ButterKnife.bind(this, view);

        realmConfig = new RealmConfiguration.Builder(getContext()).build();
        realm = Realm.getInstance(realmConfig);

        rvList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvList.setLayoutManager(llm);


        return view;
    }

    public void AddEmployee() {
        startActivity(new Intent(getContext(), CreateEmployeeActivity.class));
    }

    private void AddEmployeeToDatabase() {

    }
}
