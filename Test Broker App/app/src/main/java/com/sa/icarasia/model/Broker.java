package com.sa.icarasia.model;

import android.content.Context;
import android.widget.Toast;

import com.sa.icarasia.R;

public class Broker implements IUser {
    @Override
    public void showAccountType(Context context) {
        Toast.makeText(context,context.getString(R.string.account_type) +" "+ getClass().getSimpleName() ,Toast.LENGTH_SHORT).show();
    }
}
