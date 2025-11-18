package com.dranoer.article.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dranoer.article.ui.component.ArticleItem
import com.dranoer.article.ui.theme.ArticleTheme

@Composable
internal fun ArticlesScreen() {
    ArticleTheme {
        Scaffold(
            content = { padding ->
                Box(
                    modifier = Modifier
                        .padding(padding)
                        .fillMaxSize()
                ) {
                    ArticleList(emptyList())
                }
            })
    }
}

@Composable
private fun ArticleList(
    articles: List<String>
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        items(articles.size) { index ->
            ArticleItem(
                onClick = {}
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ArticleListPreview() {
    ArticleList(listOf("1", "2", "3"))
}