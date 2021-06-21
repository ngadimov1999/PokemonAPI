package com.example.pokemonapi.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName = "caught_pokemon", foreignKeys = [
    ForeignKey(entity = Pokemon::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("pokemon_id"),
        onDelete = CASCADE)]
)
data class CaughtPokemon (
    @ColumnInfo(name = "pokemon_id")
    val pokemon_id: Int,

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
)