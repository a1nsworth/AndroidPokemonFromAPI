package ru.a1nsworth.lab3.repository.pokemon

import androidx.room.Transaction
import ru.a1nsworth.lab3.dao.pokemon.AttackDao
import ru.a1nsworth.lab3.dao.pokemon.PokemonDao
import ru.a1nsworth.lab3.model.pokemon.Attack
import ru.a1nsworth.lab3.model.pokemon.Pokemon
import ru.a1nsworth.lab3.model.pokemon.PokemonWithAttack

class PokemonRepository(private val pokemonDao: PokemonDao, private val attackDao: AttackDao) {
    fun getAll(): List<Pokemon> = pokemonDao.getAll()
    fun getByName(name: String): Pokemon = pokemonDao.getByName(name)
    fun getPokemonWithAttack(): List<PokemonWithAttack> = pokemonDao.getListPokemonWithAttack()
    fun getNamesPokemons(): List<String> = pokemonDao.getNamesPokemons()
    fun insert(pokemon: Pokemon) = pokemonDao.insert(pokemon)

    @Transaction
    fun insert(pokemon: Pokemon, attacks: List<Attack>) {
        val id = insert(pokemon)

        for (attack in attacks) {
            attack.id = id
            attackDao.insert(attack)
        }
    }

}