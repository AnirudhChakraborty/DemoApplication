package com.anirudh.mydermacydemo.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.anirudh.mydermacydemo.R;
import com.anirudh.mydermacydemo.activities.BaseActivity;
import com.anirudh.mydermacydemo.models.LoginPayload;
import com.anirudh.mydermacydemo.utils.AppLogger;

/**
 * Created by Anirudh Chakraborty on 22/12/16.
 * Form Fragment.
 */

public class FormFragment extends Fragment implements View.OnClickListener{

    private final String TAG = FormFragment.class.getSimpleName();
    private EditText etUserName, etPassword;
    private Button btSubmit;
    private Context mContext;
    private OnSubmitButtonClick mlistener;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        AppLogger.infoLog(TAG, "onCreateView");

        View rootView = inflater.inflate(R.layout.fragment_form, container, false);
        etUserName    = (EditText) rootView.findViewById(R.id.et_user_name);
        etPassword    = (EditText) rootView.findViewById(R.id.et_password);
        btSubmit      = (Button)   rootView.findViewById(R.id.bt_submit);

        mContext = getActivity();

        btSubmit.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View view) {

        AppLogger.infoLog(TAG, "onClick");
        switch (view.getId()) {
            case R.id.bt_submit:
                AppLogger.infoLog(TAG, "Clicked - " + btSubmit.getText());
                String password = etPassword.getText().toString();
                String userName = etUserName.getText().toString();

                if (password.length()>0 && userName.length()>0) {
                    LoginPayload payload = new LoginPayload();
                    payload.setPassword(password);
                    payload.setUserName(userName);

                    mlistener.onSubmitButtonClicked(payload);
                }
                else {
                    ((BaseActivity)mContext).showToast("Please fill details");
                }


                break;
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mlistener = (OnSubmitButtonClick) activity;
    }

    public interface OnSubmitButtonClick {
        void onSubmitButtonClicked(LoginPayload payload);
    }
}
