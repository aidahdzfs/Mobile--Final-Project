package com.D121211037.filmapp

import android.app.Application
import com.D121211037.filmapp.data.AppContainer
import com.D121211037.filmapp.data.DefaultAppContainer

class FilmsApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}