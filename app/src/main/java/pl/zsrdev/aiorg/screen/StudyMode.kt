package pl.zsrdev.aiorg.screen

import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.Dimension
import pl.zsrdev.aiorg.Ash
import pl.zsrdev.aiorg.R
import pl.zsrdev.aiorg.createSchedule

class StudyMode(private val a: Ash) : Screen(a) {
    val colors = arrayOf(-2677187, -13072807, -748724, -16672778, 0, -1)

    override fun show() {
        val schedule = createSchedule(ash.configData, ash.userData) ?: return

        val border = TextView(ash.act)
        border.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 20.0f)

        ash.screen.addView(border)

        schedule.forEachIndexed { index, studyTime ->
            val time = TextView(ash.act)
            time.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, studyTime.minutes / (ash.configData.hoursPerDay * 0.6f))
            time.text = "${studyTime.subject}: ${studyTime.minutes} ${ash.act.getString(R.string.mins)}"
            time.setTextColor(-1)
            time.setTextSize(Dimension.SP, 16.0f)

            if ((index % 2) == 0) time.setBackgroundColor(colors[index / 2])
            else time.setBackgroundColor(-12632257)

            ash.screen.addView(time)
        }

        val borderBottom = TextView(ash.act)
        borderBottom.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 20.0f)
        ash.screen.addView(borderBottom)
    }
}