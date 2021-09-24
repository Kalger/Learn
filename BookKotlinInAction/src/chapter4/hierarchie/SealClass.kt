package chapter4.hierarchie

import java.io.File
import java.lang.IllegalArgumentException
import javax.sql.DataSource

// kotlin in action original
interface Expr
class Num(val value: Int) : Expr
class Sum(val left: Expr, val right: Expr) : Expr

fun eval(e: Expr) : Int =
    when (e) {
        is Num -> e.value
        is Sum -> eval(e.right) + eval (e.left)
        else -> throw IllegalArgumentException("Unknown expression")
    }

// kotlin in action with sealed
sealed class Expr2 {
    class Num2(val value: Int) : Expr2()
    class Sum2(val left: Expr2, val right: Expr2) : Expr2()
}

fun eval2(e: Expr2) : Int =
    when (e) {
        is Expr2.Num2 -> e.value
        is Expr2.Sum2 -> eval2(e.right) + eval2(e.left)
    }


// Alger tries out
sealed interface Expr3

class Num3(val value: Int) : Expr3
class Sum3(val left: Expr3, val right: Expr3) : Expr3

fun eval3(e: Expr3) : Int =
    when (e) {
        is Num3 -> e.value
        is Sum3 -> eval3(e.right) + eval3(e.left)
    }


// kotlin doc
sealed interface Error
sealed class IOError(): Error

class FileReadError(val f: File): IOError()
class DatabaseError(val source: DataSource): IOError()

object RuntimeError : Error

fun log(e: Error) = when(e) {
    is FileReadError -> { println("Error while reading file ${e.f}") }
    is DatabaseError -> { println("Error while reading from database ${e.source}") }
    RuntimeError ->  { println("Runtime error") }
    // the `else` clause is not required because all the cases are covered
}