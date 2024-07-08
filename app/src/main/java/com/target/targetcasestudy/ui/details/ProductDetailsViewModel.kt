package com.target.targetcasestudy.ui.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.target.targetcasestudy.api.Deal
import com.target.targetcasestudy.api.DealsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(savedStateHandle: SavedStateHandle, repository: DealsRepository): ViewModel() {

    private val _productDetailsUiState = MutableStateFlow<Deal?>(null)
    val productDetailsUiState = _productDetailsUiState.asStateFlow()

    init {
        val id = savedStateHandle.get<String>("item")
        id?.let {
            viewModelScope.launch {
                val deal = repository.retrieveDeal(id)
                _productDetailsUiState.getAndUpdate { deal }
            }
        } ?: error("Deal ID must be provided.")
    }
}