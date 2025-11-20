package com.dranoer.article.data.worker

import android.content.Context
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.dranoer.article.data.worker.PrefetchWorker.Companion.DAILY_PREFETCH
import java.util.concurrent.TimeUnit

object WorkScheduler {

    fun scheduleDailyPrefetch(context: Context) {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresBatteryNotLow(true)
            .build()

        val request = PeriodicWorkRequestBuilder<PrefetchWorker>(
            1, TimeUnit.DAYS
        ).setConstraints(constraints)
            .build()

        WorkManager.getInstance(context)
            .enqueueUniquePeriodicWork(
                DAILY_PREFETCH,
                ExistingPeriodicWorkPolicy.KEEP,
                request
            )
    }
}