package com.cloudwalkdigital.activation.evaluationapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.cloudwalkdigital.activation.evaluationapp.fragments.AccountsFragment_q1;
import com.cloudwalkdigital.activation.evaluationapp.fragments.AccountsFragment_q2;

/**
 * Created by henry on 25/04/2016.
 */
public class AccountsAdapter extends FragmentPagerAdapter {

    public AccountsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0: return AccountsFragment_q1.newInstance("param1");
            case 1: return AccountsFragment_q2.newInstance("param1");
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
