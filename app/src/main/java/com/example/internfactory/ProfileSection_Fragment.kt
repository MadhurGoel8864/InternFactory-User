package com.example.internfactory

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.internfactory.Activities.Auth.Home_page
import com.example.internfactory.modules.LogInInfo
import com.example.internfactory.modules.UserDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ProfileSection_Fragment : Fragment() {

    lateinit var signOutBtn : TextView

    fun signOut(view: View){
        GlobalScope.launch(Dispatchers.IO) {
            val userDetails = UserDetails(view.context)
            userDetails.storeUserData(LogInInfo("", false,""))
        }
        val intent = Intent(view.context, Home_page::class.java)
        startActivity(intent)
        activity?.finish()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_profile_section_, container, false)

        signOutBtn = view.findViewById(R.id.signOut_btn)
        signOutBtn.setOnClickListener{
            signOut(view)
        }
        return view
    }

//    lateinit var edit_profile_btn : TextView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        edit_profile_btn = view.findViewById(R.id.edit_profile_btn)
//        edit_profile_btn.setOnClickListener{

//            requireActivity().run{
//                startActivity(Intent(this, activity_EditProfile::class.java))
//                finish()
//            }
//        }

    }

    private fun replaceFrag(fragment : Fragment,name: String){
        val fm : FragmentManager = parentFragmentManager
        val ft : FragmentTransaction = fm.beginTransaction()
        ft.addToBackStack(name)
        ft.add(R.id.container, fragment)
        ft.commit()
    }
}