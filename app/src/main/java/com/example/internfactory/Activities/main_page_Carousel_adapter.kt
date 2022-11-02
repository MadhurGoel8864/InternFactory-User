package com.example.internfactory.Activities

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.internfactory.R
import com.makeramen.roundedimageview.RoundedImageView

class main_page_Carousel_adapter(val context: Context):RecyclerView.Adapter<main_page_Carousel_adapter.MyViewHolder>() {

lateinit var list:List<Int>
fun setContentList(list:List<Int>){
    this.list = list
    notifyDataSetChanged()
}

    inner class MyViewHolder(view: View):RecyclerView.ViewHolder(view){
        var image = itemView.findViewById<RoundedImageView>(R.id.list_item_image)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):main_page_Carousel_adapter.MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.carousel_list_item,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.image.setImageResource(list[position])


    }




}