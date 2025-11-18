package com.dranoer.article

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dranoer.article.ui.screen.ArticlesScreen
import com.dranoer.article.ui.screen.DetailScreen
import com.dranoer.article.ui.theme.ArticleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArticleTheme {
                AppScreen()
            }
        }
    }
}

@Composable
private fun AppScreen() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "articles"
    ) {
        composable("articles") {
            ArticlesScreen(
                navigateToDetail = { navController.navigate("detail/$id") }
            )
        }
        composable("detail/{id}") { backStackEntry ->
            DetailScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}