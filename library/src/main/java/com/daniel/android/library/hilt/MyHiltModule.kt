package com.daniel.android.library.hilt

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import javax.inject.Named

@InstallIn(MyHiltComponent::class)
@Module
object MyHiltModule {

    @Provides
    fun provideTime(): Int {
        return 99999
    }
}