package com.example.internfactory

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.internfactory.Activities.Adapters.internship_adapter
import com.example.internfactory.modules.Internship_request
import com.example.internfactory.modules.Internship_response
import com.example.internfactory.modules.category_seeall_response
import com.example.internfactory.modules.trending_seeall_response
import com.example.internfactory.server.RetrofitApi
import com.example.internfactory.server.ServiceBuilder
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class Internship_See_all : Fragment() {

    lateinit var adapter: internship_adapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_internship__see_all, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.MyInternshiprecyclerview)

        val internshipRequest=Internship_request("0","5","id","asc")
        val serviceBuilder = ServiceBuilder.buildService(RetrofitApi::class.java)
        val Call = serviceBuilder.allinternship((activity as activity_Dashboard).xid,internshipRequest,"Bearer " + (activity as activity_Dashboard).token)

        Log.d("id", (activity as activity_Dashboard).xid.toString())
        Call.enqueue(object : Callback,
            retrofit2.Callback<Internship_response> {
            override fun onResponse(
                call: Call<Internship_response>,
                response: Response<Internship_response>
            ) {
                if(response.isSuccessful) {
                    recyclerView.layoutManager= LinearLayoutManager(requireContext())
                    adapter= internship_adapter(response.body()!!)
                    recyclerView.adapter=adapter
                    adapter.setOnItemClickListner(object : internship_adapter.onItemClickListner {
                        override fun onItemClick(position: Int) {
                            Toast.makeText(view?.context,"Clicked Item", Toast.LENGTH_SHORT).show()
                        }
                    })
                }
            }

            override fun onFailure(
                call: Call<Internship_response>,
                t: Throwable
            ) {
                t.printStackTrace()
            }

        })
        return view
    }

}