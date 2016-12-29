package com.cloudwalkdigital.activation.evaluationapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.cloudwalkdigital.activation.evaluationapp.R;
import com.cloudwalkdigital.activation.evaluationapp.models.EmployeeModel;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by jpjpjp28 on 15/04/2016.
 */
public class CreateEmployeeActivity extends AppCompatActivity {

    String[] arrayDepartment = { "Account Executive", "Operations", "Project Manager", "Activations Head", "Setup Head", "CEO",
            "Accounting", "Inventory Head", "Human Resources Head", "Production Representative" };

    private Realm realm;
    private RealmConfiguration realmConfig;

    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.et_name) EditText etName;
    @Bind(R.id.et_email)EditText etEmail;
    @Bind(R.id.spinner) AppCompatSpinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_employee);
        ButterKnife.bind(this);

        //Configure the realm database
        realmConfig = new RealmConfiguration.Builder(getApplicationContext()).build();
        realm = Realm.getInstance(realmConfig);

        //Setup toolbar
        setSupportActionBar(toolbar);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayDepartment);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(aa);
    }

    @OnClick(R.id.tv_done)
    public void Done() {
        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        String department = spinner.getSelectedItem().toString();

        //Add employee to realm database
        realm.beginTransaction();
        //EmployeeModel employeeModel = realm.createObject(EmployeeModel.class);
        EmployeeModel employeeModel = new EmployeeModel();
        //EmployeeModel employeeModel = realm.allObjects(EmployeeModel.class);
        long key = 0;


        Close();
    }

    @OnClick(R.id.iv_x)
    public void Close() {
        finish();
    }

    @Override
    public void finish() {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("passed_item", "OK_PASSED");
        setResult(RESULT_OK, returnIntent);
        super.finish();
    }
}
