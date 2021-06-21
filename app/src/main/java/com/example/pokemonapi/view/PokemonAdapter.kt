package com.example.pokemonapi.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokemonapi.R
import com.example.pokemonapi.databinding.PokemonItemBinding
import com.example.pokemonapi.manager.PokemonManager
import com.example.pokemonapi.model.Pokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class PokemonAdapter : RecyclerView.Adapter<PokemonAdapter.PokemonTitleHolder>() {
    lateinit var itemBinding: PokemonItemBinding
    lateinit var pokemonManager : PokemonManager

    var list = listOf<Pokemon>()
    lateinit var mContext : Context

    fun update(list: List<Pokemon>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonTitleHolder {
        itemBinding = PokemonItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        pokemonManager = PokemonManager(parent.context)
        mContext = parent.context

        return PokemonTitleHolder(itemBinding)
    }

    override fun getItemCount(): Int = list.count()

    override fun onBindViewHolder(holder: PokemonTitleHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            fragmentJump(item)
        }
        val likeButton = itemBinding.heartCardButton
        likeButton.setOnClickListener{
            GlobalScope.launch(Dispatchers.IO) {
                withContext(Dispatchers.IO) {
                    pokemonManager.catchPokemon(item)
                }
            }
        }
    }

    private fun fragmentJump(selectedPokemon: Pokemon) {
        val newFragment = PokemonDetailFragment()

        if (mContext is MainActivity) {
            val mainActivity = mContext as MainActivity
            mainActivity.pokemonSelected = selectedPokemon
            mainActivity.switchContent(R.id.searchFragment, newFragment)
        }
    }

    class PokemonTitleHolder(private val itemBinding: PokemonItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: Pokemon) {
            val image_view = itemBinding.imageView

            Glide
                .with(itemBinding.root)
                .load(item.imageUrl)
                .into(image_view)

            itemBinding.title.text = item.name
        }

    }
}