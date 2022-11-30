package com.kerencev.companies.presentation.listcompanies

sealed class ListCompaniesState<out T> {
    data class Success<T>(val data: List<T>) : ListCompaniesState<T>()
    object Loading : ListCompaniesState<Nothing>()
    data class Error(val throwable: Throwable) : ListCompaniesState<Nothing>()
}
