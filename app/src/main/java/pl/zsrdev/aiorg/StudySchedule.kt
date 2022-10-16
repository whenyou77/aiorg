package pl.zsrdev.aiorg

import android.os.Build
import androidx.annotation.RequiresApi
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import kotlin.math.roundToInt

val timetablePriorities = arrayOf(10.0, 5.0, 3.0, 2.0, 1.0, 1.0, 1.0)
val gradePriorities = arrayOf(20.0, 15.0, 10.0, 5.0, -5.0, -10.0)
val examPriorities = arrayOf(7.0, 4.0, 2.0, 1.0, 0.5)

//@RequiresApi(Build.VERSION_CODES.O)
fun createSchedule(configData: ConfigData, userData: UserData): ArrayList<StudyTime>? {
    val dateNow = Calendar.getInstance()

    // TODO remove debug lines
//    dateNow.set(Calendar.DAY_OF_MONTH, 3)
//    println(dateNow)

    val dateDayOfWeek = dateNow.get(Calendar.DAY_OF_WEEK) - 1

    println(dateDayOfWeek)

//    if (dateDayOfWeek !in 0..4) return null

    val listTimetable = Array(userData.subjects.size) { 0.0 }
    userData.timetable.forEachIndexed { index, ints ->
        ints.forEach {
            listTimetable[it] = max(listTimetable[it], timetablePriorities[abs(index - dateDayOfWeek) % 7])
        }
    }

    val listGrades = Array(userData.subjects.size) { 0.0 }
    userData.subjects.forEachIndexed { index, s ->
        val average = userData.calcAverageFor(s)

        if (average == 0.0) {
            listGrades[index] = 0.0
        } else {
            val avgInt = max(1, min(6, average.roundToInt()))
            listGrades[index] = gradePriorities[avgInt - 1]
        }
    }

    val listExams = Array(userData.subjects.size) { 0.0 }
    userData.subjects.forEachIndexed { index, s ->
        val exam = userData.getFirstExam(s)

        if (exam != null) {
//                val daysBefore = exam.date.minusDays(LocalDate.now().toEpochDay()).toEpochDay()
            val daysBefore = TimeUnit.DAYS.convert(exam.date.time - dateNow.time.time, TimeUnit.MILLISECONDS)
            if (daysBefore in 1..5) // TODO proper days before exam calculation
                listExams[index] = examPriorities[daysBefore.toInt() - 1] * exam.weight
        }
    }

    val listFinal = Array(userData.subjects.size) { 0.0 }
    listFinal.forEachIndexed { index, i ->
        listFinal[index] = listTimetable[index] + listGrades[index] + listExams[index] + configData.priorities[index]
    }

    val scheduledSubjects = ArrayList<Pair<Int, Double>>()
    for (i in 0..(configData.hoursPerDay * 2 - 2)) {
        val index = listFinal.indices.maxByOrNull { listFinal[it] }
        scheduledSubjects.add(Pair(index!!, listFinal[index]))
        listFinal[index] = 0.0
    }

    var prioritySum = 0.0
    scheduledSubjects.forEach {
        prioritySum += it.second
    }

    val schedule: ArrayList<StudyTime> = ArrayList()
    val breakTime = (configData.hoursPerDay * 2.5).roundToInt()
    val fullTime = configData.hoursPerDay * 60 - (scheduledSubjects.size - 1) * breakTime

    scheduledSubjects.forEach {
        val minutes = (it.second / prioritySum) * fullTime
        schedule.add(StudyTime(userData.subjects[it.first], minutes.roundToInt()))
        schedule.add(StudyTime("Przerwa", breakTime))
    }

    schedule.removeLast()

//        println(priorities.toString())
//        println(listTimetable.contentToString())
//        println(listGrades.contentToString())
//        println(listExams.contentToString())
//        println()
//        println(listFinal.contentToString())

    return schedule
}