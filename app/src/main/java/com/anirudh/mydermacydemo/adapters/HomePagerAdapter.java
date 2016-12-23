package com.anirudh.mydermacydemo.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.anirudh.mydermacydemo.fragments.DetailsFragment;
import com.anirudh.mydermacydemo.fragments.FormFragment;
import com.anirudh.mydermacydemo.utils.AppLogger;

/**
 * Created by Anirudh Chakraborty on 22/12/16.
 * Pager Adapter to manage tabs in home activity.
 */

public class HomePagerAdapter extends FragmentPagerAdapter {

    //String array for tab titles
    private String tabTitles[] = {"Login", "Details"};
    private final String TAG = HomePagerAdapter.class.getSimpleName();

    public HomePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        AppLogger.infoLog(TAG, "getItem");
        Fragment fragment = null;
        switch (position) {

            case 0:
                AppLogger.infoLog(TAG, "getItem : case 0");
                fragment = new FormFragment();
                break;

            case 1:
                AppLogger.infoLog(TAG, "getItem : case 1");
                fragment = new DetailsFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        AppLogger.infoLog(TAG, "getPageTitle");
        return tabTitles[position];
    }
}
