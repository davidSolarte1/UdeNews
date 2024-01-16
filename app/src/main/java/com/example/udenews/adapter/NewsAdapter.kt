package com.example.udenews.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.udenews.R
import com.example.udenews.model.News

class NewsAdapter(private val news: List<News>, private val context: Context) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvNewsTitle.text = news[position].title
        holder.tvDate.text = news[position].date
        holder.tvPlace.text = news[position].place
        Glide.with(context).load(news[position].img).into(holder.ivNews)
    }

    override fun getItemCount(): Int {
        return news.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivNews: ImageView = itemView.findViewById(R.id.ivNews)
        val tvNewsTitle: TextView = itemView.findViewById(R.id.tvNewsTitle)
        val tvDate: TextView = itemView.findViewById(R.id.tvDate)
        val tvPlace: TextView = itemView.findViewById(R.id.tvPlace)
    }
}