package com.example.pokemonapi.model

import com.google.gson.annotations.SerializedName

data class RawStat(
    @SerializedName("base_stat")
    var baseStat: Int,
    var stat: RawStatInfo
)
