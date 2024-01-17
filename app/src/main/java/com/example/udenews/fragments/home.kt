package com.example.udenews.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.udenews.R
import com.example.udenews.adapter.NewsAdapter
import com.example.udenews.model.News
import com.example.udenews.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class home : Fragment() {
    private lateinit var news: List<News>
    private lateinit var recyclerView: RecyclerView
    private lateinit var newsAdapter: NewsAdapter


    @SuppressLint("MissingInflatedId")
    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home,container,false)


        recyclerView = view.findViewById(R.id.rvNews_main)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 1)

        val layoutManager = LinearLayoutManager(requireContext())
        layoutManager.reverseLayout = true
        layoutManager.stackFromEnd = true
        recyclerView.layoutManager = layoutManager
        showNews()

        return view
    }


    private fun showNews() {
        val retrofitGet = ApiClient.consApi.getNews()
        retrofitGet.enqueue(object : Callback<List<News>> {
            override fun onResponse(call: Call<List<News>>, response: Response<List<News>>) {
                if (response.isSuccessful) {
                    news = response.body() ?: emptyList()
                    newsAdapter = NewsAdapter(news, requireContext())
                    recyclerView.adapter = newsAdapter
                }
            }

            override fun onFailure(call: Call<List<News>>, t: Throwable) {
                Toast.makeText(requireContext(), "Error de conexion", Toast.LENGTH_SHORT).show()
            }
        })
    }
}

