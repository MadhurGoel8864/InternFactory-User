package com.example.internfactory

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.example.internfactory.Activities.Auth.Home_page

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)
        Handler().postDelayed({
            val Intent = Intent(this@MainActivity, activity_Dashboard::class.java)
            startActivity(Intent)
            finish()
        }, 3000)
    }
}

