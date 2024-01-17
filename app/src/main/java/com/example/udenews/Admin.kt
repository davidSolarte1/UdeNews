package com.example.udenews

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.udenews.databinding.ActivityAdminBinding
import com.example.udenews.fragments.Add_news
import com.example.udenews.fragments.Map_fragment
import com.example.udenews.fragments.admin_fragment
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth


class Admin : AppCompatActivity() {
  //  private lateinit var news: List<News>
  //  private lateinit var recyclerView: RecyclerView
  //  private lateinit var newsAdapter: NewsAdapter
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityAdminBinding




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth


        //recyclerView=findViewById(R.id.rvNews)
        //recyclerView.layoutManager = GridLayoutManager(applicationContext, 1)

        changefragment(admin_fragment())
        //showNews()


        binding.bottonNavigation.setOnNavigationItemSelectedListener {

            when (it.itemId){
                R.id.home -> changefragment(admin_fragment())
                R.id.Map -> changefragment(Map_fragment())
                R.id.addnew -> openLinkInBrowser("http://192.168.1.2:8000/")
                R.id.out -> signOut()

                else ->{

                }
            }

            true

        }


    }

    private fun changefragment(fragment: Fragment) {

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()

    }
    private fun openLinkInBrowser(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    fun changeActivity(context: Context, targetActivity: Class<*>) {
        val intent = Intent(context, targetActivity)
        context.startActivity(intent)
    }


    //private fun showNews(){
       // val retrofitGet = ApiClient.consApi.getNews()
        //retrofitGet.enqueue(object : Callback<List<News>>{
          //  override fun onResponse(call: Call<List<News>>, response: Response<List<News>>) {
             //   if (response.isSuccessful){
                //    news = response.body()!!
                  //  newsAdapter = NewsAdapter(news,applicationContext)
                  //  recyclerView.adapter= newsAdapter
               // }
            //}

          //  override fun onFailure(call: Call<List<News>>, t: Throwable) {
         //       Toast.makeText(baseContext , "Error de conexion", Toast.LENGTH_SHORT).show()
         //   }


        //})
    //}
    private fun signOut(){
        Firebase.auth.signOut()
        val intent = Intent(this, Login::class.java)
        startActivity(intent)

        finish()
    }


}