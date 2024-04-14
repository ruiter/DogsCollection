package com.rmmobile.dogscollection.features.breedDetails

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun BreedDetailsScreen(navController: NavController, breed: String?, subBreed: String?) {
    Text(text = "Breed details $breed - $subBreed")
}