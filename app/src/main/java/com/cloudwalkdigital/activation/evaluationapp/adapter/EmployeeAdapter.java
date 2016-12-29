package com.cloudwalkdigital.activation.evaluationapp.adapter;

import android.content.Context;
import android.support.v7.widget.ContentFrameLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.cloudwalkdigital.activation.evaluationapp.R;
import com.cloudwalkdigital.activation.evaluationapp.models.EmployeeModel;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jpjpjp28 on 15/04/2016.
 */
public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {

    List<EmployeeModel> employeeModels;
    static Context vg;

    public EmployeeAdapter(List<EmployeeModel> employeeModels) {
        this.employeeModels = employeeModels;
    }

    @Override
    public EmployeeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.row_employee, parent, false);
        vg = parent.getContext();
        return new EmployeeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(EmployeeViewHolder holder, int position) {
        //holder.tvName.setText(employeeModels.get(position).getName());
        //holder.tvEmail.setText(employeeModels.get(position).getEmail()+" Password:" + employeeModels.get(position).getPassword());
        //holder.tvEmail.setText(employeeModels.get(position).getId()+"");
        //holder.tvDepartment.setText(employeeModels.get(position).getDepartment());
    }

    @Override
    public int getItemCount() {
        return employeeModels.size();
    }

    public static class EmployeeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @Bind(R.id.tv_name) TextView tvName;
        @Bind(R.id.tv_job_title) TextView tvEmail;
        @Bind(R.id.tv_department) TextView tvDepartment;

        public EmployeeViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ButterKnife.bind(this, itemView);
        }

        public void onClick(View view) {
            Toast.makeText(vg,"sampel"+ tvName.getText(),Toast.LENGTH_SHORT).show();
        }

    }
}
