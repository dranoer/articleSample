package com.dranoer.article.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dranoer.article.domain.repository.ArticleRepository
import com.dranoer.article.ui.mapper.ArticleUiMapper
import com.dranoer.article.ui.view.article.ArticleUiState
import com.dranoer.article.ui.view.detail.DetailUiState
import com.dranoer.article.util.Result
import com.dranoer.article.util.toUserMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(
    private val repository: ArticleRepository,
    private val mapper: ArticleUiMapper,
) : ViewModel() {

    private val _articleState = MutableStateFlow<ArticleUiState>(ArticleUiState.Loading)
    val articleState = _articleState.asStateFlow()

    private val _detailState = MutableStateFlow<DetailUiState>(DetailUiState.Loading)
    val detailState = _detailState.asStateFlow()

    init {
        getArticles()
    }

    internal fun getArticles() {
        viewModelScope.launch {
            when (val result = repository.getArticles()) {
                is Result.Success -> {
                    if (result.data.isEmpty()) {
                        _articleState.value = ArticleUiState.Empty
                    } else {
                        val articles = mapper.mapArticles(result.data)
                        _articleState.value = ArticleUiState.Loaded(articles)
                    }
                }

                is Result.Error -> {
                    _articleState.value = ArticleUiState.Error(
                        type = result.type,
                        message = result.type.toUserMessage()
                    )
                }
            }
        }
    }

    internal fun getArticleDetail(id: String) {
        viewModelScope.launch {
            when (val result = repository.getArticleDetail(id)) {
                is Result.Success -> {
                    val article = mapper.mapArticle(result.data)
                    _detailState.value = DetailUiState.Loaded(
                        title = article.title,
                        contentMarkdown = article.contentMarkdown ?: ""
                    )
                }

                is Result.Error -> {
                    _detailState.value = DetailUiState.Error(
                        type = result.type,
                        message = result.type.toUserMessage()
                    )
                }
            }
        }
    }
}