package com.dranoer.article.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dranoer.article.ui.component.DetailItem

@Composable
internal fun DetailScreen() {
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            DetailItem()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailPreview() {
    DetailScreen()
}