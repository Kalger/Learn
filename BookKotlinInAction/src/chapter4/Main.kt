package chapter4

import chapter4.part2.*

fun main() {
    casePrivateSetter()
//    caseBackingField()
//    useInterfaceWithProperty()
//    Button()
//    twoImpl()
}

fun casePrivateSetter() {
    val lengthCounter = LengthCounter()
    lengthCounter.addWord("Hi!")
    println(lengthCounter.counter)
}

fun caseBackingField() {
    val user = BackingField("Alice")
    user.address = "Taichung 46, 806 Taiwan"
    user.address = "Taipei 77, 452 Taiwan"
}

fun useInterfaceWithProperty() {
    println(PrivateMember("test@kotlinlang.org").nickname)
    println(SubscribingMember("test@kotlinlang.org").nickname)
    println("=======")
    val specialMember = SpecialMember("test@kotlinlang.org")
    println(specialMember.email)
    println(specialMember.nickname)
}

fun dataClassVsClassWithDecompile() {
    da1(3, "2").hashCode()
    da2(4, "5").hashCode()
}

data class da1(val a: Int, val b: String)

class da2(val a: Int, val b: String)

