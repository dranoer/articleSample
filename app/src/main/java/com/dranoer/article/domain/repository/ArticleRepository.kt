package com.dranoer.article.domain.repository

import com.dranoer.article.domain.model.Article
import com.dranoer.article.util.Result

interface ArticleRepository {

    suspend fun getArticles(): Result<List<Article>>

    suspend fun getArticleDetail(id: String): Result<Article>
}