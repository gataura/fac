package com.hfad.fac

import android.content.Context
import android.content.Intent
import android.content.Intent.getIntent
import android.net.Uri
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.content.ContextCompat
import android.support.v4.content.ContextCompat.startActivity
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
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

@Suppress("OverridingDeprecatedMember")
class MainActivity : AppCompatActivity() {


    lateinit var webView: WebView
    var clientId = "34f18331adf3a2ecc7b3"
    var clientSecret = "9299ce3bfd5e19e1b894871d0680ba38a94a0483"
    var redirectUri = "fac://callback"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView = find(R.id.webView)
        webView.settings.javaScriptEnabled = true
        webView.loadUrl("https://github.com/login/oauth/authorize?client_id=$clientId&scope=repo&redirect_uri=$redirectUri")
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String): Boolean {
                OAuth(url)
                return true
            }

            @RequiresApi(Build.VERSION_CODES.N)
            override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
                var urla: String = request.url.toString()
                OAuth(urla)
                return true
            }

        }


        /*override fun onResume() {
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

    }*/
    }
    fun OAuth(url:String) {

        if (url.startsWith(redirectUri)) {
            var code = url.split("code=")[1]


            var builder = Retrofit.Builder().baseUrl("https://github.com/").addConverterFactory(GsonConverterFactory.create())

            var retrofit: Retrofit = builder.build()

            var client = retrofit.create(GitHubClient::class.java)
            var accessTokenCall = client.getAccessToken(
                    clientId,
                    clientSecret,
                    code
            )

            accessTokenCall.enqueue(object : Callback<AccessToken> {
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
