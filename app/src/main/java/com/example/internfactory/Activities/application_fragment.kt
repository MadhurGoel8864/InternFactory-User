package com.example.internfactory.Activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.internfactory.R
import com.example.internfactory.activity_Dashboard
import com.example.internfactory.modules.*
import com.example.internfactory.server.RetrofitApi
import com.example.internfactory.server.ServiceBuilder
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class application_fragment : Fragment() {
    private lateinit var whyshould:TextInputEditText
    private lateinit var shareyourwork:TextInputEditText
    private lateinit var workedinteam:TextInputEditText
    private lateinit var strength:TextInputEditText
    private lateinit var weakness:TextInputEditText
    private lateinit var hobbies:TextInputEditText
    private lateinit var button:Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_application_fragment, container, false)

        whyshould=view.findViewById(R.id.ques1_text)
        shareyourwork=view.findViewById(R.id.ques2_text)
        workedinteam=view.findViewById(R.id.ques3_text)
        strength=view.findViewById(R.id.ques4_text)
        weakness=view.findViewById(R.id.ques5_text)
        hobbies=view.findViewById(R.id.ques6_text)
        button=view.findViewById(R.id.btn)

        val why_should=whyshould.text.toString()
        val share_your_woek=shareyourwork.text.toString()
        val worked_in_team=workedinteam.text.toString()
        val strengths=strength.text.toString()
        val weak=weakness.text.toString()
        val hobby=hobbies.text.toString()

//        button.setOnClickListener{
//
//            val applyinternships= ApplyInternshipRequest(why_should,share_your_woek,worked_in_team,strengths,weak,hobby)
//            val retrofitAPI = ServiceBuilder.buildService(RetrofitApi::class.java)
//            val call = retrofitAPI.applyinternship((activity as activity_Dashboard).email,applyinternships,"Bearer " + (activity as activity_Dashboard).token)
//
//            Log.d("tuk",InternshipDetails(null,null,null,null,null,null,null,null,null,null,null,null,null).id.toString())
//            call.enqueue(object : Callback<ApplyInternshipResponses> {
//                override fun onResponse(call: Call<ApplyInternshipResponses>, response: Response<ApplyInternshipResponses>) {
//                    if (response.code() == 200) {
//                        Log.i("Naman", response.code().toString().toString())
//                    } else {
//                        Toast.makeText(view?.context,response.code().toString(), Toast.LENGTH_SHORT).show()
//
//                    }
//                }
//
//                override fun onFailure(call: Call<ApplyInternshipResponses>, t: Throwable) {
//                    Toast.makeText(view?.context, "Please check your internet connection", Toast.LENGTH_SHORT).show()
//                    Log.i("Naman", "Please check your internet connection")
//                }
//            })
//        }

        return view
    }

}