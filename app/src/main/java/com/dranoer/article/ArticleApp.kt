package com.dranoer.article

import android.app.Application
import com.dranoer.article.data.worker.WorkScheduler
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ArticleApp : Application() {

    override fun onCreate() {
        super.onCreate()
        WorkScheduler.scheduleDailyPrefetch(applicationContext)
    }
}