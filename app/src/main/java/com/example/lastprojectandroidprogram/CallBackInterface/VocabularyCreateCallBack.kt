package com.example.lastprojectandroidprogram.CallBackInterface

import com.example.lastprojectandroidprogram.Response.ResponseLogin
import com.example.lastprojectandroidprogram.Response.WordResponse

interface VocabularyCreateCallBack {
    fun onResult(response: WordResponse?)
}