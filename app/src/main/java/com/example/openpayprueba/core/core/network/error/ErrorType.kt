package com.example.openpayprueba.core.core.network.error

import com.google.gson.annotations.SerializedName

enum class ErrorType {
    @SerializedName("error_type_generic")
    GENERIC,
    @SerializedName("error_type_server")
    SERVER,
    @SerializedName("error_type_connection")
    CONNECTION
}