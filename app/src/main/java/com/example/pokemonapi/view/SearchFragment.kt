package com.example.pokemonapi.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pokemonapi.databinding.FragmentSearchBinding
import com.example.pokemonapi.manager.PokemonManager
import com.example.pokemonapi.manager.NetworkManager
import com.example.pokemonapi.manager.UiHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    lateinit var pokemonManager: PokemonManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        pokemonManager = PokemonManager(requireContext())

        val view = binding.root

        binding.searchButton.setOnClickListener {
            val textField = binding.searchField
            val nm = NetworkManager(view.context)
            val connected = nm.isConnectedToInternet

            GlobalScope.launch(Dispatchers.IO) {
                val pokemonName = textField.text.toString()
                val pokemons = pokemonManager.getPokemonByName(pokemonName)

                withContext(Dispatchers.Main) {
                    UiHelper.updateAdapter(view, binding.recView, pokemons)
                }
            }
        }

        return view
    }
}