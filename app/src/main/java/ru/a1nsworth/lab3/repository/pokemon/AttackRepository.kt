package ru.a1nsworth.lab3.repository.pokemon

import ru.a1nsworth.lab3.dao.pokemon.AttackDao
import ru.a1nsworth.lab3.model.pokemon.Attack

class AttackRepository(private val attackDao: AttackDao) {
    fun getAll(): List<Attack> = attackDao.getAll()
    fun getById(id: Long): Attack = attackDao.getById(id)
    fun getByPokemonName(name: String): List<Attack> = attackDao.getByPokemonName(name)
    fun insert(attack: Attack) = attackDao.insert(attack)
}