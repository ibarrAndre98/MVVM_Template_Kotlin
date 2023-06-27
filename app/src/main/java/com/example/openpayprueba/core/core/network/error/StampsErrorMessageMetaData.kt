package com.example.openpayprueba.core.core.network.error

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class StampsErrorMessageMetaData(@SerializedName("balance") val balance: Int,
                                      @SerializedName("requiredBalance") val requiredBalance: Int,
                                      @SerializedName("maxGiftableStamps") val maxGiftableStamps: Int,
                                      @SerializedName("newLoyaltyId") val newLoyaltyId: String?,
                                      @SerializedName("updateMessage") var updateMessage: String? = null,
                                      @SerializedName("update_url") var updateUrl: String? = null,
                                      @SerializedName("cardNumber") val cardNumber: String?)
