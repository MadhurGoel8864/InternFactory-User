package com.example.internfactory

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.example.internfactory.Activities.Home_page
import com.example.internfactory.Activities.main_screen

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)
        Handler().postDelayed({
            val Intent = Intent(this@MainActivity, main_screen::class.java)
            startActivity(Intent)
            finish()
        }, 3000)
    }
}

