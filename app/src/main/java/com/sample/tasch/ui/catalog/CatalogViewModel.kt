package com.sample.tasch.ui.catalog

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.sample.tasch.data.model.Product
import com.sample.tasch.data.repository.CatalogRepository
import com.sample.tasch.data.repository.WishListRepository
import com.sample.tasch.extensions.runInBackground
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