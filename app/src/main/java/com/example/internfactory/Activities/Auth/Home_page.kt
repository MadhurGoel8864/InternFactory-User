package com.example.internfactory.Activities.Auth

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.internfactory.Activities.connecting
import com.example.internfactory.R

class Home_page : Activity() {

    fun connect(view : View){
        val intent=Intent(this, connecting::class.java)
        startActivity(intent)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
    }
}