package com.example.udenews.model

data class User(
    val id:String,
    val userId:String,
    val name:String,
    val email:String,
    val program:String,
    val role:String
){
    fun toMap(): MutableMap<String, Any>{
        return mutableMapOf(
            "user_id" to this.userId,
            "student_id" to this.id,
            "name" to this.name,
            "email" to this.email,
            "program" to this.program,
            "role" to this.role
        )
    }
}
