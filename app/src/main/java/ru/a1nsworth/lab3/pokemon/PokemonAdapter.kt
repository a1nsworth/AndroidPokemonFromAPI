package ru.a1nsworth.lab3.pokemon

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.a1nsworth.lab3.R
import ru.a1nsworth.lab3.databinding.PokemonItemBinding

class PokemonAdapter(private val listener: ClickListener) :
    RecyclerView.Adapter<PokemonAdapter.PokemonHolder>() {
    var pokemons: List<Pokemon> = arrayListOf()

    interface ClickListener {
        fun onClick(pokemon: Pokemon)
    }

    class PokemonHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val bindings = PokemonItemBinding.bind(item)

        fun bind(pokemon: Pokemon, listener: ClickListener) {
            bindings.pokemonImage.setImageBitmap(pokemon.bitmap)

            itemView.setOnClickListener { listener.onClick(pokemon) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonHolder {
        return PokemonHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.pokemon_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return pokemons.size
    }

    override fun onBindViewHolder(holder: PokemonHolder, position: Int) {
        holder.bind(pokemons[position], listener)
    }
}