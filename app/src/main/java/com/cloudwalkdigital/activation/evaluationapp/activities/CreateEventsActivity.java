package com.cloudwalkdigital.activation.evaluationapp.activities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.cloudwalkdigital.activation.evaluationapp.R;
import com.cloudwalkdigital.activation.evaluationapp.models.EmployeeModel;
import com.cloudwalkdigital.activation.evaluationapp.models.EventModel;

import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class CreateEventsActivity extends AppCompatActivity{

    private Realm realm;
    private RealmConfiguration realmConfig;


    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.et_name)
    EditText etName;
    @Bind(R.id.et_jonum)EditText etJonum;
    //@Bind(R.id.et_eventdate)EditText etEventdate;
    @Bind(R.id.showMyDate) TextView showMyDate;
    private int mYear;
    private int mMonth;
    private int mDay;
    private DatePicker datePicker;
    private Calendar calendar;

    static final int DATE_DIALOG_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_events);
        ButterKnife.bind(this);

        realmConfig = new RealmConfiguration.Builder(getApplicationContext()).build();
        realm = Realm.getInstance(realmConfig);
        //setSupportActionBar(toolbar);
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        // display the current date
        showDate(mYear, mMonth+1, mDay);

    }

    public void setDate(View view) {
        showDialog(999);
        Toast.makeText(getApplicationContext(), "ca", Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, mYear, mMonth, mDay);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
            // arg1 = year
            // arg2 = month
            // arg3 = day
            showDate(arg1, arg2+1, arg3);
        }
    };

    private void showDate(int year, int month, int day) {
        showMyDate.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }

    @OnClick(R.id.tv_done)
    public void Done() {
        String name = etName.getText().toString();
        String email = etJonum.getText().toString();
        String eDate = showMyDate.getText().toString();

        Close();
    }

    @OnClick(R.id.myDatePickerButton)
    public void SetTheDate(View v){
        setDate(v);
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
