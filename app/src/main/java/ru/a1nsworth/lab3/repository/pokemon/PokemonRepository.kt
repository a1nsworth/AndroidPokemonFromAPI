package ru.a1nsworth.lab3.repository.pokemon

import ru.a1nsworth.lab3.dao.pokemon.AttackDao
import ru.a1nsworth.lab3.dao.pokemon.PokemonDao
import ru.a1nsworth.lab3.model.pokemon.Attack
import ru.a1nsworth.lab3.model.pokemon.Pokemon
import ru.a1nsworth.lab3.model.pokemon.PokemonWithAttacks

class PokemonRepository(private val pokemonDao: PokemonDao, private val attackDao: AttackDao) {
    fun getAll(): List<Pokemon> = pokemonDao.getAll()
    fun getByName(name: String): Pokemon = pokemonDao.getByName(name)
    fun getListPokemonWithAttacks(): List<PokemonWithAttacks> = pokemonDao.getListPokemonWithAttacks()
    fun getPokemonWithAttacksById(id: Long): PokemonWithAttacks = pokemonDao.getPokemonWithAttacksById(id)
    fun getListPokemons(): List<Pokemon> = pokemonDao.getListPokemons()
    fun getAttacksPokemonById(id: Long): List<Attack> = pokemonDao.getAttackPokemonById(id)
    fun getNamesPokemons(): List<String> = pokemonDao.getNamesPokemons()
    fun insert(pokemon: Pokemon) = pokemonDao.insert(pokemon)

    fun insert(pokemon: Pokemon, attacks: List<Attack>) {
        val id = insert(pokemon)

        for (attack in attacks) {
            attack.pokemonId = id
            attackDao.insert(attack)
        }
    }

}