package com.daniel.android.library.hilt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.daniel.android.demo.library.databinding.ActivityHiltTestBinding
import dagger.hilt.EntryPoints

class HiltTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityHiltTestBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val component = constructComponent()
        val time = EntryPoints.get(component, TimeEntryPoint::class.java).time()
        binding.time.text = time.toString()
    }

    private fun constructComponent(): MyHiltComponent {
        return EntryPoints.get(application, MyHiltComponentBuilderEntryPoint::class.java)
            .entryPoint()
            .name("hilt")
            .build()
    }
}