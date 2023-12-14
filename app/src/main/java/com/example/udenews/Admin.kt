package com.example.udenews

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.udenews.adapter.NewsAdapter
import com.example.udenews.databinding.ActivityAdminBinding
import com.example.udenews.model.News
import com.example.udenews.network.ApiClient
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Admin : AppCompatActivity() {
    private lateinit var news: List<News>
    private lateinit var recyclerView: RecyclerView
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityAdminBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth

        recyclerView=findViewById(R.id.rvNews)
        recyclerView.layoutManager = GridLayoutManager(applicationContext, 1)

        showNews()

        binding.btnLogOut.setOnClickListener {
            signOut()
            finish()
        }

        binding.btnAddNew.setOnClickListener {
            val intent = Intent(this, AddNews::class.java)
            startActivity(intent)
        }

    }

    private fun showNews(){
        val retrofitGet = ApiClient.consApi.getNews()
        retrofitGet.enqueue(object : Callback<List<News>>{
            override fun onResponse(call: Call<List<News>>, response: Response<List<News>>) {
                if (response.isSuccessful){
                    news = response.body()!!
                    newsAdapter = NewsAdapter(news,applicationContext)
                    recyclerView.adapter= newsAdapter
                }
            }

            override fun onFailure(call: Call<List<News>>, t: Throwable) {
                Toast.makeText(baseContext , "Error de conexion", Toast.LENGTH_SHORT).show()
            }


        })
    }
    private fun signOut(){
        Firebase.auth.signOut()
        val intent = Intent(this, Login::class.java)
        startActivity(intent)

    }
}