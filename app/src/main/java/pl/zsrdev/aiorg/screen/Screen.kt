package pl.zsrdev.aiorg.screen

import android.view.Gravity
import pl.zsrdev.aiorg.Ash

abstract class Screen(protected val ash: Ash) {
    abstract fun show()
}