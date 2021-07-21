package com.daniel.android.demo.coordinate

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
object CoordinateModule {

    @Provides
    @IntoSet
    fun provideCoordinateLayoutActivityKClass(): Pair<Int, KClass<out Activity>> {
        return R.string.coordinate_layout to CoordinateLayoutActivity::class
    }
}
