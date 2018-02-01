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
        boolean validUrl = !TextUtils.isEmpty(url);
        Picasso picasso = Picasso.with(context);
        RequestCreator creator = (validUrl) ? picasso.load(url) : picasso.load(placeholderId);
        creator = creator.resize(400, 400)
                .centerCrop()
                .placeholder(placeholderId);
        if (validUrl) { creator = creator.error(placeholderId); }
        creator.into(imageView);
    }
}
