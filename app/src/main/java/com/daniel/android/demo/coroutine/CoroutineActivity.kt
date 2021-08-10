package com.daniel.android.demo.coroutine

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.daniel.android.demo.databinding.ActivityCoroutineBinding
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.Observable

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.rx2.openSubscription

@AndroidEntryPoint(AppCompatActivity::class)
class CoroutineActivity : Hilt_CoroutineActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCoroutineBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        binding.fab.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
                val channel = Observable.create<Int> {
                    for (i in 3 downTo 1) it.onNext(i)
                }.doOnNext {
                    if (it == 1) throw Exception("test exception")
                }.openSubscription()
                try {
                    channel.consumeEach {
                        binding.includeContent.textView.text = it.toString()
                    }
                } catch (t: Throwable) {
                    Log.e(CoroutineActivity::class.simpleName, "$t")
                }
            }
        }
    }
}
