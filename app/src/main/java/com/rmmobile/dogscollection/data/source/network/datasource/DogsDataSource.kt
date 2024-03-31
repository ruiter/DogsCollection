package com.rmmobile.dogscollection.data.source.network.datasource

import com.rmmobile.dogscollection.data.source.network.model.NetworkBreeds
import retrofit2.Response

interface DogsDataSource {

    suspend fun getAllBreeds(): Response<NetworkBreeds>
}