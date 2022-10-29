package com.example.internfactory.Activities

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.widget.addTextChangedListener
import com.example.internfactory.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class SignUp_Fragment : Fragment() {
    private lateinit var firstname_inp : TextInputEditText
    private lateinit var lastname_inp : TextInputEditText
    private lateinit var email_inp : TextInputEditText
    private lateinit var password_inp : TextInputEditText
    private lateinit var firstname : TextInputLayout
    private lateinit var lastname : TextInputLayout
    private lateinit var email_box : TextInputLayout
    private lateinit var password : TextInputLayout
    private lateinit var button : Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_up_, container, false)
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

        email_inp.addTextChangedListener {
            email_box.helperText = validemail()
            button.isEnabled =
                (email_box.helperText == null) and (password.helperText == null) and (firstname.helperText == null)
        }
        password_inp.addTextChangedListener {
            password.helperText = validPass()
            button.isEnabled =
                (email_box.helperText == null) and (password.helperText == null) and (firstname.helperText == null)
        }
        firstname_inp.addTextChangedListener {
            if(firstname_inp.text.toString() != "") {
                firstname.helperText = null
            }
            else{
                firstname.helperText = "Required"
            }
            button.isEnabled =
                (email_box.helperText == null) and (password.helperText == null) and (firstname.helperText == null)
        }
    }
    private fun validPass(): String? {
        val pass_txt = password_inp.text.toString()
        if(pass_txt.length<8){
            return "Minimum 8 characters Required"
        }
        if(!pass_txt.matches(".*[A-Z]*.".toRegex())){
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
        val email_text = email_inp.text.toString()
        if(!Patterns.EMAIL_ADDRESS.matcher(email_text).matches()){
            return "Invalid Email Address"
        }
        return null
    }
}
