package unit4.coroutines

import kotlinx.coroutines.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object IntroCoroutines {

    fun global() {
        println("global ${Thread.currentThread()}")

        repeat(3) {
            GlobalScope.launch {
                println("Hi from ${Thread.currentThread()}")
            }
        }
        // avoid that app ending results in coroutines ends before complete its task
        println("Hello")
        Thread.sleep(1000)
    }

    private val formatter: DateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME
    private val time = { formatter.format(LocalDateTime.now()) }    // lambda function
    fun caseRunBlocking() {
        println("caseRunBlocking ${Thread.currentThread()}")

        runBlocking {
            println("runBlocking 1 ${Thread.currentThread()}")

            val num1 = getValue()
            val num2 = getValue()
            println("result of num1 + num2 = ${num1 + num2}")
        }
        println("==============")

        runBlocking {
            println("runBlocking 2 ${Thread.currentThread()}")

            val num1 = async { getValue() }
            val num2 = async { getValue() }
            println("result of num1 + num2 = ${num1.await() + num2.await()}")
        }
        println("end")
    }


    suspend fun getValue(): Double {
        println("getValue ${Thread.currentThread()}")
        println("enter getValue() at ${time()}")
        delay(1000)
        println("leaving getValue() at ${time()}")
        return Math.random()
    }

    fun caseImitateMultiThread() {
        val states = arrayOf("Starting", "Doing Task 1", "Doing Task 2", "Ending")
        repeat(3) {
            GlobalScope.launch {
                println("${Thread.currentThread()} has started")
                for (i in states) {
                    println("${Thread.currentThread()} - $i")
                    delay(50)
                }
            }
        }
        Thread.sleep(1000)
    }
}