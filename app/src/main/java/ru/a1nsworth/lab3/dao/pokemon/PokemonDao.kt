package ru.a1nsworth.lab3.dao.pokemon

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import ru.a1nsworth.lab3.model.pokemon.Pokemon
import ru.a1nsworth.lab3.model.pokemon.PokemonWithAttack

@Dao
interface PokemonDao {
    @Query("SELECT * FROM pokemon")
    fun getAll(): List<Pokemon>

    @Query("SELECT * FROM pokemon WHERE name=:name")
    fun getByName(name: String): Pokemon

    @Transaction
    @Query("select * from pokemon")
    fun getListPokemonWithAttack(): List<PokemonWithAttack>

    @Transaction
    @Query("select name from pokemon")
    fun getNamesPokemons(): List<String>

    @Insert
    fun insert(pokemon: Pokemon): Long

    @Insert
    fun insert(pokemons: List<Pokemon>): List<Long>
}