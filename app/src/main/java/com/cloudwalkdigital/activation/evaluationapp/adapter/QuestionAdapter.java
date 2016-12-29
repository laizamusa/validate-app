package com.cloudwalkdigital.activation.evaluationapp.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cloudwalkdigital.activation.evaluationapp.R;
import com.cloudwalkdigital.activation.evaluationapp.activities.QuestionActivity;
import com.cloudwalkdigital.activation.evaluationapp.constant.Constant;
import com.cloudwalkdigital.activation.evaluationapp.fragments.AccountsFragment_q1;
import com.cloudwalkdigital.activation.evaluationapp.fragments.AccountsFragment_q2;
import com.cloudwalkdigital.activation.evaluationapp.fragments.Choices3Fragment;
import com.cloudwalkdigital.activation.evaluationapp.fragments.EventProperQsFragment;
import com.cloudwalkdigital.activation.evaluationapp.fragments.PostEventQsFragment;
import com.cloudwalkdigital.activation.evaluationapp.fragments.QuestionFragment;
import com.cloudwalkdigital.activation.evaluationapp.models.QuestionModel;
import com.cloudwalkdigital.activation.evaluationapp.utils.Globals;

import java.util.List;

/**
 * Created by henry on 02/05/2016.
 */
public class QuestionAdapter extends FragmentPagerAdapter {

    public static Context mContext;
    List<QuestionModel> mQuestion;

    public static final String PREFS_NAME = "MyPref";
    public static SharedPreferences sharedPreference;
    SharedPreferences.Editor editor;
    String LOG_IN = "User";
    Globals g = Globals.getInstance();

    public static String EVALUATOR = "AE";
    public static String DEPT_QUESTION = "Account Executive";
    public static String EVENT_NAME = "DOVE EVENT";
    public static String EVENT_CAT = "DOVE EVENT";

    public QuestionAdapter(FragmentManager fm) {
        super(fm);
    }

    public static void setContext(Context context){
        mContext = context;
        sharedPreference = mContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public Fragment getItem(int position) {
        return QuestionFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return g.getQuestion().size();
    }
}
