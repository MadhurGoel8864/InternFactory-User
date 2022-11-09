package com.example.internfactory.Activities.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import coil.load
import com.example.internfactory.R

class ImageAdapter(private val imageList: ArrayList<String>, private val viewPager2: ViewPager2) :
    RecyclerView.Adapter<ImageAdapter.Imageviewholder>() {

    class Imageviewholder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val imageView: ImageView = itemview.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Imageviewholder {
val view = LayoutInflater.from(parent.context).inflate(R.layout.image_container,parent,false)
        return Imageviewholder(view)



    }

    override fun onBindViewHolder(holder: Imageviewholder, position: Int) {
holder.imageView.load(imageList[position])
        if(position == imageList.size-1){
            viewPager2.post(runnable)
        }

    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    private val runnable = Runnable{
        imageList.addAll(imageList)
        notifyDataSetChanged()
    }


}