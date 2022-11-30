package com.kerencev.companies.presentation.base

sealed class AppState<out T> {
    data class Success<T>(val data: List<T>) : AppState<T>()
    object Loading : AppState<Nothing>()
    data class Error(val throwable: Throwable) : AppState<Nothing>()
}
