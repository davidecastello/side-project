package io.moku.davide.sideproject.profile

import android.app.Activity
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import io.moku.davide.sideproject.R
import io.moku.davide.sideproject.model.User
import kotlinx.android.synthetic.main.profile_cell_layout.view.*

/**
 * Created by Davide Castello on 08/02/18.
 * Project: side-project
 * Copyright © 2018 Moku S.r.l. All rights reserved.
 */

class ChangeProfileAdapter(val context: Context, var users: List<User>, var selectedUserId: Int)
    : RecyclerView.Adapter<ChangeProfileAdapter.ProfileViewHolder>() {

    override fun getItemCount(): Int = users.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ProfileViewHolder
            = ProfileViewHolder(LayoutInflater.from(context).inflate(R.layout.profile_cell_layout, parent, false))

    override fun onBindViewHolder(holder: ProfileViewHolder?, position: Int) {
        val user = users.get(position)
        val view = holder?.itemView
        // update UI
        view?.profileNameCheckedTV?.text = user.name
        user.loadProfilePicture(context, view?.profilePic)
        val shouldBeChecked = (user.id == selectedUserId)
        view?.profileNameCheckedTV?.isChecked = shouldBeChecked
        println("${user.name}: $shouldBeChecked")
        // listeners
        view?.setOnClickListener { v -> run {
            if (selectedUserId != user.id) {
                // val oldSelectedUserId = selectedUserId // da usare per ottimizzazione: notifyItemChanged(position)
                selectedUserId = user.id
                notifyDataSetChanged()
            }
        } }
    }

    class ProfileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}