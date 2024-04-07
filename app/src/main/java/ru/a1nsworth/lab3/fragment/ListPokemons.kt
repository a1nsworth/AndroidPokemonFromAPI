package ru.a1nsworth.lab3.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ru.a1nsworth.lab3.adapter.PokemonAdapter
import ru.a1nsworth.lab3.databinding.FragmentListPokemonsBinding
import ru.a1nsworth.lab3.dependence.Database
import ru.a1nsworth.lab3.model.pokemon.Pokemon

class ListPokemons(private val intentActivity: Activity) :
    PokemonAdapter.ClickListener, Fragment() {
    private lateinit var binding: FragmentListPokemonsBinding
    private val pokemonAdapter = PokemonAdapter(this)

    companion object {
        @JvmStatic
        fun newInstance(intentActivity: Activity) = ListPokemons(intentActivity)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListPokemonsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Thread {
            pokemonAdapter.pokemon = Database.pokemonRepository.getListPokemons()
            requireActivity().runOnUiThread {
                bindPokemonRecyclerView()
            }
        }.start()
    }

    override fun onClick(pokemon: Pokemon) {
        startActivity(Intent(context, intentActivity::class.java).apply {
            putExtra(
                "pokemonId",
                pokemon.id
            )
        })
    }

    private fun bindPokemonRecyclerView() {
        binding.apply {
            pokemonRecyclerView.layoutManager =
                LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
            pokemonRecyclerView.adapter = pokemonAdapter
        }
    }
}