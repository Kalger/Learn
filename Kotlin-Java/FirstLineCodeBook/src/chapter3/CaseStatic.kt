package chapter3


fun doSomething() {
    println("do something")
}

class Util {
    fun doAction1() {
        println("do action1")
    }

    companion object {

        @JvmStatic // 使該方法成為靜態方法，從 Java 調用時，也能使用 Util.doAction2()
        fun doAction2() {
            println("do action2")
        }
    }
}