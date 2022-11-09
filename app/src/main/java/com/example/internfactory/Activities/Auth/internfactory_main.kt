package com.example.internfactory

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.example.internfactory.Activities.Auth.Home_page
import com.example.internfactory.Activities.Internship_details_activity
import com.example.internfactory.Activities.internship_deatils_fragement
import com.example.internfactory.modules.UserDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        var intent = Intent(this@MainActivity, Home_page::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            val userDetails=UserDetails(this@MainActivity)
            userDetails.getToken().collect{
                if(it.logInState){
                    intent=Intent(this@MainActivity,activity_Dashboard::class.java)
                }
            }
        }
        Handler().postDelayed({
            startActivity(intent)
            finish()
        }, 3000)
    }
}
