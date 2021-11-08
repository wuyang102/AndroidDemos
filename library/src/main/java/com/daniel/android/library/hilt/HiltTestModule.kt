package com.daniel.android.library.hilt

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
object HiltTestModule {

    @Provides
    @IntoSet
    fun provideHiltTestActivityKClass(): Pair<Int, KClass<out Activity>> {
        return R.string.hilt_test to HiltTestActivity::class
    }
}