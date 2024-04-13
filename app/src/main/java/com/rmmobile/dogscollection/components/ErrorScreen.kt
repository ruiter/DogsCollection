package com.rmmobile.dogscollection.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rmmobile.dogscollection.R

@Composable
fun ErrorScreen() {
    Column (modifier = Modifier.fillMaxSize().padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
        Image(
            modifier = Modifier.size(100.dp).padding(16.dp),
            painter = painterResource(id = R.drawable.error_inspect_ios11_svgrepo_com),
            contentDescription = "connection error"
        )
        Text(text = stringResource(id = R.string.connection_error), fontSize = 18.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorPreview() {
    ErrorScreen()
}
