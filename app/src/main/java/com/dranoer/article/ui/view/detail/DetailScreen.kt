package com.dranoer.article.ui.view.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.dranoer.article.R
import com.dranoer.article.ui.theme.ArticleTheme
import com.dranoer.article.ui.view.component.EmptyView
import com.dranoer.article.ui.view.component.ErrorView
import com.dranoer.article.ui.view.component.LoadingView
import com.dranoer.article.ui.viewmodel.ArticleViewModel

@Composable
internal fun DetailScreen(
    id: String,
    viewModel: ArticleViewModel,
    onBackClick: () -> Unit
) {
    val state by viewModel.detailState.collectAsState()

    LaunchedEffect(id) {
        viewModel.getArticleDetail(id)
    }

    ArticleDetail(
        state = state,
        onRetryClick = { viewModel.getArticleDetail(id) },
        onBackClick = onBackClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ArticleDetail(
    state: DetailUiState,
    onRetryClick: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val title = when (state) {
        is DetailUiState.Loaded -> state.title
        else -> "Article"
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(title) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            painter = painterResource(R.drawable.ic_back),
                            contentDescription = stringResource(R.string.back)
                        )
                    }
                }
            )
        },
        modifier = modifier
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            when (state) {
                DetailUiState.Loading -> {
                    LoadingView()
                }

                is DetailUiState.Loaded -> {
                    DetailItem(
                        content = state.contentMarkdown
                    )
                }

                is DetailUiState.Error -> {
                    ErrorView(
                        message = state.message,
                        onRetryClick = onRetryClick
                    )
                }

                DetailUiState.Empty -> {
                    EmptyView()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailScreenLoadedPreview() {
    ArticleTheme {
        ArticleDetail(
            state = DetailUiState.Loaded(
                title = "Sample article title",
                contentMarkdown = "# Heading\n\nSome markdown content."
            ),
            onRetryClick = {},
            onBackClick = {}
        )
    }
}