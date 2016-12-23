package com.anirudh.mydermacydemo.activities;

import android.app.ProgressDialog;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

/**
 * Created by Anirudh on 21/12/16.
 * BaseActivity to reuse code.
 */

public class BaseActivity extends ActionBarActivity {

    private final String TAG = BaseActivity.class.getSimpleName();
    ProgressDialog progressDialog;

    public void showProgressDialog(String message) {
        if (!isFinishing()) {
            if (progressDialog == null) {
                progressDialog = new ProgressDialog(this);
                progressDialog.setMessage(message);
                progressDialog.setCancelable(false);
                progressDialog.setIndeterminate(true);
                progressDialog.show();

            }
        }
    }

    public void hideProgressDialog() {
        if (progressDialog != null) {
            if (progressDialog.isShowing()) {
                if (!isFinishing() && !isDestroyed()) {
                    progressDialog.dismiss();
                    progressDialog = null;
                }
            }
        }
    }

    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
