package com.test.testintact

import com.test.testintact.di.components.DaggerAppComponent
import dagger.android.support.DaggerApplication

class AndroidApplication: DaggerApplication() {

    override fun applicationInjector() = DaggerAppComponent.builder().application(this).build()
}