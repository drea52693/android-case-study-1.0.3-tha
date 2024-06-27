package com.target.targetcasestudy.api

import javax.inject.Inject

class DealsRepository @Inject constructor(
    private val dealApiKtx: DealApiKtx
) {
    suspend fun retrieveDeals(): DealResponse {
        return dealApiKtx.retrieveDeals()
    }

    suspend fun retrieveDeal(dealId: String) : Deal {
        return dealApiKtx.retrieveDeal(dealId)
    }
}