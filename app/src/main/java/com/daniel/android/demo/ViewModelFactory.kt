package com.daniel.android.demo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Lazy
import javax.inject.Inject

/**
 * @author wuyang
 */
class ViewModelFactory<VM : ViewModel> @Inject constructor(private val viewMode: Lazy<VM>) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return viewMode.get() as T
    }
}