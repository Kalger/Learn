package chapter3.strings

fun String.lastChar(): Char = get(length - 1)

val String.lastChar: Char
    get() = get(length - 1)
