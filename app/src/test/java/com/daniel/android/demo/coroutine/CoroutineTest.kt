package com.daniel.android.demo.coroutine

import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consume
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.reactive.collect
import kotlinx.coroutines.reactive.openSubscription
import kotlinx.coroutines.reactive.publish
import kotlinx.coroutines.rx2.*
import kotlinx.coroutines.selects.select
import kotlinx.coroutines.selects.whileSelect
import org.junit.Test
import org.reactivestreams.Publisher
import java.util.concurrent.TimeUnit
import kotlin.coroutines.CoroutineContext

/**
 * @author wuyang
 */
@ExperimentalCoroutinesApi
class CoroutineTest {

    @Test
    fun first() = runBlocking {
        GlobalScope.launch {
            delay(1000L)
            println("World!")
        }
        println("Hello,")
        delay(2000L)
    }

    @Test
    fun waitForJob() = runBlocking {
        val job = GlobalScope.launch {
            delay(1000L)
            println("World!")
        }
        println("Hello,")
        job.join()
    }

    @Test
    fun structured() = runBlocking {
        launch {
            delay(1000L)
            println("World!")
        }
        println("Hello,")
    }

    @Test
    fun scopeBuilder() = runBlocking {
        launch {
            doWorld()
        }

        coroutineScope {
            launch {
                delay(500L)
                println("Task from nested launch")
            }

            delay(100L)
            println("Task from coroutine scope")
        }
        println("Coroutine scope is over")
    }

    private suspend fun doWorld() {
        delay(200L)
        println("Task from runBlocking")
    }

    @Test
    fun cancelCoroutine() = runBlocking {
        val job = launch {
            repeat(1000) { i ->
                println("I'm sleeping $i")
                delay(500L)
            }
        }
        delay(1300L)
        println("main: I'm tired of waiting!")
        job.cancelAndJoin()
        println("main: Now I can quit")
    }

    @Test
    fun noCancelCoroutine() = runBlocking {
        val startTime = System.currentTimeMillis()
        val job = launch(Dispatchers.Default) {
            var nextPrintTime = startTime
            var i = 0
            while (isActive) {
                if (System.currentTimeMillis() >= nextPrintTime) {
                    println("I'm sleeping ${i++} ...")
                    nextPrintTime += 500L
                }
            }
        }
        delay(1300L)
        println("main: I'm tired of waiting!")
        job.cancelAndJoin()
        println("main: Now I can quit")
    }

    @Test
    fun finallyCoroutine() = runBlocking {
        val job = launch {
            try {

                repeat(1000) { i ->
                    println("I'm sleeping $i")
                    delay(500L)
                }
            } finally {
                withContext(NonCancellable) {
                    println("I'm running finally")
                    delay(1000L)
                    println("And I've just delayed for 1 sec because I'm non-cancellable")
                }
            }
        }
        delay(1300L)
        println("main: I'm tired of waiting!")
        job.cancelAndJoin()
        println("main: Now I can quit")
    }

    private fun log(msg: String) {
        println("[${Thread.currentThread().name}] $msg")
    }

    @Test
    fun timeout() = runBlocking {
        log("1")
        val result = withTimeoutOrNull(1300L) {
            log("2")
            repeat(1000) { i ->
                println("I'm sleeping $i")
                delay(500L)
            }
        }
        println("result: $result")
    }

    @ObsoleteCoroutinesApi
    @ExperimentalCoroutinesApi
    @Test
    fun channel() = runBlocking {
        val channel = receiveChannel()
        for (i in 0 until 5) {
            println("before receive")
            println(channel.receive())
            println("after receive")
        }
        println("Done")
    }

    @ExperimentalCoroutinesApi
    private fun CoroutineScope.receiveChannel(): ReceiveChannel<Int> {
        return produce {
            repeat(5) {
                println("before delay")
                delay(1000L)
                println("after delay")
                send(it * it)
                println("send: ${it * it} ")
            }
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun prime() = runBlocking {
        var cur = numberFrom(2)
        for (i in 1..10) {
            println("before receive")
            val prime = cur.receive()
            println("after receive: $prime")
            cur = filter(cur, prime)
        }
        coroutineContext.cancelChildren()
    }

    @ExperimentalCoroutinesApi
    private fun CoroutineScope.numberFrom(start: Int) = produce() {
        var x = start
        while (true) {
            println("number before send: $x")
            send(x++)
            println("number after send: ${x - 1}")
        }
    }

    private fun CoroutineScope.filter(numbers: ReceiveChannel<Int>, prime: Int) = produce {
        for (x in numbers) {
            println("filter: prime = $prime, x = $x")
            if (x % prime != 0) {
                println("filter before send: $x")
                send(x)
                println("filter after send: $x")
            }
        }
    }

    @Test
    fun rxCoroutine() = runBlocking {
        val source = Flowable.range(1, 5)
            .doOnSubscribe { println("OnSubscribe") }
            .doOnComplete { println("OnComplete") }
            .doFinally { println("Finally") }
        source.collect { println(it) }
    }

    @Test
    fun rxCoroutine2() = runBlocking {
        val source = rxFlowable {
            for (x in 1..3) {
                send(x)
                println("Sent $x")
            }
        }
        source.observeOn(Schedulers.io(), false, 1)
            .doOnComplete { println("Complete") }
            .subscribe {
                Thread.sleep(500)
                println("Processed $it")
            }
        delay(2000)
    }

    @Test
    fun rxCoroutine3() = runBlocking {
        val subject = BehaviorSubject.create<String>()
        subject.onNext("one")
        subject.onNext("two")
        launch(Dispatchers.Unconfined) {
            subject.collect { println(it) }
        }
        launch(Dispatchers.Unconfined) {
            delay(900)
            subject.collect { println("another: $it") }
        }
        subject.onNext("three")
        delay(1000)
        subject.onNext("four")
    }

    @Test
    fun rxCoroutine4() = runBlocking {
        publish {
            for (i in 0..5) send(i)
        }.collect {
            println("$it")
        }
    }

    private fun println(str: String) {
        kotlin.io.println("${Thread.currentThread().name}: $str")
    }

    @Test
    fun selectTest() = runBlocking {

        val fizz = produce {
            repeat(4) { index ->
                //                println("before send Fizz$index")
                send("Fizz$index")
//                println("after send Fizz$index")
            }
        }

        val buzz = produce {
            repeat(4) { index ->
                //                println("before send Buzz$index")
                send("Buzz$index")
//                println("after send Buzz$index")
            }
        }

        repeat(8) {
            //            println("before receive")
            println("receive " + select {
                fizz.onReceiveOrNull { it }
                buzz.onReceiveOrNull { it }
            })
        }
        println("Done")
    }

    @Test
    fun takeUntilTest() = runBlocking {
        rangeWithInterval(1000, 1, 10).takeUtil(coroutineContext, publish<Unit> { delay(4500) })
            .collect { println("$it") }
    }

    private fun <T, U> Publisher<T>.takeUtil(context: CoroutineContext, other: Publisher<U>) =
        GlobalScope.publish<T>(context) {
            this@takeUtil.openSubscription().consume {
                var current = this
                other.openSubscription().consume {
                    var other = this
                    whileSelect {
                        other.onReceiveOrNull { false }
                        current.onReceive {
                            send(it)
                            true
                        }

                    }
                }
            }
        }

    private fun CoroutineScope.rangeWithInterval(time: Long, start: Int, count: Int) = publish<Int> {
        for (x in start until start + count) {
            delay(time)
            send(x)
        }
    }

    private fun <T> Publisher<T>.merge(context: CoroutineContext, other: Publisher<T>) =
        GlobalScope.publish(context) {
            this@merge.openSubscription().consume {
                var current = this
                other.openSubscription().consume {
                    var other = this
                    whileSelect {
                        current.onReceive {
                            send(it)
                            true
                        }
                        other.onReceive {
                            send(it)
                            true
                        }
                    }
                }
            }
        }

    @Test
    fun rxMergeTest() = runBlocking {
        rangeWithInterval(200, 1, 9).merge(coroutineContext, rangeWithInterval(200, 10, 19))
            .collect { println("$it") }
    }

    private fun rangeWithIntervalRx(scheduler: Scheduler, time: Long, start: Int, count: Int): Flowable<Int> =
        Flowable.zip(
            Flowable.range(start, count),
            Flowable.interval(time, TimeUnit.MILLISECONDS, scheduler),
            BiFunction { x, _ ->
                println("$x on thread ${Thread.currentThread().name}")
                x
            })

    @Test
    fun testRangeWithIntervalRx() = runBlocking {
        rangeWithIntervalRx(Schedulers.computation(), 100, 1, 3)
            .collect { println("$it on thread ${Thread.currentThread().name}") }
    }

    @Test
    fun channelTest() = runBlocking {
        val handler = CoroutineExceptionHandler { _, throwable -> println("$throwable") }
        val channel = rxFlowable(handler) {
            for (i in 1..5) send(i)
            throw Exception("test exception")
        }.filter { it % 2 != 0 }.openSubscription()
        channel.consumeEach { println("$it") }
    }

    @Test
    fun asyncTest() = runBlocking {
        val async = rxSingle {
            throw Exception("test exception")
            1
        }
        try {
            val result = async.await()
            println("$result")
        } catch (t: Throwable) {
            println("$t")
        }
    }

    @Test
    fun rxTest() = runBlocking {
        val channel = Observable.interval(1000, TimeUnit.MILLISECONDS)
            .doOnNext {
                if (it == 3L) throw Exception("test exception")
            }.openSubscription()
        try {
            channel.consumeEach {
                println("$it")
            }
        } catch (t: Throwable) {
            println("$t")
        }
    }

    @Test
    fun asyncTest2() = runBlocking(Dispatchers.Default) {
        println("loading")
        withContext(Dispatchers.IO) {
            delay(1000)
            withContext(Dispatchers.Default) {
                println("do something")
            }
        }
        println("finish")
    }
}
