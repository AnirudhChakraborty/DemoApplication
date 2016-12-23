package com.anirudh.mydermacydemo.utils;

import android.util.Log;

/**
 * Created by Anirudh on 22/12/16.
 * Logger class to maintain logs. Here we can manage when to show log and not show logs.
 */

public class AppLogger {

    private static String TAG = "DEMO-";

    public static void infoLog(String tag, String logText) {
        Log.i(TAG.concat(tag), logText);
    }

    public static void debugLog(String tag, String logText) {
        Log.d(TAG.concat(tag), logText);
    }
}

