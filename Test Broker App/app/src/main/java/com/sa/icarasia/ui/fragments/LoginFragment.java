package com.sa.icarasia.ui.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.sa.icarasia.R;
import com.sa.icarasia.model.UserModel;
import com.sa.icarasia.realm.RealmController;
import com.sa.icarasia.ui.activities.HomeActivity;
import com.sa.icarasia.ui.fragments.basefragment.BaseFragment;
import com.sa.icarasia.utility.Prefs;
import com.sa.icarasia.utility.SharedObjects;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class LoginFragment extends BaseFragment {

    private static final String TAG = "LoginFragment";
    private Realm realm;

    @BindView(R.id.input_email_login)
    EditText _emailText;
    @BindView(R.id.input_password_login)
    EditText _passwordText;
    @BindView(R.id.btn_login)
    Button _loginButton;
    @BindView(R.id.maincontainer)
    LinearLayout _mainContainer;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        this.realm = RealmController.with(getActivity()).getRealm();
        RealmController.with(this).refresh();

        //Test Purpose
//        _emailText.setText("salman@gmail.com");
//        _passwordText.setText("salman@12");
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            //onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        showDialog("Authenticating...");

        final String email = _emailText.getText().toString();
        final String password = _passwordText.getText().toString();


        // TODO: Implement your own authentication logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        UserModel userExist = realm.where(UserModel.class).equalTo("email", email).equalTo("password", password).findFirst();

                        if (userExist != null) {
                            onLoginSuccess();
                            UserModel model = new UserModel(userExist.getId(), userExist.getEmail(), userExist.getPassword(), userExist.getUsertype(), userExist.getNumber(), userExist.getFname(), userExist.getLname());
                            Intent intent = new Intent(getActivity(), HomeActivity.class);
                            intent.putExtra("userobj", model);
                            getActivity().startActivity(intent);
                            getActivity().finish();

                        } else {
                            onLoginFailed();
                        }

                        hideDialog();
                    }
                }, 3000);
    }


    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
        SharedObjects.getInstance().startNewActivity(getActivity(), HomeActivity.class, true);
    }

    public void onLoginFailed() {
//        Toast.makeText(getActivity(), "Login failed", Toast.LENGTH_LONG).show();
        Snackbar.make(_mainContainer, "Login failed, Please Signup", Snackbar.LENGTH_LONG).show();
        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || password.isEmpty()) {
            valid = false;
            Snackbar.make(_mainContainer, "Please complete the form", Snackbar.LENGTH_LONG).show();
        } else {
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                _emailText.setError("enter a valid email address");
                valid = false;
            } else {
                _emailText.setError(null);
            }

            if (!SharedObjects.getInstance().isValidPassword(password)) {
                _passwordText.setError("Password should contain one special character and\n" +
                        "minimum 8 characters required");
                valid = false;
            } else {
                _passwordText.setError(null);
            }
        }
        return valid;
    }
}
