package com.kerencev.companies.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler

abstract class BaseViewModel<T> : ViewModel() {

    abstract val liveData: LiveData<AppState<T>>
    protected val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        handleError(throwable)
    }

    abstract fun getData(id: String?)
    abstract fun handleError(error: Throwable)
}