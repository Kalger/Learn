package chapter4

import chapter4.hierarchie.Button

fun main() {
    Button()
//    twoImpl()
}

fun dataClassVsClassWithDecompile() {
    da1(3, "2").hashCode()
    da2(4, "5").hashCode()
}

data class da1(val a: Int, val b: String)

class da2(val a: Int, val b: String)

