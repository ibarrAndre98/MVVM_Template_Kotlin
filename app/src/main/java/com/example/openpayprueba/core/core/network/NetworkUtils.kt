package com.example.openpayprueba.core.core.network

import android.util.Log
import com.example.openpayprueba.core.core.network.error.StampsErrorAdapter
import com.example.openpayprueba.core.core.network.error.StampsNetworkException
import kotlin.Exception

suspend fun <T: Any> safeApiCall(call: suspend () -> Result<T>, errorMessage: String): Result<T> {
    return try {
        call()
    } catch (e: Exception) {
        // An exception was thrown when calling the API so we're converting this to an IOException
        Log.d("Untracked error", e.toString())

        Result.Error(
            StampsNetworkException(
                StampsErrorAdapter()
            .mapClientError(e))
        )
    }
}