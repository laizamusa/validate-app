package com.cloudwalkdigital.activation.evaluationapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.cloudwalkdigital.activation.evaluationapp.R;
import com.cloudwalkdigital.activation.evaluationapp.activities.Category;
import com.cloudwalkdigital.activation.evaluationapp.activities.LoginActivity;
import com.cloudwalkdigital.activation.evaluationapp.activities.QuestionActivity;
import com.cloudwalkdigital.activation.evaluationapp.models.EventModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.StringTokenizer;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by henry on 11/05/2016.
 */
public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ItemHolder> {
    private List<EventModel> eventsModels;
    public static Context context;

    public static final String PREFS_NAME = "MyPref";
    static SharedPreferences sharedPreference;
    SharedPreferences.Editor editor;

    public ProjectAdapter(List<EventModel> eventsModels) {
        this.eventsModels = eventsModels;
    }

    @Override
    public ProjectAdapter.ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.row_events, parent, false);
        context = parent.getContext();
        sharedPreference = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return new ProjectAdapter.ItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProjectAdapter.ItemHolder holder, int position) {
        holder.tvName.setText(eventsModels.get(position).getName());
        holder.tvEmail.setText(eventsModels.get(position).getJonum());
        holder.tvDepartment.setText("Expiration: "+ eventsModels.get(position).getEventdate());
        holder.tvId.setText(eventsModels.get(position).getId()+"");
        holder.tvExpires.setText(eventsModels.get(position).getEventdate()+"");
    }

    @Override
    public int getItemCount() {
        return (null != eventsModels ? eventsModels.size() : 0);
    }

    public static class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @Bind(R.id.tv_name)         TextView tvName;
        @Bind(R.id.tv_job_title)    TextView tvEmail;
        @Bind(R.id.tv_eventdate)    TextView tvDepartment;
        @Bind(R.id.tv_id)           TextView tvId;
        @Bind(R.id.event_expires)   TextView tvExpires;

        public ItemHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ButterKnife.bind(this, itemView);
        }

        public void onClick(View view) {
            //Toast.makeText(vg,"sampel"+ tvName.getText(),Toast.LENGTH_SHORT).show();
            //Toast.makeText(context,""+tvName.getText(),Toast.LENGTH_LONG).show();
            SharedPreferences.Editor editor = sharedPreference.edit();
            editor.putString("EVENTNAME", tvName.getText()+"");
            editor.putInt("EVENTID", Integer.parseInt(tvId.getText()+""));
            editor.commit();
            context.startActivity(new Intent(context, Category.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            ((Activity)context).finish();
        }
    }
}
