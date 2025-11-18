package com.dranoer.article.ui.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
internal fun DetailItem() {
    Text("Article Detail\n details")
}

@Preview(showBackground = true)
@Composable
private fun DetailItemPreview() {
    DetailItem()
}