package com.example.udenews.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.fragment.app.Fragment
import com.example.udenews.R


class Add_news : Fragment() {
    private var nav: WebView?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_news, container, false)
        nav = view?.findViewById(R.id.wvAdd)

        load(view)
        return view

    }

    private fun load(view: View){
        nav?.clearCache(false)
        nav?.settings?.javaScriptEnabled=true
        nav?.loadUrl("http://192.168.1.2:8000")
    }


}