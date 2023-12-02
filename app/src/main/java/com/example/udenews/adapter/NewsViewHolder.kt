package com.example.udenews.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.udenews.News
import com.example.udenews.databinding.ItemNewsBinding

class NewsViewHolder(val view:View):ViewHolder(view) {

    val binding = ItemNewsBinding.bind(view)

    //esta funcion se llama por cada item de noticia
    fun reder(newsmodel: News, onClickListener: (News) -> Unit, onclickDeleted: (Int) -> Unit){

        binding.tvnewname.text =newsmodel.newname
        binding.tvdescription.text = newsmodel.description
        Glide.with(binding.ivnews.context).load(newsmodel.photo).into(binding.ivnews)
        itemView.setOnClickListener { onClickListener(newsmodel) }
        binding.btnBorrar.setOnClickListener { onclickDeleted(adapterPosition)}
    }


}
