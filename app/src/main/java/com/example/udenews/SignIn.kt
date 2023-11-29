package com.example.udenews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputBinding
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
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

        with(binding.autoCompleteTextView) {
            setAdapter(adapter)
        }
        binding.btnRegister.setOnClickListener {
            val id = binding.etId.text.toString()
            val name = binding.etName.text.toString()
            val password = binding.etPass.text.toString()
            val repPassword = binding.etPassRep.text.toString()
            val program = binding.autoCompleteTextView.text.toString()

            if (!validate(id,name,password,repPassword, program)){
                
            }else{

                Toast.makeText(baseContext, "Ingresaste bien", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun validate(id:String, name:String, password:String, repPassword:String, program:String):Boolean{
        if (id.isEmpty() || name.isEmpty() || password.isEmpty() || repPassword.isEmpty()||program.isEmpty()){
            Toast.makeText(baseContext, "Complete todos los datos", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
}