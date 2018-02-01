package io.moku.davide.sideproject.utils.gridView;

import java.util.Comparator;

import io.moku.davide.sideproject.model.ProgrammingLanguage;

/**
 * Created by Davide Castello on 01/02/18.
 * Project: side-project
 * Copyright © 2018 Moku S.r.l. All rights reserved.
 */

public class ProgrammingLanguageUsage {

    private ProgrammingLanguage language;
    private int people;

    public static final Comparator<ProgrammingLanguageUsage> ORDER_BY_USAGE = new Comparator<ProgrammingLanguageUsage>() {
        @Override
        public int compare(ProgrammingLanguageUsage lhs, ProgrammingLanguageUsage rhs) {
            return (lhs.getPeople() < rhs.getPeople()) ? 1 : -1;
        }
    };

    public ProgrammingLanguageUsage() {}
    public ProgrammingLanguageUsage(ProgrammingLanguage language, int people) {
        this.language = language;
        this.people = people;
    }

    public ProgrammingLanguage getLanguage() {
        return language;
    }

    public void setLanguage(ProgrammingLanguage language) {
        this.language = language;
    }

    public int getPeople() {
        return people;
    }

    public void setPeople(int people) {
        this.people = people;
    }
}
