package com.rmmobile.dogscollection.data.repository

import com.rmmobile.dogscollection.data.source.network.datasource.DogsDataSource
import com.rmmobile.dogscollection.data.source.network.model.NetworkBreeds
import com.rmmobile.dogscollection.util.ResourceState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class HomeRepository @Inject constructor(private val homeDataSourceImpl: DogsDataSource) {

//    suspend fun getAllBreeds(): Response<NetworkBreeds> {
//        return homeDataSourceImpl.getAllBreeds()
//    }

    suspend fun getAllBreeds(): Flow<ResourceState<NetworkBreeds>> {
        return flow {
            emit(ResourceState.Loading())

            val response = homeDataSourceImpl.getAllBreeds()

            if (response.isSuccessful && response.body() != null) {
                emit(ResourceState.Success(response.body()!!))
            } else {
                emit(ResourceState.Error("Error trying fetching data"))
            }
        }.catch { e ->
            emit(ResourceState.Error(e.localizedMessage ?: "Exception trying fetching data"))
        }
    }
}