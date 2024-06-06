package com.example.lastprojectandroidprogram.Response

import com.google.gson.annotations.SerializedName

data class CourseParticipateResponse(
    @SerializedName("percent_completed") val percentCompleted: Double,
    @SerializedName("leared_word") val learnedWord: Int,

    @SerializedName("course_id") val courseId: Int,
    val name: String,
    @SerializedName("start_date") val startDate: String,
    @SerializedName("quantity_words") val quantityWords: Int
)
