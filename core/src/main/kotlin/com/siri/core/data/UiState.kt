package com.siri.core.data

/** 12/20/2022. */

sealed class UiState<out T : Any?> {

    object Loading : UiState<Nothing>()

    data class Success<out T : Any>(val data: T) : UiState<T>()

    data class Error(val errorMessage: String) : UiState<Nothing>()
}