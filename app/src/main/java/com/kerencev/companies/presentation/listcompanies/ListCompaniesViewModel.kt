package com.kerencev.companies.presentation.listcompanies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kerencev.companies.data.remote.dto.CompanyDto
import com.kerencev.companies.domain.Repository
import com.kerencev.companies.presentation.base.AppState
import com.kerencev.companies.presentation.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListCompaniesViewModel(private val repository: Repository) :
    BaseViewModel<CompanyDto>() {

    override val liveData = MutableLiveData<AppState<CompanyDto>>()

    init {
        getData(null)
    }

    override fun getData(id: String?) {
        liveData.value = AppState.Loading
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val data = repository.getAllCompanies()
            liveData.postValue(AppState.Success(data))
        }
    }

    override fun handleError(error: Throwable) {
        liveData.postValue(AppState.Error(error))
    }
}