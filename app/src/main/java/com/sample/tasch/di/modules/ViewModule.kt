package com.sample.tasch.di.modules

import com.sample.tasch.ui.MainActivity
import com.sample.tasch.ui.catalog.CatalogFragment
import com.sample.tasch.ui.productDetails.ProductDetailsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ViewModule {

    // Activities

    @ContributesAndroidInjector
    fun mainActivity(): MainActivity

    // Fragments

    @ContributesAndroidInjector
    fun catalogFragment(): CatalogFragment

    @ContributesAndroidInjector
    fun productDetailsFragment(): ProductDetailsFragment
}