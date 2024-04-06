package ru.a1nsworth.lab3.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.a1nsworth.lab3.converter.Converter
import ru.a1nsworth.lab3.dao.pokemon.AttackDao
import ru.a1nsworth.lab3.dao.pokemon.PokemonDao
import ru.a1nsworth.lab3.model.pokemon.Attack
import ru.a1nsworth.lab3.model.pokemon.Pokemon

@Database(
    version = 1,
    entities = [
        Pokemon::class,
        Attack::class
    ]
)
@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getPokemonDao(): PokemonDao
    abstract fun getAttackDao(): AttackDao
}