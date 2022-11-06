package com.example.internfactory

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Profile
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.internfactory.Activities.Auth.SignIn_Fragment
import com.example.internfactory.Activities.search_page
import com.google.android.material.bottomnavigation.BottomNavigationView

class activity_Dashboard : AppCompatActivity() {

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

    fun categoryFrag(view : View){
        val categoryFrag = CategoriesSeeAll()
        replaceFrag(categoryFrag,"category")
    }

    fun dashboardFrag(view : View){
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
                R.id.Applied -> replaceFrag(CategoriesSeeAll(),"Applied")
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