package com.example.lastprojectandroidprogram.Request

import com.google.gson.annotations.SerializedName

data class VocabularyCreateRequest(
    @SerializedName("english")
    val english: String,
    @SerializedName("vietnamese")
    val vietnamese: String,
    @SerializedName("spell")
    val spell: String,
    @SerializedName("parts_of_speech")
    val parts_of_speech: String,
    @SerializedName("course_id")
    val course_id: Int,
    @SerializedName("example")
    val example: String,
    @SerializedName("example_translate")
    val example_translate: String


)
