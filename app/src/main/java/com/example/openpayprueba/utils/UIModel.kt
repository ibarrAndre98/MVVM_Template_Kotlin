package com.example.openpayprueba.utils

data class UIModel<E: Exception, T>(
    val showProgress: Boolean,
    val showServerError: Event<E>? = null,
    val showClientError: Event<E>? =null,
    val showSuccess: Event<T>?
)