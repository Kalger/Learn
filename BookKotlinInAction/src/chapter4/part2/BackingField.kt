package chapter4.part2

class BackingField (val name: String) {
    var address: String = "unspecified"
        set(value: String) {
            println("""
                Address was changed for $name:
                "$field" -> "$value".
            """.trimIndent())
            field = value
        }
}

// ITHelp : https://ithelp.ithome.com.tw/articles/10239912
class Wallet(_balance: String) {
    var balance = _balance
        get() {
            println("")
            return field
        }
}

// Kotlin Doc
var stringRepresentation: String
    get() = ""
    set(value) {
        setDataFromString(value) // parses the string and assigns values to other properties
    }

fun setDataFromString(value: String) {

}

var _table: Map<String, Int>? = null
    get() {
        if (field == null) field = HashMap()
        return field
    }
    private set
