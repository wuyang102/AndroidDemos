package com.daniel.android.demo

import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import javax.inject.Named

/**
 * @author wuyang
 */
@Module
abstract class MainActivityModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainActivityScopeModel::class])
    abstract fun contributeMainAcitivtyInjector(): MainActivity


    @Module
    abstract class MainActivityScopeModel {

        @Module
        companion object {

            @JvmStatic
            @ActivityScope
            @Provides
            fun provideCurrentTimestamp(): Long = System.currentTimeMillis()


            @JvmStatic
            @ActivityScope
            @Provides
            @Named("hashcode")
            fun provideInt(activity: MainActivity): Int = activity.hashCode()
        }
    }

}