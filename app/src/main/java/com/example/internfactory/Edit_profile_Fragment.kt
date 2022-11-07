package com.example.internfactory

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class Edit_profile_Fragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    private lateinit var first_name_cont: TextInputLayout
    private lateinit var last_name_cont: TextInputLayout
    private lateinit var email_cont: TextInputLayout
    private lateinit var phone_no_cont: TextInputLayout

    private lateinit var first_name_inp: TextInputEditText
    private lateinit var last_name_inp: TextInputEditText
    private lateinit var email_inp: TextInputEditText
    private lateinit var phone_no_inp: TextInputEditText

    private lateinit var submit_button: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
                val view = inflater.inflate(R.layout.fragment_edit_profile_, container, false)

        val fm : FragmentManager = parentFragmentManager
        val ft : FragmentTransaction = fm.beginTransaction()


//        first_name_cont = requireView().findViewById(R.id.first_name_cont)

        first_name_cont = view.findViewById(R.id.first_name_cont)
        last_name_cont = view.findViewById(R.id.last_name_cont)
        email_cont = view.findViewById(R.id.email_cont)
        phone_no_cont = view.findViewById(R.id.phone_no_cont)

        first_name_inp = view.findViewById(R.id.first_name_inp)
        last_name_inp = view.findViewById(R.id.last_name_inp)
        email_inp = view.findViewById(R.id.email_inp)
        phone_no_inp = view.findViewById(R.id.phone_no_inp)

//        submit_button = view.findViewById(R.id.submit_bn)
//
//        submit_button.setOnClickListener{
//            first_name_cont.helperText = validfirstname()
//            last_name_cont.helperText = validlastname()
//            email_cont.helperText = validemail()
//            phone_no_cont.helperText = validnumber()
//        }

        // Inflate the layout for this fragment
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Edit_profile_Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Edit_profile_Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
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
    private fun replaceFrag(fragment : Fragment,name: String){
        val fm : FragmentManager = parentFragmentManager
        val ft : FragmentTransaction = fm.beginTransaction()
        ft.addToBackStack(name)
        ft.add(R.id.container, fragment)
        ft.commit()
    }





}