package com.siri.core.data

/** 12/20/2022. */

sealed class ProductUiState<out T : Any?> {

    object Loading : ProductUiState<Nothing>()

    data class Success<out T : Any>(val data: T) : ProductUiState<T>()

    data class Error(val errorMessage: String) : ProductUiState<Nothing>()
}