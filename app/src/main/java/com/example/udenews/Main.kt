package com.example.udenews


import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.udenews.databinding.ActivityMainBinding
import com.example.udenews.fragments.Map_fragment
import com.example.udenews.fragments.home
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class Main : AppCompatActivity() {

    //private lateinit var news: List<News>
    //private lateinit var recyclerView: RecyclerView
    //private lateinit var newsAdapter: NewsAdapter
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth

        //recyclerView=findViewById(R.id.rvNews)
        //recyclerView.layoutManager = GridLayoutManager(applicationContext, 1)


        changefragment(home())
        //showNews()

        //signOut()
        binding.bottonNavigationMain?.setOnNavigationItemSelectedListener {

            when (it.itemId){
                R.id.home -> changefragment(home())
                R.id.Map ->changefragment(Map_fragment())
                R.id.out -> signOut()

                else ->{

                }
            }

            true

        }



    }

    fun changeActivity(context: Context, targetActivity: Class<*>) {
        val intent = Intent(context, targetActivity)
        context.startActivity(intent)
    }

    private fun changefragment(fragment: Fragment) {

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout_main,fragment)
        fragmentTransaction.commit()

    }
    //private fun showNews(){
      //  val retrofitGet = ApiClient.consApi.getNews()
       // retrofitGet.enqueue(object : Callback<List<News>> {
         //   override fun onResponse(call: Call<List<News>>, response: Response<List<News>>) {
           //     if (response.isSuccessful){
             //       news = response.body()!!
               //     newsAdapter = NewsAdapter(news,applicationContext)
                 //   recyclerView.adapter= newsAdapter
               // }
           // }

          //  override fun onFailure(call: Call<List<News>>, t: Throwable) {
           //     Toast.makeText(baseContext , "Error de conexion", Toast.LENGTH_SHORT).show()
           // }


       // })
   // }
    private fun signOut() {
        Firebase.auth.signOut()
        val intent = Intent(this, Login::class.java)
        startActivity(intent)

        finish()

    }


}