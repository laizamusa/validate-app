package com.cloudwalkdigital.activation.evaluationapp.activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.provider.Contacts;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.cloudwalkdigital.activation.evaluationapp.R;
import com.cloudwalkdigital.activation.evaluationapp.adapter.ProjectAdapter;
import com.cloudwalkdigital.activation.evaluationapp.constant.Constant;
import com.cloudwalkdigital.activation.evaluationapp.fragments.ProjectFragment;
import com.cloudwalkdigital.activation.evaluationapp.models.EventModel;
import com.cloudwalkdigital.activation.evaluationapp.utils.DatabaseAccess;
import com.cloudwalkdigital.activation.evaluationapp.utils.JSONResponse;
import com.cloudwalkdigital.activation.evaluationapp.utils.RequestInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import io.realm.Sort;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProjectsActivity extends AppCompatActivity {
    private Realm realm;
    private RealmConfiguration realmConfig;

    String name, jobTitle, eventDate, eid;
    private ArrayList<EventModel> listEventModels;
    private List<EventModel> feedItemList = new ArrayList<EventModel>();
    private ProjectAdapter projectAdapter;
    private EventTask mAuthTask = null;


    public static final String PREFS_NAME = "MyPref";
    SharedPreferences sharedPreference;
    SharedPreferences.Editor editor;

    @Bind(R.id.rv_list)
    RecyclerView rvList;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        rvList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvList.setLayoutManager(llm);

        sharedPreference = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        eid = sharedPreference.getString("EID", "");

        mAuthTask = new EventTask(eid, "");
        mAuthTask.execute((Void) null);

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

    public class EventTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEid;
        private final String mPassword;
        private String mEvaluate = "";
        private String mRoles = "";
        String link;
        String data;
        BufferedReader bufferedReader;
        String result;
        DatabaseAccess databaseAccess;
        EventModel eModel;

        EventTask(String email, String password) {
            mEid = email;
            mPassword = password;
        }

        @Override
        protected void onPreExecute() {
            // Things to be done before execution of long running operation. For
            // example showing ProgessDialog
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
            databaseAccess.open();

            feedItemList = databaseAccess.getEvents(mEid);

            databaseAccess.close();
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            String jsonStr = result;
            mAuthTask = null;
            //showProgress(false);
            projectAdapter = new ProjectAdapter(feedItemList);
            rvList.setAdapter(projectAdapter);
        }

        @Override
        protected void onCancelled(){
            mAuthTask = null;
            //showProgress(false);
        }
    }
}
