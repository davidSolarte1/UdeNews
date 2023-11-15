package com.example.udenews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.udenews.adapter.NewsViewHolder

class NewsAdapter(private val newsList: List<News>) : RecyclerView.Adapter<NewsViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        return NewsViewHolder(layoutInflater.inflate(R.layout.item_news, parent,false))
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    //este metodo pasa por los items y llama a la fun render

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {

        val item = newsList[position]
        holder.reder(item)
    }
}