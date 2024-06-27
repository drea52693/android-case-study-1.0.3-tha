package com.target.targetcasestudy.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.target.targetcasestudy.api.Deal
import com.target.targetcasestudy.api.DealResponse
import com.target.targetcasestudy.api.DealsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class DealListViewModel @Inject constructor(
    application: Application,
    private val dealsRepository: DealsRepository,
) : AndroidViewModel(application) {

    private val _dealListUiStateFlow = MutableStateFlow<List<Deal>>(emptyList())
    val dealListUiStateFlow = _dealListUiStateFlow.asStateFlow()

    private val _dealUiStateFlow = MutableStateFlow<Deal?>(null)
    val dealUiStateFlow = _dealUiStateFlow.asStateFlow()

    fun fetchDeals() {
        dealsRepository.retrieveDeals(object : Callback<DealResponse> {
            override fun onResponse(call: Call<DealResponse>, response: Response<DealResponse>) {
                if (response.isSuccessful) {
                    _dealListUiStateFlow.getAndUpdate {
                        (response.body()?.products ?: null)!!
                    }
                } else {
                    // Handle error
                }
            }

            override fun onFailure(call: Call<DealResponse>, t: Throwable) {
                // Handle failure
            }
        })
    }

    fun fetchDeal(dealId: String) {
        dealsRepository.retrieveDeal(dealId, object : Callback<Deal> {
            override fun onResponse(call: Call<Deal>, response: Response<Deal>) {
                if (response.isSuccessful) {
                    _dealUiStateFlow.getAndUpdate {
                        response.body()
                    }
                } else {
                    // Handle error
                }
            }

            override fun onFailure(call: Call<Deal>, t: Throwable) {
                // Handle failure
            }
        })
    }
}