package io.moku.davide.sideproject;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.decoder.SimpleProgressiveJpegConfig;

import io.moku.davide.sideproject.model.User;
import io.moku.davide.sideproject.utils.preferences.PreferencesManager;
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

        setupFresco();

        RealmUtils.initialize(this);
        RealmUtils.loadDB(getApplicationContext());
        RealmUtils.onCreateApplication();

        PreferencesManager.storeLoggedUserId(this, User.getAllUsers().get(0).getId());
    }

    private void setupFresco() {
        /*Fresco.initialize(this);*/
        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
                .setProgressiveJpegConfig(new SimpleProgressiveJpegConfig())
                .setResizeAndRotateEnabledForNetwork(true)
                .setDownsampleEnabled(true)
                .build();
        Fresco.initialize(this, config);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

        RealmUtils.onTerminateApplication();
    }
}