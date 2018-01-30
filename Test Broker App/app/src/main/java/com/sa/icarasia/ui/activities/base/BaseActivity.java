package com.sa.icarasia.ui.activities.base;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.sa.icarasia.R;

public class BaseActivity extends AppCompatActivity {

    public void showMessage(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    ProgressDialog progressDialog;
    public void showProgressDialog(String message){
        progressDialog = new ProgressDialog(getApplicationContext(),
                R.style.Theme_AppCompat_DayNight_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(message);
        progressDialog.show();
    }

    public void hideDialog(){
        if(progressDialog!=null){
            progressDialog.dismiss();
        }
    }
}
