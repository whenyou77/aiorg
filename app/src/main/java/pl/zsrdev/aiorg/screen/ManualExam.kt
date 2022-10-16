package pl.zsrdev.aiorg.screen

import android.util.TypedValue
import android.view.Gravity
import android.widget.*
import androidx.annotation.Dimension
import org.w3c.dom.Text
import pl.zsrdev.aiorg.Ash
import pl.zsrdev.aiorg.Exam
import pl.zsrdev.aiorg.R
import pl.zsrdev.aiorg.dumpManual
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class ManualExam(private val a: Ash) : Screen(a) {
    private val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.US)

    var examSubject = -1
    var examWeight = 1

    private val scrollView = ScrollView(ash.act)
    private val scrollLayout = LinearLayout(ash.act)

    private val subjectText = TextView(ash.act)

    private val weightLayout = LinearLayout(ash.act)
    private val weightText = TextView(ash.act)
    private val weightMinus = TextView(ash.act)
    private val weightNumber = TextView(ash.act)
    private val weightPlus = TextView(ash.act)

    private val dateLayout = LinearLayout(ash.act)
    private val dateText = TextView(ash.act)
    private val dateInput = EditText(ash.act)

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
            ash.showScreen(ash.manualExamSelect)
        }



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
//        weightNumber.text = examWeight.toString()
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
            if (examWeight > 1) {
                examWeight--
                ash.showScreen(this)
            }
        }

        weightPlus.setOnClickListener {
            if (examWeight < 10) {
                examWeight++
                ash.showScreen(this)
            }
        }

        weightLayout.addView(weightText)
        weightLayout.addView(weightMinus)
        weightLayout.addView(weightNumber)
        weightLayout.addView(weightPlus)



        dateLayout.orientation = LinearLayout.HORIZONTAL

        dateText.maxLines = 1
        dateText.gravity = Gravity.CENTER
        dateText.layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f)
        dateText.text = "${ash.act.getString(R.string.date)}: (YYYY-MM-DD)"
        dateText.setTextSize(Dimension.SP, 18.0f)
        dateText.setTextColor(ash.act.resources.getColor(R.color.header_foreground))
        dateText.setPadding(paddingHorizontal, paddingVertical, paddingHorizontal, paddingVertical)

        dateInput.setTextColor(ash.act.resources.getColor(R.color.header_foreground, ash.act.theme))
        dateInput.setHintTextColor(-10461088)
        dateInput.hint = formatter.format(Calendar.getInstance().time)
        dateInput.layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f)
        dateInput.setTextSize(Dimension.SP, 18.0f)
        dateInput.setPadding(paddingHorizontal, paddingVertical, paddingHorizontal, paddingVertical)

        dateLayout.addView(dateText)
        dateLayout.addView(dateInput)



        descriptionInput.setTextColor(ash.act.resources.getColor(R.color.header_foreground, ash.act.theme))
        descriptionInput.setHintTextColor(-10461088)
        descriptionInput.hint = ash.act.getString(R.string.description)
        descriptionInput.setTextSize(Dimension.SP, 18.0f)
        descriptionInput.setPadding(paddingHorizontal, paddingVertical, paddingHorizontal, paddingVertical)




        scrollLayout.addView(subjectText)
        scrollLayout.addView(weightLayout)
        scrollLayout.addView(dateLayout)
        scrollLayout.addView(descriptionInput)
    }

    fun reset() {
        examSubject = -1
        examWeight = 1

        dateInput.text.clear()
        descriptionInput.text.clear()
    }

    override fun show() {
        ash.headerBackText.text = ash.act.resources.getText(R.string.new_exam)
        ash.headerBackIcon.setOnClickListener { ash.showScreen(ash.homeExams) }
        (ash.headerBack.parent as LinearLayout?)?.removeView(ash.headerBack)
        ash.screen.addView(ash.headerBack)

        subjectText.text = "${ash.act.resources.getText(R.string.subject)}: " + (if (examSubject == -1) "${ash.act.resources.getText(R.string.not_chosen)}" else ash.userData.subjects[examSubject])
        weightNumber.text = examWeight.toString()

        (scrollView.parent as ScrollView?)?.removeView(scrollView)
        ash.screen.addView(scrollView)

        if (examSubject == -1)
        {
            ash.greenButton.setBackgroundColor(-8355712)
            ash.greenButton.setOnClickListener(null)
        }
        else
        {
            ash.greenButton.setBackgroundColor(-16733132)
            ash.greenButton.setOnClickListener {
                try {
                    val date = formatter.parse(dateInput.text.toString())
                    ash.userData.exams.add(Exam(examSubject, examWeight, date, descriptionInput.text.toString()))
                    ash.manualFile.writeText(dumpManual(ash.userData))
                    ash.showScreen(ash.homeExams)
                } catch (e: ParseException) {
                    Toast.makeText(ash.act, ash.act.resources.getText(R.string.bad_date), Toast.LENGTH_SHORT).show()
                }
            }
        }

        ash.greenButton.text = ash.act.resources.getText(R.string.add)
        (ash.greenButton.parent as LinearLayout?)?.removeView(ash.greenButton)
        ash.screen.addView(ash.greenButton)
    }
}