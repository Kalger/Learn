package chapter4.hierarchie

fun twoImpl() {
    val btn = Button()
    btn.showOff()
    btn.setFocus(true)
    btn.click()
}

interface Clickable {
    fun click()
    fun showOff() = print("I'm clickable!")
}

class Button : Clickable, Focusable {
    override fun click() = println("clicked")

    override fun showOff() {
        super<Clickable>.showOff()
        super<Focusable>.showOff()
    }
}

interface Focusable {
    fun setFocus(b: Boolean) = println("I ${if (b) "got" else "lost"} focus.")

    fun showOff() = println("I'm focusable!")
}