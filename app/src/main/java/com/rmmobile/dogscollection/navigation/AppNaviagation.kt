package com.rmmobile.dogscollection.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rmmobile.dogscollection.features.breedDetails.BreedDetailsScreen
import com.rmmobile.dogscollection.features.home.HomeScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val breedDetailsRoute = Screens.BreedDetails.route
    NavHost(navController = navController, startDestination = Screens.Home.route) {
        composable(route = Screens.Home.route) {
            HomeScreen(viewModel = hiltViewModel(), navController)
        }
        composable(
            route = "$breedDetailsRoute/{breed}/{subBreed}",
            arguments = listOf(
                navArgument("breed") { type = NavType.StringType },
                navArgument("subBreed") { type = NavType.StringType })
        ) { backStackEntry ->
            BreedDetailsScreen(
                navController,
                backStackEntry.arguments?.getString("breed"),
                backStackEntry.arguments?.getString("subBreed")
            )
        }
    }
}