package com.example.udenews

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.inputmethod.InputBinding
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.udenews.databinding.ActivityMainBinding
import com.example.udenews.databinding.ActivitySignInBinding
import com.example.udenews.model.User
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore

class SignIn : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    private lateinit var auth: FirebaseAuth
    private val _loading = MutableLiveData(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth

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
            val email = binding.etEmail.text.toString()
            val password = binding.etPass.text.toString()
            val repPassword = binding.etPassRep.text.toString()
            val program = binding.autoCompleteTextView.text.toString()

            if (validate(id,name,email, password,repPassword, program)){
                Toast.makeText(baseContext, "Ingresaste bien", Toast.LENGTH_SHORT).show()
                Log.d("TAG", "ola")
                Log.d("TAG", "$program")
                createUserWithEmailAndPassword(email, password, name,id,program)
            }
        }
    }
    private fun validate(id:String, name:String, email:String, password:String, repPassword:String, program:String):Boolean{
        if (id.isEmpty() || name.isEmpty() || email.isEmpty() || password.isEmpty() || repPassword.isEmpty()||program.isEmpty()){
            Toast.makeText(baseContext, "Complete los campos vacíos", Toast.LENGTH_SHORT).show()
            return false
        }
        if (password.length< 6){
            Toast.makeText(baseContext, "La contraseña debe tener al menos 6 dígitos", Toast.LENGTH_SHORT).show()
            return false
        }
        if(repPassword!=password){
            Toast.makeText(baseContext, "La contraseña no concuerda", Toast.LENGTH_SHORT).show()
            return false
        }
        if (!isValidEmail(email)) {
            Toast.makeText(baseContext, "Correo electrónico no válido", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
    private fun isValidEmail(email: String): Boolean {
        val pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }

    private fun createUserWithEmailAndPassword(email:String, password:String, id:String, name:String, program:String){
        if (_loading.value == false){
            _loading.value = true
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if(task.isSuccessful){
                    createUser(name,id,email,program)
                    reload()
                }else{
                    Log.d("TAG", "no se crea")
                }
                _loading.value = false
            }
        }
    }

    private fun createUser(id:String, name:String, email:String, program:String) {
        val userId = auth.currentUser?.uid
        // usando data class
        val user = User(
            userId = userId.toString(),
            name = name,
            email = email,
            program = program,
            id = id,
            role = "student"
        ).toMap()

        FirebaseFirestore.getInstance().collection("users")
            .add(user)
            .addOnSuccessListener{
                Log.d("TAG", "Creado ${it.id}")
            }.addOnFailureListener{
                Log.d("TAG", "ocurrio un error ${it}")
            }
    }
    private fun reload(){
        val intent = Intent(this, Admin::class.java)
        this.startActivity(intent)
    }
}