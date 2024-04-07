package ru.a1nsworth.lab3.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ru.a1nsworth.lab3.adapter.AttackAdapter
import ru.a1nsworth.lab3.databinding.FragmentDetailInfoBinding
import ru.a1nsworth.lab3.dependence.Database
import ru.a1nsworth.lab3.model.pokemon.PokemonWithAttacks

class DetailInfo : Fragment() {
    private lateinit var binding: FragmentDetailInfoBinding
    private val attackAdapter = AttackAdapter()

    companion object {
        @JvmStatic
        fun newInstance() = DetailInfo()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailInfoBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pokemonId = requireArguments().getLong("pokemonId")
        Thread {
           val pokemonWithAttacks = Database.pokemonRepository.getPokemonWithAttacksById(pokemonId)
           requireActivity().runOnUiThread{
              bind(pokemonWithAttacks)
           }
        }.start()
    }

    private fun bind(pokemonWithAttacks: PokemonWithAttacks) {
        binding.apply {
            attackAdapter.attacks = pokemonWithAttacks.attacks
            pokemonImageDetail.setImageBitmap(pokemonWithAttacks.pokemon.image)
            pokemonHPDetail.text = pokemonWithAttacks.pokemon.hp.toString()
            pokemonNameDetail.text = pokemonWithAttacks.pokemon.name
            pokemonTypesDetail.text = pokemonWithAttacks.pokemon.types.joinToString()
            attacksRecyclerView.layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL, false
            ); attacksRecyclerView.adapter = attackAdapter }
    }
}