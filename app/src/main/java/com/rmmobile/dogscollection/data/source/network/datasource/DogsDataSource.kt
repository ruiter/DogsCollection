package com.rmmobile.dogscollection.data.source.network.datasource

import com.rmmobile.dogscollection.data.source.network.model.NetworkBreedDetail
import com.rmmobile.dogscollection.data.source.network.model.NetworkBreeds
import com.rmmobile.dogscollection.data.source.network.model.NetworkRandomImagesOfBreed
import retrofit2.Response

interface DogsDataSource {

    suspend fun getAllBreeds(): Response<NetworkBreeds>

    suspend fun getBreedAndSubBreed(breed: String, subBreed: String): Response<NetworkBreedDetail>

    suspend fun searchRandomImagesOfBreed(breed: String): Response<NetworkRandomImagesOfBreed>
}