package io.moku.davide.sideproject.model;

import com.google.gson.annotations.SerializedName;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import io.moku.davide.sideproject.R;
import io.moku.davide.sideproject.utils.assets.AssetsUtils;
import io.moku.davide.sideproject.utils.assets.ImagesUtils;
import io.moku.davide.sideproject.utils.preferences.PreferencesManager;
import io.moku.davide.sideproject.utils.realm.RealmUtils;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Davide Castello on 29/01/18.
 * Project: TestingLibraries
 * Copyright © 2018 Moku S.r.l. All rights reserved.
 */

public class User extends RealmObject {

    @Ignore public static final String EXTRA_USER_ID = "extraUserId";

    @PrimaryKey private int id;
    private String name;
    private String personalInfo;
    private String profilePictureUrl;
    private String backgroundCoverUrl;
    private RealmList<UsedProgrammingLanguage> usedProgrammingLanguages;

    public User() {}
    public User(int id, String name, String personalInfo, String profilePictureUrl, String backgroundCoverUrl, RealmList<UsedProgrammingLanguage> usedProgrammingLanguages) {
        this.id = id;
        this.name = name;
        this.personalInfo = personalInfo;
        this.profilePictureUrl = profilePictureUrl;
        this.backgroundCoverUrl = backgroundCoverUrl;
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

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public String getBackgroundCoverUrl() {
        return backgroundCoverUrl;
    }

    public void setBackgroundCoverUrl(String backgroundCoverUrl) {
        this.backgroundCoverUrl = backgroundCoverUrl;
    }

    public RealmList<UsedProgrammingLanguage> getUsedProgrammingLanguages() {
        return usedProgrammingLanguages;
    }

    public void setUsedProgrammingLanguages(RealmList<UsedProgrammingLanguage> usedProgrammingLanguages) {
        this.usedProgrammingLanguages = usedProgrammingLanguages;
    }

    public static RealmResults<User> getAllUsers() {

        Realm realm = null;
        RealmResults<User> users = null;
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

    public static RealmResults<User> getLoggedUserFriends(Context context) {
        return getUser(PreferencesManager.getLoggedUserId(context)).getFriends();
    }

    public static User getUser(int userId) {
        Realm realm = null;
        User user = null;
        try {
            realm = RealmUtils.getCurrentRealm();
            RealmResults<User> results = realm.where(User.class).equalTo("id", userId).findAll();
            if (results.size() != 0) {
                user = results.first();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (realm != null && !realm.isClosed()) {
                realm.close();
            }
        }
        return user;
    }

    public RealmResults<User> getFriends() {
        Realm realm = null;
        RealmResults<User> users = null;
        try {
            realm = RealmUtils.getCurrentRealm();
            users = realm.where(User.class).notEqualTo("id", id).findAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (realm != null && !realm.isClosed()) {
                realm.close();
            }
        }
        return users;
    }

    public void loadProfilePicture(Context context, ImageView imageView) {
        ImagesUtils.loadUrlIntoImageView(profilePictureUrl, context, imageView, R.drawable.boy);
    }

    public void loadBackgroundCover(Context context, ImageView imageView) {
        ImagesUtils.loadUrlIntoImageView(backgroundCoverUrl, context, imageView, R.drawable.no_background_cover, false);
    }

    public boolean isValidProfilePictureUrl() { return isValidUrl(profilePictureUrl); }

    public boolean isValidBackgroundCoverUrl() { return isValidUrl(backgroundCoverUrl); }

    private boolean isValidUrl(String url) {
        return !TextUtils.isEmpty(url);
    }
}
