package com.sa.icarasia.utility;

import android.app.Activity;
import android.content.Intent;
import android.telephony.PhoneNumberUtils;

public class SharedObjects {

    public static SharedObjects getInstance = null;

    public static SharedObjects getInstance() {
        if (getInstance == null) {
            getInstance = new SharedObjects();
        }
        return getInstance;

    }

    public void startNewActivity(Activity activity, Class classToStart,
                                 boolean doFinish) {
        Intent intent = new Intent(activity, classToStart);
        activity.startActivity(intent);
        if (doFinish) {
            activity.finish();
        }
    }

    public boolean isEmailValid(String emailStr) {
        if (emailStr == null)
            return false;
        return android.util.Patterns.EMAIL_ADDRESS.matcher(emailStr).matches();

    }

    public boolean isValidPhoneNumber(String target) {
        if (target == null || target.length() < 10 || target.length() > 13) {
            return false;
        } else if (PhoneNumberUtils.isGlobalPhoneNumber(target)) {
            return true;
        } else {
            return android.util.Patterns.PHONE.matcher(target).matches();
        }

    }

    public boolean isValidPassword(String password) {
        if (password.matches("^(?=.*?[#?!@$%^&*-]).{8,}$")) {
            return true;
        } else {
            return false;
        }
    }
}
