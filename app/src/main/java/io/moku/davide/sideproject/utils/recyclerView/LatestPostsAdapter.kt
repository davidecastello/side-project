package io.moku.davide.sideproject.utils.recyclerView

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.moku.davide.sideproject.R
import io.moku.davide.sideproject.profile.DisplayUserPhotoActivity
import io.moku.davide.sideproject.profile.ProfileActivity
import io.moku.davide.sideproject.utils.assets.DisplayFullPhotoActivity
import kotlinx.android.synthetic.main.post_cell_layout.view.*

/**
 * Created by Davide Castello on 07/02/18.
 * Project: side-project
 * Copyright © 2018 Moku S.r.l. All rights reserved.
 */
class LatestPostsAdapter(val context: Context, val posts: List<Post>) : RecyclerView.Adapter<LatestPostsAdapter.PostViewHolder>() {

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
    }


    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}