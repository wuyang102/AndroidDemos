package com.daniel.android.library.rxjava

import android.app.Activity
import com.daniel.android.demo.library.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import kotlin.reflect.KClass

@Module
@InstallIn(SingletonComponent::class)
object RxjavaModule {

    @Provides
    @IntoSet
    fun provideRxjavaActivityKClass(): Pair<Int, KClass<out Activity>> {
        return R.string.rxjava_test to RxjavaActivity::class
    }
}