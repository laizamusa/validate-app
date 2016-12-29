package com.cloudwalkdigital.activation.evaluationapp.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.cloudwalkdigital.activation.evaluationapp.R;
import com.cloudwalkdigital.activation.evaluationapp.utils.BarGraph;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResultActivity extends AppCompatActivity {

    @Bind(R.id.chart)
    BarChart bGraph;
    BarData bDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ButterKnife.bind(this);
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(4f, 0));
        entries.add(new BarEntry(8f, 1));
        entries.add(new BarEntry(6f, 2));
        entries.add(new BarEntry(12f, 3));
        entries.add(new BarEntry(18f, 4));
        entries.add(new BarEntry(9f, 5));
        ArrayList<String> labels = new ArrayList<String>();
        labels.add("Accounts");
        labels.add("Operations");
        labels.add("Negotiators Assessment");
        labels.add("Setup");
        labels.add("Production");
        labels.add("Inventory");
        BarDataSet barDataSet1 = new BarDataSet(entries, "Brand 1");
        BarData data = new BarData(labels, barDataSet1);
        bGraph.setData(data);
        //setContentView(bGraph);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        this.invalidateOptionsMenu();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch(item.getItemId()){
            case R.id.action_logout:
                startActivity(new Intent(getApplicationContext(), LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
