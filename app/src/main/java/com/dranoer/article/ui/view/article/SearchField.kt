package com.dranoer.article.ui.view.article

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dranoer.article.R
import com.dranoer.article.ui.theme.ArticleTheme

@Composable
internal fun SearchField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 16.dp),
        placeholder = { Text(stringResource(R.string.search)) }
    )
}

@Preview()
@Composable
private fun SearchFieldPreview() {
    ArticleTheme {
        SearchField(value = "value", onValueChange = {})
    }
}