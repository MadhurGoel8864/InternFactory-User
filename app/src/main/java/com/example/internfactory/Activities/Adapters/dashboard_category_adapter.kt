package com.example.internfactory.Activities.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.internfactory.R
import com.example.internfactory.modules.category_seeall_response
import com.example.internfactory.modules.main_screen_category_dataclass

class dashboard_category_adapter(val categoriesSeeAll: MutableList<main_screen_category_dataclass>):
    RecyclerView.Adapter<category_ViewHolder1>() {

    private lateinit var mListner1:onItemClickListner1

    fun setOnItemClickListner1(listner1:onItemClickListner1){

        mListner1=listner1
    }
    interface onItemClickListner1{
        fun onItemClick1(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): category_ViewHolder1 {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_category_grid_mainscreen,parent,false)
        return category_ViewHolder1(view,mListner1)
    }

    override fun onBindViewHolder(holder: category_ViewHolder1, position: Int) {
        return holder.bindview(categoriesSeeAll[position])
    }

    override fun getItemCount(): Int {
        return categoriesSeeAll.size
    }
}


class category_ViewHolder1(itemView:View,listner1: dashboard_category_adapter.onItemClickListner1):RecyclerView.ViewHolder(itemView){
    private val categorytitle:TextView = itemView.findViewById(R.id.categ_title)

    private val categotyimage:ImageView = itemView.findViewById(R.id.category_img)

    fun bindview(mainScreenCategoryDataclass: main_screen_category_dataclass){
        categorytitle.text = mainScreenCategoryDataclass.categoryName
        val x = "https://internfactory.herokuapp.com/file/" + mainScreenCategoryDataclass.imageName
        categotyimage.load(x)
    }

    init{

        itemView.setOnClickListener {

            listner1.onItemClick1(adapterPosition)
        }
    }
}