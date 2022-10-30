package com.example.internfactory.Activities

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
import com.example.internfactory.R
import com.example.internfactory.modules.LoginRequest
import com.example.internfactory.modules.LoginResponse
import com.example.internfactory.modules.User
import com.example.internfactory.modules.UserDetails
import com.example.internfactory.server.ApiClient
import com.example.internfactory.server.RetrofitApi
import com.example.internfactory.server.ServiceBuilder
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
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
        val view= inflater.inflate(R.layout.fragment_sign_in_, container, false)

        emailin= view.findViewById(R.id.email_inp)
        passwordin= view.findViewById(R.id.password_input)
        buttonin= view.findViewById(R.id.login_btn)

        userDetails=UserDetails(view.context)


        buttonin.setOnClickListener {

//            apiClient.getApiService().login(LoginRequest("bansal@gmail.com", "12345678"))
//                .enqueue(object : Callback<LoginResponse> {
//
//                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
//                        Toast.makeText(
//                            view.context,
//                            "Please check your internet connection",
//                            Toast.LENGTH_SHORT
//                        ).show()
//                        Log.i("Naman", "Please check your internet connection")
//                    }
//
//                    override fun onResponse(
//                        call: Call<LoginResponse>,
//                        response: Response<LoginResponse>
//                    ) {
//                        val loginResponse = response.body()
//
//                        if (loginResponse?.user != null) {
//
//                            suspend fun storedData(name:String){
//                                var x=userDetails.storeUserData(loginResponse.authToken.toString())
//                                return x
//                            }
//                            Toast.makeText(view.context, "Hello " + "!", Toast.LENGTH_SHORT).show()
////                        Log.i("Naman", response.body().toString())
//                        }
//                        if(loginResponse?.user==null){
//                            Toast.makeText(view.context, "Invalid Email or Password", Toast.LENGTH_SHORT).show()
//                        }
//                    }
//                })
//        }
//        return view




            val user= User(null,null,emailin.text.toString(),passwordin.text.toString())
            val retrofitApi= ServiceBuilder.buildService(RetrofitApi::class.java)
            val call=retrofitApi.sendUserData(user)


            call.enqueue(object: Callback<User>{
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    val login_response=response.body()

                    if(login_response == null){
                        Toast.makeText(view.context, "Invalid Email or Password", Toast.LENGTH_SHORT).show()
                    }
                    else{
//                        userDetails.storeUserData(login_response.Token.toString())
                        Toast.makeText(view.context, "Hello " + response.body()?.firstName.toString() + "!", Toast.LENGTH_SHORT).show()
                        Log.i("Naman", response.body().toString())
                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    Toast.makeText(view.context, "Please check your internet connection", Toast.LENGTH_SHORT).show()
                    Log.i("Naman", "Please check your internet connection")
                }
            })
        }
        return view

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        password_text = requireView().findViewById(R.id.password_input  )
        ed1 = requireView().findViewById(R.id.email_inp)
        login_btn = requireView().findViewById(R.id.login_btn)
        email_cont = requireView().findViewById(R.id.ed1)
        password_cont = requireView().findViewById(R.id.password_inp)

        ed1.addTextChangedListener{
            email_cont.helperText = validemail()
            login_btn.isEnabled = (email_cont.helperText == null) and (password_cont.helperText == null)
        }
        password_text.addTextChangedListener{
            password_cont.helperText = validPass()
            login_btn.isEnabled = (email_cont.helperText == null) and (password_cont.helperText == null)
        }
    }
    private fun validPass(): String? {
        val pass_txt = password_text.text.toString()
        if(pass_txt.length<8){
            Log.d("madhur","1st cont")
            return "Minimum 8 characters Required"
        }
        if(!(pass_txt.matches(".*[A-Z]*.".toRegex()))){
            return "At least 1 UpperCase Alphabet Required"
        }
        if(!pass_txt.matches(".*[a-z]*.".toRegex())){
            return "At least 1 LowerCase Alphabet Required"
        }
        if(!pass_txt.matches(".*[@#$%^&*+=]*.".toRegex())){
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
}