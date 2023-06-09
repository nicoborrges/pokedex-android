package br.com.nico.pokedex_android.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.nico.pokedex_android.R
import br.com.nico.pokedex_android.domain.Pokemon
import com.squareup.picasso.Picasso

class PokemonAdapter(
    private val items: List<Pokemon?>
) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.bindView(item)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(item: Pokemon?) = with(itemView) {
            val ivPokemon = findViewById<ImageView>(R.id.ivPokemon)
            val tvNumber = findViewById<TextView>(R.id.tvPokemonNumber)
            val tvName = findViewById<TextView>(R.id.tvPokemonName)
            val ivType1 = findViewById<ImageView>(R.id.ivType1)
            val ivType2 = findViewById<ImageView>(R.id.ivType2)

            // TODO: Load image with Glide

            item?.let {
                Picasso.get().load(it.imageUrl).into(ivPokemon)
                //Log.d("POKEMON_API", item.imageUrl)
                //Log.d("POKEMON_API", it.imageUrl)
                //https://assets.pokemon.com/assets/cms2/img/pokedex/full/002.png

                tvNumber.text = "Nº ${item.formattedNumber}"
                tvName.text = item.formattedName

                //https://veekun.com/dex/media/types/en/bug.png
                //"https://veekun.com/dex/media/types/en/${item.types[0].name}.png"

                //tvType1.text = item.types[0].name.capitalize()

                Picasso.get()
                    .load("https://veekun.com/dex/media/types/en/${item.types[0].name}.png")
                    .into(ivType1)
                if (item.types.size > 1) {
                    ivType2.visibility = View.VISIBLE
                    Picasso.get()
                        .load("https://veekun.com/dex/media/types/en/${item.types[1].name}.png")
                        .into(ivType2)
                    //tvType2.text = item.types[1].name.capitalize()
                } else {
                    ivType2.visibility = View.GONE
                }
            }

        }
    }
}
