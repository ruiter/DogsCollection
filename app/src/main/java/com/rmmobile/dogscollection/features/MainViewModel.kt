package com.rmmobile.dogscollection.features

import androidx.annotation.StringRes
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor() : ViewModel() {

    private val _selectedTab: MutableState<Int> = mutableIntStateOf(0)
    val selectedTab: State<Int> get() = _selectedTab

    fun selectTab(@StringRes tab: Int) {
        _selectedTab.value = tab
    }
}