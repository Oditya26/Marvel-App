package com.example.marvelheroes

import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.marvelheroes.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    companion object {
        const val EXTRA_CHARACTER = "extra_character"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Inisialisasi View Binding
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mengatur padding untuk edge-to-edge
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Mengambil data karakter dari intent
        val dataCharacter = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(EXTRA_CHARACTER, Character::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Character>(EXTRA_CHARACTER)
        }

        // Menampilkan data karakter
        dataCharacter?.let { character ->
            binding.tvCharacterName.text = character.name
            binding.tvCharacterDescription.text = character.description
            Glide.with(this).load(character.photo).into(binding.imageCharacter)
        }
    }
}
