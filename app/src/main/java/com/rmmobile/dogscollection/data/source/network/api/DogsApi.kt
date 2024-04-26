package com.rmmobile.dogscollection.data.source.network.api

import com.rmmobile.dogscollection.data.source.network.model.NetworkBreedDetail
import com.rmmobile.dogscollection.data.source.network.model.NetworkBreeds
import com.rmmobile.dogscollection.data.source.network.model.NetworkRandomImagesOfBreed
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DogsApi {

    @GET("api/breeds/list/all")
    suspend fun getAllBreeds(): Response<NetworkBreeds>

    @GET("api/breed/{breed}/{subbreed}/images/random")
    suspend fun getBreedAndSubBreed(
        @Path("breed") breed: String,
        @Path("subbreed") subbreed: String
    ): Response<NetworkBreedDetail>

    @GET("api/breed/{breed}/images/random/3")
    suspend fun searchRandomImagesOfBreed(@Path("breed") breed: String): Response<NetworkRandomImagesOfBreed>
}

// https://dog.ceo/api/breed/hound/images/random/3
// https://dog.ceo/api/breeds/list/all

// SINGLE RANDOM IMAGE FROM A SUB BREED COLLECTION
// https://dog.ceo/api/breed/hound/afghan/images/random