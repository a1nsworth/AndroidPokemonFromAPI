package ru.a1nsworth.lab3.model.pokemon

import androidx.room.Embedded
import androidx.room.Relation

data class PokemonWithAttacks(
    @Embedded val pokemon: Pokemon,
    @Relation(
        parentColumn = "id",
        entityColumn = "pokemon_id",
        entity = Attack::class
    ) val attacks: List<Attack>
) 
