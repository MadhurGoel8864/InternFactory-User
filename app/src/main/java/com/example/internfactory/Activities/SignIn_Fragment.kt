package com.example.internfactory.Activities

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.widget.AppCompatButton
import androidx.core.widget.addTextChangedListener
import com.example.internfactory.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


class SignIn_Fragment : Fragment() {

    private lateinit var emailin: EditText
    private lateinit var passwordin:EditText
    private lateinit var buttonin:Button



    private lateinit var password_text : TextInputEditText
    private lateinit var password_cont : TextInputLayout
    private lateinit var ed1 : TextInputEditText
    private lateinit var email_cont : TextInputLayout
    private lateinit var login_btn : Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_in_, container, false)
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