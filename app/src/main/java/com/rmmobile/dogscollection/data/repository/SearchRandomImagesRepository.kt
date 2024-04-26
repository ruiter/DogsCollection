package com.rmmobile.dogscollection.data.repository

import android.util.Log
import com.rmmobile.dogscollection.data.model.RandomImagesOfBreed
import com.rmmobile.dogscollection.data.source.network.datasource.DogsDataSource
import com.rmmobile.dogscollection.data.source.network.model.NetworkRandomImagesOfBreed
import com.rmmobile.dogscollection.util.ResourceState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchRandomImagesRepository @Inject constructor(private val dogsDataSource: DogsDataSource) {
    
    suspend fun searchRandomImagesOfBreed(breed: String): Flow<ResourceState<RandomImagesOfBreed>> {
        return flow {
            emit(ResourceState.Loading())

            val response = dogsDataSource.searchRandomImagesOfBreed(breed = breed)
            val randomImagesList = response.body()
            if (response.isSuccessful) {
                Log.i("ruiter", "randomImage list: $randomImagesList ");
                if (randomImagesList != null) {
                    emit(ResourceState.Success(randomImagesList.toRandomImagesOfBreed()))
                }
            } else {
                emit(ResourceState.Error("Error to search random breed image"))
            }
        }.catch { e ->
            Log.i("ruiter", "error exception: ${e.message}");
            Log.i("ruiter", "error exception: ${e.stackTrace}");
            emit(
                ResourceState.Error(
                    e.localizedMessage ?: "Exception trying fetching data from breed detail"
                )
            )
        }
    }

    fun NetworkRandomImagesOfBreed.toRandomImagesOfBreed() = RandomImagesOfBreed(listBreedsImages = message)
}