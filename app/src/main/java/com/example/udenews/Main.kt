package com.example.udenews


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.udenews.adapter.NewsAdapter
import com.example.udenews.databinding.ActivityAdminBinding
import com.example.udenews.databinding.ActivityMainBinding
import com.example.udenews.model.News
import com.example.udenews.network.ApiClient
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Main : AppCompatActivity() {

    private lateinit var news: List<News>
    private lateinit var recyclerView: RecyclerView
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth

        recyclerView=findViewById(R.id.rvNews)
        recyclerView.layoutManager = GridLayoutManager(applicationContext, 1)

        showNews()

        //signOut()
    }
    private fun showNews(){
        val retrofitGet = ApiClient.consApi.getNews()
        retrofitGet.enqueue(object : Callback<List<News>> {
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
    private fun signOut() {
        Firebase.auth.signOut()
        val intent = Intent(this, Login::class.java)
        startActivity(intent)

    }


}