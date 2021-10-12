package chapter4.part4



// define extension fun on companion object
class Person2(val firstName: String, val lastName: String) {
    companion object {}
}

fun Person2.Companion.fromJSON(json: String): Person2 {
    return Person2("Alger", "Lu")
}

// ----

fun <T> loadFromJSON(factory: JSONFactory<T>): T {
    return factory.fromJSON("")
}

class Human2() {
    companion object : JSONFactory<Human2> {
        override fun fromJSON(jsonTxt: String): Human2 {
            return Human2()
        }
    }
}

interface JSONFactory<T> {
    fun fromJSON(jsonTxt: String): T
}

class Human(val name: String) {
    companion object Loader {
        fun fromJSON(jsonText: String): Human {
            return Human(jsonText)
        }
    }
}

class UserWithFactory private constructor(val nickname: String) {
    companion object {
        fun newSubscribingUser(email: String) = UserWithFactory(email.substringBefore('@'))
        fun newFacebookUser(facebookId: Int) = UserWithFactory(getFacebookName(facebookId))
    }
}

class User {
    val nickname: String

    constructor(email: String) {
        nickname = email.substringBefore('@')
    }

    constructor(facebookId: Int) {
        nickname = getFacebookName(facebookId)
    }
}

fun getFacebookName(facebookId: Int) = "name: $facebookId"

class A {
    companion object {
        fun bar() {
            println("Companion object called")
        }
    }
}