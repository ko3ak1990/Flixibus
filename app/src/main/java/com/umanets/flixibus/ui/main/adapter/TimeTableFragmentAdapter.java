package com.umanets.flixibus.ui.main.adapter;

import android.app.Fragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


import com.umanets.flixibus.ui.main.MainPresenter;
import com.umanets.flixibus.ui.main.TimeTableFragment;

/**
 * Created by Евгений on 17.09.2016.
 */
public class TimeTableFragmentAdapter  extends FragmentStatePagerAdapter {
    private static final int SIZE = 2;


    public TimeTableFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public TimeTableFragment getItem(int position) {
        switch (position) {
            case 0:
                return TimeTableFragment.newInstance(MainPresenter.ARRIVALS);
            case 1:
                return TimeTableFragment.newInstance(MainPresenter.DEPARTURES);
        }
        return null;

    }


    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Arrivals";
            case 1:
                return "Departures";
        }
        return "";
    }

    @Override
    public int getCount() {
        return SIZE;
    }



}
