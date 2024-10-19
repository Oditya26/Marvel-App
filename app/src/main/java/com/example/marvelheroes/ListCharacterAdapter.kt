package com.example.marvelheroes

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvelheroes.databinding.ItemRowCharBinding

class ListCharacterAdapter(
    private val listCharacter: ArrayList<Character>
) : RecyclerView.Adapter<ListCharacterAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    class ListViewHolder(var binding: ItemRowCharBinding) : RecyclerView.ViewHolder(binding.root)

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val binding =
            ItemRowCharBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listCharacter.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val character = listCharacter[position]
        Glide.with(holder.itemView.context).load(character.photo)
            .into(holder.binding.imageItemPhoto)
        holder.binding.tvItemName.text = character.name
        holder.binding.tvItemDescription.text = character.description

        // Menggunakan instance onItemClickCallback
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(character) // Use the callback to handle click
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Character)
    }
}
