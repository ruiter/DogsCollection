package com.rmmobile.dogscollection.data.repository

import com.rmmobile.dogscollection.data.model.BreedDetail
import com.rmmobile.dogscollection.data.source.network.datasource.DogsDataSource
import com.rmmobile.dogscollection.data.source.network.model.NetworkBreedDetail
import com.rmmobile.dogscollection.util.ResourceState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BreedDetailRepository @Inject constructor(private val dataSourceImpl: DogsDataSource) {

    suspend fun getBreedAndSubBreed(breed: String, subBreed: String): Flow<ResourceState<BreedDetail>> {
        return flow {
            emit(ResourceState.Loading())

            val response = dataSourceImpl.getBreedAndSubBreed(breed, subBreed)

            if (response.isSuccessful && response.body() != null) {
                emit(ResourceState.Success(response.body()!!.toBreedDetail()))
            }
        }
    }

    private fun NetworkBreedDetail.toBreedDetail() = BreedDetail(url = url)
}