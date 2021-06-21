package com.example.pokemonapi.manager

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapi.model.Pokemon
import com.example.pokemonapi.view.PokemonAdapter

class UiHelper {
    companion object {
        fun updateAdapter(view: View, rv: RecyclerView, pokemons: List<Pokemon>?) {
            val adapter = PokemonAdapter()

            rv.layoutManager = GridLayoutManager(view.context, 2)
            rv.adapter = adapter

            if (pokemons != null) {
                adapter.update(pokemons)
            }
        }
    }
}