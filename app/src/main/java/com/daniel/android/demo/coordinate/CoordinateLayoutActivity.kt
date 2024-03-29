package com.daniel.android.demo.coordinate

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.core.view.OnApplyWindowInsetsListener
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.daniel.android.demo.databinding.ActivityCoordinateLayoutBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CoordinateLayoutActivity : AppCompatActivity() {

    @Inject
    lateinit var adapter: SimpleAdapter

    @RequiresApi(Build.VERSION_CODES.KITKAT_WATCH)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        val binding = ActivityCoordinateLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerView.adapter = adapter
        val listener = OnApplyWindowInsetsListener { v, insets ->
            for (i in 0 until (v as ViewGroup).childCount)
                ViewCompat.dispatchApplyWindowInsets(v.getChildAt(i), copyWindowInsetsCompat(insets))
            insets.consumeSystemWindowInsets()
        }
        ViewCompat.setOnApplyWindowInsetsListener(binding.appBarLayout, listener)
        ViewCompat.setOnApplyWindowInsetsListener(binding.toolbarLayout, listener)
    }

    private fun copyWindowInsetsCompat(insets: WindowInsetsCompat): WindowInsetsCompat =
        insets.replaceSystemWindowInsets(
            insets.systemWindowInsetLeft,
            insets.systemWindowInsetTop,
            insets.systemWindowInsetRight,
            insets.stableInsetBottom
        )
}
