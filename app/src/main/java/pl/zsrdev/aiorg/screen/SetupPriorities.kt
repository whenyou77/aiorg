package pl.zsrdev.aiorg.screen

import android.util.TypedValue
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.annotation.Dimension
import pl.zsrdev.aiorg.Ash
import pl.zsrdev.aiorg.R
import pl.zsrdev.aiorg.dumpConfig
import pl.zsrdev.aiorg.dumpManual

class SetupPriorities(private val a: Ash) : Screen(a) {
    override fun show() {
        ash.headerBackText.text = ash.act.getString(R.string.priorities)
        ash.headerBackIcon.setOnClickListener { ash.showScreen(ash.manualTimetable) }
        (ash.headerBack.parent as LinearLayout?)?.removeView(ash.headerBack)
        ash.screen.addView(ash.headerBack)

        val scrollView = ScrollView(ash.act)
        scrollView.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, 0, 85.0f
        )
        ash.screen.addView(scrollView)

        var timetableEmpty = false

        ash.userData.timetable.forEach {
            if (it.isEmpty()) timetableEmpty = true
        }

        if (timetableEmpty)
        {
            ash.greenButton.setBackgroundColor(-8355712)
            ash.greenButton.setOnClickListener(null)
        }
        else
        {
            ash.greenButton.setBackgroundColor(-16733132)
            ash.greenButton.setOnClickListener {
                ash.configFile.writeText(dumpConfig(ash.configData))
//                // TODO show hoursPerDay and priorities configuration
                ash.showScreen(ash.home)
            }
        }

        (ash.greenButton.parent as LinearLayout?)?.removeView(ash.greenButton)
        ash.screen.addView(ash.greenButton)

        val scrollLayout = LinearLayout(ash.act)
        scrollLayout.orientation = LinearLayout.VERTICAL
        scrollView.addView(scrollLayout)

        val paddingVertical = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16.0f, ash.act.resources.displayMetrics).toInt()
        val paddingHorizontal = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8.0f, ash.act.resources.displayMetrics).toInt()

        if (ash.configData.priorities.size != ash.userData.subjects.size)
            ash.configData.priorities = Array(ash.userData.subjects.size) { 5 }

        ash.userData.subjects.forEachIndexed { index, s ->
            val subjectLayout = LinearLayout(ash.act)
            subjectLayout.orientation = LinearLayout.HORIZONTAL

            val subjectName = TextView(ash.act)
            subjectName.layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 65.0f)
            subjectName.text = s
            subjectName.setTextSize(Dimension.SP, 20.0f)
            subjectName.setTextColor(ash.act.resources.getColor(R.color.header_foreground))
            subjectName.setPadding(paddingHorizontal, paddingVertical, paddingHorizontal, paddingVertical)

            val priorityMinus = TextView(ash.act)
            priorityMinus.gravity = Gravity.CENTER
            priorityMinus.layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 10.0f)
            priorityMinus.text = "-"
            priorityMinus.setTextSize(Dimension.SP, 20.0f)
            priorityMinus.setTextColor(-65536)
            priorityMinus.setTypeface(ash.act.resources.getFont(R.font.fa_solid))
            priorityMinus.setPadding(paddingHorizontal, paddingVertical, paddingHorizontal, paddingVertical)

            val priorityNumber = TextView(ash.act)
            priorityNumber.gravity = Gravity.CENTER
            priorityNumber.layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 15.0f)
            priorityNumber.text = ash.configData.priorities[index].toString()
            priorityNumber.setTextSize(Dimension.SP, 20.0f)
            priorityNumber.setTextColor(ash.act.resources.getColor(R.color.header_foreground))
            priorityNumber.setPadding(paddingHorizontal, paddingVertical, paddingHorizontal, paddingVertical)

            val priorityPlus = TextView(ash.act)
            priorityPlus.gravity = Gravity.CENTER
            priorityPlus.layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 10.0f)
            priorityPlus.text = "+"
            priorityPlus.setTextSize(Dimension.SP, 20.0f)
            priorityPlus.setTextColor(-15540377)
            priorityPlus.setTypeface(ash.act.resources.getFont(R.font.fa_solid))
            priorityPlus.setPadding(paddingHorizontal, paddingVertical, paddingHorizontal, paddingVertical)

            priorityMinus.setOnClickListener {
                if (ash.configData.priorities[index] > 1) {
                    ash.configData.priorities[index]--
                    ash.showScreen(this)
                }
            }

            priorityPlus.setOnClickListener {
                if (ash.configData.priorities[index] < 10) {
                    ash.configData.priorities[index]++
                    ash.showScreen(this)
                }
            }

            subjectLayout.addView(subjectName)
            subjectLayout.addView(priorityMinus)
            subjectLayout.addView(priorityNumber)
            subjectLayout.addView(priorityPlus)

            scrollLayout.addView(subjectLayout)
        }
    }
}