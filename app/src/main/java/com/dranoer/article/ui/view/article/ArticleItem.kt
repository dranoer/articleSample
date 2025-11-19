package com.dranoer.article.ui.view.article

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dranoer.article.ui.model.ArticleUiModel

@Composable
internal fun ArticleItem(
    article: ArticleUiModel,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = article.title,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = article.summary,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = article.updatedAt,
                style = MaterialTheme.typography.bodySmall,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ArticlePreview() {
    ArticleItem(
        article = ArticleUiModel(
            id = "1",
            title = "title 1",
            summary = "summary one\nsummary one\nsummary one",
            updatedAt = "12/12/2022"
        ),
        onClick = {}
    )
}