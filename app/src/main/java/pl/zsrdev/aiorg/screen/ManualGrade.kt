package pl.zsrdev.aiorg.screen

import android.util.TypedValue
import android.view.Gravity
import android.widget.*
import androidx.annotation.Dimension
import pl.zsrdev.aiorg.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class ManualGrade(private val a: Ash) : Screen(a) {
    private val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.US)

    var gradeSubject = -1
    var gradeWeight = 1
    var gradeValue = 1

    private val scrollView = ScrollView(ash.act)
    private val scrollLayout = LinearLayout(ash.act)

    private val subjectText = TextView(ash.act)

    private val valueLayout = LinearLayout(ash.act)
    private val valueText = TextView(ash.act)
    private val valueMinus = TextView(ash.act)
    private val valueNumber = TextView(ash.act)
    private val valuePlus = TextView(ash.act)

    private val weightLayout = LinearLayout(ash.act)
    private val weightText = TextView(ash.act)
    private val weightMinus = TextView(ash.act)
    private val weightNumber = TextView(ash.act)
    private val weightPlus = TextView(ash.act)

    private val descriptionInput = EditText(ash.act)

    init {
        val paddingVertical = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16.0f, ash.act.resources.displayMetrics).toInt()
        val paddingHorizontal = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8.0f, ash.act.resources.displayMetrics).toInt()

        scrollView.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, 0, 85.0f
        )
        ash.screen.addView(scrollView)

        scrollLayout.orientation = LinearLayout.VERTICAL
        scrollView.addView(scrollLayout)



//        subjectText.text = "Przedmiot: " + (if (examSubject == -1) "NIE WYBRANO" else ash.userData.subjects[examSubject])
        subjectText.setTextSize(Dimension.SP, 18.0f)
        subjectText.setTextColor(ash.act.resources.getColor(R.color.header_foreground))
        subjectText.setPadding(paddingHorizontal, paddingVertical, paddingHorizontal, paddingVertical)

        subjectText.setOnClickListener {
            ash.showScreen(ash.manualGradeSelect)
        }







        valueLayout.orientation = LinearLayout.HORIZONTAL

        valueText.layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 65.0f)
        valueText.text = ash.act.getString(R.string.grade)
        valueText.setTextSize(Dimension.SP, 18.0f)
        valueText.setTextColor(ash.act.resources.getColor(R.color.header_foreground))
        valueText.setPadding(paddingHorizontal, paddingVertical, paddingHorizontal, paddingVertical)

        valueMinus.gravity = Gravity.CENTER
        valueMinus.layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 10.0f)
        valueMinus.text = "-"
        valueMinus.setTextSize(Dimension.SP, 18.0f)
        valueMinus.setTextColor(-65536)
        valueMinus.setTypeface(ash.act.resources.getFont(R.font.fa_solid))
        valueMinus.setPadding(paddingHorizontal, paddingVertical, paddingHorizontal, paddingVertical)

        valueNumber.gravity = Gravity.CENTER
        valueNumber.layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 15.0f)
        valueNumber.setTextSize(Dimension.SP, 18.0f)
        valueNumber.setTextColor(ash.act.resources.getColor(R.color.header_foreground))
        valueNumber.setPadding(paddingHorizontal, paddingVertical, paddingHorizontal, paddingVertical)

        valuePlus.gravity = Gravity.CENTER
        valuePlus.layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 10.0f)
        valuePlus.text = "+"
        valuePlus.setTextSize(Dimension.SP, 18.0f)
        valuePlus.setTextColor(-15540377)
        valuePlus.setTypeface(ash.act.resources.getFont(R.font.fa_solid))
        valuePlus.setPadding(paddingHorizontal, paddingVertical, paddingHorizontal, paddingVertical)

        valueMinus.setOnClickListener {
            if (gradeValue > 1) {
                gradeValue--
                ash.showScreen(this)
            }
        }

        valuePlus.setOnClickListener {
            if (gradeValue < 6) {
                gradeValue++
                ash.showScreen(this)
            }
        }

        valueLayout.addView(valueText)
        valueLayout.addView(valueMinus)
        valueLayout.addView(valueNumber)
        valueLayout.addView(valuePlus)





        weightLayout.orientation = LinearLayout.HORIZONTAL

        weightText.layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 65.0f)
        weightText.text = ash.act.getString(R.string.weight)
        weightText.setTextSize(Dimension.SP, 18.0f)
        weightText.setTextColor(ash.act.resources.getColor(R.color.header_foreground))
        weightText.setPadding(paddingHorizontal, paddingVertical, paddingHorizontal, paddingVertical)

        weightMinus.gravity = Gravity.CENTER
        weightMinus.layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 10.0f)
        weightMinus.text = "-"
        weightMinus.setTextSize(Dimension.SP, 18.0f)
        weightMinus.setTextColor(-65536)
        weightMinus.setTypeface(ash.act.resources.getFont(R.font.fa_solid))
        weightMinus.setPadding(paddingHorizontal, paddingVertical, paddingHorizontal, paddingVertical)

        weightNumber.gravity = Gravity.CENTER
        weightNumber.layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 15.0f)
        weightNumber.setTextSize(Dimension.SP, 18.0f)
        weightNumber.setTextColor(ash.act.resources.getColor(R.color.header_foreground))
        weightNumber.setPadding(paddingHorizontal, paddingVertical, paddingHorizontal, paddingVertical)

        weightPlus.gravity = Gravity.CENTER
        weightPlus.layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 10.0f)
        weightPlus.text = "+"
        weightPlus.setTextSize(Dimension.SP, 18.0f)
        weightPlus.setTextColor(-15540377)
        weightPlus.setTypeface(ash.act.resources.getFont(R.font.fa_solid))
        weightPlus.setPadding(paddingHorizontal, paddingVertical, paddingHorizontal, paddingVertical)

        weightMinus.setOnClickListener {
            if (gradeWeight > 1) {
                gradeWeight--
                ash.showScreen(this)
            }
        }

        weightPlus.setOnClickListener {
            if (gradeWeight < 10) {
                gradeWeight++
                ash.showScreen(this)
            }
        }

        weightLayout.addView(weightText)
        weightLayout.addView(weightMinus)
        weightLayout.addView(weightNumber)
        weightLayout.addView(weightPlus)



        descriptionInput.setTextColor(ash.act.resources.getColor(R.color.header_foreground, ash.act.theme))
        descriptionInput.setHintTextColor(-10461088)
        descriptionInput.hint = ash.act.getString(R.string.description)
        descriptionInput.setTextSize(Dimension.SP, 18.0f)
        descriptionInput.setPadding(paddingHorizontal, paddingVertical, paddingHorizontal, paddingVertical)




        scrollLayout.addView(subjectText)
        scrollLayout.addView(valueLayout)
        scrollLayout.addView(weightLayout)
        scrollLayout.addView(descriptionInput)
    }

    fun reset() {
        gradeSubject = -1
        gradeWeight = 1
        gradeValue = 5

        descriptionInput.text.clear()
    }

    override fun show() {
        ash.headerBackText.text = ash.act.getString(R.string.new_grade)
        ash.headerBackIcon.setOnClickListener { ash.showScreen(ash.homeGrades) }
        (ash.headerBack.parent as LinearLayout?)?.removeView(ash.headerBack)
        ash.screen.addView(ash.headerBack)

        subjectText.text = "${ash.act.getString(R.string.subject)}: " + (if (gradeSubject == -1) "${ash.act.getString(R.string.not_chosen)}" else ash.userData.subjects[gradeSubject])
        valueNumber.text = gradeValue.toString()
        weightNumber.text = gradeWeight.toString()

        (scrollView.parent as ScrollView?)?.removeView(scrollView)
        ash.screen.addView(scrollView)

        if (gradeSubject == -1)
        {
            ash.greenButton.setBackgroundColor(-8355712)
            ash.greenButton.setOnClickListener(null)
        }
        else
        {
            ash.greenButton.setBackgroundColor(-16733132)
            ash.greenButton.setOnClickListener {
                ash.userData.grades.add(Grade(gradeSubject, gradeValue.toDouble(), gradeWeight, descriptionInput.text.toString()))
                ash.manualFile.writeText(dumpManual(ash.userData))
                ash.showScreen(ash.homeGrades)
            }
        }

        ash.greenButton.text = ash.act.getString(R.string.add)
        (ash.greenButton.parent as LinearLayout?)?.removeView(ash.greenButton)
        ash.screen.addView(ash.greenButton)
    }
}