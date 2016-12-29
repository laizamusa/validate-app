package com.cloudwalkdigital.activation.evaluationapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cloudwalkdigital.activation.evaluationapp.R;
import com.cloudwalkdigital.activation.evaluationapp.models.EmployeeModel;
import com.cloudwalkdigital.activation.evaluationapp.models.EventModel;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by henry on 10/05/2016.
 */
public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventsViewHolder> {

    List<EventModel> eventsModels;
    static Context vg;

    public EventsAdapter(List<EventModel> eventsModels) {
        this.eventsModels = eventsModels;
    }

    @Override
    public EventsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.row_events, parent, false);
        vg = parent.getContext();
        return new EventsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(EventsViewHolder holder, int position) {
        holder.tvName.setText(eventsModels.get(position).getName());
        holder.tvEmail.setText(eventsModels.get(position).getJonum());
        //holder.tvEmail.setText(eventsModels.get(position).getId()+"");
        holder.tvDepartment.setText(eventsModels.get(position).getEventdate());
    }

    @Override
    public int getItemCount() {
        return eventsModels.size();
    }

    public static class EventsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @Bind(R.id.tv_name) TextView tvName;
        @Bind(R.id.tv_job_title) TextView tvEmail;
        @Bind(R.id.tv_eventdate) TextView tvDepartment;

        public EventsViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ButterKnife.bind(this, itemView);
        }

        public void onClick(View view) {
            Toast.makeText(vg,"sampel"+ tvName.getText(),Toast.LENGTH_SHORT).show();
        }

    }
}
