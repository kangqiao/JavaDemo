package com.zp.demo.kotlin

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import reactor.core.publisher.Mono.delay
import java.time.Duration
import kotlin.system.measureTimeMillis


var ucDomain: String? = null
    set(value) {
        println("field="+ field)
        println("value="+value)
        field = value
    }

// note that we don't have `runBlocking` to the right of `main` in this example
fun main() {
    //testRunBlocking()
    //testAsyncAwait()
    ucDomain = "hello"
    println(ucDomain)
    println("zhaopan ===  ")
    ucDomain = "world"
    println(ucDomain)

}

fun testRunBlocking() = runBlocking<Unit> {
    val time = measureTimeMillis {
        val one = doSomethingUsefulOne()
        val two = doSomethingUsefulTwo()
        println("The answer is ${one + two}")
    }
    println("Completed in $time ms")
}

fun testAsync() {
//    measureTimeMillis {
//        val one = async(start = CoroutineStart.LAZY) { doSomethingUsefulOne() }
//        val two = async(start = CoroutineStart.LAZY) { doSomethingUsefulTwo() }
//        // 执行一些计算
//        one.start() // 启动第一个
//        two.start() // 启动第二个
//        println("The answer is ${one.await() + two.await()}")
//    }
}

fun testAsyncAwait() {
    val time = measureTimeMillis {
        // we can initiate async actions outside of a coroutine
        val one = somethingUsefulOneAsync()
        val two = somethingUsefulTwoAsync()
        // but waiting for a result must involve either suspending or blocking.
        // here we use `runBlocking { ... }` to block the main thread while waiting for the result
        runBlocking {
            println("The answer is ${one.await() + two.await()}")
        }
    }
    println("Completed in $time ms")
}

fun somethingUsefulOneAsync() = GlobalScope.async {
    doSomethingUsefulOne()
}

fun somethingUsefulTwoAsync() = GlobalScope.async {
    doSomethingUsefulTwo()
}

suspend fun doSomethingUsefulOne(): Int {
    // delay(Duration.ofMillis(1000)) // pretend we are doing something useful here
    Thread.sleep(1000)
    return 13
}

suspend fun doSomethingUsefulTwo(): Int {
    //delay(Duration.ofMillis(10000)) // pretend we are doing something useful here, too
    Thread.sleep(1000)
    return 29
}