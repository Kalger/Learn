@file:JvmName("StringFunctions")
package chapter3

import java.lang.StringBuilder

@JvmOverloads
fun <T> joinToString(
    collection: Collection<T>,
    separator: String = ", ",
    prefix: String = "",
    suffix: String = ""
): String {
    val result = StringBuilder(prefix)
    for ((index, element) in collection.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(element)
    }

    result.append(suffix)
    return result.toString()
}

fun <T> Collection<T>.myJoinToString(
    separator: String = ", ",
    prefix: String = "",
    postfix: String = ""
): String {
    val result = StringBuilder(prefix)
    for ((index, element) in withIndex()) {
        if (index > 0) result.append(separator)
        result.append(element)
    }
    result.append(postfix)
    return result.toString()
}

fun readTopPropertyAtAnotherKtFile() {
    unix0
    UNIX_LINE_SEPARATOR
}