package com.daniel.android.library.hilt

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn

@EntryPoint
@InstallIn(MyHiltComponent::class)
interface TimeEntryPoint {

    fun time(): Int
}