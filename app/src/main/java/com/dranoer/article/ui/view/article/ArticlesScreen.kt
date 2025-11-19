package com.dranoer.article.ui.view.article

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dranoer.article.ui.model.ArticleUiModel
import com.dranoer.article.ui.theme.ArticleTheme
import com.dranoer.article.ui.view.component.EmptyView
import com.dranoer.article.ui.view.component.ErrorView
import com.dranoer.article.ui.view.component.LoadingView
import com.dranoer.article.ui.viewmodel.ArticleViewModel

@Composable
internal fun ArticlesScreen(
    viewModel: ArticleViewModel,
    navigateToDetail: (String) -> Unit
) {
    val state by viewModel.articleState.collectAsState()

    ArticleTheme {
        Scaffold(
            content = { padding ->
                Box(
                    modifier = Modifier
                        .padding(padding)
                        .fillMaxSize()
                ) {
                    when (val currentState = state) {
                        is ArticleUiState.Loading -> {
                            LoadingView()
                        }

                        is ArticleUiState.Loaded -> {
                            ArticleList(
                                articles = currentState.articles,
                                onArticleClick = { articleId ->
                                    navigateToDetail(articleId)
                                }
                            )
                        }

                        is ArticleUiState.Error -> {
                            ErrorView(
                                message = currentState.message,
                                onRetryClick = { viewModel.getArticles() }
                            )
                        }

                        is ArticleUiState.Empty -> {
                            EmptyView()
                        }
                    }
                }
            }
        )
    }
}

@Composable
private fun ArticleList(
    articles: List<ArticleUiModel>,
    onArticleClick: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        items(articles.size) { index ->
            val article = articles[index]
            ArticleItem(
                article = article,
                onClick = { onArticleClick(article.id) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ArticlesPreview() {
    ArticleList(
        articles = listOf(
            ArticleUiModel(
                id = "1",
                title = "title 2",
                summary = "summary one\nsummary one\nsummary one",
                updatedAt = "12/12/2022"
            ),
            ArticleUiModel(
                id = "2",
                title = "title 2",
                summary = "summary one\nsummary two\nsummary two",
                updatedAt = "03/12/2025"
            ),
            ArticleUiModel(
                id = "3",
                title = "title 3",
                summary = "summary one\nsummary two\nsummary two",
                updatedAt = "03/12/2025"
            )
        ),
        onArticleClick = {}
    )
}