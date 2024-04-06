package ru.a1nsworth.lab3.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.a1nsworth.lab3.R
import ru.a1nsworth.lab3.databinding.PokemonAttackDetailBinding
import ru.a1nsworth.lab3.model.pokemon.Attack


class AttackAdapter : RecyclerView.Adapter<AttackAdapter.AttackHolder>() {
    var attacks: List<Attack> = arrayListOf()

    class AttackHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val bindings = PokemonAttackDetailBinding.bind(item)

        fun bind(attack: Attack) {
            bindings.attackDamage.text = attack.damage
            bindings.attackDescription.text = attack.description
            bindings.attackName.text = attack.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttackHolder {
        return AttackHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.pokemon_attack_detail, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return attacks.size
    }

    override fun onBindViewHolder(holder: AttackHolder, position: Int) {
        holder.bind(attacks[position])
    }
}