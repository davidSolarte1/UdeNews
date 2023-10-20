package com.example.udenews

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class menu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.sleep(2000)
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val btnLogin = findViewById<Button>(R.id.login)
        val btnMain = findViewById<Button>(R.id.main)

        btnLogin.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
        btnMain.setOnClickListener {
            startActivity(Intent(this, Main::class.java))
        }
    }
}