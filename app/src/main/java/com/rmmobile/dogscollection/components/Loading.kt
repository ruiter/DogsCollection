package com.rmmobile.dogscollection.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Loading() {
    CircularProgressIndicator(
        modifier = Modifier.size(50.dp).padding(10.dp),
        color = Color.Black
    )
}