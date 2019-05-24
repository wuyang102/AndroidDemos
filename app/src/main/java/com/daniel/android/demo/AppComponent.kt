package com.daniel.android.demo

import android.app.Application
import com.daniel.android.demo.coordinate.CoordinateLayoutActivity
import com.daniel.android.demo.coordinate.CoordinateModule
import com.daniel.android.demo.coroutine.CoroutineModule
import com.daniel.android.demo.dagger2.Dagger2Module
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * @author wuyang
 */
@Singleton
@Component(
    modules = [AppModule::class,
        CoordinateModule::class,
        CoroutineModule::class,
        Dagger2Module::class]
)
interface AppComponent {

    fun inject(activity: CoordinateLayoutActivity)

    fun inject(activity: MainActivity)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}