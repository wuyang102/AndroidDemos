package com.daniel.android.demo.coroutine

import android.app.Activity
import com.daniel.android.demo.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import kotlin.reflect.KClass

/**
 * @author wuyang
 */
@Module
@InstallIn(SingletonComponent::class)
class CoroutineModule {

    @Provides
    @IntoSet
    fun provideCoroutineActivityKClass(): Pair<Int, KClass<out Activity>> {
        return R.string.coroutine to CoroutineActivity::class
    }
}