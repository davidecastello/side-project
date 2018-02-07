package io.moku.davide.sideproject.utils.recyclerView

import android.content.Context
import android.widget.ImageView
import io.moku.davide.sideproject.R
import io.moku.davide.sideproject.model.User
import io.moku.davide.sideproject.utils.assets.ImagesUtils
import io.moku.davide.sideproject.utils.datetime.DateUtils
import java.sql.Timestamp
import java.util.*

/**
 * Created by Davide Castello on 07/02/18.
 * Project: side-project
 * Copyright © 2018 Moku S.r.l. All rights reserved.
 */
class Post(val user: User,
           val timestamp: Long,
           val photoUrl: String?,
           var likes: Int = 0) {

    fun getDateString() = DateUtils.getDateInTheCorrectFormat(timestamp)

    fun getLikesString(context: Context) = String.format(context.getString(R.string.likes), likes)

    fun loadPhoto(context: Context, imageView: ImageView?)
            = ImagesUtils.loadUrlIntoImageView(photoUrl, context, imageView, R.drawable.pattern)

    companion object {
        fun getLatestPosts() : List<Post> =
            User.getAllUsers().map { it -> Post(
                    user = it,
                    timestamp = DateUtils.getRandomTimestampInTheLastMonth(),
                    photoUrl = getRandomPhotoUrl(),
                    likes = getRandomLikesNumber()) }
                    .sortedByDescending { it.timestamp }

        fun getRandomLikesNumber() : Int = (Math.random() * 200).toInt()

        fun getRandomPhotoUrl() : String? = listOf(
                "https://images3.alphacoders.com/823/82317.jpg",
                "https://www.hdwallpapers.in/walls/autumn_bench-HD.jpg",
                "http://hdfreewallpaper.net/wp-content/uploads/2015/10/nice-beautiful-hd-free-wallpapers-for-desktop.jpg",
                "http://s1.bwallpapers.com/wallpapers/2014/05/09/blue-full-hd-desktop-wallpapers_034316171.jpg",
                "http://globalmedicalco.com/photos/globalmedicalco/8/36012.jpg",
                "https://i.chzbgr.com/full/3725317/h8D9E50A1/",
                "http://kb4images.com/images/great-pictures/37928934-great-pictures.jpg").random()

        fun <E> List<E>.random(): E? = if (size > 0) get(Random().nextInt(size)) else null
    }
}