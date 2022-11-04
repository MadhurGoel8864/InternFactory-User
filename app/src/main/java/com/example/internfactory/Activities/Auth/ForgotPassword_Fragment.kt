package com.example.internfactory.Activities.Auth

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.internfactory.Activities.connecting
import com.example.internfactory.R
import com.example.internfactory.modules.Email
import com.example.internfactory.modules.User
import com.example.internfactory.server.RetrofitApi
import com.example.internfactory.server.ServiceBuilder
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForgotPassword_Fragment : Fragment() {
    private lateinit var email_inp: TextInputEditText
    private lateinit var email_cont: TextInputLayout
    private lateinit var button: Button
    lateinit var otpBtn : Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View =  inflater.inflate(R.layout.fragment_forgot_password_, container, false)

        val fm : FragmentManager = parentFragmentManager
        val ft : FragmentTransaction = fm.beginTransaction()

        otpBtn = view.findViewById(R.id.otp_btn)
        otpBtn.setOnClickListener{
            (activity as connecting).email = email_inp.text.toString()
            val email = Email(email_inp.text.toString())
            val retrofitApi = ServiceBuilder.buildService(RetrofitApi::class.java)
            val call = retrofitApi.forgotPassword(email)

            call.enqueue(object: Callback<String>{
                override fun onResponse(call: Call<String>, response: Response<String>){
                    if (response.code()==200){
                        Toast.makeText(view.context, response.body()?.toString(), Toast.LENGTH_SHORT).show()
                        otpVerificationFrag()
                        Log.i("Naman", response.body().toString())
                    }
                    else{
                        android.widget.Toast.makeText(view.context, response.code().toString(), android.widget.Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(call: Call<String>, t:Throwable){
                    Toast.makeText(view.context, "Failed", Toast.LENGTH_LONG).show()
                }
            })
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button = requireView().findViewById(R.id.otp_btn)
        email_cont = requireView().findViewById(R.id.ed1)
        email_inp = requireView().findViewById(R.id.forgot_email_inp)
        email_inp.addTextChangedListener {
            email_cont.helperText = validEmail()
            button.isEnabled = (email_cont.helperText == null)
        }
    }
    private fun validEmail(): String? {
        val email_text = email_inp.text.toString()
        if (!Patterns.EMAIL_ADDRESS.matcher(email_text).matches()) {
            return "Invalid Email Address "
        }
        return null
    }

    private fun replaceFrag(fragment : Fragment,name: String){
        val fm : FragmentManager = parentFragmentManager
        val ft : FragmentTransaction = fm.beginTransaction()
        ft.addToBackStack(name)
        ft.add(R.id.container, fragment)
        ft.commit()
    }

    fun otpVerificationFrag(){
        val otpVerificationFrag = Verification_Fragment()
        replaceFrag(otpVerificationFrag,"otppage")
    }
}

