package pl.zsrdev.aiorg.screen

import android.util.TypedValue
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.annotation.Dimension
import androidx.core.view.children
import pl.zsrdev.aiorg.Ash
import pl.zsrdev.aiorg.R
import java.text.SimpleDateFormat
import java.util.*

class HomeExams(private val a: Ash) : Screen(a) {
    override fun show() {
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.US)

        val paddingVertical = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16.0f, ash.act.resources.displayMetrics).toInt()
        val paddingHorizontal = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8.0f, ash.act.resources.displayMetrics).toInt()

        ash.headerPlain.text = ash.act.resources.getText(R.string.exams_label)
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
            val exams = ash.userData.getExamsBySubject(index)

            if (exams.size > 0) {
                val subjectView = TextView(ash.act)
                subjectView.text = s
                subjectView.setTextColor(-1)
                subjectView.gravity = Gravity.CENTER
                subjectView.textSize = 36.0f
                scrollLayout.addView(subjectView)

                exams.forEach {
                    val examView = TextView(ash.act)
                    examView.text = "${ash.act.getString(R.string.date)}: ${formatter.format(it.date)}\n${ash.act.getString(R.string.weight)}: ${it.weight} \n${ash.act.getString(R.string.description)}: ${it.description}\n"
                    examView.textSize = 18.0f
                    scrollLayout.addView(examView)
                }
            }
        }

        if (scrollLayout.childCount == 0)
        {
            val infoView = TextView(ash.act)
            infoView.setTextColor(-1)
            infoView.gravity = Gravity.CENTER
            infoView.textSize = 36.0f
            infoView.text = ash.act.getString(R.string.no_exams)
            scrollLayout.addView(infoView)
        }



        val examAdd = TextView(ash.act)
        examAdd.gravity = Gravity.CENTER
        examAdd.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        examAdd.text = "+"
        examAdd.setTextSize(Dimension.SP, 20.0f)
        examAdd.setTextColor(-15540377)
        examAdd.setTypeface(ash.act.resources.getFont(R.font.fa_solid))
        examAdd.setPadding(paddingHorizontal, paddingVertical, paddingHorizontal, paddingVertical)

        examAdd.setOnClickListener {
//                showManualTimetableSelect(index)
//            ash.manualTimetableSelect.dayIndex = index
//            ash.showScreen(ash.manualTimetableSelect)
            ash.manualExam.reset()
            ash.showScreen(ash.manualExam)
        }

        scrollLayout.addView(examAdd)




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

        ash.homeButtonsExams.children.iterator().forEach { textView ->
            (textView as TextView).setTextColor(ash.act.resources.getColor(R.color.nav_foreground_selected))
        }

        (ash.homeButtons.parent as LinearLayout?)?.removeView(ash.homeButtons)
        ash.screen.addView(ash.homeButtons)
    }
}