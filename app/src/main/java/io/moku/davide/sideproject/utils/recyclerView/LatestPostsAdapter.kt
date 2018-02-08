package io.moku.davide.sideproject.utils.recyclerView

import android.app.Activity
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.moku.davide.sideproject.R
import io.moku.davide.sideproject.model.User
import io.moku.davide.sideproject.profile.DisplayUserPhotoActivity
import io.moku.davide.sideproject.profile.ProfileActivity
import io.moku.davide.sideproject.utils.alerts.ProgressDialogHelper
import io.moku.davide.sideproject.utils.assets.DisplayFullPhotoActivity
import io.moku.davide.sideproject.utils.datetime.DateUtils
import io.moku.davide.sideproject.utils.preferences.PreferencesManager
import kotlinx.android.synthetic.main.post_cell_layout.view.*

/**
 * Created by Davide Castello on 07/02/18.
 * Project: side-project
 * Copyright © 2018 Moku S.r.l. All rights reserved.
 */
class LatestPostsAdapter(val context: Context, var posts: List<Post>) : RecyclerView.Adapter<LatestPostsAdapter.PostViewHolder>() {

    override fun getItemCount(): Int = posts.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PostViewHolder
            = PostViewHolder(LayoutInflater.from(context).inflate(R.layout.post_cell_layout, parent, false))

    override fun onBindViewHolder(holder: PostViewHolder?, position: Int) {
        val post = posts.get(position)
        val view = holder?.itemView
        // update UI
        view?.postName?.text = post.user.name
        view?.postTime?.text = post.getDateString()
        view?.postLikes?.text = post.getLikesString(context)
        post.loadPhoto(context, view?.postPhoto)
        post.user.loadProfilePicture(context, view?.postProfilePic)
        // listeners
        view?.postInfoLayout?.setOnClickListener { v -> v.context.startActivity(ProfileActivity.newIntent(v.context, post.user.id)) }
        view?.postPhoto?.setOnClickListener { v -> v.context.startActivity(DisplayFullPhotoActivity.newIntent(v.context, post.photoUrl)) }
        view?.postLikeButton?.setOnClickListener({
            post.likes++
            view.postLikes?.text = post.getLikesString(view.context)
        })
        view?.postShareButton?.setOnClickListener({
            sharePost(post)
        })
    }

    fun sharePost(post: Post) {
        // prepare dialog
        val helper = ProgressDialogHelper.Builder(context as Activity)
                .setInitalMsg(context.getString(R.string.operation_running))
                .setDoneMsg(context.getString(R.string.post_shared))
                .setAnimationName("check_done.json")
                .setSpeed(0.5f)
                .show()
        // share post
        posts = posts.plus(Post(user = User.getUser(PreferencesManager.getLoggedUserId(context)),
                                timestamp = DateUtils.getCurrentTimestamp(),
                                photoUrl = post.photoUrl))
                .sortedByDescending { it.timestamp }
        // notify adapter
        notifyItemInserted(0)
        // show animation "Post shared!"
        helper.done()
    }

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}