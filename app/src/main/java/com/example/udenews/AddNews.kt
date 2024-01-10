package com.example.udenews

import android.app.DownloadManager.Request
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.udenews.R
import com.example.udenews.databinding.ActivityAddNewsBinding
import com.example.udenews.model.News
import com.example.udenews.network.ApiClient
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import java.io.File


class AddNews : AppCompatActivity() {
    var etPlace: EditText?=null
    var etTitle:EditText?=null
    var etDate:EditText?=null
    var btnCalendar:ImageButton?=null
    var dpDate:DatePicker?=null
    private var selectedImageUri: Uri? = null
    val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()){uri ->

        if(uri!= null){
            selectedImageUri = uri
            ivImage.setImageURI(uri)

            Log.i("ola","seleccionado")
        }else{
            Log.i("ola","no seleccionado")
        }

    }

    lateinit var btnImage: Button
    lateinit var ivImage: ImageView

    val apiService = ApiClient.consApi

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_news)

        btnImage=findViewById(R.id.btnImage)
        ivImage=findViewById(R.id.ivImage)
        etPlace=findViewById(R.id.etPlace)
        etTitle=findViewById(R.id.etTitle)
        etDate=findViewById(R.id.etDate)
        btnCalendar=findViewById(R.id.btnCalendar)
        dpDate=findViewById(R.id.dpDate)


        btnImage.setOnClickListener {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }
        val btnCancel = findViewById<Button>(R.id.btnCancel)
        btnCancel.setOnClickListener {
            finish()
        }

        val btnAdd = findViewById<Button>(R.id.btnAdd)
        btnAdd.setOnClickListener {
            val title = etTitle?.text.toString()
            val date = etDate?.text.toString()
            val place = etPlace?.text.toString()

            // Validar que los campos obligatorios no estén vacíos
            if (title.isNotEmpty() && date.isNotEmpty() && place.isNotEmpty() && selectedImageUri != null) {
                // Crear una instancia de RequestBody para los campos de texto
                val titleRequestBody: RequestBody = RequestBody.create(MediaType.parse("text/plain"), title)
                val dateRequestBody: RequestBody = RequestBody.create(MediaType.parse("text/plain"), date)
                val placeRequestBody: RequestBody = RequestBody.create(MediaType.parse("text/plain"), place)

                // Convertir la URI a un archivo

                val file = File(selectedImageUri?.path)

                // Crear una instancia de MultipartBody.Part para la imagen
                val requestFile: RequestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file)
                val imageBody: MultipartBody.Part = MultipartBody.Part.createFormData("image", file.name, requestFile)

                // Realizar la solicitud de carga de noticias
                val call: Call<News> = apiService.uploadNews(titleRequestBody, imageBody, dateRequestBody, placeRequestBody)

                call.enqueue(object : Callback<News> {
                    override fun onResponse(call: Call<News>, response: retrofit2.Response<News>) {
                        if (response.isSuccessful) {
                            // Noticia cargada con éxito
                            // Manejar la respuesta del servidor según sea necesario
                            val news = response.body()
                            Log.i("response", "Noticia cargada con éxito: $news")

                            // Puedes realizar acciones adicionales aquí después de cargar la noticia
                            // Por ejemplo, limpiar los campos de entrada o navegar a otra pantalla
                            // según el flujo de tu aplicación.
                        } else {
                            // Error en la carga de la noticia
                            // Manejar el error según sea necesario
                            Log.i("response", "Error al cargar la noticia: ${response.code()}")
                        }
                    }

                    override fun onFailure(call: Call<News>, t: Throwable) {
                        // Manejar errores de red u otros problemas
                        Log.i("response", "Error en la solicitud: $t")
                    }
                })
            } else {
                // Mostrar un mensaje de error si algún campo está vacío
                Toast.makeText(this, "Completa todos los campos antes de agregar la noticia", Toast.LENGTH_SHORT).show()
            }
            /*
            val url = "http://192.168.0.15:8000/api/news_rest/"
            val queue = Volley.newRequestQueue(this)
            var resultPost = object : StringRequest(com.android.volley.Request.Method.POST,url,
                Response.Listener<String> { response ->
                    Toast.makeText(baseContext, "Noticia insertada", Toast.LENGTH_SHORT).show()
                    Log.i("response", "Noticia insertada")
                },Response.ErrorListener { error ->
                    Toast.makeText(baseContext, "Noticia no inserta", Toast.LENGTH_SHORT).show()
                    Log.i("response", "no se inserta nada $error")
                }
            ){
                override fun getParams(): MutableMap<String, String>? {
                    val parameters = HashMap<String,String>()
                    parameters.put("title", etTitle?.text.toString())
                    parameters.put("img", selectedImageUri.toString())
                    parameters.put("date", etDate?.text.toString())
                    parameters.put("place", etPlace?.text.toString())
                    return parameters
                }
            }
            queue.add(resultPost)

            Log.i("report", "$selectedImageUri"+"$" )

             */
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