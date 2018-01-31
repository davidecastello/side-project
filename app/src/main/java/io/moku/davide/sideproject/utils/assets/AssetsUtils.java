package io.moku.davide.sideproject.utils.assets;

import com.google.gson.Gson;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

import io.moku.davide.sideproject.utils.Constants;

/**
 * Created by Davide Castello on 31/01/18.
 * Project: side-project
 * Copyright © 2018 Moku S.r.l. All rights reserved.
 */

public class AssetsUtils {

    public static String readJsonFile(Context context, String fileName) {

        String json = null;

        // Load JSON from asset
        try {
            InputStream is = context.getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, Constants.DEFAULT_CHARSET);
            return json;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static <E> E parseJson(String json, Class<E> eClass) {
        return new Gson().fromJson(json, eClass);
    }
}
