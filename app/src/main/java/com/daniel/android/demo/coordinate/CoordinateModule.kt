package com.daniel.android.demo.coordinate

import android.app.Activity
import com.daniel.android.demo.ActivityScope
import com.daniel.android.demo.R
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoSet
import kotlin.reflect.KClass

/**
 * @author wuyang
 */
@Module
abstract class CoordinateModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        @IntoSet
        fun provideCoordinateLayoutActivityKClass(): Pair<Int, KClass<out Activity>> {
            return R.string.coordinate_layout to CoordinateLayoutActivity::class
        }
    }

    @ActivityScope
    @ContributesAndroidInjector
    internal abstract fun contributeCoordinateAcitivtyInjector(): CoordinateLayoutActivity
}