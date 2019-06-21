package com.pantaleon.imb_test

import android.app.Application
import android.content.Context
import com.pantaleon.imb_test.di.ApplicationComponent
import com.pantaleon.imb_test.di.DaggerApplicationComponent
import com.pantaleon.imb_test.di.DatabaseModule
import com.pantaleon.imb_test.di.NetworkModule

class MoviesApp : Application() {

    private lateinit var component: ApplicationComponent

    companion object {
        fun getAppComponent(context: Context) = (context.applicationContext as MoviesApp).component
    }

    override fun onCreate() {
        super.onCreate()
        component = DaggerApplicationComponent.builder()
            .networkModule(NetworkModule(this))
            .databaseModule(DatabaseModule(this))
            .build()
    }
}