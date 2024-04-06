package ru.a1nsworth.lab3.dao.pokemon

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ru.a1nsworth.lab3.model.pokemon.Attack

@Dao
interface AttackDao {
    @Query("SELECT * FROM attack")
    fun getAll(): List<Attack>

    @Query("SELECT * FROM attack WHERE id=:id")
    fun getById(id: Long): Attack

    @Query("SELECT * FROM attack WHERE attack.pokemon_name=:name")
    fun getByPokemonName(name: String): List<Attack>

    @Insert
    fun insert(attack: Attack)

    @Insert
    fun insert(attacks: List<Attack>)
}