package com.daniel.android.demo.dagger2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.daniel.android.demo.R

class Dagger2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dagger2_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, Dagger2Fragment.newInstance())
                .commitNow()
        }
    }

}
