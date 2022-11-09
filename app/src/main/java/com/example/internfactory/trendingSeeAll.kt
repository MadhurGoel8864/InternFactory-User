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
import com.example.internfactory.Activities.Adapters.category_adapter
import com.example.internfactory.Activities.Adapters.trending_seeall_adapters
import com.example.internfactory.modules.trending_seeall_response
import com.example.internfactory.server.RetrofitApi
import com.example.internfactory.server.ServiceBuilder
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class trendingSeeAll : Fragment() {

    private lateinit var newRecyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<Trending_dataclass>
    lateinit var imageId: Array<Int>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_trending_see_all, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.Myrecyclerview)

        val serviceBuilder = ServiceBuilder.buildService(RetrofitApi::class.java)
        val Call = serviceBuilder.gettrends()

        Call.enqueue(object :Callback,
            retrofit2.Callback<MutableList<trending_seeall_response>> {
            override fun onResponse(
                call: Call<MutableList<trending_seeall_response>>,
                response: Response<MutableList<trending_seeall_response>>
            ) {
                    if(response.isSuccessful){
                        recyclerView.apply {
                            layoutManager = LinearLayoutManager(context)
                            adapter = trending_seeall_adapters(response.body()!!)
                        }
                    }
            }
            override fun onFailure(
                call: Call<MutableList<trending_seeall_response>>,
                t: Throwable
            ) {
                t.printStackTrace()
            }

        })
        return view
    }

}