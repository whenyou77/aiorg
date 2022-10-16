package pl.zsrdev.aiorg.screen

import android.graphics.Typeface
import android.os.Build
import android.util.TypedValue
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.Dimension
import androidx.annotation.RequiresApi
import pl.zsrdev.aiorg.Ash
import pl.zsrdev.aiorg.R
import pl.zsrdev.aiorg.dumpManual

class ManualTimetable(private val a: Ash) : Screen(a) {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun show() {
        ash.headerBackText.text = ash.act.getString(R.string.timetable_label)
        ash.headerBackIcon.setOnClickListener { ash.showScreen(ash.manualSubjects) }
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
            ash.greenButton.setOnClickListener {
                Toast.makeText(ash.act, ash.act.getString(R.string.no_timetable), Toast.LENGTH_SHORT).show()
            }
        }
        else
        {
            ash.greenButton.setBackgroundColor(-16733132)
            ash.greenButton.setOnClickListener {
                ash.manualFile.writeText(dumpManual(ash.userData))
                // TODO show hoursPerDay and priorities configuration
                ash.showScreen(ash.setupPriorities)
//                (ash.act as MainActivity).showHome()
            }
        }

        ash.greenButton.text = ash.act.getString(R.string.next)
        (ash.greenButton.parent as LinearLayout?)?.removeView(ash.greenButton)
        ash.screen.addView(ash.greenButton)

        val timetableLayout = LinearLayout(ash.act)
        timetableLayout.orientation = LinearLayout.VERTICAL
        scrollView.addView(timetableLayout)

        val paddingVertical = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16.0f, ash.act.resources.displayMetrics).toInt()
        val paddingHorizontal = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8.0f, ash.act.resources.displayMetrics).toInt()

        ash.userData.timetable.forEachIndexed { index, ints ->
            val dayNameText = TextView(ash.act)
            dayNameText.gravity = Gravity.CENTER
            dayNameText.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            dayNameText.text = ash.weekdayNames[index]
            dayNameText.setTextSize(Dimension.SP, 20.0f)
            dayNameText.setTextColor(ash.act.resources.getColor(R.color.header_foreground))
            dayNameText.setTypeface(dayNameText.typeface, Typeface.BOLD)
            dayNameText.setPadding(paddingHorizontal, paddingVertical, paddingHorizontal, paddingVertical)

            timetableLayout.addView(dayNameText)

            ash.userData.timetable[index].forEachIndexed { ind, i ->
                val subjectLayout = LinearLayout(ash.act)
                subjectLayout.orientation = LinearLayout.HORIZONTAL

                val subjectName = TextView(ash.act)
                subjectName.layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 17.0f)
                subjectName.text = ash.userData.subjects[i]
                subjectName.setTextSize(Dimension.SP, 20.0f)
                subjectName.setTextColor(ash.act.resources.getColor(R.color.header_foreground))
                subjectName.setPadding(paddingHorizontal, paddingVertical, paddingHorizontal, paddingVertical)

                val subjectDelete = TextView(ash.act)
                subjectDelete.gravity = Gravity.CENTER
                subjectDelete.layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 3.0f)
                subjectDelete.text = "\uF2ED"
                subjectDelete.setTextSize(Dimension.SP, 20.0f)
                subjectDelete.setTextColor(-65536)
                subjectDelete.setTypeface(ash.act.resources.getFont(R.font.fa_regular))
                subjectDelete.setPadding(paddingHorizontal, paddingVertical, paddingHorizontal, paddingVertical)

                subjectDelete.setOnClickListener {
                    ash.userData.timetable[index].removeAt(ind)
                    ash.showScreen(this)
                }

                subjectLayout.addView(subjectName)
                subjectLayout.addView(subjectDelete)

                timetableLayout.addView(subjectLayout)
            }

            val timetableAdd = TextView(ash.act)
            timetableAdd.gravity = Gravity.CENTER
            timetableAdd.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            timetableAdd.text = "+"
            timetableAdd.setTextSize(Dimension.SP, 20.0f)
            timetableAdd.setTextColor(-15540377)
            timetableAdd.setTypeface(ash.act.resources.getFont(R.font.fa_solid))
            timetableAdd.setPadding(paddingHorizontal, paddingVertical, paddingHorizontal, paddingVertical)

            timetableAdd.setOnClickListener {
//                showManualTimetableSelect(index)
                ash.manualTimetableSelect.dayIndex = index
                ash.showScreen(ash.manualTimetableSelect)
            }

            timetableLayout.addView(timetableAdd)
        }
    }
}