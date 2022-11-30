package com.kerencev.companies.domain

import com.kerencev.companies.data.remote.dto.CompanyDetailsDto
import com.kerencev.companies.data.remote.dto.CompanyDto

interface Repository {
    suspend fun getAllCompanies(): List<CompanyDto>
    suspend fun getCompanyById(id: String): List<CompanyDetailsDto>
}