package com.kerencev.companies.domain

import com.kerencev.companies.data.remote.dto.CompanyDto

interface Repository {
    suspend fun getAllCompanies(): List<CompanyDto>
}