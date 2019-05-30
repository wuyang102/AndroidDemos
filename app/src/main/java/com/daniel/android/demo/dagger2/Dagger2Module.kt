package com.daniel.android.demo.dagger2

import android.app.Activity
import com.daniel.android.demo.ActivityScope
import com.daniel.android.demo.FragmentScope
import com.daniel.android.demo.R
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoSet
import javax.inject.Named
import kotlin.reflect.KClass

/**
 * @author wuyang
 */
@Module
abstract class Dagger2Module {

    @Module
    companion object {

        @Provides
        @IntoSet
        @JvmStatic
        fun provideDagger2ActivityKClass(): Pair<Int, KClass<out Activity>> {
            return R.string.dagger2 to Dagger2Activity::class
        }
    }

    @ActivityScope
    @ContributesAndroidInjector(modules = [Dagger2ActivityScopeModel::class])
    abstract fun contributeDagger2Activity(): Dagger2Activity

}

@Module
abstract class Dagger2ActivityScopeModel {

    @Module
    companion object {

        @Provides
        @ActivityScope
        @JvmStatic
        @Named("ts")
        fun provideIntentStringArgument(activity: Dagger2Activity): String {
            return activity.intent.getLongExtra("ts", 0).toString()
        }
    }

    @FragmentScope
    @ContributesAndroidInjector(modules = [Dagger2FragmentScopeModel::class])
    abstract fun contributeDagger2Fragment(): Dagger2Fragment
}

@Module
abstract class Dagger2FragmentScopeModel {

    @Module
    companion object {

        @Provides
        @FragmentScope
        @JvmStatic
        @Named("ts2")
        fun provideIntentStringArgument(): String {
            return System.currentTimeMillis().toString()
        }
    }

}