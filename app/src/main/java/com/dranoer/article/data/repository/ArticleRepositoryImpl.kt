package com.dranoer.article.data.repository

import com.dranoer.article.data.mapper.ArticleMapper
import com.dranoer.article.data.model.ArticleDto
import com.dranoer.article.data.remote.ApiService
import com.dranoer.article.domain.model.Article
import com.dranoer.article.domain.repository.ArticleRepository
import com.dranoer.article.util.Result
import com.dranoer.article.util.toErrorType
import com.dranoer.shared.cache.DEFAULT_TTL_MS
import com.dranoer.shared.cache.KeyValueCache
import com.dranoer.shared.currentTimeMillis
import javax.inject.Inject

class ArticleRepositoryImpl @Inject constructor(
    val apiService: ApiService,
    val mapper: ArticleMapper,
    private val cache: KeyValueCache
) : ArticleRepository {

    override suspend fun getArticles(): Result<List<Article>> {
        return try {
            val result = apiService.getArticles()
            cache.put(KEY_ARTICLES, result)
            Result.Success(mapper.mapArticles(result))
        } catch (e: Exception) {
            val cached = cache.get(KEY_ARTICLES)
            if (cached != null && !cached.isStale(DEFAULT_TTL_MS, currentTimeMillis())) {
                val cacheDto = cached.value as List<ArticleDto>
                Result.Success(mapper.mapArticles(cacheDto.toList()))
            } else {
                Result.Error(e.toErrorType())
            }
        }
    }

    override suspend fun getArticleDetail(id: String): Result<Article> {
        return try {
            val result = apiService.getArticleDetail(id)
            cache.put(detailKey(id), result)
            Result.Success(mapper.mapArticle(result))
        } catch (e: Exception) {
            val cached = cache.get(detailKey(id))
            if (cached != null && !cached.isStale(DEFAULT_TTL_MS, currentTimeMillis())) {
                val cacheDto = cached.value as ArticleDto
                Result.Success(mapper.mapArticle(cacheDto))
            } else {
                Result.Error(e.toErrorType())
            }
        }
    }

    companion object {
        private const val KEY_ARTICLES = "articles"
        private fun detailKey(id: String) = "detail_$id"
    }
}