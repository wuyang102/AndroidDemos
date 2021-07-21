package com.daniel.android.demo.dagger2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.daniel.android.demo.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Provider

@AndroidEntryPoint
class Dagger2Activity : AppCompatActivity() {

    @Inject
    @Named("ts")
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
