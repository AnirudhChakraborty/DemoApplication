package com.anirudh.mydermacydemo.managers;

import android.content.Context;
import android.content.SharedPreferences;

import com.anirudh.mydermacydemo.utils.AppLogger;

/**
 * Created by Anirudh Chakraborty on 23/12/16.
 *
 */

public class PreferenceManager {
    private static final String PREF_NAME = "com.anirudh.mydermacydemo.PREFERENCES_FILE_KEY";

    private static volatile PreferenceManager preferencesManager = null;
    private final SharedPreferences sharedPreferences;
    private String TAG = PreferenceManager.class.getSimpleName();

    private PreferenceManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static PreferenceManager getInstance(Context context) {
        if (preferencesManager == null) {
            synchronized (PreferenceManager.class) {
                if (preferencesManager == null) {
                    preferencesManager = new PreferenceManager(context);
                }
            }
        }
        return preferencesManager;
    }

    public void setValueForKey(String key, String value) {
        AppLogger.infoLog(TAG, "set value for key");
        try {
            sharedPreferences.edit().putString(key,value).commit();
        }
        catch(Exception e) {
            AppLogger.infoLog(TAG, "Cannot write in SharedPreferences: " + e.toString());
        }
    }


    public String getStringForKey(String key) {
        AppLogger.infoLog(TAG, "get value for key");
        String returnValue = null;
        try {
            returnValue = sharedPreferences.getString(key, null);
        }
        catch (Exception e) {
            AppLogger.infoLog(TAG, "Cannot read from SharedPreferences: " + e.toString());
        }
        return returnValue;
    }
}
