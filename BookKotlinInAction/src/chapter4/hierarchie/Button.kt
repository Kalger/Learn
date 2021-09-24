package chapter4.hierarchie

class Button2 : View {

    override fun getCurrentState(): State = ButtonState()

    override fun restoreState(state: State) {}

    class ButtonState : State {}
}

class Outer {
    inner class Inner {
        fun getOuterReference(): Outer = this@Outer
    }
}