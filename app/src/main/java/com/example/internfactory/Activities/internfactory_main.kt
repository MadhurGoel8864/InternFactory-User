package com.example.internfactory

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import com.example.internfactory.Activities.Home_page
import com.example.internfactory.modules.User
import com.example.internfactory.server.RetrofitApi
import com.example.internfactory.server.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)
        Handler().postDelayed({
            val Intent = Intent(this@MainActivity, Home_page::class.java)
            startActivity(Intent)
            finish()
        }, 3000)
    }
}