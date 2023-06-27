package com.example.openpayprueba.utils

import androidx.lifecycle.ViewModel

internal fun<E: Exception, T> ViewModel.emitUiState(showProgress: Boolean = false,
    showServerError: Event<E>? = null,
    showClientError: Event<E>? = null,
    showSuccess: Event<T>? = null
): UIModel<E, T> {
    return UIModel(showProgress, showServerError, showClientError, showSuccess)
}