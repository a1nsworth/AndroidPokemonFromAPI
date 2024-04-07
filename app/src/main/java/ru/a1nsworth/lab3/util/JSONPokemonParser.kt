package ru.a1nsworth.lab3.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import org.json.JSONArray
import org.json.JSONObject
import ru.a1nsworth.lab3.model.pokemon.Attack
import ru.a1nsworth.lab3.model.pokemon.Pokemon
import ru.a1nsworth.lab3.model.pokemon.PokemonWithAttacks
import java.net.URL

object JSONPokemonParser {
    fun parse(json: JSONObject) = iterator {
        val jsonResponse = json.getJSONArray("data")

        for (i in 0 until jsonResponse.length()) {
            val item = jsonResponse.getJSONObject(i)

            val typesJson = item.getJSONArray("types")
            val types: MutableList<String> = arrayListOf()
            for (j in 0 until typesJson.length()) {
                types.add(typesJson.get(j).toString())
            }

            val attacksJson: JSONArray
            val attacks: MutableList<Attack> = arrayListOf()
            if (item.has("attacks")) {
                attacksJson = item.getJSONArray("attacks")
                for (k in 0 until attacksJson.length()) {
                    val attackObj = attacksJson.getJSONObject(k)
                    attacks.add(
                        Attack(
                            attackObj.get("name").toString(),
                            attackObj.get("damage").toString(),
                            attackObj.get("text").toString(),
                            null
                        )
                    )
                }
            }

            yield(
                PokemonWithAttacks(
                    Pokemon(
                        item.get("name").toString(),
                        item.get("hp").toString().toInt(),
                        types.toList(),
                        Bitmap.createBitmap(
                            BitmapFactory.decodeStream(
                                URL(
                                    item.getJSONObject(
                                        "images"
                                    ).get("small").toString()
                                ).openStream()
                            )
                        ),
                    ), attacks
                )
            )
        }
    }
}