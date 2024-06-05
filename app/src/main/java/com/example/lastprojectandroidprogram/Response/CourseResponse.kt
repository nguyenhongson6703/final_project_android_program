package com.example.lastprojectandroidprogram.Response

import com.google.gson.annotations.SerializedName

data class CourseResponse(
    val id: Int,
    val name: String,
    @SerializedName("start_date") val startDate: String,
    @SerializedName("end_date") val endDate: String?,
    val description: String,
    @SerializedName("quantity_words") val quantityWords: Int,
    val username: String

)
