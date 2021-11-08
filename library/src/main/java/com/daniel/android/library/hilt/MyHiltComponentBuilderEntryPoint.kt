package com.daniel.android.library.hilt

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface MyHiltComponentBuilderEntryPoint {

    fun entryPoint(): MyHiltComponent.MyHiltComponentBuilder
}