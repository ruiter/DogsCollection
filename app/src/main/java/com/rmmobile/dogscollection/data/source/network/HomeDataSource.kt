package com.rmmobile.dogscollection.data.source.network

import com.rmmobile.dogscollection.data.source.network.api.DogsApi
import com.rmmobile.dogscollection.data.source.network.model.NetworkBreeds
import retrofit2.Response
import javax.inject.Inject

class HomeDataSource @Inject constructor(): DogsApi {
    override fun getAllBreeds(): Response<NetworkBreeds> {
        TODO("Not yet implemented")
    }

}