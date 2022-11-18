package com.example.internfactory

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.internfactory.Activities.connecting
import com.example.internfactory.modules.*
import com.example.internfactory.server.CommunicatorEmail
import com.example.internfactory.server.RetrofitApi
import com.example.internfactory.server.ServiceBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditProfile_Fragment : Fragment() {

    private lateinit var firstname_inp:TextView
    private lateinit var lastname_inp:TextView
    private lateinit var email_inp: TextView
    private lateinit var gender_inp:TextView
    private lateinit var number_inp:TextView
    private  var email:String=""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_edit_profile, container, false)


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        GlobalScope.launch(Dispatchers.IO) {
            val userDetails= UserDetails(view.context)
            userDetails.getToken().collect{
                email=it.signInemail
                apicalling()
                Log.d("naman",email)
            }
        }

        firstname_inp=view.findViewById(R.id.textView2)
        lastname_inp=view.findViewById(R.id.textView4)
        email_inp=view.findViewById(R.id.textView6)
        number_inp=view.findViewById(R.id.textView8)
        gender_inp=view.findViewById(R.id.textView10)


    }
    fun apicalling(){
        val editProfileRequest=EditProfileRequest(email)
        val serviceBuilder = ServiceBuilder.buildService(RetrofitApi::class.java)
        val call = serviceBuilder.viewProfile(editProfileRequest,"Bearer " + (activity as activity_Dashboard).token)

        call.enqueue(object : Callback<EditProfileResponse> {
            override fun onResponse(call: Call<EditProfileResponse>, response: Response<EditProfileResponse>) {
                if (response.isSuccessful) {
                    (activity as activity_Dashboard).name=response.body()?.firstname.toString()+" "+response.body()?.lastname.toString()
                    firstname_inp.text= response.body()?.firstname
                    lastname_inp.text=response.body()?.lastname
                    email_inp.text=response.body()?.email
                    number_inp.text=response.body()?.phoneNumber
                    gender_inp.text=response.body()?.gender
                    Log.i("Naman", response.code().toString() )
//                    progressBar.dismiss()
                } else {
                    Toast.makeText(view?.context,"Incorrect Format", Toast.LENGTH_SHORT).show()
//                    progressBar.dismiss()

                }
                Log.d("naman",response.body().toString())
            }

            override fun onFailure(call: Call<EditProfileResponse>, t: Throwable) {
                Toast.makeText(view?.context, "Please check your internet connection", Toast.LENGTH_SHORT).show()
                Log.i("Naman", "Please check your internet connection")
//                progressBar.dismiss()
            }
        })
    }
}