package com.rmmobile.dogscollection.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rmmobile.dogscollection.data.repository.HomeRepository
import com.rmmobile.dogscollection.data.source.network.model.NetworkBreeds
import com.rmmobile.dogscollection.util.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val homeRepository: HomeRepository) : ViewModel() {

    private val _breeds : MutableStateFlow<ResourceState<NetworkBreeds>> = MutableStateFlow(ResourceState.Loading())
    val breeds : StateFlow<ResourceState<NetworkBreeds>> = _breeds

    init {
        getAllBreeds()
    }

    private fun getAllBreeds() {
        viewModelScope.launch(Dispatchers.IO) {
            homeRepository.getAllBreeds().collectLatest {
                _breeds.value = it
            }
        }
    }
}