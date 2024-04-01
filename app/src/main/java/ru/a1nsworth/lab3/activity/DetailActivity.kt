package ru.a1nsworth.lab3.activity

import android.graphics.Bitmap
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import ru.a1nsworth.lab3.R
import ru.a1nsworth.lab3.databinding.ActivityDetailBinding
import ru.a1nsworth.lab3.databinding.ActivityMainBinding
import ru.a1nsworth.lab3.pokemon.AttackAdapter
import ru.a1nsworth.lab3.pokemon.Pokemon

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val attackAdapter = AttackAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pokemon = intent.getSerializableExtra("pokemon") as Pokemon
        pokemon.bitmap = intent.getParcelableExtra("bitMapImage")!!
        attackAdapter.attacks = pokemon.attacks

        bind(pokemon)
    }

    private fun bind(pokemon: Pokemon) {
        binding.apply {
            pokemonImageDetail.setImageBitmap(pokemon.bitmap)
            pokemonHPDetail.text = pokemon.hp
            pokemonNameDetail.text = pokemon.name
            pokemonTypesDetail.text = pokemon.types.joinToString()
            attacksRecyclerView.layoutManager = LinearLayoutManager(
                this@DetailActivity,
                LinearLayoutManager.VERTICAL, false
            )
            attacksRecyclerView.adapter = attackAdapter
        }
    }
}
