package com.example.pokemonapi.model

import com.google.gson.annotations.SerializedName

data class RawOtherSprite (
    @SerializedName("official-artwork")
    var officialArtwork: RawOfficialArtwork
)
