package pl.zsrdev.aiorg

import org.json.JSONArray
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

fun updateConfig(configData: ConfigData, json: String) {
    val root = JSONObject(json)

    if (root.has("method"))
        configData.method = Method.valueOf(root.getString("method"))

    if (root.has("hoursPerDay"))
        configData.hoursPerDay = root.getInt("hoursPerDay")

    if (root.has("priorities")) {
        val priorities = root.getJSONArray("priorities")
        configData.priorities = Array(priorities.length()) { 0 }
        for (i in 0 until priorities.length())
            configData.priorities[i] = priorities.getInt(i)
    }
}

fun updateManual(userData: UserData, json: String) {
    val root = JSONObject(json)
    val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.US)

    if (root.has("subjects")) {
        val subjects = root.getJSONArray("subjects")
        for (i in 0 until subjects.length())
            userData.subjects.add(subjects.getString(i))
    }

    if (root.has("grades")) {
        val grades = root.getJSONArray("grades")
        for (i in 0 until grades.length()) {
            val it = grades.getJSONObject(i)
            val subject = it.getInt("subject")
            val value = it.getDouble("value")
            val weight = it.getInt("weight")
            val description = it.getString("description")
            userData.grades.add(Grade(subject, value, weight, description))
        }
    }

    if (root.has("exams")) {
        val exams = root.getJSONArray("exams")
        for (i in 0 until exams.length()) {
            val it = exams.getJSONObject(i)
            val subject = it.getInt("subject")
            val weight = it.getInt("weight")
            val date = formatter.parse(it.getString("date"))
            val description = it.getString("description")
            userData.exams.add(Exam(subject, weight, date!!, description))
        }
    }

    if (root.has("timetable")) {
        val timetable = root.getJSONArray("timetable")
        for (i in 0 until timetable.length()) {
            val it = timetable.getJSONArray(i)
            for (j in 0 until it.length())
                userData.timetable[i].add(it.getInt(j))
        }
    }
}

fun dumpConfig(configData: ConfigData): String {
    val root = JSONObject()

    // sourceType, hoursPerDay, priorities

    root.put("method", configData.method.name)
    root.put("hoursPerDay", configData.hoursPerDay)

    val priorities = JSONArray()
    configData.priorities.forEach {
        priorities.put(it)
    }
    root.put("priorities", priorities)

    return root.toString()
}

fun dumpManual(userData: UserData): String {
    val root = JSONObject()
    val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.US)

    // timetable, exams, grades, subjects

    val subjectsJson = JSONArray()
    userData.subjects.forEach {
        subjectsJson.put(it)
    }
    root.put("subjects", subjectsJson)

    val gradesJson = JSONArray()
    userData.grades.forEach {
        val gradeJson = JSONObject()
        gradeJson.put("subject", it.subject)
        gradeJson.put("value", it.value)
        gradeJson.put("weight", it.weight)
        gradeJson.put("description", it.description)
        gradesJson.put(gradeJson)
    }
    root.put("grades", gradesJson)

    val timetableJson = JSONArray()
    userData.timetable.forEach { dayArray ->
        val dayJson = JSONArray()
        dayArray.forEach {
            dayJson.put(it)
        }
        timetableJson.put(dayJson)
    }
    root.put("timetable", timetableJson)

    val examsJson = JSONArray()
    userData.exams.forEach {
        val examJson = JSONObject()
        examJson.put("subject", it.subject)
        examJson.put("date", formatter.format(it.date))
        examJson.put("description", it.description)
        examJson.put("weight", it.weight)
        examsJson.put(examJson)
    }
    root.put("exams", examsJson)

    return root.toString()
}