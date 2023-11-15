package com.example.udenews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputBinding
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.udenews.databinding.ActivityMainBinding
import com.example.udenews.databinding.ActivitySignInBinding

class SignIn : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val programs = resources.getStringArray(R.array.programs)
        val adapter =  ArrayAdapter(
            this,
            R.layout.list_item,
            programs
        )

        with(binding.autoCompleteTextView){
            setAdapter(adapter)
        }


    }
}