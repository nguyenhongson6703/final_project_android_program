package com.example.lastprojectandroidprogram.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lastprojectandroidprogram.Response.WordResponse
import com.example.lastprojectandroidprogram.Service.ServiceInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OneWordViewModel  : ViewModel() {
    val word: MutableLiveData<WordResponse> = MutableLiveData()
    val error: MutableLiveData<String> = MutableLiveData()

    fun fetchWord(id: Int, token: String) {
        ServiceInstance.apiService.getOneVocabulary(id, "Bearer $token").enqueue(object :
            Callback<WordResponse> {
            override fun onResponse(call: Call<WordResponse>, response: Response<WordResponse>) {
                if (response.isSuccessful) {
                    word.postValue(response.body())
                    Log.d("Data", word.toString())
                } else {
                    error.postValue("Error fetching word")
                    Log.d("Data", "Error")
                }
            }

            override fun onFailure(call: Call<WordResponse>, t: Throwable) {
                error.postValue(t.message)
                Log.d("Data", t.message.toString())
            }
        })
    }
}