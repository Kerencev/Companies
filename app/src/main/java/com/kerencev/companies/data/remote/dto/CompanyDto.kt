package com.kerencev.companies.data.remote.dto

import com.google.gson.annotations.SerializedName


data class CompanyDto(
    @field:SerializedName("id") val id: String,
    @field:SerializedName("img") val img: String,
    @field:SerializedName("name") val name: String
)