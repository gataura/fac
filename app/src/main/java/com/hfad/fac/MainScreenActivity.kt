package com.hfad.fac

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainScreenActivity : AppCompatActivity() {

    var myUrl:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)


        if (myUrl == null) {
            finish()
        } else {

        }
    }

    companion object {
        fun start(context: Context, accessToken: String) {
            val intent = Intent(context, MainScreenActivity::class.java)
            context.startActivity(intent)
        }
    }

}
