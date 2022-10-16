package pl.zsrdev.aiorg.screen

import android.util.TypedValue
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.annotation.Dimension
import pl.zsrdev.aiorg.Ash
import pl.zsrdev.aiorg.R

class ManualTimetableSelect(private val a: Ash) : Screen(a) {
    var dayIndex = 0

    override fun show() {
        ash.headerBackText.text = ash.act.getString(R.string.choose_subject)
        ash.headerBackIcon.setOnClickListener { ash.showScreen(ash.manualTimetable) }
        (ash.headerBack.parent as LinearLayout?)?.removeView(ash.headerBack)
        ash.screen.addView(ash.headerBack)

        val scrollView = ScrollView(ash.act)
        scrollView.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, 0, 91.0f
        )
        ash.screen.addView(scrollView)

        val subjectsLayout = LinearLayout(ash.act)
        subjectsLayout.orientation = LinearLayout.VERTICAL
        scrollView.addView(subjectsLayout)

        val paddingVertical = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16.0f, ash.act.resources.displayMetrics).toInt()
        val paddingHorizontal = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8.0f, ash.act.resources.displayMetrics).toInt()

        ash.userData.subjects.forEachIndexed { index, s ->
            val subjectText = TextView(ash.act)
            subjectText.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            subjectText.text = s
            subjectText.setTextSize(Dimension.SP, 20.0f)
            subjectText.setTextColor(ash.act.resources.getColor(R.color.header_foreground))
            subjectText.setPadding(paddingHorizontal, paddingVertical, paddingHorizontal, paddingVertical)

            subjectText.setOnClickListener {
                ash.userData.timetable[dayIndex].add(index)
                ash.showScreen(ash.manualTimetable)
            }

            subjectsLayout.addView(subjectText)
        }
    }
}