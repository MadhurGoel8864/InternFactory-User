 package com.example.internfactory.Activities

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.internfactory.Activities.Adapters.SearchAdapter
import com.example.internfactory.Internship_See_all
import com.example.internfactory.R
import com.example.internfactory.activity_Dashboard
import com.example.internfactory.modules.InternshipDetail_response
import com.example.internfactory.modules.SearchingResponse
import com.example.internfactory.server.RetrofitApi
import com.example.internfactory.server.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

 class internship_deatils_fragement : Fragment() {

    private lateinit var logo:ImageView
    private lateinit var displayName:TextView
    private lateinit var companyName:TextView
    private lateinit var workPlace:TextView
    private lateinit var tenure:TextView
    private lateinit var stipened:TextView
    private lateinit var date:TextView
    private lateinit var about:TextView
    private lateinit var skillsRequired:TextView
    private lateinit var whoCanApply:TextView
    private lateinit var perks:TextView
    private lateinit var applybtn:Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_internship_deatils_fragement, container, false)

        val fm : FragmentManager = parentFragmentManager
        val ft : FragmentTransaction = fm.beginTransaction()

        logo=view.findViewById(R.id.imageView12)
        displayName=view.findViewById(R.id.textView17)
        companyName=view.findViewById(R.id.textView18)
        workPlace=view.findViewById(R.id.textView19)
        tenure=view.findViewById(R.id.textView20)
        stipened=view.findViewById(R.id.textView21)
        date=view.findViewById(R.id.enddate)
        about=view.findViewById(R.id.about_text)
        skillsRequired=view.findViewById(R.id.skills_list)
        whoCanApply=view.findViewById(R.id.apply_criteria_list)
        perks=view.findViewById(R.id.perks_list)
        applybtn=view.findViewById(R.id.apply)

        val retrofitAPI = ServiceBuilder.buildService(RetrofitApi::class.java)
        val call = retrofitAPI.internshipDetail((activity as activity_Dashboard).internshipId,"Bearer " + (activity as activity_Dashboard).token)

        call.enqueue(object : Callback<InternshipDetail_response> {
            override fun onResponse(
                call: Call<InternshipDetail_response>,
                response: Response<InternshipDetail_response>
            ) {
                if (response.isSuccessful) {
                    val x = "https://internfactory.herokuapp.com/file/" + response.body()?.imageUrl
                    logo.load(x)
                    displayName.text=response.body()?.displayName
                    companyName.text=response.body()?.provider
                    workPlace.text=response.body()?.type
                    tenure.text=response.body()?.tenure
                    stipened.text=response.body()?.stipend
                    date.text=response.body()?.lastDate
                    about.text=response.body()?.about
                    skillsRequired.text=response.body()?.skills
                    whoCanApply.text=response.body()?.who_can_apply
                    perks.text=response.body()?.perks
                    applybtn.setOnClickListener{
                        apply_internship()
                    }
                } else {
                    Toast.makeText(view?.context, response.code().toString(),Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<InternshipDetail_response>, t: Throwable) {
                Toast.makeText(view?.context, "Please Check Your Internet Connection",Toast.LENGTH_SHORT).show()
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

     fun apply_internship() {
         val apply_internship = application_fragment()
         replaceFrag(apply_internship, "applyInternship")
     }
}