package io.moku.davide.sideproject.model;

import java.util.Comparator;

import io.moku.davide.sideproject.utils.gridView.ProgrammingLanguageUsage;
import io.moku.davide.sideproject.utils.realm.RealmUtils;
import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Davide Castello on 30/01/18.
 * Project: TestingLibraries
 * Copyright © 2018 Moku S.r.l. All rights reserved.
 */

public class ProgrammingLanguage extends RealmObject {

    @PrimaryKey
    private int id;
    private String name;

    public ProgrammingLanguage() {}
    public ProgrammingLanguage(int id, String name) {
        this.id = id;
        this.name = name;
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

    public static RealmResults<ProgrammingLanguage> getAllLanguages() {

        Realm realm = null;
        RealmResults<ProgrammingLanguage> languages = null;
        try {
            realm = RealmUtils.getCurrentRealm();
            languages = realm.where(ProgrammingLanguage.class).findAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (realm != null && !realm.isClosed()) {
                realm.close();
            }
        }
        return languages;
    }

    public int getUsage() {
        int people = 0;
        for(User user : User.getAllUsers()) {
            for (UsedProgrammingLanguage language : user.getUsedProgrammingLanguages()) {
                if (language.getLanguage().getId() == id) {
                    people++;
                }
            }
        }
        return people;
    }














}
