package pl.zsrdev.aiorg.screen

import android.view.View
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.core.view.children
import pl.zsrdev.aiorg.Ash
import pl.zsrdev.aiorg.R

class Home(private val a: Ash) : Screen(a) {
    override fun show() {
        ash.headerPlain.text = ash.act.getString(R.string.welcome_screen)
        (ash.headerPlain.parent as LinearLayout?)?.removeView(ash.headerPlain)
        ash.screen.addView(ash.headerPlain)

        val scrollView = ScrollView(ash.act)
        scrollView.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, 0, 78.0f
        )
        ash.screen.addView(scrollView)

        val scrollLayout = LinearLayout(ash.act)
        scrollLayout.orientation = LinearLayout.VERTICAL
        scrollView.addView(scrollLayout)

        ash.userData.subjects.forEachIndexed { index, s ->
            val subjectView = TextView(ash.act)
            subjectView.text = "$s: ${ash.configData.priorities[index]}"
            subjectView.textSize = 18.0f
            scrollLayout.addView(subjectView)
        }

        // TODO greenButton text to xml
        ash.greenButton.text = ash.act.getString(R.string.study_mode)
        ash.greenButton.setOnClickListener { ash.showScreen(ash.studyMode) }
        (ash.greenButton.parent as LinearLayout?)?.removeView(ash.greenButton)
        ash.screen.addView(ash.greenButton)

        ash.homeButtons.children.iterator().forEach { layout ->
            (layout as LinearLayout).children.iterator().forEach { textView ->
                (textView as TextView).setTextColor(ash.act.resources.getColor(R.color.nav_foreground))
            }
        }

        (ash.homeButtons.parent as LinearLayout?)?.removeView(ash.homeButtons)
        ash.screen.addView(ash.homeButtons)
    }
}