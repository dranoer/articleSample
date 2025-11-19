package com.dranoer.article.domain.repository

import com.dranoer.article.domain.model.Article

interface ArticleRepository {

    suspend fun getArticles(): Result<List<Article>>

    suspend fun getArticleDetail(id: String): Result<Article>
}