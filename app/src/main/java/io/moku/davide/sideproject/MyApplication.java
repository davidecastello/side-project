package io.moku.davide.sideproject;

import android.app.Application;

import io.moku.davide.sideproject.utils.realm.RealmUtils;

/**
 * Created by Davide Castello on 30/01/18.
 * Project: TestingLibraries
 * Copyright © 2018 Moku S.r.l. All rights reserved.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        RealmUtils.initialize(this);
        RealmUtils.loadDB(getApplicationContext());
    }
}