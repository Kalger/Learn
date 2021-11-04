fun main() {
    makeWordList()
}

fun makeWordList() {
    val words = listOf("about", "acute", "awesome", "balloon", "best", "brief", "class", "coffee", "creative")
    val filterWords = words.filter { it.startsWith("b", true) }
        .shuffled()
        .take(2)
        .sorted()
    println(filterWords)
}

fun sortTest() {
    val peopleNames = listOf("Fred", "Ann", "Barbara", "Joe")
    println(peopleNames.sortedWith { str1: String, str2: String -> str1.length - str2.length })
}

fun testLambda() {

//    val triple: (Int) -> Int = { a: Int -> a * 3 }
//    val triple = { a: Int -> a * 3 }
//    val triple: (Int) -> Int = { a -> a*3 }
    val triple: (Int) -> Int = { it*3 }

    // If your lambda consists of a single function call, you may use function pointers (::)
//    val triple: (Int) -> Int = Int::inc
    println(triple(5))
}

fun higherOderFunReturn() {
    val num = 2

    // in curly brace need not explicit "return" keyword
    testReturn { num == 2 }
}

fun testReturn(numF: (Int) -> Boolean) {
    numF(1)
}

fun basicMap() {
    val peopleAges = mutableMapOf<String, Int>(
        "Fred" to 30,
        "Ann" to 23
    )
    println(peopleAges)

    peopleAges.put("Alger", 26)
    peopleAges["Jim"] = 28
    println(peopleAges)
    peopleAges.put("Fred", 99)
    println(peopleAges)

    peopleAges.forEach {
        print("${it.key} => ${it.value}, ")
    }

    println(peopleAges.map { "${it.key} => ${it.value}"}.joinToString(", "))
    val filteredNames = peopleAges.filter { it.key.length < 4 }
    println(filteredNames)
}

private fun testToSet() {
    val numbers = listOf(0, 3, 8, 4, 0, 5, 5, 8, 9, 2)
    println("list:   ${numbers}")

    val setOfNumbers = numbers.toSet()
    println("set:    ${setOfNumbers}")
}

fun orderInfluence() {
    val numbers2 = listOf(1,2,3)
    val numbers3 = listOf(2,1,3)

    println("$numbers3 == $numbers2    ${numbers2 == numbers3}")

    val set1 = setOf(1,2,3)
    val set2 = mutableSetOf(2,3,1)
    println("$set1 == $set2    ${set1 == set2}")
}
