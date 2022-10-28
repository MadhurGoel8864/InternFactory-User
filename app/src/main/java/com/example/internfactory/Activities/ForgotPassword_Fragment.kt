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

class ForgotPassword_Fragment : Fragment() {
    private lateinit var email_inp: TextInputEditText
    private lateinit var email_cont: TextInputLayout
    private lateinit var button: Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forgot_password_, container, false)
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
            return "Invalid Email Address"
        }
        return null
    }
}
//        email_inp.addTextChangedListener(object: TextWatcher {
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//            }
//
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//            }
//
//            override fun afterTextChanged(p0: Editable?) {
//                if (android.util.Patterns.EMAIL_ADDRESS.matcher(email_inp.text.toString()).matches()){
//                    button.isEnabled = true
//                }
//            }
//        })
