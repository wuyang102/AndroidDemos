package com.daniel.android.demo

import java.lang.Exception

/**
 * @author wuyang
 */
class Graph {

    companion object {

        private var appComponent: AppComponent? = null

        fun init(appComponent: AppComponent) {
            this.appComponent = appComponent
        }

        fun appComponent(): AppComponent {
            return appComponent ?: throw Exception("Graph is not inited")
        }
    }
}