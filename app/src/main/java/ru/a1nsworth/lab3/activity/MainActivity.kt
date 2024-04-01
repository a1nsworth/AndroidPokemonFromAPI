package ru.a1nsworth.lab3.activity

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONArray
import org.json.JSONObject
import ru.a1nsworth.lab3.databinding.ActivityMainBinding
import ru.a1nsworth.lab3.pokemon.Pokemon
import ru.a1nsworth.lab3.pokemon.PokemonAdapter
import java.io.IOException

class MainActivity : PokemonAdapter.ClickListener, AppCompatActivity() {
    private val URL = "https://api.pokemontcg.io/v2/cards"
    private lateinit var binding: ActivityMainBinding
    private val pokemonAdapter = PokemonAdapter(this)

    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initPokemonAdapter()
    }

    private fun initPokemonAdapter() {
        val request = Request.Builder().url(this.URL).build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    val pokemons: MutableList<Pokemon> = arrayListOf()

                    if (!response.isSuccessful) {
                        throw IOException("Unexpected code $response")
                    }

                    val jsonResponse = JSONObject(response.body!!.string()).getJSONArray("data")

                    for (i in 0 until jsonResponse.length()) {
                        val item = jsonResponse.getJSONObject(i)

                        val typesJson = item.getJSONArray("types")
                        val types: MutableList<String> = arrayListOf()
                        for (j in 0 until typesJson.length()) {
                            types.add(typesJson.get(j).toString())
                        }

                        val attacksJson: JSONArray
                        val attacks: MutableList<Pokemon.Attack> = arrayListOf()
                        if (item.has("attacks")) {
                            attacksJson = item.getJSONArray("attacks")
                            for (k in 0 until attacksJson.length()) {
                                val attackObj = attacksJson.getJSONObject(k)
                                attacks.add(
                                    Pokemon.Attack(
                                        attackObj.get("name").toString(),
                                        attackObj.get("text").toString(),
                                        attackObj.get("damage").toString()
                                    )
                                )
                            }
                        }
                        pokemons.add(
                            Pokemon(
                                item.get("name").toString(),
                                Bitmap.createBitmap(
                                    BitmapFactory.decodeStream(
                                        java.net.URL(
                                            item.getJSONObject(
                                                "images"
                                            ).get("small").toString()
                                        ).openStream()
                                    )
                                ),
                                item.get("hp").toString(),
                                types,
                                attacks
                            )
                        )
                    }
                    runOnUiThread {
                        pokemonAdapter.pokemons = pokemons
                        initPokemonRecyclerView()
                    }
                }

            }
        })

    }

    private fun initPokemonRecyclerView() {
        binding.apply {
            pokemonRecyclerView.layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            pokemonRecyclerView.adapter = pokemonAdapter
            Log.d("Array", pokemonAdapter.pokemons.toString())
        }
    }

    override fun onClick(pokemon: Pokemon) {
        startActivity(Intent(this, DetailActivity::class.java).apply {
            putExtra(
                "pokemon",
                pokemon
            )

            putExtra(
                "bitMapImage",
                pokemon.bitmap
            )
        })
    }
}