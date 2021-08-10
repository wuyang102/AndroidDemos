package com.daniel.android.library.rxjava

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.daniel.android.demo.library.databinding.ActivityRxjavaBinding
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import kotlin.concurrent.thread
import kotlin.math.abs
import kotlin.random.Random

@AndroidEntryPoint(AppCompatActivity::class)
class RxjavaActivity : Hilt_RxjavaActivity() {

    private val publish = PublishSubject.create<Int>()
    private lateinit var binding: ActivityRxjavaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRxjavaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        thread {
            Thread.sleep(2000)
//            runOnUiThread {
                publish.onNext(1)
//            }
        }
        subscribe("One")
        subscribe("Tow")
        subscribe("Three")
    }

    fun subscribe(tag: String): Disposable {
        return publish
            .observeOn(Schedulers.computation())
            .doOnNext { Thread.sleep(abs(Random.nextLong() % 100)) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                binding.textView.text = "$tag $it"
                Log.i(RxjavaActivity::class.simpleName, "$tag $it")
            }
    }

}