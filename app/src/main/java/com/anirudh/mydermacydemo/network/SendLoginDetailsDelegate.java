package com.anirudh.mydermacydemo.network;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.anirudh.mydermacydemo.managers.APIManager;
import com.anirudh.mydermacydemo.models.LoginPayload;
import com.anirudh.mydermacydemo.models.LoginResponse;
import com.anirudh.mydermacydemo.utils.AppLogger;
import com.anirudh.mydermacydemo.utils.DataManager;
import com.anirudh.mydermacydemo.utils.VolleyErrorHelper;

import org.json.JSONObject;

/**
 * Created by Anirudh Chakraborty on 23/12/16.
 * Class to manage network call for login page
 */

public class SendLoginDetailsDelegate {

    private final String TAG = SendLoginDetailsDelegate.class.getSimpleName();
    private String URL = "http://snackums.comli.com/demo.php";
    private SendDetailsCallback mCallback;
    private Context mContext;

    public SendLoginDetailsDelegate(Context context, Object handler) {
        mCallback = (SendDetailsCallback) handler;
        mContext  = context;
    }

    public interface SendDetailsCallback {
        void OnSendSuccess(String[] locations);
        void OnSendFailure(String message);
    }

    public void SendDetails(LoginPayload payload) {
        AppLogger.infoLog(TAG, "SendDetails");
        String jsonData = DataManager.getInstance().getGsonBuilder().toJson(payload);
        JSONObject data = DataManager.getInstance().getJSONObject(jsonData);

        APIManager.getInstance().post(URL, data,
                new ResponseListener(), new ErrorListener());
    }


    private class ResponseListener implements Response.Listener<JSONObject>{

        @Override
        public void onResponse(JSONObject response){
            AppLogger.infoLog(TAG, "onResponse: " + response.toString());

            LoginResponse loginResponse = DataManager
                    .getInstance()
                    .getGsonBuilder()
                    .fromJson(response.toString(), LoginResponse.class);

            String[] locations = loginResponse.getLocations();
            mCallback.OnSendSuccess(locations);
        }
    }

    private class ErrorListener implements Response.ErrorListener{

        @Override
        public void onErrorResponse(VolleyError error){
            String errorMessage = VolleyErrorHelper.getMessage(error, mContext);
            AppLogger.infoLog(TAG, "ErrorListener: " + errorMessage);
            mCallback.OnSendFailure("Failure");
        }
    }
}
