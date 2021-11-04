package chapter4

import chapter4.part2.*
import chapter4.part3.Client
import chapter4.part3.CountingSet
import chapter4.part3.DataClient
import chapter4.part4.*
import java.io.File

fun main() {
    caseObjectClass()
//    caseDelegate()
//    caseDataClass()
//    caseOverrideGeneralMethod()
//    casePrivateSetter()
//    caseBackingField()
//    useInterfaceWithProperty()
//    Button()
//    twoImpl()
}

fun caseObjectClass() {

    Payroll.allEmployees.add(Person("Lucy"))
    Payroll.calculateSalary()

    println(CaseInsensitiveFileComparator.compare(File("/User"), File("/user")))

    // sort with a comparator
    val files = listOf(File("/Z"), File("/a"))
    println(files.sortedWith(CaseInsensitiveFileComparator))

    // object class in a class
    val persons = listOf(Person("Bob"), Person("Alice"))
    println(persons.sortedWith(Person.NameComparator))

    // companion
    A.bar()

    // factory method in companion
    val subscribingUser = UserWithFactory.newSubscribingUser("bob@gmail.com")
    val facebookUser = UserWithFactory.newFacebookUser(4)
    println(subscribingUser.nickname)

    // name a companion object
    val person = Human.Loader.fromJSON("{name: 'Dmi'}")
    println(person.name)

    val person2 = Human.fromJSON("{name: 'Jack'}")
    println(person2.name)

    // deserialize with a factory object
    loadFromJSON(Human2)

    // extension fun on companion object
    val p = Person2.fromJSON("")
}

fun caseDelegate() {
    val countingSet = CountingSet<Int>()
    countingSet.addAll(listOf(1, 1, 3))
    println("${countingSet.objectsAdded} objects wrer added, ${countingSet.size} remain")
}

/* use same code with caseOverrideGeneralMethod() on data class*/
fun caseDataClass() {
    val client1 = DataClient("Alice", 34290)
    println(client1)

    val client2 = DataClient("Alice", 34290)
    println(client1 == client2)

    val processed = hashSetOf(Client("Alice", 222))
    println(processed.contains(Client("Alice", 222)))

    // copy
    val bob = DataClient("Bob", 1234)
    println(bob.copy(postalCode = 9999))
}

fun caseOverrideGeneralMethod() {
    val client1 = Client("Alice", 34290)
    println(client1)

    // override equals
    val client2 = Client("Alice", 34290)
    println(client1 == client2)

    // override hashcode
    val processed = hashSetOf(Client("Alice", 222))
    println(processed.contains(Client("Alice", 222)))
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

