package ru.a1nsworth.lab3.dependence

import android.content.Context
import androidx.room.Room
import ru.a1nsworth.lab3.database.AppDatabase
import ru.a1nsworth.lab3.repository.pokemon.AttackRepository
import ru.a1nsworth.lab3.repository.pokemon.PokemonRepository

object Database {
    private lateinit var applicationContext: Context
    fun init(context: Context) {
        applicationContext = context
    }

    private val appDatabase: AppDatabase by lazy {
        Room.databaseBuilder(applicationContext, AppDatabase::class.java, "pokemon.db").build()
    }

    val attackRepository: AttackRepository by lazy { AttackRepository(appDatabase.getAttackDao()) }
    val pokemonRepository: PokemonRepository by lazy {
        PokemonRepository(
            appDatabase.getPokemonDao(),
            appDatabase.getAttackDao()
        )
    }
}