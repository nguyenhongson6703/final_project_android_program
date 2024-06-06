package com.example.lastprojectandroidprogram.Service

import retrofit2.Call
import com.example.lastprojectandroidprogram.Request.RequestRegister
import com.example.lastprojectandroidprogram.Response.CourseParticipateResponse
import com.example.lastprojectandroidprogram.Response.CourseResponse
import com.example.lastprojectandroidprogram.Response.ResponseLogin
import com.example.lastprojectandroidprogram.Response.ResponseRegister
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

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

}