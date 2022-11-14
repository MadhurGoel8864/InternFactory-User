package com.example.internfactory.Activities.Adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.internfactory.R
import com.example.internfactory.modules.Internship_response

class internship_adapter(val internshipResponse: Internship_response):
    RecyclerView.Adapter<internship_adapter.internshipViewHolder>() {

    private lateinit var mListner:onItemClickListner
        interface onItemClickListner{
            fun onItemClick(position: Int)
        }

        fun setOnItemClickListner(listner:onItemClickListner){

            mListner=listner
        }

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): internshipViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_trending_seeall,parent,false)
            return internshipViewHolder(view,mListner)
    }

    override fun onBindViewHolder(holder: internshipViewHolder, position: Int) {
        return holder.bindView(internshipResponse)
    }

    override fun getItemCount(): Int {
        return internshipResponse.pageSize
    }

    class internshipViewHolder(itemView: View,listner: onItemClickListner): RecyclerView.ViewHolder(itemView){

        private val trending_title: TextView = itemView.findViewById(R.id.trending_title)
        private val trending_image: ImageView = itemView.findViewById(R.id.trending_image)


        fun bindView(internshipSeeallResponse: Internship_response){
            trending_title.text = internshipSeeallResponse.content[0].title
            val x =
                "https://internfactory.herokuapp.com/file/" + internshipSeeallResponse.content[0].imageUrl
            trending_image.load(x)
        }

        init{

            itemView.setOnClickListener {

                listner.onItemClick(adapterPosition)
            }
        }
    }

}