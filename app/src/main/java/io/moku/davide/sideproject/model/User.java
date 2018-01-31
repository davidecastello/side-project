package io.moku.davide.sideproject.model;

import com.google.gson.annotations.SerializedName;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import io.moku.davide.sideproject.R;
import io.moku.davide.sideproject.utils.realm.RealmUtils;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Davide Castello on 29/01/18.
 * Project: TestingLibraries
 * Copyright © 2018 Moku S.r.l. All rights reserved.
 */

public class User extends RealmObject {

    @PrimaryKey private int id;
    private String name;
    private String personalInfo;
    private RealmList<UsedProgrammingLanguage> usedProgrammingLanguages;

    public User() {}
    public User(int id, String name, String personalInfo, RealmList<UsedProgrammingLanguage> usedProgrammingLanguages) {
        this.id = id;
        this.name = name;
        this.personalInfo = personalInfo;
        this.usedProgrammingLanguages = usedProgrammingLanguages;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPersonalInfo() {
        return personalInfo;
    }

    public void setPersonalInfo(String personalInfo) {
        this.personalInfo = personalInfo;
    }

    public RealmList<UsedProgrammingLanguage> getUsedProgrammingLanguages() {
        return usedProgrammingLanguages;
    }

    public void setUsedProgrammingLanguages(RealmList<UsedProgrammingLanguage> usedProgrammingLanguages) {
        this.usedProgrammingLanguages = usedProgrammingLanguages;
    }

    public static List<User> getAllUsers() {

        Realm realm = null;
        List<User> users = new ArrayList<>();
        try {
            realm = RealmUtils.getCurrentRealm();
            users = realm.where(User.class).findAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (realm != null && !realm.isClosed()) {
                realm.close();
            }
        }
        return users;
    }
}
