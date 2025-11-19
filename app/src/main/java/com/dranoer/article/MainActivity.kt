package com.dranoer.article

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dranoer.article.ui.theme.ArticleTheme
import com.dranoer.article.ui.view.article.ArticlesScreen
import com.dranoer.article.ui.view.detail.DetailScreen
import com.dranoer.article.ui.viewmodel.ArticleViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: ArticleViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArticleTheme {
                AppScreen(viewModel)
            }
        }
    }
}

@Composable
private fun AppScreen(viewModel: ArticleViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "articles"
    ) {
        composable("articles") {
            ArticlesScreen(
                viewModel = viewModel,
                navigateToDetail = { articleId ->
                    navController.navigate("detail/$articleId")
                }
            )
        }
        composable("detail/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")

            DetailScreen(
                id = id ?: "",
                viewModel = viewModel,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}