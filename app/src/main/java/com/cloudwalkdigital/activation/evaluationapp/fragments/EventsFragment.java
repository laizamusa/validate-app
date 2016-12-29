package com.cloudwalkdigital.activation.evaluationapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cloudwalkdigital.activation.evaluationapp.R;
import com.cloudwalkdigital.activation.evaluationapp.adapter.EmployeeAdapter;
import com.cloudwalkdigital.activation.evaluationapp.adapter.EventsAdapter;
import com.cloudwalkdigital.activation.evaluationapp.models.EmployeeModel;
import com.cloudwalkdigital.activation.evaluationapp.models.EventModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by jpjpjp28 on 14/04/2016.
 */
public class EventsFragment extends Fragment {

    private Realm realm;
    private RealmConfiguration realmConfig;

    String name, jobTitle, eventDate;
    private List<EventModel> listEventModels;
    private EventsAdapter eventAdapter;

    @Bind(R.id.rv_list) RecyclerView rvList;

    public EventsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard_create_event, container, false);
        ButterKnife.bind(this, view);

        realmConfig = new RealmConfiguration.Builder(getContext()).build();
        realm = Realm.getInstance(realmConfig);

        rvList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvList.setLayoutManager(llm);

        listEventModels = new ArrayList<EventModel>();
        eventAdapter = new EventsAdapter(listEventModels);

        rvList.setAdapter(eventAdapter);

        return view;
    }
}
