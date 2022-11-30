package com.kerencev.companies.presentation.listcompanies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kerencev.companies.data.remote.dto.CompanyDto
import com.kerencev.companies.domain.Repository
import com.kerencev.companies.presentation.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListCompaniesViewModel(private val repository: Repository) :
    BaseViewModel<ListCompaniesState<CompanyDto>>() {

    override val liveData = MutableLiveData<ListCompaniesState<CompanyDto>>()

    init {
        getData()
    }

    override fun getData() {
        liveData.value = ListCompaniesState.Loading
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val data = repository.getAllCompanies()
            liveData.postValue(ListCompaniesState.Success(data))
        }
    }

    override fun handleError(error: Throwable) {
        liveData.postValue(ListCompaniesState.Error(error))
    }
}