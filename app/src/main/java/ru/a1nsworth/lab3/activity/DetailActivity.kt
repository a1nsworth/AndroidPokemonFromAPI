package ru.a1nsworth.lab3.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ru.a1nsworth.lab3.databinding.ActivityDetailBinding
import ru.a1nsworth.lab3.pokemon.AttackAdapter

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val attackAdapter = AttackAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pokemon = intent.getSerializableExtra("pokemon") as String
        pokemon.bitmap = intent.getParcelableExtra("bitMapImage")!!
        attackAdapter.attacks = pokemon.attacks

        bind(pokemon)
    }

    private fun bind(pokemonWithAttack: String) {
        binding.apply {
            pokemonImageDetail.setImageBitmap(pokemonWithAttack.bitmap)
            pokemonHPDetail.text = pokemonWithAttack.hp
            pokemonNameDetail.text = pokemonWithAttack.name
            pokemonTypesDetail.text = pokemonWithAttack.types.joinToString()
            attacksRecyclerView.layoutManager = LinearLayoutManager(
                this@DetailActivity,
                LinearLayoutManager.VERTICAL, false
            )
            attacksRecyclerView.adapter = attackAdapter
        }
    }
}
