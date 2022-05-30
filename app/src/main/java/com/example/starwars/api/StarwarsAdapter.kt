package com.example.starwars.api

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.starwars.databinding.ItemStarwarsBinding
import com.example.starwars.model.Result


class StarwarsAdapter:RecyclerView.Adapter<StarwarsAdapter.StarViewHolder>() {

    inner class StarViewHolder(val binding:ItemStarwarsBinding):RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object :DiffUtil.ItemCallback<Result>(){
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
                   return  oldItem == newItem
        }

    }

    private val differ = AsyncListDiffer(this,diffCallback)
    var characters:List<Result>
    get() = differ.currentList
    set(value) {
        differ.submitList(value)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StarViewHolder {

        return StarViewHolder(ItemStarwarsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))

    }

    override fun onBindViewHolder(holder: StarViewHolder, position: Int) {
           holder.binding.apply {
               val character = characters[position]
               names.text = character.name
               species.text = character.gender
               homeworlds.text = character.homeworld
           }
    }

    override fun getItemCount() = characters.size
}