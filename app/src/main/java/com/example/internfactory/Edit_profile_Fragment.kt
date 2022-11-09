package com.example.internfactory

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.widget.AdapterView.OnItemSelectedListener
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.internfactory.modules.*
import com.example.internfactory.server.RetrofitApi
import com.example.internfactory.server.ServiceBuilder
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Edit_profile_Fragment : Fragment(),OnItemSelectedListener {

    private lateinit var first_name_cont: TextInputLayout
    private lateinit var last_name_cont: TextInputLayout
    private lateinit var email_cont: TextInputLayout
    private lateinit var phone_no_cont: TextInputLayout

    private lateinit var firstname_inp: TextInputEditText
    private lateinit var lastname_inp: TextInputEditText
    private lateinit var emailinp: TextInputEditText
    private lateinit var phoneno_inp: TextInputEditText
    private lateinit var gender: Spinner
    var gen: String = ""
    private var email: String = ""
    private lateinit var submit_button: Button


    var dropDownList = arrayOf<String>("Male", "Female")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_edit_profile_, container, false)

        firstname_inp = view.findViewById(R.id.first_name_inp)
        lastname_inp = view.findViewById(R.id.last_name_inp)
        emailinp = view.findViewById(R.id.email_inp)
        phoneno_inp = view.findViewById(R.id.phone_no_inp)
        gender = view.findViewById(R.id.statusfiller)
        submit_button = view.findViewById(R.id.submit_bn)

        GlobalScope.launch(Dispatchers.IO) {
            val userDetails = UserDetails(view.context)
            userDetails.getToken().collect {
                email = it.signInemail
                apicalling()
                Log.d("naman", email)
            }
        }

        val spin = view.findViewById<Spinner>(R.id.statusfiller)
        spin.onItemSelectedListener = this
//        val ad = ArrayAdapter<Any?>(this,android.R.layout.simple_spinner_item,dropDownList)
        val ad = activity.let {
            ArrayAdapter(
                it!!.applicationContext,
                android.R.layout.simple_spinner_item,
                dropDownList
            )
        }

        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spin.adapter = ad
        val fm: FragmentManager = parentFragmentManager
        val ft: FragmentTransaction = fm.beginTransaction()


//        first_name_cont = requireView().findViewById(R.id.first_name_cont)

        first_name_cont = view.findViewById(R.id.first_name_cont)
        last_name_cont = view.findViewById(R.id.last_name_cont)
        email_cont = view.findViewById(R.id.email_cont)
        phone_no_cont = view.findViewById(R.id.phone_no_cont)

        firstname_inp = view.findViewById(R.id.first_name_inp)
        lastname_inp = view.findViewById(R.id.last_name_inp)
        emailinp = view.findViewById(R.id.email_inp)
        phoneno_inp = view.findViewById(R.id.phone_no_inp)

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

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        gen = dropDownList[position]
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }


    private fun validnumber(): String? {
        val number_input = phoneno_inp.text.toString()
        if (number_input == "")
            return "Required"
        if (number_input.length != 10)
            return "Number must have 10 digits"
        return null
    }

    private fun validemail(): String? {
        val email_text = emailinp.text.toString()
        if (email_text == "") {
            return "Required"
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email_text).matches()) {
            return "Invalid Email Address"
        }
        return null
    }

    private fun validfirstname(): String? {
        val txt = firstname_inp.text.toString()
        if (txt == "") {
            return "Required"
        }
        if (txt.contains(Regex("[1234567890]"))) {
            return "Must contain only alphabets"
        }
        return null
    }

    private fun validlastname(): String? {
        val txt = lastname_inp.text.toString()
        if (txt.contains(Regex("[1234567890]"))) {
            return "Must contain only alphabets"
        }
        return null
    }

    private fun replaceFrag(fragment: Fragment, name: String) {
        val fm: FragmentManager = parentFragmentManager
        val ft: FragmentTransaction = fm.beginTransaction()
        ft.addToBackStack(name)
        ft.add(R.id.container, fragment)
        ft.commit()
    }

    fun apicalling() {
        submit_button.setOnClickListener {
            val newemail = firstname_inp.text.toString()
            val firstname = firstname_inp.text.toString()
            val lastname = lastname_inp.text.toString()
//        val gen=gender.selectedItem.toString()
            val mobile = phoneno_inp.text.toString()

            val viewProfile = ViewProfile(email, firstname, lastname, gen, newemail, mobile)
            val retrofitAPI = ServiceBuilder.buildService(RetrofitApi::class.java)
            val call = retrofitAPI.editProfile(viewProfile, "Bearer " + (activity as activity_Dashboard).token)

            Log.d("bansal",(activity as activity_Dashboard).token)
            call.enqueue(object : Callback<ViewProfileResponse> {
                override fun onResponse(
                    call: Call<ViewProfileResponse>,
                    response: Response<ViewProfileResponse>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        Toast.makeText(view?.context, "Succesfully Saved", Toast.LENGTH_SHORT)
                            .show()
                        Log.i("Naman", response.body().toString())
                    } else {
                        android.widget.Toast.makeText(view?.context, response.code().toString(), android.widget.Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ViewProfileResponse>, t: Throwable) {
                    Toast.makeText(view?.context, "Please check your internet connection", Toast.LENGTH_SHORT).show()
                    Log.i("Naman", "Please check your internet connection")
                }
            })
        }
    }
}