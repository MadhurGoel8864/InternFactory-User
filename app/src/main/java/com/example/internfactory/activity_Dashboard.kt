package com.example.internfactory

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Profile
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.internfactory.Activities.All_internship_list_fragment
import com.example.internfactory.Activities.Applied_Internships_Fragment
import com.example.internfactory.Activities.Auth.SignIn_Fragment
import com.example.internfactory.Activities.search_page
import com.google.android.material.bottomnavigation.BottomNavigationView

class activity_Dashboard : AppCompatActivity() {

    lateinit var email:String

    private lateinit var bottom_nav: BottomNavigationView

    var count=1;

    private fun replaceFrag(fragment : Fragment, name: String){
        val fm : FragmentManager =supportFragmentManager
        val ft : FragmentTransaction = fm.beginTransaction()
        ft.addToBackStack(name)
        ft.add(R.id.dashboard, fragment)
        ft.commit()
    }

    fun trendingFrag(view : View){
        val trendingFrag = trendingSeeAll()
        replaceFrag(trendingFrag,"trending")
    }

    fun profileFrag(view:View){
        val Profile_Frag =EditProfile_Fragment()
        replaceFrag(Profile_Frag,"profile")
    }

    fun editprofileFrag(view:View){
        val editProfile_Frag =Edit_profile_Fragment()
        replaceFrag(editProfile_Frag,"editprofile")
    }

    fun all_internship_list_Frage(view: View){
        val all_inten_list =All_internship_list_fragment()
        replaceFrag(all_inten_list,"editprofile")
    }

    fun aboutFrag(view:View){
        Log.d("Hello","about page")
        val aboutfrag = About_Fragment()
        replaceFrag(aboutfrag,"about")
    }

    fun helpFrag(view:View){
        val helpfrag = Help_Fragment()
        replaceFrag(helpfrag,"help")
    }
    fun categoryFrag(view : View){
        val categoryFrag = CategoriesSeeAll()
        replaceFrag(categoryFrag,"category")
    }

    fun dashboardFrag(view : View){
        val fm : FragmentManager = supportFragmentManager
        if(fm.backStackEntryCount>0){
            fm.popBackStackImmediate()
            return
        }

        val dashboardFrag = DashBoard_Fragment()
        replaceFrag(dashboardFrag,"mainScreen")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        bottom_nav = findViewById(R.id.bottomnavbar)
        bottom_nav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.Home ->  replaceFrag(DashBoard_Fragment(),"Home")
                R.id.Profile -> replaceFrag(ProfileSection_Fragment(),"Profile")
                R.id.Search -> replaceFrag(search_page(),"Search")
                R.id.Applied -> replaceFrag(Applied_Internships_Fragment(),"Applied")
                else->{
                }
            }
            true
        }
        val fm : FragmentManager = supportFragmentManager
        val ft : FragmentTransaction = fm.beginTransaction()
        val logInFragment = DashBoard_Fragment()
        ft.add(R.id.dashboard, logInFragment)
        ft.commit()

        val actionBar = supportActionBar
        actionBar?.setDisplayShowHomeEnabled(false)
        actionBar?.setDisplayShowTitleEnabled(false)
    }
}