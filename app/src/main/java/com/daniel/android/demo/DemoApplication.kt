package com.daniel.android.demo

import android.app.Application

/**
 * @author wuyang
 */
class DemoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        val appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()
        Graph.init(appComponent)
    }
}