package com.anirudh.mydermacydemo.models;

/**
 * Created by anirudh on 23/12/16.
 */

public class LoginResponse {

   private int status;
   private String message;
   private String[] locations;

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String[] getLocations() {
        return locations;
    }
}
