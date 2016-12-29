package com.cloudwalkdigital.activation.evaluationapp.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.SQLException;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cloudwalkdigital.activation.evaluationapp.adapter.AccountsAdapter;
import com.cloudwalkdigital.activation.evaluationapp.adapter.QuestionAdapter;
import com.cloudwalkdigital.activation.evaluationapp.constant.Constant;
import com.cloudwalkdigital.activation.evaluationapp.models.DataBaseHelper;
import com.cloudwalkdigital.activation.evaluationapp.R;
import com.cloudwalkdigital.activation.evaluationapp.utils.Globals;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QuestionActivity extends AppCompatActivity {

    @Bind(R.id.header_title)
    TextView hTitle;
    @Bind(R.id.department_label)
    TextView dLabel;
    @Bind(R.id.section_label)
    TextView sLabel;
    @Bind(R.id.question_label)
    TextView qLabel;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.ll_heading)
    LinearLayout ll_heading;
    @Bind(R.id.vp_container)
    ViewPager vp_container;
    @Bind(R.id.progressBar)
    ProgressBar pBar;
    int currentPage;


    public static final String PREFS_NAME = "MyPref";
    SharedPreferences sharedPreference;
    SharedPreferences.Editor editor;
    String LOG_IN = "User";
    Globals g = Globals.getInstance();

    public String EVALUATOR = "AE";
    public String DEPT_QUESTION = "Account Executive";
    public String EVENT_NAME = "DOVE EVENT";
    public String EVENTCATEGORY = "Pre Event";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        pBar.setProgress(0);
        sharedPreference = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        LOG_IN = sharedPreference.getString("EVALUATOR", "");
        EVENTCATEGORY = sharedPreference.getString("EVENTCATEGORY", "");
        EVENT_NAME = sharedPreference.getString("EVENTNAME", "");
        sLabel.setText(EVENTCATEGORY);
        hTitle.setText(EVENT_NAME);
        QuestionAdapter.setContext(getApplicationContext());
        vp_container.setAdapter(new QuestionAdapter(getSupportFragmentManager()));
        qLabel.setText("Question "+(vp_container.getCurrentItem() + 1)+" of "+vp_container.getAdapter().getCount());
        changeUi(vp_container.getCurrentItem());
        vp_container.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            public void onPageSelected(int position) {
                qLabel.setText("Question "+(vp_container.getCurrentItem() + 1)+" of "+vp_container.getAdapter().getCount());
                currentPage = position;
                int curPosition = vp_container.getCurrentItem() + 1;
                int totalItems = vp_container.getAdapter().getCount();
                float progress = (float) curPosition / totalItems;
                float pTotal = progress * 100;
                pBar.setProgress((int)((long) pTotal));
                toolbar.setTitleTextColor(Color.WHITE);
                toolbar.setSubtitleTextColor(Color.WHITE);
                hTitle.setTextColor(Color.WHITE);
                dLabel.setTextColor(Color.WHITE);
                changeUi(vp_container.getCurrentItem());

            }
        });


        Typeface mostserratBold = Typeface.createFromAsset(getAssets(), Constant.mostserratBold);
        Typeface mostserratRegular = Typeface.createFromAsset(getAssets(), Constant.mostserratRegular);
        Typeface mostserratLight = Typeface.createFromAsset(getAssets(), Constant.mostserratLight);
        hTitle.setTypeface(mostserratRegular);
        sLabel.setTypeface(mostserratRegular);
        qLabel.setTypeface(mostserratRegular);


    }


    public void changeUi(int curPosition){
        boolean inBounds = (curPosition >= 0) && (curPosition < g.getQuestion().size());
        if(inBounds){
            switch(Integer.parseInt(g.getQuestion().get(curPosition).getQdept())){
                case 1:
                    dLabel.setText("Account Executive");
                    toolbar.setBackgroundResource(R.color.accountsColor);
                    ll_heading.setBackgroundResource(R.color.accountsColor);
                    break;
                case 2:
                    dLabel.setText("Operations");
                    toolbar.setBackgroundResource(R.color.cmtuvaColor);
                    ll_heading.setBackgroundResource(R.color.cmtuvaColor);
                    break;
                case 3:
                    dLabel.setText("Negotiator's Assesment");
                    toolbar.setBackgroundResource(R.color.stpColor);
                    ll_heading.setBackgroundResource(R.color.stpColor);
                    break;
                case 4:
                    dLabel.setText("Project Manager's");
                    toolbar.setBackgroundResource(R.color.stpColor);
                    ll_heading.setBackgroundResource(R.color.stpColor);
                    break;
                case 5:
                    dLabel.setText("Team Leader's Rating");
                    toolbar.setBackgroundResource(R.color.stpColor);
                    ll_heading.setBackgroundResource(R.color.stpColor);
                    break;
                case 6:
                    dLabel.setText("Setup");
                    toolbar.setBackgroundResource(R.color.stpColor);
                    ll_heading.setBackgroundResource(R.color.stpColor);
                    break;
                case 7:
                    dLabel.setText("Setup Leader Assesment");
                    toolbar.setBackgroundResource(R.color.stpColor);
                    ll_heading.setBackgroundResource(R.color.stpColor);
                    break;
                case 8:
                    dLabel.setText("Production");
                    toolbar.setBackgroundResource(R.color.prColor);
                    ll_heading.setBackgroundResource(R.color.prColor);
                    break;
                case 9:
                    dLabel.setText("Inventory");
                    toolbar.setBackgroundResource(R.color.invColor);
                    ll_heading.setBackgroundResource(R.color.invColor);
                    break;
                case 10:
                    dLabel.setText("Human Resource Department");
                    toolbar.setBackgroundResource(R.color.hrColor);
                    ll_heading.setBackgroundResource(R.color.hrColor);
                    break;
            }
        }
    }

    @OnClick(R.id.section_label)
    public void backCat(){
        startActivity(new Intent(getApplicationContext(), Category.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
        finish();
    }

    @OnClick(R.id.nxtBtn)
    public void nextPage(){
        int totalPage = vp_container.getAdapter().getCount();
        int page = getItem() + 1;
        if(page <= totalPage){
            vp_container.setCurrentItem(page, true);
            if(page == vp_container.getAdapter().getCount()){
                startActivity(new Intent(getApplicationContext(), FinishActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();
            }
        }
    }

    @OnClick(R.id.prevBtn)
    public void prevPage(){
        int page = getItem() - 1;
        if(page >= 0){
            vp_container.setCurrentItem(page, true);
        }
    }

    private int getItem() {
        return vp_container.getCurrentItem();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout){
            startActivity(new Intent(getApplicationContext(), LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }

        if (id == R.id.action_home){
            startActivity(new Intent(getApplicationContext(), Category.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

}
