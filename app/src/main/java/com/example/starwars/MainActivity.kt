package com.example.starwars

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.starwars.Viewmodel.StarViewmodel
import com.example.starwars.api.RetrofitInstance
import com.example.starwars.api.StarwarsAdapter
import com.example.starwars.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var viewModel:StarViewmodel
    private lateinit var binding: ActivityMainBinding
    lateinit var starwarsAdapter: StarwarsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(StarViewmodel::class.java)
         setUpRecyclerView()

        binding.progressBar.isVisible = true
        

    }

    private  fun setUpRecyclerView() = binding.recyclerCharacters.apply{
                   starwarsAdapter = StarwarsAdapter()
                    adapter = starwarsAdapter
                    layoutManager = LinearLayoutManager(this@MainActivity)


    }
}