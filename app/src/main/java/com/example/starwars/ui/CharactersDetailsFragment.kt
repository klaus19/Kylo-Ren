package com.example.starwars.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.starwars.R
import com.example.starwars.Utils.Resource
import com.example.starwars.Viewmodel.StarDetailViewmodel
import com.example.starwars.databinding.FragmentCharactersDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect


@AndroidEntryPoint
class CharactersDetailsFragment : Fragment() {

      private lateinit var binding:FragmentCharactersDetailsBinding
      private val viewmodel:StarDetailViewmodel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharactersDetailsBinding.inflate(inflater,container,false)
        val view = binding.root

        viewmodel.details.observe(viewLifecycleOwner, Observer { result ->
            with(binding){
                textViewFullNameValue.text = result.name
                textViewSkinColorValue.text = result.skin_color
                binding.textViewHairColorValue.text = result.hair_color
                binding.textViewHeightValue.text = result.height
                binding.textViewMassValue.text = result.mass
                binding.textViewEyeColorValue.text = result.eye_color
                binding.textViewGenderValue.text = result.gender
                binding.textViewBirthYearValue.text = result.birth_year
            }

        })

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewmodel.homeworld.collect {  event->
                when (event) {
                    is Resource.Success -> {
                        binding.progressBarHomeWord.isVisible = false
                        binding.textViewHomeWorldValue.text = event.data!!.name
                    }
                    is Resource.Failure -> {
                        binding.progressBarHomeWord.isVisible = false
                        binding.textViewHomeWorldValue.text = event.message
                        Toast.makeText(requireContext(), event.message, Toast.LENGTH_SHORT).show()
                    }
                    is Resource.Loading -> {
                        binding.progressBarHomeWord.isVisible = true
                    }
                    else -> Unit
                }


            }
        }
        return view
    }

}