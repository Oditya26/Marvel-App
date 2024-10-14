package com.example.marvelheroes

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marvelheroes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val list = ArrayList<Character>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Inisialisasi View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mengatur padding untuk edge-to-edge
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Menyiapkan RecyclerView
        binding.rvCharacters.setHasFixedSize(true)
        list.addAll(getListCharacters())
        showRecyclerList()
    }

    private fun getListCharacters(): ArrayList<Character> {
        val dataName = resources.getStringArray(R.array.data_char)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.getStringArray(R.array.data_photo)
        return ArrayList(dataName.indices.map {
            Character(dataName[it], dataDescription[it], dataPhoto[it])
        })
    }

    private fun showRecyclerList() {
        binding.rvCharacters.layoutManager = LinearLayoutManager(this)
        val listCharacterAdapter = ListCharacterAdapter(list)
        binding.rvCharacters.adapter = listCharacterAdapter

        // Mengatur klik item pada adapter
        listCharacterAdapter.setOnItemClickCallback(object : ListCharacterAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Character) {
                showSelectedCharacter(data)
            }
        })
    }

    private fun showSelectedCharacter(character: Character) {
        val moveWithObjectIntent = Intent(this@MainActivity, DetailActivity::class.java)
        moveWithObjectIntent.putExtra(DetailActivity.EXTRA_CHARACTER, character)
        startActivity(moveWithObjectIntent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true // Changed to true for a more standard return value
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_about -> {
                val moveIntent = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(moveIntent)
                true // Return true to indicate the menu item click was handled
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
