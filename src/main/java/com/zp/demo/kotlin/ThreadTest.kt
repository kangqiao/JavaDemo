package com.zp.demo.kotlin

class ThreadTest {

    val value by lazy {  }

    val value2 by lazy(LazyThreadSafetyMode.PUBLICATION) {

    }
}