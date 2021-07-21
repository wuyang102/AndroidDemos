package com.daniel.android.demo

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

/**
 * @author wuyang
 */
@Module
@InstallIn(SingletonComponent::class)
object MainActivityModule {

    @Provides
    fun provideCurrentTimestamp(): Long = System.currentTimeMillis()


    @Provides
    @Named("hashcode")
    fun provideInt(): Int = hashCode()

}