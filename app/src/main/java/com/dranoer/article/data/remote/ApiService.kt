package com.dranoer.article.data.remote

import com.dranoer.article.data.model.ArticleDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("articles")
    suspend fun getArticles(): List<ArticleDto>

    @GET("articles/{id}")
    suspend fun getArticleDetail(@Path("id") id: String): ArticleDto
}