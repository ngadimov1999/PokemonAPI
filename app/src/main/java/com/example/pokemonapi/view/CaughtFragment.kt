package com.example.pokemonapi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pokemonapi.databinding.FragmentCaughtBinding
import com.example.pokemonapi.manager.PokemonManager
import com.example.pokemonapi.manager.UiHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class CaughtFragment : Fragment() {
    private var _binding: FragmentCaughtBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    lateinit var pokemonManager: PokemonManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCaughtBinding.inflate(inflater, container, false)
        val view = binding.root

        pokemonManager = PokemonManager(requireContext())

        GlobalScope.launch(Dispatchers.IO) {
            val pokemons = pokemonManager.getCaughtPokemons()
            withContext(Dispatchers.Main) {
                UiHelper.updateAdapter(view, binding.recView, pokemons)
            }
        }
        return view
    }
}