package io.moku.davide.sideproject.utils.realm;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import android.util.Log;

import java.io.IOException;

import io.moku.davide.sideproject.model.ProgrammingLanguage;
import io.moku.davide.sideproject.model.UsedProgrammingLanguage;
import io.moku.davide.sideproject.model.User;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by Davide Castello on 31/01/18.
 * Project: side-project
 * Copyright © 2018 Moku S.r.l. All rights reserved.
 */

public class UserTypeAdapter extends TypeAdapter<User> {

    public static final String TAG = UserTypeAdapter.class.getSimpleName();

    @Override
    public void write(JsonWriter out, User value) throws IOException {
        // No need for now
    }

    @Override
    public User read(JsonReader in) throws IOException {
        User user = new User();

        Realm realm = null;
        try {
            realm = RealmUtils.getCurrentRealm();

            in.beginObject();
            while (in.hasNext()) {
                String name = in.nextName();
                switch (name) {
                    case "id":
                        user.setId(getIntValue(in));
                        break;
                    case "name":
                        user.setName(getStringValue(in));
                        break;
                    case "info":
                        user.setPersonalInfo(getStringValue(in));
                        break;
                    case "profile_picture_url":
                        user.setProfilePictureUrl(getStringValue(in));
                        break;
                    case "background_cover_url":
                        user.setBackgroundCoverUrl(getStringValue(in));
                        break;
                    case "used_languages":
                        setUsedLanguages(realm, user, in);
                        break;
                    default:
                        in.skipValue();
                        break;
                }
            }
            in.endObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (realm != null && !realm.isClosed()) {
                realm.close();
            }
        }

        return user;
    }

    private int getIntValue(JsonReader in) throws IOException {
        if(in.peek() != JsonToken.NULL) {
            return in.nextInt();
        } else {
            in.skipValue();
            return -1;
        }
    }

    private String getStringValue(JsonReader in) throws IOException {
        if(in.peek() != JsonToken.NULL) {
            return in.nextString();
        } else {
            in.skipValue();
            return null;
        }
    }


    private void setUsedLanguages(Realm realm, User user, JsonReader in) throws IOException {
        RealmList<UsedProgrammingLanguage> usedProgrammingLanguages = new RealmList<>();
        in.beginArray();
        while (in.hasNext()) {

            UsedProgrammingLanguage language = retrieveUsedProgrammingLanguage(realm, in);
            if (language != null) {
                usedProgrammingLanguages.add(language);
            }

            /*int skillId = in.nextInt();
            // Retrieve Skill with that skillID from Realm and add it in the list
            RealmResults<Skill> results = realm.where(Skill.class).equalTo("id", skillId).findAll();
            if (results.size() != 0) {
                Skill skill = results.first();
                //Log.d("ServiceGenerator", skill.getName());
                list.add(skill);
            } else {
                Log.d("ServiceGenerator", "SKILL NOT FOUND");
            }*/
        }
        user.setUsedProgrammingLanguages(usedProgrammingLanguages);
        in.endArray();
    }

    private UsedProgrammingLanguage retrieveUsedProgrammingLanguage(Realm realm, JsonReader in) throws IOException {
        UsedProgrammingLanguage usedProgrammingLanguage = new UsedProgrammingLanguage();
        in.beginObject();
        while (in.hasNext()) {
            String name = in.nextName();
            switch (name) {
                case "id":
                    setLanguage(realm, usedProgrammingLanguage, getIntValue(in));
                    break;
                case "percentage":
                    usedProgrammingLanguage.setPercentage(getPercentage(in.nextDouble()));
                    break;
                default:
                    in.skipValue();
                    break;
            }
        }
        in.endObject();
        if (usedProgrammingLanguage.getLanguage() != null) {
            return usedProgrammingLanguage;
        } else {
            return null;
        }
    }

    private float getPercentage(double value) {
        return (float) value / 100;
    }

    private void setLanguage(Realm realm, UsedProgrammingLanguage language, int id) throws IOException {
        if (id != -1) {
            RealmResults<ProgrammingLanguage> results = realm.where(ProgrammingLanguage.class).equalTo("id", id).findAll();
            if (results.size() != 0) {
                language.setLanguage(results.first());
            } else {
                Log.i(TAG, "LINGUAGGIO NON TROVATO");
            }
        }
    }








}
