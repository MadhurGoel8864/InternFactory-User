package com.example.internfactory.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.internfactory.R

class Trending_SeeAll : AppCompatActivity() {


    private lateinit var newRecyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<Trending_dataclass>
    lateinit var imageId: Array<Int>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trending_see_all)

        imageId = arrayOf(R.drawable.img6,R.drawable.img7,R.drawable.img8,R.drawable.img9,R.drawable.img10)

        newRecyclerView = findViewById(R.id.recyclerview)

        newRecyclerView.layoutManager = LinearLayoutManager(this)
        newRecyclerView.setHasFixedSize(true)

        newArrayList = arrayListOf<Trending_dataclass>()
        getUserdata()
    }

    private fun getUserdata() {
        for(i in imageId.indices){
            val imageat = Trending_dataclass(imageId[i])
            newArrayList.add(imageat)
        }
        newRecyclerView.adapter = Trending_adapter(newArrayList)

    }
}