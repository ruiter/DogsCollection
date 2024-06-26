package com.rmmobile.dogscollection.data.source.network.datasource

import com.rmmobile.dogscollection.data.source.network.api.DogsApi
import com.rmmobile.dogscollection.data.source.network.model.NetworkBreedDetail
import com.rmmobile.dogscollection.data.source.network.model.NetworkBreeds
import com.rmmobile.dogscollection.data.source.network.model.NetworkRandomImagesOfBreed
import retrofit2.Response
import javax.inject.Inject

class DataSourceImpl @Inject constructor(private val dogsApi: DogsApi): DogsDataSource {
    override suspend fun getAllBreeds(): Response<NetworkBreeds> {
        return dogsApi.getAllBreeds()
    }

    override suspend fun getBreedAndSubBreed(
        breed: String,
        subBreed: String
    ): Response<NetworkBreedDetail> {
        return dogsApi.getBreedAndSubBreed(breed = breed, subbreed = subBreed)
    }

    override suspend fun searchRandomImagesOfBreed(breed: String): Response<NetworkRandomImagesOfBreed> {
        return dogsApi.searchRandomImagesOfBreed(breed = breed)
    }
}