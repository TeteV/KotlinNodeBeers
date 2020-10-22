package com.example.beers.service

import android.content.Context
import com.example.beers.models.Beer

interface IBeerService {
    fun getAll(context: Context, completionHandler: (response: ArrayList<Beer>?) -> Unit)

    fun getById(context: Context, beerId: Int, completionHandler: (response: Beer?) -> Unit)

    fun deleteById(context: Context, beerId: Int, completionHandler: () -> Unit)

    fun updateBeer(context: Context, beer: Beer, completionHandler: () -> Unit)

    fun createBeer(context: Context, beer: Beer, completionHandler: () -> Unit)

}