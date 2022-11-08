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
import com.example.internfactory.modules.category_seeall_request
import com.example.internfactory.modules.category_seeall_response
import com.example.internfactory.server.RetrofitApi
import com.example.internfactory.server.ServiceBuilder
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class CategoriesSeeAll : Fragment() {

    private fun replaceFrag(fragment : Fragment, name: String){
        val fm : FragmentManager =supportFragmentManager
        val ft : FragmentTransaction = fm.beginTransaction()
        ft.addToBackStack(name)
        ft.add(R.id.dashboard, fragment)
        ft.commit()
    }

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
        val Call = serviceBuilder.getcategories()


        Call.enqueue(object : Callback,retrofit2.Callback<MutableList<category_seeall_response>> {
            override fun onResponse(
                call: Call<MutableList<category_seeall_response>>,
                response: Response<MutableList<category_seeall_response>>
            ) {
                if (response.isSuccessful) {
                    recyclerView.apply {
                        layoutManager = LinearLayoutManager(context)
                        adapter = category_adapter(response.body()!!)


                    }
//                    recyclerView.apply {
//                        layoutManager = LinearLayoutManager(this@CategoriesSeeAll)
//                        adapter = category_adapter(response.body()!!)
//                    }
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