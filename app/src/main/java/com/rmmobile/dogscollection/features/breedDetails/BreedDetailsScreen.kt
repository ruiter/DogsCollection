package com.rmmobile.dogscollection.features.breedDetails

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.rmmobile.dogscollection.components.ErrorScreen
import com.rmmobile.dogscollection.components.Loading
import com.rmmobile.dogscollection.ui.theme.ManropeFontFamily
import com.rmmobile.dogscollection.util.ResourceState
import kotlin.math.min

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
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Navigate back")
                }
            }
        )

        when (breedDetail) {
            is ResourceState.Loading -> {
                Loading()
            }

            is ResourceState.Success -> {
                breedPhotoUrl = (breedDetail as ResourceState.Success).data.url
                BreedDetailContent(
                    breedPhotoUrl = breedPhotoUrl,
                    breed = breed,
                    subBreed = subBreed
                )
            }

            is ResourceState.Error -> {
                ErrorScreen()
            }
        }
    }
}

@Composable
fun BreedDetailContent(breedPhotoUrl: String, breed: String?, subBreed: String?) {
    val painter = rememberAsyncImagePainter(model = breedPhotoUrl)
    val state = painter.state

    val transition by animateFloatAsState(
        targetValue = if (state is AsyncImagePainter.State.Success) 1f else 0f
    )

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (state is AsyncImagePainter.State.Loading) {
            CircularProgressIndicator()
        }
        Image(
            painter = painter,
            contentDescription = "breed photo",
            modifier = Modifier
                .scale(.8f + (.2f * transition))
                .graphicsLayer { rotationX = (1f - transition) * 5f }
                .alpha(
                    min(1f, transition / .2f)
                )
        )

        Text(
            text = "$breed - $subBreed",
            fontSize = 16.sp,
            fontFamily = ManropeFontFamily,
            fontWeight = FontWeight.Medium
        )
    }
}