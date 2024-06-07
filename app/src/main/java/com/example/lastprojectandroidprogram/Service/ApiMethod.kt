package com.example.lastprojectandroidprogram.Service
import android.util.Log
import com.example.lastprojectandroidprogram.CallBackInterface.CourseCreateCallBack
import com.example.lastprojectandroidprogram.CallBackInterface.LoginCallback
import com.example.lastprojectandroidprogram.CallBackInterface.ParticipateCallBack
import com.example.lastprojectandroidprogram.CallBackInterface.RegisterCallback
import com.example.lastprojectandroidprogram.CallBackInterface.ScoreCallBack
import com.example.lastprojectandroidprogram.CallBackInterface.VocabularyCreateCallBack
import com.example.lastprojectandroidprogram.Request.RequestCreateCourse
import com.example.lastprojectandroidprogram.Request.RequestRegister
import com.example.lastprojectandroidprogram.Request.ScoreRequest
import com.example.lastprojectandroidprogram.Request.VocabularyCreateRequest
import com.example.lastprojectandroidprogram.Response.CourseCreateResponse
import com.example.lastprojectandroidprogram.Response.ResponseLogin
import com.example.lastprojectandroidprogram.Response.ResponseRegister
import com.example.lastprojectandroidprogram.Response.WordResponse
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

fun participateCourse(token: String, courseId: Int, callback: ParticipateCallBack) {
    val body = mapOf("course_id" to courseId)
    val call = ServiceInstance.apiService.participateCourse("Bearer $token", body)

    call.enqueue(object : Callback<Void> {
        override fun onResponse(call: Call<Void>, response: Response<Void>) {
            if (response.isSuccessful && response.code() == 201) {
                callback.onResult(true)
            } else {
                callback.onResult(false)
            }
        }

        override fun onFailure(call: Call<Void>, t: Throwable) {
            Log.d("ParticipateCourse", "Failed: ${t.message}")
            callback.onResult(false)
        }
    })
}
fun ScoreVocabulary(token: String, courseId: Int, vocabularyId: Int, callback: ScoreCallBack) {

    val body = ScoreRequest(courseId, vocabularyId)
    val call = ServiceInstance.apiService.scoreVocabulary("Bearer $token", body)

    call.enqueue(object : Callback<Void> {
        override fun onResponse(call: Call<Void>, response: Response<Void>) {
            if (response.isSuccessful && response.code() == 200) {
                callback.onResult(true)
            } else {
                callback.onResult(false)
            }
        }

        override fun onFailure(call: Call<Void>, t: Throwable) {
            Log.d("Score", "Failed: ${t.message}")
            callback.onResult(false)
        }
    })
}

fun createCourse(token: String, name: String, description: String, quantiryWords: Int, callback: CourseCreateCallBack) {
    val request = RequestCreateCourse(name, description, quantiryWords)
    val call = ServiceInstance.apiService.createCourse("Bearer $token", request)

    call.enqueue(object : Callback<CourseCreateResponse> {
        override fun onResponse(call: Call<CourseCreateResponse>, response: Response<CourseCreateResponse>) {
            if (response.isSuccessful) {
                response.body()?.let { coursecreateResponse ->
                    callback.onResult(coursecreateResponse)
                } ?: run {
                    callback.onResult(null)
                }
            } else {
                callback.onResult(null)
            }
        }

        override fun onFailure(call: Call<CourseCreateResponse>, t: Throwable) {
            Log.d("SignUp", "Failed: ${t.message}")
            callback.onResult(null)
        }
    })
}

fun createVocabulary(token: String,english: String, vietnamese: String, spell :String,
    partsOfSpeech: String , idCourse : Int , example: String, exampleTranslate: String, callback: VocabularyCreateCallBack) {
    val request = VocabularyCreateRequest(english, vietnamese,spell,partsOfSpeech, course_id = idCourse ,example,exampleTranslate)
    val call = ServiceInstance.apiService.createVocabulary("Bearer $token", request)

    call.enqueue(object : Callback<WordResponse> {
        override fun onResponse(call: Call<WordResponse>, response: Response<WordResponse>) {
            if (response.isSuccessful) {
                response.body()?.let { wordResponse ->
                    callback.onResult(wordResponse)
                } ?: run {
                    callback.onResult(null)
                }
            } else {
                callback.onResult(null)
            }
        }

        override fun onFailure(call: Call<WordResponse>, t: Throwable) {
            Log.d("SignUp", "Failed: ${t.message}")
            callback.onResult(null)
        }
    })
}


