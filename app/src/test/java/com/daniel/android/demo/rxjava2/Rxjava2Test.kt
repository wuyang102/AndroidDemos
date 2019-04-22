package com.daniel.android.demo.rxjava2

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.junit.Test

/**
 * @author wuyang
 */
class Rxjava2Test {

    @Test
    fun completableTest() {
        Completable.fromAction {
            Completable.create { emitter1 ->
                println("start")
                Observable.create<Int> { emitter2 ->
                    for (i in 1..5) {
                        if (i == 2) throw Exception("error")
                        emitter2.onNext(i)
                        Thread.sleep(2000)
                    }
                }.doOnNext { if (it == 3) emitter1.onComplete() }
                    .subscribeOn(Schedulers.io()).subscribe({
                        println(it)
                    }) { emitter1.onError(it) }
            }.subscribe({ println("complete") }) {
                println(it.message)
            }
        }.subscribe { Thread.sleep(12000) }
    }
}