package com.test.testintact.data.repository

import com.test.testintact.data.model.Product
import com.test.testintact.data.model.Size
import org.junit.Assert
import org.junit.Test

class WishListRepositoryTest {

    private val wishListRepository = WishListRepository()

    private val product = Product(
        "bag",
        "url",
        "description",
        "small_description",
        emptyList(),
        Size(1f, 1f, 1f),
        3f,
        3
    )

    @Test
    fun `Product is added to list`() {
        wishListRepository.addToWishList(product)
        wishListRepository.wishList.test()
            .assertNotTerminated()
            .assertValue {
                it[0] == product && it.size == 1
            }
    }

    @Test
    fun `Product is removed from list`() {
        wishListRepository.addToWishList(product)
        wishListRepository.removeFromWishList(product)
        wishListRepository.wishList.test()
            .assertNotTerminated()
            .assertValue {
                it.isEmpty()
            }
    }

    @Test
    fun `The list is cleared`() {
        wishListRepository.addToWishList(product)
        wishListRepository.clearWishList()
        wishListRepository.wishList.test()
            .assertNotTerminated()
            .assertValue {
                it.isEmpty()
            }
    }

    @Test
    fun `Product is in wish list`() {
        Assert.assertEquals(false, wishListRepository.isProductIsWishList(product))
        wishListRepository.addToWishList(product)
        Assert.assertEquals(true, wishListRepository.isProductIsWishList(product))
    }
}