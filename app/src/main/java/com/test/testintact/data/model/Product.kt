package com.test.testintact.data.model

import com.google.gson.annotations.SerializedName

data class Product(
    val name: String,
    val imageUrl: String,
    val description: String,
    @SerializedName("small_description")
    val smallDescription: String,
    val colors: List<String>,
    val size: Size,
    val price: Float,
    val quantity: Int
)

data class Size(val width: Float, val height: Float, val depth: Float)