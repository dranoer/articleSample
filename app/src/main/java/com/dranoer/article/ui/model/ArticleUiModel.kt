package com.dranoer.article.ui.model

data class ArticleUiModel(
    val id: String,
    val title: String,
    val summary: String,
    val updatedAt: String,
    val contentMarkdown: String? = null
)