package com.kerencev.companies.data.remote

import com.kerencev.companies.data.remote.dto.CompanyDto
import retrofit2.http.GET

interface ApiService {

    @GET("test.php")
    suspend fun getAllCompanies(): List<CompanyDto>
}