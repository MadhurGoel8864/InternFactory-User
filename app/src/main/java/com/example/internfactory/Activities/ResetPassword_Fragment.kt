package com.example.internfactory.Activities

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.internfactory.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class ResetPassword_Fragment : Fragment() {

    private lateinit var pass_input: TextInputEditText
    private lateinit var pass : TextInputLayout
    private lateinit var conf_pass : TextInputLayout
    private lateinit var conf_pass_inp : TextInputEditText
    private lateinit var reset_btn : Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragement_reset_password, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pass_input = requireView().findViewById(R.id.password_form_inp)
        pass = requireView().findViewById(R.id.password_inp)
        conf_pass= requireView().findViewById(R.id.conf_pass)
        conf_pass_inp= requireView().findViewById(R.id.conf_password_form_inp)
        reset_btn= requireView().findViewById(R.id.reset_btn)

        pass_input.addTextChangedListener {
            pass.helperText = validPass()
            reset_btn.isEnabled = (pass.helperText == null) and (conf_pass.helperText == null)
        }

        conf_pass_inp.addTextChangedListener {
            if(conf_pass_inp.text.toString() == pass_input.text.toString()){
                conf_pass.helperText = null
                reset_btn.isEnabled = true
            }
            else{
                conf_pass.helperText = "Password and Confirm Password Must be Same"
                reset_btn.isEnabled = false
            }
        }
    }

    private fun validPass(): String? {
        val pass_txt = pass_input.text.toString()
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


}