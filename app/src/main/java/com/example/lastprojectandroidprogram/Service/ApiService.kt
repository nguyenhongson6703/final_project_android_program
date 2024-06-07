package com.example.lastprojectandroidprogram.Service

import com.example.lastprojectandroidprogram.Request.RequestCreateCourse
import retrofit2.Call
import com.example.lastprojectandroidprogram.Request.RequestRegister
import com.example.lastprojectandroidprogram.Request.ScoreRequest
import com.example.lastprojectandroidprogram.Request.VocabularyCreateRequest
import com.example.lastprojectandroidprogram.Response.CourseCreateResponse
import com.example.lastprojectandroidprogram.Response.CourseParticipateResponse
import com.example.lastprojectandroidprogram.Response.CourseResponse
import com.example.lastprojectandroidprogram.Response.ResponseLogin
import com.example.lastprojectandroidprogram.Response.ResponseRegister
import com.example.lastprojectandroidprogram.Response.WordResponse
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    @POST("users/register")
    fun registerUser(@Body request: RequestRegister): Call<ResponseRegister>


    @FormUrlEncoded
    @POST("login")
    fun loginUser(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<ResponseLogin>

    @GET("courses/all")
    fun getAllCourses(): Call<List<CourseResponse>>

    @GET("courses/get_course_participate")
    fun getCourseParticipate(@Header("Authorization") token: String): Call<List<CourseParticipateResponse>>

    @POST("courses/participate")
    fun participateCourse(
        @Header("Authorization") token: String,
        @Body body: Map<String, Int>
    ): Call<Void>
    @PUT("vocabulary/score")
    fun scoreVocabulary(
        @Header("Authorization") token: String,
        @Body body: ScoreRequest
    ): Call<Void>

    @GET("vocabulary/new_word/{id}")
    fun getNewWord(
        @Path("id") id: Int,
        @Header("Authorization") token: String
    ): Call<WordResponse>

    @GET("vocabulary/{id}")
    fun getOneVocabulary(
        @Path("id") id: Int,
        @Header("Authorization") token: String
    ): Call<WordResponse>

    @GET("vocabulary/review/{id}")
    fun getVocabularyReview(
        @Path("id") id: Int,
        @Header("Authorization") token: String
    ): Call<List<WordResponse>>

    @POST("courses/createcourse")
    fun createCourse(
        @Header("Authorization") token: String,
        @Body body: RequestCreateCourse
    ): Call<CourseCreateResponse>

    @POST("vocabulary/create")
    fun createVocabulary(
        @Header("Authorization") token: String,
        @Body body: VocabularyCreateRequest
    ): Call<WordResponse>
}