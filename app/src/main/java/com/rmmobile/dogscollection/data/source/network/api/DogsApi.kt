package com.rmmobile.dogscollection.data.source.network.api

import com.rmmobile.dogscollection.data.source.network.model.NetworkBreeds
import retrofit2.Response
import retrofit2.http.GET

interface DogsApi {

    @GET("api/breeds/list/all")
    suspend fun getAllBreeds(): Response<NetworkBreeds>
}

// https://dog.ceo/api/breeds/list/all