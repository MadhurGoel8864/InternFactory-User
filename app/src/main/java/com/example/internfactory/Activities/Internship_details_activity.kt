package com.example.internfactory.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.internfactory.CategoriesSeeAll
import com.example.internfactory.DashBoard_Fragment
import com.example.internfactory.R

class Internship_details_activity : AppCompatActivity() {

    var count=1;

    private fun replaceFrag(fragment : Fragment, name: String){
        val fm : FragmentManager =supportFragmentManager
        val ft : FragmentTransaction = fm.beginTransaction()
        ft.addToBackStack(name)
        ft.add(R.id.internship_det, fragment)
        ft.commit()
    }

    fun application_fragment(view:View){
        val applicationFrag = application_fragment()
        replaceFrag(applicationFrag,"application")
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_internship_details)


        val fm : FragmentManager = supportFragmentManager
        val ft : FragmentTransaction = fm.beginTransaction()
//        val logInFragment = DashBoard_Fragment()
        val details_fragment = internship_deatils_fragement()
        ft.add(R.id.internship_det, details_fragment)
        ft.commit()

        val actionBar = supportActionBar
        actionBar?.setDisplayShowHomeEnabled(false)
        actionBar?.setDisplayShowTitleEnabled(false)

    }
}