package pl.zsrdev.aiorg.screen

import android.view.Gravity
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.core.view.children
import pl.zsrdev.aiorg.Ash
import pl.zsrdev.aiorg.R

class HomeTimetable(private val a: Ash) : Screen(a) {
    override fun show() {
        ash.headerPlain.text = ash.act.resources.getText(R.string.timetable_label)
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

        ash.userData.timetable.forEachIndexed { index, ints ->
            val weekdayView = TextView(ash.act)
            weekdayView.text = ash.weekdayNames[index]
            weekdayView.setTextColor(-1)
            weekdayView.gravity = Gravity.CENTER
            weekdayView.textSize = 36.0f
            scrollLayout.addView(weekdayView)

            ints.forEach {
                val subjectView = TextView(ash.act)
                subjectView.text = ash.userData.subjects[it]
                subjectView.textSize = 18.0f
                scrollLayout.addView(subjectView)
            }
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

        ash.homeButtonsTimetable.children.iterator().forEach { textView ->
            (textView as TextView).setTextColor(ash.act.resources.getColor(R.color.nav_foreground_selected))
        }

        (ash.homeButtons.parent as LinearLayout?)?.removeView(ash.homeButtons)
        ash.screen.addView(ash.homeButtons)
    }
}