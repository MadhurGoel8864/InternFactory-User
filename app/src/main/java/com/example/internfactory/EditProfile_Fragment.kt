package com.example.internfactory

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.internfactory.Activities.connecting
import com.example.internfactory.modules.EditProfileRequest
import com.example.internfactory.modules.EditProfileResponse
import com.example.internfactory.modules.UserDetails
import com.example.internfactory.modules.trending_seeall_response
import com.example.internfactory.server.CommunicatorEmail
import com.example.internfactory.server.RetrofitApi
import com.example.internfactory.server.ServiceBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import java.lang.reflect.GenericArrayType
import javax.security.auth.callback.Callback

class EditProfile_Fragment : Fragment() {

    private lateinit var firstname_inp:TextView
    private lateinit var lastname_inp:TextView
    private lateinit var email_inp: TextView
    private lateinit var gender_inp:TextView
    private lateinit var number_inp:TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_edit_profile, container, false)

        var email:String=""

        GlobalScope.launch(Dispatchers.IO) {
            val userDetails= UserDetails(view.context)
            userDetails.getToken().collect{
                email=it.signInemail
                Log.d("naman",email)
            }
        }

        firstname_inp=view.findViewById(R.id.textView2)
        lastname_inp=view.findViewById(R.id.textView4)
        email_inp=view.findViewById(R.id.textView6)
        number_inp=view.findViewById(R.id.textView8)
        gender_inp=view.findViewById(R.id.textView10)

        val editProfileRequest=EditProfileRequest(email)
        val serviceBuilder = ServiceBuilder.buildService(RetrofitApi::class.java)
        val Call = serviceBuilder.viewProfile(editProfileRequest)

        Call.enqueue(object : Callback,
            retrofit2.Callback<EditProfileResponse> {
            override fun onResponse(call: Call<EditProfileResponse>, response: Response<EditProfileResponse>) {
                TODO("Not yet implemented")
                if(response.isSuccessful){
                    firstname_inp.text= response.body()?.firstname
                    lastname_inp.text=response.body()?.lastname
                }
            }

            override fun onFailure(call: Call<EditProfileResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
            })
        return view
    }
}