package com.example.udenews

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.udenews.databinding.ActivityLoginBinding
import com.example.udenews.databinding.ActivitySignInBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class Login : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        binding.btnLogin.setOnClickListener {
            val email = binding.etUser.text.toString()
            val password = binding.etPassword.text.toString()
            if (email.isEmpty() || password.isEmpty()){
                Toast.makeText(baseContext, "Complete todos los campos", Toast.LENGTH_SHORT).show()
            }
            else{
                LogIn(email, password)
            }
        }

        binding.btnSignIn.setOnClickListener {
            val intent = Intent(this, SignIn::class.java)
            this.startActivity(intent)
        }
    }

    public override fun onStart() {
        super.onStart()

        val currentUser = auth.currentUser
        if(currentUser != null){
            reload()
        }
    }
    private fun LogIn(email:String, password:String){
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this){ task ->
            if (task.isSuccessful) {
                Log.d("TAG", "signInWithEmail:success")
                reload()
                finish()

            } else {
                Log.w("TAG", "signInWithEmail:failure", task.exception)
                Toast.makeText(
                    baseContext,
                    "Authentication failed.", Toast.LENGTH_SHORT,
                ).show()

            }
        }
    }
    private fun reload(){
        val intent = Intent(this, Admin::class.java)
        this.startActivity(intent)
    }
}