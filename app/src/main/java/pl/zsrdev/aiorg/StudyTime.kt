package pl.zsrdev.aiorg

class StudyTime(val subject: String, val minutes: Int) {
    override fun toString(): String {
        return "($subject, $minutes)"
    }
}