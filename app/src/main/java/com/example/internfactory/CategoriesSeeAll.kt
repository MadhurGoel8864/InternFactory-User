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
import com.example.internfactory.Activities.Adapters.category_adapter
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
        val fm : FragmentManager = parentFragmentManager
        val ft : FragmentTransaction = fm.beginTransaction()

        val recyclerView = view.findViewById<RecyclerView>(R.id.myrecycleView)

        val serviceBuilder = ServiceBuilder.buildService(RetrofitApi::class.java)
        val Call = serviceBuilder.getcategories("Bearer " + (activity as activity_Dashboard).token )


        Call.enqueue(object : Callback,retrofit2.Callback<MutableList<category_seeall_response>> {
            override fun onResponse(
                call: Call<MutableList<category_seeall_response>>,
                response: Response<MutableList<category_seeall_response>>
            ) {
                if (response.isSuccessful) {

                    recyclerView.layoutManager=LinearLayoutManager(requireContext())
                    adapter= category_adapter(response.body()!!)
                    recyclerView.adapter=adapter
                    adapter.setOnItemClickListner(object : category_adapter.onItemClickListner {
                        override fun onItemClick(position: Int) {
                            (activity as activity_Dashboard).xid = adapter.categoriesSeeAll[position].categoryId!!
                            internship_see_all_frag()
//                            Toast.makeText(requireContext(), "click", Toast.LENGTH_SHORT).show()
                            Log.d("tech",(activity as activity_Dashboard).xid.toString())
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