package io.moku.davide.testinglibraries.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import io.moku.davide.testinglibraries.R;

/**
 * Created by Davide Castello on 29/01/18.
 * Project: TestingLibraries
 * Copyright © 2018 Moku S.r.l. All rights reserved.
 */

public class User {

    private String name;
    private String description;

    public User(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static List<User> getUsers(Context context) {
        int USERS_COUNT = 10;
        List<User> users = new ArrayList<>();
        for (int i = 0; i < USERS_COUNT; ++i) {
            users.add(new User(context.getString(R.string.example_name), context.getString(R.string.example_description)));
        }
        return users;
    }
}
