package com.example.starwars.api

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.starwars.databinding.CharacterRowBinding
import com.example.starwars.model.Result


class CharactersAdapter(private val onClickListener: OnClickListener) :
    PagingDataAdapter<com.example.starwars.model.Result, CharactersAdapter.MyViewHolder>(CHARACTER_COMPARATOR) {

    inner class MyViewHolder(private val binding: CharacterRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(character: com.example.starwars.model.Result?) {
            binding.nameTextView.text = character?.name
            binding.dobTextView.text = character?.homeworld
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            CharacterRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val character = getItem(position)
        holder.bind(character)

        holder.itemView.setOnClickListener {
            onClickListener.onClick(character!!)
        }
    }

    companion object {
        private val CHARACTER_COMPARATOR = object : DiffUtil.ItemCallback<com.example.starwars.model.Result>() {
            override fun areItemsTheSame(oldItem: com.example.starwars.model.Result, newItem: com.example.starwars.model.Result): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem:com.example.starwars.model.Result, newItem: com.example.starwars.model.Result): Boolean {
                return oldItem == newItem
            }
        }
    }

    class OnClickListener(val clickListener: (character:com.example.starwars.model.Result) -> Unit) {
        fun onClick(character: Result) = clickListener(character)
    }
}