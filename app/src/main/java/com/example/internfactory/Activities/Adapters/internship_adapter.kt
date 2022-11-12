package com.example.internfactory.Activities.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.internfactory.R
import com.example.internfactory.modules.ContentX
import com.example.internfactory.modules.Internship_response
import com.example.internfactory.modules.trending_seeall_response

//class internship_adapter(val internshipResponse: Internship_response) {
//    RecyclerView.Adapter<InternshipViewHolder>() {
//
//        private lateinit var mListner:onItemClickListner
//        interface onItemClickListner{
//            fun onItemClick(position: Int)
//        }
//
//        fun setOnItemClickListner(listner:onItemClickListner){
//
//            mListner=listner
//        }
//
//        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InternshipViewHolder {
//            val view = LayoutInflater.from(parent.context).inflate(R.layout.card_trending_seeall,parent,false)
//            return InternshipViewHolder(view,mListner)
//        }
//
//        override fun onBindViewHolder(holder: trendingViewHolder, position: Int) {
//            return holder.bindView(internshipResponse[position])
//        }
//
//        override fun getItemCount(): Int {
//            return internshipResponse.size
//        }
//    }
//
//    class internshipViewHolder(itemView: View, listner: internship_adapter.onItemClickListner):
//        RecyclerView.ViewHolder(itemView){
//
//        private val trending_title: TextView = itemView.findViewById(R.id.trending_title)
//        private val trending_image: ImageView = itemView.findViewById(R.id.trending_image)
//
//        fun bindView(internshipSeeallResponse: Internship_response){
//            trending_title.text = internshipSeeallResponse.content[ContentX].about
//            val x =
//                "https://internfactory.herokuapp.com/file/images/${internshipSeeallResponse.content[ContentX].imageUrl}"
//            trending_image.load(x)
//        }
//
//        init{
//
//            itemView.setOnClickListener {
//
//                listner.onItemClick(adapterPosition)
//            }
//        }
//    }
//}