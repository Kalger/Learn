package chapter5


fun main() {
    overloadOperator()
}

fun operatorTest() {
    val num = -23
    println("unaryPlus: ${num.unaryPlus()}")
    println("unaryMinus: ${-num}, ${num.unaryMinus()}")

    val bool = true
    println("not: ${bool.not()}")

    if ("hello".contains("he")) println("hello.contains(he)")
    if ("he" in "hello") println("he in hello")
}

fun overloadOperator() {
    val money1 = Money(5)
    val money2 = Money(50)
    val money3 = money1 + money2
    val money4 = money3 + 100
    println(money4.value)

    println("str times: ${"test_" * (1..4).random()}")
}

fun extension() {
    val str = "abba123dsf=-01!3f"
    // old method
//    val count = StringUtil.lettersCount(str)
    val count = str.lettersCount()
    println("count : $count")
    println("reverse str: ${str.reversed()}")

    println("capitalize first char for str: ${str.replaceFirstChar { it.uppercase() }}")
}