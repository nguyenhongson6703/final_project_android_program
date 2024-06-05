package com.example.lastprojectandroidprogram.Service
import android.util.Log
import com.example.lastprojectandroidprogram.CallBackInterface.LoginCallback
import com.example.lastprojectandroidprogram.CallBackInterface.RegisterCallback
import com.example.lastprojectandroidprogram.Request.RequestRegister
import com.example.lastprojectandroidprogram.Response.ResponseLogin
import com.example.lastprojectandroidprogram.Response.ResponseRegister
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun registerUser(username: String, password: String, email: String, phone: String, callback: RegisterCallback) {
    val request = RequestRegister(username, password, email, phone)
    val call = ServiceInstance.apiService.registerUser(request)

    call.enqueue(object : Callback<ResponseRegister> {
        override fun onResponse(call: Call<ResponseRegister>, response: Response<ResponseRegister>) {
            if (response.isSuccessful) {
                response.body()?.let { registerResponse ->
                    callback.onResult(registerResponse)
                } ?: run {
                    callback.onResult(null)
                }
            } else {
                callback.onResult(null)
            }
        }

        override fun onFailure(call: Call<ResponseRegister>, t: Throwable) {
            Log.d("SignUp", "Failed: ${t.message}")
            callback.onResult(null)
        }
    })
}

fun login(username: String, password: String ,callback: LoginCallback) {

    val call = ServiceInstance.apiService.loginUser(username, password)

    call.enqueue(object : Callback<ResponseLogin> {
        override fun onResponse(call: Call<ResponseLogin>, response: Response<ResponseLogin>) {
            if (response.isSuccessful) {
                response.body()?.let { loginResponse ->
                    callback.onResult(loginResponse)
                } ?: run {
                    callback.onResult(null)
                }
            } else {
                callback.onResult(null)
            }
        }

        override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
            Log.d("Login", "Failed: ${t.message}")
            callback.onResult(null)
        }
    })
}


