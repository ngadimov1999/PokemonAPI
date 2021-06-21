package com.example.pokemonapi.view

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.pokemonapi.R
import com.example.pokemonapi.manager.NetworkManager
import com.example.pokemonapi.manager.PokemonManager
import com.example.pokemonapi.model.Pokemon
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    lateinit var pokemonSelected : Pokemon

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val nm = NetworkManager(this.applicationContext)
        if (!nm.isConnectedToInternet!!) {
            toolbar.setTitle(toolbar.title.toString() + " - OFFLINE")
        }
        setSupportActionBar(toolbar)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.navigation)
        val navController = findNavController(R.id.fragment)

        bottomNavigationView.setupWithNavController(navController)
        bottomNavigationView.setSelectedItemId(R.id.allPokFragment)
        bottomNavigationView.setItemIconTintList(null)

        val pokemonManager = PokemonManager(this.baseContext)
        GlobalScope.launch(Dispatchers.IO) {
            pokemonManager.downloadPokemons()
        }

//        navController.addOnDestinationChangedListener { _, destination, _ ->
//            if(destination.id == R.id.loginFragment) {
//                bottomNavigationView.visibility = View.GONE
//            } else {
//                bottomNavigationView.visibility = View.VISIBLE
//            }
//        }
    }

     override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun switchContent(id: Int, fragment: Fragment) {
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        var rootView = this.findViewById<View>(android.R.id.content)
        ft.replace(rootView.id, fragment, fragment.toString())
        ft.addToBackStack(fragment.toString())
        ft.commit()

        (fragment as PokemonDetailFragment).pokemon = pokemonSelected
    }
}