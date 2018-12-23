package com.hfad.fac.api.service

import com.hfad.fac.api.model.AccessToken
import com.hfad.fac.api.model.GitHubRepo
import retrofit2.Call
import retrofit2.http.*

public interface GitHubClient {

    @Headers("Accept: application/json")
    @POST("login/oauth/access_token")
    @FormUrlEncoded
    fun getAccessToken(
            @Field("client_id") clientId:String,
            @Field("client_secret") clientSecret:String,
            @Field("code") code:String
    ): Call<AccessToken>

    @GET("/users/repos?&access_token={access_token}")
    fun reposForToken(@Path("access_token") access_token:String): Call<List<GitHubRepo>>
}