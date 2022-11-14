package com.example.internfactory.Activities.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.internfactory.R
import com.google.android.material.imageview.ShapeableImageView

class Trending_adapter(private val imagelist: ArrayList<Trending_dataclass>):
    RecyclerView.Adapter<Trending_adapter.Myviewholder>() {

    private lateinit var mListener:onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)

    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Myviewholder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.trending_list_item,parent,false)
        return Myviewholder(itemView,mListener)
    }
    override fun onBindViewHolder(holder: Myviewholder, position: Int) {
        val currentitem = imagelist[position]
        holder.image.setImageResource(currentitem.images)
//        holder.titleimg.setImageResource(currentitem.title_img)
//        holder.heading.text = currentitem.heading
    }


    override fun getItemCount(): Int {
        return imagelist.size
    }

    class Myviewholder(itemView: View,listener: onItemClickListener): RecyclerView.ViewHolder(itemView) {

        val image: ShapeableImageView = itemView.findViewById(R.id.img)

        init{
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)

            }
        }


    }
}