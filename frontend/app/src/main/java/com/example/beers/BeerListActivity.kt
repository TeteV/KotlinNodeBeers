package com.example.beers

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.beers.models.Beer
import com.example.beers.service.BeerServiceImpl
import com.google.android.material.floatingactionbutton.FloatingActionButton

class BeerListActivity : AppCompatActivity() {
    private lateinit var beers: ArrayList<Beer>
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: BeerAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beer_list)

        beers = ArrayList<Beer>()

        viewManager = LinearLayoutManager(this)

        viewAdapter = BeerAdapter(beers, this)


        recyclerView = findViewById<RecyclerView>(R.id.recyclerViewBeers)
        // use a linear layout manager
        recyclerView.layoutManager = viewManager

        // specify an viewAdapter (see also next example)
        recyclerView.adapter = viewAdapter

        getAllBeers()

        val fab: FloatingActionButton = findViewById(R.id.floatingActionButton)
        fab.setOnClickListener{
            val intent = Intent(this, BeerDetailActivity::class.java)
            intent.putExtra("state", "Adding")
            startActivity(intent)
        }
    }

    private fun getAllBeers() {

        val beerServiceImpl = BeerServiceImpl()
        beerServiceImpl.getAll(this) { response ->
            run {
                if (response != null) {
                    viewAdapter.beerList = response
                }
                viewAdapter.notifyDataSetChanged()
            }
        }
    }

}