package br.com.nico.pokedex_android.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.nico.pokedex_android.R
import br.com.nico.pokedex_android.api.PokemonRepository
import br.com.nico.pokedex_android.domain.Pokemon

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.rvPokemonList)

        Thread(Runnable {
            loadPokemons()
        }).start()

    }

    private fun loadPokemons() {
        val pokemonsApiResult = PokemonRepository.listPokemons()

        pokemonsApiResult?.results?.let {
            val pokemons: List<Pokemon?> = it.map { pokemonResult ->
                val number = pokemonResult.url.replace("https://pokeapi.co/api/v2/pokemon/", "")
                    .replace("/", "").toInt()

                val pokemonApiResult = PokemonRepository.getPokemon(number)

                pokemonApiResult?.let {
                    Pokemon(
                        pokemonApiResult.id,
                        pokemonApiResult.name,
                        pokemonApiResult.types.map { type ->
                            type.type
                        }
                    )
                }
            }

            val layoutManager = LinearLayoutManager(this)

            recyclerView.post {
                recyclerView.layoutManager = layoutManager
                recyclerView.adapter = PokemonAdapter(pokemons)
            }
        }

    }
}