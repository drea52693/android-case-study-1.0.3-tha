package com.target.targetcasestudy.api

import retrofit2.Callback
import javax.inject.Inject

class DealsRepository @Inject constructor(
    private val dealApi: DealApi
) {
    fun retrieveDeals(callback: Callback<DealResponse>) {
        dealApi.retrieveDeals().enqueue(callback)
    }

    fun retrieveDeal(dealId: String, callback: Callback<Deal>) {
        dealApi.retrieveDeal(dealId).enqueue(callback)
    }
}