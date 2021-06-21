package com.example.pokemonapi.model.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.pokemonapi.model.CaughtPokemon;


@Dao
interface CaughtPokemonDao {
    @Insert
    fun insert(caughtPokemon: CaughtPokemon)

    @Query("DELETE FROM caught_pokemon")
    fun deleteAll()

    @Query("DELETE FROM caught_pokemon WHERE id = :id")
    fun deletePokemon(id : Int)

    @Query("SELECT * FROM caught_pokemon")
    fun getCaughtPokemons() : List<CaughtPokemon>

    @Query("SELECT * FROM caught_pokemon WHERE pokemon_id = :id LIMIT 1")
    fun getCaughtPokemon(id: Int) : CaughtPokemon
}
