package com.example.internfactory

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
import com.example.internfactory.Activities.Adapters.trending_seeall_adapters
import com.example.internfactory.modules.trending_seeall_response
import com.example.internfactory.server.RetrofitApi
import com.example.internfactory.server.ServiceBuilder
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class trendingSeeAll : Fragment() {

    lateinit var adapter:trending_seeall_adapters

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_trending_see_all, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.Myrecyclerview)

        val serviceBuilder = ServiceBuilder.buildService(RetrofitApi::class.java)
        val Call = serviceBuilder.gettrends("Bearer " + (activity as activity_Dashboard).token)
        Call.enqueue(object :Callback,
            retrofit2.Callback<MutableList<trending_seeall_response>> {
            override fun onResponse(
                call: Call<MutableList<trending_seeall_response>>,
                response: Response<MutableList<trending_seeall_response>>
            ) {
                    if(response.isSuccessful) {
                        recyclerView.layoutManager=LinearLayoutManager(requireContext())
                        adapter= trending_seeall_adapters(response.body()!!)
                        recyclerView.adapter=adapter
                        adapter.setOnItemClickListner(object : trending_seeall_adapters.onItemClickListner {
                            override fun onItemClick(position: Int) {
                                (activity as activity_Dashboard).tid= adapter.trendingSeeallResponse[position].categoryId!!
                                Log.d("tid",(activity as activity_Dashboard).tid.toString())
                                internship_see_all_frag()
//                                Toast.makeText(view?.context,"Clicked Item", Toast.LENGTH_SHORT).show()
                            }
                        })
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

    private fun replaceFrag(fragment : Fragment,name: String){
        val fm : FragmentManager = parentFragmentManager
        val ft : FragmentTransaction = fm.beginTransaction()
        ft.addToBackStack(name)
        ft.add(R.id.dashboard, fragment)
        ft.commit()
    }
    fun internship_see_all_frag(){
        val internship_see_all_frag = Internship_See_all()
        replaceFrag(internship_see_all_frag,"internship_seeAll")
    }
}
