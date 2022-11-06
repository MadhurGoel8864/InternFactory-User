package com.example.internfactory.Activities.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.internfactory.CategoriesSeeAll
import com.example.internfactory.R
import com.example.internfactory.modules.category_seeall_response

class category_adapter(val categoriesSeeAll: MutableList<category_seeall_response>): RecyclerView.Adapter<category_ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): category_ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_categoryseeall,parent,false)
        return category_ViewHolder(view)
    }

    override fun onBindViewHolder(holder: category_ViewHolder, position: Int) {
        return holder.bindview(categoriesSeeAll[position])
    }

    override fun getItemCount(): Int {
        return categoriesSeeAll.size
    }
}

class category_ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
    private val categorytitle:TextView = itemView.findViewById(R.id.category_title)
    private val categoryheading:TextView = itemView.findViewById(R.id.category_heading)

    fun bindview(categorySeeallResponse: category_seeall_response){
        categorytitle.text = categorySeeallResponse.categoryName
        categoryheading.text = categorySeeallResponse.imageName
    }
}