package com.sa.icarasia;

import android.app.Application;


import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainApplication extends Application {

    private static MainApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this)
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);

    }

    public static MainApplication getInstance() {
        return instance;
    }
}
