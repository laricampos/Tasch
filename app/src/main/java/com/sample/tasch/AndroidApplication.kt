package com.sample.tasch

import com.sample.tasch.di.components.DaggerAppComponent
import dagger.android.support.DaggerApplication

class AndroidApplication: DaggerApplication() {

    override fun applicationInjector() = DaggerAppComponent.builder().application(this).build()
}