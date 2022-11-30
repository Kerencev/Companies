package com.kerencev.companies.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler

abstract class BaseViewModel<T> : ViewModel() {

    abstract val liveData: LiveData<T>
    protected val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        handleError(throwable)
    }

    abstract fun getData()
    abstract fun handleError(error: Throwable)
}