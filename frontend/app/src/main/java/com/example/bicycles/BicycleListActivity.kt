package com.example.bicycles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bicycles.models.Bicycle

class BicycleListActivity : AppCompatActivity() {
    lateinit var bicycles: ArrayList<Bicycle>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bicycle_list)

        bicycles = ArrayList<Bicycle>()

        recyclerView = findViewById<RecyclerView>(R.id.RecyclerView)

        RecyclerView.LayoutManager= LinearLayoutManager(this)

        recyclerView.adapter = BicycleAdapter(bicycles)

        getAllBicycles()

        (recyclerView.adapter as BicycleAdapter).notifyDataSetChanged()
    }

    private fun getAllBicycles() {
        bicycles = ArrayList<Bicycle>()

        bicycles.add(Bicycle("bh", "star"))
        bicycles.add(Bicycle("oo", "que fue"))
        bicycles.add(Bicycle("lo que", "sea"))
    }
}