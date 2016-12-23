package com.anirudh.mydermacydemo.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.anirudh.mydermacydemo.R;
import com.anirudh.mydermacydemo.adapters.RecyclerAdapter;
import com.anirudh.mydermacydemo.managers.PreferenceManager;
import com.anirudh.mydermacydemo.utils.AppLogger;
import com.anirudh.mydermacydemo.utils.Constants;

/**
 * Created by Anirudh Chakraborty on 22/12/16.
 * Fragment to show list
 */

public class DetailsFragment extends Fragment {

    private RecyclerView recyclerView;
    private Context mContext;
    private final String TAG = DetailsFragment.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        AppLogger.infoLog(TAG, "onCreateView");
        View rootView = inflater.inflate(R.layout.fragment_details, container, false);
        mContext = getActivity();

        String isDataFetched = PreferenceManager.getInstance(mContext).getStringForKey(Constants.DATA_FETCHED);
        if (isDataFetched.equalsIgnoreCase("true")) {
            AppLogger.infoLog(TAG, "onCreateView " + "is data fetched true");
            String data = PreferenceManager.getInstance(mContext).getStringForKey(Constants.LOCATION_LIST);

            if (data.length()>0) {
                String[] locations = data.split(",");

                recyclerView = (RecyclerView) rootView.findViewById(R.id.rv_details_list);
                LinearLayoutManager lManager = new LinearLayoutManager(mContext);
                RecyclerAdapter mAdapter = new RecyclerAdapter(mContext, locations);

                recyclerView.setLayoutManager(lManager);
                recyclerView.setAdapter(mAdapter);
            }
        }

        return rootView;
    }
}
