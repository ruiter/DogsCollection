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

    suspend fun getAllBreeds(): Flow<ResourceState<List<Breeds>>> {
        return flow {
            emit(ResourceState.Loading())

            val response = homeDataSourceImpl.getAllBreeds()

            if (response.isSuccessful && response.body() != null) {
                val listOfBreeds = getBreedsFromMap(response.body()!!)
                emit(ResourceState.Success(listOfBreeds))
            } else {
                emit(ResourceState.Error("Error trying fetching data"))
            }
        }.catch { e ->
            emit(ResourceState.Error(e.localizedMessage ?: "Exception trying fetching data"))
        }
    }

    private fun getBreedsFromMap(dogBreeds: NetworkBreeds): List<Breeds> {
        val breedsList = mutableListOf<Breeds>()

        dogBreeds.message.forEach { (breedname, subBreeds) ->
            subBreeds.forEach { subBreed ->
                breedsList.add(Breeds(breedname, subBreed))
            }
        }

        return breedsList.toList()
    }
}