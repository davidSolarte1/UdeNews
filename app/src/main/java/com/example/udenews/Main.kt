package com.example.udenews


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.udenews.adapter.NewsAdapter
import com.example.udenews.databinding.ActivityMainBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class Main : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding
    private val newsMutableList: MutableList<News> = NewsProvider.newslist.toMutableList()
    private lateinit var adapter: NewsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecycleView()

        auth = Firebase.auth

        //signOut()
    }

    private fun signOut() {
        Firebase.auth.signOut()
        val intent = Intent(this, Login::class.java)
        startActivity(intent)

    }

    private fun initRecycleView() {
        adapter = NewsAdapter(
            newsList = newsMutableList,
            onClickListener = { news -> onItemSelected(news)},
            onclickDeleted = {position -> onclickDeleted(position)})

        val manager = LinearLayoutManager(this)
        val decoration = DividerItemDecoration(this, manager.orientation)
        binding.Reciclenews.layoutManager = LinearLayoutManager(this)
        binding.Reciclenews.adapter = adapter
        binding.Reciclenews.addItemDecoration(decoration)
    }

    private fun onItemSelected(news: News) {

        Toast.makeText(this, news.newname, Toast.LENGTH_SHORT).show()
    }

    private fun onclickDeleted(position:Int){

        newsMutableList.removeAt(position)
        adapter.notifyItemRemoved(position)

    }
}