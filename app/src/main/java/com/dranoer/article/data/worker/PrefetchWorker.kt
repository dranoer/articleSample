package com.dranoer.article.data.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.dranoer.article.data.repository.ArticleRepositoryImpl
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * Daily lightweight cache refresh.
 *
 * Updates the cached article list and
 * Prefetches a few article details.
 */
class PrefetchWorker @Inject constructor(
    @ApplicationContext context: Context,
    params: WorkerParameters,
    private val repository: ArticleRepositoryImpl
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        return try {
            val results = repository.getArticles()
            if (results is com.dranoer.article.util.Result.Success) {
                results.data.take(3).forEach { article -> repository.getArticleDetail(article.id) }
            }
            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }

    companion object {
        const val DAILY_PREFETCH = "daily_prefetch"
    }
}