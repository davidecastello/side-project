package io.moku.davide.sideproject.utils.preferences;

import io.moku.davide.sideproject.model.User;

/**
 * Created by Davide Castello on 08/02/18.
 * Project: side-project
 * Copyright © 2018 Moku S.r.l. All rights reserved.
 */

public class PreferencesManager {

    // fake implementation
    public static final int getLoggedUserId() {
        return User.getAllUsers().get(0).getId();
    }
}
