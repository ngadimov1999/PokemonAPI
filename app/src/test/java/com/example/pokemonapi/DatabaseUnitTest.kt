package com.example.pokemonapi

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.example.pokemonapi.model.Pokemon
import com.example.pokemonapi.model.db.PokemonDao
import com.example.pokemonapi.model.db.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class DatabaseUnitTest {
    private lateinit var pokemonDao: PokemonDao

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext;
        pokemonDao = AppDatabase.createDb(context).pokemonDao()
    }

    @Test
    fun insert_pokemon_isCorrect() {
        val anime = Pokemon(
            "bulbasaur",
            1,
            "0",
            "https:\\/\\/raw.githubusercontent.com\\/PokeAPI\\/sprites\\/master\\/sprites\\/pokemon\\/1.png"
        )
        GlobalScope.launch(Dispatchers.IO) {
            pokemonDao.insert(anime)
            val added_pok = pokemonDao.getPokemonById(1)
            pokemonDao.deletePokemonById(added_pok.id)
            assertEquals(anime.name, added_pok.name)
        }
    }
}