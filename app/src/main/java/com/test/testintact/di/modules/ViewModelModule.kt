package com.test.testintact.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.testintact.di.ViewModelFactory
import com.test.testintact.di.ViewModelKey
import com.test.testintact.ui.catalog.CatalogViewModel
import com.test.testintact.ui.productDetails.ProductDetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CatalogViewModel::class)
    abstract fun bindCatalogViewModel(viewModel: CatalogViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProductDetailsViewModel::class)
    abstract fun bindProductDetailsViewModel(viewModel: ProductDetailsViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}