package com.test.testintact.di.modules

import com.test.testintact.ui.MainActivity
import com.test.testintact.ui.catalog.CatalogFragment
import com.test.testintact.ui.productDetails.ProductDetailsFragment
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