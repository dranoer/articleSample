package com.dranoer.article

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.dranoer.article.ui.view.article.ArticleUiState
import com.dranoer.article.ui.view.article.ArticlesScreenContent
import com.dranoer.article.util.ErrorType
import org.junit.Rule
import org.junit.Test

class ArticlesScreenTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun errorState_showsError_andRetryCallsCallback() {
        val uiState = ArticleUiState.Error(
            type = ErrorType.Network,
            message = "No internet or server unavailable."
        )
        var retryCalled = false

        rule.setContent {
            ArticlesScreenContent(
                state = uiState,
                query = "",
                filteredArticles = emptyList(),
                onArticleClick = {},
                onSearchChange = {},
                onRetryClick = { retryCalled = true }
            )
        }

        rule.onNodeWithText("No internet or server unavailable.").assertIsDisplayed()
        rule.onNodeWithText("Retry").performClick()
        assert(retryCalled)
    }
}