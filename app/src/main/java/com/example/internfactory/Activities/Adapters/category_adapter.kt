package com.example.internfactory.Activities.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.internfactory.CategoriesSeeAll
import com.example.internfactory.R
import com.example.internfactory.modules.category_seeall_response
class category_adapter(val categoriesSeeAll: MutableList<category_seeall_response>): RecyclerView.Adapter<category_ViewHolder>() {

    private lateinit var mListner:onItemClickListner

    fun setOnItemClickListner(listner:onItemClickListner){

        mListner=listner
    }
    interface onItemClickListner{
                fun onItemClick(position: Int)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): category_ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_categoryseeall,parent,false)
        return category_ViewHolder(view,mListner)
    }

    override fun onBindViewHolder(holder: category_ViewHolder, position: Int) {
        return holder.bindview(categoriesSeeAll[position])
    }

    override fun getItemCount(): Int {
        return categoriesSeeAll.size
    }
}

class category_ViewHolder(itemView:View,listner: category_adapter.onItemClickListner):RecyclerView.ViewHolder(itemView){
    private val categorytitle:TextView = itemView.findViewById(R.id.category_title)

    private val categotyimage:ImageView = itemView.findViewById(R.id.category_image)

    fun bindview(categorySeeallResponse: category_seeall_response){
        categorytitle.text = categorySeeallResponse.categoryName
        val x = "https://internfactory.herokuapp.com/file/" + categorySeeallResponse.imageName
        categotyimage.load(x)
    }

    init{

        itemView.setOnClickListener {

            listner.onItemClick(adapterPosition)
        }
    }
}