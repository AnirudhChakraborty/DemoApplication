package com.anirudh.mydermacydemo.utils;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Anirudh Chakraborty on 23/12/16.
 * Json data manager
 */

public class DataManager {

    private String TAG = DataManager.class.getSimpleName();
    private static volatile DataManager dataManager;
    private Gson gson;

    private DataManager() {
        gson = new GsonBuilder().disableHtmlEscaping()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setPrettyPrinting()
                .serializeNulls()
                .create();
    }

    public static DataManager getInstance() {
        if (dataManager == null) {
            synchronized (DataManager.class) {
                if (dataManager == null) {
                    dataManager = new DataManager();
                }
            }
        }
        return dataManager;
    }

    public Gson getGsonBuilder() {
        return gson;
    }

    public JSONObject getJSONObject(String payload){
        JSONObject payloadObject = null;
        try {
            payloadObject = new JSONObject(payload);

        } catch (JSONException e){
            AppLogger.debugLog(TAG, "JSON Exception in do Login " + e.getMessage());
        }
        return payloadObject;
    }
}
