package io.moku.davide.sideproject.network.asynctasks;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.Toast;

import io.moku.davide.sideproject.R;
import io.moku.davide.sideproject.utils.alerts.ProgressDialogHelper;

/**
 * Created by Davide Castello on 02/02/18.
 * Project: side-project
 * Copyright © 2018 Moku S.r.l. All rights reserved.
 */

public class AsyncAddFriend extends AsyncTask<Void, Void, Boolean> {

    private Boolean remoteError = false;
    private Activity activity;
    private ProgressDialogHelper helper;

    public AsyncAddFriend(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        helper = new ProgressDialogHelper.Builder(activity)
                .setInitalMsg(activity.getString(R.string.operation_running))
                .setDoneMsg(activity.getString(R.string.added_to_friends))
                .setAnimationName("check_done.json")
                .setSpeed(0.5f)
                //.setFinalDelaySeconds(1.0)
                .show();
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        // fake implementation
        try {
            Thread.sleep(2000);
            return true;
        } catch (InterruptedException e) {
            e.printStackTrace();
            remoteError = true;
        }
        return false;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        if (activity != null) {
            if (remoteError) {
                helper.dismissWithToast(activity.getString(R.string.something_went_wrong), Toast.LENGTH_LONG);
            } else {
                helper.done(new ProgressDialogHelper.OnDialogDismissedListener() {
                    @Override
                    public void onDialogDismissed(Activity activity) {
                        //activity.finish();
                    }
                });

                // or you can use
                //helper.dismiss();
            }
        }
    }
}
