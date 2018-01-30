package com.sa.icarasia.ui.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.sa.icarasia.R;
import com.sa.icarasia.model.UserModel;
import com.sa.icarasia.realm.RealmController;
import com.sa.icarasia.ui.activities.base.BaseActivity;
import com.sa.icarasia.utility.Prefs;
import com.sa.icarasia.utility.SharedObjects;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class HomeActivity extends BaseActivity {
    private Realm realm;
    private UserModel model;
    private Context mContext;
    private String mobile;

    @BindView(R.id.editTextFname)
    EditText _fNameText;
    @BindView(R.id.editTextLname)
    EditText _lNameText;
    @BindView(R.id.editTextNumber)
    EditText _numberText;
    @BindView(R.id.editImageView)
    ImageView _editNumber;
    @BindView(R.id.buttonUserType)
    Button _btnUserType;
    @BindView(R.id.buttonLogout)
    Button _btnLogout;
    @BindView(R.id.maincontainer)
    LinearLayout _mainContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mContext = this;
        ButterKnife.bind(this);
        this.realm = RealmController.with(this).getRealm();
        model = (UserModel) getIntent().getSerializableExtra("userobj");

        if (model.getFname().isEmpty()) {
            _fNameText.setText("First Name Not Found");
        } else {
            _fNameText.setText(model.getFname());
        }
        if (model.getLname().isEmpty()) {
            _lNameText.setText("Last Name Not Found");
        } else {

            _lNameText.setText(model.getLname());
        }
        _numberText.setText(model.getNumber());

        _btnUserType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMessage("Your account type is " + model.getUsertype());
            }
        });

        _editNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showUpdateNumberDialog();
            }
        });

        _btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog progressDialog = new ProgressDialog(mContext,
                        R.style.Theme_AppCompat_DayNight_Dialog);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Logout...");
                progressDialog.show();
              //  showProgressDialog("Logout...");

                new android.os.Handler().postDelayed(
                        new Runnable() {
                            public void run() {

                                SharedObjects.getInstance().startNewActivity(HomeActivity.this, SplashActivity.class, true);

                                hideDialog();
                            }
                        }, 5000);
            }
        });
    }

    private void showUpdateNumberDialog() {

        LayoutInflater factory = LayoutInflater.from(mContext);
        final View updateDialogView = factory.inflate(R.layout.dialog_update_number, null);
        final AlertDialog updateDialog = new AlertDialog.Builder(mContext).create();
        updateDialog.setCancelable(false);
        updateDialog.setView(updateDialogView);
        final EditText editText = (EditText) updateDialogView.findViewById(R.id.et_number);
        editText.setText(model.getNumber());

        updateDialogView.findViewById(R.id.bt_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDialog.dismiss();
            }
        });

        mobile = editText.getText().toString();
        updateDialogView.findViewById(R.id.bt_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mobile = editText.getText().toString();
                if (!SharedObjects.getInstance().isValidPhoneNumber(mobile)) {
                    editText.setError("Enter Valid Mobile Number");

                } else {


                    UserModel obj = realm.where(UserModel.class).equalTo("email", model.getEmail()).findFirst();
                    realm.beginTransaction();
                    if (obj != null) {
                        obj.setNumber(mobile);
                        _numberText.setText(mobile);
                        realm.copyToRealmOrUpdate(obj);
                        realm.commitTransaction();
                        Snackbar.make(_mainContainer, "Number edit successfully", Snackbar.LENGTH_LONG).show();
                        updateDialog.dismiss();
                    }

                }
            }
        });
        updateDialog.show();
    }
}
