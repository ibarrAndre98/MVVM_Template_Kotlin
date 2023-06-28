package com.example.openpayprueba.core.core.network.error

import android.net.ParseException
import android.text.TextUtils
import android.util.Log
import androidx.annotation.VisibleForTesting
import com.google.gson.GsonBuilder
import okhttp3.ResponseBody
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.net.UnknownHostException
import java.nio.charset.StandardCharsets

class StampsErrorAdapter : HttpErrorAdapter<StampsNetworkException> {

    private val TAG = StampsErrorAdapter::class.java.simpleName

    companion object {
        const val ERROR_CLIENT_OTHERS = "error_client_others"
        const val ERROR_NETWORK_SERVER_ERROR_OTHER = "error_network_server_error_other"
        const val ERROR_CLIENT_NO_INTERNET = "error_client_no_internet"
        const val ERROR_CLIENT_NO_CONNECTION = "error_client_no_connection"
    }

    @VisibleForTesting
    fun getStatusError(responseBody: ResponseBody?): StampsErrorMessage? {
        var statusError: StampsErrorMessage? = null
        if (responseBody != null) {
            try {
                val source = responseBody.source()
                if (source != null) {
                    source.request(java.lang.Long.MAX_VALUE) // Buffer the entire body.
                    var charset = StandardCharsets.UTF_8
                    val contentType = responseBody.contentType()
                    if (contentType != null) {
                        charset = contentType.charset(StandardCharsets.UTF_8)
                    }
                    val string = source.buffer().clone().readString(charset)

                    if (!TextUtils.isEmpty(string)) {
                        val gsonBuilder = GsonBuilder()
                        val gson = gsonBuilder.create()

                        val responseBodyJson = JSONObject(string)
                        var dataObject: JSONObject?

                        try {
                            dataObject = responseBodyJson.getJSONObject("data")
                        } catch (jsonException: JSONException) {
                            dataObject = null
                        }

                        statusError = if (dataObject != null) {
                            val errorMessage = gson.fromJson(string, ResponseErrorMessage::class.java)
                            errorMessage.data
                        } else {
                            gson.fromJson(string, StampsErrorMessage::class.java)
                        }
                    }
                }
            } catch (e: Exception) {
                Log.w(TAG, "Impossible to get StampsErrorMessage", e)
            }
        }

        if (statusError != null) {
            statusError.errorType = ErrorType.SERVER
        }

        return statusError
    }

    override fun mapServerError(errorBody: ResponseBody?): StampsNetworkException? {
        return StampsNetworkException(getStatusError(errorBody))
    }

    //todo check internet
    override fun mapClientError(exception: Exception): StampsNetworkException {
        Log.w(TAG, exception.message!!)
        when (exception) {
            is UnknownHostException -> {
                //todo check internet
                val errorMessageNoConnection = StampsErrorMessage(
                    ERROR_CLIENT_NO_CONNECTION,
                    ERROR_CLIENT_NO_CONNECTION,
                    null,
                    ErrorType.CONNECTION)
                return StampsNetworkException(errorMessageNoConnection)
            }
            is java.net.SocketTimeoutException -> {
                val errorMessageNoConnection = StampsErrorMessage(
                    ERROR_CLIENT_NO_CONNECTION,
                    ERROR_CLIENT_NO_CONNECTION,
                    null,
                    ErrorType.CONNECTION)

                return StampsNetworkException(errorMessageNoConnection)
            }
            is ParseException -> {
                // error on parsing, classify as server error
                val errorMessageNoConnection = StampsErrorMessage(
                    ERROR_NETWORK_SERVER_ERROR_OTHER,
                    exception.message ?: ERROR_CLIENT_NO_CONNECTION,
                    null,
                    ErrorType.GENERIC)

                return StampsNetworkException(errorMessageNoConnection)
            }
            else -> {
                // the short error show the general server error, details on the full message
                val otherErrorMessage = StampsErrorMessage(
                    ERROR_CLIENT_OTHERS,
                    ERROR_CLIENT_OTHERS,
                    null,
                    ErrorType.GENERIC)

                return StampsNetworkException(otherErrorMessage)
            }
        }
    }
}


interface HttpErrorAdapter<E : Throwable> {
    fun mapClientError(exception: java.lang.Exception): E
    @Throws(IOException::class)
    fun mapServerError(errorBody: ResponseBody?): StampsNetworkException?
}
