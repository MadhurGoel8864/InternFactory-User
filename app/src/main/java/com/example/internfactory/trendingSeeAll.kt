package com.example.internfactory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.internfactory.Activities.Adapters.Trending_adapter
import com.example.internfactory.Activities.Adapters.Trending_dataclass

// TODO: Rename parameter arguments, choose names that match
class trendingSeeAll : Fragment() {

    private lateinit var newRecyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<Trending_dataclass>
    lateinit var imageId: Array<Int>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_trending_see_all, container, false)
        imageId = arrayOf(R.drawable.img6,R.drawable.img6,R.drawable.img6,R.drawable.img6,R.drawable.img6,R.drawable.img6,R.drawable.img6,R.drawable.img6,R.drawable.img6,R.drawable.img6,R.drawable.img6,R.drawable.img6,R.drawable.img6,R.drawable.img6,R.drawable.img6,R.drawable.img6)

        newRecyclerView = view.findViewById(R.id.recyclerview)

        newRecyclerView.layoutManager = LinearLayoutManager(view.context)
        newRecyclerView.setHasFixedSize(true)

        newArrayList = arrayListOf<Trending_dataclass>()
        getUserdata()
        return view
    }

    private fun getUserdata() {
        for(i in imageId.indices){
            val imageat = Trending_dataclass(imageId[i])
            newArrayList.add(imageat)
        }
        newRecyclerView.adapter = Trending_adapter(newArrayList)

    }
}