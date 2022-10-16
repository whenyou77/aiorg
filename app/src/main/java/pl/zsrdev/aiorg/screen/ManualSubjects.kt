package pl.zsrdev.aiorg.screen

import android.os.Build
import android.util.TypedValue
import android.view.Gravity
import android.widget.*
import androidx.annotation.Dimension
import androidx.annotation.RequiresApi
import pl.zsrdev.aiorg.Ash
import pl.zsrdev.aiorg.R
import pl.zsrdev.aiorg.dumpManual

class ManualSubjects(private val a: Ash) : Screen(a) {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun show() {
        ash.headerBackText.text = ash.act.getString(R.string.subject)
        ash.headerBackIcon.setOnClickListener { ash.showScreen(ash.setupMethod) }
        (ash.headerBack.parent as LinearLayout?)?.removeView(ash.headerBack)
        ash.screen.addView(ash.headerBack)

        val scrollView = ScrollView(ash.act)
        scrollView.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, 0, 85.0f
        )
        ash.screen.addView(scrollView)

        if (ash.userData.subjects.isEmpty())
        {
            ash.greenButton.setBackgroundColor(-8355712)
            ash.greenButton.setOnClickListener {
                Toast.makeText(ash.act, ash.act.getString(R.string.no_subjects), Toast.LENGTH_SHORT).show()
            }
        }
        else
        {
            ash.greenButton.setBackgroundColor(-16733132)
            ash.greenButton.setOnClickListener {
                ash.showScreen(ash.manualTimetable)
            }
        }

        ash.greenButton.text = ash.act.getString(R.string.next)
        (ash.greenButton.parent as LinearLayout?)?.removeView(ash.greenButton)
        ash.screen.addView(ash.greenButton)

        val subjectsLayout = LinearLayout(ash.act)
        subjectsLayout.orientation = LinearLayout.VERTICAL
        scrollView.addView(subjectsLayout)

        val paddingVertical = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16.0f, ash.act.resources.displayMetrics).toInt()
        val paddingHorizontal = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8.0f, ash.act.resources.displayMetrics).toInt()

        ash.userData.subjects.forEachIndexed { index, s ->
            val subjectLayout = LinearLayout(ash.act)
            subjectLayout.orientation = LinearLayout.HORIZONTAL

            val subjectName = TextView(ash.act)
            subjectName.layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 17.0f)
            subjectName.text = s
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
                ash.userData.grades.forEachIndexed { i,grade ->
                    if (grade.subject == index)
                    {
                        ash.userData.grades.removeAt(i)
                    }
                }
                ash.userData.exams.forEachIndexed { i,exam ->
                    if (exam.subject == index)
                    {
                        ash.userData.exams.removeAt(i)
                    }
                }
                for (y in 0 until ash.userData.timetable.size)
                {
                    if (ash.userData.timetable[y].contains(index))
                    {
                        ash.userData.timetable[y].removeAt(index)
                    }
                }
                ash.userData.subjects.removeAt(index)
                dumpManual(ash.userData)
                ash.showScreen(this)
            }

            subjectLayout.addView(subjectName)
            subjectLayout.addView(subjectDelete)

            subjectsLayout.addView(subjectLayout)
        }

        val subjectLayout = LinearLayout(ash.act)
        subjectLayout.orientation = LinearLayout.HORIZONTAL

        val subjectInput = EditText(ash.act)
        subjectInput.setTextColor(ash.act.resources.getColor(R.color.header_foreground, ash.act.theme))
        subjectInput.setHintTextColor(-10461088)
        subjectInput.hint = ash.act.getString(R.string.subject)
        subjectInput.layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 17.0f)
        subjectInput.setTextSize(Dimension.SP, 20.0f)
        subjectInput.setTextColor(ash.act.resources.getColor(R.color.header_foreground, ash.act.theme))
        subjectInput.setPadding(paddingHorizontal, paddingVertical, paddingHorizontal, paddingVertical)

        val subjectAdd = TextView(ash.act)
        subjectAdd.gravity = Gravity.CENTER
        subjectAdd.layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 3.0f)
        subjectAdd.text = "+"
        subjectAdd.setTextSize(Dimension.SP, 20.0f)
        subjectAdd.setTextColor(-15540377)
        subjectAdd.setTypeface(ash.act.resources.getFont(R.font.fa_solid))
        subjectAdd.setPadding(paddingHorizontal, paddingVertical, paddingHorizontal, paddingVertical)

        subjectAdd.setOnClickListener {
            val text = subjectInput.text.toString()

            if (text.isEmpty()) return@setOnClickListener

            if (ash.userData.subjects.contains(text)) {
                Toast.makeText(ash.act, ash.act.getString(R.string.subject_in), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            ash.userData.subjects.add(text)
            ash.showScreen(this)
        }

        subjectLayout.addView(subjectInput)
        subjectLayout.addView(subjectAdd)

        subjectsLayout.addView(subjectLayout)
    }
}