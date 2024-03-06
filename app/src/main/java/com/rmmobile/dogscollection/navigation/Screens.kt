package com.rmmobile.dogscollection.navigation

sealed class Screens(val route: String) {

    object Home: Screens("Home")

    object Search: Screens("Search")

    object BreedDetails: Screens("Bread_Details")
}