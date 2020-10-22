package com.example.beers

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.beers.models.Beer

class BeerAdapter(var beerList: ArrayList<Beer>, val context: Context) : RecyclerView.Adapter<BeerAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.beer_list_row, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(beerList[position], context)
    }

    override fun getItemCount(): Int {
        return beerList.size;
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(b: Beer, context: Context){
            val url = "http://192.168.0.10:8080/img/beer-"
            val txt_brand: TextView = itemView.findViewById(R.id.textViewBrand)
            val txt_model: TextView = itemView.findViewById(R.id.textViewModel)
            val img: ImageView = itemView.findViewById(R.id.imageViewBeer)

            txt_brand.text = b.brand
            txt_model.text = b.model

            val imageUrl = url + b.id.toString() + ".jpg"
            Picasso.with(context).load(imageUrl).into(img);

            itemView.setOnClickListener {
                val intent = Intent(context, BeerDetailActivity::class.java)
                intent.putExtra("beerId", b.id)
                intent.putExtra("state", "Showing")
                Log.v("Esto es antes", b.id.toString())
                context.startActivity(intent)
            }
        }
    }
}