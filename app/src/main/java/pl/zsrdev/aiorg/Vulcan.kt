package pl.zsrdev.aiorg

import android.app.AlertDialog
import android.content.DialogInterface
import io.github.wulkanowy.sdk.scrapper.Scrapper
import kotlinx.coroutines.runBlocking
import java.time.LocalDate
import java.time.ZoneId
import java.util.*

fun vulcan(mail: String, pass: String, symbol: String, ash: Ash)
{

    runBlocking {
        val sdk = Scrapper()

        sdk.apply {
            loginType = Scrapper.LoginType.STANDARD
        }

        var students = sdk.let {
            it.baseUrl = "https://vulcan.net.pl"
            it.email = mail
            it.password = pass
            it.symbol = symbol
            it.getStudents()
        }

        // print(students[0])

        sdk.apply {
            classId = students[0].classId
            studentId = students[0].studentId
            schoolSymbol = students[0].schoolSymbol
        }

        students[0].semesters.forEach {
            val now = LocalDate.now()
            if (it.start.isBefore(now) && it.end.isAfter(now)) {
                // print(it)

                sdk.apply {
                    diaryId = it.diaryId
                }

                // print(sdk.getGrades(it.semesterId))
            }
        }

        var nowDate = LocalDate.now()
        var startDate = nowDate.minusDays(nowDate.dayOfWeek.value.toLong() - 1)
        var endDate = startDate.plusDays(5)

        var timetableFull = sdk.getTimetableFull(startDate, endDate)

        timetableFull.lessons.forEach {
            if (!ash.userData.subjects.contains(it.subject))
                ash.userData.subjects.add(it.subject)

            ash.userData.timetable[it.date.dayOfWeek.value - 1].add(
                ash.userData.subjects.indexOf(it.subject)
            )
        }

        var grades = sdk.getGrades(students[0].semesters[0].semesterId)

        grades.first.forEach {
            var gradeIndex = 0
            var realDescription = ash.act.getString(R.string.nan)
            for (n in 0 until timetableFull.lessons.size)
            {
                if (it.subject == timetableFull.lessons[n].subject)
                {
                    gradeIndex = n
                    break
                }
            }
            if (it.description.orEmpty() != "")
            {
                realDescription = it.description.toString()
            }
            if (it.value.toDouble() != 0.0)
            {
                ash.userData.grades.add(
                    Grade(
                        gradeIndex, it.value.toDouble(), it.weightValue.toInt(), realDescription
                    )
                )
            }

        }

        var exams = sdk.getExams(nowDate, nowDate.plusDays(5))

        exams.forEach {
            var subjectInt = 0
            for (n in 0 until timetableFull.lessons.size)
            {
                if (it.subject == timetableFull.lessons[n].subject)
                {
                    subjectInt = n
                    break
                }
            }

            ash.userData.exams.add(
                // TODO() val input_weight: Int = [Zapytaj uytkownika jaka waga sprawdzianu]

            Exam(
                subjectInt, 1, Date.from(it.date.atZone(ZoneId.systemDefault()).toInstant()), it.description

                )
            )
        }

    }

    dumpManual(ash.userData)
}