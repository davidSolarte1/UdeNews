package com.example.udenews

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView



class Main : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecicleView()

        

    }

    private fun initRecicleView(){


        val recyclerView = findViewById<RecyclerView>(R.id.Reciclenews)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter

    }


}