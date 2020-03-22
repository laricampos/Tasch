package com.sample.tasch.data.repository

import com.sample.tasch.data.model.Product
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WishListRepository @Inject constructor() {

    private val _wishList: MutableList<Product> = mutableListOf()
    val wishList: BehaviorSubject<List<Product>> = BehaviorSubject.createDefault(_wishList)

    fun addToWishList(product: Product) {
        _wishList.add(product)
        wishList.onNext(_wishList)
    }

    fun removeFromWishList(product: Product) {
        _wishList.remove(product)
        wishList.onNext(_wishList)
    }

    fun clearWishList() {
        _wishList.clear()
        wishList.onNext(_wishList)
    }

    fun isProductIsWishList(product: Product): Boolean = _wishList.contains(product)
}