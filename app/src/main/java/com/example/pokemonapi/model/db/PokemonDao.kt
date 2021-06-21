package com.example.pokemonapi.model.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.pokemonapi.model.Pokemon


@Dao
interface PokemonDao {
    @Insert
    fun insert(pokemon: Pokemon)

    @Query("SELECT * FROM pokemon")
    fun getPokemons() : List<Pokemon>

    @Query("SELECT COUNT(*) FROM pokemon")
    fun countPokemons() : Int

    @Query("SELECT * FROM pokemon WHERE id = :id")
    fun getPokemonById(id : Int) : Pokemon

    @Query("DELETE FROM pokemon")
    fun deleteAll()

    @Query("DELETE FROM pokemon WHERE id = :id")
    fun deletePokemonById(id : Int)

    @Query("SELECT * FROM pokemon WHERE name LIKE '%' || :name || '%'")
    fun getPokemonLikeTitle(name : String) : List<Pokemon>
}
