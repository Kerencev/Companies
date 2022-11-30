package com.kerencev.companies.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CompanyDetailsDto(
    @field:SerializedName("id") val id: String,
    @field:SerializedName("description") val description: String,
    @field:SerializedName("img") val img: String,
    @field:SerializedName("lat") val lat: Double,
    @field:SerializedName("lon") val lon: Double,
    @field:SerializedName("name") val name: String,
    @field:SerializedName("phone") val phone: String,
    @field:SerializedName("www") val www: String
)