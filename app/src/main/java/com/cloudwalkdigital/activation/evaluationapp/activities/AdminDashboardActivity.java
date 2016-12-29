package com.cloudwalkdigital.activation.evaluationapp.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.cloudwalkdigital.activation.evaluationapp.R;
import com.cloudwalkdigital.activation.evaluationapp.adapter.EmployeeAdapter;
import com.cloudwalkdigital.activation.evaluationapp.fragments.EmployeesFragment;
import com.cloudwalkdigital.activation.evaluationapp.fragments.EventsFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AdminDashboardActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)     Toolbar toolbar;
    @Bind(R.id.tabs)        TabLayout tabLayout;
    @Bind(R.id.viewpager)   ViewPager viewPager;

    public static final String PREFS_NAME = "MyPref";
    SharedPreferences sharedPreference;
    SharedPreferences.Editor editor;
    String LOG_IN = "User";
    String USER = "User";
    int REQUEST_CODE =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        ButterKnife.bind(this);

        sharedPreference = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        LOG_IN = sharedPreference.getString("EVALUATOR", "");
        USER = sharedPreference.getString("USER", "");

        //Setup toolbar
        setSupportActionBar(toolbar);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new EmployeesFragment(), "Employees");
        adapter.addFragment(new EventsFragment(), "Events");
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            viewPager.setAdapter(null);
            setupViewPager(viewPager);
            viewPager.getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.admin_menu, menu);
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
            case R.id.action_add:
                //startActivity(new Intent(getApplicationContext(), CreateEmployeeActivity.class));
                startActivityForResult(new Intent(this, CreateEmployeeActivity.class), REQUEST_CODE);
                break;
            case R.id.action_add_events:
                startActivityForResult(new Intent(this, CreateEventsActivity.class), REQUEST_CODE);
                break;
            case R.id.action_result:
                startActivityForResult(new Intent(this, ResultActivity.class), REQUEST_CODE);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
