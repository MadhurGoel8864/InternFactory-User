package com.example.internfactory

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.internfactory.Activities.Adapters.internship_adapter
import com.example.internfactory.Activities.internship_deatils_fragement
import com.example.internfactory.modules.Internship_request
import com.example.internfactory.modules.Internship_response
import com.example.internfactory.server.RetrofitApi
import com.example.internfactory.server.ServiceBuilder
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class Internship_See_all : Fragment(){

    lateinit var adapter: internship_adapter
    private lateinit var apply_btn: Button
    private lateinit var viewmore:TextView
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_internship__see_all, container, false)
        val fm : FragmentManager = parentFragmentManager
        val ft : FragmentTransaction = fm.beginTransaction()

        viewmore=view.findViewById(R.id.textView31)
        
        val recyclerView = view.findViewById<RecyclerView>(R.id.MyInternshiprecyclerview)

        var pagenum:Int=0
        var idd:Int
        if((activity as activity_Dashboard).xid==-1){idd=(activity as activity_Dashboard).tid}else{idd=(activity as activity_Dashboard).xid}
        Log.d("id", (activity as activity_Dashboard).xid.toString())
        val internshipRequest=Internship_request(pagenum,"5","id","asc")
        val serviceBuilder = ServiceBuilder.buildService(RetrofitApi::class.java)
        val Call = serviceBuilder.allinternship(idd,internshipRequest,"Bearer " + (activity as activity_Dashboard).token)

        Log.d("id", idd.toString()+" "+"token: "+(activity as activity_Dashboard).token)
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
                            (activity as activity_Dashboard).internshipId = adapter.internshipResponse.content[position].id
                            internship_details()
                            Toast.makeText(view?.context,(activity as activity_Dashboard).internshipId.toString(), Toast.LENGTH_SHORT).show()
                        }
//
//                        override fun btnonItemClick(position: Button) {
//                            internship_details()
//                            Toast.makeText(context, position.toString(), Toast.LENGTH_SHORT).show()
//                        }
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

        viewmore.setOnClickListener{
            if(pagenum<5) {
                pagenum = pagenum + 1

                val internshipRequestt = Internship_request(pagenum, "5", "id", "asc")
                Log.d("page", pagenum.toString())
                val serviceBuildert = ServiceBuilder.buildService(RetrofitApi::class.java)
                val Call2 = serviceBuildert.allinternship(
                    (activity as activity_Dashboard).xid, internshipRequestt, "Bearer " + (activity as activity_Dashboard).token)
                Call2.enqueue(object : Callback,
                    retrofit2.Callback<Internship_response> {
                    override fun onResponse(
                        call: Call<Internship_response>,
                        response: Response<Internship_response>
                    ) {
                        if (response.isSuccessful) {
                            recyclerView.layoutManager = LinearLayoutManager(requireContext())
                            adapter = internship_adapter(response.body()!!)
                            recyclerView.adapter = adapter
                            adapter.setOnItemClickListner(object :
                                internship_adapter.onItemClickListner {
                                override fun onItemClick(position: Int) {
                                    internship_details()
                                    Toast.makeText(view?.context, "Clicked Item", Toast.LENGTH_SHORT).show()
                                }

//                                override fun btnonItemClick(position: Button) {
//                                    internship_details()
//                                    Toast.makeText(context, position.toString(), Toast.LENGTH_SHORT).show()
//                                }
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
            }

        }
        return view
    }

    private fun replaceFrag(fragment : Fragment,name: String){
        val fm : FragmentManager = parentFragmentManager
        val ft : FragmentTransaction = fm.beginTransaction()
        ft.addToBackStack(name)
        ft.add(R.id.dashboard, fragment)
        ft.commit()
    }
    fun internship_details(){
        val internship_details = internship_deatils_fragement()
        replaceFrag(internship_details,"internship_details")
    }
}