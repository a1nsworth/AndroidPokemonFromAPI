package ru.a1nsworth.lab3.dao.pokemon

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import ru.a1nsworth.lab3.model.pokemon.Attack
import ru.a1nsworth.lab3.model.pokemon.Pokemon
import ru.a1nsworth.lab3.model.pokemon.PokemonWithAttacks

@Dao
interface PokemonDao {
    @Query("select * from pokemon")
    fun getAll(): List<Pokemon>

    @Query("select * from pokemon where name=:name")
    fun getByName(name: String): Pokemon

    @Transaction
    @Query("select * from pokemon")
    fun getListPokemonWithAttacks(): List<PokemonWithAttacks>

    @Transaction
    @Query("select name from pokemon")
    fun getNamesPokemons(): List<String>

    @Transaction
    @Query("select * from pokemon where name=:name")
    fun getPokemonWithAttacksByName(name: String): PokemonWithAttacks

    @Transaction
    @Query("select * from pokemon where id = :id")
    fun getPokemonWithAttacksById(id: Long): PokemonWithAttacks

    @Transaction
    @Query("select * from pokemon")
    fun getListPokemons(): List<Pokemon>

    @Transaction
    @Query("select * from attack where pokemon_id = :id")
    fun getAttackPokemonById(id: Long): List<Attack>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(pokemon: Pokemon): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(pokemons: List<Pokemon>): List<Long>
}