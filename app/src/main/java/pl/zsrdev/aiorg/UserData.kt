package pl.zsrdev.aiorg

import android.os.Build
import androidx.annotation.RequiresApi
import org.json.JSONArray
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.time.Duration
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList
import kotlin.math.*

class UserData {
    val subjects: ArrayList<String> = ArrayList()
    val grades: ArrayList<Grade> = ArrayList()
    val timetable: Array<ArrayList<Int>> = Array(5) { ArrayList() }
    val exams: ArrayList<Exam> = ArrayList()
//    val priorities: ArrayList<Int> = ArrayList()

//    val timetablePriorities = arrayOf(10.0, 5.0, 3.0, 2.0, 1.0)
//    val gradePriorities = arrayOf(20.0, 15.0, 10.0, 5.0, -5.0, -10.0)
//    val examPriorities = arrayOf(7.0, 4.0, 2.0, 1.0, 0.5)
//
//    var hoursPerDay = 3

//    @RequiresApi(Build.VERSION_CODES.O)
//    fun createSchedule(): ArrayList<StudyTime>? {
//        val dateNow = Calendar.getInstance()
//        val dateDayOfWeek = dateNow.get(Calendar.DAY_OF_WEEK) - 1
//
//        println(dateDayOfWeek)
//
//        if (dateDayOfWeek !in 0..4) return null
//
//        val listTimetable = Array(subjects.size) { 0.0 }
//        timetable.forEachIndexed { index, ints ->
//            ints.forEach {
//                listTimetable[it] = max(listTimetable[it], timetablePriorities[abs(index - dateDayOfWeek) % 5])
//            }
//        }
//
//        val listGrades = Array(subjects.size) { 0.0 }
//        subjects.forEachIndexed { index, s ->
//            val average = calcAverageFor(s)
//
//            if (average == 0.0) {
//                listGrades[index] = 0.0
//            } else {
//                val avgInt = max(1, min(6, average.roundToInt()))
//                listGrades[index] = gradePriorities[avgInt - 1]
//            }
//        }
//
//        val listExams = Array(subjects.size) { 0.0 }
//        subjects.forEachIndexed { index, s ->
//            val exam = getFirstExam(s)
//
//            if (exam != null) {
////                val daysBefore = exam.date.minusDays(LocalDate.now().toEpochDay()).toEpochDay()
//                val daysBefore = TimeUnit.DAYS.convert(exam.date.time - dateNow.time.time, TimeUnit.MILLISECONDS)
//                if (daysBefore in 1..5) // TODO proper days before exam calculation
//                    listExams[index] = examPriorities[daysBefore.toInt() - 1] * exam.weight
//            }
//        }
//
//        val listFinal = Array(subjects.size) { 0.0 }
//        listFinal.forEachIndexed { index, i ->
//            listFinal[index] = listTimetable[index] + listGrades[index] + listExams[index] + priorities[index]
//        }
//
//        val scheduledSubjects = ArrayList<Pair<Int, Double>>()
//        for (i in 0..(hoursPerDay * 2 - 2)) {
//            val index = listFinal.indices.maxByOrNull { listFinal[it] }
//            scheduledSubjects.add(Pair(index!!, listFinal[index]))
//            listFinal[index] = 0.0
//        }
//
//        var prioritySum = 0.0
//        scheduledSubjects.forEach {
//            prioritySum += it.second
//        }
//
//        val schedule: ArrayList<StudyTime> = ArrayList()
//        val breakTime = (hoursPerDay * 2.5).roundToInt()
//        val fullTime = hoursPerDay * 60 - (scheduledSubjects.size - 1) * breakTime
//
//        scheduledSubjects.forEach {
//            val minutes = (it.second / prioritySum) * fullTime
//            schedule.add(StudyTime(subjects[it.first], minutes.roundToInt()))
//            schedule.add(StudyTime("PRZERWA", breakTime))
//        }
//
//        schedule.removeLast()
//
////        println(priorities.toString())
////        println(listTimetable.contentToString())
////        println(listGrades.contentToString())
////        println(listExams.contentToString())
////        println()
////        println(listFinal.contentToString())
//
//        return schedule
//    }

    fun getFirstExam(subject: String): Exam? {
        var exam: Exam? = null

        exams.forEach {
            if (it.subject == subjects.indexOf(subject)) {
                if (Calendar.getInstance().before(it.date)) {
                    if (exam == null) exam = it
                    if (it.date.before(exam!!.date)) exam = it
                }
            }
        }

        return exam
    }

    fun getExamsBySubject(subject: Int): ArrayList<Exam> {
        val listExams = ArrayList<Exam>()
        exams.forEach { if (it.subject == subject) listExams.add(it) }
        return listExams
    }

    fun getGradesBySubject(subject: Int): ArrayList<Grade> {
        val listGrades = ArrayList<Grade>()
        grades.forEach { if (it.subject == subject) listGrades.add(it) }
        return listGrades
    }

    fun getGradesBySubject(subject: String): ArrayList<Grade> {
        val listGrades = ArrayList<Grade>()
        grades.forEach { if (it.subject == subjects.indexOf(subject)) listGrades.add(it) }
        return listGrades
    }

    fun calcAverageFor(subject: String): Double {
        val listGrades = getGradesBySubject(subject)
        var average = 0.0
        var dividend = 0

        if (listGrades.size == 0) return 0.0

        listGrades.forEach {
            average += it.value * it.weight
            dividend += it.weight
        }

        return average / dividend
    }

//    @RequiresApi(Build.VERSION_CODES.O)
    fun fromJsonString(json: String): UserData {
        val root = JSONObject(json)

        val subjectsJson = root.getJSONArray("subjects")
        for (i in 0 until subjectsJson.length())
        {
            subjects.add(subjectsJson.getString(i))
        }

//        subjectsJson.forEach {
//            subjects.add(it.toString())
//        }

        val gradesJson = root.getJSONArray("grades")
        for (i in 0 until gradesJson.length())
        {
            val it = gradesJson.getJSONObject(i)
            val subject = it.getInt("subject")
            val value = it.getDouble("value")
            val weight = it.getInt("weight")
            val description = it.getString("description")
            grades.add(Grade(subject, value, weight, description))
        }
//        gradesJson.forEach {
//            val gradeJson: JSONObject = it as JSONObject
//            val subject = it.getInt("subject")
//            val value = it.getDouble("value")
//            val weight = it.getInt("weight")
//            val description = it.getString("description")
//            grades.add(Grade(subject, value, weight, description))
//        }

        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.US)

        val examsJson = root.getJSONArray("exams")
        for (i in 0 until examsJson.length())
        {
            val it = examsJson.getJSONObject(i)
            val subject = it.getInt("subject")
            val weight = it.getInt("weight")
            val date = formatter.parse(it.getString("date"))
            val description = it.getString("description")
            exams.add(Exam(subject, weight, date!!, description))
        }

//        examsJson.forEach {
//            val examJson: JSONObject = it as JSONObject
//            val subject = it.getInt("subject")
//            val weight = it.getInt("weight")
//            val date = LocalDate.parse(it.getString("date"))
//            val description = it.getString("description")
//            exams.add(Exam(subject, weight, date, description))
//        }

        val timetableJson = root.getJSONArray("timetable")
        for (i in 0 until timetableJson.length())
        {
            val it = timetableJson.getJSONArray(i)

            for (j in 0 until it.length())
            {
                timetable[i].add(it.getInt(j))
            }
        }

//        timetableJson.forEachIndexed { index, any ->
//            val dayJson: JSONArray = any as JSONArray
//            dayJson.forEach {
//                timetable[index].add(it as Int)
//            }
//        }

//        val prioritiesJson = root.getJSONArray("priorities")
//        for (i in 0 until prioritiesJson.length())
//        {
//            priorities.add(prioritiesJson.getInt(i))
//        }
//        prioritiesJson.forEach {
//            priorities.add(it as Int)
//        }

        return this
    }

//    fun toJsonString(): String {
//        // TODO toJsonString
//        val root = JSONObject()
//
//        val subjectsJson = JSONArray().putAll(subjects)
//        root.put("subjects", subjectsJson)
//
//        val gradesJson = JSONArray()
//        grades.forEach {
//            val gradeJson = JSONObject()
//            gradeJson.put("subject", it.subject)
//            gradeJson.put("value", it.value)
//            gradeJson.put("weight", it.weight)
//            gradeJson.put("description", it.description)
//            gradesJson.put(gradeJson)
//        }
//        root.put("grades", gradesJson)
//
//        val timetableJson = JSONArray()
//        timetable.forEach {
//            timetableJson.put(JSONArray().putAll(it))
//        }
//        root.put("timetable", timetableJson)
//
//        val examsJson = JSONArray()
//        exams.forEach {
//            val examJson = JSONObject()
//            examJson.put("subject", it.subject)
//            examJson.put("date", it.date)
//            examJson.put("description", it.description)
//            examJson.put("weight", it.weight)
//            examsJson.put(examJson)
//        }
//        root.put("exams", examsJson)
//
//        val prioritiesJson = JSONArray()
//        prioritiesJson.putAll(priorities)
//        root.put("priorities", prioritiesJson)
//
//        return root.toString()
//    }
}