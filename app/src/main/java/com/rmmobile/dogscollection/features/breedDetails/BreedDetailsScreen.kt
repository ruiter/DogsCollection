package com.rmmobile.dogscollection.features.breedDetails

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.rmmobile.dogscollection.components.ErrorScreen
import com.rmmobile.dogscollection.components.Loading
import com.rmmobile.dogscollection.util.ResourceState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BreedDetailsScreen(navController: NavController, breed: String?, subBreed: String?) {
    val viewModel: BreedDetailViewModel = hiltViewModel()

    val breedDetail by viewModel.breedDetail.collectAsState()
    var breedPhotoUrl = ""

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text(text = "Breed Detail") },
            navigationIcon = {
                IconButton(onClick = { /* Your back navigation action */ }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Navigate back")
                }
            }
        )

        when(breedDetail) {
            is ResourceState.Loading -> {
                Loading()
            }

            is ResourceState.Success -> {
                breedPhotoUrl = (breedDetail as ResourceState.Success).data.url
                BreedDetailContent(breedPhotoUrl)
            }

            is ResourceState.Error -> {
                ErrorScreen()
            }
        }
    }
}

@Composable
fun BreedDetailContent(breedPhotoUrl: String) {
    Log.i("ruiter", "breedPhotoUrl - $breedPhotoUrl: ")
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
        AsyncImage(
            model = breedPhotoUrl,
            contentDescription = "breed photo",
            contentScale = ContentScale.Crop
        )
        Text(text = "Content")
    }
}