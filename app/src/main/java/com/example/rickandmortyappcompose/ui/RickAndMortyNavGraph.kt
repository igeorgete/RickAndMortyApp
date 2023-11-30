package com.example.rickandmortyappcompose.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.rickandmortyappcompose.ui.detail.DetailsScreen
import com.example.rickandmortyappcompose.ui.home.HomeScreen
import com.example.rickandmortyappcompose.ui.loading.LoadingScreen

@Composable
fun RickAndMortyNavGraph(
    modifier: Modifier = Modifier,
    navigateToHome: () -> Unit,
    navigateToDetail: (Int) -> Unit,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.Loading.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(route = Screen.Loading.route) {
            LoadingScreen(
                onLoadingFinished = navigateToHome
            )
        }
        composable(route = Screen.Home.route) {
            HomeScreen(
                onItemClicked = { navigateToDetail(it) }
            )
        }
        composable(
            route = Screen.Detail.route,
            arguments = listOf(
                navArgument("id") { type = NavType.IntType }
            )
        ) {
            DetailsScreen(
                upPress = navigateToHome
            )
        }
    }
}