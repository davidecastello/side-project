package io.moku.davide.sideproject.utils.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import io.moku.davide.sideproject.model.User;

/**
 * Created by Davide Castello on 08/02/18.
 * Project: side-project
 * Copyright © 2018 Moku S.r.l. All rights reserved.
 */

public class PreferencesManager {

    private static final String SHARED_PREFERENCES_NAME = "shared_preferences";
    private static final String LOGGED_USER_ID = "logged_user_id";

    private static SharedPreferences getPrefs(Context context) {
        return context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public static int getLoggedUserId(Context context) {
        return getPrefs(context).getInt(LOGGED_USER_ID, -1);
    }

    public static void storeLoggedUserId(Context context, int userId) {
        getPrefs(context).edit()
                .putInt(LOGGED_USER_ID, userId)
                .apply();
    }

    public static void clear(Context context) {
        getPrefs(context).edit().clear().apply();
    }
}
