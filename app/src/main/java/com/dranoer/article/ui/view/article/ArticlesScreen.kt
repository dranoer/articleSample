package com.dranoer.article.ui.view.article

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dranoer.article.R
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
    val query by viewModel.searchQuery.collectAsState()
    val filteredArticles by viewModel.filteredArticles.collectAsState()

    ArticlesScreenContent(
        state = state,
        query = query,
        filteredArticles = filteredArticles,
        onArticleClick = navigateToDetail,
        onSearchChange = viewModel::onSearchQueryChanged,
        onRetryClick = { viewModel.getArticles() }
    )
}

@Composable
internal fun ArticlesScreenContent(
    state: ArticleUiState,
    query: String,
    filteredArticles: List<ArticleUiModel>,
    onArticleClick: (String) -> Unit,
    onSearchChange: (String) -> Unit,
    onRetryClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold { padding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            SearchField(
                value = query,
                onValueChange = onSearchChange
            )

            Box(modifier = Modifier.fillMaxSize()) {
                when (state) {
                    is ArticleUiState.Loading -> LoadingView()
                    is ArticleUiState.Empty -> EmptyView()
                    is ArticleUiState.Error -> ErrorView(
                        message = state.message,
                        onRetryClick = onRetryClick
                    )

                    is ArticleUiState.Loaded -> {
                        if (filteredArticles.isEmpty() && query.isNotEmpty()) {
                            FilterNotFound()
                        } else {
                            ArticleList(
                                articles = filteredArticles,
                                onArticleClick = onArticleClick
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ArticleList(
    articles: List<ArticleUiModel>,
    onArticleClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
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

@Composable
private fun FilterNotFound(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = stringResource(id = R.string.filter_not_found))
    }
}

@Preview(showBackground = true)
@Composable
private fun ArticlesPreview() {
    ArticleTheme {
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
}

@Preview()
@Composable
private fun FilterNotFoundPreview() {
    ArticleTheme {
        FilterNotFound()
    }
}