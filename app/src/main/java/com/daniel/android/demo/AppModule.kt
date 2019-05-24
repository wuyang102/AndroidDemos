package com.daniel.android.demo

import android.app.Activity
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import kotlin.reflect.KClass

/**
 * @author wuyang
 */
@Module
class AppModule {

    @Provides
    @Singleton
    fun provideActivities(set: Set<@JvmSuppressWildcards Pair<Int, KClass<out Activity>>>)
            : List<Pair<Int, KClass<out Activity>>> = ArrayList(set)
}