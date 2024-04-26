package com.rmmobile.dogscollection.data.source.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkRandomImagesOfBreed(@field:Json(name = "message") val message: List<String>)
