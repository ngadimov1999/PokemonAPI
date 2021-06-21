package com.example.pokemonapi.model

data class RawPokemon (
    var id: Int,
    var name: String,
    var sprites: RawSprite,
    var stats: List<RawStat>
)