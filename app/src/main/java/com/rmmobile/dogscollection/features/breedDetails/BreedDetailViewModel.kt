package com.rmmobile.dogscollection.features.breedDetails

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rmmobile.dogscollection.data.model.BreedDetail
import com.rmmobile.dogscollection.data.repository.BreedDetailRepository
import com.rmmobile.dogscollection.util.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BreedDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    val breedDetailRepository: BreedDetailRepository
) : ViewModel() {

    val breed: String = checkNotNull(savedStateHandle["breed"])
    val subBreed: String = checkNotNull(savedStateHandle["subBreed"])

    private val _breedDetail: MutableStateFlow<ResourceState<BreedDetail>> = MutableStateFlow(ResourceState.Loading())
    val breedDetail: StateFlow<ResourceState<BreedDetail>> = _breedDetail

    init {
        Log.i("ruiter", "breed in viewmodel: $breed $subBreed");
        getBreedAndSubBreed(breed, subBreed)
    }
    fun getBreedAndSubBreed(breed: String, subBreed: String) {
        viewModelScope.launch(Dispatchers.IO) {
            breedDetailRepository.getBreedAndSubBreed(breed = breed, subBreed = subBreed).collectLatest {
                _breedDetail.value = it
            }
        }
    }
}