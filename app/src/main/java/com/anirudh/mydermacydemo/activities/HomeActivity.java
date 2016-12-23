package com.anirudh.mydermacydemo.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.anirudh.mydermacydemo.adapters.HomePagerAdapter;
import com.anirudh.mydermacydemo.R;
import com.anirudh.mydermacydemo.fragments.FormFragment;
import com.anirudh.mydermacydemo.managers.PreferenceManager;
import com.anirudh.mydermacydemo.models.LoginPayload;
import com.anirudh.mydermacydemo.network.SendLoginDetailsDelegate;
import com.anirudh.mydermacydemo.utils.AppLogger;
import com.anirudh.mydermacydemo.utils.Constants;

import java.lang.reflect.Array;
import java.util.Arrays;

public class HomeActivity extends BaseActivity implements FormFragment.OnSubmitButtonClick,
    SendLoginDetailsDelegate.SendDetailsCallback{

    private final String TAG = HomeActivity.class.getSimpleName();
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private HomePagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppLogger.infoLog(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        PreferenceManager.getInstance(this).setValueForKey(Constants.DATA_FETCHED, "false");

        pagerAdapter  = new HomePagerAdapter(getSupportFragmentManager());
        viewPager     = (ViewPager) findViewById(R.id.demo_pager);
        tabLayout     = (TabLayout) findViewById(R.id.demo_tabs);

        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(pagerAdapter);

    }

    @Override
    public void onSubmitButtonClicked(LoginPayload payload) {
        showProgressDialog("Please Wait");
        SendLoginDetailsDelegate delegate = new SendLoginDetailsDelegate(getBaseContext(),this);
        delegate.SendDetails(payload);
    }

    @Override
    public void OnSendSuccess(String[] locations) {
        AppLogger.infoLog(TAG, "OnSendSuccess");
        hideProgressDialog();
        PreferenceManager.getInstance(this).setValueForKey(Constants.DATA_FETCHED, "true");
        PreferenceManager.getInstance(this).setValueForKey(Constants.LOCATION_LIST, Arrays.toString(locations));
        pagerAdapter.notifyDataSetChanged();
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(1);
    }
    @Override
    public void OnSendFailure(String message) {
        hideProgressDialog();
        showToast(message);
    }
}
