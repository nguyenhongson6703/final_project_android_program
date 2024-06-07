package com.example.lastprojectandroidprogram.Response

import com.google.gson.annotations.SerializedName

data class CourseCreateResponse(
    val id: Int,
    val name: String,
    @SerializedName("start_date")
    val start_date: String,
    @SerializedName("end_date")
    val end_date : String?,
    @SerializedName("description")
    val description: String ,
    @SerializedName("quantity_words")
    val quantity_words: Int

)
