package com.target.targetcasestudy.ui.list


import androidx.lifecycle.ViewModel
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
    private val dealsRepository: DealsRepository,
) : ViewModel() {

    private val _dealListUiStateFlow = MutableStateFlow<List<Deal>>(emptyList())
    val dealListUiStateFlow = _dealListUiStateFlow.asStateFlow()

    private val _dealUiStateFlow = MutableStateFlow<Deal?>(null)
    val dealUiStateFlow = _dealUiStateFlow.asStateFlow()

    init {
        viewModelScope.launch {
            //fetchDeals()
        }
    }

    suspend fun fetchDeals() {
        _dealListUiStateFlow.getAndUpdate {
            dealsRepository.retrieveDeals().products
        }
    }

    fun fetchDeal(dealId: String) {
        viewModelScope.launch {
            _dealUiStateFlow.getAndUpdate {
                dealsRepository.retrieveDeal(dealId)
            }
        }
    }
}
