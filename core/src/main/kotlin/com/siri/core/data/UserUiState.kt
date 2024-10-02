package com.siri.core.data

sealed class UserUiState<out T : Any?> {

    object Loading : UserUiState<Nothing>()

    data class Success<out T : Any>(val data: T) : UserUiState<T>()

    data class Error(val errorMessage: String) : UserUiState<Nothing>()
}