package com.example.pokemonapi.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pokemonapi.model.Pokemon
import com.example.pokemonapi.model.CaughtPokemon

@Database(entities = arrayOf(Pokemon::class, CaughtPokemon::class), version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao
    abstract fun caughtPokemonDao(): CaughtPokemonDao

    companion object {
        fun createDb(contex: Context) =
            Room.databaseBuilder(contex, AppDatabase::class.java, "pokemonapi").build()
    }
}