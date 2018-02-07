package io.moku.davide.sideproject.utils.gridView

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import io.moku.davide.sideproject.R
import kotlinx.android.synthetic.main.programming_language_cell_layout.view.*

/**
 * Created by Davide Castello on 06/02/18.
 * Project: side-project
 * Copyright © 2018 Moku S.r.l. All rights reserved.
 */
class KotlinProgrammingLanguagesAdapter(val context: Context, val languageUsages: List<ProgrammingLanguageUsage>) : BaseAdapter() {

    val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view = convertView ?: inflater.inflate(R.layout.programming_language_cell_layout, parent, false)
        val usage = languageUsages.get(position)
        view.name.text = usage.language.name
        view.usage.text = String.format(context.getString(R.string.usage), usage.people)
        view.setOnClickListener(null)
        return view
    }

    override fun getItem(position: Int): Any = languageUsages.get(position)

    override fun getItemId(position: Int): Long = languageUsages.get(position).language.id.toLong()

    override fun getCount(): Int = languageUsages.size
}