package com.example.lastprojectandroidprogram.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lastprojectandroidprogram.Response.WordResponse
import com.example.lastprojectandroidprogram.Service.ServiceInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WordReviewViewModel  : ViewModel() {
    val words: MutableLiveData<List<WordResponse>> = MutableLiveData()
    val error: MutableLiveData<String> = MutableLiveData()

    fun fetchWords(id: Int, token: String) {
        ServiceInstance.apiService.getVocabularyReview(id, "Bearer $token").enqueue(object : Callback<List<WordResponse>> {
            override fun onResponse(call: Call<List<WordResponse>>, response: Response<List<WordResponse>>) {
                if (response.isSuccessful) {
                    words.postValue(response.body())
                } else {
                    error.postValue("Error fetching words")
                }
            }

            override fun onFailure(call: Call<List<WordResponse>>, t: Throwable) {
                error.postValue(t.message)
            }
        })
    }
}