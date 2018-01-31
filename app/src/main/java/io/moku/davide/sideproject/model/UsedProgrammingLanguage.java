package io.moku.davide.sideproject.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by Davide Castello on 31/01/18.
 * Project: side-project
 * Copyright © 2018 Moku S.r.l. All rights reserved.
 */

public class UsedProgrammingLanguage extends RealmObject {

    private ProgrammingLanguage language;
    private float percentage;

    public UsedProgrammingLanguage() {}
    public UsedProgrammingLanguage(ProgrammingLanguage language, float percentage) {
        this.language = language;
        this.percentage = percentage;
    }

    public ProgrammingLanguage getLanguage() {
        return language;
    }

    public void setLanguage(ProgrammingLanguage language) {
        this.language = language;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }
}
