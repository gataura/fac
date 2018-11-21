package com.hfad.fac

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.widget.Toast
import com.hfad.fac.api.model.AccessToken
import com.hfad.fac.api.model.GitHubRepo
import com.hfad.fac.api.model.Login
import com.hfad.fac.api.model.User
import com.hfad.fac.api.service.GitHubClient
import com.hfad.fac.api.service.UserClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {



    var clientId = "34f18331adf3a2ecc7b3"
    var clientSecret = "9299ce3bfd5e19e1b894871d0680ba38a94a0483"
    var redirectUri = "fac://callback"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val intent = Intent (Intent.ACTION_VIEW, Uri.parse("https://github.com/login/oauth/authorize?client_id=$clientId&scope=repo&redirect_uri=$redirectUri"))
        startActivity(intent)

    }


    override fun onResume() {
        super.onResume()
        var uri = getIntent().getData()

        if (uri != null && uri.toString().startsWith(redirectUri)) {
            var code = uri.getQueryParameter("code")


            var builder = Retrofit.Builder().baseUrl("https://github.com/").addConverterFactory(GsonConverterFactory.create())

            var retrofit:Retrofit = builder.build()

            var client = retrofit.create(GitHubClient::class.java)
            var accessTokenCall = client.getAccessToken(
                    clientId,
                    clientSecret,
                    code
            )

            accessTokenCall.enqueue(object: Callback<AccessToken> {
                override fun onResponse(call: Call<AccessToken>?, response: Response<AccessToken>?) {
                    Toast.makeText(this@MainActivity, "yay!", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<AccessToken>?, t: Throwable?) {
                    Toast.makeText(this@MainActivity, "no!", Toast.LENGTH_SHORT).show()
                }

            })
        }

    }
}
