package io.moku.davide.sideproject.kotlin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import io.moku.davide.sideproject.R
import io.moku.davide.sideproject.model.ProgrammingLanguage
import io.moku.davide.sideproject.utils.activity.BasicSecondaryActivity
import io.moku.davide.sideproject.utils.gridView.ProgrammingLanguageUsage
import kotlinx.android.synthetic.main.activity_kotlin_programming_languages.*

fun Context.KotlinProgrammingLanguagesActivityIntent(): Intent {
    return Intent(this, KotlinProgrammingLanguagesActivity::class.java)
    /*return Intent(this, KotlinProgrammingLanguagesActivityIntent()::class.java).apply {
        putExtra(KotlinProgrammingLanguagesActivity.EXTRA_MSG, msg)
    }*/
}

class KotlinProgrammingLanguagesActivity : BasicSecondaryActivity() {

    private val adapter by lazy { setupAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_programming_languages)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        programmingLanguagesGridView.adapter = adapter
    }

    private fun setupAdapter(): KotlinProgrammingLanguagesAdapter
            = KotlinProgrammingLanguagesAdapter(this, ProgrammingLanguage.getAllLanguages()
            .map { it -> ProgrammingLanguageUsage(it, it.usage) }
            .sortedByDescending { it.people })
}
