package com.example.beers.service

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.example.beers.models.Beer
import org.json.JSONObject

class BeerServiceImpl : IBeerService {
    override fun getAll(context: Context, completionHandler: (response: ArrayList<Beer>?) -> Unit) {
        val path = BeerSingleton.getInstance(context).baseUrl + "/api/beers"
        val arrayRequest = JsonArrayRequest(Request.Method.GET, path, null,
            { response ->
                var beers: ArrayList<Beer> = ArrayList()
                for (i in 0 until response.length()) {
                    val beer = response.getJSONObject(i)
                    val id = beer.getInt("id")
                    val model = beer.getString("model")
                    val brand = beer.getString("brand")
                    beers.add(Beer(id, brand, model))
                }
                completionHandler(beers)
            },
            { error ->
                completionHandler(ArrayList<Beer>())
            })
        BeerSingleton.getInstance(context).addToRequestQueue(arrayRequest)
    }

    override fun getById(context: Context, beerId: Int, completionHandler: (response: Beer?) -> Unit) {
        val path = BeerSingleton.getInstance(context).baseUrl + "/api/beers/" + beerId
        val objectRequest = JsonObjectRequest(Request.Method.GET, path, null,
            { response ->
                if(response == null) completionHandler(null)

                val id = response.getInt("id")
                val model = response.getString("model")
                val brand = response.getString("brand")

                val beer = Beer(id,brand,model)
                completionHandler(beer)
            },
            { error ->
                completionHandler(null)
            })
        BeerSingleton.getInstance(context).addToRequestQueue(objectRequest)
    }

    override fun deleteById(context: Context, beerId: Int, completionHandler: () -> Unit) {
        val path = BeerSingleton.getInstance(context).baseUrl + "/api/beers/" + beerId
        val objectRequest = JsonObjectRequest(Request.Method.DELETE, path, null,
            { response ->
                Log.v("estoy en delet", "se borró")
                completionHandler()
            },
            { error ->
                Log.v("error del", "dió error")
                completionHandler()
            })
        BeerSingleton.getInstance(context).addToRequestQueue(objectRequest)
    }

    override fun updateBeer(context: Context, beer: Beer, completionHandler: () -> Unit) {
        val path = BeerSingleton.getInstance(context).baseUrl + "/api/beers/" + beer.id
        val beerJson: JSONObject = JSONObject()
        beerJson.put("id", beer.id.toString())
        beerJson.put("brand", beer.brand)
        beerJson.put("model", beer.model)

        val objectRequest = JsonObjectRequest(Request.Method.PUT, path, beerJson,
            { response ->
                completionHandler()
            },
            { error ->
                completionHandler()
            })
        BeerSingleton.getInstance(context).addToRequestQueue(objectRequest)
    }

    override fun createBicycle(context: Context, beer: Beer, completionHandler: () -> Unit) {
        val path = BeerSingleton.getInstance(context).baseUrl + "/api/beers"
        val beerJson: JSONObject = JSONObject()
        beerJson.put("id", beer.id.toString())
        beerJson.put("brand", beer.brand)
        beerJson.put("model", beer.model)

        val objectRequest = JsonObjectRequest(Request.Method.POST, path, beerJson,
            { response -> completionHandler() },
            { error -> completionHandler() })
        BeerSingleton.getInstance(context).addToRequestQueue(objectRequest)
    }
}