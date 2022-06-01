package com.example.starwars.api

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.starwars.databinding.CharacterRowBinding
import com.example.starwars.databinding.ItemStarwarsBinding
import com.example.starwars.model.Result


class CharactersAdapter(private val onClickListener: OnClickListener) :
    PagingDataAdapter<Result, CharactersAdapter.MyViewHolder>(CHARACTER_COMPARATOR) {

    inner class MyViewHolder(private val binding: CharacterRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(character: Result?) {
            binding.nameTextView.text = character?.name
            binding.dobTextView.text = character?.birthYear
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
        private val CHARACTER_COMPARATOR = object : DiffUtil.ItemCallback<Result>() {
            override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem == newItem
            }
        }
    }

    class OnClickListener(val clickListener: (character: Result) -> Unit) {
        fun onClick(character: Result) = clickListener(character)
    }
}