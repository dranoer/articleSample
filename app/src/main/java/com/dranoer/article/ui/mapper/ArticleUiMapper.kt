package com.dranoer.article.ui.mapper

import com.dranoer.article.domain.model.Article
import com.dranoer.article.ui.model.ArticleUiModel
import javax.inject.Inject

class ArticleUiMapper @Inject constructor() {

    fun mapArticles(articles: List<Article>): List<ArticleUiModel> {
        return articles.map { mapArticle(it) }
    }

    fun mapArticle(article: Article): ArticleUiModel {
        return ArticleUiModel(
            id = article.id,
            title = article.title,
            summary = article.summary,
            updatedAt = article.updatedAt,
            contentMarkdown = article.contentMarkdown
        )
    }
}