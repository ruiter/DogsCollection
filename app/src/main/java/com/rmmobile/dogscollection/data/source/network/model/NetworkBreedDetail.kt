package com.rmmobile.dogscollection.data.source.network.model

import com.google.gson.annotations.JsonAdapter
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkBreedDetail(@field:Json(name = "message") val message: String)