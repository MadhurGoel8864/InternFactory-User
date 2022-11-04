package com.example.internfactory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class activity_EditProfile : AppCompatActivity() {

    private lateinit var first_name_cont:TextInputLayout
    private lateinit var last_name_cont:TextInputLayout
    private lateinit var email_cont:TextInputLayout
    private lateinit var phone_no_cont:TextInputLayout

    private lateinit var first_name_inp:TextInputEditText
    private lateinit var last_name_inp:TextInputEditText
    private lateinit var email_inp:TextInputEditText
    private lateinit var phone_no_inp:TextInputEditText

    private lateinit var submit_button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        first_name_cont = findViewById(R.id.first_name_cont)
        last_name_cont = findViewById(R.id.last_name_cont)
        email_cont = findViewById(R.id.email_cont)
        phone_no_cont = findViewById(R.id.phone_no_cont)

        first_name_inp = findViewById(R.id.first_name_inp)
        last_name_inp = findViewById(R.id.last_name_inp)
        email_inp = findViewById(R.id.email_inp)
        phone_no_inp = findViewById(R.id.phone_no_inp)

        submit_button = findViewById(R.id.submit_bn)

        submit_button.setOnClickListener{
            first_name_cont.helperText = validfirstname()
            last_name_cont.helperText = validlastname()
            email_cont.helperText = validemail()
            phone_no_cont.helperText = validnumber()
        }
    }

    private fun validnumber():String?{
        val number_input = phone_no_inp.text.toString()
        if(number_input=="")
            return "Required"
        if(number_input.length!=10)
            return "Number must have 10 digits"
        return null
    }

    private fun validemail(): String? {
        val email_text = email_inp.text.toString()
        if(email_text==""){
            return "Required"
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email_text).matches()){
            return "Invalid Email Address"
        }
        return null
    }

    private fun validfirstname():String?{
        val txt = first_name_inp.text.toString()
        if(txt==""){
            return "Required"
        }
        if(txt.contains(Regex("[1234567890]"))){
            return "Must contain only alphabets"
        }
        return null
    }
    private fun validlastname():String?{
        val txt = last_name_inp.text.toString()
        if(txt.contains(Regex("[1234567890]"))){
            return "Must contain only alphabets"
        }
        return null
    }







}
