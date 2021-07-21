package com.daniel.android.demo.dagger2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject
import javax.inject.Named

class Dagger2ViewModel @Inject constructor(@param:Named("ts") private val ts: String) : ViewModel() {

    private val _tsLD = MutableLiveData<String>()
    val tsLD: LiveData<String> = _tsLD

    init {
        _tsLD.value = ts
    }
}
