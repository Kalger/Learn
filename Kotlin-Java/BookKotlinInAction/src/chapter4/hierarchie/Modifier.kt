package chapter4.hierarchie

open class RichButton : Clickable {

    fun disable() {}

    open fun animate() {}

    override fun click() {}

    final override fun showOff() {}
}

abstract class Animated {

    abstract fun animate()

    open fun stopAnimating() {}

    fun animateTwice() {}
}

internal open class TalkativeButton : Focusable {
    private fun yell() = println("Hey")
    protected fun whisper() = println("Let's talk")
}

// an example of error use
//fun TalkativeButton.giveSpeech() {
//    yell()
//
//    whisper()
//}
