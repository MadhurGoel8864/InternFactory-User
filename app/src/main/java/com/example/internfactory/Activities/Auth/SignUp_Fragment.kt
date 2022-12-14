package com.example.internfactory.Activities.Auth

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.internfactory.Activities.connecting
import com.example.internfactory.R
import com.example.internfactory.modules.SignUpResponse
import com.example.internfactory.modules.User
import com.example.internfactory.server.RetrofitApi
import com.example.internfactory.server.ServiceBuilder
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUp_Fragment : Fragment() {

    lateinit var progressBar:AlertDialog

    private lateinit var firstname_inp : TextInputEditText
    private lateinit var lastname_inp : TextInputEditText
    private lateinit var email_inp : TextInputEditText
    private lateinit var password_inp : TextInputEditText
    private lateinit var firstname : TextInputLayout
    private lateinit var lastname : TextInputLayout
    private lateinit var email_box : TextInputLayout
    private lateinit var password : TextInputLayout
    private lateinit var button : Button

    var builder : AlertDialog.Builder? = null
    fun getDialogueProgressBar(view : View) : AlertDialog.Builder{
        if(builder==null){
            builder = AlertDialog.Builder(view.context)
            val progressBar = ProgressBar(view.context)
            val lp = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            progressBar.layoutParams = lp
            builder!!.setView(progressBar)
        }
        return builder as AlertDialog.Builder
    }


    lateinit var signupbtn: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View= inflater.inflate(R.layout.fragment_sign_up_, container, false)
        val fm : FragmentManager = parentFragmentManager
        val ft : FragmentTransaction = fm.beginTransaction()
        firstname_inp=view.findViewById(R.id.first_name_inp)
        lastname_inp=view.findViewById(R.id.last_name_inp)
        email_inp = view.findViewById(R.id.email_inp)
        password_inp = view.findViewById(R.id.password_form_inp)
        signupbtn=view.findViewById(R.id.sign_up_btn)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firstname_inp = requireView().findViewById(R.id.first_name_inp)
        lastname_inp = requireView().findViewById(R.id.last_name_inp)
        email_inp = requireView().findViewById(R.id.email_inp)
        password_inp = requireView().findViewById(R.id.password_form_inp)
        button = requireView().findViewById<Button>(R.id.sign_up_btn)
        lastname = requireView().findViewById(R.id.last_name)
        firstname = requireView().findViewById(R.id.first_name)
        email_box = requireView().findViewById(R.id.email_box)
        password = requireView().findViewById(R.id.password)

        progressBar = getDialogueProgressBar(view).create()
        progressBar.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        progressBar.setCanceledOnTouchOutside(false)

        button.setOnClickListener{
            progressBar.show()

            email_box.helperText = validemail()
            password.helperText = validPass()
            firstname.helperText = validfirstname()
            lastname.helperText = validlastname()
            if(email_box.helperText == null && password.helperText == null && firstname.helperText == null && lastname.helperText == null){ val firstname =firstname_inp.text.toString()
            val lastname = lastname_inp.text.toString()
            val emaiil = email_inp.text.toString()
            val password= password_inp.text.toString()

            val checkfirstname= isValidName(firstname)
            val checklastname= isValidName(lastname)
            val checkemail= isValidEmail(emaiil)
            (activity as connecting).signUpEmail=email_inp.text.toString()
            (activity as connecting).signuppass=password_inp.text.toString()


            if(checkfirstname){
                if(checklastname){
                    if(checkemail){
                        val msg= isValidPassword(password)
                        if (msg=="true"){
                            val user = User(firstname,lastname,emaiil,password)
                            val retrofitAPI = ServiceBuilder.buildService(RetrofitApi::class.java)
                            val call = retrofitAPI.signIn(user)

                            call.enqueue(object: Callback<SignUpResponse> {
                                override fun onResponse(call: Call<SignUpResponse>, response: Response<SignUpResponse>) {
                                    if (response.code()==201){
                                        Toast.makeText(view.context, "Otp Sent Succesfully", Toast.LENGTH_SHORT).show()
                                        signupotpVerificationFrag()
                                        Log.i("Naman", response.body().toString())
                                        progressBar.dismiss()
                                    }
                                    else if(response.code()==200){
                                        Toast.makeText(view.context, "Otp Sent Succesfully", Toast.LENGTH_SHORT).show()
                                        signupotpVerificationFrag()
                                        Log.i("Naman", response.body().toString())
                                        progressBar.dismiss()
                                    }
                                    else{
                                        android.widget.Toast.makeText(view.context,"Already Registered Email", android.widget.Toast.LENGTH_SHORT).show()
                                        progressBar.dismiss()
                                    }
                                }

                                override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                                    Toast.makeText(view.context, "Please check your internet connection", Toast.LENGTH_SHORT).show()
                                    Log.i("Naman", "Please check your internet connection")
                                    progressBar.dismiss()
                                }
                            })
                        }
                        else{
                            Toast.makeText(view.context, msg, Toast.LENGTH_SHORT).show()
                            progressBar.dismiss()
                        }
                    }
                    else{
                        Toast.makeText(view.context, "Enter a valid email", Toast.LENGTH_SHORT).show()
                        progressBar.dismiss()
                    }
                }
                else{
                    Toast.makeText(view.context, "Enter a valid last name", Toast.LENGTH_SHORT).show()
                    progressBar.dismiss()
                }
            }
            else{
                Toast.makeText(view.context, "Enter valid first name", Toast.LENGTH_SHORT).show()
                progressBar.dismiss()
            }
            }
        }
        }


    private fun validfirstname():String?{
        val txt = firstname_inp.text.toString()
        if(txt==null){
            progressBar.dismiss()
            return "Required"
        }
        if(txt.contains(Regex("[1234567890]"))){
            progressBar.dismiss()
            return "Must contain only alphabets"
        }
        return null
    }
    private fun validlastname():String?{
        val txt = lastname_inp.text.toString()
        if(txt.contains(Regex("[1234567890]"))){
            progressBar.dismiss()
            return "Must contain only alphabets"
        }
        return null
    }

    private fun validPass(): String? {
        val pass_txt = password_inp.text.toString()
        if(pass_txt.length<8){
            progressBar.dismiss()
            return "Minimum 8 characters Required"
        }
        if(!pass_txt.contains(Regex("[A-Z]"))){
            progressBar.dismiss()
            return "At least 1 UpperCase Alphabet Required"
        }
        if(!pass_txt.contains(Regex("[a-z]"))){
            progressBar.dismiss()
            return "At least 1 LowerCase Alphabet Required"
        }
        if(!pass_txt.contains(Regex("[@#\$%^&*+=]"))){
            progressBar.dismiss()
            return "At least 1  Special Character Required"
        }
        return null
    }
    private fun validemail(): String? {
        val email_text = email_inp.text.toString()
        if(!Patterns.EMAIL_ADDRESS.matcher(email_text).matches()){
            progressBar.dismiss()
            return "Invalid Email Address"
        }
        return null
    }

    //api files

    fun isValidName(name: String): Boolean {
        if(name.isEmpty()){
            return false
        }
        for (char in name){
            if (char !in 'A'..'Z' && char !in 'a'..'z') {
                return false
            }
        }
        return true
    }

    fun isValidPassword(password: String): String {


        if(password.length < 8){
            progressBar.dismiss()
            return "Password must contain at least 8 characters"
        }

        var hasUpperCase = false
        var hasLowerCase = false
        var hasNumber = false
        var hasSpecialSymbol = false

        for(char in password){
            when (char) {
                in 'A'..'Z' -> {
                    hasUpperCase = true
                }
                in 'a'..'z' -> {
                    hasLowerCase = true
                }
                in '0'..'9' -> {
                    hasNumber = true
                }
                else -> {
                    hasSpecialSymbol = true
                }
            }
        }

        if(!hasUpperCase){
            progressBar.dismiss()
            return "Password must contain at least one uppercase character"
        }
        if(!hasLowerCase){
            progressBar.dismiss()
            return "Password must contain at least one lowercase character"
        }
        if(!hasNumber){
            progressBar.dismiss()
            return "Password must contain at least one number"
        }
        if(!hasSpecialSymbol){
            progressBar.dismiss()
            return "Password must contain at least one special symbol"
        }
        return "true"
    }

    fun isValidEmail(email : String) : Boolean{
        val indexOfAt = email.indexOf('@')
        val indexOfDot = email.lastIndexOf('.')
        return indexOfDot != -1 && indexOfAt != -1 && indexOfDot > indexOfAt
    }

    private fun replaceFrag(fragment : Fragment,name: String){
        val fm : FragmentManager = parentFragmentManager
        val ft : FragmentTransaction = fm.beginTransaction()
        ft.addToBackStack(name)
        ft.add(R.id.container, fragment)
        ft.commit()
    }
    fun signupotpVerificationFrag(){
        val signupotpVerificationFrag = Verification_signUp_fragment()
        replaceFrag(signupotpVerificationFrag,"SignUpOtpPage")
    }
}