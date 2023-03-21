package br.com.nico.pokedex_android.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.nico.pokedex_android.R
import br.com.nico.pokedex_android.domain.Pokemon
import br.com.nico.pokedex_android.domain.PokemonType

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.rvPokemonList)

        val layoutManager = LinearLayoutManager(this)

        val bulbasaur = Pokemon(
            "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/001.png",
            1,
            "Bulbasaur",
            listOf(PokemonType("Grass"), PokemonType("Poison"))
        )

        val pokemons = listOf(bulbasaur, bulbasaur, bulbasaur)

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = PokemonAdapter(pokemons)

    }
}