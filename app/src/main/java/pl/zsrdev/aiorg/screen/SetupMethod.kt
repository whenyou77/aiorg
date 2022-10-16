package pl.zsrdev.aiorg.screen

import android.annotation.SuppressLint
import android.app.ActionBar
import android.app.AlertDialog
import android.content.DialogInterface
import android.util.TypedValue
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.Dimension
import androidx.core.content.res.TypedArrayUtils.getString
import androidx.core.content.res.TypedArrayUtils.getText
import androidx.core.view.setMargins
import pl.zsrdev.aiorg.Ash
import pl.zsrdev.aiorg.R
import java.security.AccessController.getContext

class SetupMethod(private val a: Ash) : Screen(a) {
    override fun show() {

        ash.screen.gravity = Gravity.CENTER

        val layoutParams = LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT)
        layoutParams.setMargins(
            TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 20.0f, ash.act.resources.displayMetrics
            ).toInt())

        val titleText = TextView(ash.act)
        titleText.text = ash.act.getString(R.string.wassup)
        titleText.setTextSize(Dimension.SP, 18.0f)
        titleText.gravity = Gravity.CENTER

        val vulcanButton = Button(ash.act)
        vulcanButton.text = ash.act.getString(R.string.vulkan_login)
        vulcanButton.setTextColor(-1)
        vulcanButton.setBackgroundColor(-3002833)
        vulcanButton.layoutParams = layoutParams

        val middleText = TextView(ash.act)
        middleText.text = ash.act.getString(R.string.or)
        middleText.setTextSize(Dimension.SP, 18.0f)
        middleText.gravity = Gravity.CENTER

        val manualButton = Button(ash.act)
        manualButton.setTextColor(-2039584)
        manualButton.setBackgroundColor(-8355712)
        manualButton.text = ash.act.getString(R.string.by_hand)

        manualButton.setOnClickListener {
            ash.showScreen(ash.manualSubjects)
        }
        vulcanButton.setOnClickListener {
            ash.showScreen(ash.vulcanLogin)
        }

        ash.screen.addView(titleText)
        ash.screen.addView(vulcanButton)
        ash.screen.addView(middleText)
        ash.screen.addView(manualButton)
    }
}