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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.sa.icarasia.R;
import com.sa.icarasia.model.UserModel;
import com.sa.icarasia.realm.RealmController;
import com.sa.icarasia.ui.activities.HomeActivity;
import com.sa.icarasia.ui.fragments.basefragment.BaseFragment;
import com.sa.icarasia.utility.SharedObjects;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class SignupFragment extends BaseFragment {

    private static final String TAG = "SignupActivity";
    private String userType = "";
    private OnAddUserClickListener listener;

    private Realm realm;

    @BindView(R.id.input_fname)
    EditText _firstNameText;
    @BindView(R.id.input_lname)
    EditText _lastNameText;
    @BindView(R.id.input_email)
    EditText _emailText;
    @BindView(R.id.input_mobile)
    EditText _mobileText;
    @BindView(R.id.input_password)
    EditText _passwordText;
    @BindView(R.id.btn_signup)
    Button _signupButton;
    @BindView(R.id.spinnerUserType)
    Spinner _userTypeSpinner;
    @BindView(R.id.maincontainer)
    LinearLayout _mainContainer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_signup, container, false);
        ButterKnife.bind(this, view);
        this.realm = RealmController.with(getActivity()).getRealm();
        RealmController.with(this).refresh();
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        _userTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                TextView tv = ((TextView) parent.getChildAt(0));
                // tv.setTextColor(color);
                tv.setTextSize(14);

                if (position == 0) {
                    userType = "";
                } else {
                    userType = getResources().getStringArray(R.array.usertype)[position];
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            // onSignupFailed();
            return;
        } else {

            _signupButton.setEnabled(false);

            showDialog("Creating Account...");

            final String email = _emailText.getText().toString();
            final String password = _passwordText.getText().toString();
            final String firstName = _firstNameText.getText().toString();
            final String lastName = _lastNameText.getText().toString();
            final String mobile = _mobileText.getText().toString();


            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {


                            UserModel userExist = realm.where(UserModel.class).equalTo("email", email).findFirst();

                            if (userExist != null) {
                                // Exists
                                showMessage("You email already exited, use another one" + userExist.getEmail());
                                _signupButton.setEnabled(true);
                            } else {
                                // Not exist

                                UserModel user = new UserModel();

                                user.setId(getNextKey());
                                user.setEmail(email);
                                user.setPassword(password);
                                user.setFname(firstName);
                                user.setLname(lastName);
                                user.setUsertype(userType);
                                user.setNumber(mobile);
                                realm.beginTransaction();
                                realm.copyToRealm(user);
                                realm.commitTransaction();

                                UserModel model = new UserModel(getNextKey(), email, password, userType, mobile, firstName, lastName);
                                Intent intent = new Intent(getActivity(), HomeActivity.class);
                                intent.putExtra("userobj", model);
                                getActivity().startActivity(intent);
                                getActivity().finish();
                                // onSignupSuccess();
                            }
                            //  listener.onAddUserClickListener(user);
                            hideDialog();
                        }
                    }, 1000);


        }
    }


    public void onSignupSuccess() {
        _signupButton.setEnabled(true);

        SharedObjects.getInstance().startNewActivity(getActivity(), HomeActivity.class, true);

    }


    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();
        String mobile = _mobileText.getText().toString();


        if (email.isEmpty() || password.isEmpty() || userType.isEmpty() || mobile.isEmpty()) {

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
                _passwordText.setError("Password should contain one special character and minimum 8 characters required");
                valid = false;
            } else {
                _passwordText.setError(null);
            }


            if (!SharedObjects.getInstance().isValidPhoneNumber(mobile)) {
                _mobileText.setError("Enter Valid Mobile Number");
                valid = false;
            } else {
                _mobileText.setError(null);
            }


            if (userType.isEmpty()) {
                valid = false;
                Toast.makeText(getActivity(), "Please select user type", Toast.LENGTH_LONG).show();
            }
        }
        return valid;
    }


    public void setListener(OnAddUserClickListener listener) {
        this.listener = listener;
    }

    public interface OnAddUserClickListener {
        void onAddUserClickListener(UserModel user);
    }


    public int getNextKey() {
        try {
            Number number = realm.where(UserModel.class).max("id");
            if (number != null) {
                return number.intValue() + 1;
            } else {
                return 0;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return 0;
        }
    }
}
