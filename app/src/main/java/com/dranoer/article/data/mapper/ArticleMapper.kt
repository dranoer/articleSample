package com.dranoer.article.data.mapper

import com.dranoer.article.data.model.ArticleDto
import com.dranoer.article.domain.model.Article
import javax.inject.Inject

class ArticleMapper @Inject constructor() {

    fun mapArticles(articles: List<ArticleDto>): List<Article> {
        return articles.map { mapArticle(it) }
    }

    fun mapArticle(article: ArticleDto): Article {
        return Article(
            id = article.id,
            title = article.title ?: "",
            summary = article.summary ?: "",
            updatedAt = article.updatedAt ?: "",
            contentMarkdown = article.contentMarkdown
        )
    }
}