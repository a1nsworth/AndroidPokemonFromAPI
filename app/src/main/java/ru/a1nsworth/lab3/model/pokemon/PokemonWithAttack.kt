package ru.a1nsworth.lab3.model.pokemon

import androidx.room.Embedded
import androidx.room.Relation

data class PokemonWithAttack(
    @Embedded val pokemon: Pokemon,
    @Relation(
        parentColumn = "name",
        entityColumn = "pokemon_name",
        entity = Attack::class
    ) val attacks: List<Attack>
) 
