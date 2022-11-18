package com.example.internfactory.Activities.Adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.internfactory.Activities.internship_deatils_fragement
import com.example.internfactory.R
import com.example.internfactory.modules.Internship_response

class internship_adapter(val internshipResponse: Internship_response):
    RecyclerView.Adapter<internship_adapter.internshipViewHolder>() {

    private lateinit var mListner:onItemClickListner
        interface onItemClickListner{
            fun onItemClick(position: Int)
//            fun btnonItemClick(position: Button)
        }

        fun setOnItemClickListner(listner:onItemClickListner){

            mListner=listner
        }

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): internshipViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_all_internship,parent,false)
            return internshipViewHolder(view,mListner)
    }

    override fun onBindViewHolder(holder: internshipViewHolder, position: Int) {
        return holder.bindView(internshipResponse)
    }

    override fun getItemCount(): Int {
        return internshipResponse.content.size
    }

    class internshipViewHolder(itemView: View,listner: onItemClickListner): RecyclerView.ViewHolder(itemView){

        private val company_logo:ImageView = itemView.findViewById(R.id.imageView14)
        private val internship_domain:TextView = itemView.findViewById(R.id.textView26)
        private val company_name:TextView = itemView.findViewById(R.id.textView27)
        private val remote_location:TextView = itemView.findViewById(R.id.textView28)
        private val internship_timeline:TextView = itemView.findViewById(R.id.textView29)
        private val amount:TextView = itemView.findViewById(R.id.textView30)
        private val apply_btn:Button = itemView.findViewById(R.id.Apply_btn)



        fun bindView(internshipSeeallResponse: Internship_response){

            internship_domain.text = internshipSeeallResponse.content[adapterPosition].displayName
            company_name.text = internshipSeeallResponse.content[adapterPosition].provider
            remote_location.text = internshipSeeallResponse.content[adapterPosition].type
            internship_timeline.text = internshipSeeallResponse.content[adapterPosition].tenure
            amount.text = internshipSeeallResponse.content[adapterPosition].stipend
            val x = "https://internfactory.herokuapp.com/file/" + internshipSeeallResponse.content[adapterPosition].imageUrl
            company_logo.load(x)
        }

        init{

            apply_btn.setOnClickListener {

                listner.onItemClick(adapterPosition)
            }
        }
    }
//    private fun replaceFrag(fragment : Fragment, name: String){
//        val fm : FragmentManager = parentFragmentManager
//        val ft : FragmentTransaction = fm.beginTransaction()
//        ft.addToBackStack(name)
//        ft.add(R.id.container, fragment)
//        ft.commit()
//    }
//
//    fun internshipdetails(view: View) {
//        val internshipdetails = internship_deatils_fragement()
//        replaceFrag(internshipdetails, "internshipdetails")
//    }
}