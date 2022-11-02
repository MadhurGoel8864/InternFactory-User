package com.example.internfactory.Activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.example.internfactory.R

class main_screen : Activity() {
    lateinit var abc : ViewPager2
    lateinit var adapters: main_page_Carousel_adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

        var list = mutableListOf<Int>()

        list.add(R.drawable.img7)
        list.add(R.drawable.img10)
        list.add(R.drawable.img11)
        list.add(R.drawable.img12)



        adapters = main_page_Carousel_adapter(this)
        adapters.setContentList(list)

        abc = findViewById(R.id.carousel_pageview)
        abc.adapter = adapters




    }
}
