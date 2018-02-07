package io.moku.davide.sideproject.utils.assets;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.pkmmte.view.CircularImageView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

/**
 * Created by Davide Castello on 01/02/18.
 * Project: side-project
 * Copyright © 2018 Moku S.r.l. All rights reserved.
 */

public class ImagesUtils {

    public static void loadUrlIntoImageView(String url, Context context, ImageView imageView, int placeholderId) {
        loadUrlIntoImageView(url, context, imageView, placeholderId, true);
    }

    public static void loadUrlIntoImageView(String url, Context context, ImageView imageView, int placeholderId, boolean resize) {
        boolean validUrl = !TextUtils.isEmpty(url);
        Picasso picasso = Picasso.with(context);
        RequestCreator creator = (validUrl) ? picasso.load(url) : picasso.load(placeholderId);
        if (resize) { creator = creator.resize(1200, 1200).centerCrop(); }
        creator = creator.placeholder(placeholderId);
        if (validUrl) { creator = creator.error(placeholderId); }
        creator.into(imageView);
    }
}
