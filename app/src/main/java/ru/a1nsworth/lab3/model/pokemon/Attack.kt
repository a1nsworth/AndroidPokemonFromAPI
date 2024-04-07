package ru.a1nsworth.lab3.model.pokemon

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Attack(
    val name: String = "",
    val damage: String = "",
    val description: String = "",
    @ColumnInfo(name = "pokemon_id") var pokemonId: Long?,
    @PrimaryKey(autoGenerate = true) val id: Long? = null
)

