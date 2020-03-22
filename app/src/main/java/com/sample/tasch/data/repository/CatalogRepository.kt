package com.sample.tasch.data.repository

import android.content.Context
import com.google.gson.Gson
import com.sample.tasch.data.model.Product
import io.reactivex.Single
import javax.inject.Inject

private const val CATALOG_FILE = "products.json"

class CatalogRepository @Inject constructor(private val gson: Gson, private val context: Context) {

    val catalog: Single<List<Product>> = Single.fromCallable {
        val productsJsonString = context.assets.open(CATALOG_FILE).bufferedReader().use {
            it.readText()
        }

        gson.fromJson(productsJsonString, Array<Product>::class.java).toList()
    }
}