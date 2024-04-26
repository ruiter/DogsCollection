package com.rmmobile.dogscollection.data.repository

import com.rmmobile.dogscollection.data.model.BreedDetail
import com.rmmobile.dogscollection.data.source.network.datasource.DogsDataSource
import com.rmmobile.dogscollection.data.source.network.model.NetworkBreedDetail
import com.rmmobile.dogscollection.util.ResourceState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BreedDetailRepository @Inject constructor(private val dataSourceImpl: DogsDataSource) {

    suspend fun getBreedAndSubBreed(
        breed: String,
        subBreed: String
    ): Flow<ResourceState<BreedDetail>> {
        return flow {
            emit(ResourceState.Loading())

            val response = dataSourceImpl.getBreedAndSubBreed(breed, subBreed)
            val breeddetail = response.body()
            if (response.isSuccessful) {
                if (breeddetail != null) {
                    emit(ResourceState.Success(breeddetail.toBreedDetail()))
                }
            } else {
                emit(ResourceState.Error("Error on get breed details."))
            }
        }.catch { e ->
            emit(
                ResourceState.Error(
                    e.localizedMessage ?: "Exception trying fetching data from breed detail"
                )
            )
        }
    }

    private fun NetworkBreedDetail.toBreedDetail() = BreedDetail(url = message)
}