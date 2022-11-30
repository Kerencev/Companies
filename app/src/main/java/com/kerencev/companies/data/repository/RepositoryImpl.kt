package com.kerencev.companies.data.repository

import com.kerencev.companies.data.remote.ApiService
import com.kerencev.companies.data.remote.dto.CompanyDto
import com.kerencev.companies.domain.Repository

class RepositoryImpl(private val apiService: ApiService) : Repository {

    override suspend fun getAllCompanies(): List<CompanyDto> {
        return apiService.getAllCompanies()
    }
}