package com.example.pokemonapi.model

import com.google.gson.annotations.SerializedName

data class RawOfficialArtwork (
    @SerializedName("front_default")
    var frontDefault: String
)