package pl.zsrdev.aiorg

import java.util.Date

class Exam(val subject: Int, val weight: Int, val date: Date, val description: String) {
    override fun toString(): String {
        return "(subject: $subject, weight: $weight, date: $date, description: $description)"
    }
}