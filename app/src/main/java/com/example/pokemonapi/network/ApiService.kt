package com.example.pokemonapi.network;

import com.example.pokemonapi.model.Pokemon
import com.example.pokemonapi.model.PokemonList
import com.example.pokemonapi.model.PokemonSearchResult
import com.example.pokemonapi.model.RawPokemon
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("pokemon")
    suspend fun getAllPokemonNames(@Query("limit") limit: Int = 9999) : Response<PokemonList>

    @GET("pokemon/{name}")
    suspend fun getPokemon(@Path("name") name: String) : Response<RawPokemon>

    companion object {
        const val API_URL = "https://pokeapi.co/api/v2/"

        fun instance() = Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}