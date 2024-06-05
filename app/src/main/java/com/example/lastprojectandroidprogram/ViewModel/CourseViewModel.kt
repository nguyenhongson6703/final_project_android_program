package com.example.lastprojectandroidprogram.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lastprojectandroidprogram.Response.CourseResponse
import com.example.lastprojectandroidprogram.Service.ServiceInstance
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class CourseViewModel : ViewModel() {
    val courses: MutableLiveData<List<CourseResponse>> = MutableLiveData()
    val error: MutableLiveData<String> = MutableLiveData()

    fun fetchCourses() {
        ServiceInstance.apiService.getAllCourses().enqueue(object : Callback<List<CourseResponse>> {
            override fun onResponse(call: Call<List<CourseResponse>>, response: Response<List<CourseResponse>>) {
                if (response.isSuccessful) {
                    courses.postValue(response.body())
                    Log.d("Data", courses.toString())
                } else {
                    error.postValue("Error fetching courses")
                    Log.d("Data", "Error")
                }
            }

            override fun onFailure(call: Call<List<CourseResponse>>, t: Throwable) {
                error.postValue(t.message)
                Log.d("Data", t.message.toString())
            }
        })
    }
}