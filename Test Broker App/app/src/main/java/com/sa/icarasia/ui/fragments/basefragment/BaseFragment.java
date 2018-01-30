package com.sa.icarasia.ui.fragments.basefragment;

import android.app.ProgressDialog;
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
import android.widget.Toast;

import com.sa.icarasia.R;
import com.sa.icarasia.ui.activities.HomeActivity;
import com.sa.icarasia.utility.SharedObjects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BaseFragment extends Fragment {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public void showMessage(String text){
        Toast.makeText(getActivity(), text, Toast.LENGTH_LONG).show();
    }

    ProgressDialog progressDialog;
    public void showDialog(String message){
        progressDialog = new ProgressDialog(getActivity(),
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
