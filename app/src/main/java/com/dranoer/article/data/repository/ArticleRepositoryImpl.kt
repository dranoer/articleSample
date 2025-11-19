package com.dranoer.article.data.repository

import com.dranoer.article.data.mapper.ArticleMapper
import com.dranoer.article.data.remote.ApiService
import com.dranoer.article.domain.model.Article
import com.dranoer.article.domain.repository.ArticleRepository
import com.dranoer.article.util.Result
import com.dranoer.article.util.toErrorType
import javax.inject.Inject

class ArticleRepositoryImpl @Inject constructor(
    val apiService: ApiService,
    val mapper: ArticleMapper
) : ArticleRepository {

    override suspend fun getArticles():  Result<List<Article>> {
        return try {
            val result = apiService.getArticles()
            Result.Success(mapper.mapArticles(result))
        } catch (e: Exception) {
            apiService.getArticles()
            Result.Error(e.toErrorType())
        }
    }

    override suspend fun getArticleDetail(id: String): Result<Article> {
        return try {
            val result = apiService.getArticleDetail(id)
            Result.Success(mapper.mapArticle(result))
        } catch (e: Exception) {
            Result.Error(e.toErrorType())
        }
    }
}