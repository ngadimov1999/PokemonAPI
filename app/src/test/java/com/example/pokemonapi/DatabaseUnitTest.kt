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
    fun insert_anime_isCorrect() {
        val anime = Pokemon("18507", "Free!", 0, "Haruka Nanase has a love for water and a passion for swimming. In elementary school, he competed in and won a relay race with his three friends Rin Matsuoka, Nagisa Hazuki, and Makoto Tachibana. After...",
                            "7.36", "https:\\/\\/cdn.myanimelist.net\\/images\\/anime\\/6\\/51107.jpg?s=277f33627dad8f3f349c2ac234138ca6")
        GlobalScope.launch(Dispatchers.IO) {
            pokemonDao.insert(anime)
            val added_anime = pokemonDao.getAnimeByMalId("18507")
            pokemonDao.deleteAnimeById(added_anime.id)
            assertEquals(anime.name, added_anime.title)
        }
    }
}