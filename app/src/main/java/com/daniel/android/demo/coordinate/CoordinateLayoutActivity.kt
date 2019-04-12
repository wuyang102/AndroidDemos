package com.daniel.android.demo.coordinate

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.view.OnApplyWindowInsetsListener
import android.support.v4.view.ViewCompat
import android.support.v4.view.WindowInsetsCompat
import android.view.View
import android.view.ViewGroup
import com.daniel.android.demo.R
import com.daniel.android.demo.SimpleAdapter
import kotlinx.android.synthetic.main.activity_coordinate_layout.*

class CoordinateLayoutActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.KITKAT_WATCH)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        setContentView(R.layout.activity_coordinate_layout)
        recyclerView.adapter = SimpleAdapter()
        val listener = OnApplyWindowInsetsListener { v, insets ->
            for (i in 0 until (v as ViewGroup).childCount)
                ViewCompat.dispatchApplyWindowInsets(v.getChildAt(i), copyWindowInsetsCompat(insets))
            insets.consumeSystemWindowInsets()
        }
        ViewCompat.setOnApplyWindowInsetsListener(appBarLayout, listener)
        ViewCompat.setOnApplyWindowInsetsListener(toolbarLayout, listener)
    }

    private fun copyWindowInsetsCompat(insets: WindowInsetsCompat): WindowInsetsCompat =
        insets.replaceSystemWindowInsets(
            insets.systemWindowInsetLeft,
            insets.systemWindowInsetTop,
            insets.systemWindowInsetRight,
            insets.stableInsetBottom
        )
}