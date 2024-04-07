package ru.a1nsworth.lab3.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ru.a1nsworth.lab3.adapter.AttackAdapter
import ru.a1nsworth.lab3.databinding.ActivityDetailBinding
import ru.a1nsworth.lab3.dependence.Database
import ru.a1nsworth.lab3.model.pokemon.PokemonWithAttacks

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val attackAdapter = AttackAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pokemonId = intent.getSerializableExtra("pokemonId") as Long
        var pokemonWithAttacks: PokemonWithAttacks
        Thread {
            pokemonWithAttacks = Database.pokemonRepository.getPokemonWithAttacksById(pokemonId)

            runOnUiThread {
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
                this@DetailActivity,
                LinearLayoutManager.VERTICAL, false
            )
            attacksRecyclerView.adapter = attackAdapter
        }
    }
}
