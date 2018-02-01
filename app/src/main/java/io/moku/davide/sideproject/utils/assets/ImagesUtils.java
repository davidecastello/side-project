package io.moku.davide.sideproject.utils.assets;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by Davide Castello on 01/02/18.
 * Project: side-project
 * Copyright © 2018 Moku S.r.l. All rights reserved.
 */

public class ImagesUtils {

    public static void loadUrlIntoImageView(String url, Context context, ImageView imageView, int placeholderId) {
        if(!TextUtils.isEmpty(url)) {
            Picasso.with(context)
                    .load(url)
                    .placeholder(placeholderId)
                    .error(placeholderId)
                    .into(imageView);
        } else {
            Picasso.with(context)
                    .load(placeholderId)
                    .placeholder(placeholderId)
                    .into(imageView);
        }
    }
}
