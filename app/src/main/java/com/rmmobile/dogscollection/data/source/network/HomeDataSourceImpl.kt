package com.rmmobile.dogscollection.data.source.network

import com.rmmobile.dogscollection.data.source.network.api.DogsApi
import com.rmmobile.dogscollection.data.source.network.datasource.DogsDataSource
import com.rmmobile.dogscollection.data.source.network.model.NetworkBreeds
import retrofit2.Response
import javax.inject.Inject

class HomeDataSourceImpl @Inject constructor(private val dogsApi: DogsApi): DogsDataSource {
    override suspend fun getAllBreeds(): Response<NetworkBreeds> {
        return dogsApi.getAllBreeds()
    }
}