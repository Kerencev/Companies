package com.kerencev.companies.presentation.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kerencev.companies.data.remote.dto.CompanyDetailsDto
import com.kerencev.companies.domain.Repository
import com.kerencev.companies.presentation.base.AppState
import com.kerencev.companies.presentation.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsViewModel(private val repository: Repository) : BaseViewModel<CompanyDetailsDto>() {

    override val liveData = MutableLiveData<AppState<CompanyDetailsDto>>()

    override fun getData(id: String?) {
        if (id.isNullOrEmpty()) return
        liveData.value = AppState.Loading
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val data = repository.getCompanyById(id)
            liveData.postValue(AppState.Success(data))
        }
    }

    override fun handleError(error: Throwable) {
        liveData.postValue(AppState.Error(error))
    }
}