package com.example.pokemonapi.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.pokemonapi.databinding.FragmentPokemonDetailBinding
import com.example.pokemonapi.manager.PokemonManager
import com.example.pokemonapi.model.Pokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PokemonDetailFragment : Fragment() {
    lateinit var pokemon : Pokemon
    lateinit var pokemonManager : PokemonManager
    private var _binding: FragmentPokemonDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        pokemonManager = PokemonManager(requireContext())
        _binding = FragmentPokemonDetailBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.pokemonTitle.text = pokemon.name
        binding.synopsis.text = pokemon.overview
        Glide
            .with(view)
            .load(pokemon.imageUrl)
            .into(binding.pokemonImage)

        binding.buttonHeart.setOnClickListener{
            GlobalScope.launch(Dispatchers.IO) {
                withContext(Dispatchers.IO) {
                    pokemonManager.catchPokemon(pokemon)
                }
            }
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}