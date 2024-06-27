package com.target.targetcasestudy.ui.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.target.targetcasestudy.api.Deal
import com.target.targetcasestudy.api.DealsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(savedStateHandle: SavedStateHandle, repository: DealsRepository): ViewModel() {

    val productDetailsUiState: MutableStateFlow<Deal?> = MutableStateFlow(null)

    init {
        val id = savedStateHandle.get<String>("item")
        id?.let {
            viewModelScope.launch {
                val deal = repository.retrieveDeal(id)
                productDetailsUiState.update { deal }
            }
        } ?: error("Deal ID must be provided.")
    }
}