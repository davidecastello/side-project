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

class ChangeProfileAdapter(val _context: Context, val layoutResourceId : Int, var users: List<User>)
    : ArrayAdapter<User>(_context, layoutResourceId, users) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view = convertView ?: (_context as Activity).layoutInflater.inflate(layoutResourceId, parent, false)
        val user = users.get(position)
        view.profileNameCheckedTV.text = user.name
        user.loadProfilePicture(_context, view.profilePic)
        return view
    }
}