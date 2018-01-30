package com.sa.icarasia.realm;


import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;

import com.sa.icarasia.model.UserModel;

import io.realm.Realm;
import io.realm.RealmResults;


public class RealmController {

    private static RealmController instance;
    private final Realm realm;

    public RealmController(Application application) {
        realm = Realm.getDefaultInstance();
    }

    public static RealmController with(Fragment fragment) {

        if (instance == null) {
            instance = new RealmController(fragment.getActivity().getApplication());
        }
        return instance;
    }

    public static RealmController with(Activity activity) {

        if (instance == null) {
            instance = new RealmController(activity.getApplication());
        }
        return instance;
    }

    public static RealmController with(Application application) {

        if (instance == null) {
            instance = new RealmController(application);
        }
        return instance;
    }

    public static RealmController getInstance() {

        return instance;
    }

    public Realm getRealm() {

        return realm;
    }

    //Refresh the realm istance
    public void refresh() {

        realm.refresh();
    }

    public void clearAll() {

        realm.beginTransaction();
        realm.clear(UserModel.class);
        realm.commitTransaction();
    }


    public RealmResults<UserModel> getUsers() {

        return realm.where(UserModel.class).findAll();
    }


    public UserModel getUser(String email) {

        return realm.where(UserModel.class).equalTo("email", email).findFirst();
    }

    //check if UserModel.class is empty
    public boolean hasUsers() {

        return !realm.allObjects(UserModel.class).isEmpty();
    }

    //query example
    public RealmResults<UserModel> queryedUser(String email, String password) {

        return realm.where(UserModel.class)
                .contains("email", email)
                .or()
                .contains("password", password)
                .findAll();

    }
}
