package io.moku.davide.sideproject.profile

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.AdapterView
import io.moku.davide.sideproject.MainActivity
import io.moku.davide.sideproject.R
import io.moku.davide.sideproject.model.User
import io.moku.davide.sideproject.utils.activity.BasicSecondaryActivity
import io.moku.davide.sideproject.utils.preferences.PreferencesManager
import kotlinx.android.synthetic.main.activity_change_profile.*

fun Context.ChangeProfileActivityIntent(userId: Int): Intent {
    return Intent(this, ChangeProfileActivity::class.java).apply {
        putExtra(User.EXTRA_USER_ID, userId)
    }
}

class ChangeProfileActivity : BasicSecondaryActivity() {

    private val adapter by lazy { setupAdapter() }
    private var selectedUserId = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_profile)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        selectedUserId = intent.getIntExtra(User.EXTRA_USER_ID, -1)

        usersLV.adapter = adapter
        usersLV.setOnItemClickListener { parent, view, position, id -> run {
            val user = parent.getItemAtPosition(position) as User
            if(selectedUserId != user.id) {
                selectedUserId = user.id
                usersLV.setItemChecked(position, true)
            }
        } }
        usersLV.setItemChecked(adapter.getPosition(User.getUser(selectedUserId)), true)
    }

    private fun setupAdapter(): ChangeProfileAdapter = ChangeProfileAdapter(this, R.layout.profile_cell_layout, User.getAllUsers().map { it })

    override fun onBackPressed() {
        PreferencesManager.storeLoggedUserId(this, selectedUserId)
        finish()
    }
}