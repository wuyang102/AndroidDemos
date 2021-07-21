package com.daniel.android.demo.coordinate

import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.view.OnApplyWindowInsetsListener
import android.support.v4.view.ViewCompat
import android.support.v4.view.WindowInsetsCompat
import android.view.View
import android.view.ViewGroup
import com.daniel.android.demo.BaseActivity
import com.daniel.android.demo.databinding.ActivityCoordinateLayoutBinding
import javax.inject.Inject

class CoordinateLayoutActivity : BaseActivity() {

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
