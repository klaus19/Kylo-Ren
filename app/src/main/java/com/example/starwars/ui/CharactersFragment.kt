package com.example.starwars.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.example.starwars.R
import com.example.starwars.Viewmodel.StarViewmodel
import com.example.starwars.api.CharactersAdapter
import com.example.starwars.databinding.FragmentCharactersBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect


@AndroidEntryPoint
class CharactersFragment : Fragment() {

    private lateinit var binding: FragmentCharactersBinding
    private val viewModel:StarViewmodel by viewModels()

    private val charactersAdapter:CharactersAdapter by lazy {

        CharactersAdapter(CharactersAdapter.OnClickListener{ character->

            val action = CharactersFragmentDirections.actionCharactersFragmentToCharactersDetailsFragment(
                character
            )
            findNavController().navigate(action)
            binding.searchView.editText!!.text.isEmpty()
        })

    }




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
          binding = FragmentCharactersBinding.inflate(inflater,container,false)
              val view = binding.root

        binding.searchView.setEndIconOnClickListener {
            setupObserver(binding.searchView.editText!!.text.toString())
            binding.charactersProgressBar.isVisible=true
        }
        setupObserver("")
        setupAdapter()

        return view
    }
    private fun setupObserver(searchsTRING: String) {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.getCharacters(searchsTRING).collect {
                charactersAdapter.submitData(lifecycle,it)
            }
        }
    }

    private fun setupAdapter() {

        binding.charactersRecyclerview.apply {
            adapter = charactersAdapter
        }

        charactersAdapter.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.Loading) {
                if (charactersAdapter.snapshot().isEmpty()) {
                    binding.charactersProgressBar.isVisible = true
                }
                binding.textViewError.isVisible = false

            } else {
                binding.charactersProgressBar.isVisible = false

                val error = when {
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error

                    else -> null
                }
                error?.let {
                    if (charactersAdapter.snapshot().isEmpty()) {
                        binding.textViewError.isVisible = true
                        binding.textViewError.text = it.error.localizedMessage
                    }
                }
            }
        }
    }

    }



