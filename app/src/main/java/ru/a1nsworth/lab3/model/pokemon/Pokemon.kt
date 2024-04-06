package ru.a1nsworth.lab3.model.pokemon

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Pokemon(
    @PrimaryKey val name: String,
    val hp: Int,
    val types: List<String>,
    val image: Bitmap,
)