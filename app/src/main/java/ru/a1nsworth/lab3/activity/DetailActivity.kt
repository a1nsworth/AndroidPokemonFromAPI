package ru.a1nsworth.lab3.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.a1nsworth.lab3.R
import ru.a1nsworth.lab3.databinding.ActivityDetailBinding
import ru.a1nsworth.lab3.fragment.DetailInfo

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragment = DetailInfo.newInstance()
        val bundle = Bundle()
        bundle.putLong("pokemonId",intent.getLongExtra("pokemonId", 0))
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction()
            .replace(R.id.detailInfoFragment, fragment).commit()
    }
}
