package com.example.internfactory.Activities.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.internfactory.R
import com.example.internfactory.modules.trending_seeall_response

class trending_seeall_adapters(val trendingSeeallResponse:MutableList<trending_seeall_response>):
    RecyclerView.Adapter<trendingViewHolder>() {

    private lateinit var mListner:onItemClickListner
    interface onItemClickListner{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListner(listner:onItemClickListner){

        mListner=listner
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): trendingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_trending_seeall,parent,false)
        return trendingViewHolder(view,mListner)
    }

    override fun onBindViewHolder(holder: trendingViewHolder, position: Int) {
        return holder.bindView(trendingSeeallResponse[position])
    }

    override fun getItemCount(): Int {
        return trendingSeeallResponse.size
    }
}
class trendingViewHolder(itemView: View,listner: trending_seeall_adapters.onItemClickListner):RecyclerView.ViewHolder(itemView){

    private val trending_title:TextView = itemView.findViewById(R.id.trending_title)
    private val trending_image:ImageView = itemView.findViewById(R.id.trending_image)

    fun bindView(trendingSeeallResponse: trending_seeall_response){
        trending_title.text = trendingSeeallResponse.categoryName
        val x =  "https://internfactory.herokuapp.com/file/" + trendingSeeallResponse.imageName
        trending_image.load(x)
    }

    init{

        itemView.setOnClickListener {

            listner.onItemClick(adapterPosition)
        }
    }
}