package com.daniel.android.demo

import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp

/**
 * @author wuyang
 */
@HiltAndroidApp(MultiDexApplication::class)
class DemoApplication : Hilt_DemoApplication()