package ru.a1nsworth.lab3.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import ru.a1nsworth.lab3.R
import ru.a1nsworth.lab3.databinding.ActivityMainBinding
import ru.a1nsworth.lab3.dependence.Database
import ru.a1nsworth.lab3.fragment.ListPokemons
import ru.a1nsworth.lab3.util.JSONPokemonParser
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private val URL = "https://api.pokemontcg.io/v2/cards"
    private lateinit var binding: ActivityMainBinding

    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Database.init(applicationContext)
        makeResponseInitDB()
        supportFragmentManager.beginTransaction()
            .replace(R.id.mainFrameLayout, ListPokemons.newInstance(DetailActivity()))
            .commit()
    }

    private fun makeResponseInitDB() {
        val request = Request.Builder().url(this.URL).build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) {
                        throw IOException("Unexpected code $response")
                    }

                    for (pokemonWithAttack in JSONPokemonParser.parse(JSONObject(response.body!!.string())))
                        Database.pokemonRepository.insert(pokemonWithAttack.pokemon, pokemonWithAttack.attacks)
                }
            }

        })
    }
}