package chapter4.part4

import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.JWindow


fun anonymousObjectFun() {
    var clickCount = 0
    val window = JWindow()
    window.addMouseListener(object : MouseAdapter() {
        override fun mouseClicked(e: MouseEvent?) {
            clickCount++
        }

        override fun mouseEntered(e: MouseEvent?) {}
    })

    // -------

    val listener = object : MouseAdapter() {
        override fun mouseClicked(e: MouseEvent?) {}

        override fun mouseEntered(e: MouseEvent?) {}
    }
}
