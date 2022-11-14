package com.example.internfactory

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.internfactory.Activities.Adapters.category_ViewHolder
import com.example.internfactory.Activities.Adapters.category_adapter
import com.example.internfactory.Activities.Adapters.trending_seeall_adapters
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

    lateinit var adapter:category_adapter
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
        val Call = serviceBuilder.getcategories("Bearer " + (activity as activity_Dashboard).token )


        Call.enqueue(object : Callback,retrofit2.Callback<MutableList<category_seeall_response>> {
            override fun onResponse(
                call: Call<MutableList<category_seeall_response>>,
                response: Response<MutableList<category_seeall_response>>
            ) {
                val t = (activity as activity_Dashboard).token

                if (response.isSuccessful) {

                    (activity as activity_Dashboard).xid = response.body()?.get(0)?.categoryId!!
                    Log.d("cat", (activity as activity_Dashboard).xid.toString())

                    recyclerView.layoutManager=LinearLayoutManager(requireContext())
                    adapter= category_adapter(response.body()!!)
                    recyclerView.adapter=adapter
                    adapter.setOnItemClickListner(object : category_adapter.onItemClickListner {
                        override fun onItemClick(position: Int) {
                            Toast.makeText(view?.context,"Clicked Item", Toast.LENGTH_SHORT).show()
                        }
                    })
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