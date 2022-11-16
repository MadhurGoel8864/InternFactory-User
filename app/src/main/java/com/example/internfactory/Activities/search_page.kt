package com.example.internfactory.Activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.internfactory.Activities.Adapters.SearchAdapter
import com.example.internfactory.Activities.Adapters.category_adapter
import com.example.internfactory.R
import com.example.internfactory.activity_Dashboard
import com.example.internfactory.modules.SearchingRequest
import com.example.internfactory.modules.SearchingResponse
import com.example.internfactory.modules.ViewProfileResponse
import com.example.internfactory.server.RetrofitApi
import com.example.internfactory.server.ServiceBuilder
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class search_page : Fragment() {

    lateinit var adapter: SearchAdapter

    private lateinit var Search:TextInputEditText
    private lateinit var SearchText:TextInputLayout
    private lateinit var SearchResult:RecyclerView
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_search_page, container, false)
        Search=view.findViewById(R.id.search_box_input)
        SearchText=view.findViewById(R.id.search_box)
        SearchResult=view.findViewById(R.id.searchResult)

        val search=Search.text.toString()
        val pagenumber:Int=0
        Log.d("boom",search)

        val recyclerView = view.findViewById<RecyclerView>(R.id.searchResult)

        Search.setOnClickListener {
            val searching=SearchingRequest(pagenumber,5,"title","asc")
            val retrofitAPI = ServiceBuilder.buildService(RetrofitApi::class.java)
            val call = retrofitAPI.searchapi(Search.text.toString(),searching,"Bearer " + (activity as activity_Dashboard).token)

            Log.d("boom",Search.text.toString()+" "+pagenumber+" "+(activity as activity_Dashboard).token)
            call.enqueue(object : Callback<SearchingResponse> {
                override fun onResponse(
                    call: Call<SearchingResponse>,
                    response: Response<SearchingResponse>
                ) {
                    if (response.isSuccessful) {

                        recyclerView.layoutManager= LinearLayoutManager(requireContext())
                        adapter= SearchAdapter(response.body()!!)
                        recyclerView.adapter=adapter
                        adapter.setOnItemClickListner(object : SearchAdapter.onItemClickListner {
                            override fun onItemClick(position: Int) {
                                Toast.makeText(requireContext(), "click", Toast.LENGTH_SHORT).show()
                            }
                        })

                        Toast.makeText(view?.context, "Succesfully Saved", Toast.LENGTH_SHORT).show()
                        Log.d("Naman", response.body().toString())
                    } else {
                        android.widget.Toast.makeText(view?.context, response.code().toString(), android.widget.Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<SearchingResponse>, t: Throwable) {
                    Toast.makeText(view?.context,"Please check your internet connection", Toast.LENGTH_SHORT).show()
                    Log.i("Naman", t.stackTraceToString())
                }
            })
        }
        return view
    }
}