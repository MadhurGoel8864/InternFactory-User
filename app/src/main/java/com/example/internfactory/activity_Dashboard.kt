package com.example.internfactory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class activity_Dashboard : AppCompatActivity() {

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        val fm : FragmentManager = supportFragmentManager
        val ft : FragmentTransaction = fm.beginTransaction()
        val logInFragment = trendingSeeAll()
        ft.add(R.id.dashboard, logInFragment)
        ft.commit()

        val actionBar = supportActionBar
        actionBar?.setDisplayShowHomeEnabled(false)
        actionBar?.setDisplayShowTitleEnabled(false)
    }
}