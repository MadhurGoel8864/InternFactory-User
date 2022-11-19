package com.example.internfactory.Activities

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import coil.load
import com.example.internfactory.R
import com.example.internfactory.activity_Dashboard
import com.example.internfactory.modules.*
import com.example.internfactory.server.RetrofitApi
import com.example.internfactory.server.ServiceBuilder
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class application_fragment : Fragment() {

    private lateinit var logo: ImageView
    private lateinit var displayName: TextView
    private lateinit var companyName: TextView
    private lateinit var applierName: TextView
    private lateinit var applierEmail: TextView

    private lateinit var whyshould: TextInputEditText
    private lateinit var shareyourwork: TextInputEditText
    private lateinit var workedinteam: TextInputEditText
    private lateinit var strength: TextInputEditText
    private lateinit var weakness: TextInputEditText
    private lateinit var hobbies: TextInputEditText

    private lateinit var whyshouldq: TextView
    private lateinit var shareyourworkq: TextView
    private lateinit var workedinteamq: TextView
    private lateinit var strengthq: TextView
    private lateinit var weaknessq: TextView
    private lateinit var hobbiesq: TextView
    private lateinit var button: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_application_fragment, container, false)

        val fm: FragmentManager = parentFragmentManager
        val ft: FragmentTransaction = fm.beginTransaction()

        logo = view.findViewById(R.id.imageView112)
        displayName = view.findViewById(R.id.textView17)
        companyName = view.findViewById(R.id.textView18)
        applierName = view.findViewById(R.id.Applier_Name)
        applierEmail = view.findViewById(R.id.Applier_email)

        whyshould = view.findViewById(R.id.ques1_text)
        shareyourwork = view.findViewById(R.id.ques2_text)
        workedinteam = view.findViewById(R.id.ques3_text)
        strength = view.findViewById(R.id.ques4_text)
        weakness = view.findViewById(R.id.ques5_text)
        hobbies = view.findViewById(R.id.ques6_text)

        whyshouldq = view.findViewById(R.id.ques1)
        shareyourworkq = view.findViewById(R.id.ques2)
        workedinteamq = view.findViewById(R.id.ques3)
        strengthq = view.findViewById(R.id.ques4)
        weaknessq = view.findViewById(R.id.ques5)
        hobbiesq = view.findViewById(R.id.ques6)
        button = view.findViewById(R.id.btn)

//        val applyinternrequest= Applyinternrequest()
//        applyinternrequest.questions.get(0).question
//        applyinternrequest.questions.get(1).answer
        val retrofitAPI = ServiceBuilder.buildService(RetrofitApi::class.java)
        val call = retrofitAPI.internshipquestion(
            (activity as activity_Dashboard).internshipId,
            "Bearer " + (activity as activity_Dashboard).token
        )

        call.enqueue(object : Callback<ApplyInternship_response> {
            override fun onResponse(
                call: Call<ApplyInternship_response>,
                response: Response<ApplyInternship_response>
            ) {
                if (response.isSuccessful) {

                    whyshouldq.text = response.body()?.questions?.get(0)?.question.toString()
                    shareyourworkq.text = response.body()?.questions?.get(1)?.question.toString()
                    workedinteamq.text = response.body()?.questions?.get(2)?.question.toString()
                    strengthq.text = response.body()?.questions?.get(3)?.question.toString()
                    weaknessq.text = response.body()?.questions?.get(4)?.question.toString()
                    hobbiesq.text = response.body()?.questions?.get(5)?.question.toString()

                    val call2 = retrofitAPI.internshipDetail(
                        (activity as activity_Dashboard).internshipId,
                        "Bearer " + (activity as activity_Dashboard).token
                    )

                    call2.enqueue(object : Callback<InternshipDetail_response> {
                        override fun onResponse(
                            call: Call<InternshipDetail_response>,
                            response: Response<InternshipDetail_response>
                        ) {
                            if (response.isSuccessful) {
                                val x =
                                    "https://internfactory.herokuapp.com/file/" + response.body()?.imageUrl
                                logo.load(x)
                                displayName.text = response.body()?.displayName
                                companyName.text = response.body()?.provider
                                applierName.text = (activity as activity_Dashboard).name
                                applierEmail.text = (activity as activity_Dashboard).email
                            } else {
                                Toast.makeText(
                                    view?.context,
                                    response.code().toString(),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }

                        override fun onFailure(
                            call: Call<InternshipDetail_response>,
                            t: Throwable
                        ) {
                            Toast.makeText(
                                view?.context,
                                "Please Check Your Internet Connection",
                                Toast.LENGTH_SHORT
                            ).show()

                        }
                    })

//                    button.setOnClickListener {

//                        val submission=Applyinternrequest(applyinternrequest)
//                        val retrofitAPI = ServiceBuilder.buildService(RetrofitApi::class.java)
//                        val call = retrofitAPI.applyinternship((activity as activity_Dashboard).email
//                            ,(activity as activity_Dashboard).internshipId
//                            ,submission
//                            ,"Bearer " + (activity as activity_Dashboard).token)
//
//                        call.enqueue(object : Callback<Applyinternresponse> {
//                            override fun onResponse(
//                                call: Call<Applyinternresponse>,
//                                response: Response<Applyinternresponse>
//                            ) {
//                                if (response.isSuccessful) {
//                                    android.widget.Toast.makeText(view?.context, response.code().toString(), android.widget.Toast.LENGTH_SHORT).show()
//                                } else {
//                                    android.widget.Toast.makeText(view?.context, response.code().toString(), android.widget.Toast.LENGTH_SHORT).show()
//                                }
//                            }
//
//                            override fun onFailure(call: Call<Applyinternresponse>, t: Throwable) {
//                                Toast.makeText(view?.context,"Please check your internet connection", Toast.LENGTH_SHORT).show()
//                                Log.i("Naman", t.stackTraceToString())
//                            }
//                        })
//                    }
//                }
//                else {
//                    Toast.makeText(view?.context, response.code().toString(), Toast.LENGTH_SHORT).show()
//                }
//            }
//
//            override fun onFailure(call: Call<ApplyInternship_response>, t: Throwable) {
//                Toast.makeText(
//                    view?.context,
//                    "Please Check Your Internet Connection",
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
//        })


                }
            }

            override fun onFailure(call: Call<ApplyInternship_response>, t: Throwable) {
                Toast.makeText(
                    view?.context,
                    "Please Check Your Internet Connection",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
        return view
    }

}

//    private fun replaceFrag(fragment : Fragment, name: String){
//        val fm : FragmentManager = parentFragmentManager
//        val ft : FragmentTransaction = fm.beginTransaction()
//        ft.addToBackStack(name)
//        ft.add(R.id.dashboard, fragment)
//        ft.commit()
//    }
//
//    fun appliedInternship(){
//        val appliedInternship = Applied_Internships_Fragment()
//        replaceFrag(appliedInternship,"appliedInternship")
//    }
//}