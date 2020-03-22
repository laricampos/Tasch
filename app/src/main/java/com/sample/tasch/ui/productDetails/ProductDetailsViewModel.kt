package com.sample.tasch.ui.productDetails

import androidx.lifecycle.ViewModel
import com.sample.tasch.data.model.Product
import com.sample.tasch.data.repository.WishListRepository
import javax.inject.Inject

class ProductDetailsViewModel @Inject constructor(
    private val wishListRepository: WishListRepository
) : ViewModel() {

    fun isProductIsWishList(product: Product): Boolean = wishListRepository.isProductIsWishList(product)

    fun addToWishList(product: Product) {
        wishListRepository.addToWishList(product)
    }

    fun removeFromWishList(product: Product) {
        wishListRepository.removeFromWishList(product)
    }
}