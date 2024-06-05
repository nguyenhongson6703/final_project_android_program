package com.example.lastprojectandroidprogram.CallBackInterface

import com.example.lastprojectandroidprogram.Response.ResponseLogin
import com.example.lastprojectandroidprogram.Response.ResponseRegister

interface LoginCallback {
    fun onResult(response: ResponseLogin?)
}