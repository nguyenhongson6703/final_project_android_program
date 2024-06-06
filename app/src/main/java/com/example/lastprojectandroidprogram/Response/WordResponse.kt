package com.example.lastprojectandroidprogram.Response

data class WordResponse(
    val id: Int,
    val english: String,
    val vietnamese: String,
    val spell: String,
    val parts_of_speech: String,
    val mp3_url: String?,
    val image_url: String?,
    val course_id: Int,
    val example: String,
    val example_translate: String
)
