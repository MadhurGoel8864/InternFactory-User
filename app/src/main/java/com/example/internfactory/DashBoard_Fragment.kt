package com.example.internfactory

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.internfactory.Activities.Adapters.ImageAdapter
import java.util.Locale.Category
import kotlin.math.abs

class DashBoard_Fragment : Fragment() {

    lateinit var viewpager2 : ViewPager2
    lateinit var handler: Handler
    lateinit var imageList: ArrayList<Int>
    lateinit var categ_seeall_btn: TextView
    lateinit var trending_seeall_btn: TextView

    lateinit var adapter: ImageAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_dash_board_, container, false)

//        categ_seeall_btn = view.findViewById(R.id.categories_seall)
//        categ_seeall_btn.setOnClickListener{
//            val intent = Intent(view.context,trendingSeeAll::class.java)
//            startActivity(intent)
//        }
//        trending_seeall_btn = view.findViewById(R.id.trending_seeall)
//        trending_seeall_btn.setOnClickListener{
//            val intent = Intent(view.context,CategoriesSeeAll::class.java)
//            startActivity(intent)
//        }

        init(view)
        setUptransformer()

        viewpager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(runnable)
                handler.postDelayed(runnable,2000)
            }
        })
        return view

    }
    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(runnable,2000)

    }

    private val runnable = Runnable{
        viewpager2.currentItem = viewpager2.currentItem + 1
    }

    private fun setUptransformer() {
        val transformer =  CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(40))
        transformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.14f
        }
        viewpager2.setPageTransformer(transformer)
    }

    private fun init(view: View){
        viewpager2 = view.findViewById(R.id.viewpager2)
        handler = Handler(Looper.myLooper()!!)
        imageList = ArrayList()

        imageList.add(R.drawable.img10)
        imageList.add(R.drawable.img11)
        imageList.add(R.drawable.img12)
        imageList.add(R.drawable.img6)
        imageList.add(R.drawable.img8)

        adapter = ImageAdapter(imageList,viewpager2)
        viewpager2.adapter = adapter
        viewpager2.offscreenPageLimit = 3
        viewpager2.clipToPadding = false
        viewpager2.clipChildren = false
        viewpager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

    }

    fun Dashboardconnect(view : View){
        val intent= Intent(view.context, activity_Dashboard::class.java)
        startActivity(intent)
    }
    }
