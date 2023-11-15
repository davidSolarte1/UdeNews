package com.example.udenews.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.udenews.News
import com.example.udenews.R

class NewsViewHolder(val view:View):ViewHolder(view) {

    val namenews =view.findViewById<TextView>(R.id.tvnewname)
    val descriptionnews =view.findViewById<TextView>(R.id.tvdescription)
    val photonews =view.findViewById<ImageView>(R.id.ivnews)

    //esta funcion se llama por cada item de noticia
    fun reder(newsmodel: News){

        namenews.text =newsmodel.newname
        descriptionnews.text = newsmodel.description


    }


}
