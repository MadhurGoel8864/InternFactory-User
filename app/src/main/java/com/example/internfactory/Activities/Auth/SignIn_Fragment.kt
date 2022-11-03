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
import com.example.internfactory.R
import com.example.internfactory.modules.LoginResponse
import com.example.internfactory.modules.User
import com.example.internfactory.modules.UserDetails
import com.example.internfactory.server.RetrofitApi
import com.example.internfactory.server.ServiceBuilder
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SignIn_Fragment : Fragment() {

    private lateinit var emailin: TextInputEditText
    private lateinit var passwordin:TextInputEditText
    private lateinit var buttonin:Button
    private lateinit var userDetails: UserDetails


    private lateinit var password_text : TextInputEditText
    private lateinit var password_cont : TextInputLayout
    private lateinit var ed1 : TextInputEditText
    private lateinit var email_cont : TextInputLayout
    private lateinit var login_btn : Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_sign_in_, container, false)


        val fm : FragmentManager = parentFragmentManager
        val ft : FragmentTransaction = fm.beginTransaction()


        emailin = view.findViewById(R.id.email_inp)
        passwordin = view.findViewById(R.id.password_input)
        buttonin = view.findViewById(R.id.login_btn)

        userDetails = UserDetails(view.context)


//        buttonin.setOnClickListener {

//        }
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        password_text = requireView().findViewById(R.id.password_input  )
        ed1 = requireView().findViewById(R.id.email_inp)
        login_btn = requireView().findViewById(R.id.login_btn)
        email_cont = requireView().findViewById(R.id.ed1)
        password_cont = requireView().findViewById(R.id.password_inp)

//        ed1.addTextChangedListener{
//            email_cont.helperText = validemail()
//            login_btn.isEnabled = (email_cont.helperText == null) and (password_cont.helperText == null)
//        }
//        password_text.addTextChangedListener{
//            password_cont.helperText = validPass()
//            login_btn.isEnabled = (email_cont.helperText == null) and (password_cont.helperText == null)
//        }
//    }

        login_btn.setOnClickListener{

            email_cont.helperText = validemail()
            password_cont.helperText = validPass()

            if(email_cont.helperText == null && password_cont.helperText == null){
                apicalling()
            }
        }
        }

    private fun validPass(): String? {
        val pass_txt = password_text.text.toString()
        if(pass_txt.length<8){
            return "Minimum 8 characters Required"
        }
        if(!pass_txt.contains(Regex("[A-Z]"))){
            return "At least 1 UpperCase Alphabet Required"
        }
        if(!pass_txt.contains(Regex("[a-z]"))){
            return "At least 1 LowerCase Alphabet Required"
        }
        if(!pass_txt.contains(Regex("[@#\$%^&*+=]"))){
            return "At least 1  Special Character Required"
        }
        return null
    }
    private fun validemail(): String? {
        val email_text = ed1.text.toString()
        if(!Patterns.EMAIL_ADDRESS.matcher(email_text).matches()){
            return "Invalid Email Address"
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

    fun apicalling() {
        val user = User(null, null, emailin.text.toString(), passwordin.text.toString())
        val retrofitAPI = ServiceBuilder.buildService(RetrofitApi::class.java)
        val call = retrofitAPI.login(user)

        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.code() == 200) {
                    Toast.makeText(view?.context, response.body()?.toString(), Toast.LENGTH_SHORT).show()
                    runBlocking { view?.let { UserDetails(it.context).storeUserData(response.body()?.authToken.toString()) } }
                    Log.i("Naman", response.code().toString().toString())
                } else {
                    Toast.makeText(view?.context, response.code().toString(), Toast.LENGTH_SHORT).show()

                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(
                    view?.context,
                    "Please check your internet connection",
                    Toast.LENGTH_SHORT
                ).show()
                Log.i("Naman", "Please check your internet connection")
            }
        })
    }


}