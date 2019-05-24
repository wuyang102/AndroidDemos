package com.daniel.android.demo.dagger2

import android.app.Activity
import com.daniel.android.demo.R
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
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
}
