package ru.a1nsworth.lab3.pokemon

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.a1nsworth.lab3.R
import ru.a1nsworth.lab3.databinding.PokemonItemBinding

class PokemonAdapter(private val listener: ClickListener) :
    RecyclerView.Adapter<PokemonAdapter.PokemonHolder>() {

    var pokemons_names: List<String> = arrayListOf()

    interface ClickListener {
        fun onClick(pokemon_name: String)
    }

    class PokemonHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val bindings = PokemonItemBinding.bind(item)

        fun bind(pokemon_name: String, listener: ClickListener) {
//            bindings.pokemonImage.setImageBitmap(pokemon_name.bitmap)

            itemView.setOnClickListener { listener.onClick(pokemon_name) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonHolder {
        return PokemonHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.pokemon_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return pokemons_names.size
    }

    override fun onBindViewHolder(holder: PokemonHolder, position: Int) {
        holder.bind(pokemons_names[position], listener)
    }
}