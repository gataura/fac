package com.hfad.fac.api.service

import android.content.Context
import com.hfad.fac.api.model.Login
import retrofit2.Call
import com.hfad.fac.api.model.User
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

public interface UserClient {
    @POST("login") fun login(@Body login: Login):Call<User>

    @GET("secretinfo") fun getSecret(@Header("Authorization") authToken: String):Call<ResponseBody>
}