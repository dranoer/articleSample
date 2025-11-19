package com.dranoer.article.domain.model 

data class Article(
    val id: String,
    val title: String,
    val summary: String,
    val updatedAt: String,
    val contentMarkdown: String? = null
)