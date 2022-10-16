package pl.zsrdev.aiorg

import android.graphics.Typeface
import android.os.Build
import android.util.TypedValue
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.Dimension
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import pl.zsrdev.aiorg.screen.*
import java.io.File
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
class Ash(val act: AppCompatActivity) {
    var date = Calendar.getInstance().time

    val weekdayNames = arrayOf(act.getString(R.string.monday), act.getString(R.string.tuesday), act.getString(R.string.wednesday), act.getString(R.string.thursday), act.getString(R.string.friday))

    val configFile = File(act.filesDir, "config.json")
    val manualFile = File(act.filesDir, "manual.json")

    val configData = ConfigData()
    val userData = UserData()

    val screen = act.findViewById<LinearLayout>(R.id.screen)

    val headerBackIcon = TextView(act)
    val headerBackText = TextView(act)
    val headerBack = LinearLayout(act)
    val headerPlain = TextView(act)

    val greenButton = TextView(act)

    val homeButtons = LinearLayout(act)
    val homeButtonsTimetable = LinearLayout(act)
    val homeButtonsTimetableText = TextView(act)
    val homeButtonsTimetableIcon = TextView(act)
    val homeButtonsExams = LinearLayout(act)
    val homeButtonsExamsText = TextView(act)
    val homeButtonsExamsIcon = TextView(act)
    val homeButtonsGrades = LinearLayout(act)
    val homeButtonsGradesText = TextView(act)
    val homeButtonsGradesIcon = TextView(act)
    val homeButtonsSettings = LinearLayout(act)
    val homeButtonsSettingsText = TextView(act)
    val homeButtonsSettingsIcon = TextView(act)

    val setupMethod = SetupMethod(this)
    val setupPriorities = SetupPriorities(this)

    val manualSubjects = ManualSubjects(this)
    val manualTimetable = ManualTimetable(this)
    val manualTimetableSelect = ManualTimetableSelect(this)

    val manualExam = ManualExam(this)
    val manualExamSelect = ManualExamSelect(this)

    val manualGrade = ManualGrade(this)
    val manualGradeSelect = ManualGradeSelect(this)

    val home = Home(this)
    val homeTimetable = HomeTimetable(this)
    val homeExams = HomeExams(this)
    val homeGrades = HomeGrades(this)
    val homeSettings = HomeSettings(this)
    val vulcanLogin = VulcanLogin(this)

    val studyMode = StudyMode(this)

    init {
        val padding = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 16.0f, act.resources.displayMetrics
        ).toInt()

        val paddingBottom = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP, 3.0f, act.resources.displayMetrics
        ).toInt()

        // =========================================================================================
        // HEADER WITH BACK BUTTON
        // =========================================================================================

        headerBack.setBackgroundColor(act.resources.getColor(R.color.header_background))
        headerBack.orientation = LinearLayout.HORIZONTAL
        headerBack.gravity = Gravity.CENTER_VERTICAL
        headerBack.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, 0, 9.0f
        )

        headerBackIcon.text = "\uF060"
        headerBackIcon.setTextSize(Dimension.SP, 20.0f)
        headerBackIcon.setTextColor(act.resources.getColor(R.color.header_foreground))
        // TODO font setting was wrong...
        headerBackIcon.setTypeface(act.resources.getFont(R.font.fa_solid))
        headerBackIcon.setPadding(padding, 0, padding, 0)

        headerBackText.setTextSize(Dimension.SP, 20.0f)
        headerBackText.setTextColor(act.resources.getColor(R.color.header_foreground))

        headerBack.addView(headerBackIcon)
        headerBack.addView(headerBackText)

        // =========================================================================================
        // HEADER WITH TEXT ONLY
        // =========================================================================================

        headerPlain.setTextSize(Dimension.SP, 20.0f)
        headerPlain.setTextColor(act.resources.getColor(R.color.header_foreground))
        headerPlain.setBackgroundColor(act.resources.getColor(R.color.header_background))
        headerPlain.gravity = Gravity.CENTER_VERTICAL
        headerPlain.setPadding(padding, 0, padding, 0)
        headerPlain.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, 0, 9.0f
        )

        // =========================================================================================
        // GREEN BUTTON
        // =========================================================================================

        greenButton.isAllCaps = true
        greenButton.text = act.getString(R.string.next)
        greenButton.setTypeface(greenButton.typeface, Typeface.BOLD)
        greenButton.gravity = Gravity.CENTER
        greenButton.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, 0, 6.0f
        )
        greenButton.setTextSize(Dimension.SP, 18.0f)
        greenButton.setBackgroundColor(-16733132)
        greenButton.setTextColor(-1)

        // =========================================================================================
        // HOME BUTTONS: TIMETABLE
        // =========================================================================================

        homeButtonsTimetable.gravity = Gravity.BOTTOM
        homeButtonsTimetable.orientation = LinearLayout.VERTICAL
        homeButtonsTimetable.setOnClickListener { showScreen(homeTimetable) }
        homeButtonsTimetable.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f
        )

        homeButtonsTimetableIcon.typeface = act.resources.getFont(R.font.fa_regular)
        homeButtonsTimetableIcon.gravity = Gravity.CENTER
        homeButtonsTimetableIcon.text = act.getText(R.string.timetable_icon)
        homeButtonsTimetableIcon.setTextColor(act.resources.getColor(R.color.nav_foreground))
        homeButtonsTimetableIcon.setTextSize(Dimension.SP, 20.0f)
        homeButtonsTimetableIcon.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
        )

        homeButtonsTimetableText.gravity = Gravity.CENTER
        homeButtonsTimetableText.text = act.getText(R.string.timetable_label)
        homeButtonsTimetableText.setTextColor(act.resources.getColor(R.color.nav_foreground))
        homeButtonsTimetableText.setTextSize(Dimension.SP, 12.0f)
        homeButtonsTimetableText.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
        )

        homeButtonsTimetable.addView(homeButtonsTimetableIcon)
        homeButtonsTimetable.addView(homeButtonsTimetableText)

        // =========================================================================================
        // HOME BUTTONS: EXAMS
        // =========================================================================================

        homeButtonsExams.gravity = Gravity.BOTTOM
        homeButtonsExams.orientation = LinearLayout.VERTICAL
        homeButtonsExams.setOnClickListener { showScreen(homeExams) }
        homeButtonsExams.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f
        )

        homeButtonsExamsIcon.typeface = act.resources.getFont(R.font.fa_solid)
        homeButtonsExamsIcon.gravity = Gravity.CENTER
        homeButtonsExamsIcon.text = act.getText(R.string.exams_icon)
        homeButtonsExamsIcon.setTextColor(act.resources.getColor(R.color.nav_foreground))
        homeButtonsExamsIcon.setTextSize(Dimension.SP, 20.0f)
        homeButtonsExamsIcon.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
        )

        homeButtonsExamsText.gravity = Gravity.CENTER
        homeButtonsExamsText.text = act.getText(R.string.exams_label)
        homeButtonsExamsText.setTextColor(act.resources.getColor(R.color.nav_foreground))
        homeButtonsExamsText.setTextSize(Dimension.SP, 12.0f)
        homeButtonsExamsText.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
        )

        homeButtonsExams.addView(homeButtonsExamsIcon)
        homeButtonsExams.addView(homeButtonsExamsText)

        // =========================================================================================
        // HOME BUTTONS: GRADES
        // =========================================================================================

        homeButtonsGrades.gravity = Gravity.BOTTOM
        homeButtonsGrades.orientation = LinearLayout.VERTICAL
        homeButtonsGrades.setOnClickListener { showScreen(homeGrades) }
        homeButtonsGrades.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f
        )

        homeButtonsGradesIcon.typeface = act.resources.getFont(R.font.fa_solid)
        homeButtonsGradesIcon.gravity = Gravity.CENTER
        homeButtonsGradesIcon.text = act.getText(R.string.grades_icon)
        homeButtonsGradesIcon.setTextColor(act.resources.getColor(R.color.nav_foreground))
        homeButtonsGradesIcon.setTextSize(Dimension.SP, 20.0f)
        homeButtonsGradesIcon.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
        )

        homeButtonsGradesText.gravity = Gravity.CENTER
        homeButtonsGradesText.text = act.getText(R.string.grades_label)
        homeButtonsGradesText.setTextColor(act.resources.getColor(R.color.nav_foreground))
        homeButtonsGradesText.setTextSize(Dimension.SP, 12.0f)
        homeButtonsGradesText.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
        )

        homeButtonsGrades.addView(homeButtonsGradesIcon)
        homeButtonsGrades.addView(homeButtonsGradesText)

        // =========================================================================================
        // HOME BUTTONS: SETTINGS
        // =========================================================================================

        homeButtonsSettings.gravity = Gravity.BOTTOM
        homeButtonsSettings.orientation = LinearLayout.VERTICAL
        homeButtonsSettings.setOnClickListener { showScreen(homeSettings) }
        homeButtonsSettings.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f
        )

        homeButtonsSettingsIcon.typeface = act.resources.getFont(R.font.fa_solid)
        homeButtonsSettingsIcon.gravity = Gravity.CENTER
        homeButtonsSettingsIcon.text = act.getText(R.string.settings_icon)
        homeButtonsSettingsIcon.setTextColor(act.resources.getColor(R.color.nav_foreground))
        homeButtonsSettingsIcon.setTextSize(Dimension.SP, 20.0f)
        homeButtonsSettingsIcon.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
        )

        homeButtonsSettingsText.gravity = Gravity.CENTER
        homeButtonsSettingsText.text = act.getText(R.string.settings_label)
        homeButtonsSettingsText.setTextColor(act.resources.getColor(R.color.nav_foreground))
        homeButtonsSettingsText.setTextSize(Dimension.SP, 12.0f)
        homeButtonsSettingsText.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
        )

        homeButtonsSettings.addView(homeButtonsSettingsIcon)
        homeButtonsSettings.addView(homeButtonsSettingsText)

        // =========================================================================================
        // HOME BUTTONS
        // =========================================================================================

        homeButtons.setPadding(0, 0, 0, paddingBottom)
        homeButtons.orientation = LinearLayout.HORIZONTAL
        homeButtons.setBackgroundColor(act.resources.getColor(R.color.nav_background))
        homeButtons.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, 0, 7.0f
        )

        homeButtons.addView(homeButtonsTimetable)
        homeButtons.addView(homeButtonsExams)
        homeButtons.addView(homeButtonsGrades)
        homeButtons.addView(homeButtonsSettings)

    }

    fun showScreen(screen: Screen) {
        this.screen.removeAllViews()
        this.screen.gravity = Gravity.NO_GRAVITY
        screen.show()
    }
}