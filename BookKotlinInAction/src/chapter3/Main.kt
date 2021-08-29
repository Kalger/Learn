package chapter3

import chapter3.strings.lastChar
import java.awt.Point
import java.lang.StringBuilder

var opCount = 0
val unix0 = "1"
const val UNIX_LINE_SEPARATOR = "\n"

fun main() {
    useLocalMethod()
}

fun useLocalMethod() {
//    saveUserOri(User(1, "", ""))
//    saveUserWithLocalMethod(User(1, "", ""))
    saveUserWithExtLocalMethod(User(1, "", ""))
}

fun multiLineTripleQuotedStr() {
    val kotlinLogo1 = """| //
                       .|//
                       .|/ \""".trimMargin(".")
    println(kotlinLogo1)
    println("----I'm a separator----")
    val kotlinLogo2 = """
        | //\n
        |//
        |/ \""".trimIndent()
    println(kotlinLogo2)
    println("----I'm a separator----")
    val normalStr = "cake\ndog";
    println(normalStr)
    println("----I'm a separator----")
    val number = 44
    val strTemplate = """
        | //
        number: $number $99.9 ${"nothing "}77.98  ${'$'}
        |/ \""".trimIndent()
    println(strTemplate)
}

fun regexWithCellPhone() {
    val numbers = arrayOf("09123456789", "30912345678", "0112345678", "0912345678")
    val pattern = "09[0-9]{8}".toRegex()
    for (num in numbers) {
        println("$num is cellPhone: ${num.matches(pattern)}")
    }
}

fun parsePath() {
    val path = "Users/kotlin-book/chapter.doc"
    val directory = path.substringBeforeLast("/")
    val fullName = path.substringAfterLast("/")

    val fileName = fullName.substringBeforeLast(".")
    val extension = fullName.substringAfterLast(".")
    println("Dir: $directory, name: $fileName, ext: $extension")

    println("---------")

    // with regular expression in triple-quoted string
    val regex = """(.+)/(.+)\.(.+)""".toRegex()
    val matchResult = regex.matchEntire(path)
    if (matchResult != null) {
        val (dir, filename, ext) = matchResult.destructured
        println("Dir: $dir, name: $filename, ext: $ext")
    }
}

fun splitWithRegex() {
    println("12.345-6.A".split("\\.|-".toRegex()))
    println("12.345-6.A".split(".", "-"))
}

fun deStructureDeclare() {
    val (number, name) = 1 to "one"
    println(number)
    println(name)
    val list = listOf("one", "two", "three")
    for ((index, element) in list.withIndex()) println("$index: $element")
}

fun infixCall() {
    val map = mapOf(1 to "one", 7 to "seven", 53.to("fifty-three"))

    println("yo" haha "lo")
}

infix fun String.haha(other: String): String = this + other

fun varargWithSpreadOperator() {
    val args = arrayOf("1", "2", "3")
    val list = listOf("0", *args)
    println(list)
}

fun extProperty() {
    println("Kotlin".lastChar)
    val sb = StringBuilder("Kotlin?")
    sb.lastChar = '!'
    println(sb)
}

var StringBuilder.lastChar: Char
    get() = get(length - 1)
    set(value) = setCharAt(length - 1, value)

fun overridesFun() {
    val view: View = Button()
    view.click()
    view.showOff()
}

open class View{
    open fun click() = println("view clicked")
}

class Button: View() {
    override fun click()  = println("button clicked")
}

fun myExtFunJoinToString() {
    val list = arrayListOf(1, 2, 3)
    println(list.myJoinToString(separator = "; ", prefix = "(", postfix = ")"))
}

fun interoperateKotlinMethodInJava() {
    val javaClass = UseKtMethod()
    javaClass.callKotlinExtendFun()
}

fun extendFun() {
    println("Kotlin".lastChar())
}

fun topProperty() {
    opCount++
    println("Operation performed $opCount times")
}

fun printCollection() {
    val list = listOf(1, 2, 3)
    println(joinToString(list, "; ", "(", ")"))
    println(joinToString(list, ", ", "", ""))
    println(joinToString(list))
    println(joinToString(list, "; "))
    println(joinToString(list, suffix = ";", prefix = "# "))
}

fun createCollection() {
    val set = hashSetOf(1, 7, 53)
    val list = arrayListOf(1, 7, 53)
    val map = hashMapOf(1 to "one", 7 to "seven", 53 to "fifty-three")

    println(set.javaClass)
    println(list.javaClass)
    println(map.javaClass)

    val strings = listOf("first", "second", "fourteenth")
    println(strings.last())

    val numbers = setOf(1, 14, 2)
    println(numbers.maxOrNull())
}

class Main {
    companion object{
        val test = ""
    }
}

object TestObject {
    val test = ""
}




