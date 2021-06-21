package com.example.pokemonapi.model

import com.google.gson.annotations.SerializedName

data class PokemonList (
    @SerializedName("results")
    val pokemonList: List<PokemonSearchResult>
)