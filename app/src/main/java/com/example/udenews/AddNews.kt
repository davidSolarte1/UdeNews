package com.example.udenews

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.udenews.R
import com.example.udenews.databinding.ActivityAddNewsBinding


class AddNews : AppCompatActivity() {
    var etDate:EditText?=null
    var btnCalendar:ImageButton?=null
    var dpDate:DatePicker?=null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_news)

        etDate=findViewById(R.id.etDate)
        btnCalendar=findViewById(R.id.btnCalendar)
        dpDate=findViewById(R.id.dpDate)

        val btnCancel = findViewById<Button>(R.id.bntCancel)
        btnCancel.setOnClickListener {
            finish()
        }


        etDate?.setText(getDatePicker())

        btnCalendar?.setOnClickListener{
                view ->
            showCalendar(view)
        }

        dpDate?.setOnDateChangedListener { dpDate, year, monthOfYear, dayOfMonth ->
            etDate?.setText(getDatePicker())
            dpDate.visibility=View.GONE
        }


    }

    fun getDatePicker():String{
        var day = dpDate?.dayOfMonth.toString().padStart(2,'0')
        var month = (dpDate!!.month+1).toString().padStart(2,'0')
        var year = dpDate?.year.toString().padStart(4,'0')
        return day+"/"+month+"/"+year
    }

    fun showCalendar(view: View){
        dpDate?.visibility=View.VISIBLE
    }

}