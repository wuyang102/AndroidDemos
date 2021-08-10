package com.daniel.android.libary.hilt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.daniel.android.demo.library.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint(AppCompatActivity::class)
class HiltTestActivity : Hilt_HiltTestActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hilt_test)
    }
}