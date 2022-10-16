package pl.zsrdev.aiorg.screen;

import android.app.ActionBar
import android.graphics.Typeface
import android.text.InputType
import android.util.TypedValue
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.Dimension
import androidx.core.view.setMargins
import pl.zsrdev.aiorg.Ash;
import pl.zsrdev.aiorg.R
import pl.zsrdev.aiorg.vulcan

class VulcanLogin(private val a:Ash) : Screen(a) {
    override fun show() {
        ash.screen.gravity = Gravity.CENTER

        val layoutParams = LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT)
        layoutParams.setMargins(
            TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 20.0f, ash.act.resources.displayMetrics
            ).toInt())

        val titleText = TextView(ash.act)
        titleText.text = ash.act.getString(R.string.vulkan_login)
        titleText.setTextSize(Dimension.SP, 24.0f)
        titleText.setTypeface(titleText.typeface, Typeface.BOLD)
        titleText.setTextColor(-1)
        titleText.gravity = Gravity.CENTER

        val emailInput = EditText(ash.act)
        emailInput.hint = "E-Mail"
        emailInput.setTextSize(Dimension.SP, 20.0f)
        emailInput.gravity = Gravity.CENTER
        emailInput.layoutParams = layoutParams
        emailInput.setTextColor(-1)
        emailInput.setHintTextColor(-8355712)

        val passwordInput = EditText(ash.act)
        passwordInput.inputType = InputType.TYPE_CLASS_TEXT + InputType.TYPE_TEXT_VARIATION_PASSWORD
        passwordInput.hint = ash.act.getString(R.string.pass)
        passwordInput.setTextSize(Dimension.SP, 20.0f)
        passwordInput.gravity = Gravity.CENTER
        passwordInput.layoutParams = layoutParams
        passwordInput.setTextColor(-1)
        passwordInput.setHintTextColor(-8355712)
        passwordInput.typeface = emailInput.typeface

        val symbolInput = EditText(ash.act)
        symbolInput.hint = "${ash.act.getString(R.string.county)} powiatpoznanski)"
        symbolInput.setTextSize(Dimension.SP, 20.0f)
        symbolInput.gravity = Gravity.CENTER
        symbolInput.layoutParams = layoutParams
        symbolInput.setTextColor(-1)
        symbolInput.setHintTextColor(-8355712)

        val loginButton = Button(ash.act)
        loginButton.setTextColor(-1)
        loginButton.setBackgroundColor(-3002833)
        loginButton.text = ash.act.getString(R.string.login)
        loginButton.layoutParams = layoutParams
        loginButton.setTextSize(Dimension.SP, 18.0f)

        val loggingText = TextView(ash.act)
        loggingText.text = ash.act.getString(R.string.vulkan_login)
        loggingText.setTextSize(Dimension.SP, 24.0f)
        loggingText.setTypeface(loggingText.typeface, Typeface.BOLD)
        loggingText.setTextColor(-1)
        loggingText.gravity = Gravity.CENTER

        loginButton.setOnClickListener {
            ash.screen.addView(loggingText)
            ash.showScreen(this)
            vulcan(emailInput.text.toString(), passwordInput.text.toString(), symbolInput.text.toString(), ash)
            ash.showScreen(ash.manualSubjects)
        }

        ash.screen.addView(titleText)
        ash.screen.addView(emailInput)
        ash.screen.addView(passwordInput)
        ash.screen.addView(symbolInput)
        ash.screen.addView(loginButton)
    }
}
