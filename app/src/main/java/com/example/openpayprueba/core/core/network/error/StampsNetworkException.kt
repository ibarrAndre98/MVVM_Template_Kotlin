package com.example.openpayprueba.core.core.network.error

import android.util.Log

class StampsNetworkException: Exception {

    private var errorMessage: StampsErrorMessage
    private val TAG = StampsNetworkException::class.java.name

    constructor(error: StampsErrorMessage?){
        errorMessage = error ?: StampsErrorMessage("error",
        "null error message",
        null,
        ErrorType.SERVER)
    }

    constructor(exception: StampsNetworkException?){
        if(exception is StampsNetworkException){
            this.errorMessage = exception.errorMessage
        } else {
            Log.e(TAG, "Non expected exception: " + exception.toString())
            this.errorMessage = StampsErrorMessage("error",
                "Non expected exception: " + exception.toString(),
                null,
                ErrorType.GENERIC)
        }
    }

    fun getCode(): String? {
        return errorMessage.code
    }

    fun getMetaData(): StampsErrorMessageMetaData? {
        return errorMessage.metadata
    }

    fun getType(): ErrorType {
        return errorMessage.errorType
    }

    override fun getLocalizedMessage(): String? {
        return errorMessage.message
    }

    override fun toString(): String {
        return "StampsNetworkException" +
                "{" +
                "type= ${errorMessage.errorType}" +
                "code= ${errorMessage.code}" +
                "message ${errorMessage.message}" +
                "}"

    }

}