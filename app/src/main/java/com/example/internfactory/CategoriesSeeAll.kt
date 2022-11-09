package com.example.internfactory

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.internfactory.Activities.Adapters.category_ViewHolder
import com.example.internfactory.Activities.Adapters.category_adapter
import com.example.internfactory.Activities.All_internship_list_fragment
import com.example.internfactory.modules.UserDetails.Companion.token
import com.example.internfactory.modules.category_seeall_request
import com.example.internfactory.modules.category_seeall_response
import com.example.internfactory.server.RetrofitApi
import com.example.internfactory.server.ServiceBuilder
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class CategoriesSeeAll : Fragment() {

    private lateinit var categadap: category_adapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_categories_see_all, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.myrecycleView)

        val serviceBuilder = ServiceBuilder.buildService(RetrofitApi::class.java)
        val Call = serviceBuilder.getcategories("Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZWFtaW50ZXJuZmFjdG9yeUBnbWFpbC5jb20iLCJleHAiOjE2NjgwODk3MjYsImlhdCI6MTY2ODAwMzMyNn0.Jc4mGv6FLdrgRzFmaxGyKDQsdmpcEAlw1XgdjwhGrwwBH0pInGuG9KPoTMix0PXUzU6PtxbpMx-DRCf4yBSxzg" )


        Call.enqueue(object : Callback,retrofit2.Callback<MutableList<category_seeall_response>> {
            override fun onResponse(
                call: Call<MutableList<category_seeall_response>>,
                response: Response<MutableList<category_seeall_response>>
            ) {
                val t = (activity as activity_Dashboard).token

//                    val x = response.body().toString()
                Log.d("resp",t)
                if (response.isSuccessful) {
                    recyclerView.apply {
                        layoutManager = LinearLayoutManager(context)
                        Log.d("Madhur","Hello")
                        adapter = category_adapter(response.body()!!)


                    }
                    Log.e("success", response.body().toString())
                }
            }

            override fun onFailure(
                call: Call<MutableList<category_seeall_response>>,
                t: Throwable
            ) {
                t.printStackTrace()
                Log.e("failure", t.message.toString())
            }
        }
        )
    }
}