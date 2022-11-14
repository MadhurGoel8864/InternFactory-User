package com.example.internfactory

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.internfactory.Activities.Adapters.ImageAdapter
import com.example.internfactory.Activities.Adapters.category_adapter
import com.example.internfactory.modules.category_seeall_response
import com.example.internfactory.modules.trending_seeall_response
import com.example.internfactory.server.RetrofitApi
import com.example.internfactory.server.ServiceBuilder
import retrofit2.Call
import retrofit2.Response
import java.util.Locale.Category
import javax.security.auth.callback.Callback
import kotlin.math.abs

class DashBoard_Fragment : Fragment() {

    lateinit var viewpager2 : ViewPager2
    lateinit var handler: Handler
    lateinit var imageList: ArrayList<String>
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

        val serviceBuilder = ServiceBuilder.buildService(RetrofitApi::class.java)
        val Call = serviceBuilder.gettrends("Bearer " + (activity as activity_Dashboard).token )

        Call.enqueue(object : Callback,retrofit2.Callback<MutableList<trending_seeall_response>> {
            override fun onResponse(
                call: Call<MutableList<trending_seeall_response>>,
                response: Response<MutableList<trending_seeall_response>>
            ) {
                if (response.isSuccessful) {
                    for (i in response.body() as MutableList<trending_seeall_response>){
                        val url = "https://internfactory.herokuapp.com/file/"+i.imageName
                        imageList.add(url)
                    }
                    adapter = ImageAdapter(imageList,viewpager2)
                    viewpager2.adapter = adapter
                    viewpager2.offscreenPageLimit = 3
                    viewpager2.clipToPadding = false
                    viewpager2.clipChildren = false
                    viewpager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
                    }
                    Log.e("success", response.body().toString())
                }
            override fun onFailure(
                call: Call<MutableList<trending_seeall_response>>,
                t: Throwable
            ) {
                t.printStackTrace()
                Log.e("failure", t.message.toString())
            }
        }
        )

    fun Dashboardconnect(view : View){
        val intent= Intent(view.context, activity_Dashboard::class.java)
        startActivity(intent)
    }
    }}
