package com.example.lastprojectandroidprogram.ViewModel


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lastprojectandroidprogram.Response.CourseParticipateResponse
import com.example.lastprojectandroidprogram.Service.ServiceInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CourseParticipateViewModel: ViewModel() {
    val courseParticipates: MutableLiveData<List<CourseParticipateResponse>> = MutableLiveData()
    val error: MutableLiveData<String> = MutableLiveData()

    fun fetchCourseParticipates(token: String) {
        ServiceInstance.apiService.getCourseParticipate("Bearer $token").enqueue(object : Callback<List<CourseParticipateResponse>> {
            override fun onResponse(call: Call<List<CourseParticipateResponse>>, response: Response<List<CourseParticipateResponse>>) {
                if (response.isSuccessful) {
                    courseParticipates.postValue(response.body())
                    Log.d("Data", courseParticipates.toString())
                } else {
                    error.postValue("Error fetching course participations")
                    Log.d("Data", "Error")
                }
            }

            override fun onFailure(call: Call<List<CourseParticipateResponse>>, t: Throwable) {
                error.postValue(t.message)
                Log.d("Data", t.message.toString())
            }
        })
    }
}