package com.rmmobile.dogscollection.features.home

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.rmmobile.dogscollection.components.ErrorScreen
import com.rmmobile.dogscollection.components.Loading
import com.rmmobile.dogscollection.data.model.Breeds
import com.rmmobile.dogscollection.navigation.Screens
import com.rmmobile.dogscollection.util.ResourceState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(homeViewModel: HomeViewModel, navController: NavController) {

    val breeds by homeViewModel.breeds.collectAsState()

    var listOfBreeds = listOf<Breeds>()

    Box(modifier = Modifier.fillMaxSize()) {
        TopAppBar(title = { Text(text = "Breeds Collection") })

        when (breeds) {
            is ResourceState.Loading -> {
                Loading()
            }

            is ResourceState.Success -> {
                listOfBreeds = (breeds as ResourceState.Success).data
                Log.i("ruiter", "success state: $listOfBreeds");
            }

            is ResourceState.Error -> {
                ErrorScreen()
            }
        }
        LazyColumn(
            modifier = Modifier
                .padding(top = 64.dp, start = 16.dp, end = 16.dp, bottom = 48.dp)
                .fillMaxWidth()
        ) {
            items(listOfBreeds) { breeds ->
                BreedsCard(breed = breeds.name, subBreed = breeds.subname) { breed, subBreed ->
                    Log.i("ruiter", "it string clicked: $breed");
                    navController.navigate(Screens.BreedDetails.route+"/$breed/$subBreed")
                }
            }
        }
    }
}

@Composable
fun BreedsCard(breed: String, subBreed: String, onItemClicked: (String, String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClicked(breed, subBreed) }
            .padding(bottom = 8.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .weight(1f),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = breed, fontSize = 16.sp)
                Text(
                    text = subBreed,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            Icon(
                imageVector = Icons.Filled.KeyboardArrowRight,
                contentDescription = null,
                modifier = Modifier.padding(end = 8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    //HomeContent()
}