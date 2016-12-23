package com.anirudh.mydermacydemo.utils;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Anirudh Chakraborty on 23/12/16.
 * Error helper for server calls
 */

public class VolleyErrorHelper {

    private static String TAG = VolleyErrorHelper.class.getSimpleName();
    private static String TIMEOUT_ERROR = "Time out error";
    private static String NO_INTERNET = "No internet";
    private static String GENERIC_ERROR = "generic error";
    /**
     * Returns appropriate message which is to be displayed to the user
     * against the specified error object.
     *
     * @param error
     * @param context
     * @return
     */
    public static String getMessage(Object error, Context context) {
        AppLogger.infoLog(TAG,"getMessage - VolleyErrorHelper");
        if (error instanceof TimeoutError) {
            AppLogger.infoLog(TAG, "Timeout Error");
            return TIMEOUT_ERROR;
        }
        else if (isServerProblem(error)) {
            AppLogger.infoLog(TAG, "Server Problem");
            return handleServerError(error, context);
        }
        else if (isNetworkProblem(error)) {
            AppLogger.infoLog(TAG, "Network Problem");
            return NO_INTERNET;
        }
        AppLogger.infoLog(TAG, "Generic Error");
        return GENERIC_ERROR;
    }

    /**
     * Determines whether the error is related to network
     * @param error
     * @return
     */
    private static boolean isNetworkProblem(Object error) {
        return (error instanceof NetworkError) || (error instanceof NoConnectionError);
    }
    /**
     * Determines whether the error is related to server
     * @param error
     * @return
     */
    private static boolean isServerProblem(Object error) {
        return (error instanceof ServerError) || (error instanceof AuthFailureError);
    }

    /**
     * Handles the server error, tries to determine whether to show a stock message or to
     * show a message retrieved from the server.
     *
     * @param err
     * @param context
     * @return
     */
    private static String handleServerError(Object err, Context context) {
        VolleyError error = (VolleyError) err;

        NetworkResponse response = error.networkResponse;
        AppLogger.infoLog(TAG, "Status Code: " + response.statusCode);

        if (response != null) {
            switch (response.statusCode) {
                case 404:
                    return GENERIC_ERROR;
                case 422:
                case 400:
                    try {
                        // server might return error like this { "error": "Some error occured" }
                        // Use "Gson" to parse the result
                        AppLogger.infoLog(TAG, "Response Data: " + response.data);
                        HashMap<String, String> result = DataManager
                                .getInstance()
                                .getGsonBuilder()
                                .fromJson(new String(response.data),
                                        new TypeToken<Map<String, String>>() {
                                        }.getType());

                        if (result != null && result.containsKey("error")) {
                            return result.get("error");
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    // invalid request
                    return error.getMessage();
                 default:
                    return GENERIC_ERROR;
            }
        }
        return GENERIC_ERROR;
    }
}
