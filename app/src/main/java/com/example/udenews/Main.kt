package com.example.udenews


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.udenews.adapter.NewsAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.Firebase
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class Main : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecicleView()

        auth = Firebase.auth

        //signOut()
    }

    private fun signOut(){
        Firebase.auth.signOut()
        val intent = Intent(this, Login::class.java)
        startActivity(intent)

    }

    private fun initRecicleView(){


        val recyclerView = findViewById<RecyclerView>(R.id.Reciclenews)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = NewsAdapter(NewsProvider.newslist)

    }


}