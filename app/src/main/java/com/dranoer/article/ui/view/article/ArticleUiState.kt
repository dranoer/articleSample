package com.dranoer.article.ui.view.article

import com.dranoer.article.ui.model.ArticleUiModel
import com.dranoer.article.util.ErrorType

sealed class ArticleUiState {
    object Empty : ArticleUiState()

    object Loading : ArticleUiState()

    data class Loaded(
        val articles: List<ArticleUiModel>
    ) : ArticleUiState()

    data class Error(
        val type: ErrorType,
        val message: String
    ) : ArticleUiState()
}