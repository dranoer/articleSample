package com.dranoer.article.ui.view.detail

import com.dranoer.article.util.ErrorType

sealed class DetailUiState {
    object Empty : DetailUiState()

    object Loading : DetailUiState()

    data class Loaded(
        val title: String,
        val contentMarkdown: String
    ) : DetailUiState()

    data class Error(
        val type: ErrorType,
        val message: String
    ) : DetailUiState()
}