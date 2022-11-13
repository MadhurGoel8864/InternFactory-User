package com.example.internfactory.Activities.Auth
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.internfactory.Activities.connecting
import com.example.internfactory.R
import com.example.internfactory.activity_Dashboard
import com.example.internfactory.modules.*
import com.example.internfactory.server.RetrofitApi
import com.example.internfactory.server.ServiceBuilder
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Verification_signUp_fragment : Fragment() {
    lateinit var progressBar:AlertDialog

    private lateinit var otp_input : TextInputEditText
    private lateinit var otp_cont : TextInputLayout
    private lateinit var otp_btn : Button

    private lateinit var resend_btn:TextView
    lateinit var timer: CountDownTimer


    lateinit var verifybtn: Button

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
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_verification_sign_up_fragment, container, false)

        val fm : FragmentManager = parentFragmentManager
        val ft : FragmentTransaction = fm.beginTransaction()


        resend_btn = view.findViewById(R.id.resendSignup_btn)
        timer = object : CountDownTimer(30000,1000){
            override fun onTick(millisUntilFinished: Long) {
                val x = millisUntilFinished/1000
                resend_btn.text = "You can resend OTP in "+ "${x.toString()}" + " sec"
                resend_btn.isEnabled = false
            }

            override fun onFinish() {
                resend_btn.text = "Resend OTP"
                resend_btn.isEnabled = true
            }
        }

//        verifybtn=view.findViewById(R.id.button2n)
//        verifybtn.setOnClickListener {
//            val verifyOtp = VerifyOtp((activity as connecting).signUpEmail,otp_input.text.toString())
//            Log.d("Naman", "Hello")
//            Log.d("Naman", (activity as connecting).signUpEmail)
//            val retrofitApi = ServiceBuilder.buildService(RetrofitApi::class.java)
//            val call = retrofitApi.verifyotp(verifyOtp)
//
//            call.enqueue(object: Callback<VerifyOtpResponse> {
//                override fun onResponse(call: Call<VerifyOtpResponse>, response: Response<VerifyOtpResponse>){
//                    if (response.code()==200){
//                        Toast.makeText(view.context, response.body()?.toString(), Toast.LENGTH_SHORT).show()
//                        Log.i("Naman", response.body().toString())
//
//                        val user = User(null, null, (activity as connecting).signUpEmail,(activity as connecting).signuppass)
//                        val call2 = retrofitApi.login(user)
//
//                        call2.enqueue(object : Callback<LoginResponse> {
//                            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
//                                if (response.code() == 200) {
//                                    Toast.makeText(view?.context, "Otp Verified", Toast.LENGTH_SHORT).show()
////                                    runBlocking { view?.let { UserDetails(it.context).storeUserData(
////                                        LogInInfo(response.body()?.authToken.toString(),true)
////                                    ) } }
//
//                                    GlobalScope.launch(Dispatchers.IO) {
//                                        val dataStoreManager = UserDetails(view.context)
//                                        dataStoreManager.storeUserData(LogInInfo(response.body()?.authToken.toString(), true))
//                                    }
//
//                                    requireActivity().run{
//                                        startActivity(
//                                            Intent(context,
//                                                activity_Dashboard::class.java)
//                                        )
//                                        finish()
//                                    }
//                                    Log.i("Naman", response.code().toString().toString())
//                                } else {
//                                    Toast.makeText(view?.context, "Incorrect otp", Toast.LENGTH_SHORT).show()
//
//                                }
//                            }
//
//                            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
//                                Toast.makeText(
//                                    view?.context,
//                                    "Please check your internet connection",
//                                    Toast.LENGTH_SHORT
//                                ).show()
//                                Log.i("Naman", "Please check your internet connection")
//                            }
//                        })
//                    }
//
//                    else{
//                        Toast.makeText(view.context, (activity as connecting).signUpEmail,Toast.LENGTH_SHORT).show()
//                    }
//                }
//                override fun onFailure(call: Call<VerifyOtpResponse>, t:Throwable){
//                    Toast.makeText(view.context, "Failed", Toast.LENGTH_LONG).show()
//                }
//            })
//        }
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        otp_input = requireView().findViewById(R.id.otp_inputn)
        otp_btn = requireView().findViewById(R.id.button2n)
        otp_cont = requireView().findViewById(R.id.otp_contn)

        progressBar = getDialogueProgressBar(view).create()
        progressBar.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        progressBar.setCanceledOnTouchOutside(false)


        otp_btn.setOnClickListener{
            progressBar.show()
            if(otp_input.text.toString()==""){
                otp_cont.helperText = "Required"
            }
            else if(otp_input.text?.length!! <6){
                otp_cont.helperText= "Pease enter 6 digit OTP."
            }
            else{
                val verifyOtp = VerifyOtp((activity as connecting).signUpEmail,otp_input.text.toString())
                Log.d("Naman", "Hello")
                Log.d("Naman", (activity as connecting).signUpEmail)
                val retrofitApi = ServiceBuilder.buildService(RetrofitApi::class.java)
                val call = retrofitApi.verifyotp(verifyOtp)

                call.enqueue(object: Callback<VerifyOtpResponse> {
                    override fun onResponse(call: Call<VerifyOtpResponse>, response: Response<VerifyOtpResponse>){
                        if (response.code()==200){
                            Toast.makeText(view.context, "Welcome", Toast.LENGTH_SHORT).show()
                            Log.i("Naman", response.body().toString())

                            val user = User(null, null, (activity as connecting).signUpEmail,(activity as connecting).signuppass)
                            val call2 = retrofitApi.login(user)

                            call2.enqueue(object : Callback<LoginResponse> {
                                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                                    if (response.code() == 200) {
                                        Toast.makeText(view?.context, "Otp Verified", Toast.LENGTH_SHORT).show()
//                                    runBlocking { view?.let { UserDetails(it.context).storeUserData(
//                                        LogInInfo(response.body()?.authToken.toString(),true)
//                                    ) } }

                                        GlobalScope.launch(Dispatchers.IO) {
                                            val dataStoreManager = UserDetails(view.context)
                                            dataStoreManager.storeUserData(LogInInfo(response.body()?.authToken.toString(), true,(activity as connecting).signUpEmail))
                                        }


                                        requireActivity().run{
                                            startActivity(
                                                Intent(context,
                                                    activity_Dashboard::class.java)
                                            )
                                            finish()
                                        }
                                        Log.i("Naman", response.code().toString().toString())
                                        progressBar.dismiss()
                                    } else {
                                        Toast.makeText(view?.context, "Incorrect otp", Toast.LENGTH_SHORT).show()
                                        progressBar.dismiss()

                                    }
                                }

                                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                                    Toast.makeText(
                                        view?.context,
                                        "Please check your internet connection",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    Log.i("Naman", "Please check your internet connection")
                                    progressBar.dismiss()
                                }
                            })
                        }

                        else{
                            Toast.makeText(view.context, "Invalid Otp",Toast.LENGTH_SHORT).show()
                            progressBar.dismiss()
                        }
                    }
                    override fun onFailure(call: Call<VerifyOtpResponse>, t:Throwable){
                        Toast.makeText(view.context, "Failed", Toast.LENGTH_LONG).show()
                        progressBar.dismiss()
                    }
                })

            }
        }

        resend_btn.setOnClickListener {
            progressBar.show()
            val email = Email((activity as connecting).signUpEmail.toString())
            val retrofitApi = ServiceBuilder.buildService(RetrofitApi::class.java)
            val call = retrofitApi.forgotPassword(email)

            call.enqueue(object : Callback<ForgotPassResponse> {
                override fun onResponse(
                    call: Call<ForgotPassResponse>,
                    response: Response<ForgotPassResponse>
                ) {
                    if (response.code() == 200) {
                        Toast.makeText(view.context, "OTP Sent To Registered Email", Toast.LENGTH_SHORT).show()
                        Log.i("Naman", response.body().toString())
                        progressBar.dismiss()
                    } else {
                        Toast.makeText(view.context,"Incorrect Password", Toast.LENGTH_SHORT)
                            .show()
                        Log.i("Naman", response.body().toString())
                        progressBar.dismiss()
                    }
                }

                override fun onFailure(call: Call<ForgotPassResponse>, t: Throwable) {
                    Toast.makeText(view.context, "Failed", Toast.LENGTH_LONG).show()
                    progressBar.dismiss()
                }
            })
        }
//        otp_input.addTextChangedListener(object: TextWatcher {
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//            }
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//            }
//            override fun afterTextChanged(p0: Editable?) {
//                if(otp_input.text?.length == 6){
//                    otp_btn.isEnabled = true
//                    otp_cont.helperText = null
//                }
//                else{
//                    otp_btn.isEnabled = false
//                    otp_cont.helperText = "Required"
//
//                }
//            }
//        })
    }

    private fun replaceFrag(fragment : Fragment,name: String){
        val fm : FragmentManager = parentFragmentManager
        val ft : FragmentTransaction = fm.beginTransaction()
        ft.addToBackStack(name)
        ft.add(R.id.container, fragment)
        ft.commit()
    }

    fun reset_pass(){
        val reset_pass = ResetPassword_Fragment()
        replaceFrag(reset_pass,"reset_pass")
    }
}
