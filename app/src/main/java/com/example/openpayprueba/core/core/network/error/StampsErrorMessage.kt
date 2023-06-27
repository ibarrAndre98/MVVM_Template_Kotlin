package com.example.openpayprueba.core.core.network.error

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class StampsErrorMessage (@SerializedName("code") val code: String? = null,
                               @SerializedName("message") val message: String? = null,
                               @SerializedName("metadata") val metadata: StampsErrorMessageMetaData? = null,
                               @SerializedName("error_type") var errorType: ErrorType = ErrorType.SERVER,
                               @SerializedName("detail") val detail: String? = null)

@Keep
data class ResponseErrorMessage(
    val status: String,
    val data: StampsErrorMessage
)