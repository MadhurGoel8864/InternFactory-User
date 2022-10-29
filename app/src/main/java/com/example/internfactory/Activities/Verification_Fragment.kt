package com.example.internfactory.Activities
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.example.internfactory.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class Verification_Fragment : Fragment() {
private lateinit var otp_input : TextInputEditText
private lateinit var otp_cont : TextInputLayout
private lateinit var otp_btn : Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_verification_, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        otp_input = requireView().findViewById(R.id.otp_input)
        otp_btn = requireView().findViewById(R.id.button2)
        otp_cont = requireView().findViewById(R.id.otp_cont)
        otp_input.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun afterTextChanged(p0: Editable?) {
                if(otp_input.text?.length == 6){
                    otp_btn.isEnabled = true
                    otp_cont.helperText = null
                }
                else{
                    otp_btn.isEnabled = false
                    otp_cont.helperText = "Required"

                }
//                    (otp_input.text?.isNotEmpty() == true)
            }
        })



    }
}