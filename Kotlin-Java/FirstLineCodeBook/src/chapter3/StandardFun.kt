package chapter3

class StandardFun {
    val list = listOf("Apple", "Banana", "Orange")
    fun caseOri() {
        val builder = StringBuilder()
        builder.append("Start eating fruits.\n")
        for (fruit in list) {
            builder.append(fruit).append("\n")
        }
        builder.append("Ate all fruits.")
        val result = builder.toString()
        println(result)
    }

    fun caseWith() {
        val result = with(StringBuilder()) {
            append("Start eating fruits.\n")
            for (fruit in list) {
                append(fruit).append("\n")
            }
            append("Ate all fruits.")
            toString()
        }
        println(result)
    }

    fun caseRun() {
        val result = StringBuilder().run {
            append("Start eating fruits.\n")
            for (fruit in list) {
                append(fruit).append("\n")
            }
            append("Ate all fruits.")
            toString()
        }
        println(result)
    }

    fun caseApply() {
        val result = StringBuilder().apply {
            append("Start eating fruits.\n")
            for (fruit in list) {
                append(fruit).append("\n")
            }
            append("Ate all fruits.")
        }
        println(result.toString())
    }
}