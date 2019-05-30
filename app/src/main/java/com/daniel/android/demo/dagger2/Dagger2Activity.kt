package com.daniel.android.demo.dagger2

import android.os.Bundle
import com.daniel.android.demo.BaseActivity
import com.daniel.android.demo.R
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Provider

class Dagger2Activity : BaseActivity() {

    @Inject
    @field:Named("ts")
    lateinit var ts: Provider<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dagger2_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, Dagger2Fragment.newInstance().apply {
                    arguments = Bundle()
                    arguments!!.putString("st", ts.get())
                })
                .commitNow()
        }
    }

}
