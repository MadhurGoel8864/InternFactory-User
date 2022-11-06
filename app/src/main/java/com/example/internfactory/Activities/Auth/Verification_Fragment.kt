package com.example.internfactory.Activities.Auth
import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
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
import com.example.internfactory.modules.Email
import com.example.internfactory.modules.VerifyOtp
import com.example.internfactory.modules.VerifyOtpResponse
import com.example.internfactory.server.RetrofitApi
import com.example.internfactory.server.ServiceBuilder
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Verification_Fragment : Fragment() {
private lateinit var otp_input : TextInputEditText
private lateinit var otp_cont : TextInputLayout
private lateinit var otp_btn : Button


private lateinit var reset_otp:TextView
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
        val view= inflater.inflate(R.layout.fragment_verification_, container, false)

        val fm : FragmentManager = parentFragmentManager
        val ft : FragmentTransaction = fm.beginTransaction()

//        reset_otp=view.findViewById(R.id.textView8)
//        reset_otp.setOnClickListener()

//        verifybtn=view.findViewById(R.id.button2)
//        verifybtn.setOnClickListener {
//            val verifyOtp = VerifyOtp((activity as connecting).email,otp_input.text.toString())
//            Log.d("Naman", "Hello")
//            Log.d("Naman", (activity as connecting).email)
//            val retrofitApi = ServiceBuilder.buildService(RetrofitApi::class.java)
//            val call = retrofitApi.verifyotp(verifyOtp)
//
//            call.enqueue(object: Callback<VerifyOtpResponse> {
//                override fun onResponse(call: Call<VerifyOtpResponse>, response: Response<VerifyOtpResponse>){
//                    if (response.code()==200){
//                        Toast.makeText(view.context, "Otp Verified", Toast.LENGTH_SHORT).show()
//                        reset_pass()
//                        Log.i("Naman", response.body().toString())
//                    }
//                    else{
//                        Toast.makeText(view.context, "Incorrect otp",Toast.LENGTH_SHORT).show()
//                    }
//                }
//                override fun onFailure(call: Call<VerifyOtpResponse>, t:Throwable){
//                    Toast.makeText(view.context, "Failed", Toast.LENGTH_LONG).show()
//                }
//            })

        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        otp_input = requireView().findViewById(R.id.otp_input)
        otp_btn = requireView().findViewById(R.id.button2)
        otp_cont = requireView().findViewById(R.id.otp_cont)

        val progressBar = getDialogueProgressBar(view).create()
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
                val verifyOtp = VerifyOtp((activity as connecting).email,otp_input.text.toString())
                Log.d("Naman", "Hello")
                Log.d("Naman", (activity as connecting).email)
                val retrofitApi = ServiceBuilder.buildService(RetrofitApi::class.java)
                val call = retrofitApi.verifyotp(verifyOtp)

                call.enqueue(object: Callback<VerifyOtpResponse> {
                    override fun onResponse(call: Call<VerifyOtpResponse>, response: Response<VerifyOtpResponse>){
                        if (response.code()==200){
                            Toast.makeText(view.context, "Otp Verified", Toast.LENGTH_SHORT).show()
                            reset_pass()
                            Log.i("Naman", response.body().toString())
                            progressBar.dismiss()
                        }
                        else{
                            Toast.makeText(view.context, "Incorrect otp",Toast.LENGTH_SHORT).show()
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
