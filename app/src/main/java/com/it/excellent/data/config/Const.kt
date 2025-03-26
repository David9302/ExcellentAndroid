package com.it.excellent.data.config

import android.content.Context
import android.os.Environment
import com.it.excellent.R
import com.it.excellent.common.utils.Utils
import java.io.File

object Const {

    private val appContext: Context by lazy {
        Utils.getApp()
    }

    val COVER_PATH: String by lazy {
        appContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES)?.path ?: run {
            File(appContext.filesDir, "pictures").apply { mkdirs() }.absolutePath
        }
    }

    val COLUMN_LINK: String @JvmStatic get() = appContext.getString(R.string.article_navigation).also {
        require(it.isNotBlank()) { "Navigation link cannot be empty"}
    }

    val PROJECT_LINK: String @JvmStatic get() = appContext.getString(R.string.github_project).also {
        require(it.isNotBlank()) { "GitHub link cannot be empty"}
    }
}