package com.kerencev.companies.data.remote

import com.kerencev.companies.data.remote.dto.CompanyDetailsDto
import com.kerencev.companies.data.remote.dto.CompanyDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("test.php")
    suspend fun getAllCompanies(): List<CompanyDto>

    @GET("test.php")
    suspend fun getCompanyById(@Query("id") id: String): List<CompanyDetailsDto>
}