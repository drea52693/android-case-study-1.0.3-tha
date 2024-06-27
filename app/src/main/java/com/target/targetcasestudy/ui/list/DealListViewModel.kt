package com.target.targetcasestudy.ui.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.target.targetcasestudy.api.Deal
import com.target.targetcasestudy.api.DealsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DealListViewModel @Inject constructor(
    application: Application,
    private val dealsRepository: DealsRepository,
) : AndroidViewModel(application) {

    private val _dealListUiStateFlow = MutableStateFlow<List<Deal>>(emptyList())
    val dealListUiStateFlow = _dealListUiStateFlow.asStateFlow()

    init {
        fetchDeals()
    }

    private fun fetchDeals() {
        viewModelScope.launch {
            runCatching {
                dealsRepository.retrieveDeals()
            }.onFailure {
                // Handle error
            }.onSuccess { deals ->
                _dealListUiStateFlow.getAndUpdate { deals.products }
            }
        }
    }
}