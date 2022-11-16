package com.example.internfactory.Activities.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.internfactory.R
import com.example.internfactory.modules.SearchingResponse

class SearchAdapter(val searchingResponse: SearchingResponse): RecyclerView.Adapter<SearchAdapter.search_page_ViewHolder>() {
    private lateinit var mListner: onItemClickListner

    interface onItemClickListner {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListner(listner: onItemClickListner) {

        mListner = listner
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): search_page_ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.card_search_result, parent, false)
        return search_page_ViewHolder(view, mListner)
    }

    override fun onBindViewHolder(holder: search_page_ViewHolder, position: Int) {
        return holder.bindview(searchingResponse)
    }

    override fun getItemCount(): Int {
        return searchingResponse.content.size
    }


    class search_page_ViewHolder (itemView: View, listner: SearchAdapter.onItemClickListner) :
        RecyclerView.ViewHolder(itemView) {
        private val categorytitle: TextView = itemView.findViewById(R.id.search_title)

        private val categotyimage: ImageView = itemView.findViewById(R.id.search_image)

        fun bindview(searchingResponse: SearchingResponse) {
            categorytitle.text = searchingResponse.content[adapterPosition].displayName
            val x = "https://internfactory.herokuapp.com/file/" + searchingResponse.content[adapterPosition].imageUrl
            categotyimage.load(x)
        }

        init {

            itemView.setOnClickListener {

                listner.onItemClick(adapterPosition)
            }
        }

    }
}