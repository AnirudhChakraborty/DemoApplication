package com.anirudh.mydermacydemo.managers;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.anirudh.mydermacydemo.utils.AppLogger;

import org.json.JSONObject;

/**
 * Created by anirudh on 23/12/16.
 */

public class APIManager {

    private String TAG = APIManager.class.getSimpleName();
    private static volatile APIManager instance = null;

    public static APIManager getInstance() {
        if (instance == null){
            synchronized (APIManager.class){
                if (instance == null){
                    instance = new APIManager();
                }
            }
        }
        return instance;
    }


    public void post( String url, JSONObject payload,
                      Response.Listener<JSONObject> responseListener,
                      Response.ErrorListener errorListener){

        AppLogger.infoLog(TAG,"Making POST Request");
        AppLogger.infoLog(TAG,"URL - " + url);
        AppLogger.infoLog(TAG,"payload - " + payload);


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                url , payload,responseListener, errorListener);

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                20 * 1000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // Adding request to request queue
        MyApplication.getInstance().addToRequestQueue(jsonObjectRequest);

    }
}
