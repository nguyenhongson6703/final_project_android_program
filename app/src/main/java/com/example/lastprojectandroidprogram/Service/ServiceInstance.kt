package com.example.lastprojectandroidprogram.Service
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceInstance {

    private const val BASE_URL = "https://21d5-2402-800-629c-ce50-44bd-e74e-5bd6-3275.ngrok-free.app/"

    val instance: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: ApiService by lazy {
        instance.create(ApiService::class.java)
    }

}