package unit4.coroutines

fun caseThreadUnpredictableBehavior() {
    var count = 0
    for (i in 1..50) {
        Thread {
            count += 1
            println("Thread: $i count: $count")
        }.start()
    }
}

fun caseThread() {
    val thread = Thread {
        println("${Thread.currentThread()} has run.")
    }
    thread.start()
}

fun caseMultiThread() {
    val states = arrayOf("Starting", "Doing Task 1", "Doing Task 2", "Ending")
    repeat(3) {
        Thread {
            println("${Thread.currentThread()} has started")
            for (i in states) {
                println("${Thread.currentThread()} - $i")
                Thread.sleep(50)
            }
        }.start()
    }
}