package com.example.starwars

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.starwars.Viewmodel.StarViewmodel
import com.example.starwars.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var viewModel:StarViewmodel()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

         setUpRecyclerView()
        viewModel = ViewModelProvider(this).get(StarViewmodel::class.java)

    }

    private fun setUpRecyclerView() = binding.recyclerCharacters.apply{

    }
}