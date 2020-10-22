package com.example.beers

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.beers.models.Beer
import com.example.beers.service.BeerServiceImpl
import com.example.beers.service.BeerSingleton
import com.google.android.material.textfield.TextInputEditText

class BeerDetailActivity : AppCompatActivity() {
    private lateinit var state: String
    private lateinit var textInputEditTextBrand: EditText
    private lateinit var textInputEditTextModel: EditText
    private lateinit var buttonEdit: Button
    private lateinit var buttonDelete: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beer_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)



        state = this.intent.getStringExtra("state").toString()

        val beerId = this.intent.getIntExtra("beerId", 1)

        textInputEditTextBrand = findViewById(R.id.TextInputEditTextBrand)
        textInputEditTextModel = findViewById(R.id.TextInputEditTextModel)

        buttonDelete = findViewById(R.id.buttonDelete)
        buttonDelete.setOnClickListener {
            deleteBeer(beerId)
        }

        if(state == "Showing") getBicycle(beerId)

        buttonEdit = findViewById(R.id.buttonEdit)
        buttonEdit.setOnClickListener {
            when(state){
                "Showing" -> {
                    changeButtonsToEditing()
                }
                "Editing" -> {
                    val beer = Beer(beerId, textInputEditTextBrand.text.toString(), textInputEditTextModel.text.toString())
                    updateBeer(beer)
                }
                "Adding" -> {
                    val beer = Beer(beerId, textInputEditTextBrand.text.toString(), textInputEditTextModel.text.toString())
                    createBeer(beer)
                }
            }
        }

        if(state == "Adding") changeButtonsToAdding()
    }

    private fun updateBeer(bicycle: Beer) {
        val beerServiceImpl = BeerServiceImpl()
        beerServiceImpl.updateBeer(this, bicycle) { ->
            run {
                changeButtonsToShowing(beer.id)
                val intent = Intent(this, BeerListActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun createBeer(beer: Beer) {
        val beerServiceImpl = BeerServiceImpl()
        beerServiceImpl.createBicycle(this, beer) { ->
            run {
                changeButtonsToShowing(beer.id)
                val intent = Intent(this, BeerListActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun changeButtonsToAdding() {
        buttonDelete.visibility = View.GONE
        buttonDelete.isEnabled = false
        buttonEdit.setText("New Beer")
        textInputEditTextBrand.isEnabled = true
        textInputEditTextModel.isEnabled = true
        state = "Adding"
    }

    private fun changeButtonsToShowing(beerId: Int){
        buttonDelete.visibility = View.VISIBLE
        buttonDelete.isEnabled = true
        buttonEdit.setText("Edit Beer")
        textInputEditTextBrand.isEnabled = false
        textInputEditTextModel.isEnabled = false
        state = "Showing"
    }

    private fun changeButtonsToEditing() {
        buttonDelete.visibility = View.GONE
        buttonDelete.isEnabled = false
        buttonEdit.setText("Apply changes")
        textInputEditTextBrand.isEnabled = true
        textInputEditTextModel.isEnabled = true
        state = "Editing"
    }

    private fun getBicycle(beerId: Int) {
        val beerServiceImpl = BeerServiceImpl()
        beerServiceImpl.getById(this, beerId) { response ->
            run {

                val txt_brand: TextInputEditText = findViewById(R.id.TextInputEditTextBrand)
                val txt_model: TextInputEditText = findViewById(R.id.TextInputEditTextModel)
                val img: ImageView = findViewById(R.id.imageViewBeerDetail)

                txt_brand.setText(response?.brand ?: "")
                txt_model.setText(response?.model ?: "")

                val url = BeerSingleton.getInstance(this).baseUrl + "/img/beer-"
                val imageUrl = url + (response?.id.toString() ?: "0" ) + ".jpg"
                Picasso.with(this).load(imageUrl).into(img);
            }
        }
    }

    private fun deleteBeer(beerId: Int) {
        val beerServiceImpl = BeerServiceImpl()
        beerServiceImpl.deleteById(this, beerId) { ->
            run {
                val intent = Intent(this, BeerListActivity::class.java)
                startActivity(intent)
            }
        }
    }
}