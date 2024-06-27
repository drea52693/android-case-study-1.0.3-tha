package com.target.targetcasestudy.api

import com.google.gson.annotations.SerializedName

data class Deal(
  val id: Int = 0,
  val title: String = "defaultTitle",
  val description: String = "defaultDescription",
  @SerializedName("image_url") val imageUrl: String = "defaultImageUrl",
  @SerializedName("regular_price") val regularPrice: RegularPrice?,
  @SerializedName("sale_price") val salePrice: SalePrice?,
  val aisle: String = "defaultAisle",
  val fulfillment: String = "defaultFulfillment",
  val availability: String = "defaultAvailability",
)

data class SalePrice(
  @SerializedName("amount_in_cents")
  val amountInCents: Int = 0,
  @SerializedName("currency_symbol")
  val currencySymbol: String = "defaultCurrencySymbol",
  @SerializedName("display_string")
  val displayString: String = "defaultDisplayString",
)

data class RegularPrice(
  @SerializedName("amount_in_cents") val amountInCents: Int = 0,
  @SerializedName("currency_symbol") val currencySymbol: String = "defaultCurrencySymbol",
  @SerializedName("display_string") val displayString: String = "defaultDisplayString",
)
