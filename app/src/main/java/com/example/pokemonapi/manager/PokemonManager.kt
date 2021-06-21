package com.example.pokemonapi.manager

import android.content.Context
import com.example.pokemonapi.model.Pokemon
import com.example.pokemonapi.model.CaughtPokemon
import com.example.pokemonapi.model.db.AppDatabase
import com.example.pokemonapi.model.db.CaughtPokemonDao
import com.example.pokemonapi.model.db.PokemonDao
import com.example.pokemonapi.network.ApiService

class PokemonManager(context: Context) {
    private var caughtPokemonDao: CaughtPokemonDao? = AppDatabase.createDb(context).caughtPokemonDao()
    private var pokemonDao: PokemonDao? = AppDatabase.createDb(context).pokemonDao()

    suspend fun downloadPokemons() {
        if (pokemonDao?.countPokemons()!! > 0) {
            return
        }

        val apiService = ApiService.instance()
        val pokemonList = apiService.getAllPokemonNames().body()?.pokemonList!!

        for(pokemonSearchResult in pokemonList) {
            val rawPokemon = apiService.getPokemon(pokemonSearchResult.name).body()
            val pokemon = Pokemon()
            if (rawPokemon != null) {
                pokemon.id = rawPokemon.id
                pokemon.name = rawPokemon.name
                pokemon.overview = ""
                for(stat in rawPokemon.stats){
                    pokemon.overview += "${stat.stat.name}: ${stat.baseStat}\n"
                }
                pokemon.imageUrl = rawPokemon.sprites.other.officialArtwork.frontDefault

                savePokemonToDb(pokemon)
            }
        }
    }

    fun catchPokemon(item: Pokemon) {
        val pokemon = pokemonDao?.getPokemonById(item.id)
        if (pokemon != null) {
            val caughtPokemon = caughtPokemonDao?.getCaughtPokemon(item.id)
            if (caughtPokemon == null) {
                caughtPokemonDao?.insert(CaughtPokemon(pokemon.id))
            } else {
                caughtPokemonDao?.deletePokemon(caughtPokemon.id)
            }
        }
    }

    fun getPokemonByName(pokemonName: String): List<Pokemon>? {
        return pokemonDao?.getPokemonLikeTitle(pokemonName)
    }

    private fun savePokemonToDb(pokemon: Pokemon) {
        val foundPokemon = pokemonDao?.getPokemonById(pokemon.id)
        if (foundPokemon == null) {
            pokemonDao!!.insert(pokemon)
        }
    }

    fun getCaughtPokemons(): List<Pokemon>? {
        return convertCaughtPokemonsToPokemons(caughtPokemonDao?.getCaughtPokemons())
    }

    private fun convertCaughtPokemonsToPokemons(caughtPokemons: List<CaughtPokemon>?): List<Pokemon> {
        val pokemons = mutableListOf<Pokemon>()
        if (caughtPokemons != null) {
            for (pokemon in caughtPokemons) {
                pokemons.add(pokemonDao!!.getPokemonById(pokemon.pokemon_id))
            }
        }
        return pokemons
    }
}