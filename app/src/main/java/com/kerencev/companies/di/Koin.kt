package com.kerencev.companies.di

import com.kerencev.companies.BuildConfig
import com.kerencev.companies.data.remote.ApiService
import com.kerencev.companies.data.remote.dto.CompanyDetailsDto
import com.kerencev.companies.data.remote.dto.CompanyDto
import com.kerencev.companies.data.repository.RepositoryImpl
import com.kerencev.companies.domain.Repository
import com.kerencev.companies.presentation.base.BaseViewModel
import com.kerencev.companies.presentation.details.DetailsViewModel
import com.kerencev.companies.presentation.listcompanies.ListCompaniesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    single<ApiService> {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(ApiService::class.java)
    }
    single<Repository> { RepositoryImpl(apiService = get()) }
}

val listOfCompaniesScreen = module {
    viewModel<BaseViewModel<CompanyDto>>(named(COMPANIES_VIEW_MODEL)) {
        ListCompaniesViewModel(repository = get())
    }
}

val detailsScreen = module {
    viewModel<BaseViewModel<CompanyDetailsDto>>(named(DETAILS_VIEW_MODEL)) {
        DetailsViewModel(repository = get())
    }
}

const val COMPANIES_VIEW_MODEL = "COMPANIES_VIEW_MODEL"
const val DETAILS_VIEW_MODEL = "DETAILS_VIEW_MODEL"