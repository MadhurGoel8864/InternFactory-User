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

        val user = User("User", "Five", "fgltzul@gmail.com", "user")

        val retrofitAPI = ServiceBuilder.buildService(RetrofitApi::class.java)
        val call = retrofitAPI.sendUserData(user)


        val x = call.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.body() != null) {
                    Toast.makeText(applicationContext, "valid Email or Password", Toast.LENGTH_LONG)
                        .show()
                    Log.i("Naman", response.body().toString())
                } else {
                    Toast.makeText(applicationContext, "Hello " + response.body()?.firstName.toString() + "!", Toast.LENGTH_LONG).show()
                    Log.i("Naman", "Fail")
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(applicationContext, "Please check your internet connection", Toast.LENGTH_LONG).show()
                Log.i("Naman", "Fail")
            }
        })
    }
}