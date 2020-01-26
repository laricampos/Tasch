package com.test.testintact.di.components

import android.app.Application
import com.test.testintact.AndroidApplication
import com.test.testintact.di.modules.AppModule
import com.test.testintact.di.modules.ViewModelModule
import com.test.testintact.di.modules.ViewModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, ViewModule::class, ViewModelModule::class])
interface AppComponent : AndroidInjector<AndroidApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    override fun inject(application: AndroidApplication)
}