package com.example.tarea4galeriaperros

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tarea4galeriaperros.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    //Link views
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: DogAdapter
    private val dogImg = mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.svDogs.setOnQueryTextListener(this)
        initRecyclerView()
    }

    private fun initRecyclerView(){
        adapter = DogAdapter(dogImg)
        binding.rvDogs.layoutManager = LinearLayoutManager(this)
        binding.rvDogs.adapter = adapter
    }

    private fun getRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/breed/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun searchByName(query:String){
        //Coroutines!
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIService::class.java).getDogsByBreeds("$query/images")
            val puppies = call.body()
            runOnUiThread{
                if(call.isSuccessful){
                    //show recyclerview
                    var img = puppies?.images ?: emptyList()
                    dogImg.clear()
                    dogImg.addAll(img)
                    adapter.notifyDataSetChanged()

                }else{
                    //error
                    showError()
                }
            }

        }
    }
    private fun showError(){
        Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_LONG).show()
    }

    //When user end of search
    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrBlank()){
            searchByName(query.toLowerCase())
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }


}