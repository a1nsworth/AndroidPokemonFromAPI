package ru.a1nsworth.lab3.model.pokemon

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            Pokemon::class,
            parentColumns = ["name"],
            childColumns = ["pokemon_name"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class Attack(
    val name: String,
    val damage: String,
    val description: String = "",
    @ColumnInfo(name = "pokemon_name") val pokemonName: String?,
    @PrimaryKey(autoGenerate = true) var id: Long = 0
)

