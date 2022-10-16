package pl.zsrdev.aiorg.screen

import android.util.TypedValue
import android.view.Gravity
import android.widget.*
import androidx.annotation.Dimension
import androidx.core.view.children
import pl.zsrdev.aiorg.Ash
import pl.zsrdev.aiorg.R
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class HomeSettings(private val a: Ash) : Screen(a) {
    override fun show() {
        ash.headerPlain.text = ash.act.resources.getText(R.string.settings_label)
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

        val paddingVertical = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16.0f, ash.act.resources.displayMetrics).toInt()
        val paddingHorizontal = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8.0f, ash.act.resources.displayMetrics).toInt()



        val deleteFiles = TextView(ash.act)
        deleteFiles.text = ash.act.getString(R.string.delete_config)
        deleteFiles.textSize = 18.0f
        deleteFiles.setOnClickListener {
            val configFile = File(ash.act.filesDir, "config.json")
            val manualFile = File(ash.act.filesDir, "manual.json")

            configFile.delete()
            manualFile.delete()

            Toast.makeText(ash.act, ash.act.getString(R.string.config_deleted), Toast.LENGTH_LONG).show()
            ash.userData.subjects.clear()
            ash.userData.grades.clear()
            ash.userData.exams.clear()
            for (y in 0..ash.userData.timetable.size-1)
            {
                ash.userData.timetable[y].clear()
            }
            ash.showScreen(ash.setupMethod)
        }
        deleteFiles.setPadding(paddingHorizontal, paddingVertical, paddingHorizontal, paddingVertical)
        scrollLayout.addView(deleteFiles)






        val hourLayout = LinearLayout(ash.act)
        hourLayout.orientation = LinearLayout.HORIZONTAL

        val hourName = TextView(ash.act)
        hourName.layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 65.0f)
        hourName.text = ash.act.resources.getText(R.string.study_hours)
        hourName.setTextSize(Dimension.SP, 20.0f)
        hourName.setTextColor(ash.act.resources.getColor(R.color.header_foreground))
        hourName.setPadding(paddingHorizontal, paddingVertical, paddingHorizontal, paddingVertical)

        val hourMinus = TextView(ash.act)
        hourMinus.gravity = Gravity.CENTER
        hourMinus.layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 10.0f)
        hourMinus.text = "-"
        hourMinus.setTextSize(Dimension.SP, 20.0f)
        hourMinus.setTextColor(-65536)
        hourMinus.setTypeface(ash.act.resources.getFont(R.font.fa_solid))
        hourMinus.setPadding(paddingHorizontal, paddingVertical, paddingHorizontal, paddingVertical)

        val hourNumber = TextView(ash.act)
        hourNumber.gravity = Gravity.CENTER
        hourNumber.layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 15.0f)
        hourNumber.text = ash.configData.hoursPerDay.toString()
        hourNumber.setTextSize(Dimension.SP, 20.0f)
        hourNumber.setTextColor(ash.act.resources.getColor(R.color.header_foreground))
        hourNumber.setPadding(paddingHorizontal, paddingVertical, paddingHorizontal, paddingVertical)

        val hourPlus = TextView(ash.act)
        hourPlus.gravity = Gravity.CENTER
        hourPlus.layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 10.0f)
        hourPlus.text = "+"
        hourPlus.setTextSize(Dimension.SP, 20.0f)
        hourPlus.setTextColor(-15540377)
        hourPlus.setTypeface(ash.act.resources.getFont(R.font.fa_solid))
        hourPlus.setPadding(paddingHorizontal, paddingVertical, paddingHorizontal, paddingVertical)

        hourMinus.setOnClickListener {
            if (ash.configData.hoursPerDay > 1) {
                ash.configData.hoursPerDay--
                ash.showScreen(this)
            }
        }

        hourPlus.setOnClickListener {
            if (ash.configData.hoursPerDay < 5) {
                ash.configData.hoursPerDay++
                ash.showScreen(this)
            }
        }

        hourLayout.addView(hourName)
        hourLayout.addView(hourMinus)
        hourLayout.addView(hourNumber)
        hourLayout.addView(hourPlus)

        scrollLayout.addView(hourLayout)










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

        ash.homeButtonsSettings.children.iterator().forEach { textView ->
            (textView as TextView).setTextColor(ash.act.resources.getColor(R.color.nav_foreground_selected))
        }

        (ash.homeButtons.parent as LinearLayout?)?.removeView(ash.homeButtons)
        ash.screen.addView(ash.homeButtons)
    }
}