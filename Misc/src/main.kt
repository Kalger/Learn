import java.io.IOException
import java.lang.Exception

enum class Color(val r:Int){
    RED(100), BLUE(299);
    val g: Int = 4
    fun rgb() = r + g
}

interface Expr{
    fun testMen()
}
class Num(val value: Int) : ExprJ

class Sum(val left: ExprJ, val right: ExprJ) : ExprJ

class Person(val name: String)

fun eval(e: ExprJ): Int =
    when(e){
        is Num -> e.value
        is Sum -> eval(e.left) + eval(e.right)
        else -> throw IllegalArgumentException("unknown expression")
    }

fun rec(c: Char) =
    when(c){
        in '0'..'9' -> "digit"
        in 'a'..'z', in 'A'..'Z' -> "letter"
        else -> "don't know"
    }

fun normalFun(){
    throw Exception()
}

fun printBorder(){
    repeat(23){
        print("=")
    }
}

fun testIntRange(){
    val ran = 1..7
    val ran2: IntRange = 2..8
    ran.random()
}


fun mockMain() {
    val myFirstDice = Dice(6)
    val diceRoll = myFirstDice.roll()
    println("Your ${myFirstDice.numSides} sided dice rolled ${diceRoll}!")
}

class Dice (val numSides: Int) {

    fun roll(): Int {
        val randomNumber = (1..numSides).random()
        return randomNumber
    }
}

fun stringAndBuilder() {
    val str = StringBuilder("aa")
    val str1 = "123" + "1234"
}

fun main(){
    println(3 or 4)
    println(rec('8'))

//    val per1 = Person("black")
//    println(per1.name)
//    println(RED.rgb())
//    println(eval(Sum(Sum(Num(1), Num(2)), Num(4))))
//    for(i in 1 downTo -100 step 2){
//        println(i)
//    }
//    val list = arrayListOf("10", "11", "12")
//    for((index, element) in list.withIndex()){
//    }
//    try {
//        throw IOException()
//    }catch (e: Exception){

//    }

}
