package com.target.targetcasestudy.api

import retrofit2.http.GET
import retrofit2.http.Path

interface DealApiKtx {

  @GET("deals")
  suspend fun retrieveDeals(): DealResponse

  @GET("deals/{dealId}")
  suspend fun retrieveDeal(@Path("dealId") dealId: String): Deal
}
