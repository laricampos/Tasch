package com.test.testintact.ui.catalog

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.test.testintact.data.model.Product
import com.test.testintact.data.repository.CatalogRepository
import com.test.testintact.data.repository.WishListRepository
import com.test.testintact.extensions.runInBackground
import io.reactivex.BackpressureStrategy
import javax.inject.Inject

class CatalogViewModel @Inject constructor(
    private val catalogRepository: CatalogRepository,
    private val wishListRepository: WishListRepository
) : ViewModel() {

    val catalog: LiveData<List<Product>> by lazy {
        LiveDataReactiveStreams.fromPublisher(
            catalogRepository.catalog
                .runInBackground()
                .toFlowable()
        )
    }

    val wishList: LiveData<List<Product>> by lazy {
        LiveDataReactiveStreams.fromPublisher(
            wishListRepository.wishList
                .runInBackground()
                .toFlowable(BackpressureStrategy.LATEST)
        )
    }

    fun checkout() {
        wishListRepository.clearWishList()
    }
}