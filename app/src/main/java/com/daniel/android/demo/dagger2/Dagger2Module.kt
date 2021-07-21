package com.daniel.android.demo.dagger2

import android.app.Activity
import com.daniel.android.demo.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import javax.inject.Named
import kotlin.reflect.KClass

/**
 * @author wuyang
 */
@Module
@InstallIn(SingletonComponent::class)
object Dagger2Module {

    @Provides
    @IntoSet
    fun provideDagger2ActivityKClass(): Pair<Int, KClass<out Activity>> {
        return R.string.dagger2 to Dagger2Activity::class
    }
}

@Module
@InstallIn(ActivityRetainedComponent::class)
object Dagger2ActivityScopeModel {

    @Provides
    @Named("ts")
    fun provideIntentStringArgument(): String {
        return "abc123"
    }
}

@Module
@InstallIn(FragmentComponent::class)
object Dagger2FragmentScopeModel {

    @Provides
    @Named("ts2")
    fun provideIntentStringArgument(): String {
        return System.currentTimeMillis().toString()
    }
}