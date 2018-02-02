package io.moku.davide.sideproject.utils.realm;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import android.content.Context;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import io.moku.davide.sideproject.model.ProgrammingLanguage;
import io.moku.davide.sideproject.model.User;
import io.moku.davide.sideproject.utils.Constants;
import io.moku.davide.sideproject.utils.assets.AssetsUtils;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Davide Castello on 30/01/18.
 * Project: TestingLibraries
 * Copyright © 2018 Moku S.r.l. All rights reserved.
 */

public class RealmUtils {

    private static RealmConfiguration currentConfiguration;
    private static final Type userToken = new TypeToken<User>(){}.getType();

    public static void initialize(Context context) {
        Realm.init(context);
        currentConfiguration = new RealmConfiguration.Builder()
                .name("current.realm")
                .deleteRealmIfMigrationNeeded()
                .build();
    }

    public static Realm getCurrentRealm() {
        return Realm.getInstance(currentConfiguration);
    }

    public static int getCurrentInstancesCount() {
        return Realm.getLocalInstanceCount(currentConfiguration);
    }

    public static void onCreateApplication() {
        getCurrentRealm();
    }

    public static void onTerminateApplication() {
        Realm realm = getCurrentRealm();
        if (!realm.isClosed()) realm.close();
        if (!realm.isClosed()) realm.close();
    }

    public static void loadDB(Context context) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(userToken, new UserTypeAdapter())
                .create();
        Realm realm = null;

        try {
            realm = RealmUtils.getCurrentRealm();
            realm.beginTransaction();

            // Programming Languages
            List<ProgrammingLanguage> programmingLanguages = loadProgrammingLanguages(gson, context);
            realm.insertOrUpdate(programmingLanguages);

            // Users
            List<User> users = loadUsers(gson, context);
            realm.insertOrUpdate(users);

            realm.commitTransaction();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (realm != null && !realm.isClosed()) {
                realm.close();
            }
        }
    }

    private static List<ProgrammingLanguage> loadProgrammingLanguages(Gson gson, Context context) {
        List<ProgrammingLanguage> programmingLanguages = new ArrayList<>();
        String dbFileProgrammingLanguages = AssetsUtils.readJsonFile(context, Constants.DB_FILE_PROGRAMMING_LANGUAGUES);
        if (dbFileProgrammingLanguages != null) {
            JsonArray programmingLanguagesJson = new JsonParser().parse(dbFileProgrammingLanguages).getAsJsonArray();
            for (JsonElement element : programmingLanguagesJson) {
                programmingLanguages.add(gson.fromJson(element, ProgrammingLanguage.class));
            }
        }
        return programmingLanguages;
    }

    private static List<User> loadUsers(Gson gson, Context context) {
        List<User> users = new ArrayList<>();
        String dbFileUsers = AssetsUtils.readJsonFile(context, Constants.DB_FILE_USERS);
        if (dbFileUsers != null) {
            JsonArray usersJson = new JsonParser().parse(dbFileUsers).getAsJsonArray();
            for (JsonElement element : usersJson) {
                users.add(gson.fromJson(element, User.class));
            }
        }
        return users;
    }

}